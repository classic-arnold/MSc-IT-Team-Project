package main.model;

import java.util.HashMap;

/**
 * Represents a list of games with ID
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U
 *
 */
public class DataListOfGames {

	/**
	 * represents the list of games
	 */
	private HashMap<Integer, DataGame> games = new HashMap<Integer, DataGame>();

	/**
	 * id of games. Starts from 10 to avoid clashes
	 */
	private int currentID = 10;

	/**
	 * get a game with an ID
	 * @param id representing the id of the game
	 * @return the game object
	 */
	public DataGame get(int id) {
		DataGame game;

		game = this.games.get(id);

		return game;
	}

	/**
	 * add a game
	 * @param game object to add to list
	 * @return int representing the game id
	 */
	public int add(DataGame game) {
		this.currentID+=1;
		this.games.put(this.currentID, game);
		return this.currentID;
	}
}
