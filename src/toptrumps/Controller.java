package toptrumps;

import java.util.Random;

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
            		
//            		int category = this.viewCli.displayCategorySelection();
            		
            		this.dataGame.playRound(DataGame.CATEGORYNAMES[category-1]);
            		
            		this.viewCli.displayRoundResult();
            		
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


