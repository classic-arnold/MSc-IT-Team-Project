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
	
	private DataGame model;
//	=DataGame.getInstance(numberOfArtificialIntelligencePlayers);
	
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
	 * 1. get round number from the model. 
	 * 2. 
	 * 3. pass list of cards=card name, categories
	*/
<<<<<<< HEAD
	@GET
	@Path("/game")
	public String getDeckFile() {
		return deckFile;
	}
	
	
	/**
	 * Get current round number
	 * @returns String type.
	 */	
	@GET
	@Path("/game/roundNumber")
	public String getRoundNumber() throws IOException{
		int roundNumber=model.getRoundNumber();
		//cast to String
		String roundNumberInString=oWriter.writeValueAsString(roundNumber);
		return roundNumberInString;
	}
	
	
	/**
	 * Get human player's card name, card categories only.
	 * @return string type of ArrayList
	 */	
	@GET
	@Path("/game")
	public ArrayList<String> getHumanCardsName() throws IOException{
		ArrayList<String> listOfCards=new ArrayList<String>();
		
		//Categories for the human player
		listOfCards.add(oWriter.writeValueAsString(model.getHumanPlayer().getDeck().get(0)));	
		return listOfCards;
	}
	
	@GET
	@Path("/game")
	public String getAICards() {
		
	}
	
	@GET
	@Path("/stats")
	public 
	
	
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
=======
//	@GET
//	@Path("/game")
//	public int getRoundNumber() throws IOException{
//		
//		return roundNumber;
//	}
	
	
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
	
//	@POST
//	@Path("/game")
//	
//	@PUT
//	@Path("/game")
//	
//	@HEAD
//	@Path("/game")
	
	
	
>>>>>>> 967ce4e1eb37ed99ed1857a43ca783f2136375fc
}
