package toptrumps;

class DataGame {
	private static DataGame instance = new DataGame();
	
	private DataGame() {
		
	}
	
	public static DataGame getInstance(){
      return DataGame.instance;
   }
}
