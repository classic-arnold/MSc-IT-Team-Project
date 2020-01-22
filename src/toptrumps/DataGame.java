package toptrumps;

import java.util.ArrayList;
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
	 * @return GameState enum representing the new game state
	 */
	public GameState checkGameState() {
		if(this.deck.size() == 0) {
			for(DataPlayer player : players) {
				if(player.getDeck().size() == 40) {
					this.winner = player;
					return GameState.ENDED;
				}
			}
		}
		return GameState.RUNNING;
	}
	
	/**
	 * takes an array of cards and return the winning cards
	 * @param cards the array of cards
	 * @param category
	 * @return
	 */
	public ArrayList<DataCard> getWinningCards(DataCard[] cards, String category) {
		ArrayList<DataCard> winningCards = new ArrayList<DataCard>();
		
		DataCard lastWinnerCard = cards[0];
		
		for(int i=0; i<cards.length; i++) {
			if (cards[i].compare(lastWinnerCard, category) == 2) {
				lastWinnerCard = cards[i];
				winningCards.add(cards[i]);
			} else if (cards[i].compare(lastWinnerCard, category) == 1) {
				lastWinnerCard = cards[i];
				winningCards.clear();
				winningCards.add(cards[i]);
			}
		}
		
		return winningCards;
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
