package toptrumps;

import java.util.Random;

// not needed. delete.
// used only for quick testing.
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DataGame game = DataGame.getInstance(1); // get the game instance with 1 AI player
//		game = DataGame.resetAndGetInstance(1); // used to start a new game
		game.startGame();
		while(game.getGameState()==DataGame.GameState.RUNNING) {
			Random r = new Random();
			game.playRound(DataGame.CATEGORYNAMES[r.nextInt(5)]);
		}
		
		
//		DataGame dG = DataGame.getInstance();
//		dG.loadDeck();
//		DataCard[] ca = dG.getCompleteDeck();
//		for (DataCard c : ca) {
//			System.out.println(c);
//		}
		
//		DataPlayer p = new DataPlayer("human");
//		DataCard[] cards = p.createRandomDeck();
//		
//		System.out.println(p.getType());
//		
//		for(DataCard card:cards) {
//			System.out.println(card);
//		}
//		
//		System.out.println(DataCardCache.get());
	}

}
