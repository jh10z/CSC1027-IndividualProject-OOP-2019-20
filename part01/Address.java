package part01;

/**
 * This class is used to store the supplier address
 * @author 40259391
 *
 */
public class Address {
	private int bldNum; //Building Number
	private String bldStreet; //Street Name
	private String bldTown; // Town of Street
	private String bldPCode;// Postcode of Area
	private String bldCountry; // Country of Address
	
	/**
	 * Construtor for Address
	 * @param bldNum - the given building number
	 * @param bldStreet - the given street name
	 * @param bldTown - the given town name
	 * @param bldPCode - the given postcode
	 * @param bldCountry - the given country
	 * @throws Exception
	 */
	public Address(int bldNum, String bldStreet, String bldTown, String bldPCode, String bldCountry) throws Exception {
		//setters are called to add instance data with validation
		setBldNum(bldNum);
		setBldStreet(bldStreet);
		setBldTown(bldTown);
		setBldPCode(bldPCode);
		setBldCountry(bldCountry);
	}

	//Getters
	/**
	 * Gather the full address of the object class into a specifically formated way for later use
	 * @return - string details (seperated by commas) of address
	 */
	public String getFullAddress() {
		String str = "";
		str += this.bldNum + ",";
		str += this.bldStreet + ",";;
		str += this.bldTown + ",";
		str += this.bldPCode + ",";;
		str += this.bldCountry;
		return str;
	}
	
	//Getters
	/**
	 * Accessor for bldNum
	 * @return int - the building number
	 */
	public int getBldNum() {
		return this.bldNum;
	}
	
	/**
	 * Accessor for bldStreet
	 * @return String - the street name
	 */
	public String getBldStreet() {
		return this.bldStreet;
	}
	
	/**
	 * Accessor for bldTown
	 * @return String - the town name
	 */
	public String getBldTown() {
		return this.bldTown;
	}
	
	/**
	 * Accessor for bldPCode
	 * @return String - the postcode
	 */
	public String getBldPCode() {
		return this.bldPCode;
	}
	
	/**
	 * Accessor for bldCountry
	 * @return String - the country name
	 */
	public String getBldCountry() {
		return this.bldCountry;
	}
	
	//Setters
	/**
	 * Public mutator for bldNum 
	 * @param bldNum - int, the new building number
	 * @throws Exception - thrown if number is below 1
	 */
	public void setBldNum(int bldNum) throws Exception {
		if(bldNum > 0) {
			this.bldNum = bldNum;
		}
		else {
			throw new Exception("Building number must be more than 0, not: " + bldNum);
		}
	}
	
	/**
	 * Public mutator for bldStreet
	 * @param bldStreet - String, the new street name
	 * @throws Exception 
	 */
	public void setBldStreet(String bldStreet) throws Exception {
		if(bldStreet != null && !bldStreet.isEmpty()) {
			this.bldStreet = bldStreet;
		}
		else {
			throw new Exception("(!) The building street must be a non-empty string");
		}
	}
	
	/**
	 * Public mutator for bldTown
	 * @param bldTown - String, the new town name
	 * @throws Exception 
	 */
	public void setBldTown(String bldTown) throws Exception {
		if(bldTown != null && !bldTown.isEmpty()) {
			this.bldTown = bldTown;
		}
		else {
			throw new Exception("(!) The town must be a non-empty string");
		}
	}
	
	/**
	 * Public mutator for bldPCode
	 * @param bldPCode - String, the new postcode
	 * @throws Exception 
	 */
	public void setBldPCode(String bldPCode) throws Exception {
		if(bldPCode != null && !bldPCode.isEmpty()) {
			this.bldPCode = bldPCode;
		}
		else {
			throw new Exception("(!) The postcode must be a non-empty string");
		}
	}
	
	/**
	 * Public mutator for bldCountry
	 * @param bldCountry - String, the new country name
	 * @throws Exception 
	 */
	public void setBldCountry(String bldCountry) throws Exception {
		if(bldCountry!= null && !bldCountry.isEmpty()) {
			this.bldCountry = bldCountry;
		}
		else {
			throw new Exception("(!) The postcode must be a non-empty string");
		}
	}
	
}
