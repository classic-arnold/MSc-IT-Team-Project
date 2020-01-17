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
	
	/** DataCard array representing the deck of cards */
	private DataCard[] cardDeck = new DataCard[7];
	
	/** PlayerType enum representing the type of player - human or AI */
	private PlayerType type;
	
	
}
