package exceptions;

/**
 * Exception thrown if database connection fails
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U
 */
public class CannotConnectToDataBaseException extends RuntimeException {
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = -7112161920564010483L;

	public CannotConnectToDataBaseException() {
		super("Database connection failed. Please check again!");
	}
}
