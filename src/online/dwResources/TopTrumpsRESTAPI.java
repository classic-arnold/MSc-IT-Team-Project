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
import main.model.*;


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
 * TopTrumpsRESTAPI 
 * @author Team Try-Catch - Bokyung Lee 2431088l
 * 
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	TopTrumpsJSONConfiguration conf;

	//	private DataGame model;

	/**
	 * listOfGames when accessing two or more players in game via different devices.
	 * */
	private DataListOfGames listOfGames = new DataListOfGames();

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
		this.conf = conf;
	}

	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------

	/**
	 * Starts the game
	 * @param numberOfAIPlayers: int
	 * @returns game ID
	 */	
	@GET
	@Path("/game/startGame")
	public int startGame(@QueryParam("numberOfAIPlayers") int numberOfAIPlayers) {
		int gameID;
		this.conf.setNumAIPlayers(numberOfAIPlayers);

		DataGame game = new DataGame(numberOfAIPlayers);

		gameID = this.listOfGames.add(game);

		game.startGame();
		return gameID;
	}

	/**
	 * return boolean value as String whether human selects or not.
	 * @param gameID: int
	 * @return JSONString type
	 */	
	@GET
	@Path("/game/shouldHumanSelectCategory")
	public String shouldHumanSelectCategory(@QueryParam("gameID") int gameID) {
		String result = "";

		boolean shouldHumanChooseCategory;

		DataGame game = this.listOfGames.get(gameID);

		if (game.getCategoryChooser().getType()==DataPlayer.PlayerType.HUMAN) {
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
	 * Get current round number:integer.
	 * @param gameID: int
	 * @returns JSONString type
	 */	
	@GET
	@Path("/game/roundNumber")
	public String getRoundNumber(@QueryParam("gameID") int gameID) {
		String roundNumber="";

		DataGame game = this.listOfGames.get(gameID);

		try {
			roundNumber = oWriter.writeValueAsString(game.getRound().getRoundNumber());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roundNumber;
	}

	/**
	 * Call every active players from a list and return in JSONString
	 * @param gameID: int
	 * @returns JSONString
	 */	
	@GET
	@Path("/game/activePlayers")
	public String getActivePlayers(@QueryParam("gameID") int gameID) {
		String activePlayersAsString="";

		DataGame game = this.listOfGames.get(gameID);

		try {
			activePlayersAsString = oWriter.writeValueAsString(game.getActivePlayers());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activePlayersAsString;
	}

	/**
	 * Get card name, card categories for all active players.
	 * @param gameID: int
	 * @return JSONString type
	 */	
	@GET
	@Path("/game/roundCards")
	public String getRoundCardsBeforePlayRound(@QueryParam("gameID") int gameID) {	
		List<DataCard> listOfCards=new ArrayList<DataCard>();
		DataGame game = this.listOfGames.get(gameID);
		DataCard humanCard = game.getRoundHumanPlayerCardBeforePlayRound();

		if(humanCard!=null) {
			listOfCards.add(game.getRoundHumanPlayerCardBeforePlayRound());
		}

		DataCard[] cards = game.getRoundAIPlayerCardsBeforePlayRound();

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

	/**
	 *Get the category that had selected from the AI players.
	 * @param gameID: int
	 *@return human: when active player is human, result: when there is a valid result.
	 * */
	@GET
	@Path("/game/getAIPlayerCategory")
	public String getAIPlayerCategory(@QueryParam("gameID") int gameID) {
		String result = "";

		DataGame game = this.listOfGames.get(gameID);

		DataPlayer activePlayer = game.getCategoryChooser();
		int category = game.getBestCategoryForPlayer(activePlayer);

		// sometimes the first player is human and this is not accounted for here
		if(category==0) {
			return "human";
		}
		result = DataGame.CATEGORYNAMES[category-1];

		return result;
	}

	/**
	 * plays the round
	 * @param category
	 * @param gameID
	 * @return stats
	 */
	@GET
	@Path("/game/playRound")
	public String playRound(@QueryParam("category") String category, @QueryParam("gameID") int gameID){
		DataGame game = this.listOfGames.get(gameID);

		game.playRound(category);
		if(game.getGameState()!=DataGame.GameState.RUNNING) {
			return "" + game.getGameWinner().getName() + " at round " + game.getRound().getRoundNumber();
		}
		game.getRound().incrementRound();
		return "running";
	}


	/**
	 * Get categories for selecting menu
	 * @returns JSONString type
	 */	
	@GET
	@Path("/game/categoryMenu")
	public String getCategoryForMenu() {
		List<String> listOfCategory=new ArrayList<String>();
		for(int i=0;i<DataGame.CATEGORYNAMES.length;i++) {
			listOfCategory.add(DataGame.CATEGORYNAMES[i]);
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
	 * Get category chooser of the round:String.
	 * @param gameID: int
	 * @returns String type
	 */	
	@GET
	@Path("/game/getRoundActivePlayer")
	public String getRoundActivePlayer(@QueryParam("gameID") int gameID){
		DataGame game = this.listOfGames.get(gameID);
		return game.getRound().getRoundActivePlayer().getName();
	}

	/**
	 * Get players result of the game
	 * @param gameID: int
	 * @returns String type
	 */	
	@GET
	@Path("/game/allPlayersScores")
	public String getAllPlayersAndScores(@QueryParam("gameID") int gameID) {
		String result=null;

		DataGame game = this.listOfGames.get(gameID);

		DataPlayer[] players = game.getAllPlayers();

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

	/**
	 * Get round winner's name or 'draw'
	 * @param gameID: int
	 * @returns String type
	 */	
	@GET
	@Path("/game/getRoundWinner")
	public String getRoundWinner(@QueryParam("gameID") int gameID){
		String result = null;
		DataGame game = this.listOfGames.get(gameID);

		ArrayList<DataPlayer> roundWinningPlayers = game.getRound().getRoundWinningPlayers();

		if(roundWinningPlayers.size() == 1) {
			result = roundWinningPlayers.get(0).getName();
		} else {
			result = "draw";
		}
		return result;
	}

	/**
	 * get number of cards in common pile. which is number of active players..
	 * @param gameID: int
	 * @return model.getNumberOfCardsInCommonPile()
	 * */
	@GET
	@Path("/game/numberOfCardsInCommonPile")
	public int getNumberOfCardsInCommonPile(@QueryParam("gameID") int gameID) {
		DataGame game = this.listOfGames.get(gameID);
		return game.getNumberOfCardsInCommonPile();
	}

	/**
	 * get playerName's deck number
	 * @param playerName: String, gameID: int
	 * @return integer
	 */
	@GET
	@Path("/game/cardsLeft")
	public int getPlayerDeck(@QueryParam("playerName") String playerName, @QueryParam("gameID") int gameID){
		int numberOfDeck=0;
		DataGame game = this.listOfGames.get(gameID);
		DataPlayer[] players = game.getActivePlayers();
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
	 * Get game statistics
	 * @return JSONString type
	 * number of games
	 * number of human wins
	 * number of ai wins
	 * average of draws
	 * longest game
	 */	
	@GET
	@Path("/stats/statistics")
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

}
