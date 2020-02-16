package exceptions;

/**
 * Exception thrown if a method is called and player is not an AI
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U
 */
public class PlayerIsNotAIException extends RuntimeException {
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = -2041330877874911154L;

	public PlayerIsNotAIException() {
		super("You cannot do this operation with a non-AI player. Please check again!");
	}
}
