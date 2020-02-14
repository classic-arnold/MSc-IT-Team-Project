package toptrumps;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to cache the card details from the file. This ensures that the file is read just once per game.
 * @author salistechltd
 *
 */
public class DataCardCache {
	/** holds the list of cards in order from the file */
	private static DataCard[] CARDARRAY = new DataCard[40];

	/**
	 * gets a clone of all card objects and returns it in a list in order
	 * @return DataCard array representing all cards in deck in order
	 */
	public static DataCard[] getAllCardsInOrder() {
		// store number of cards
		int length = CARDARRAY.length;

		// holds a deep copy of the CARDARRAY
		DataCard[] deepCopyArray = new DataCard[length];

		// clone all objects
		for(int i = 0; i<length; i++) {
			deepCopyArray[i] = (DataCard)CARDARRAY[i].clone();
		}

		return deepCopyArray;
	}

	/**
	 * loads cards from file and stores in the cache
	 */
	public static void loadCardFromFileAndCache() {

		// file reader to read files
		FileReader fileReader;

		try {
			// read this file
			fileReader = new FileReader("StarCitizenDeck.txt");

			// load contents in scanner
			Scanner s = new Scanner(fileReader);

			try {
				// skip first line
				String categoriesLine = s.nextLine();

				Scanner s2 = new Scanner(categoriesLine);

				ArrayList<String> categories = new ArrayList<String>();

				s2.next();

				// load the categories
				while(s2.hasNext()) {
					categories.add(s2.next());
				}

				// store them in a static array
				DataGame.CATEGORYNAMES = categories.toArray(new String[5]);

				int i = 0;

				// while card has a next line, load card details into a new DataCard object
				while(s.hasNextLine()) {
					String description = s.next();
					int size = s.nextInt(); int speed = s.nextInt(); int range = s.nextInt(); int firePower = s.nextInt(); int cargo = s.nextInt();

					CARDARRAY[i] = new DataCard(description, size, speed, range, firePower, cargo);
					i++;
				}

				s2.close();
			} catch(Exception e) {
				e.printStackTrace();
			}

			// close scanner
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
