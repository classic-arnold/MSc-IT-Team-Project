package toptrumps;

import java.util.ArrayList;
import java.util.Iterator;

public class DataCommonDeck implements Iterable<DataCard> {
	private ArrayList<DataCard> cardDeck = new ArrayList<DataCard>();
	
	public DataCommonDeck(){
		
	}
	
	ArrayList<DataCard> getArrayList(){
		return this.cardDeck;
	}
	
	void add(DataCard card) {
		this.cardDeck.add(card);
	}
	
	void remove(DataCard card) {
		this.cardDeck.remove(card);
	}
	
	void remove(int index) {
		this.cardDeck.remove(index);
	}
	
	int size() {
		return cardDeck.size();
	}
	
	public Iterator<DataCard> iterator() {
		return cardDeck.iterator();
	}
}
