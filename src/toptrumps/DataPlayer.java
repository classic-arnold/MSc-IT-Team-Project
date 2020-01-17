package toptrumps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 
 * This class represents a player
 * 
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U Team
 *
 */
class DataPlayer {
	/**
	 * enum to represents the different types of player
	 * @author salistechltd
	 */
	enum PlayerType {
		AI, HUMAN
	}
	
	/** static int to keep track of AI player count */
	private static int AIID = 1; //reset this when you restart game
	
	/** DataCard array representing the deck of cards */
	private DataCard[] cardDeck = new DataCard[7];
	
	/** PlayerType enum representing the type of player - human or AI */
	private PlayerType type;
	
	/** integer representing the player's current score */
	private int score;
	
	/** String representing the player's name */
	private String name;
	
	DataPlayer(PlayerType type) {
		this.createPlayer(type);
		this.cardDeck = this.createRandomDeck();
	}
	
	void createPlayer(PlayerType type) {
		this.type = type;
		
		if (type == PlayerType.HUMAN) {
			this.name = "You";
			this.score = 0;
		} else if (type == PlayerType.AI) {
			this.name = "AI Player " + AIID++;
			this.score = 0;
		}
	}
	
	DataCard[] createRandomDeck() {
		DataCard[] cardDeck = new DataCard[7];
		
		for(DataCard card:cardDeck) {
			card = DataCardCache.getRandomCard();
		}
		
		return cardDeck;
	}
	
	
	
}
