package toptrumps;

/**
 * 
 * This class represents a card
 * 
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U
 * 
 */
class DataCard implements Cloneable{
	
	/** string representing card description */
	private String description;

	/** integer representing card category1 */
	private int category1;

	/** integer representing card category2 */
	private int category2;

	/** integer representing card category3 */
	private int category3;

	/** integer representing card category4 */
	private int category4;

	/** integer representing card category5 */
	private int category5;

	/**
	 * 
	 * creates a new card
	 * 
	 * @param name string representing card name
	 * @param category1 integer representing card category1
	 * @param category2 integer representing card category2
	 * @param category3 integer representing card category3
	 * @param category4 integer representing card category4
	 * @param category5 integer representing card category5
	 */
	DataCard(String description, int category1, int category2, int category3, int category4, int category5){
		this.description = description;
		this.category1 = category1;
		this.category2 = category2;
		this.category3 = category3;
		this.category4 = category4;
		this.category5 = category5;
	}

	/**
	 * 
	 * compares two cards based on the category provided. If this card category value is greater than
	 * the other card "otherCard", returns true. Returns false otherwise.
	 * 
	 * @param otherCard DataCard class representing the other card
	 * @param category String representing the category
	 * @return true if this card category value is greater than the other card's "otherCard". Returns false otherwise.
	 */
	int compare(DataCard otherCard, String category){
		/* 
		 * uses switch to set the result to true or false, depending on the category chosen.
		 * since the first letter of all categories are different, we check for this (and its uppercase) only
		 * to avoid formatting error.
		 * 
		 */
		String category1FirstTwoLetters =DataGame.CATEGORYNAMES[0].charAt(0)+""+DataGame.CATEGORYNAMES[0].charAt(1);
		String category2FirstTwoLetters =DataGame.CATEGORYNAMES[1].charAt(0)+""+DataGame.CATEGORYNAMES[1].charAt(1);
		String category3FirstTwoLetters =DataGame.CATEGORYNAMES[2].charAt(0)+""+DataGame.CATEGORYNAMES[2].charAt(1);
		String category4FirstTwoLetters =DataGame.CATEGORYNAMES[3].charAt(0)+""+DataGame.CATEGORYNAMES[3].charAt(1);
		String category5FirstTwoLetters =DataGame.CATEGORYNAMES[4].charAt(0)+""+DataGame.CATEGORYNAMES[4].charAt(1);
		String chosenCategoryFirstTwoLetters =Character.toLowerCase(category.charAt(0))+""+Character.toLowerCase(category.charAt(1));
		
		int result = 0;
		
		if(chosenCategoryFirstTwoLetters.equalsIgnoreCase(category1FirstTwoLetters)) {
			result = this.category1 > otherCard.category1 ? 1 : result;
			result = this.category1 == otherCard.category1 ? 2 : result;
			return result;
		}
		
		if(chosenCategoryFirstTwoLetters.equalsIgnoreCase(category2FirstTwoLetters)) {
			result = this.category2 > otherCard.category2 ? 1 : result;
			result = this.category2 == otherCard.category2 ? 2 : result;
			return result;
		}
		
		if(chosenCategoryFirstTwoLetters.equalsIgnoreCase(category3FirstTwoLetters)) {
			result = this.category3 > otherCard.category3 ? 1 : result;
			result = this.category3 == otherCard.category3 ? 2 : result;
			return result;
		}
		
		if(chosenCategoryFirstTwoLetters.equalsIgnoreCase(category4FirstTwoLetters)) {
			result = this.category4 > otherCard.category4 ? 1 : result;
			result = this.category4 == otherCard.category4 ? 2 : result;
			return result;
		}
		
		if(chosenCategoryFirstTwoLetters.equalsIgnoreCase(category5FirstTwoLetters)) {
			result = this.category5 > otherCard.category5 ? 1 : result;
			result = this.category5 == otherCard.category5 ? 2 : result;
			return result;
		}
		
		System.out.println(chosenCategoryFirstTwoLetters);
		
		throw new exceptions.CategoryNotFoundException(); // if category does not exist
	}
	
	public int findTopCategory() {
		if(this.category1>=this.category2 && this.category1>=this.category3 && this.category1>=this.category4 && this.category1>=this.category5) {
			return 1;
		}
		if(this.category2>=this.category1 && this.category2>=this.category3 && this.category2>=this.category4 && this.category2>=this.category5) {
			return 2;
		}
		if(this.category3>=this.category1 && this.category3>=this.category2 && this.category3>=this.category4 && this.category3>=this.category5) {
			return 3;
		}
		if(this.category4>=this.category1 && this.category4>=this.category2 && this.category4>=this.category3 && this.category4>=this.category5) {
			return 4;
		}
		if(this.category5>=this.category1 && this.category5>=this.category2 && this.category5>=this.category3 && this.category5>=this.category4) {
			return 5;
		}
		return 0;
	}

	// GETTER METHODS START

	/**
	 * get description
	 * @return string representing card description
	 */
	String getDescription() {
		return this.description;
	}

	/**
	 * get size
	 * @return integer representing card value for category - size
	 */
	int getCategory1() {
		return this.category1;
	}

	/**
	 * get speed
	 * @return integer representing card value for category - speed
	 */
	int getCategory2() {
		return this.category2;
	}

	/**
	 * get category3
	 * @return integer representing card value for category - category3
	 */
	int getCategory3() {
		return this.category3;
	}

	/**
	 * get fire power
	 * @return category4 integer representing card value for category - fire power
	 */
	int getCategory4() {
		return this.category4;
	}

	/**
	 * get category5
	 * @return integer representing card value for category - category5
	 */
	int getCategory5() {
		return this.category5;
	}

	// GETTER METHODS END

	/**
	 * used to clone the card objects of this card class
	 * @return object representing new clone of this class instance
	 */
	public Object clone() {
		Object cloneCard = null; // holds the clone

		try {
			cloneCard = super.clone(); // try to clone
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(); // if clone not supported, print stack trace
		}

		return cloneCard;
	}
	
	/**
	 * used to render this class to string
	 * @return string representing class details
	 */
	@Override
	public String toString() {
		// store constants
		String string = "";
		String space = " ";
		String arrow = ">";
		String colon = ":";
		String newLine = "\n";
		
		// create strings
		string += arrow + space + DataGame.CATEGORYNAMES[0] + colon + space + this.category1 + newLine;
		string += arrow + space + DataGame.CATEGORYNAMES[1] + colon + space + this.category2 + newLine;
		string += arrow + space + DataGame.CATEGORYNAMES[2] + colon + space + this.category3 + newLine;
		string += arrow + space + DataGame.CATEGORYNAMES[3] + colon + space + this.category4 + newLine;
		string += arrow + space + DataGame.CATEGORYNAMES[4] + colon + space + this.category5 + newLine;
		
		return string;
	}
	
	/**
	 * used to render this class to string with winning category
	 * @return string representing class details
	 */
	public String toString(String category) {
		// store constants
		String string = "";
		String space = " ";
		String arrow = ">";
		String colon = ":";
		String newLine = "\n";
		
		// create strings
		string += arrow + space + DataGame.CATEGORYNAMES[0] + colon + space + this.category1;
		if(((category.charAt(0)) + "" + Character.toLowerCase(category.charAt(1))).equalsIgnoreCase(DataGame.CATEGORYNAMES[0].charAt(0)+""+DataGame.CATEGORYNAMES[0].charAt(1))){
			string += " <--";
		}
		string += newLine;
		
		
		string += arrow + space + DataGame.CATEGORYNAMES[1] + colon + space + this.category2;
		if((Character.toLowerCase(category.charAt(0)) + "" + Character.toLowerCase(category.charAt(1))).equalsIgnoreCase(DataGame.CATEGORYNAMES[1].charAt(0)+""+DataGame.CATEGORYNAMES[1].charAt(1))){
			string += " <--";
		}
		string += newLine;
		
		
		string += arrow + space + DataGame.CATEGORYNAMES[2] + colon + space + this.category3;
		if((category.charAt(0)) == DataGame.CATEGORYNAMES[2].charAt(0)){
			string += " <--";
		}
		string += newLine;
		
		
		string += arrow + space + DataGame.CATEGORYNAMES[3] + colon + space + this.category4;
		if((category.charAt(0)) == DataGame.CATEGORYNAMES[3].charAt(0)){
			string += " <--";
		}
		string += newLine;
		
		string += arrow + space + DataGame.CATEGORYNAMES[4] + colon + space + this.category5;
		if((category.charAt(0)) == DataGame.CATEGORYNAMES[4].charAt(0)){
			string += " <--";
		}
		string += newLine;
		
		return string;
	}


}
