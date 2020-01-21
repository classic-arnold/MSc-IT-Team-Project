package toptrumps;
/*
 * REMINDER: load cache before using getCard. Load cache in main method.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DataCardCache {
	private static DataCard[] CARDARRAY = new DataCard[40];
	
	public static DataCard[] getAllCardsInOrder() {
		return CARDARRAY.clone();
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
					
					CARDARRAY[i] = new DataCard(description, size, speed, range, firePower, cargo);
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
