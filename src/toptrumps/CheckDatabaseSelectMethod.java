package toptrumps;

public class CheckDatabaseSelectMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProgramDatabase postgre=new ProgramDatabase();
		System.out.println("Game Count: "+postgre.getGameCount()
		+"\nHuman Player Wons: "+postgre.getHumanWon()
		+"\nAI Player Wons: "+postgre.getAIWon()
		+"\nAverage Draws: "+postgre.getDraws()
		+"\nBiggest Round: "+postgre.getLargestRound());
	}

}
