package toptrumps;

import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * ViewCLI- creates the commandline view for the TopTrumps application
 * 
 */

public class ViewCLI {

	// class attributes

	private DataGame model;

	 /**
     *view constructor sets the model
     */
	public ViewCLI(DataGame model) {
		this.model = model;
	}

	/**
	 * Displays initial options for the user.
	 */
	public int chooseDisplay() {
		String getInput = null;
		int startChoice = 1;
		boolean redo = false;
		while (!NumberUtils.isParsable(getInput) || startChoice < 1 || startChoice > 3) {
			// displays invalid input method if the user is trying to input again
			if (redo) {
				System.out.println("\n Invalid input.Please input 1,2 or 3");
			}
			System.out.println("Do you want to see past results,play a game or quit the program?");
			System.out.println("1:Print Game Statistics");
			System.out.println("2:Play game");
			System.out.println("3:Quit program");
			System.out.print("Enter the number for your selection:");
			Scanner choiceIn = new Scanner(System.in);
			getInput = choiceIn.nextLine();
			redo = true;
			if (NumberUtils.isParsable(getInput)) {
				startChoice = Integer.parseInt(getInput);
			}
		}

		return startChoice;
	}

	/**
	 * Displays round number
	 */
	private void displayRound() {
		if (model.getRoundNumber() == 1) {
			System.out.println("\n/*****GAME START*****/ \n");
		}
		System.out.println("Round " + model.getRoundNumber()+"\n");
	}
	
	private void displayRoundMessageAndActivePlayer() {
		System.out.println("\nRound " + model.getRoundNumber() + " : Players have drawn their cards");
        System.out.println("The active player is "+ model.getCategoryChooser().getName() + "\n");
	}

	/**
	 * Displays human player card
	 */
	private void displayPlayerCard() {
	
		if (model.getHumanPlayer().getDeck().size() > 0) {
			System.out.println("You drew " + model.getHumanPlayer().getDeck().get(0).getDescription() + " :");
			System.out.println(model.getHumanPlayer().getDeck().get(0));
		}
	}

	/**
	 * Displays number of cards in deck
	 */
	private void displayNumDeckCards() {

		for (int i = 0; i < model.getActivePlayers().length; i++) {
			if (model.getActivePlayers()[i].getTypeAsString().equals("human")) {
				System.out.println(model.getActivePlayers()[i].getName() + " have "
						+ model.getActivePlayers()[i].getNumberOfCardsInDeck() + " cards in your deck.");
			}
			if (model.getActivePlayers()[i].getTypeAsString().equals("ai")) {
				System.out.println(model.getActivePlayers()[i].getName() + " has "
						+ model.getActivePlayers()[i].getNumberOfCardsInDeck() + " cards.");
			}

		}
	}

	/**
	 * Displays category selection menu
	 */
	public int displayCategorySelection() {
		String getInput = null;
		int categoryChoice = 0;
		boolean redo = false;
		while (!NumberUtils.isParsable(getInput) || categoryChoice < 0 || categoryChoice > 5) {
			if (redo) {
				System.out.println("\n Invalid input.Please input a number between 1 and 5");
			}
			System.out.println(" \n It is your turn to select a category, the categories are: ");
			System.out.println("1: " + DataGame.CATEGORYNAMES[0]);
			System.out.println("2: " + DataGame.CATEGORYNAMES[1]);
			System.out.println("3: " + DataGame.CATEGORYNAMES[2]);
			System.out.println("4: " + DataGame.CATEGORYNAMES[3]);
			;
			System.out.println("5: " + DataGame.CATEGORYNAMES[4]);
			System.out.print("Enter the number for your attribute: ");
			Scanner categoryIn = new Scanner(System.in);
			getInput = categoryIn.nextLine();
			redo = true;
			if (NumberUtils.isParsable(getInput)) {
				categoryChoice = Integer.parseInt(getInput);
			}
		}

		return categoryChoice;
	}

	/**
	 * Method to update the view
	 */
	public void updateView() {
		displayRound();
		displayNumDeckCards();
		displayRoundMessageAndActivePlayer();
		displayPlayerCard();
		

	}

	/**
	 * Displays to user the option to move to next round or quit
	 */
	public String nextRoundChoice() {
		String nextRoundAction = "initial";
		boolean redo = false;
		while (!nextRoundAction.equals("") && !nextRoundAction.equals("q")) {
			if (redo) {
				System.out.println("Invalid Input \n");
			}
			System.out.println("Press enter to move to next round or press q to quit");
			Scanner nextRoundChoiceIn = new Scanner(System.in);
			nextRoundAction = nextRoundChoiceIn.nextLine();
			System.out.println(nextRoundAction);
			redo = true;
		}

		return nextRoundAction;
	}

	/**
	 * Displays the result of the round
	 */
	public void displayRoundResult(String category) {
		System.out.println("");
		if (!model.getRoundWasDraw()) {

			System.out.println("Round " + model.getRoundNumber() + " " + model.getRoundWinningPlayers().get(0).getName()
					+ " won this round.");
			System.out.println(model.getRoundWinningCardToString(category));
		}
		if (model.getRoundWasDraw()) {
			System.out.println("This round was a draw.There are now " + model.getNumberOfCardsInCommonPile()
					+ " cards in the common pile");
		}
	}

	/**
	 * Displays game winner and scores
	 */
	public void gameEnd() {
		System.out.println("Game End");
		System.out.println(model.getGameWinner().getName() + "won the game \n");
		System.out.println("Player scores");
		for (int i = 0; i < model.getAllPlayers().length; i++) {
			System.out.println(model.getAllPlayers()[i].getName() + " :" + model.getAllPlayers()[i].getScore());
		}
		System.out.println(" ");

	}

	/**
	 * Displays game statistics
	 */
	public void displayStats() {
		System.out.println("\nGame Statistics:");
		System.out.println("Number of Games : " + model.getNumberOfGames());
		System.out.println("Number of Human Wins:" + model.getNumberOfHumanWins());
		System.out.println("Number of AI Wins : " + model.getNumberOfAIWins());
		System.out.println("Average number of Draws : " + model.getAvgNumberOfDraws());
		System.out.println("Longest Game : " + model.getLongestGame());
	}

	public static void main(String[] args) {
		DataGame model = DataGame.resetAndGetInstance(4);
		model.startGame();
		ViewCLI testview = new ViewCLI(model);
		testview.nextRoundChoice();

	}

}
