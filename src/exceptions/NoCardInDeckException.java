package exceptions;

/**
 * Exception thrown if a method and theres no card in deck
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U
 */
public class NoCardInDeckException extends RuntimeException {
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 7072158662776465952L;

	public NoCardInDeckException() {
		super("There is no card in the deck you are trying to operate on. Please check again!");
	}
}
