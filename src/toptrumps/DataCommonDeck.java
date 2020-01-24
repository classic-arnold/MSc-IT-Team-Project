package toptrumps;

import java.util.ArrayList;
import java.util.Collections;
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

	void addAll(ArrayList<DataCard> list){
		for(DataCard dataCard : list){
			this.cardDeck.add(dataCard);
		}
	}
	
	void remove(DataCard card) {
		this.cardDeck.remove(card);
	}
	
	void remove(int index) {
		this.cardDeck.remove(index);
	}

	ArrayList<DataCard> pop(){
		ArrayList<DataCard> list = new ArrayList<>(cardDeck);
		cardDeck.clear();
		return list;
	}

	
	int size() {
		return cardDeck.size();
	}
	
	public Iterator<DataCard> iterator() {
		return cardDeck.iterator();
	}

	boolean isEmpty(){
		return cardDeck.isEmpty();
	}
}
