package main.java.exceptions;

public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException() {
		super("You picked an invalid category. Please check again!");
	}
}
