package toptrumps;

public class CheckDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProgramDatabase postgre=new ProgramDatabase();
		
		//check selection. 
		//What about changing selectGameStats method returns 5 values in array so that model could call it?
		//Currently, model needs to call every single values by using getters like below:
		System.out.println("Number of games played overall: "+postgre.getGameCount()
		+"\nHow many times the computer has won: "+postgre.getAIWon()
		+"\nHow many times the human has won: "+postgre.getHumanWon()
		+"\nThe average number of draws: "+postgre.getDraws()
		+"\nThe largest number of rounds played in a single game: "+postgre.getLargestRound());
		
		
	}

}
