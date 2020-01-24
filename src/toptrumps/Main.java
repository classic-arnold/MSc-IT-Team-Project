package toptrumps;

import java.util.Random;

// not needed. delete.
// used only for quick testing.
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		DataGame game = DataGame.getInstance(10); // get the game instance with 1 AI player
//		DataGame game = DataGame.getInstance(4);
		int i = 2;
		while (i<5) {
			DataGame game = DataGame.resetAndGetInstance(i); // used to start a new game
			i += 2;
			game.startGame();
			while(game.getGameState()==DataGame.GameState.RUNNING) {
				Random r = new Random();
				game.playRound(DataGame.CATEGORYNAMES[r.nextInt(5)]);
			}
			
			DataPlayer[] p = game.getAllPlayers();
			
			System.out.println("\nAll players:");
			for(DataPlayer pl : p) {
				System.out.println(pl.getName() + ": " + pl.getScore());
			}
			
			System.out.println("Draw: " + game.getNumberOfDraws());
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
