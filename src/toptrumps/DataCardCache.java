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
	private static DataCard[] CARDARRAY = new DataCard[40];

	public static DataCard getCard(String cardDescription) {
		DataCard cachedCard = CARDMAP.get(cardDescription);
		return (DataCard) cachedCard.clone();
	}
	
	public static DataCard getRandomCard() {
		List<String> listOfKeys = new ArrayList<String>(CARDMAP.keySet());
		Random r = new Random();
		
		DataCard cachedCard = CARDMAP.get(listOfKeys.get(r.nextInt(listOfKeys.size())));
		return (DataCard) cachedCard.clone();
	}
	
	public static DataCard[] getAllCardsInOrder() {
		return CARDARRAY;
	}
	
	public static HashMap<String, DataCard> get() {
		return CARDMAP;
	}

	public static void loadCardFromFileAndCache() {
		
		FileReader fileReader;
		try {
			fileReader = new FileReader("StarCitizenDeck.txt");
			
			Scanner s = new Scanner(fileReader);
			
			try {
				s.nextLine();
				
				int i = 0;
				
				while(s.hasNextLine()) {
					String description = s.next();
					int size = s.nextInt(); int speed = s.nextInt(); int range = s.nextInt(); int firePower = s.nextInt(); int cargo = s.nextInt();
					CARDMAP.put(description, new DataCard(description, size, speed, range, firePower, cargo));
					
					CARDARRAY[i] = DataCardCache.getCard(description);
					i++;
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
