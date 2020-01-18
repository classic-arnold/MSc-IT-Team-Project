package toptrumps;

import interfaces.DataGameInterface;

class DataGame implements DataGameInterface{
	private static DataGame instance = null; // initialize again during game reset

	private DataGame() {

	}

	public static DataGame getInstance(){
		if (instance == null) { 
			instance = new DataGame(); 
		} 
		return instance; 
	}
}
