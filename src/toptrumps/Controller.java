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
	private TestLog testlog;

	public Controller(DataGame dataGame, ViewCLI viewCli, boolean writeGameLogsToFile) {
		this.dataGame = dataGame;
		this.viewCli = viewCli;
		this.writeGameLogsToFile = writeGameLogsToFile;
		
		testlog = new TestLog (dataGame);
	}

	public int startGame() {
		int startChoice = this.viewCli.chooseDisplay();

		if(startChoice == 1) {	
			this.viewCli.displayStats();
		} else if(startChoice == 2) {

			String continueOrEndGameChoice = null;

			this.dataGame.startGame();

			while(this.dataGame.getGameState()==DataGame.GameState.RUNNING) {
				this.viewCli.updateView();
				int category;


				if(this.dataGame.getRoundNumber() == 1) {
					category = this.getRandomCategory();
				} else if(this.dataGame.shouldHumanChooseCategory()) {
					category = this.viewCli.displayCategorySelection();
					//            			category = 2;
				} else {
					category = this.getRandomCategory();
				}

				// int category = this.viewCli.displayCategorySelection();

				this.dataGame.playRound(DataGame.CATEGORYNAMES[category-1]);

				this.viewCli.displayRoundResult(DataGame.CATEGORYNAMES[category-1]);

				//				this.testLog.printSomething;

				continueOrEndGameChoice = this.viewCli.nextRoundChoice();

				System.out.print(continueOrEndGameChoice);

				if(continueOrEndGameChoice.contentEquals("")) {

				} else if (continueOrEndGameChoice.contentEquals("q")) {
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

	public int getRandomCategory() {
		Random random = new Random();

		int randomNumber = random.nextInt(DataGame.CATEGORYNAMES.length) + 1;

		return randomNumber;
	}

}


