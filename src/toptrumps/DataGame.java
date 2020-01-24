package toptrumps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/*
 * 
 * NOTE TO TEAM
 * We should use singleton design pattern to ensure this object is only created once to avoid hard to detect bugs, as the program is quite complex.
 * Do not bother about enums. I'm using it only in the model. It makes my work easier and i can convert to string for anyone to use.
 *
 * NOTE TO SELF
 * TODO: Uncomment database parts and add JavaDoc comments
 * TODO: Test all functions
 * TODO: Verify game specifications are accurate
 * TODO: Remove wasted codes and comments
 * TODO: Remove unneccessary codes
 * TODO: Improve code algorithms
 * TODO: Add methods and class visibility
 * TODO: Reduce couplings and increase cohesion
 * TODO: Maybe create abstract class Cardable and move all deck methods there
 *
 */

/**
 * 
 * DataGame represents the game class. It should only be created once per game.
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U Team
 *
 */
public class DataGame{
	
	/** represents the DataGame instance */
	private static DataGame instance = null;

	/** represents an array of the category names */
	public static final String[] CATEGORYNAMES = {"size", "speed", "range", "firePower", "cargo"};
	
	/** represents the list of players in the game */
	private ArrayList<DataPlayer> activePlayers = new ArrayList<DataPlayer>();
	
	/** represents the list of all players that started the game */
	private ArrayList<DataPlayer> allPlayers = new ArrayList<DataPlayer>();
	
	/** enum to represent the game state */
	enum GameState {
		RUNNING, ENDED
	}
	
//	/** enum to represent the results of round */
//	enum RoundResults {
//		DRAW, WIN
//	}
	
	/** represents the game state */
	private GameState gameState;
	
	/** represents the number of draws in a game */
	private int numberOfDraws; // Will be used when I get Estelle's code
	
	/** represents the common deck */
	private DataCommonDeck commonDeck = new DataCommonDeck();
	
	/** represents the deck of cards */
	private ArrayList<DataCard> originalDeck = new ArrayList<DataCard>();
	
	/** represents the winner of the game(not round) */
	private DataPlayer gameWinner;
	
	/** represents the current round number */
	private int roundNumber;
	
	/** represents the draw status of last round */
	private boolean roundWasDraw;
	
	/** represents the winning cards of the round */
	private ArrayList<DataCard> roundWinningCards = new ArrayList<DataCard>();
	
	/** represents the players of round */
	private ArrayList<DataPlayer> roundWinningPlayers = new ArrayList<DataPlayer>();
	
//	/** represents whether players cards have been drawn or not for each round */
//	private boolean roundHasPlayersDrawnCards;
	
	/** represents the card drawn by human player for each round */
	private DataCard roundHumanPlayerCard;
	
	/** represents the cards drawn by AI players for each round */
	private ArrayList<DataCard> roundAIPlayerCards = new ArrayList<DataCard>();
	
	/** represents the round category */
	private String roundCategory;

	/**
	 * creates a new DataGame Object
	 * @param numberOfArtificialIntelligencePlayers represents the number of AI players in the game
	 */
	private DataGame(int numberOfArtificialIntelligencePlayers) {
		this.gameState = GameState.RUNNING; // set game state to running
		
		this.activePlayers.add(new DataPlayer(DataPlayer.PlayerType.HUMAN, numberOfArtificialIntelligencePlayers)); // add one human player
		
		// add the artificial intelligence players to the DataGame players depending on the number specified above
		for(int i=0;i<numberOfArtificialIntelligencePlayers;i++) {
			this.activePlayers.add(new DataPlayer(DataPlayer.PlayerType.AI, numberOfArtificialIntelligencePlayers));
		}
		
		this.allPlayers = (ArrayList<DataPlayer>)this.activePlayers.clone();
	}
	
	/**
	 * gets the instance of the single DataGame
	 * @param numberOfArtificialIntelligencePlayers represents the number of AI players in the game
	 * @return DataGame object representing the current game
	 */
	public static DataGame getInstance(int numberOfArtificialIntelligencePlayers) {
		// if game hasn't been started, start it
		if(DataGame.instance == null) {
			DataGame.instance = new DataGame(numberOfArtificialIntelligencePlayers);
		}
		return DataGame.instance;
	}
	
	/**
	 * starts a new game by resetting the instance
	 * @param numberOfArtificialIntelligencePlayers represents the number of AI players in the game
	 * @return DataGame object representing the current game
	 */
	public static DataGame resetAndGetInstance(int numberOfArtificialIntelligencePlayers) {
		DataGame.instance = new DataGame(numberOfArtificialIntelligencePlayers); // start game
		return DataGame.instance;
	}
	
	/**
	 * starts the game
	 */
	public void startGame() {
		
		DataCardCache.loadCardFromFileAndCache(); // load cards from file and cache them
		
		this.originalDeck = this.shuffleDeck(this.getNewDeck()); // shuffle the deck
		
		int numberOfCardsPerPlayer = this.originalDeck.size()/this.activePlayers.size(); // get number of cards per player
		
		// serve cards to player from deck
		for(DataPlayer player : this.activePlayers) {
			for(int i = 0; i<numberOfCardsPerPlayer; i++) {
				player.addCardToDeck(this.originalDeck.get(0));
				this.originalDeck.remove(0);
			}
		}
		
		this.originalDeck.clear(); // clear the leftover cards in the deck
		
//		FOR TESTING
//		for(int i=0;i<this.deck.size();i++) {
//			System.out.println(this.deck.get(i));	
//		}
		
//		for testing
//		System.out.println(this.players.get(0).getDeck().get(0));
		
//		for testing
//		System.out.println(this.originalDeck.size());
		
//		this.incrementRound(); // increase the round number
		
	}
	
	/**
	 * plays a round
	 * @param category string representing the chosen category
	 */
	public void playRound(String category) {
		
		this.roundWinningPlayers.clear(); // clear last round details
		this.roundWinningCards.clear(); // clear last round details
		this.roundAIPlayerCards.clear(); // clear last round details
		
		
		ArrayList<DataCard> roundCards = new ArrayList<DataCard>(); // holds the card drawn for this round
		
		ArrayList<DataPlayer> playersToRemove = new ArrayList<DataPlayer>(); // holds list of players that have no cards
		
		for(DataPlayer player : this.activePlayers) {
			
//			System.out.println(player.getName());
			
			// remove players without cards
			if(player.getDeck().size()==0) {
				playersToRemove.add(player);
//				System.out.println(player.getName());
//				System.out.println("No cards\n");
			} else { // draw player top cards
				
				// REMOVE START
				System.out.println(player.getName());
				System.out.println(player.getDeck().get(0));
				// REMOVE END
				
				DataCard card = player.getDeck().get(0);
//				player.removeTopCardFromDeck();
				roundCards.add(card);
				
				// store cards in round details
				if(player.getType()==DataPlayer.PlayerType.HUMAN) {
					this.roundHumanPlayerCard = card;
				} else if (player.getType()==DataPlayer.PlayerType.AI) {
					this.roundAIPlayerCards.add(card);
				}
			}
//			player.getDeck().remove(0); // moved elsewhere
		}
		
		// remove players marked for removal because they have no cards anymore
		for(DataPlayer player : playersToRemove) {
			this.activePlayers.remove(player);
		}
		
		HashMap<String, Object> roundDetails = this.getNewGameStateAndWinner(); // holds the round game state and winner
		
		this.gameState = (GameState)roundDetails.get("gamestate");
		this.gameWinner = (DataPlayer)roundDetails.get("winner");
		
		// increment round if game is still running
		if(this.getGameState() == GameState.RUNNING) {
			this.incrementRound(); // increase the round number
		} else { // save game stats to database
			this.saveGameStats();
			
			// REMOVE START
			if(this.gameWinner!=null) {
				System.out.println("Winner: " + this.gameWinner.getName());
				System.out.println("This should be (floor(Number of cards/number of players)): "+(this.gameWinner.getDeck().size() + this.commonDeck.size()));
			} else {
				System.out.println("No winner. Game Drawn.");
			}
				
			System.out.println("Final round: " + this.roundNumber);
			// REMOVE END
			
			return;
		}
		
//		// set players have drawn cards to true, for the round
//		this.roundHasPlayersDrawnCards = true;
		
		// holds the winning cards and winning players
		HashMap<String, Object> winningCardsAndPlayers = this.getWinningCardsAndPlayers(DataGame.arrayListToArrayCard(roundCards), category);
		
		// set round winning cards
		this.roundWinningCards = (ArrayList<DataCard>)winningCardsAndPlayers.get("winning cards");
		
		// holds round winning players
		HashSet<DataPlayer> roundWinningPlayers = (HashSet<DataPlayer>)winningCardsAndPlayers.get("winning players");
		
		// set round winning players from set
		for(DataPlayer player : roundWinningPlayers) {
			this.roundWinningPlayers.add(player);
		}
		
		//if there is only 1 winning player, round wasn't drawn
		if(this.roundWinningPlayers.size()==1) {
			
			roundCards.addAll(this.commonDeck.getAllCards()); // add common deck cards to round cards
			this.commonDeck.clear(); // clear common deck
			
			this.roundWinningPlayers.get(0).addCardsToDeck(roundCards); // add round cards to his deck
			this.roundWinningPlayers.get(0).incrementScore();
			
			this.roundWasDraw = false;
			
			// REMOVE START
			System.out.println("Round " + this.roundNumber + " winner: " + this.roundWinningPlayers.get(0).getName() + "\n");
			// REMOVE END
			
		} else if (this.roundWinningPlayers.size()>1) { // if there were multiple winning players, round was drawn
			this.incrementNumberOfDraws(); // increment number of draws
			this.roundWasDraw = true;
			this.commonDeck.addCardsToDeck(roundCards); // add round cards to common deck
			
			// REMOVE START
			System.out.println("Round " + this.roundNumber + " draw\n");
			// REMOVE END
		}
		
	}
	
	/**
	 * used to get a fresh new deck
	 * @return ArrayList representing lists of cards in new deck
	 */
	public ArrayList<DataCard> getNewDeck() {
		return DataGame.arrayToArrayList(DataCardCache.getAllCardsInOrder());
	}
	
	/**
	 * checks if game has ended
	 * @return GameState HashMap containing the winning player and the new game state
	 */
	public HashMap<String, Object> getNewGameStateAndWinner() {
		HashMap<String, Object> result = new HashMap<String, Object>(); // stores results
		
		// check if there is any card in the deck. If there is, game cant be finished
//		int checker = 0;
//		for(DataPlayer player : this.players) {
//			if(player.getDeck().size()>0) {
//				checker += 1;
//			}
//		}
		
		// check the numbers of players left. If it's just one, the game has ended and he is the winner
		if(this.activePlayers.size()==1) {
			result.put("winner", this.activePlayers.get(0)); // store player as winner
			result.put("gamestate", GameState.ENDED); // store game state ended
			return result;
		}
		
		// check if game was drawn i.e all cards are in the common deck
		if(this.didWholeGameDraw()) {
			result.put("winner", null); // no winner
			result.put("gamestate", GameState.ENDED); // store game state ended
			return result;
		}
		
		// at this point, game must still be running
		// store no winner and game state running
		result.put("winner", null);
		result.put("gamestate", GameState.RUNNING);
		return result;
	}
	
	/**
	 * checks if game was drawn by checking if all cards are in the common deck
	 * @return boolean representing if the game was drawn or not
	 */
	public boolean didWholeGameDraw() {
		// check if all cards are in the common deck
		if(this.commonDeck.size()==40) {
			return true; // if yes, game was drawn
		}
		return false; // if not, game was not drawn
	}
	
	/**
	 * takes an array of cards and return the winning cards
	 * @param cards the array of all cards
	 * @param category the category selected
	 * @return HashMap<String, Object> containing list of winning cards and a set of winning players
	 */
	public HashMap<String, Object> getWinningCardsAndPlayers(DataCard[] cards, String category) {
		HashMap<String, Object> results = new HashMap<String, Object>(); // stores results
		ArrayList<DataCard> winningCards = new ArrayList<DataCard>(); // initialize winning cards list
		HashSet<DataPlayer> winningPlayers = new HashSet<DataPlayer>(); // initialize winning cards list
		
		// this means the game(not round) has been drawn
//		if (cards.length==0) {
//			results.put("winning cards", winningCards);
//			results.put("winning players", winningPlayers);
//			return results;
//		}
		
		DataCard lastWinnerCard = cards[0]; //store 1st card as winning card
		
		// check each card against last winning card
		for(int i=0; i<cards.length; i++) {
			// if both cards equal, store as winning cards and store owner as winning player
			if (cards[i].compare(lastWinnerCard, category) == 2) {
				lastWinnerCard = cards[i]; // set card as last winning card
				winningCards.add(cards[i]); // add new higher and equal card
				
				// store winning players
				for (DataPlayer player : this.activePlayers) {
					// if player deck contains winning card
					if(player.getDeck().contains(cards[i])) {
						winningPlayers.add(player); // add owner of card to winning players
//						player.getDeck().remove(cards[i]); // remove cards
					}
				}
				
			} else if (cards[i].compare(lastWinnerCard, category) == 1) { // else if current card greater than last winning card, clear winning cards and players and store as winning cards and store owner as winning player
				lastWinnerCard = cards[i]; // set card as last winning card
				winningCards.clear(); // reset list of winning cards, because we have a new higher card
				winningCards.add(cards[i]); // add new higher card
				
				// store winning players
				for (DataPlayer player : this.activePlayers) {
					// if player deck contains winning card
					if(player.getDeck().contains(cards[i])) {
						winningPlayers.clear(); // clear previous winning players
						winningPlayers.add(player); // add owner of card to winning players
//						player.getDeck().remove(cards[i]);
					}
				}
			}
		}
		
		// remove all players top card from deck
		for(DataPlayer player : this.activePlayers) {
			player.removeTopCardFromDeck();
		}
		
		// store both winning card and winning player lists in results
		results.put("winning cards", winningCards);
		results.put("winning players", winningPlayers);
		return results;
	}
	
	/**
	 * increase the round number by 1
	 */
	public void incrementRound() {
		this.roundNumber += 1;
	}
	
	/**
	 * increase the number of draws by 1
	 */
	public void incrementNumberOfDraws() {
		this.numberOfDraws += 1;
	}
	
//	/**
//	 * calculates if round was a draw or not by counting the number of winning playes
//	 * @param winningPlayers represents the list of winning players
//	 * @return RoundResults enum representing the round result
//	 */
////	public RoundResults getRoundState(DataPlayer[] winningPlayers) {
////		// if there are more than 1 player, its a draw, else its win
////		if(winningPlayers.length > 1) {
////			return RoundResults.DRAW;
////		} else {
////			return RoundResults.WIN;
////		}
////	}
	
	/**
	 * shuffles the deck by rearranging the cards randomly
	 * @param deck the list of cards that needs to be shuffled
	 * @return ArrayList<DataCard> representing a new deck
	 */
	public ArrayList<DataCard> shuffleDeck(ArrayList<DataCard> deck) {
		// if deck.size() is 0, throw exception as there is nothing to shuffle
		if(deck.size()==0) {
			throw new exceptions.NoCardInDeckException();
		}
		
		ArrayList<DataCard> shuffledDeck = new ArrayList<DataCard>(); // store the shuffled deck
		
		HashSet<Integer> listOfRandoms = new HashSet<Integer>(); // store list of used randoms
		
		Random r = new Random();
		
		// run this loop until the shuffledDeck has 40 cards
		while(shuffledDeck.size()<40){
			int randomNumber;
			
			// check if this random number has been used before, and keep getting random numbers until we find one that hasn't
			do{
				randomNumber = r.nextInt(deck.size());
			} while(listOfRandoms.contains(randomNumber));
			
			shuffledDeck.add(deck.get(randomNumber)); // add random card to shuffled deck
			listOfRandoms.add(randomNumber); // add random number to set of random to avoid repetition
		}
		
		return shuffledDeck;
	}
	
	/**
	 * Static method used to convert an array of objects of generic types to an ArrayList
	 * @param <t> generic type
	 * @param array original array
	 * @return an ArrayList shallow copy of array
	 */
	public static <t> ArrayList<t> arrayToArrayList(t[] array){
		ArrayList<t> arrayList = new ArrayList<t>();
		// copy all elements in order
		for(int i = 0; i<array.length;i++) {
			arrayList.add(array[i]);
		}
		return arrayList;
	}
	
	/**
	 * Static method used to convert an ArrayList of DataCard to an array
	 * @param arrayList original array list
	 * @return an array shallow copy of ArrayList
	 */
	public static DataCard[] arrayListToArrayCard(ArrayList<DataCard> arrayList){
		int length = arrayList.size();
		DataCard[] array = new DataCard[length];
		// copy all elements in order
		for(int i = 0; i<length;i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}
	
	/**
	 * Static method used to convert an ArrayList of DataPlayer to an array
	 * @param arrayList original array list
	 * @return an array shallow copy of ArrayList
	 */
	public static DataPlayer[] arrayListToArrayPlayer(ArrayList<DataPlayer> arrayList){
		int length = arrayList.size();
		DataPlayer[] array = new DataPlayer[length];
		// copy all elements in order
		for(int i = 0; i<length;i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}
	
	/**
	 * Updates database, using methods provided in the database class
	 */
		public void saveGameStats() {
//			Database.incrementNumberOfGames();
			if(this.gameWinner!=null) {
				if(gameWinner.getType()==DataPlayer.PlayerType.HUMAN) {
//					Database.incrementNumberOfHumanWins();
				} else if(gameWinner.getType()==DataPlayer.PlayerType.AI) {
//					Database.incrementNumberOfAIWins();
				}
			}
			
			for (DataPlayer player : this.activePlayers) {
//				Database.setPlayerScore(player);
			}
			
//			Database.setNumberOfDraws(this.numberOfDraws);
			
			if(this.gameState == GameState.ENDED) {
//				Database.calculateLongestGame(this.roundNumber);
			}
		}
	
	// GETTER METHODS START
	/**
	 * get original deck as array. Might be shuffled or unshuffled.
	 * @return DataCard array containing all cards in original deck
	 */
	// this might not be useful(for CLI at least)
	public DataCard[] getCompleteDeckAsArray() {
		return DataGame.arrayListToArrayCard(this.originalDeck);
	}
	
	/**
	 * get original deck as ArrayList. Might be shuffled or unshuffled.
	 * @return ArrayList<DataCard> containing all cards in original deck
	 */
	public ArrayList<DataCard> getCompleteDeckAsArrayList() {
		return this.originalDeck;
	}
	
	/**
	 * get the round number
	 * @return int representing the round number
	 */
	public int getRoundNumber() {
		return this.roundNumber;
	}
	
	/**
	 * get the list of active players as array
	 * @return DataPlayer array containing active players still in the game
	 */
	public DataPlayer[] getActivePlayers() {
		return DataGame.arrayListToArrayPlayer(this.activePlayers);
	}
	
	/**
	 * get the list of all players as array
	 * @return DataPlayer array containing all players still in the game
	 */
	public DataPlayer[] getAllPlayers() {
		return DataGame.arrayListToArrayPlayer(this.allPlayers);
	}
	
	/**
	 * get the number of draws for the game
	 * @return int representing number of draws
	 */
	public int getNumberOfDraws() {
		return this.numberOfDraws;
	}
	
	/**
	 * get if round was draw
	 * @return boolean representing if round was drawn
	 */
	public boolean getRoundWasDraw() {
		return this.roundWasDraw;
	}
	
	/**
	 * get number of cards in common pile
	 * @return int representing get number of cards in common pile
	 */
	public int getNumberOfCardsInCommonPile() {
		return this.commonDeck.size();
	}
	
	/**
	 * current game state
	 * @return GameState enum representing game state
	 */
	public GameState getGameState() {
		return this.gameState;
	}
	
	/**
	 * get round winning cardS. Might be more than one card
	 * @return ArrayList<DataCard> containing the winning cards
	 */
	public ArrayList<DataCard> getRoundWinningCards() {
		return this.roundWinningCards;
	}
	
	/**
	 * get round winning card. Just the card. Used to display info
	 * @return DataCard object representing the winning card
	 */
	public DataCard getRoundWinningCard() {
		return this.roundWinningCards.get(0);
	}
	
	/**
	 * get round winning players
	 * @return ArrayList<DataPlayer> representing list of winning players
	 */
	public ArrayList<DataPlayer> getRoundWinningPlayers() {
		return this.roundWinningPlayers;
	}
	
	/**
	 * get round category
	 * @return String representing round category
	 */
	public String getRoundCategory() {
		return this.roundCategory;
	}
	
	/**
	 * get game winner
	 * @return DataPlayer representing game winner
	 */
	public DataPlayer getGameWinner() {
		return this.gameWinner;
	}
	
	/**
	 * get the card drawn by human player for this round
	 * @return DataCard representing card drawn by human player for this round
	 */
	public DataCard getRoundHumanPlayerCard() {
		return this.roundHumanPlayerCard;
	}
	
	/**
	 * get all cards drawn by AI player. Card owners cannot be identified.
	 * @return DataCard array representing the cards drawn by AI player
	 */
	public DataCard[] getRoundAIPlayerCards() {
		return DataGame.arrayListToArrayCard(this.roundAIPlayerCards);
	}
	
//	GETTERS FROM DATABASE - waiting on Estelle
//	public static int getNumberOfHumanWinsDB() {
//		return ProgramDatabase.getNumberOfHumanWins();
//	}
//	
//	public static int getNumberOfAIWinsDB() {
//		return ProgramDatabase.getNumberOfAIWins();
//	}
//	
//	public static int getNumberOfGamesDB() {
//		return ProgramDatabase.getNumberOfGames();
//	}
//	
//	public static int getAvgNumberOfDrawsDB() {
//		return ProgramDatabase.getAvgNumberOfDraws();
//	}
//	
//	public static int getLongestGameDB() {
//		return ProgramDatabase.getLongestGame();
//	}
	
	// REMOVE START
	// FAKE GETTERS FROM DATABASE - returns mock values
	public int getNumberOfHumanWins() {
		return 2;
	}
	
	public int getNumberOfAIWins() {
		return 2;
	}
	
	public int getNumberOfGames() {
		return 56;
	}
	
	public int getAvgNumberOfDraws() {
		return 4;
	}
	
	public int getLongestGame() {
		return 55;
	}
	// REMOVE END
	
	// GETTER METHODS END
	
}
