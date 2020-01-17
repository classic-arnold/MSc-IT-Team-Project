package toptrumps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class DataCardCache {
	private static HashMap<String, DataCard> cardMap  = new HashMap<String, DataCard>();

	public static DataCard getCard(String cardId) {
		DataCard cachedCard = cardMap.get(cardId);
		return (DataCard) cachedCard.clone();
	}
	
	public static DataCard getRandomCard() {
		DataCard cachedCard = new DataCard("remove", 1, 1, 1, 1, 1); // remove this
//		set String cardId to random card
//		DataCard cachedCard = cardMap.get(cardId); // use this
//		return (DataCard) cachedCard.clone();
		return cachedCard;
	}

	// load cache before using getCard. load cache in main method.

	public static void loadCache() {
//		DataCard card350r = new DataCard("350r", 1, );
//		circle.setId("1");
//		shapeMap.put(circle.getId(),circle);
//
//		Square square = new Square();
//		square.setId("2");
//		shapeMap.put(square.getId(),square);
//
//		Rectangle rectangle = new Rectangle();
//		rectangle.setId("3");
//		shapeMap.put(rectangle.getId(), rectangle);
		
//		FileReader fileReader;
//		try {
//			fileReader = new FileReader("../../StarCitizenDeck.txt");
//			
//			Scanner s = new Scanner(fileReader);
//			
//			
//			
//			s.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		// read all cards from file once and put here
	}
}
