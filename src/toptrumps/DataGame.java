package toptrumps;

import java.util.HashMap;
import java.util.Random;

class DataGame{
	private static DataGame instance = null; // initialize again during game reset
	
	private DataPlayer[] players;
	
	enum GameState {
		RUNNING, ENDED
	}
	
	private GameState gameState;
	
	private int roundNumber;
	
	private DataCard[] completeDeck = new DataCard[40];

	private DataGame(int numberOfArtificialIntelligencePlayers) {
		
	}

	public static DataGame getInstance(){
		if (DataGame.instance == null) { 
			DataGame.instance = new DataGame(4); // 4 or 3?
		} 
		return DataGame.instance; 
	}
	
	public DataCard[] getNewDeck() {
		return DataCardCache.getAllCardsInOrder();
	}
	
	public DataCard[] shuffleDeck() throws exceptions.NoCardInDeckException {
		if(this.completeDeck.length==0) {
			throw new exceptions.NoCardInDeckException();
		}
		
		DataCard[] shuffledDeck = new DataCard[40];
		
		for (DataCard card : this.completeDeck) {
			Random r = new Random();
			int randomNumber = r.nextInt(shuffledDeck.length);
			
			while(shuffledDeck[randomNumber] != null) {
				randomNumber = r.nextInt(shuffledDeck.length);
			}
			
			shuffledDeck[randomNumber] = card;
		}
		
		return shuffledDeck;
	}
	
	public DataCard[] getCompleteDeck() {
		return this.completeDeck;
	}
	
}
