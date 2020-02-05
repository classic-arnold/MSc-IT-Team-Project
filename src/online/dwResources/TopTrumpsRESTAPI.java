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
	 * 3. getAICategory(): /game/aiCategorySelection
	 * 4. getActivePlayer(): /game/activePlayer
	 * 5. getRound(): /game/round
	 * 6. getHumanCards(): /game/humanCards
	 * 7. getRoundDescription(): /game/roundDescription 	<<should modify
	 * 8. getAICards(): /game/AI1Cards 						<<should modify
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
	 * @return string type of ArrayList
	 * @throws IOException
	 */	
	@GET
	@Path("/game/categoryMenu")
	public String getCategoryForMenu() throws IOException{	
		String categories=oWriter.writeValueAsString(model.CATEGORYNAMES);
		return categories;
	}
	
	
	/**
	 * Get round category which AI player had chosen.
	 * @Controller.js: function AISelectCategory
	 * @returns String type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/aiCategorySelection")
	public String getAICategory() throws IOException{
		String categorySelection=model.getRoundCategory();
		String roundCategory=oWriter.writeValueAsString(categorySelection);
		return roundCategory;
	}
	
	
	/**
	 * Get active player (ArrayList) and store in activePlayer (array).
	 * @Controller.js: function activePlayer
	 * @returns String[] type
	 * @throws IOException
	 */	
	@GET
	@Path("/game/activePlayer")
	public String[] getActivePlayer() throws IOException{
		String[] activePlayer=new String[model.getActivePlayers().length];
		
		for(int i=0;i<model.getActivePlayers().length;i++) {
			activePlayer[i]=oWriter.writeValueAsString(model.getActivePlayers());
		}

		return activePlayer;
	}
	

	/**
	 * Get current round number:integer.
	 * @Controller.js: function roundNumber
	 * @returns String type
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
	 * Get human player's card name, card categories only.
	 * @Controller.js:
	 * @return string type of ArrayList
	 * @throws IOException
	 */	
	@GET
	@Path("/game/humanCards")
	public String getHumanCards() throws IOException{		
		//Categories for the human player
		String humanCards=oWriter.writeValueAsString(model.getRoundHumanPlayerCard());	
		return humanCards;
	}	


	/**
	 * Get AI player's card name, card categories 
	 * and store in AIPlayersCard[activePlayers().length][card name, categories(5)].
	 * 
	 * @Controller.js:
	 * @return string type of ArrayList
	 * @throws IOException
	 */	
	@GET
	@Path("/game/AI1Cards")
	public String getAICards() throws IOException{	
		String[][] AIPlayersCard=new String[model.getActivePlayers().length][6];
		for(int i=0;i<model.getActivePlayers().length;i++) {
			AIPlayersCard[i][0]=oWriter.writeValueAsString(model.getRoundAIPlayerCards()[0].getDescription());
			for(int j=0;j<6;j++) {
				AIPlayersCard[i][j+1]=oWriter.writeValueAsString(model.CATEGORYNAMES[j]);
			}
		}
		model.getActivePlayers();
		String AICards=oWriter.writeValueAsString(model.getRoundAIPlayerCards());
		return AICards;
	}
	
	
	/**
	 * Print current round description. 
	 * such as: "Round 1: Players had drawn their cards".
	 * @Controller.js:
	 * @param roundNumber - round number
	 * @return string type of ArrayList
	 * @throws IOException
	 */	
	@GET
	@Path("/game/roundDescription")
	public String getRoundDescription(@QueryParam("roundNumber") int roundNumber) throws IOException{	
		String output=String.format("Round %d: Players had drawn their cards", roundNumber);
		if(model.getRoundCategory()==null) {
//			if(model.getActivePlayers()[i].getTypeAsString().equals("human"))
			output=String.format("Round %d: Players had drawn their cards", roundNumber);
		}
		else {
//			do we need to print description when certain player selects category?
//			if(model.ai)
			
			output=String.format("Round %d: Player %s won this round",
					roundNumber, 
					model.getRoundWinningPlayers().get(0).toString());
		}
		String roundDescription=oWriter.writeValueAsString(output);
		return roundDescription;
	}
	
	

	
	
//	@GET
//	@Path("/helloJSONList")
//	/**
//	 * Here is an example of a simple REST get request that returns a String.
//	 * We also illustrate here how we can convert Java objects to JSON strings.
//	 * @return - List of words as JSON
//	 * @throws IOException
//	 */
//	public String helloJSONList() throws IOException {
//		
//		List<String> listOfWords = new ArrayList<String>();
//		listOfWords.add("Hello");
//		listOfWords.add("World!");
//		
//		// We can turn arbatory Java objects directly into JSON strings using
//		// Jackson seralization, assuming that the Java objects are not too complex.
//		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
//		
//		return listAsJSONString;
//	}
//	
//	@GET
//	@Path("/helloWord")
//	/**
//	 * Here is an example of how to read parameters provided in an HTML Get request.
//	 * @param Word - A word
//	 * @return - A String
//	 * @throws IOException
//	 */
//	public String helloWord(@QueryParam("Word") String Word) throws IOException {
//		return "Hello "+Word;
//	}	

//	@GET
//	@Path("/game")
//	public int getRoundNumber() throws IOException{
//		
//		return roundNumber;
//	}
}
