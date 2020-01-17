package toptrumps;

/**
 * 
 * This class represents a card
 * 
 * @author Team TRY-CATCH - Arnold Umakhihe 2445734U Team
 */
class DataCard {
	private String name;
	private int height;
	private int weight;
	private int length;
	private int ferocity;
	private int intelligence;
	
	protected DataCard(String name, int height, int weight, int length, int ferocity, int intelligence){
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.length = length;
		this.ferocity = ferocity;
		this.intelligence = intelligence;
	}
	
	protected boolean greaterThan(DataCard otherCard, String category){
		boolean result = false;
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
			return result;
		}
	}
	
	
	
}
