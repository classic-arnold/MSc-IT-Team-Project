package toptrumps;

import java.util.HashMap;

public class DataCardCache {
	private static HashMap<String, DataCard> cardMap  = new HashMap<String, DataCard>();

	public static DataCard getCard(String cardId) {
		DataCard cachedCard = cardMap.get(cardId);
		return (DataCard) cachedCard.clone();
	}

	// load cache before using getCard

	public static void loadCache() {
		Circle circle = new Circle();
		circle.setId("1");
		shapeMap.put(circle.getId(),circle);

		Square square = new Square();
		square.setId("2");
		shapeMap.put(square.getId(),square);

		Rectangle rectangle = new Rectangle();
		rectangle.setId("3");
		shapeMap.put(rectangle.getId(), rectangle);
	}
}
