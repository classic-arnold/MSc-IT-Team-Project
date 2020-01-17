package toptrumps;

class DataGame {
	private static DataGame instance = new DataGame(); // initialize again during game reset
	
	private DataGame() {
		
	}
	
	public static DataGame getInstance(){
      return DataGame.instance;
   }
}
