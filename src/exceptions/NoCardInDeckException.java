package exceptions;

public class NoCardInDeckException extends RuntimeException {
	public NoCardInDeckException() {
		super("There is no card in the deck you are trying to operate on. Please check again!");
	}
}
