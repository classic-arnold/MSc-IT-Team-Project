package main.java.exceptions;

public class CannotConnectToDataBaseException extends RuntimeException {
	public CannotConnectToDataBaseException() {
		super("Database connection failed. Please check again!");
	}
}
