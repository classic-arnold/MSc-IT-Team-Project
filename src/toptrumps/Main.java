package toptrumps;

import toptrumps.DataPlayer.PlayerType;

// not needed. delete.
// used only for quick testing.
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataCardCache.loadCardCacheFromFile();
		
		DataPlayer p = new DataPlayer("human");
		DataCard[] cards = p.createRandomDeck();
		
		for(DataCard card:cards) {
			System.out.println(card);
		}
		
	}

}
