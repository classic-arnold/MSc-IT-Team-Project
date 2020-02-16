package commandline.controller;

import main.model.*;

import commandline.view.*;

/**
 * The controller update the Model and View according to user input
 * @author Team TRY-CATCH - Jialiang Song 2410536s
 */
public class Controller {
	/**
	 * represents the game engine (model)
	 */
	private DataGame dataGame;

	/**
	 * represents the view
	 */
	private ViewCLI viewCli;

	/**
	 * represents if test log should be updated
	 */
	private boolean writeGameLogsToFile;

	/**
	 * represents test log (part of view)
	 */
	private TestLog testLog;

	/**
	 * creates game controller
	 * @param dataGame represents the game engine (model)
	 * @param viewCli represents the view
	 * @param writeGameLogsToFile represents if test log should be updated
	 */
	public Controller(DataGame dataGame, ViewCLI viewCli, boolean writeGameLogsToFile) {
		this.dataGame = dataGame;
		this.viewCli = viewCli;
		this.writeGameLogsToFile = writeGameLogsToFile;
	}

	/**
	 * starts the game loop
	 * @return
	 */
	public int startGame() {
		// ask player to choose between playing a game, or viewing statistics
		int startChoice = this.viewCli.chooseDisplay();

		// if player chose 1, show them statistics
		if(startChoice == 1) {	
			this.viewCli.displayStats();
		} else if(startChoice == 2) { // if player chose 2, start the game

			// represents players choice to continue to next round, or quit game. 
			// Enter for continue, and q for quit
			String continueOrEndGameChoice = null;

			// start the game
			this.dataGame.startGame();

			// if flag was added at the beginning, print test log strings
			if(this.writeGameLogsToFile) {
				testLog = new TestLog (dataGame);
				this.testLog.writeDeckContents();
				this.testLog.writeShuffledDeckContents();
				this.testLog.writePlayerDecksInitial();
			}

			// while game is running, keep playing rounds
			while(this.dataGame.getGameState()==DataGame.GameState.RUNNING) {

				int category; // represents the category as integers mapped to array DataGame.CATERGORYNAMES

				// represents the active player. Player to choose the category
				DataPlayer activePlayer = this.dataGame.getCategoryChooser();

				// print strings from view
				this.viewCli.updateView();

				// if active player is an AI player, get their category automatically
				if(this.dataGame.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
					category = this.dataGame.getBestCategoryForPlayer(activePlayer);
				} else { // else, ask player to select category
					category = this.viewCli.displayCategorySelection();
				}

				// if flag was added at the beginning, print test log strings
				if(this.writeGameLogsToFile) {
					this.testLog.writeRoundNumber();
					this.testLog.writePlayerDecks();
					this.testLog.writeNumCardsInDeck();
					this.testLog.writeCardsInPlay();
					this.testLog.writeActivePlayer();
				}

				// play the round
				this.dataGame.playRound(DataGame.CATEGORYNAMES[category-1]);

				// print test log strings
				if(this.writeGameLogsToFile) {
					this.testLog.writeCategorySelected();
					this.testLog.writeRoundWinningCard();
					this.testLog.writeRoundWinner();
					this.testLog.writeCommunalPile();
				}

				// display the round results
				this.viewCli.displayRoundResult(DataGame.CATEGORYNAMES[category-1]);

				// ask player if he/she would like to continue or exit game
				continueOrEndGameChoice = this.viewCli.nextRoundChoice();

				// if player enters q, break the loop and exit game
				if (continueOrEndGameChoice.contentEquals("q")) {
					break;
				}

				// increment round
				this.dataGame.getRound().incrementRound();

			}

			// if above loop finished and player didn't ask to quit, end game properly
			if(continueOrEndGameChoice.contentEquals("")) {
				this.viewCli.gameEnd();

				if(this.writeGameLogsToFile) {
					this.testLog.writeGameWinner();
				}
			}
		} else { // if start choice was not 1(view stats) or 2(play game), return 1 to represent that the game was ended by user
			return 1;
		}

		// return 0 to represent game ended naturally
		return 0;
	}

}


