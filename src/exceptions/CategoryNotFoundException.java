package exceptions;

/**
 * Exception thrown if a method is called with an invalid category
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U
 */
public class CategoryNotFoundException extends RuntimeException {
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = -5999181924039771263L;

	public CategoryNotFoundException() {
		super("You picked an invalid category. Please check again!");
	}
}
