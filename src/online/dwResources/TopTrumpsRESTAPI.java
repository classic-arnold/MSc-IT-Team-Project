package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import online.configuration.TopTrumpsJSONConfiguration;
import toptrumps.DataGame;

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
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	private String deckFile;
	private int numAIPlayers;
	
	private DataGame model=DataGame.getInstance(numAIPlayers);
	
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
		
		
		//get the location of the deck file
		deckFile=conf.getDeckFile();
		
		//get the number of AI players.
		numAIPlayers=conf.getNumAIPlayers();
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	/*
	 * @Path("/game") includes every game functions and data.
	 * every round will be displayed in it without moving to another path: /game
	 * --include game results. 
	 * 
	 * passing details: 
	 * 1. getDeckFile(): /game/displayCards
	 * 2. getCategoryForMenu(): /game/categoryMenu
	 * 3. getRoundCategory(): /game/roundCategory			<<modified
	 * 4. getActivePlayersName(): /game/activePlayersName		<<modified
	 * 5. getRound(): /game/roundNumber				<<modified
	 * 6. getCategoryChooser(): /game/categoryChooser		<<modified
	 * 7. getNumberOfCardsInDeck(): /game/numberOfCardsInDeck		<<modified
	 * 8. getHumanCards(): /game/humanCards					<<should modify
	 * 9. getAICards(): /game/AI1Cards 						<<should modify
	 * 10. getRoundDescription1(): /game/roundDescription1 
	 * 11. getRoundDescription2(): /game/roundDescription2
	 * 12. getRoundDescription3(): /game/roundDescription3
	 * 
	 * @Path("/game/sideBar")
	 * 13. getCategoryChooser(): /game/sideBar/categoryChooser	<<modified
	 * 14. getDescriptionOfRoundCategory(): /game/sideBar/descriptionOfRoundCategory	<<modified
	 * 
	 * @Path("/game/result")
	 * 15. getGameEnd(): /game/result/gameEnd			<<modified
	 * 16. getWinnerOfTheGame(): /game/result/winner		<<modified
	 * 17. getGameResult(): /game/result/scores			<<should modify
	 * 
	 * 
	 * 
	 * @Path("/stats") includes game statistics in array
	 * 1. getStatistics(): /stats/statistics
	 * 
	*/

	
	/**
	 * Get entire deck file when the game starts.
	 * @Controller.js: function displayCard 
	 * @returns String type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/displayCards")
	public String getDeckFile() {
		return deckFile;
	}
	

	/**
	 * Get human player's card name, card categories only.
	 * @Controller.js: function humanSelectCategory
	 * @return JSONString[] type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/categoryMenu")
	public String getCategoryForMenu() throws IOException{	
//		List<String> listOfWords = new ArrayList<String>();
//		listOfWords.add("Hello");
//		listOfWords.add("World!");
//		
//		// We can turn arbatory Java objects directly into JSON strings using
//		// Jackson seralization, assuming that the Java objects are not too complex.
//		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
//		
//		return listAsJSONString;
		
		List<String> listOfCategory=new ArrayList<String>();
		for(int i=0;i<model.CATEGORYNAMES.length;i++) {
			listOfCategory.add(model.CATEGORYNAMES[i]);
		}
		
		String listAsJSONString=oWriter.writeValueAsString(listOfCategory);
		
		
//		String[] categories=new String[model.CATEGORYNAMES.length];
//		
//		for(int i=0;i<model.CATEGORYNAMES.length;i++) {
//			categories[i]=oWriter.writeValueAsString(model.CATEGORYNAMES[i]);
//		}
		return listAsJSONString;
	}
	
	
	/**
	 * Get round category:String.
	 * @Controller.js: function AISelectCategory
	 * @returns JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/roundCategory")
	public String getRoundCategory() throws IOException{
		String roundCategory=oWriter.writeValueAsString(model.getRoundCategory());
		return roundCategory;
	}
	
	
	/**
	 * Get active player (ArrayList) and store in activePlayer (array).
	 * @Controller.js: function activePlayer
	 * @returns JSONString[] type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/activePlayersName")
	public String[] getActivePlayersName() throws IOException{
		String[] activePlayer=new String[model.getActivePlayers().length];
		
		for(int i=0;i<model.getActivePlayers().length;i++) {
			activePlayer[i]=oWriter.writeValueAsString(model.getActivePlayers());
		}

		return activePlayer;
		// "[ {\r\n  \"name\" : \"You\",\r\n  \"deck\" : [ ]\r\n} ]"
	}
	

	/**
	 * Get current round number:integer.
	 * @Controller.js: function roundNumber
	 * @returns JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/roundNumber")
	public String getRoundNumber() throws IOException{
		int round=model.getRoundNumber();
		String roundNumber=oWriter.writeValueAsString(round);
		return roundNumber;
	}
	
	
	/**
	 * Get category chooser of the round:String.
	 * @Controller.js: 
	 * @returns JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/categoryChooser")
	public String getCategoryChooser() throws IOException{
		String categoryChooser=oWriter.writeValueAsString(model.getCategoryChooser());
		return categoryChooser;
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
	public String[] getNumberOfCardsInDeck() throws IOException{
		String[] numberOfCardsInDeck=new String[model.getActivePlayers().length];
		int cardsInDeck;
		for(int i=0;i<model.getActivePlayers().length;i++) {
			if(model.getActivePlayers()[i].toString().contains("human")) {
				cardsInDeck=model.getActivePlayers()[i].getDeck().size();
				numberOfCardsInDeck[i]=oWriter.writeValueAsString(cardsInDeck);
			}else {//stores the number of ai players cards in deck
				cardsInDeck=model.getActivePlayers()[i].getDeck().size();
				numberOfCardsInDeck[i]=oWriter.writeValueAsString(cardsInDeck);
			}

		}
		return numberOfCardsInDeck;
	}
	
	
	/**
	 * Get human player's card name, card categories only.
	 * @Controller.js:
	 * @return JSONString[] type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/roundHumanPlayerCard")
	public String[] getRoundHumanPlayerCard() throws IOException{	
		String[] humanCard=new String[6];
		
		//store card title in humanCard[0]
		humanCard[0]=oWriter.writeValueAsString(model.getRoundHumanPlayerCard().toString());
		
		for(int i=0;i<humanCard.length;i++) {
//			humanCard[i+1]=oWriter.writeValueAsString(model.getHumanPlayer().getDeck().get(0)[i]);
		}
		return humanCard;
	}	


	/**
	 * Get AI player's card name, card categories 
	 * and store in AIPlayersCard[activePlayers().length][card name, categories(5)].
	 * 
	 * @Controller.js:
	 * @return JSONString[] type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/AI1Cards")
	public String getAICards() throws IOException{	
		String[][] AIPlayersCard=new String[model.getActivePlayers().length][6];
		for(int i=0;i<model.getActivePlayers().length;i++) {
			AIPlayersCard[i][0]=oWriter.writeValueAsString(model.getRoundAIPlayerCards()[0].toString());
			for(int j=0;j<6;j++) {
				AIPlayersCard[i][j+1]=oWriter.writeValueAsString(model.getRoundAIPlayerCards());
			}
		}
		model.getActivePlayers();
		String AICards=oWriter.writeValueAsString(model.getRoundAIPlayerCards());
		return AICards;
	}
	
	
	/**
	 * Print description of starting stage of the round. 
	 * such as: "Round 1: Players had drawn their cards".
	 * @Controller.js:
	 * @param RoundNumber:int, CategoryChooser:String
	 * @return JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/roundDescription1")
	public String getRoundDescription1(@QueryParam("RoundNumber") int RoundNumber,
			@QueryParam("CategoryChooser") String CategoryChooser) 
					throws IOException{
		String description;
		
		description=String.format("Round %d: Players had drawn their cards.", RoundNumber);
		if(CategoryChooser.equals("You")) {
		//		if(model.getCategoryChooser().equals("human")) {
			description+=" Waiting on "+CategoryChooser+" to select category.";
		}
		
		return oWriter.writeValueAsString(description);
	}
	
	/**
	 * Print selected category of the round. 
	 * such as: "Round 1: You selected speed".
	 * @Controller.js:
	 * @param RoundNumber:int, CategoryChooser:String, RoundCategory:String
	 * @return JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/roundDescription2")
	public String getRoundDescription2(@QueryParam("RoundNumber") int RoundNumber,
			@QueryParam("CategoryChooser") String CategoryChooser,
			@QueryParam("RoundCategory") String RoundCategory) 
					throws IOException{
		String description;
		
		description=String.format("Round %d: %s selected %s.", RoundNumber, CategoryChooser, RoundCategory);
		return oWriter.writeValueAsString(description);
	}
	
	
	/**
	 * Print winner of the round. 
	 * such as: "Round 1: Player You won this round. Common pile now has 5 cards".
	 * @Controller.js:
	 * @param RoundNumber:int, RoundWinner:String, NumberOfActivePlayer:int, IsDraw:boolean
	 * @return JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/roundDescription3")
	public String getRoundDescription3(@QueryParam("RoundNumber") int RoundNumber,
			@QueryParam("RoundWinner") String RoundWinner,
			@QueryParam("NumberOfActivePlayer") int NumberOfActivePlayer,
			@QueryParam("IsDraw") boolean IsDraw)
					throws IOException{
		String description;

		description=String.format("Round %d: Player %s won this round.", RoundNumber, RoundWinner);
		
		//if the round was draw
		if(IsDraw) {
		//if(model.getRoundWasDraw()) {
			description=String.format("Round %d: This round was a Draw.", RoundNumber, NumberOfActivePlayer);
		}
		
		description+=" Common pile now has "+NumberOfActivePlayer+" cards.";
		
		return oWriter.writeValueAsString(description);
	}
	
	
	/**
	 * Get category chooser:String
	 * @Controller.js:
	 * @param CategoryChooser:String
	 * @return JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/sideBar/categoryChooser")
	public String getCategoryChooser(@QueryParam("CategoryChooser") String CategoryChooser) throws IOException{		
		return "The Active Player is "+CategoryChooser;
	}	
	
	
	/**
	 * Get description of category of the round:String
	 * @Controller.js:
	 * @param RoundCategory:String, CategoryChooser:String
	 * @return JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/sideBar/descriptionOfRoundCategory")
	public String getDescriptionOfRoundCategory(@QueryParam("RoundCategory") String RoundCategory,
			@QueryParam("CategoryChooser") String CategoryChooser) throws IOException{		
		return CategoryChooser+" selected "+RoundCategory;
	}	
	
	
	
	/**
	 * Get game end String
	 * @Controller.js:
	 * @return JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/result/gameEnd")
	public String getGameEnd() throws IOException{		
		return "Game End";
	}	
	
	
	/**
	 * Print game winner
	 * @Controller.js:
	 * @param GameWinner:String
	 * @return JSONString type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/result/winner")
	public String getWinnerOfTheGame(@QueryParam("GameWinner") String GameWinner) throws IOException{		
		return GameWinner+" won the game";
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
	 * @return JSONString[] type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/result/scores")
	public String[] getGameResult() throws IOException{		
		//Categories for the human player
		String[] gameResult=new String[model.getAllPlayers().length+1];
		gameResult[0]=oWriter.writeValueAsString("Player scores");
		for(int i=0;i<model.getAllPlayers().length;i++) {
//			gameResult[i+1]=oWriter.writeValueAsString(String.format("%s : %d", model.getAllPlayers()[i].getName(), model.getAllPlayers()[i].getScore()));
		}
		return gameResult;
	}	
	
	
	
	/**
	 * Get human player's card name, card categories only.
	 * @Controller.js:
	 * @return string type of ArrayList
	 * @throws IOException
	 */	
	@GET
	@Path("/stats/statistics")
	public String[] getStatistics() throws IOException{		
		String[] statistics=new String[5];
		statistics[0]=oWriter.writeValueAsString(model.getNumberOfGames());
		statistics[1]=oWriter.writeValueAsString(model.getNumberOfHumanWins());
		statistics[2]=oWriter.writeValueAsString(model.getNumberOfAIWins());
		statistics[3]=oWriter.writeValueAsString(model.getAvgNumberOfDraws());
		statistics[4]=oWriter.writeValueAsString(model.getLongestGame());
		
		return statistics;
	}
	
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}	

}
