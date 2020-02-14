package toptrumps.GameTests;

import static org.junit.Assert.*;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;

/**
 * This class contains the various test cases for the CLI version of the game
 * 
 * Team: TRY_CATCH
 * 
 * @author Chika Hope Elumeziem 2500799E
 * 
 * */

import org.junit.Test;

import toptrumps.Controller;
import toptrumps.DataCard;
import toptrumps.DataCardCache;
import toptrumps.DataGame;
import toptrumps.DataPlayer;
import toptrumps.ProgramDatabase;
import toptrumps.ViewCLI;

public class GameTest{
//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	private final PrintStream originalOut = System.out;
	
	DataGame model;
	ViewCLI view;
	Controller controller;
	
	
	@Before
	// Initialise instances of the game's model, view and controller objects before running each test
	public void setUp() {
		model = DataGame.resetAndGetInstance(4);
		view = new ViewCLI(model);
		controller = new Controller(model, view, true);
	}
	
	@Before
//	public void setUpStreams() {
//		System.setOut(new PrintStream(outContent));
//	}

	@Test
	public void testCLIStartGame() {
		//check if the game actually starts in the CLI version if the user selects -c flag
		//check if the exception is correctly handled if the user 
		//	i) user inputs c without the -
		//	ii) user inputs any other letter of the alphabet that's not c or o
		//	iii) user inputs a number instead of an alphabet
		
//
//		controller.startGame();
//		
//		String data = "q";
//		InputStream input = null;
//		try {
//			input = new ByteArrayInputStream(data.getBytes("UTF-8"));
//		} catch(Exception e) {
//			
//		}
//		
//		System.setIn(input);
//		
//		String output = "Do you want to see past results or play a game\n" + 
//				"1:Print Game Statistics\n" + 
//				"2:Play game\n" + 
//				"Enter the number for your selection:";
//		assertEquals(outContent.toString(), output);
	}
	
	@Test
	public void testViewOption() {
		//check if a new game starts when user inputs 1
		//check if game statistics displays if player inputs 2
		//check if exception is handled if player inputs a wrong digit
		//check if exception is handled if the user inputs a letter instead
	}
	
	@Test
	public void testAccurateRoundNumber() {

		//check that the correct round number is displayed after after several rounds are played
		
		int round = 1; //variable created by me to manually compare it with what the game outputs
		
		int startChoice = 2;

		if(startChoice == 1) {
			/* the content of "if" is omitted because it handles game statistics functionality which is 
			not what is currently being tested */
		} else if(startChoice == 2) {

			String continueOrEndGameChoice = null;

			this.model.startGame();

			while(this.model.getGameState()==DataGame.GameState.RUNNING) {
				int category;

				DataPlayer activePlayer = this.model.getCategoryChooser();
				this.view.updateView();
				if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
					category = this.model.getBestCategoryForPlayer(activePlayer);
				} else {
					category = 2;
				}

				this.model.playRound(DataGame.CATEGORYNAMES[category-1]);

				this.view.displayRoundResult(DataGame.CATEGORYNAMES[category-1]);

				continueOrEndGameChoice = "";


				if (continueOrEndGameChoice.contentEquals("q")) {
					break;
				}

				this.model.getRound().incrementRound();
				
				round++; //the round is manually incremented so that it can be compared with game round

			}

			if(continueOrEndGameChoice.contentEquals("")) {
				this.view.gameEnd();
			} else if (continueOrEndGameChoice.contentEquals("q")) {
				
			}

		} else {
		}
		
		assertEquals(round, this.model.getRound().getRoundNumber());
	}
	
	@Test
	public void testRoundNumberDisplay() {
		//test that the correct round number is displayed after several rounds are played
	}
	
	@Test /*****************************************************************************/
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
	public void testTopCardDisplay() {
		//check that the correct top card was displayed for several rounds
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
	public void testNumOfRemainingCardsDisplay() {
		//test that the number of remaining cards for each player  is correctly displayed
	}
	
	@Test
	public void testCategoryValues() {
		
		//test that the categories are properly matched with corresponding values for each card
		//check that the categories and values are correctly displayed
		//test that the category the user selected is correctly reflected
		//test that exception handling is performed for wrong user input for digits
		//check what happens if the user inputs a char or string instead
	}
	
	@Test
	public void testWinningCardAndPlayer() {
		
		
		//test that the correct winning card was returned for different rounds
		//test that the correct winning card was displayed
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
		
		int gameRoundNumber = 0;
		
		this.model.startGame();
		

		while(this.model.getGameState()==DataGame.GameState.RUNNING) {
			int category;


			DataPlayer activePlayer = this.model.getCategoryChooser();
			this.view.updateView();
			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
				category = this.model.getBestCategoryForPlayer(activePlayer);
			} else {
				category = 2;
			}
			// int category = this.viewCli.displayCategorySelection();

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
			this.view.updateView();
			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
				category = this.model.getBestCategoryForPlayer(activePlayer);
			} else {
				category = 2;
			}
			// int category = this.viewCli.displayCategorySelection();

			this.model.playRound(DataGame.CATEGORYNAMES[category-1]);

			this.model.getRound().incrementRound();

		}
		
		this.model.startGame();
		
		int category;


		DataPlayer activePlayer = this.model.getCategoryChooser();
		this.view.updateView();
		if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
			category = this.model.getBestCategoryForPlayer(activePlayer);
		} else {
			category = 2;
		}
		// int category = this.viewCli.displayCategorySelection();

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
			this.view.updateView();
			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
				category = this.model.getBestCategoryForPlayer(activePlayer);
			} else {
				category = 2;
			}
			// int category = this.viewCli.displayCategorySelection();

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

		this.model.startGame();
		

		while(this.model.getGameState()==DataGame.GameState.RUNNING) {
			int category;


			DataPlayer activePlayer = this.model.getCategoryChooser();
			this.view.updateView();
			if(this.model.getRound().getRoundActivePlayer().getType() == DataPlayer.PlayerType.AI) {
				category = this.model.getBestCategoryForPlayer(activePlayer);
			} else {
				category = this.view.displayCategorySelection();
			}

			// int category = this.viewCli.displayCategorySelection();

			this.model.playRound(DataGame.CATEGORYNAMES[category-1]);
			
			game2RoundNumber = this.model.getRound().getRoundNumber();

			this.model.getRound().incrementRound();

		}
		
		int higherRoundNumber = game2RoundNumber>game1RoundNumber ? game2RoundNumber : game1RoundNumber;
		
		assertEquals(higherRoundNumber, DataGame.getLongestGame());
		
		
	}
	
	@Test
	public void testGameStatisticsDisplay() {
		//test that all the above data are displayed correctly
	}

	
	// Unit Tests
	@Test
	public void testBestCategoryForPlayer() {
		DataPlayer player = new DataPlayer(DataPlayer.PlayerType.HUMAN, 0);
		
		player.addCardToDeck(DataCardCache.getAllCardsInOrder()[0]);
		
		assertEquals(2, this.model.getBestCategoryForPlayer(player));
	}
	
	@Test
	public void testCardShuffle() {
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
		assertTrue(this.model.getHumanPlayer().getType()==DataPlayer.PlayerType.HUMAN);
	}
	
	@Test
	public void testListToArrayAndArrayToList() {
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
		DataCard card = new DataCard("Test", 1,1,1,1,5);
		DataCard card2 = new DataCard("Test", 1,1,7,1,1);
		DataCard card3 = new DataCard("Test", 1,4,4,1,1);
		
		assertEquals(5, card.findTopCategory());
		assertEquals(3, card2.findTopCategory());
		assertTrue(card3.findTopCategory()==2 || card3.findTopCategory()==3);
	}
	
//	@After
//	public void restoreStreams() {
//		System.setOut(originalOut);
//	}

}
