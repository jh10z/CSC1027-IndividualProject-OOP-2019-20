package part01;

/**
 * This class represents a region used by suppliers
 * @author 40259391 | Kar Hay Ho
 *
 */
public enum Region {
UNITED_KINGDOM(0), EUROPE(1), OUTSIDE_EU(2);
	
	//String Array correlating the set enumuration above
	private String[] regionName = {"United Kingdom", "Europe", "Outside EU"};
	
	private int region;
	
	/**
	 * Private constuctor for Region
	 * @param region - assigned number to region
	 */
	private Region(int region) {
		this.region = region;
	}
	
	/**
	 * Accessor for region status
	 * @return formatted string gotten from regionName array
	 */
	public String getRegionAsString() {
		return regionName[region];
	}
	
	
}
