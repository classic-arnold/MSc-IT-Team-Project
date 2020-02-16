package toptrumps;

import java.util.HashMap;

public class DataListOfGames {
	
	private HashMap<Integer, DataGame> games = new HashMap<Integer, DataGame>();
	
	private int currentID = 10;
	
	public DataListOfGames() {
		
	}
	
	public DataGame get(int id) {
		DataGame game;
		
		game = this.games.get(id);
		
		return game;
	}
	
	public int add(DataGame game) {
		this.currentID+=1;
		this.games.put(this.currentID, game);
		return this.currentID;
	}
}
