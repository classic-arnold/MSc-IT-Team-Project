package toptrumps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * 
 * DataGame represents the game class. It should only be created once per game.
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U Team
 *
 */
// NOTES FOR TEAM
// We should use singleton design pattern to ensure this object is only created once to avoid hard to detect bugs, as the program is quite complex.
// Do not bother about enums. I'm using it only in the model. It makes my work easier and i can convert to string for anyone to use.
public class DataGame{
	
	/** represents the DataGame instance */
	private static DataGame instance = null;

	/** represents an array of the category names */
	public static final String[] CATEGORYNAMES = {"size", "speed", "range", "firePower", "cargo"};
	
	/** represents a list of players in the game */
	private ArrayList<DataPlayer> players = new ArrayList<DataPlayer>();
	
	/** enum to represent the game state */
	enum GameState {
		RUNNING, ENDED
	}
	
	/** enum to represent the game state */
	enum RoundResults {
		DRAW, WIN
	}
	
	/** represents the game state */
	private GameState gameState;
	
	/** represents the current round number */
	private int roundNumber;
	
	/** represents the number of draws for this game */
	private int numberOfDraws; // Will be used when I get Estelle's code
	
	/** represents the common deck */
	private DataCommonDeck commonDeck = new DataCommonDeck();
	
	/** represents the deck of cards */
	private ArrayList<DataCard> originalDeck = new ArrayList<DataCard>();
	
	/** represents the winner of the game(not round) */
	private DataPlayer winner;
	
	/** represents the status of last round */
	private boolean roundWasDraw;
	
	/** represents the winning card of the last round */
	private ArrayList<DataCard> roundWinningCards = new ArrayList<DataCard>();
	
	/** represents the players of last round */
	private ArrayList<DataPlayer> roundWinningPlayers = new ArrayList<DataPlayer>();
	
	/** represents the players of last round */
	private boolean roundHasPlayersDrawnCards;
	
	/** represents the players of last round */
	private DataCard roundHumanPlayerCard;
	
	/** represents the players of last round */
	private ArrayList<DataCard> roundAIPlayerCards;
	
//	/** represents the last round category */
//	private String roundCategory;

	/**
	 * creates a new DataGame Object
	 * @param numberOfArtificialIntelligencePlayers represents the number of AI player in the game
	 */
	private DataGame(int numberOfArtificialIntelligencePlayers) {
		this.gameState = GameState.RUNNING; // set game state to running
		
		this.players.add(new DataPlayer(DataPlayer.PlayerType.HUMAN, this)); // add one human player
		
		// add the artificial intelligence players to the DataGame players depending on the number specified above
		for(int i=0;i<numberOfArtificialIntelligencePlayers;i++) {
			this.players.add(new DataPlayer(DataPlayer.PlayerType.AI, this));
		}
	}
	
	/**
	 * gets the instance of the single DataGame
	 * @param numberOfArtificialIntelligencePlayers represents the number of AI players in the game
	 * @return DataGame object representing the current game
	 */
	public static DataGame getInstance(int numberOfArtificialIntelligencePlayers) {
		// if game hasnt been started, start it
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
		
		int numberOfCardsPerPlayer = this.originalDeck.size()/this.players.size(); // get number of cards per player
		
		// serve cards to player from deck
		for(DataPlayer player : this.players) {
			for(int i = 0; i<numberOfCardsPerPlayer; i++) {
				player.addCardToDeck(this.originalDeck.get(0));
				this.originalDeck.remove(0);
			}
		}
		
		this.originalDeck.clear(); // clear the leftover cards in the deck
		
//		for testing
//		for(int i=0;i<this.deck.size();i++) {
//			System.out.println(this.deck.get(i));	
//		}
		
//		for testing
//		System.out.println(this.players.get(0).getDeck().get(0));
		
//		for testing
//		System.out.println(this.originalDeck.size());
		
		this.incrementRound(); // increase the round number
		
	}
	
	/**
	 * plays a round
	 * @param category string representing the chosen category
	 */
	public void playRound(String category) {
		this.roundWinningPlayers.clear();
		this.roundWinningCards.clear();
		
		
		ArrayList<DataCard> roundCards = new ArrayList<DataCard>();
		
		ArrayList<DataPlayer> playersToRemove = new ArrayList<DataPlayer>();
		
		for(DataPlayer player : this.players) {
//			System.out.println(player.getName());
			if(player.getDeck().size()==0) {
				playersToRemove.add(player);
//				System.out.println(player.getName());
//				System.out.println("No cards\n");
			} else {
				System.out.println(player.getName());
				System.out.println(player.getDeck().get(0));
				DataCard card = player.getDeck().get(0);
//				player.removeTopCardFromDeck();
				roundCards.add(card);
				
				if(player.getType()==DataPlayer.PlayerType.HUMAN) {
					this.roundHumanPlayerCard = card;
				} else if (player.getType()==DataPlayer.PlayerType.AI) {
					this.roundAIPlayerCards.add(card);
				}
			}
//			player.getDeck().remove(0); // moved elsewhere
		}
		
		for(DataPlayer player : playersToRemove) {
			this.players.remove(player);
		}
		
		this.roundHasPlayersDrawnCards = true;
		
		HashMap<String, Object> roundDetails = this.getNewGameStateAndWinner();
		
		this.gameState = (GameState)roundDetails.get("gamestate");
		this.winner = (DataPlayer)roundDetails.get("winner");
		
		if(this.getGameState() == GameState.RUNNING) {
			this.incrementRound(); // increase the round number
		} else {
			this.saveGameStats();
			if(this.winner!=null) {
				System.out.println("Winner: " + this.winner.getName());
				System.out.println("This should be 40: "+(this.winner.getDeck().size() + this.commonDeck.size()));
			} else {
				System.out.println("No winner. Game Drawn.");
			}
				
			System.out.println("Final round: " + this.roundNumber);
			return;
		}
		
		HashMap<String, Object> winningCardsAndPlayers = this.getWinningCardsAndPlayers(DataGame.arrayListToArrayCard(roundCards), category);
		
		this.roundWinningCards = (ArrayList<DataCard>)winningCardsAndPlayers.get("winning cards");
		
		HashSet<DataPlayer> roundWinningPlayers = (HashSet<DataPlayer>)winningCardsAndPlayers.get("winning players");
		
		for(DataPlayer player : roundWinningPlayers) {
			this.roundWinningPlayers.add(player);
		}
		
		if(this.roundWinningPlayers.size()==1) {
			this.roundWinningPlayers.get(0).addCardsToDeck(roundCards);
			this.roundWasDraw = false;
			System.out.println("Round " + this.roundNumber + " winner: " + this.roundWinningPlayers.get(0).getName() + "\n");
		} else if (this.roundWinningPlayers.size()>1) {
			this.incrementNumberOfDraws();
			this.roundWasDraw = true;
			this.commonDeck.addCardsToDeck(roundCards);
			System.out.println("Round " + this.roundNumber + " draw\n");
		}
		
	}
	
	/**
	 * used to get a fresh new deck
	 * @return ArrayList representing lists of cards in new deck
	 */
	public ArrayList<DataCard> getNewDeck() {
		return DataGame.arrayToArrayList(DataCardCache.getAllCardsInOrder());
	}
	
	public DataCard getRoundHumanPlayerCard() {
		return this.roundHumanPlayerCard;
	}
	
	public DataCard[] getRoundAIPlayerCards() {
		return DataGame.arrayListToArrayCard(this.roundAIPlayerCards);
	}
	
	/**
	 * checks if game has ended
	 * @return GameState HashMap containing the winning player and the new game state
	 */
	public HashMap<String, Object> getNewGameStateAndWinner() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// check if there is any card in the deck. If there is, game cant be finished
//		int checker = 0;
//		for(DataPlayer player : this.players) {
//			if(player.getDeck().size()>0) {
//				checker += 1;
//			}
//		}
		if(this.players.size()==1) {
			// check if any player has all cards. If they do, game is over, and that player is the winner
			result.put("winner", this.players.get(0));
			result.put("gamestate", GameState.ENDED);
			return result;
		}
		
		if(this.didWholeGameDraw()) {
			result.put("winner", null);
			result.put("gamestate", GameState.ENDED);
			return result;
		}
		
		// store winner and game state
		result.put("winner", null);
		result.put("gamestate", GameState.RUNNING);
		return result;
	}
	
	public boolean didWholeGameDraw() {
		if(this.commonDeck.size()==40) {
			return true;
		}
		return false;
	}
	
	/**
	 * takes an array of cards and return the winning cards
	 * @param cards the array of all cards
	 * @param category the category selected
	 * @return HashMap<String, Object> containing list of winning cards and a set of winning players
	 */
	public HashMap<String, Object> getWinningCardsAndPlayers(DataCard[] cards, String category) {
		HashMap<String, Object> results = new HashMap<String, Object>();
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
			// if both cards equal, else if current card greater than last winning card
			if (cards[i].compare(lastWinnerCard, category) == 2) {
				lastWinnerCard = cards[i];
				winningCards.add(cards[i]); // add new higher and equal card
				
				// store winning players
				for (DataPlayer player : this.players) {
					if(player.getDeck().contains(cards[i])) {
						winningPlayers.add(player);
//						player.getDeck().remove(cards[i]); // remove cards
					}
				}
				
			} else if (cards[i].compare(lastWinnerCard, category) == 1) {
				lastWinnerCard = cards[i];
				winningCards.clear(); // reset list of winning cards, because we have a new higher card
				winningCards.add(cards[i]); // add new higher card
				
				// store winning players
				for (DataPlayer player : this.players) {
					if(player.getDeck().contains(cards[i])) {
						winningPlayers.clear();
						winningPlayers.add(player);
//						player.getDeck().remove(cards[i]);
					}
				}
			}
		}
		
		for(DataPlayer player : this.players) {
			player.removeTopCardFromDeck();
		}
		
		// store both lists in results
		results.put("winning cards", winningCards);
		results.put("winning players", winningPlayers);
		return results;
	}
	
	/**
	 * increase the round number and reset roundHasPlayersDrawnCards to false
	 */
	public void incrementRound() {
		this.roundNumber += 1;
		this.roundHasPlayersDrawnCards = false;
		this.roundAIPlayerCards.clear();
	}
	
	public void incrementNumberOfDraws() {
		this.numberOfDraws += 1;
	}
	
	/**
	 * calculates if round was a draw or not by counting the number of winning playes
	 * @param winningPlayers represents the list of winning players
	 * @return RoundResults enum representing the round result
	 */
	public RoundResults getRoundState(DataPlayer[] winningPlayers) {
		// if there are more than 1 player, its a draw, else its win
		if(winningPlayers.length > 1) {
			return RoundResults.DRAW;
		} else {
			return RoundResults.WIN;
		}
	}
	
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
		
		ArrayList<DataCard> shuffledDeck = new ArrayList<DataCard>();
		
		HashSet<Integer> listOfRandoms = new HashSet<Integer>();
		
		Random r = new Random();
		
		// run this loop until the shuffledDeck has 40 cards
		while(shuffledDeck.size()<40){
			int randomNumber;
			
			// check if this random number has been used before, and keep getting random numbers until we find one that hasn't
			do{
				randomNumber = r.nextInt(deck.size());
			} while(listOfRandoms.contains(randomNumber));
			
			shuffledDeck.add(deck.get(randomNumber));
			listOfRandoms.add(randomNumber);
		}
		
		return shuffledDeck;
	}
	
	/**
	 * Static method used to convert an array of generic objects to an ArrayList
	 * @param <t> generic type
	 * @param array original array
	 * @return an ArrayList shallow copy of array
	 */
	public static <t> ArrayList<t> arrayToArrayList(t[] array){
		ArrayList<t> arrayList = new ArrayList<t>();
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
		for(int i = 0; i<length;i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}
	
	/**
	 * Updates database using methods provided in the database class
	 */
		public void saveGameStats() {
//			Database.incrementNumberOfGames();
			if(this.winner!=null) {
				if(winner.getType()==DataPlayer.PlayerType.HUMAN) {
//					Database.incrementNumberOfHumanWins();
				} else if(winner.getType()==DataPlayer.PlayerType.AI) {
//					Database.incrementNumberOfAIWins();
				}
			}
			
			for (DataPlayer player : this.players) {
//				Database.setPlayerScore(player);
			}
			
//			Database.setNumberOfDraws(this.numberOfDraws);
			
			if(this.gameState == GameState.ENDED) {
//				Database.calculateLongestGame(this.roundNumber);
			}
		}
	
	// GETTER METHODS START
	
	public DataCard[] getCompleteDeckAsArray() {
		return DataGame.arrayListToArrayCard(this.originalDeck);
	}
	
	public ArrayList<DataCard> getCompleteDeckAsArrayList() {
		return this.originalDeck;
	}
	
	public int getRoundNumber() {
		return this.roundNumber;
	}
	
	public DataPlayer[] getPlayers() {
		return DataGame.arrayListToArrayPlayer(this.players);
	}
	
	public boolean getRoundResults() {
		return this.roundWasDraw;
	}
	
	public int getNumberOfCardsInCommonPile() {
		return this.commonDeck.size();
	}
	
	public GameState getGameState() {
		return this.gameState;
	}
	
	public ArrayList<DataCard> getRoundWinningCards() {
		return this.roundWinningCards;
	}
	
	public DataCard getRoundWinningCard() {
		return this.roundWinningCards.get(0);
	}
	
	public ArrayList<DataPlayer> getRoundWinningPlayers() {
		return this.roundWinningPlayers;
	}
	
//	public String getRoundCategory() {
//		return this.roundCategory;
//	}
	
//	getters from database - waiting on Estelle
//	public int getNumberOfHumanWins() {
//		return Database.getNumberOfHumanWins();
//	}
//	
//	public int getNumberOfAIWins() {
//		return Database.getNumberOfAIWins();
//	}
//	
//	public int getNumberOfGames() {
//		return Database.getNumberOfGames();
//	}
//	
//	public int getAvgNumberOfDraws() {
//		return Database.getAvgNumberOfDraws();
//	}
//	
//	public int getLongestGame() {
//		return Database.getLongestGame();
//	}
	
	// fake getters from database to return mock values
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
	
	// GETTER METHODS END
	
}
