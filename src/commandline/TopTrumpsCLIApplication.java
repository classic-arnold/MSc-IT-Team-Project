package commandline;

import main.model.*;

import commandline.view.*;
import commandline.controller.*;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// The Main method that call MVC, to implement game logic

			DataGame model = new DataGame(4); // model

			ViewCLI view = new ViewCLI(model); // initiailize view

			Controller controller = new Controller(model, view, writeGameLogsToFile); // initialize controller

			int userChoice = controller.startGame(); // start game, if return 1, then user wants to quit else, nothing

			if (userChoice == 1) {
				userWantsToQuit=true; // use this when the user wants to exit the game
			}

		}

	}

}
