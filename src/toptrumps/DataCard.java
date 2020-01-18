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
	 * DataCard class constructor
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
	boolean greaterThan(DataCard otherCard, String category) throws exceptions.CategoryNotFoundException{
		/* 
		 * uses switch to set the result to true or false, depending on the category chosen.
		 * since the first letter of all categories are different, we check for this (and its uppercase) only
		 * to avoid formatting error.
		 * 
		 */
		switch (Character.toLowerCase(category.charAt(0))+""+Character.toLowerCase(category.charAt(1))) {
		case "si":
			boolean result = this.size > otherCard.size ? true : false;
			return result;

		case "sp":
			result = this.speed > otherCard.speed ? true : false;
			return result;

		case "ra":
			result = this.range > otherCard.range ? true : false;
			return result;

		case "fi":
			result = this.firePower > otherCard.firePower ? true : false;
			return result;

		case "ca":
			result = this.cargo > otherCard.cargo ? true : false;
			return result;

		default:
			throw new exceptions.CategoryNotFoundException();
		}
	}

	// GETTER METHODS START

	/**
	 * Getter method
	 * @return name string representing card name
	 */
	String getDescription() {
		return this.description;
	}

	/**
	 * Getter method
	 * @return size integer representing card size
	 */
	int getSize() {
		return this.size;
	}

	/**
	 * Getter method
	 * @return speed integer representing card speed
	 */
	int getSpeed() {
		return this.speed;
	}

	/**
	 * Getter method
	 * @return range integer representing card range
	 */
	int getRange() {
		return this.range;
	}

	/**
	 * Getter method
	 * @return firePower integer representing card fire power
	 */
	int getFirePower() {
		return this.firePower;
	}

	/**
	 * Getter method
	 * @return cargo integer representing card cargo
	 */
	int getCargo() {
		return this.cargo;
	}

	// GETTER METHODS END

	/**
	 * used to clone the card objects
	 */
	public Object clone() {
		Object cloneCard = null;

		try {
			cloneCard = super.clone();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return cloneCard;
	}
	
	@Override
	public String toString() {
		String string = "";
		String space = " ";
		String arrow = ">";
		String colon = ":";
		String newLine = "\n";
		
		string += arrow + space + "size" + colon + space + this.size + newLine;
		string += arrow + space + "speed" + colon + space + this.speed + newLine;
		string += arrow + space + "range" + colon + space + this.range + newLine;
		string += arrow + space + "fire power" + colon + space + this.firePower + newLine;
		string += arrow + space + "cargo" + colon + space + this.cargo + newLine;
		
		return string;
	}


}
