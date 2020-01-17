package toptrumps;

/**
 * 
 * This class represents a card
 * 
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U Team
 * 
 */
class DataCard{
	/** string representing card name */
	private String name;
	
	/** integer representing card height */
	private int height;
	
	/** integer representing card weight */
	private int weight;
	
	/** integer representing card length */
	private int length;
	
	/** integer representing card ferocity */
	private int ferocity;
	
	/** integer representing card intelligence */
	private int intelligence;
	
	/**
	 * 
	 * DataCard class constructor
	 * 
	 * @param name string representing card name
	 * @param height integer representing card height
	 * @param weight integer representing card weight
	 * @param length integer representing card length
	 * @param ferocity integer representing card ferocity
	 * @param intelligence integer representing card intelligence
	 */
	DataCard(String name, int height, int weight, int length, int ferocity, int intelligence){
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.length = length;
		this.ferocity = ferocity;
		this.intelligence = intelligence;
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
	boolean greaterThan(DataCard otherCard, String category) throws exceptions.CategoryNotFoundException{
		boolean result;
		/* 
		 * uses switch to set the result to true or false, depending on the category chosen.
		 * since the first letter of all categories are different, we check for this (and its uppercase) only
		 * to avoid formatting error.
		 * 
		 */
		switch (category.charAt(0)) {
		case 'h':
		case 'H':
			return result = this.height > otherCard.height ? true : false;
		
		case 'w':
		case 'W':
			return result = this.weight > otherCard.weight ? true : false;
		
		case 'l':
		case 'L':
			return result = this.length > otherCard.length ? true : false;
			
		case 'f':
		case 'F':
			return result = this.ferocity > otherCard.ferocity ? true : false;
			
		case 'i':
		case 'I':
			return result = this.intelligence > otherCard.intelligence ? true : false;
			
		default:
			throw new exceptions.CategoryNotFoundException();
		}
	}
	
	// GETTER METHODS START
	
	/**
	 * Getter method
	 * @return name string representing card name
	 */
	String getName() {
		return this.name;
	}
	
	/**
	 * Getter method
	 * @return height integer representing card height
	 */
	int getHeight() {
		return this.height;
	}
	
	/**
	 * Getter method
	 * @return weight integer representing card weight
	 */
	int getWeight() {
		return this.weight;
	}
	
	/**
	 * Getter method
	 * @return length integer representing card length
	 */
	int getLength() {
		return this.length;
	}
	
	/**
	 * Getter method
	 * @return ferocity integer representing card ferocity
	 */
	int getFerocity() {
		return this.ferocity;
	}
	
	/**
	 * Getter method
	 * @return intelligence integer representing card intelligence
	 */
	int getIntelligence() {
		return this.intelligence;
	}
	
	// GETTER METHODS END
	
	
	
	
}
