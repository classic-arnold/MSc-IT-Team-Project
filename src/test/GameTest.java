package test;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import main.model.*;
import commandline.view.*;
import commandline.controller.*;
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

		// check that the correct round number is displayed after after several rounds are played
		// This is done by iterating a value as the game loops, until the game is over
		// Then we get the last round of the game and compare it with the value we manually iterated
		// if the game counted the round properly, the two values should be the same

		int round = 1; //variable created to manually compare it with what the game outputs

		this.model.startGame(); // start a game

		// start game loop and play game
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
			//tests the round number for every round
			assertEquals(round, this.model.getRound().getRoundNumber());

		}
		//tests the round number at the end of the game
		assertEquals(round, this.model.getRound().getRoundNumber());
	}

	@Test
	public void testCorrectTopCard() {
		// check that the correct top card was returned for several rounds

		// this works by getting the players top card manually and the playing a round and checking the round
		//cards are the players top card

		// start a game
		this.model.startGame();

		// get the games active players
		DataPlayer[] players = this.model.getActivePlayers();

		// this will represent the cards of the players we will use for test
		ArrayList<DataCard> playerCards = new ArrayList<DataCard>();

		// loop through the players and get the top cards
		for(int i=0;i<players.length;i++) {
			playerCards.add(players[i].getDeck().get(0));
		}

		// play 1 round
		this.model.playRound(DataGame.CATEGORYNAMES[0]);

		// get the round cards
		DataCard[] cards = this.model.getRound().getRoundCards();

		// check that the round cards and the top cards of the players are equal
		for(DataCard card : cards) {
			assertTrue(playerCards.contains(card));
		}
	}

	@Test
	public void testNumOfCardsRemainingForPlayers() {
		//check that the number of cards remaining for each player after each round is accurate

		// start a game
		this.model.startGame();

		// get the active players
		DataPlayer[] players = this.model.getActivePlayers();

		// assert that every player has 8 cards, if there are players, then every player must have 8 cards
		for(DataPlayer player:players) {
			assert player.getDeck().size() == 8;
		}

		// play a round
		this.model.playRound(DataGame.CATEGORYNAMES[0]);

		// get the winner
		ArrayList<DataPlayer> winners = this.model.getRound().getRoundWinningPlayers();

		// if game wasn't draw, assert that the winning player now has 12 cards, and no longer has 8 cards
		if(winners.size()==1) {
			assert winners.get(0).getDeck().size() == 12;
			assert winners.get(0).getDeck().size() != 8;

			// assert that other players have 7 cards
			for(DataPlayer player:players) {
				if(winners.get(0) != player) {
					assert player.getDeck().size() == 7;
				}
			}
		} else if(winners.size()>1) { // if game was draw, assert that common pile must have 5 cards, and all players have 7 cards
			assert this.model.getCardsInCommonPile().length == 5;
			for(DataPlayer player:players) {
				assert player.getDeck().size() == 7;
			}
		}

		// the total amount of cards
		int totalAmountOfCards = 0;

		// this loop sums up all players cards in their decks
		for(DataPlayer player : players) {
			totalAmountOfCards += player.getDeck().size();
		}

		// assert that the sum of all cards in players deck and common pile is 40
		assert 40 == totalAmountOfCards + this.model.getNumberOfCardsInCommonPile();
	}

	@Test
	public void testWinningCardAndPlayer() {
		//test that the correct winning card was returned for different rounds
		//test that the correct winning category is pointed at
		//test that the correct player was declared winner

		// start the game
		this.model.startGame();

		// get the unshuffled deck
		DataCard[] cards = this.model.getInitialUnshuffledDeck();

		// 5 cards
		DataCard[] card5 = new DataCard[5];

		// get first category
		String category = DataGame.CATEGORYNAMES[0];

		// get 5 cards from the unshuffled deck, card number 3 has the highest for this category so it should win the round
		for (int i=0;i<5;i++) {
			card5[i] = cards[i];
		}

		// get results from model object from the 5 cards, using this category
		HashMap<String, Object> map = this.model.getWinningCardsAndPlayers(card5, category);

		// get the winning cards
		ArrayList<DataCard> winningCards = (ArrayList<DataCard>)map.get("winning cards");

		// the winning cards should contain card number 3, this confirms that the correct card wins
		assertTrue(winningCards.contains(card5[2]));

		// get all players
		DataPlayer[] players = this.model.getAllPlayers();

		// remove all cards from players deck
		for(DataPlayer player : players) {
			player.removeAllCardsFromDeck();
		}

		// loop through players and add the 5 cards created to their deck, and put card 3(the winning card) in the 1st player deck
		for(DataPlayer player : players) {
			for(DataCard card : card5) {
				if(player.equals(players[0]) && card.equals(card5[2])) {
					player.addCardToDeck(card);
				} else {
					player.addCardToDeck(card);
				}
			}
		}

		// play the round
		this.model.playRound(category);

		// assert that the 1st player is one of the winning players
		assertTrue(this.model.getRound().getRoundWinningPlayers().contains(players[0]));
	}

	@Test
	public void testGameEnd() {
		// test that the correct overall winner is returned
		// create a player, create a deck of just 2 cards, whoever wins that round is supposed to be the winner
		// test that the winner displayed is correct

		// create control and check variables to represent round number
		int lastRoundNumberControl = 0;
		int lastRoundNumberCheck = 0;

		// start the game
		this.model.startGame();

		// run the game loop
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

		// store the real last round
		lastRoundNumberControl = this.model.getRound().getRoundNumber();

		// play one more round
		this.model.playRound(DataGame.CATEGORYNAMES[0]);

		// store the new round number, if shouldn't increase because the game shouldn't play
		lastRoundNumberCheck = this.model.getRound().getRoundNumber();

		// if both are equal, this means the game stopped playing when the loop ended
		assertEquals(lastRoundNumberControl, lastRoundNumberCheck);


	}

	@Test
	public void testGameRestart() {
		// test the game can restart properly

		// stores round number
		int gameRoundNumber = 0;

		// start a game
		this.model.startGame();

		// run game loop
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

		// start a new game
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

		// if round number is 1, then game restarted properly
		assertEquals(1, gameRoundNumber);

	}
	
//THE CODE BELOW IS FOR TESTING THE STATISTICS RETURNED FROM THE DATABASE. THE TEST REQUIRES THAT THE DATABASE 
//IS CLEARED FIRST SO AS TO TEST THE DATABASE WITH NEWLY GENERATED VALUES. IT IS THEREFORE COMMENTED TO AVOID DISCREPANCIES
//BETWEEN THE VALUES THE EXAMINER EXPECTS AND THE RETURNED VALUES
	@Test
	public void testGameStatistics() {
		//test that the correct number of games is returned
		//test that the correct number of human wins is returned
		//test that the correct number of AI wins is returned
		//test that the correct number of draws is returned
		//test that the largest number of rounds played in a single game is returned

//		// clears the database
//		ProgramDatabase.clearDB(this.model);
//
//		// store game round number
//		int game1RoundNumber = 0;
//
//		// start a game
//		this.model.startGame();
//
//		// run game loop
//		while(this.model.getGameState()==DataGame.GameState.RUNNING) {
//			int category;
//
//
//			DataPlayer activePlayer = this.model.getCategoryChooser();
//
//			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
//				category = this.model.getBestCategoryForPlayer(activePlayer);
//			} else {
//				category = 2;
//			}
//
//			this.model.playRound(DataGame.CATEGORYNAMES[category-1]);
//
//			game1RoundNumber = this.model.getRound().getRoundNumber();
//
//			this.model.getRound().incrementRound();
//
//		}
//
//		// assert that the database returns the correct avg number of draws, because only 1 game has been played and 
//		// the avg should be the number of draws in the last game
//		assertEquals(DataGame.getAvgNumberOfDraws(), this.model.getNumberOfDraws(), 0.001);
//
//		// if the game was won by human or AI, assert that the database is returning the correct number of AI or human wins
//		if(this.model.getGameWinner().getType() == DataPlayer.PlayerType.HUMAN) {
//			assertEquals(1, DataGame.getNumberOfHumanWins());
//		} else {
//			assertEquals(1, DataGame.getNumberOfAIWins());
//		}
//
//		// assert that only 1 game is played in database
//		assertEquals(1, DataGame.getNumberOfGames());
//
//		// next game round number
//		int game2RoundNumber = 0;
//
//		// start a new game
//		this.model = new DataGame(4);
//		this.model.startGame();
//
//
//		// run game loop
//		while(this.model.getGameState()==DataGame.GameState.RUNNING) {
//			int category;
//
//
//			DataPlayer activePlayer = this.model.getCategoryChooser();
//			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
//				category = this.model.getBestCategoryForPlayer(activePlayer);
//			} else {
//				category = 2;
//			}
//
//			this.model.playRound(DataGame.CATEGORYNAMES[category-1]);
//
//			game2RoundNumber = this.model.getRound().getRoundNumber();
//
//			this.model.getRound().incrementRound();
//
//		}
//
//		// if game 1 round number was higher than game 2 round number, game 1 was longer, and vice versa
//		int higherRoundNumber = game2RoundNumber>game1RoundNumber ? game2RoundNumber : game1RoundNumber;
//
//		// check the longer round number is being returned by the database
//		assertEquals(higherRoundNumber, DataGame.getLongestGame());

	}
}
