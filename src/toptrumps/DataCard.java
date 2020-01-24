package toptrumps;

/**
 * 
 * This class represents a card
 * 
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U Team
 * 
 */
class DataCard implements Cloneable{
	
	/** string representing card description */
	private String description;

	/** integer representing card size */
	private int size;

	/** integer representing card speed */
	private int speed;

	/** integer representing card range */
	private int range;

	/** integer representing card firePower */
	private int firePower;

	/** integer representing card cargo */
	private int cargo;

	/**
	 * 
	 * creates a new card
	 * 
	 * @param name string representing card name
	 * @param size integer representing card size
	 * @param speed integer representing card speed
	 * @param range integer representing card range
	 * @param firePower integer representing card firePower
	 * @param cargo integer representing card cargo
	 */
	DataCard(String description, int size, int speed, int range, int firePower, int cargo){
		this.description = description;
		this.size = size;
		this.speed = speed;
		this.range = range;
		this.firePower = firePower;
		this.cargo = cargo;
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
		switch (Character.toLowerCase(category.charAt(0))+""+Character.toLowerCase(category.charAt(1))) {
		case "si":
			int result = 0;
			result = this.size > otherCard.size ? 1 : result;
			result = this.size == otherCard.size ? 2 : result;
			return result;
		case "sp":
			result = 0;
			result = this.speed > otherCard.speed ? 1 : result;
			result = this.speed == otherCard.speed ? 2 : result;
			return result;

		case "ra":
			result = 0;
			result = this.range > otherCard.range ? 1 : result;
			result = this.range == otherCard.range ? 2 : result;
			return result;

		case "fi":
			result = 0;
			result = this.firePower > otherCard.firePower ? 1 : result;
			result = this.firePower == otherCard.firePower ? 2 : result;
			return result;

		case "ca":
			result = 0;
			result = this.cargo > otherCard.cargo ? 1 : result;
			result = this.cargo == otherCard.cargo ? 2 : result;
			return result;

		default:
			throw new exceptions.CategoryNotFoundException(); // if category does not exist
		}
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
	int getSize() {
		return this.size;
	}

	/**
	 * get speed
	 * @return integer representing card value for category - speed
	 */
	int getSpeed() {
		return this.speed;
	}

	/**
	 * get range
	 * @return integer representing card value for category - range
	 */
	int getRange() {
		return this.range;
	}

	/**
	 * get fire power
	 * @return firePower integer representing card value for category - fire power
	 */
	int getFirePower() {
		return this.firePower;
	}

	/**
	 * get cargo
	 * @return integer representing card value for category - cargo
	 */
	int getCargo() {
		return this.cargo;
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
		string += arrow + space + "size" + colon + space + this.size + newLine;
		string += arrow + space + "speed" + colon + space + this.speed + newLine;
		string += arrow + space + "range" + colon + space + this.range + newLine;
		string += arrow + space + "fire power" + colon + space + this.firePower + newLine;
		string += arrow + space + "cargo" + colon + space + this.cargo + newLine;
		
		return string;
	}


}
