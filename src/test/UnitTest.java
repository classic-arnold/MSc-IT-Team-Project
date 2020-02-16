package test;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.model.*;
import commandline.view.*;
import commandline.controller.*;

/**
 * Game unit tests
 * @author Team Try Catch - Chika Elumeziem - 2500799e
 */
public class UnitTest {
	DataGame model;
	ViewCLI view;
	Controller controller;

	// Initialise instances of the game's model, view and controller objects before running each test
	@Before
	public void setUp() {
		model = new DataGame(4);
		view = new ViewCLI(model);
		controller = new Controller(model, view, true);
	}

	@Test
	public void testBestCategoryForPlayer() {
		// test best category is selected for active players
		DataPlayer player = new DataPlayer(DataPlayer.PlayerType.HUMAN, 0);

		player.addCardToDeck(DataCardCache.getAllCardsInOrder()[0]);

		assertEquals(2, this.model.getBestCategoryForPlayer(player));
	}

	@Test
	public void testCardShuffle() {
		// test card is shuffled properly
		this.model.startGame();
		assert(
				!this.model.getInitialShuffledDeck()[0].equals(this.model.getInitialUnshuffledDeck()[0]) ||
				!this.model.getInitialShuffledDeck()[1].equals(this.model.getInitialUnshuffledDeck()[1]) ||
				!this.model.getInitialShuffledDeck()[2].equals(this.model.getInitialUnshuffledDeck()[2]) ||
				!this.model.getInitialShuffledDeck()[3].equals(this.model.getInitialUnshuffledDeck()[3]) 
				);
	}

	@Test
	public void testGetHumanPlayer() {
		// test method gets correct human player
		assertTrue(this.model.getHumanPlayer().getType()==DataPlayer.PlayerType.HUMAN);
	}

	@Test
	public void testListToArrayAndArrayToList() {
		// test array to list and list to array method works properly
		this.model.startGame();
		ArrayList<DataPlayer> playersList = new ArrayList<DataPlayer>();
		playersList.add(this.model.getAllPlayers()[0]);
		ArrayList<DataCard> cardsList = new ArrayList<DataCard>();
		cardsList.add(this.model.getInitialUnshuffledDeck()[0]);

		DataPlayer[] playersArr;
		DataCard[] cardsArr;

		playersArr = DataGame.arrayListToArrayPlayer(playersList);
		cardsArr = DataGame.arrayListToArrayCard(cardsList);

		assertEquals(playersList.get(0), playersArr[0]);
		assertEquals(cardsList.get(0), cardsArr[0]);

		ArrayList<DataPlayer> playersList2 = new ArrayList<DataPlayer>();
		playersList2 = DataGame.arrayToArrayList(playersArr);
		ArrayList<DataCard> cardsList2 = new ArrayList<DataCard>();
		cardsList2 = DataGame.arrayToArrayList(cardsArr);

		assertEquals(playersList2.get(0), playersArr[0]);
		assertEquals(cardsList2.get(0), cardsArr[0]);
	}

	@Test
	public void testTopCategory() {
		// test card top category is returned
		DataCard card = new DataCard("Test", 1,1,1,1,5);
		DataCard card2 = new DataCard("Test", 1,1,7,1,1);
		DataCard card3 = new DataCard("Test", 1,4,4,1,1);

		assertEquals(5, card.findTopCategory());
		assertEquals(3, card2.findTopCategory());
		assertTrue(card3.findTopCategory()==2 || card3.findTopCategory()==3);
	}

}
