package online.dwResources;

import java.io.IOException;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;
import toptrumps.DataCard;
import toptrumps.DataGame;
import toptrumps.DataPlayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input


/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controlled from a Web page.
 */


/**
 * TopTrumpsRESTAPI - 
 * @author Team Try-Catch - Bokyung Lee 2431088l
 * 
 * */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	TopTrumpsJSONConfiguration conf;
	
	private String deckFile;
	private int numAIPlayers;
	
	private DataGame model;
	
	/**
	 * Constructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		this.conf = conf; // added by Arnold
		
		//get the location of the deck file
		deckFile=conf.getDeckFile();
		
		//get the number of AI players.
		numAIPlayers=conf.getNumAIPlayers();
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	
	/*
	 * Needed methods from GameScreen.ftl
	 * 
	 * getRoundNumber();					>>getRoundNumber()
	 * shouldHumanSelectCategory();			>>shouldHumanSelectCategory()
	 * getDeck(); 							>>getDeckFile(), getOriginalDeckAsString() 
	 * getRoundCategory();					>>getRoundCategory()
	 * getRoundCards();						>>getRoundCards() 
	 * getRoundWinner();					>>getRoundWinner(), getRoundWinningCard()
	 * getNumberOfCardInCommonPile();		>>getNumberOfCardsInCommonPile()
	 * wasRoundDraw();						>>getRoundWasDraw() 
	 * getGamePlayers();					>>getActivePlayers(), getRoundActivePlayer() 
	 * getGameScores(); 					>>getGameResult()
	 * getGameStatistics();					>>getStatistics()
	 * getGameWinner(); 					>>getGameWinner()
	 * getPlayerDeck(playerName); 			>>getPlayerDeck(playerName), getNumberOfCardsInDeck():for all players
	 * 
	 */ 
	
	/*
	 * ****CURRENT FUNCTIONS THAT ARE USED
	 * 
	 * /game/startGame					-- startGame()
	 * /game/displayCards				-- getDeckFile()
	 * /game/categoryMenu				-- getCategoryForMenu()
	 * /game/roundNumber				-- getRoundNumber()
	 * /game/roundCards					-- getRoundCardsBeforePlayRound()
	 * /game/playRound					-- playRound()
	 * /game/numberOfCardsInCommonPile	-- getNumberOfCardsInCommonPile()
	 * /game/getRoundActivePlayer		-- getRoundActivePlayer()
	 * /game/getAIPlayerCategory		-- getAIPlayerCategory() 
	 * /game/shouldHumanSelectCategory	-- shouldHumanSelectCategory() 
	 * /game/roundCategory				-- getRoundCategory()
	 * /game/cardsLeft					-- getPlayerDeck() 
	 * /game/numberOfCardsInDeck		-- getNumberOfCardsInDeck() 
	 * /game/getRoundWinner				-- getRoundWinner()
	 * /game/gameWinner					-- getGameWinner() 
	 * /game/result/scores				-- getGameResult()
	 * /stats/statistics				-- getStatistics()
	 */

	 

	
	/**
	 * Starts the game
	 * @returns 1 if failed, 0 if successful
	 */	
	@GET
	@Path("/game/startGame")
	public int startGame(@QueryParam("numberOfAIPlayers") int numberOfAIPlayers) {
		try {
			this.numAIPlayers = numberOfAIPlayers;
			this.conf.setNumAIPlayers(numberOfAIPlayers);
			this.model = new DataGame(numberOfAIPlayers);
			this.model.startGame();
		} catch(Exception e) {
			return 1;
		}
		return 0;
	}

	
	/**
	 * Get deck file location when the game starts.
	 * @Controller.js: function displayCard 
	 * @returns String type
	 */	
	@GET
	@Path("/game/displayCards")
	public String getDeckFile() {
		return deckFile;
	}
	
	
	/**
	 * Get categories for selecting menu
	 * @Controller.js: 
	 * @returns JSONString type
	 */	
	@GET
 	@Path("/game/categoryMenu")
 	public String getCategoryForMenu() {
		List<String> listOfCategory=new ArrayList<String>();
 		for(int i=0;i<model.CATEGORYNAMES.length;i++) {
 			listOfCategory.add(model.CATEGORYNAMES[i]);
 		}		
 		String categoriesInString="";
		try {
			categoriesInString = oWriter.writeValueAsString(listOfCategory);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return categoriesInString;
	}
	
	/**
	 * Get current round number:integer.
	 * @Controller.js: function roundNumber
	 * @returns JSONString type
	 */	
	@GET
	@Path("/game/roundNumber")
	public String getRoundNumber() {
		String roundNumber="";
		try {
			roundNumber = oWriter.writeValueAsString(model.getRound().getRoundNumber());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roundNumber;
	}
	
	
	/**
	 * Get card name, card categories for all active players.
	 * @Controller.js:
	 * @return JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/roundCards")
	public String getRoundCardsBeforePlayRound() {	
		List<DataCard> listOfCards=new ArrayList<DataCard>();
		DataCard humanCard = model.getRoundHumanPlayerCardBeforePlayRound();
		if(humanCard!=null) {
			listOfCards.add(model.getRoundHumanPlayerCardBeforePlayRound());
		}
		DataCard[] cards = model.getRoundAIPlayerCardsBeforePlayRound();
		for(int i=0;i<cards.length;i++) {
			listOfCards.add(cards[i]);
		}
		
		String everyPlayersCard="";
		try {
			everyPlayersCard = oWriter.writeValueAsString(listOfCards);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return everyPlayersCard;
	}
	
	

	
	@GET
	@Path("/game/playRound")
	public String playRound(@QueryParam("category") String category){
		model.playRound(category);
		if(model.getGameState()!=DataGame.GameState.RUNNING) {
			return "" + model.getGameWinner().getName() + " at round " + model.getRound().getRoundNumber();
		}
		model.getRound().incrementRound();
		return "running";
	}
	
	

	
	/**
	 * get number of cards in common pile. which is number of active players..
	 * @return model.getNumberOfCardsInCommonPile()
	 * @throws IOException
	 * */
	@GET
	@Path("/game/numberOfCardsInCommonPile")
	public int getNumberOfCardsInCommonPile() {
		return model.getNumberOfCardsInCommonPile();
	}
	
	
	
	
	/**
	 * Get category chooser of the round:String.
	 * @Controller.js: 
	 * @returns String type
	 */	
	@GET
	@Path("/game/getRoundActivePlayer")
	public String getRoundActivePlayer(){
		return model.getRound().getRoundActivePlayer().getName();
	}
	

	
	/**
	 *Get the category that had selected from the AI players.
	 *@return human: when active player is human, result: when there is a valid result.
	 *@throws IOException
	 * */
	@GET
	@Path("/game/getAIPlayerCategory")
	public String getAIPlayerCategory() {
		String result = "";
		
		DataPlayer activePlayer = this.model.getCategoryChooser();
		int category = this.model.getBestCategoryForPlayer(activePlayer);
		
		// sometimes the first player is human and this is not accounted for here
		if(category==0) {
			return "human";
		}
		result = DataGame.CATEGORYNAMES[category-1];
		
		return result;
	}
	
	
	/**
	 * return boolean value as String whether human selects or not.
	 * @Controller.js:
	 * @return JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/shouldHumanSelectCategory")
	public String shouldHumanSelectCategory() {
		String result = "";
		
		boolean shouldHumanChooseCategory;
		
		if (this.model.getCategoryChooser().getType()==DataPlayer.PlayerType.HUMAN) {
			shouldHumanChooseCategory = true;
		} else {
			shouldHumanChooseCategory = false;
		}
		
		try {
			result = oWriter.writeValueAsString(shouldHumanChooseCategory);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	

	
	
	/**
	 * Get round category:String.
	 * @Controller.js: 
	 * @returns JSONString type
	 */	
	@GET
	@Path("/game/roundCategory")
	public String getRoundCategory(){
		String roundCategory="";
		try {
			roundCategory = oWriter.writeValueAsString(model.getRound().getRoundCategory());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roundCategory;
	}
	
	

	/**
	 * get playerName's deck number
	 * @return integer
	 */
	@GET
	@Path("/game/cardsLeft")
	public int getPlayerDeck(@QueryParam("playerName") String playerName){
		int numberOfDeck=0;
		DataPlayer[] players = model.getActivePlayers();
		if(playerName!=null) {
			for(int i=0;i<players.length;i++) {
				if(players[i].getName().equals(playerName)) {
					numberOfDeck=players[i].getDeck().size();
				}
			}
		}
		return numberOfDeck;
	}
	
	
	
	/**
	 * Call every active players from a list and return in JSONString
	 * @Controller.js: function activePlayer
	 * @returns JSONString
	 */	
	@GET
	@Path("/game/activePlayers")
	public String getActivePlayers() {
		String activePlayersAsString="";
		try {
			activePlayersAsString = oWriter.writeValueAsString(model.getActivePlayers());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activePlayersAsString;
	}
	
	
	
	
	/**
	 * Get the number of cards in deck:integer.
	 * numberOfCardsInDeck[0]=human
	 * numberOfCardsInDeck[1]=ai1
	 * numberOfCardsInDeck[2]=ai2
	 * numberOfCardsInDeck[3]=ai3
	 * numberOfCardsInDeck[4]=ai4
	 * @Controller.js: 
	 * @returns JSONString[] type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/numberOfCardsInDeck")
	public String getNumberOfCardsInDeck(){
		List<Integer> numberOfCardsInDeck=new ArrayList<Integer>();
		
		int cardsInDeck;
		for(int i=0;i<model.getActivePlayers().length;i++) {
			if(model.getActivePlayers()[i].toString().contains("human")) {
				cardsInDeck=model.getActivePlayers()[i].getDeck().size();
				numberOfCardsInDeck.add(cardsInDeck);
			}else {//stores the number of ai players cards in deck
				cardsInDeck=model.getActivePlayers()[i].getDeck().size();
				numberOfCardsInDeck.add(cardsInDeck);
			}
		}
		String numberOfCardsInDeckAsString="";
		try {
			numberOfCardsInDeckAsString = oWriter.writeValueAsString(numberOfCardsInDeck);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numberOfCardsInDeckAsString;
	}
	

	
	
	/**
	 * Get round winner's name or 'draw'
	 * @Controller.js: 
	 * @returns String type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/getRoundWinner")
	public String getRoundWinner() throws IOException{
		String result = null;
		ArrayList<DataPlayer> roundWinningPlayers = model.getRound().getRoundWinningPlayers();
		if(roundWinningPlayers.size() == 1) {
			result = roundWinningPlayers.get(0).getName();
		} else {
			result = "draw";
		}
		return result;
	}
	
	
	
	/**
	 * get game winner's name from DataPlayer
	 * @return JSONString
	 * */
	@GET
	@Path("/game/gameWinner")
	public String getGameWinner(){
		String gameWinner="";
		try {
			gameWinner = oWriter.writeValueAsString(model.getGameWinner().getName());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gameWinner;
	}
	
	
	
	/**
	 * Get game result
	 * includes:
	 * "Player scores
	 * you: score
	 * ai1: score
	 * ai2: score
	 * ai3: score
	 * ai4: score".
	 * @Controller.js:
	 * @return JSONString type
	 */	
	@GET
	@Path("/game/result/scores")
	public String getGameResult(){
		//Categories for the human player
		List<String> gameResult=new ArrayList<String>();
		
		gameResult.add("Player scores");
		for(int i=0;i<model.getAllPlayers().length;i++) {
			gameResult.add(String.format("%s : %d", model.getAllPlayers()[i].getName(), model.getAllPlayers()[i].getScore()));
		}
		
		String gameResultAsString="";
		try {
			gameResultAsString = oWriter.writeValueAsString(gameResult);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gameResultAsString;
	}	
	
	
	
	/**
	 * Get game statistics
	 * @Controller.js:
	 * @return JSONString type
	 * number of games
	 * number of human wins
	 * number of ai wins
	 * average of draws
	 * longest game
	 */	
	@GET
	@Path("/stats/statistics") // implement this as HashMap
	public String getStatistics() {	
		HashMap<String, String> statistics=new HashMap<String, String>();
		
		statistics.put("numberOfGames", DataGame.getNumberOfGames()+"");
		statistics.put("numberOfHumanWins", DataGame.getNumberOfHumanWins()+"");
		statistics.put("numberOfAIWins", DataGame.getNumberOfAIWins()+"");
		statistics.put("avgNumberOfDraws", DataGame.getAvgNumberOfDraws()+"");
		statistics.put("roundNumberOfLongestGame", DataGame.getLongestGame()+"");
		
		String statisticsAsString="";
		try {
			statisticsAsString = oWriter.writeValueAsString(statistics);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statisticsAsString;
	}
	
	@GET
	@Path("/game/allPlayersScores")
	public String getAllPlayersAndScores() {
		String result=null;
		
		DataPlayer[] players = this.model.getAllPlayers();
		
		List<HashMap<String, Object>> listOfPlayersAndScores = new ArrayList<HashMap<String, Object>>();
		
		for(DataPlayer player : players) {
			HashMap<String, Object> playersAndScores = new HashMap<String, Object>();
			playersAndScores.put("name", player.getName());
			playersAndScores.put("score", player.getScore());
			listOfPlayersAndScores.add(playersAndScores);
		}
		
		try {
			result = oWriter.writeValueAsString(listOfPlayersAndScores);
		} catch (JsonProcessingException e) {
			
		}
		
		return result;
	}
	
	
//	/**
//	 * get original deck as string. before shuffled.
//	 * @return 
//	 * @throws IOException
//	 */
//	@GET
//	@Path("/game/originalDeckAsString")
//	public String getOriginalDeckAsString() throws IOException {
//		String originalDeckAsString;
//		
//		originalDeckAsString=oWriter.writeValueAsString(model.getCompleteDeckAsArrayList());
//		return originalDeckAsString;
//	}
	

//	@GET
//	@Path("/game/categoryChooser")
//	public String getCategoryChooser() {
//		String categoryChooser="";
//		try {
//			categoryChooser = oWriter.writeValueAsString(model.getCategoryChooser());
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return categoryChooser;
//	}
	
	
	
//	/**
//	 * get boolean value whether the round was draw
//	 * @return boolean
//	 * true: was draw
//	 * false: not draw (has a winner)
//	 */
//	@GET
//	@Path("/game/roundWasDraw")
//	public boolean getRoundWasDraw() {
//		return model.getRoundWasDraw();
//	}
	
	
//	@GET
//	@Path("/game/roundWinningCards")
//	public String getRoundWinningCard() {
//		String roundWinningCard=model.getRoundWinningCard().getDescription();
//		return roundWinningCard;
//	}
	
	
//	/**
//	 * Get game end String
//	 * @Controller.js:
//	 * @return String type
//	 */	
//	@GET
//	@Path("/game/result/gameEnd")
//	public String getGameEnd(){		
//		return "Game End";
//	}	
	
	
//	/**
//	 * Print game winner
//	 * @Controller.js:
//	 * @param GameWinner:String
//	 * @return String type
//	 */	
//	@GET
//	@Path("/game/result/winner")
//	public String getWinnerOfTheGame(@QueryParam("GameWinner") String GameWinner){		
//		return GameWinner+" won the game";
//	}	
	
	
	
//	@GET
//	@Path("/game/cardsInCommonPile")
//	public DataCard[] getCardsInCommonPile() {
//		return model.getCardsInCommonPile();
//	}
	
	
	
//	@GET
//	@Path("/game/roundLastWinner")
//	public DataPlayer getRoundLastWinner() {
//		return model.getRoundLastWinner();
//	}
	
	

	
//	@GET
//	@Path("/game/roundWinningCardToString")
//	public String getRoundWinningCardToString(@QueryParam("category") String category) {
//		return model.getRoundWinningCardToString(category);
//	}
//	
//	
//	@GET
//	@Path("/game/roundWinningPlayers")
//	public ArrayList<DataPlayer> getRoundWinningPlayers() {
//		return model.getRoundWinningPlayers();
//	}
	

}
