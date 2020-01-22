package toptrumps;

import java.util.ArrayList;
import java.util.Collections;
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
// We should use singleton design pattern to ensure it is only created once to avoid hard to detect bug, as the program is quite complex.
// Do not bother about enums. I'm using it only in the model. It makes my work easier and i cant convert to string for anyone to use.
class DataGame{

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
	
//	private int numberOfDraws; // Will be uncommented when I get Estelle's code
	
	/** represents the common deck */
	private DataCommonDeck commonDeck = new DataCommonDeck();
	
	/** represents the deck of cards */
	private ArrayList<DataCard> deck = new ArrayList<DataCard>();
	
	/** represents the winner */
	private DataPlayer winner;

	/**
	 * creates a new DataGame Object
	 * @param numberOfArtificialIntelligencePlayers represents the number of AI player in the game
	 */
	public DataGame(int numberOfArtificialIntelligencePlayers) {
		this.gameState = GameState.RUNNING; // set game state to running
		
		this.players.add(new DataPlayer(DataPlayer.PlayerType.HUMAN, this)); // add one human player
		
		// add the artificial intelligence players to the DataGame players depending on the number specified above
		for(int i=0;i<numberOfArtificialIntelligencePlayers;i++) {
			this.players.add(new DataPlayer(DataPlayer.PlayerType.AI, this));
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
	public HashMap<String, Object> checkGameState() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// check if there is any card in the deck. If there is, game cant be finished
		if(this.deck.size() == 0) {
			// check if any player has all cards. If they do, game is over, and that player is the winner
			for(DataPlayer player : players) {
				if(player.getDeck().size() == 40) {
					// store winner and game state
					result.put("winner", player);
					result.put("gamestate", GameState.ENDED);
					return result;
				}
			}
		}
		
		// store winner and game state
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
	public HashMap<String, Object> getWinningCards(DataCard[] cards, String category) {
		HashMap<String, Object> results = new HashMap<String, Object>();
		ArrayList<DataCard> winningCards = new ArrayList<DataCard>(); // initialize winning cards list
		HashSet<DataPlayer> winningPlayers = new HashSet<DataPlayer>(); // initialize winning cards list
		
		DataCard lastWinnerCard = cards[0]; //store 1st card as winning card
		
		// check each card against last winning card
		for(int i=0; i<cards.length; i++) {
			// if both cards equal, else if current card greater than last winning card
			if (cards[i].compare(lastWinnerCard, category) == 2) {
				lastWinnerCard = cards[i];
				winningCards.add(cards[i]); // add new higher card
				
				// store winning players
				for (DataPlayer player : this.players) {
					if(player.getDeck().contains(cards[i])) {
						winningPlayers.add(player);
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
					}
				}
			}
		}
		
		// store both lists in results
		results.put("winning cards", winningCards);
		results.put("winning players", winningPlayers);
		return results;
	}
	
	public void incrementRound() {
		this.roundNumber += 1;
	}
	
	public void playRound() {
		this.incrementRound();
		
		this.deck = this.shuffleDeck(this.getNewDeck());
		
//		for(int i=0;i<this.deck.size();i++) {
//			System.out.println(this.deck.get(i));	
//		}
		
		for(DataPlayer player : this.players) {
			player.setDeck(player.createRandomDeck(this));
		}
		
		return;
		
	}
	
	public RoundResults getRoundState(DataCard[] winningCards) {
		if(winningCards.length > 1) {
			return RoundResults.DRAW;
		} else {
			return RoundResults.WIN;
		}
	}
	
	public ArrayList<DataCard> shuffleDeck(ArrayList<DataCard> deck) {
		if(deck.size()==0) {
			throw new exceptions.NoCardInDeckException();
		}
		
		ArrayList<DataCard> shuffledDeck = new ArrayList<DataCard>();
		
		HashSet<Integer> listOfRandoms = new HashSet<Integer>();
		
		Random r = new Random();
		
		do {
			int randomNumber = r.nextInt(deck.size());
			if(listOfRandoms.contains(randomNumber)) {
				randomNumber = r.nextInt(deck.size());
			}
			shuffledDeck.add(deck.get(randomNumber));
			listOfRandoms.add(randomNumber);
		} while(shuffledDeck.size()<40);
		
		return shuffledDeck;
	}
	
	public static <t> ArrayList<t> arrayToArrayList(t[] array){
		ArrayList<t> arrayList = new ArrayList<t>();
		for(int i = 0; i<array.length;i++) {
			arrayList.add(array[i]);
		}
		return arrayList;
	}
	
	public static DataCard[] arrayListToArrayCard(ArrayList<DataCard> arrayList){
		int length = arrayList.size();
		DataCard[] array = new DataCard[length];
		for(int i = 0; i<length;i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}
	
	public static DataPlayer[] arrayListToArrayPlayer(ArrayList<DataPlayer> arrayList){
		int length = arrayList.size();
		DataPlayer[] array = new DataPlayer[length];
		for(int i = 0; i<length;i++) {
			array[i] = arrayList.get(i);
		}
		return array;
	}
	
	// GETTER METHODS START
	
	public DataCard[] getCompleteDeckAsArray() {
		return DataGame.arrayListToArrayCard(this.deck);
	}
	
	public ArrayList<DataCard> getCompleteDeckAsArrayList() {
		return this.deck;
	}
	
	public int getRoundNumber() {
		return this.roundNumber;
	}
	
	public DataPlayer[] getPlayers() {
		return DataGame.arrayListToArrayPlayer(this.players);
	}
	
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
	
/**
 * Updates database using methods provided in the database class
 */
	public void saveGameStats() {
//		Database.incrementNumberOfGames();
		
		if(winner.getType()==DataPlayer.PlayerType.HUMAN) {
//			Database.incrementNumberOfHumanWins();
		} else if(winner.getType()==DataPlayer.PlayerType.AI) {
//			Database.incrementNumberOfAIWins();
		}
		
//		Database.incrementNumberOfDraws(this.numberOfDraws);
		
		if(this.gameState == GameState.ENDED) {
//			Database.calculateLongestGame(this.roundNumber);
		}
	}
	
}
