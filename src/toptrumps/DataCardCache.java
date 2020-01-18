package toptrumps;
/*
 * REMINDER: load cache before using getCard. Load cache in main method.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DataCardCache {
	private static HashMap<String, DataCard> CARDMAP  = new HashMap<String, DataCard>();

	public static DataCard getCard(String cardId) {
		DataCard cachedCard = CARDMAP.get(cardId);
		return (DataCard) cachedCard.clone();
	}
	
	public static DataCard getRandomCard() {
		List<String> arrayOfKeys = new ArrayList<String>(CARDMAP.keySet());
		Random r = new Random();
		
		DataCard cachedCard = CARDMAP.get(arrayOfKeys.get(r.nextInt(arrayOfKeys.size())));
		return (DataCard) cachedCard.clone();
	}

	public static void loadCardCacheFromFile() {
		
		FileReader fileReader;
		try {
			fileReader = new FileReader("StarCitizenDeck.txt");
			
			Scanner s = new Scanner(fileReader);
			
			try {
				s.nextLine();
				
				while(s.hasNextLine()) {
					String name = s.next();
					CARDMAP.put(name, new DataCard(name, s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()));
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
