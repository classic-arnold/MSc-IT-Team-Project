package toptrumps;

import java.util.Scanner;

/**
 * ViewCLI- creates the commandline view for the TopTrumps application
 *  @author Team TRY-CATCH - Anne-Marie Gill 2431989G
 */
//THE METHOD CURRENTLY HAS PLACEHOLDER VALUES UNTIL IT CAN BE SYNCED WITH THE MODEL AND CONTROLLER.
//IT ALSO HAS A TEST MAIN SO THAT DEVS CAN SEE HOW IT PRINTS OUT
public class ViewCLI {
    //class attributes
    
    private DataGame model;

    //view constructor
    public ViewCLI(DataGame model) {
         this.model=model;
    }

   
  
    
   

    //display initial options for user
    public int chooseDisplay() {
        System.out.println("Do you want to see past results or play a game");
        System.out.println("1:Print Game Statistics");
        System.out.println("2:Play game");
        System.out.print("Enter the number for your selection:");
        Scanner choiceIn = new Scanner(System.in);
        int startChoice = choiceIn.nextInt();
       
        return startChoice;
    }

    //display round
    private void displayRound() {
        if (model.getRoundNumber() == 1) {
            System.out.println("Game Start");
        }
        System.out.println("Round " + model.getRoundNumber());
        System.out.println("Round " + model.getRoundNumber() + " : Players have drawn their cards");


    }
    //display card
    private void displayPlayerCard(){
    	for(int i=0;i<model.getAllPlayers().length;i++) {
    		if(model.getAllPlayers()[i].getTypeAsString().equals("human")) {
    			System.out.println("You drew "+model.getActivePlayers()[i].getDeck().get(0).getDescription()+" :");
    			System.out.println(model.getActivePlayers()[i].getDeck().get(0));
    		}
    	}
    

    }
    //display number of cards in deck
    private void displayNumDeckCards(){
    	for(int i=0;i<model.getAllPlayers().length;i++) {
    		if(model.getAllPlayers()[i].getTypeAsString().equals("human")) {
    			System.out.println("You have "+model.getAllPlayers()[i].getNumberOfCardsInDeck()+" cards in your deck.");
    		}
			
		}
    }
    //display category selection menu
    public int displayCategorySelection() {
        System.out.println("It is your turn to select a category, the categories are: ");
        System.out.println("1: " + DataGame.CATEGORYNAMES[0]);
        System.out.println("2: " + DataGame.CATEGORYNAMES[1]);
        System.out.println("3: " + DataGame.CATEGORYNAMES[2]);
        System.out.println("4: " + DataGame.CATEGORYNAMES[3]);;
        System.out.println("5: " + DataGame.CATEGORYNAMES[4]);
        System.out.print("Enter the number for your attribute: ");
        Scanner categoryIn = new Scanner(System.in);
        int categoryChoice = categoryIn.nextInt();
        categoryIn.nextLine();
        return categoryChoice;
    }



    //update view function
    public void updateView(){
        displayRound();
        displayPlayerCard();
        displayNumDeckCards();
        

    }
    
    
    //display round result function
    public void displayRoundResult() {
    	System.out.println("");
    	if(!model.getRoundWasDraw()) {
			System.out.println("Round " + model.getRoundNumber()+" " +model.getRoundWinningPlayers().get(0).getName() +" won this round.");
	        System.out.println(model.getRoundWinningCard());
    	}
        if(model.getRoundWasDraw()) {
        	System.out.println("This round was a draw.There are now "+model.getNumberOfCardsInCommonPile()+"cards in the common pile");
        }
       

    }
    //display game end and stats
    public void gameEnd() {
    	System.out.println("Game End");
    	System.out.println(model.getGameWinner().getName()+" won the game");
    	System.out.println("The overall winner was");
    	for(int i=0;i<model.getAllPlayers().length;i++) {
    		System.out.println(model.getAllPlayers()[i].getName()+" :"+model.getAllPlayers()[i].getScore());
    	}
    
    	
    }
    
    //display game statistics
    public void displayStats() {
    	System.out.println("\nGame Statistics:");
    	System.out.println("Number of Games"+ model.getNumberOfGames());
    	System.out.println("Number of Human Wins:"+model.getNumberOfHumanWins());
    	System.out.println("Number of AI Wins" + model.getNumberOfHumanWins());
    	System.out.println("Average number of Draws" + model.getAvgNumberOfDraws());
    	System.out.println("Longest Game" + model.getLongestGame());
    }

  
}
