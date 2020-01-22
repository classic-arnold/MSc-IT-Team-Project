package toptrumps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
	private static int ARTIFICIAL_INTELLIGENCE_ID = 1;

	/** DataCard array representing the deck of cards */
	private ArrayList<DataCard> cardDeck;

	/** PlayerType enum representing the type of player - human or AI */
	private PlayerType type;

	/** integer representing the player's current score */
	private int score;

	/** String representing the player's name */
	private String name;

	DataPlayer(PlayerType type, DataGame game) {
		this.type = type;

		if (this.type == PlayerType.HUMAN) {
			this.name = "You";
			this.score = 0;
		} else if (this.type == PlayerType.AI) {
			this.name = "AI Player " + ARTIFICIAL_INTELLIGENCE_ID++;
			this.resetArtificialIntelligenceId();
			this.score = 0;
		}
	}
	
	void resetArtificialIntelligenceId(){
		if(DataPlayer.ARTIFICIAL_INTELLIGENCE_ID>4) {
			DataPlayer.ARTIFICIAL_INTELLIGENCE_ID = 1;
		}
	}
	
	ArrayList<DataCard> createRandomDeck(DataGame game) {
		ArrayList<DataCard> completeDeck = game.getCompleteDeckAsArrayList();
		ArrayList<DataCard> cardDeck = new ArrayList<DataCard>(Collections.nCopies(7, null));
		ArrayList<Integer> randomNumbers = new ArrayList<Integer>();

		Random random = new Random();
		for(int i=0;i<cardDeck.size();i++) {
			int randomNumber;
			do {
				randomNumber = random.nextInt(completeDeck.size());
			} while(randomNumbers.contains(randomNumber));
			
			cardDeck.add(completeDeck.get(randomNumber));
			completeDeck.remove(randomNumber);
			
			randomNumbers.add(randomNumber);
		}
		
		return cardDeck;
	}
	
	void addCardsToDeck(ArrayList<DataCard> newCards) {
		for(DataCard card : newCards) {
			this.cardDeck.add(card);
		}
	}
	
	void removeCardsFromDeck(ArrayList<DataCard> newCards) {
		for(DataCard card : newCards) {
			this.cardDeck.remove(card);
		}
	}
	
	void setDeck(ArrayList<DataCard> deck) {
		this.cardDeck = deck;
	}

	// GETTER METHODS START

	int getScore() {
		return this.score;
	}

	PlayerType getType() {
		return this.type;
	}
	
	String getTypeAsString() {
		if(this.type == PlayerType.AI) {
			return "ai";
		} else if (this.type == PlayerType.HUMAN) {
			return "human";
		} else {
			return null;
		}
	}

	String getName() {
		return this.name;
	}
	
	ArrayList<DataCard> getDeck() {
		return this.cardDeck;
	}
	
	int getNumberOfCardsInDeck() {
		return this.cardDeck.size();
	}

	// GETTER METHODS END

	void updateScore(int points) {
		this.score += points;
	}

}
