package toptrumps;

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
	private static int ARTIFICIAL_INTELLIGENCE_ID = 1; //reset this when you restart game

	/** DataCard array representing the deck of cards */
	private DataCard[] cardDeck;

	/** PlayerType enum representing the type of player - human or AI */
	private PlayerType type;

	/** integer representing the player's current score */
	private int score;

	/** String representing the player's name */
	private String name;

	DataPlayer(String type) {
		switch (Character.toLowerCase(type.charAt(0))) {

		case 'h':
			this.type = PlayerType.HUMAN;
			break;
		case 'a':
			this.type = PlayerType.AI;
			break;
		}

		if (this.type == PlayerType.HUMAN) {
			this.name = "You";
			this.score = 0;
		} else if (this.type == PlayerType.AI) {
			this.name = "AI Player " + ARTIFICIAL_INTELLIGENCE_ID++;
			this.score = 0;
		}

		this.cardDeck = this.createRandomDeck();
	}

	DataCard[] createRandomDeck() {
		DataCard[] cardDeck = new DataCard[7];

		for(int i=0; i<cardDeck.length; i++) {
			cardDeck[i] = DataCardCache.getRandomCard();
		}

		return cardDeck;
	}

	// GETTER METHODS START

	int getScore() {
		return this.score;
	}

	String getType() {
		if (this.type == PlayerType.HUMAN) {
			return PlayerType.HUMAN.toString().toLowerCase();
		} else if (this.type == PlayerType.AI) {
			return PlayerType.AI.toString().toLowerCase();
		}
		return null;
	}

	String getName() {
		return this.name;
	}

	// GETTER METHODS END

	void updateScore(int points) {
		this.score += points;
	}

}
