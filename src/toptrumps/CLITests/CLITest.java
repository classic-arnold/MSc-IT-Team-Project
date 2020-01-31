package toptrumps.CLITests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
import toptrumps.DataGame;
import toptrumps.ViewCLI;

public class CLITest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	DataGame model;
	ViewCLI view;
	Controller controller;
	
	
	@Before
	public void setUp() {
		model = DataGame.resetAndGetInstance(4);
		view = new ViewCLI(model); // we need to pass in view here
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
	void testViewOption() {
		//check if a new game starts when user inputs 1
		//check if game statistics displays if player inputs 2
		//check if exception is handled if player inputs a wrong digit
		//check if exception is handled if the user inputs a letter instead
	}
	
	@Test
	public void testAccurateRoundNumber() {
		//check that the correct round number is displayed after after several rounds are played
		
		controller.startGame();
		
		assertEquals(1,model.getRoundNumber());
		
		
	}
	
	@Test
	public void testRoundNumberDisplay() {
		//test that the correct round number is displayed after several rounds are played
	}
	
	@Test
	public void testCorrectTopCard() {
		//check that the correct top card was returned for several rounds
	}
	
	@Test
	public void testTopCardDisplay() {
		//check that the correct top card was displayed for several rounds
	}
	
	@Test
	public void testNumOfCardsRemainingForPlayers() {
		//check that the number of cards remaining for each player after each round is accurate
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
	}
	
	@Test
	public void testGameEnd() {
		//test that the correct overall winner is returned
		//test that the winner displayed is correct
		//test that the correct score for each player is returned
		//test that the correct score for each player is displayed
	}
	
	@Test
	public void testGameRestart() {
		//test that when a game ends, the user is able to either start a new game or view statistics
	}
	
	@Test
	public void testGameStatistics() {
		//test that the correct number of games is returned
		//test that the correct number of human wins is returned
		//test that the correct number of AI wins is returned
		//test that the correct number of draws is returned
		//test that the largest number of rounds played in a single game is returned
		//ensure that these values were calculated using SQL
	}
	
	@Test
	public void testGameStatisticsDisplay() {
		//test that all the above data are displayed correctly
	}
	
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}
	
	@After
	public void tearDown() {
		controller = null;
	}
	
	
	

}
