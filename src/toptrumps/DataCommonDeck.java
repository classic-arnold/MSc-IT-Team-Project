package toptrumps;

import java.util.ArrayList;
import java.util.Iterator;

public class DataCommonDeck {
	/** stores the list of cards in the common deck */
	private ArrayList<DataCard> cardDeck = new ArrayList<DataCard>();
	
	/**
	 * create a new common deck
	 */
	public DataCommonDeck(){
		
	}
	
	/**
	 * get all cards in the common deck
	 * @return ArrayList<DataCard> representing all cards in the common deck
	 */
	ArrayList<DataCard> getAllCards(){
		return this.cardDeck;
	}
	
	/**
	 * add card to the common deck
	 * @param card represents card to add
	 */
	void add(DataCard card) {
		this.cardDeck.add(card);
	}
	
	/**
	 * remove card from the common deck
	 * @param card represents card to remove
	 */
	void remove(DataCard card) {
		this.cardDeck.remove(card);
	}
	
	/**
	 * remove card from the common deck
	 * @param index represents position of cards i.e index of card in ArrayList
	 */
	void remove(int index) {
		this.cardDeck.remove(index);
	}
	
	/**
	 * clear cards in common deck
	 */
	void clear() {
		this.cardDeck.clear();
	}
	
	/**
	 * get number of cards in common deck
	 * @return int representing number of cards in the common deck
	 */
	int size() {
		return cardDeck.size();
	}
	
	/**
	 * add cards to deck
	 * @param newCards representing list of cards to be added to deck
	 */
	void addCardsToDeck(ArrayList<DataCard> newCards) {
		for(DataCard card : newCards) {
			this.cardDeck.add(card);
		}
	}
}
