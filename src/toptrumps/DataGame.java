package toptrumps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

class DataGame{
	
	private ArrayList<DataPlayer> players = new ArrayList<DataPlayer>();
	
	enum GameState {
		RUNNING, ENDED
	}
	
	enum RoundResults {
		DRAW, WIN
	}
	
	private GameState gameState;
	
	private int roundNumber;
	
//	private int numberOfDraws;
	
	private DataCommonDeck commonDeck = new DataCommonDeck();
	
	private ArrayList<DataCard> deck = new ArrayList<DataCard>();
	
	private DataPlayer winner;

	public DataGame(int numberOfArtificialIntelligencePlayers) {
		this.gameState = GameState.RUNNING;
		this.players.add(new DataPlayer(DataPlayer.PlayerType.HUMAN, this));
		for(int i=0;i<numberOfArtificialIntelligencePlayers;i++) {
			this.players.add(new DataPlayer(DataPlayer.PlayerType.AI, this));
		}
	}
	
	public ArrayList<DataCard> getNewDeck() {
		return DataGame.arrayToArrayList(DataCardCache.getAllCardsInOrder());
	}
	
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
		
		for(int i=0;i<this.deck.size();i++) {
			System.out.println(this.deck.get(i));	
		}
		
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
