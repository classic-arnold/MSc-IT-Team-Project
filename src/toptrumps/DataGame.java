package toptrumps;

import java.util.ArrayList;
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
	
	public DataCard[] getWinningCards(DataCard[] cards, String category) {
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
		
		int noOfWinningCards = winningCards.size();
		
		DataCard[] winningCardsToArray = new DataCard[noOfWinningCards];
		
		for(int i=0; i<noOfWinningCards; i++) {
			winningCardsToArray[i] = winningCards.get(i);
		}
		
		return winningCardsToArray;
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
	
	public DataCard[] getCompleteDeck() {
		return this.completeDeck;
	}
	
	public int getRoundNumber() {
		return this.roundNumber;
	}
	
	// GETTER METHODS END
	
}
