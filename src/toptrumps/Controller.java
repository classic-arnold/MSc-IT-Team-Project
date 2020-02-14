package toptrumps;

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
				testLog = new TestLog (dataGame); // refactor this
				this.testLog.writeDeckContents();
				this.testLog.writeShuffledDeckContents();
				this.testLog.writePlayerDecks();
				this.testLog.writeCardsInPlay();
			}

			while(this.dataGame.getGameState()==DataGame.GameState.RUNNING) {
				
				int category;


//				if(this.dataGame.getRoundNumber() == 1) {
//					category = this.dataGame.getBestCategoryForCurrentAIPlayers();
//					if(category == 0) {
//						category = this.viewCli.displayCategorySelection();
//					}
//				} else if(this.dataGame.shouldHumanChooseCategory()) {
//					category = this.viewCli.displayCategorySelection();
//					// category = 2;
//				} else {
//					category = this.dataGame.getBestCategoryForCurrentAIPlayers();
//				}
				
				DataPlayer activePlayer = this.dataGame.getCategoryChooser();
				this.viewCli.updateView();
				if(this.dataGame.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
					category = this.dataGame.getBestCategoryForPlayer(activePlayer);
				} else {
					category = this.viewCli.displayCategorySelection();
				}

				// int category = this.viewCli.displayCategorySelection();

				this.dataGame.playRound(DataGame.CATEGORYNAMES[category-1]);
				
//				TestLog print
				if(this.writeGameLogsToFile) {
					
					this.testLog.writePlayerDecks();
					this.testLog.writeCardsInPlay();
					this.testLog.writeCategorySelected();
					this.testLog.writeCommunalPile();
					
					
				}

				this.viewCli.displayRoundResult(DataGame.CATEGORYNAMES[category-1]);

				continueOrEndGameChoice = this.viewCli.nextRoundChoice();

//				System.out.print(continueOrEndGameChoice);

				if (continueOrEndGameChoice.contentEquals("q")) {
					break;
				}

				this.dataGame.getRound().incrementRound();

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

}


