package toptrumps;

// some Liu MoLin help.
import java.util.Scanner;

public class ViewCLI {
    private int startChoice;
    private int categoryChoice;

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
    public void displayRound(int num) {
        if (num == 1) {
            System.out.println("Game Start");
        }
        System.out.println("Round " + num);
        System.out.println("Round " + num + " : Players have drawn their cards");

    }

    public void displayPlayerCard(DataCard dataCard) {
        System.out.println("You drew '" + dataCard.getDescription() + "':");
        System.out.println("> size:" + dataCard.getSize());
        System.out.println("> Speed:" + dataCard.getSpeed());
        System.out.println("> Range:" + dataCard.getRange());
        System.out.println("> Firepower:" + dataCard.getFirePower());
        System.out.println("> Cargo:" + dataCard.getCargo());

    }

    //display number of cards in deck
    public void displayNumDeckCards(int num) {
        System.out.println("There are " + num + " cards in your deck");
    }

    //display category selection menu
    public void displayCategorySelection() {
        System.out.println("It is your turn to select a category, the categories are: ");
        System.out.println("1: size");
        System.out.println("2: Speed");
        System.out.println("3: Range");
        System.out.println("4: Firepower");
        System.out.println("5: Cargo");
        System.out.print("Enter the number for your attribute: ");
        Scanner categoryIn = new Scanner(System.in);
        categoryChoice = categoryIn.nextInt();
    }

    public int getCategoryChoice() {
        return categoryChoice;
    }

    
    public void displayRoundResult(DataPlayer player, int round, DataCard dataCard, int categoryChoice, int commonCards) {
        if(commonCards > 0){
            System.out.println("Round " + round + ": This round was a Draw, common pile now has " + commonCards + " cards");
        }else {
            System.out.println("Round "+round+": Player "+player.getName()+" won this round");
        }
        System.out.println("The winning card was '"+dataCard.getDescription()+"':");
        for(int i = 0; i < dataCard.getPropertiesName().length; i++){
            String point = "";
            if(i == categoryChoice-1){
                point = " <--";
            }
            System.out.println("> "+dataCard.getPropertiesName()[i]+": "+dataCard.getPropertiesVal()[i]+point);
        }
    }

    //display game statistics
    public void displayStats(DataGame model) {
        System.out.println("\nGame Statistics:");
        System.out.println("Number of Games: "+ model.getNumberOfGames());
        System.out.println("Number of Human Wins: "+model.getNumberOfHumanWins());
        System.out.println("Number of AI Wins: " + model.getNumberOfAIWins());
        System.out.println("Average number of Draws: " + model.getAvgNumberOfDraws());
        System.out.println("Longest Game: " + model.getLongestGame());
        System.out.println();
    }
}
