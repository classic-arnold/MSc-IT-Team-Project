package toptrumps;

import java.util.ArrayList;
import java.util.Random;

class DataGame{
	private static DataGame instance = null; // initialize again during game reset
	
	private ArrayList<DataPlayer> players;
	
	enum GameState {
		RUNNING, ENDED
	}
	
	private GameState gameState;
	
	private int roundNumber;
	
	private ArrayList<DataCard> commonDeck = new ArrayList<DataCard>();

	private DataGame(int numberOfArtificialIntelligencePlayers) {
		
	}

	public static DataGame getInstance(){
		if (DataGame.instance == null) { 
			DataGame.instance = new DataGame(4); // 4 or 3?
		} 
		return DataGame.instance; 
	}
	
	public ArrayList<DataCard> getNewDeck() {
		return DataGame.arrayToArrayList(DataCardCache.getAllCardsInOrder());
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
	
	public ArrayList<DataCard> shuffleDeck() {
		if(this.commonDeck.size()==0) {
			throw new exceptions.NoCardInDeckException();
		}
		
		ArrayList<DataCard> shuffledDeck = new ArrayList<DataCard>();
		
		for (DataCard card : this.commonDeck) {
			Random r = new Random();
			int randomNumber = r.nextInt(shuffledDeck.size());
			
			while(shuffledDeck.get(randomNumber) != null) {
				randomNumber = r.nextInt(shuffledDeck.size());
			}
			
			shuffledDeck.add(randomNumber, card);
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
	
	public DataCard[] getCompleteDeckAsArray() {
		return DataGame.arrayListToArrayCard(this.commonDeck);
	}
	
	public ArrayList<DataCard> getCompleteDeckAsArrayList() {
		return this.commonDeck;
	}
	
	public int getRoundNumber() {
		return this.roundNumber;
	}
	
	// GETTER METHODS END
	
}
