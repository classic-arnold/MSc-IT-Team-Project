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
public class DataPlayer {
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
	private ArrayList<DataCard> cardDeck = new ArrayList<DataCard>();

	/** PlayerType enum representing the type of player - human or AI */
	private PlayerType type;

	/** integer representing the player's current score */
	private int score;

	/** String representing the player's name */
	private String name;

	/**
	 * creates a new player
	 * @param type PlayerType enum representing the type of player
	 * @param numberOfArtificialIntelligencePlayers represents the number of AI players
	 */
	DataPlayer(PlayerType type, int numberOfArtificialIntelligencePlayers) {
		this.type = type; //set type
		this.score = 0;
		
		// name players based on type
		if (this.type == PlayerType.HUMAN) {
			this.name = "You";
		} else if (this.type == PlayerType.AI) {
			this.name = "AI Player " + ARTIFICIAL_INTELLIGENCE_ID++; // name AI players using static ID based on the number
			this.resetArtificialIntelligenceId(numberOfArtificialIntelligencePlayers);
		}
	}
	
	/**
	 * reset static ID used to name AI players
	 * @param numberOfArtificialIntelligencePlayers number of AI players
	 */
	private void resetArtificialIntelligenceId(int numberOfArtificialIntelligencePlayers){
		// if ID is higher than no of AI players, reset back to 1
		if(DataPlayer.ARTIFICIAL_INTELLIGENCE_ID>numberOfArtificialIntelligencePlayers) {
			DataPlayer.ARTIFICIAL_INTELLIGENCE_ID = 1;
		}
	}
	
//	ArrayList<DataCard> createRandomDeck(ArrayList<DataCard> deck) {
//		ArrayList<DataCard> completeDeck = deck;
//		ArrayList<DataCard> cardDeck = new ArrayList<DataCard>(Collections.nCopies(7, null));
//		ArrayList<Integer> randomNumbers = new ArrayList<Integer>();
//
//		Random random = new Random();
//		for(int i=0;i<cardDeck.size();i++) {
//			int randomNumber;
//			do {
//				randomNumber = random.nextInt(completeDeck.size());
//			} while(randomNumbers.contains(randomNumber));
//			
//			cardDeck.remove(i);
//			cardDeck.add(i, completeDeck.get(randomNumber));
//			completeDeck.remove(randomNumber);
//			
//			randomNumbers.add(randomNumber);
//		}
//		
//		return cardDeck;
//	}
	
	/**
	 * add cardS to player deck
	 * @param newCards represents the lists of cards to be added to deck
	 */
	void addCardsToDeck(ArrayList<DataCard> newCards) {
		for(DataCard card : newCards) {
			this.cardDeck.add(card);
		}
	}
	
	/**
	 * add card to player deck
	 * @param newCard represents the card to be added to deck
	 */
	void addCardToDeck(DataCard newCard) {
		this.cardDeck.add(newCard);
	}
	
	/**
	 * remove cardS from player deck
	 * @param cardsToRemove represents the lists of cards to be removed from deck
	 */
	void removeCardsFromDeck(ArrayList<DataCard> cardsToRemove) {
		for(DataCard card : cardsToRemove) {
			this.cardDeck.remove(card);
		}
	}
	
	/**
	 * increment players score by 1
	 */
	void incrementScore() {
		this.score += 1;
	}
	
	/**
	 * remove card from player deck
	 * @param cardToRemove represents the card to be removed from deck
	 */
	void removeCardFromDeck(DataCard cardToRemove) {
		this.cardDeck.remove(cardToRemove);
	}
	
	/**
	 * remove top card from player deck
	 */
	void removeTopCardFromDeck() {
		this.cardDeck.remove(0);
	}
	
	/**
	 * set player deck
	 * @param deck the list of cards we want to set player deck to
	 */
	void setDeck(ArrayList<DataCard> deck) {
		this.cardDeck = deck;
	}

	// GETTER METHODS START

	/**
	 * get player score
	 * @return int representing the player's score for the game
	 */
	int getScore() {
		return this.score;
	}

	/**
	 * get player type
	 * @return PlayerType enum representing the player's type
	 */
	PlayerType getType() {
		return this.type;
	}
	
	/**
	 * get players type as string
	 * @return String representing the player's type
	 */
	String getTypeAsString() {
		if(this.type == PlayerType.AI) {
			return "ai";
		} else if (this.type == PlayerType.HUMAN) {
			return "human";
		} else {
			return null;
		}
	}

	/**
	 * get player's name
	 * @return String representing the player's name
	 */
	String getName() {
		return this.name;
	}
	
	/**
	 * get players deck
	 * @return ArrayList<DataCard> representing the list of cards in players deck
	 */
	ArrayList<DataCard> getDeck() {
		return this.cardDeck;
	}
	
	/**
	 * get number of cards in players deck
	 * @return int representing the number of cards in players deck
	 */
	int getNumberOfCardsInDeck() {
		return this.cardDeck.size();
	}

	// GETTER METHODS END

}
