package toptrumps;

import toptrumps.DataGame;

/**
 * The controller update the Model and View according to user input
 */
public class Controller {
    private DataGame dataGame;
    private ViewCLI viewCli;

    public Controller(DataGame dataGame, ViewCLI viewCli) {
        this.dataGame = dataGame;
        this.viewCli = viewCli;
    }

    public void startGame() {
    	int startChoice = this.viewCli.chooseDisplay();
    	
    	if(startChoice == 1) {	
    		this.viewCli.displayStats();
    	} else if(startChoice == 2) {
    		
    			this.dataGame.startGame();
    			
    			while(this.dataGame.getGameState()==DataGame.GameState.RUNNING) {
            		int category = this.viewCli.updateView();
            		
            		this.dataGame.playRound(DataGame.CATEGORYNAMES[category-1]);
            		
            		this.viewCli.displayRoundResult();
            		
        		}
        		
        		this.viewCli.gameEnd();
    		
    	}
    	
    }

}


