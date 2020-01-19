package toptrumps;

import java.util.Scanner;

/*
 * ViewCLI- creates the commandline view for the TopTrumps application
 *  @author Team TRY-CATCH - Anne-Marie Gill 2431989G
 */
//THE METHOD CURRENTLY HAS PLACEHOLDER VALUES UNTIL IT CAN BE SYNCED WITH THE MODEL AND CONTROLLER.
//IT ALSO HAS A TEST MAIN SO THAT DEVS CAN SEE HOW IT PRINTS OUT
public class ViewCLI {
    //class attributes
    private int startChoice;
    private int categoryChoice;
    //test attributes
    int round = 1;
   
 


    //view constructor
    public ViewCLI() {

    }

    //getter for choice
    public int getStartChoice() {
        return startChoice;
    }

    //display initial options for user
    public void chooseDisplay() {
        System.out.println("Do you want to see past results or play a game");
        System.out.println("1:Print Game Statistics");
        System.out.println("2:Play game");
        System.out.print("Enter the number for your selection:");
        Scanner choiceIn = new Scanner(System.in);
        startChoice = choiceIn.nextInt();
    }

    //display round
    private void displayRound() {
        if (round == 1) {
            System.out.println("Game Start");
        }
        System.out.println("Round " + round);
        System.out.println("Round " + round + " : Players have drawn their cards");


    }
    //display card
    private void displayPlayerCard(){
        System.out.println("You drew 'Ornithomimus':");
        System.out.println("> height:10");
        System.out.println("> height:10");
        System.out.println("> height:10");
        System.out.println("> height:10");
        System.out.println("> height:10");

    }
    //display number of cards in deck
    private void displayNumDeckCards(){
        System.out.println("There are 7 cards in your deck");
    }
    //display category selection menu
    private void displayCategorySelection() {
        System.out.println("It is your turn to select a category, the categories are: ");
        System.out.println("1: height");
        System.out.println("1: height");
        System.out.println("1: height");
        System.out.println("1: height");
        System.out.println("1: height");
        System.out.print("Enter the number for your attribute: ");
        Scanner categoryIn = new Scanner(System.in);
        categoryChoice = categoryIn.nextInt();
    }



    //update view function
    public void updateView(){
        displayRound();
        displayPlayerCard();
        displayNumDeckCards();
        displayCategorySelection();

    }
    //display round result function
    public void displayRoundResult() {
    	System.out.println("");
        System.out.println("Round " + round + " Round result message here");
        System.out.println("If there was a winning card, details would go here.");

    }
    //display game end and stats
    public void gameEnd() {
    	System.out.println("Game End");
    	System.out.println("");
    	System.out.println("The overall winner was AI Player 1");
    	System.out.println("AI Player 1: 20");
    	System.out.println("AI Player 2: 30");
    	System.out.println("AI Player 4: 40");
    	System.out.println("You:50 ");
    	System.out.println("AI Player 3:60");
    	
    }

    //test main
    public static void main(String[] args) {
        ViewCLI view = new ViewCLI();
        view.chooseDisplay();
        view.updateView();
        view.displayRoundResult();
        view.gameEnd();
    }
}
