package toptrumps;

import java.util.Random;

import toptrumps.DataGame;

/**
 * The controller update the Model and View according to user input
 * 
 * @author Team TRY-CATCH - Jialiang Song 2410536s
 */
public class Controller {
	private DataGame dataGame;
	private ViewCLI viewCli;
	private boolean writeGameLogsToFile;
	private TestLog testLog;

	public Controller(DataGame dataGame, ViewCLI viewCli, boolean writeGameLogsToFile) {
		this.dataGame = dataGame;
		this.viewCli = viewCli;
		this.writeGameLogsToFile = writeGameLogsToFile;

		testLog = new TestLog (dataGame); // refactor this
	}

	public int startGame() {
		int startChoice = this.viewCli.chooseDisplay();

		if(startChoice == 1) {	
			this.viewCli.displayStats();
		} else if(startChoice == 2) {

			String continueOrEndGameChoice = null;

			this.dataGame.startGame();
			
//			TestLog print
			if(this.writeGameLogsToFile) {
				this.testLog.writeDeckContents();
				this.testLog.writeShuffledDeckContents();
			}

			while(this.dataGame.getGameState()==DataGame.GameState.RUNNING) {
				this.viewCli.updateView();
				int category;


				if(this.dataGame.getRoundNumber() == 1) {
					category = this.dataGame.getBestCategoryForCurrentAIPlayers(this.dataGame.getFirstPlayer());
					if(category == 0) {
						category = this.viewCli.displayCategorySelection();
					}
				} else if(this.dataGame.shouldHumanChooseCategory()) {
					category = this.viewCli.displayCategorySelection();
					// category = 2;
				} else {
					category = this.dataGame.getBestCategoryForCurrentAIPlayers();
				}

				// int category = this.viewCli.displayCategorySelection();

				this.dataGame.playRound(DataGame.CATEGORYNAMES[category-1]);
				
//				TestLog print
				if(this.writeGameLogsToFile) {
					this.testLog.writePlayerDecks();
					this.testLog.writeCardsInPlay();
					this.testLog.writeCommunalPile();
					this.testLog.writeCategorySelected();
					
				}

				this.viewCli.displayRoundResult(DataGame.CATEGORYNAMES[category-1]);

				continueOrEndGameChoice = this.viewCli.nextRoundChoice();

//				System.out.print(continueOrEndGameChoice);

				if (continueOrEndGameChoice.contentEquals("q")) {
					break;
				}

				this.dataGame.incrementRound();

			}

			if(continueOrEndGameChoice.contentEquals("")) {
				this.viewCli.gameEnd();
			} else if (continueOrEndGameChoice.contentEquals("q")) {
				//	this.viewCli.gameEndByUser();
			}

		} else {
			return 1;
		}

		return 0;
	}
	
	public void startGameForTests() {
		int startChoice = 2;

		if(startChoice == 1) {
			this.viewCli.displayStats();
		} else if(startChoice == 2) {

			this.dataGame.startGame();

			while(this.dataGame.getGameState()==DataGame.GameState.RUNNING) {
				this.viewCli.updateView();
				int category = 2;

				// int category = this.viewCli.displayCategorySelection();

				this.dataGame.playRound(DataGame.CATEGORYNAMES[category-1]);

				this.viewCli.displayRoundResult(DataGame.CATEGORYNAMES[category-1]);
				
//				this.testLog.printSomething;

				this.dataGame.incrementRound();

			}

			this.viewCli.gameEnd();

		}

	}

	public int getRandomCategory() {
		Random random = new Random();

		int randomNumber = random.nextInt(DataGame.CATEGORYNAMES.length) + 1;

		return randomNumber;
	}

}


