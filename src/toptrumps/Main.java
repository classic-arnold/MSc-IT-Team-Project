package toptrumps;

// not needed. delete.
// used only for quick testing.
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DataGame game = DataGame.getInstance(4);
		game.startGame();
		while(game.getGameState()==DataGame.GameState.RUNNING) {
			game.playRound("speed");
		}
		
		return;
		
		
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
