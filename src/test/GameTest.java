package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import main.model.*;
import main.view.*;
import main.controller.*;
import main.database.*;

/**
 * Game acceptance tests
 * @author Team Try Catch - Chika Elumeziem - 2500799e
 */
public class GameTest{

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
	public void testAccurateRoundNumber() {

		//check that the correct round number is displayed after after several rounds are played

		int round = 1; //variable created by me to manually compare it with what the game outputs

		this.model.startGame();

		while(this.model.getGameState()==DataGame.GameState.RUNNING) {
			int category;

			DataPlayer activePlayer = this.model.getCategoryChooser();
			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
				category = this.model.getBestCategoryForPlayer(activePlayer);
			} else {
				category = 2;
			}

			this.model.playRound(DataGame.CATEGORYNAMES[category-1]);

			this.model.getRound().incrementRound();

			if(this.model.getGameState()==DataGame.GameState.RUNNING) {
				round++; //the round is manually incremented so that it can be compared with game round
			}

		}

		assertEquals(round, this.model.getRound().getRoundNumber());
	}

	@Test
	public void testCorrectTopCard() {
		//getActivePlayer in dataDame, getDeck in dataplayer to get the player's deck, then get the zeroth element class 
		//model.getInitialShuffledDeck() does the shuffling
		//check that the correct top card was returned for several rounds

		this.model.startGame();

		DataPlayer[] players = this.model.getActivePlayers();

		ArrayList<DataCard> playerCards = new ArrayList<DataCard>();

		for(int i=0;i<players.length;i++) {
			playerCards.add(players[i].getDeck().get(0));
		}

		this.model.playRound(DataGame.CATEGORYNAMES[0]);
		DataCard[] cards = this.model.getRound().getRoundCards();

		for(DataCard card : cards) {
			assertTrue(playerCards.contains(card));
		}

	}

	@Test
	public void testNumOfCardsRemainingForPlayers() {
		//getActivePlayers in datagame, getDeck of the player in dataPlayer, then check the length of the array returned

		//check that the number of cards remaining for each player after each round is accurate

		this.model.startGame();

		DataPlayer[] players = this.model.getActivePlayers();

		for(DataPlayer player:players) {
			assert player.getDeck().size() == 8;
		}

		this.model.playRound(DataGame.CATEGORYNAMES[0]);

		ArrayList<DataPlayer> winners = this.model.getRound().getRoundWinningPlayers();

		if(winners.size()==1) {
			assert winners.get(0).getDeck().size() == 12;
			assert winners.get(0).getDeck().size() != 8;
			for(DataPlayer player:players) {
				if(winners.get(0) != player) {
					assert player.getDeck().size() == 7;
				}
			}
		} else if(winners.size()>1) {
			assert this.model.getCardsInCommonPile().length == 5;
			for(DataPlayer player:players) {
				assert player.getDeck().size() == 7;
			}
		}

		int totalAmountOfCards = 0;
		// this loop sums up all players cards in their decks
		for(DataPlayer player : players) {
			totalAmountOfCards += player.getDeck().size();
		}

		assert 40 == totalAmountOfCards + this.model.getNumberOfCardsInCommonPile();

		this.model.getRound().incrementRound();


		this.model.playRound(DataGame.CATEGORYNAMES[0]);

	}

	@Test
	public void testWinningCardAndPlayer() {
		//test that the correct winning card was returned for different rounds
		//test that the correct winning category is pointed at
		//test that the correct player was declared winner

		this.model.startGame();

		DataCard[] cards = this.model.getInitialUnshuffledDeck();

		DataCard[] card5 = new DataCard[5];

		String category = DataGame.CATEGORYNAMES[0];

		for (int i=0;i<5;i++) {
			card5[i] = cards[i];
		}

		HashMap<String, Object> map = this.model.getWinningCardsAndPlayers(card5, category);

		ArrayList<DataCard> winningCards = (ArrayList<DataCard>)map.get("winning cards");

		assertTrue(winningCards.contains(card5[2]));

		DataPlayer[] players = this.model.getAllPlayers();

		for(DataPlayer player : players) {
			player.removeAllCardsFromDeck();
		}

		for(DataPlayer player : players) {
			for(DataCard card : card5) {
				if(player.equals(players[0]) && card.equals(card5[2])) {
					player.addCardToDeck(card);
				} else {
					player.addCardToDeck(card);
				}
			}
		}

		this.model.playRound(category);

		assertTrue(this.model.getRound().getRoundWinningPlayers().contains(players[0]));
	}

	@Test
	public void testGameEnd() {
		//test that the correct overall winner is returned
		//create a player, create a deck of just 2 cards, whoever wins that round is supposed to be the winner
		//test that the winner displayed is correct
		//test that the correct score for each player is returned
		//test that the correct score for each player is displayed

		int lastRoundNumberControl = 0;
		int lastRoundNumberCheck = 0;

		this.model.startGame();


		while(this.model.getGameState()==DataGame.GameState.RUNNING) {
			int category;


			DataPlayer activePlayer = this.model.getCategoryChooser();
			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
				category = this.model.getBestCategoryForPlayer(activePlayer);
			} else {
				category = 2;
			}

			this.model.playRound(DataGame.CATEGORYNAMES[category-1]);

			this.model.getRound().incrementRound();

		}

		lastRoundNumberControl = this.model.getRound().getRoundNumber();

		this.model.playRound(DataGame.CATEGORYNAMES[0]);

		lastRoundNumberCheck = this.model.getRound().getRoundNumber();

		assertEquals(lastRoundNumberControl, lastRoundNumberCheck);


	}

	@Test
	public void testGameRestart() {
		//test that when a game ends, the user is able to either start a new game or view statistics
		int gameRoundNumber = 0;

		this.model.startGame();


		while(this.model.getGameState()==DataGame.GameState.RUNNING) {
			int category;

			DataPlayer activePlayer = this.model.getCategoryChooser();
			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
				category = this.model.getBestCategoryForPlayer(activePlayer);
			} else {
				category = 2;
			}

			this.model.playRound(DataGame.CATEGORYNAMES[category-1]);

			this.model.getRound().incrementRound();

		}

		this.model = new DataGame(4);

		this.model.startGame();

		int category;

		DataPlayer activePlayer = this.model.getCategoryChooser();

		if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
			category = this.model.getBestCategoryForPlayer(activePlayer);
		} else {
			category = 2;
		}

		this.model.playRound(DataGame.CATEGORYNAMES[category-1]);

		gameRoundNumber = this.model.getRound().getRoundNumber();

		this.model.getRound().incrementRound();

		assertEquals(1, gameRoundNumber);

	}

	@Test
	public void testGameStatistics() {
		//test that the correct number of games is returned
		//test that the correct number of human wins is returned
		//test that the correct number of AI wins is returned
		//test that the correct number of draws is returned
		//test that the largest number of rounds played in a single game is returned
		//ensure that these values were calculated using SQL

		ProgramDatabase.clearDB(this.model);

		int game1RoundNumber = 0;


		this.model.startGame();


		while(this.model.getGameState()==DataGame.GameState.RUNNING) {
			int category;


			DataPlayer activePlayer = this.model.getCategoryChooser();

			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
				category = this.model.getBestCategoryForPlayer(activePlayer);
			} else {
				category = 2;
			}

			this.model.playRound(DataGame.CATEGORYNAMES[category-1]);

			game1RoundNumber = this.model.getRound().getRoundNumber();

			this.model.getRound().incrementRound();

		}

		assertEquals(DataGame.getAvgNumberOfDraws(), this.model.getNumberOfDraws(), 0.001);

		if(this.model.getGameWinner().getType() == DataPlayer.PlayerType.HUMAN) {
			assertEquals(1, DataGame.getNumberOfHumanWins());
		} else {
			assertEquals(1, DataGame.getNumberOfAIWins());
		}

		assertEquals(1, DataGame.getNumberOfGames());

		int game2RoundNumber = 0;

		this.model = new DataGame(4);

		this.model.startGame();


		while(this.model.getGameState()==DataGame.GameState.RUNNING) {
			int category;


			DataPlayer activePlayer = this.model.getCategoryChooser();
			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
				category = this.model.getBestCategoryForPlayer(activePlayer);
			} else {
				category = 2;
			}

			this.model.playRound(DataGame.CATEGORYNAMES[category-1]);

			game2RoundNumber = this.model.getRound().getRoundNumber();

			this.model.getRound().incrementRound();

		}

		int higherRoundNumber = game2RoundNumber>game1RoundNumber ? game2RoundNumber : game1RoundNumber;

		assertEquals(higherRoundNumber, DataGame.getLongestGame());

	}
}
