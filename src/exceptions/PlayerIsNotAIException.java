package exceptions;

public class PlayerIsNotAIException extends RuntimeException {
	public PlayerIsNotAIException() {
		super("You cannot do this operation with a non-AI player. Please check again!");
	}
}
