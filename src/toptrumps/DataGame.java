package toptrumps;

import java.util.ArrayList;

import java.util.Arrays;
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
 * TODO: Test all functions
 * TODO: Verify game specifications are accurate
 * TODO: Remove MARKED FOR REMOVAL
 * TODO: Add methods visibility
 * TODO: Reduce couplings and increase cohesion
 * TODO: Maybe create abstract class Cardable and move all deck methods there
 * TODO: Deal with all warnings
 * TODO: Refactor database to prevent object creation everytime
 *
 */

/**
 * 
 * represents the game class. It should only be created once per game, hence singleton pattern is used
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U
 *
 */
public class DataGame{

	/** represents an array of the category names */
	public static String[] CATEGORYNAMES;

	/** enumerated data type to represent the game state. Possible game states are running and ended */
	public enum GameState {
		RUNNING, ENDED
	}

	/** represents the DataGame instance */
	private static DataGame instance = null;

	/** represents the list of players (still active) in the game */
	private ArrayList<DataPlayer> activePlayers = new ArrayList<DataPlayer>();

	/** represents the list of all players (including failed and active players) that started the game */
	private ArrayList<DataPlayer> allPlayers = new ArrayList<DataPlayer>();

	/** represents the game state */
	private GameState gameState;

	/** represents the number of draws in a game */
	private int numberOfDraws;

	/** represents the common deck (common pile) */
	private ArrayList<DataCard> commonDeck = new ArrayList<DataCard>();

	/** represents the original deck of cards. This is the main deck object used during game methods. */
	private ArrayList<DataCard> originalDeck = new ArrayList<DataCard>();

	/** represents the initial unshuffled deck of cards. This is just used to store this data. */
	private ArrayList<DataCard> initialUnshuffledDeck = new ArrayList<DataCard>();

	/** represents the initial shuffled deck of cards. This is just used to store this data. */
	private ArrayList<DataCard> initialShuffledDeck = new ArrayList<DataCard>();

	/** represents the winner of the game(not round) */
	private DataPlayer gameWinner;

	/** represents the round. Used to store data of the round */
	private Round round = new Round();

	/**
	 * creates a new DataGame Object. Private because we implement the Singleton pattern.
	 * @param numberOfArtificialIntelligencePlayers represents the number of AI players in the game
	 */
	private DataGame(int numberOfArtificialIntelligencePlayers) {
		this.gameState = GameState.RUNNING; // set game state to running

		this.activePlayers.add(new DataPlayer(DataPlayer.PlayerType.HUMAN, numberOfArtificialIntelligencePlayers)); // add one human player

		// add the artificial intelligence players to the DataGame players depending on the number specified above
		for(int i=0;i<numberOfArtificialIntelligencePlayers;i++) {
			this.activePlayers.add(new DataPlayer(DataPlayer.PlayerType.AI, numberOfArtificialIntelligencePlayers));
		}

		// store original version of all players
		this.allPlayers = (ArrayList<DataPlayer>)this.activePlayers.clone();
	}

	// ------------------------------------------- //
	// ------------------------------------------- //
	// ------------------------------------------- //

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
	 * @return DataGame object representing the new game
	 */
	public static DataGame resetAndGetInstance(int numberOfArtificialIntelligencePlayers) {
		DataGame.instance = new DataGame(numberOfArtificialIntelligencePlayers); // start game
		return DataGame.instance;
	}

	/**
	 * starts the game
	 */
	public void startGame() {

		DataCardCache.loadCardFromFileAndCache(); // load cards and the categories from file and cache them

		ArrayList<DataCard> deck = this.getNewDeck(); // gets a fresh deck of cards from the cache

		this.initialUnshuffledDeck = deck; // store initial unshuffled deck

		this.originalDeck = this.shuffleDeck(deck); // shuffle the deck

		this.initialShuffledDeck = (ArrayList<DataCard>)this.originalDeck.clone(); // store initial shuffled deck in array

		int numberOfCardsPerPlayer = this.originalDeck.size()/this.activePlayers.size(); // get number of cards per player

		// share cards to player from deck
		for(DataPlayer player : this.activePlayers) {
			for(int i = 0; i<numberOfCardsPerPlayer; i++) {
				player.addCardToDeck(this.originalDeck.get(0));
				this.originalDeck.remove(0);
			}
		}

		this.originalDeck.clear(); // clear the leftover cards in the deck

		this.round.incrementRound(); // increase the round number

	}

	/**
	 * plays a round
	 * @param category string representing the chosen category
	 */
	public void playRound(String category) {

		// clear last round details
		this.round.roundWinningPlayers.clear();
		this.round.roundWinningCards.clear();
		this.round.roundAIPlayerCards.clear();

		// store round category
		this.round.roundCategory = category;

		ArrayList<DataCard> roundCards = new ArrayList<DataCard>(); // represents the cards drawn for this round

		// loop through active players, and get their topmost cards
		for(DataPlayer player : this.activePlayers) {

			// get player's topmost card
			DataCard card = player.getDeck().get(0);
			roundCards.add(card); // add player's topmost card to round cards

			// store human player card and AI players cards for the round
			if (player.getType()==DataPlayer.PlayerType.AI) {
				this.round.roundAIPlayerCards.add(card);
			}
		}

		this.round.roundCards = roundCards; // store round cards

		// represents the winning cards and winning players for the round
		HashMap<String, Object> winningCardsAndPlayers = this.getWinningCardsAndPlayers(DataGame.arrayListToArrayCard(roundCards), category);

		//		MARKED FOR REMOVAL
		//		HashMap<String, Object> winningCardsAndPlayers = this.getWinningCardsAndPlayers(roundCards.toArray(new DataCard[roundCards.size()]), category);

		// store round winning cards
		this.round.roundWinningCards = (ArrayList<DataCard>)winningCardsAndPlayers.get("winning cards");

		// represents round winning players
		HashSet<DataPlayer> roundWinningPlayers = (HashSet<DataPlayer>)winningCardsAndPlayers.get("winning players");

		// store round winning players from set
		for(DataPlayer player : roundWinningPlayers) {
			this.round.roundWinningPlayers.add(player);
		}

		// if there is only 1 winning player, round wasn't drawn
		if(this.round.roundWinningPlayers.size()==1) {

			roundCards.addAll(this.commonDeck); // add common deck cards to round cards
			this.commonDeck.clear(); // clear common deck

			this.round.roundWinningPlayers.get(0).addCardsToDeck(roundCards); // add round cards to winner deck
			this.round.roundWinningPlayers.get(0).incrementScore(); // increment winner score

			this.round.roundWasDraw = false; // store that round was not draw

			this.round.roundLastWinner = this.round.roundWinningPlayers.get(0); // store last round winner

		} else if (this.round.roundWinningPlayers.size()>1) { // if there were multiple winning players, round was drawn
			this.incrementNumberOfDraws(); // increment number of draws

			this.round.roundWasDraw = true; // store that round was draw

			this.commonDeck.addAll(roundCards); // add round cards to common deck
		}

		ArrayList<DataPlayer> playersToRemove = new ArrayList<DataPlayer>(); // represents list of players that have no cards

		// remove players marked for removal because they have no cards anymore
		for(DataPlayer player : this.activePlayers) {
			// if they have no cards in their deck, store them here
			if(player.getDeck().size()==0) {
				playersToRemove.add(player);
			}
		}

		// remove players marked for removal because they have no cards anymore
		for(DataPlayer player : playersToRemove) {
			this.activePlayers.remove(player);
		}

		HashMap<String, Object> roundDetails = this.getNewGameStateAndWinner(); // represents the round game state and winner

		// store the rounds game state and winner
		this.gameState = (GameState)roundDetails.get("gamestate");
		this.gameWinner = (DataPlayer)roundDetails.get("winner");

		// save game statistics if the game is not running anymore
		if(this.getGameState() != GameState.RUNNING) {
			this.saveGameStats(); // save game statistics
		}

	}

	// ------------------------------------------- //
	// ------------------------------------------- //
	// ------------------------------------------- //

	/**
	 * used to get a fresh new deck
	 * @return ArrayList representing lists of cards in new deck
	 */
	private ArrayList<DataCard> getNewDeck() {
		return new ArrayList<DataCard>(Arrays.asList(DataCardCache.getAllCardsInOrder()));
	}

	/**
	 * checks if game has ended
	 * @return GameState HashMap containing the winning player and the new game state
	 */
	private HashMap<String, Object> getNewGameStateAndWinner() {
		HashMap<String, Object> result = new HashMap<String, Object>(); // represents result of the method

		// check the numbers of players left. If it's just one, the game has ended and the player left is the winner
		if(this.activePlayers.size()==1) {
			result.put("winner", this.activePlayers.get(0)); // set player as winner
			result.put("gamestate", GameState.ENDED); // set game state ended
			return result;
		}

		// at this point, game must still be running
		// set null winner and game state running
		result.put("winner", null);
		result.put("gamestate", GameState.RUNNING);
		return result;
	}

	/**
	 * takes an array of cards and return the winning cards
	 * @param cards the array of all cards
	 * @param category the category selected
	 * @return HashMap<String, Object> containing list of winning cards and a set of winning players
	 */
	public HashMap<String, Object> getWinningCardsAndPlayers(DataCard[] cards, String category) {
		HashMap<String, Object> results = new HashMap<String, Object>(); // represent results of method
		ArrayList<DataCard> winningCards = new ArrayList<DataCard>(); // represents winning cards list
		HashSet<DataPlayer> winningPlayers = new HashSet<DataPlayer>(); // represents winning players list

		DataCard lastWinnerCard = cards[0]; // assume 1st card as winning card

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
	 * increase the number of draws by 1
	 */
	private void incrementNumberOfDraws() {
		this.numberOfDraws += 1;
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

		ArrayList<DataCard> shuffledDeck = new ArrayList<DataCard>(); // represents the shuffled deck

		HashSet<Integer> listOfRandoms = new HashSet<Integer>(); // represents set of used random numbers

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
	private void saveGameStats() {
		ProgramDatabase.insertGameStats(this);
	}

	// ------------------------------------------- //
	// ------------------------------------------- //
	// ------------------------------------------- //

	// GETTER METHODS START

	/**
	 * get initial unshuffled deck as array
	 * @return DataCard array containing all cards in initial unshuffled deck
	 */
	public DataCard[] getInitialUnshuffledDeck() {
		return this.initialUnshuffledDeck.toArray(new DataCard[this.initialUnshuffledDeck.size()]);
	}

	/**
	 * get initial shuffled deck as array
	 * @return DataCard array containing all cards in initial shuffled deck
	 */
	public DataCard[] getInitialShuffledDeck() {
		return this.initialShuffledDeck.toArray(new DataCard[this.initialShuffledDeck.size()]);
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
		return this.allPlayers.toArray(new DataPlayer[this.allPlayers.size()]);
	}

	/**
	 * get the number of draws for the game
	 * @return int representing number of draws
	 */
	public int getNumberOfDraws() {
		return this.numberOfDraws;
	}

	/**
	 * get number of cards in common pile
	 * @return int representing number of cards in common pile
	 */
	public int getNumberOfCardsInCommonPile() {
		return this.commonDeck.size();
	}

	/**
	 * get cards in common pile
	 * @return DataCard array containing the cards in the common pile
	 */
	public DataCard[] getCardsInCommonPile() {
		return this.commonDeck.toArray(new DataCard[this.commonDeck.size()]);
	}

	/**
	 * get current game state
	 * @return GameState enumerated type representing game state
	 */
	public GameState getGameState() {
		return this.gameState;
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
	public DataCard getRoundHumanPlayerCardBeforePlayRound() {
		try {
			return this.getHumanPlayer().getDeck().get(0);
		} catch(IndexOutOfBoundsException e) { // if player has no card, return null
			return null;
		}
	}

	/**
	 * get all cards drawn by AI player. Card owners not included.
	 * @return DataCard array representing the cards drawn by AI player
	 */
	public DataCard[] getRoundAIPlayerCardsBeforePlayRound() {
		ArrayList<DataCard> cards = new ArrayList<DataCard>(); // represents a list of the topmost cards of players
		DataPlayer[] players = this.getActivePlayers(); // represents all players still in the game

		// loop through all players, and if they are AI, get their top card
		for(DataPlayer player : players) {
			if(player.getType() == DataPlayer.PlayerType.AI) {
				cards.add(player.getDeck().get(0));
			}
		}
		return DataGame.arrayListToArrayCard(cards);
	}

	/**
	 * get the human player
	 * @return DataPlayer representing the human player
	 */
	public DataPlayer getHumanPlayer() {
		// check all players
		for(DataPlayer player : this.allPlayers) {
			// return any player thats human
			if(player.getType() == DataPlayer.PlayerType.HUMAN) {
				return player;
			}
		}
		// no human player found
		return null;
	}

	/**
	 * calculate, stores and get the player to choose category for the round
	 * @return DataPlayer representing the player to choose category for the round
	 */
	public DataPlayer getCategoryChooser() {
		DataPlayer player; // represents the player to choose category for the round

		// no player won last round, this means no player has won any round
		if (this.round.roundLastWinner != null) {
			player = this.round.roundLastWinner;
		} else if (this.round.roundActivePlayer != null){ // no player won last round, this means no player has won any round
			// we need the last active player, this would happen when all previous rounds are draws
			return player = this.round.roundActivePlayer;
		} else { // no player won last round, and has ever been active, this is only possible at round 1
			// we need to get a random player
			Random r = new Random();
			player = this.activePlayers.get(r.nextInt(this.activePlayers.size()));
		}
		this.round.roundActivePlayer = player; // store the player as the active player
		return player;
	}

	/**
	 * calculate and get the best category for a given player. This is used when the active player of the round is AI.
	 * @param player representing the player that we want to get best category for.
	 * @return int representing the category mapped from 1 to 5
	 */
	public int getBestCategoryForPlayer(DataPlayer player) {
		// return the best category for the players topmost card
		return player.getDeck().get(0).findTopCategory();
	}

	/**
	 * get the round details
	 * @return Round representing current round of game
	 */
	public Round getRound() {
		return this.round;
	}

	// GETTERS FROM DATABASE
	/**
	 * get number of human wins from database
	 * @return int representing number of human wins
	 */
	public static int getNumberOfHumanWins() {
		return ProgramDatabase.getHumanWon();
	}

	/**
	 * get number of AI wins from database
	 * @return int representing number of AI wins
	 */
	public static int getNumberOfAIWins() {
		return ProgramDatabase.getAIWon();
	}

	/**
	 * get number of games played from database
	 * @return int representing number of games played
	 */
	public static int getNumberOfGames() {
		return ProgramDatabase.getGameCount();
	}

	/**
	 * get the average number of draws that have occurred during all games from database
	 * @return double representing the average of all draws
	 */
	public static double getAvgNumberOfDraws() {
		return ProgramDatabase.getDraws();
	}

	/**
	 * get the number of rounds in the longest game from database
	 * @return int representing number of rounds in the longest game
	 */
	public static int getLongestGame() {
		return ProgramDatabase.getLargestRound();
	}

	// GETTER METHODS END

	// ------------------------------------------- //
	// ------------------------------------------- //
	// ------------------------------------------- //

	/**
	 * 
	 * Represents the round. Used to store round details.
	 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U
	 * 
	 */
	public class Round{

		/** represents the current round number */
		private int roundNumber;

		/** represents the draw status of round */
		private boolean roundWasDraw;

		/** represents the winning cards of the round */
		private ArrayList<DataCard> roundWinningCards = new ArrayList<DataCard>();

		/** represents the winning players of round */
		private ArrayList<DataPlayer> roundWinningPlayers = new ArrayList<DataPlayer>();

		/** represents the cards drawn by AI players for each round */
		private ArrayList<DataCard> roundAIPlayerCards = new ArrayList<DataCard>();

		/** represents the round category */
		private String roundCategory;

		/** represents the player that won last in any round */
		private DataPlayer roundLastWinner;

		/** represents the player that chose category in round */
		private DataPlayer roundActivePlayer;

		/** represents the round cards. Used to store data of the round */
		private ArrayList<DataCard> roundCards = new ArrayList<DataCard>();

		/**
		 * get the player to choose category for the round
		 * @return DataPlayer representing the player to choose category for the round
		 */
		public DataPlayer getRoundActivePlayer() {
			return this.roundActivePlayer;
		}

		/**
		 * get round winning card. Used to display info.
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
		 * get if round was draw
		 * @return boolean representing if round was drawn
		 */
		public boolean getRoundWasDraw() {
			return this.roundWasDraw;
		}

		/**
		 * get the round number
		 * @return int representing the round number
		 */
		public int getRoundNumber() {
			return this.roundNumber;
		}

		/**
		 * increase the round number by 1, only if game is running
		 */
		public void incrementRound() {
			if(gameState==DataGame.GameState.RUNNING) {
				this.roundNumber+=1;
			}
		}

		/**
		 * get the cards in the round
		 * @return DataCard[] representing the cards in the round
		 */
		public DataCard[] getRoundCards() {
			return DataGame.arrayListToArrayCard(this.roundCards);
		}
	}

}
