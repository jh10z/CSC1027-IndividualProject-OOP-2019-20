package part02;

/**
 * This class represents a product which is stored in suppliers
 * @author 40259391 | Kar Hay Ho
 *
 */
public class Product{
	
	private int proCode; //unique instance id
	private String proMake; // product make
	private String proModel; // product model
	private double proPrice; // product price 
	private int proQtyAvailable; // product quantity
	private boolean proDiscontinued; // product discontinued status
	private static int nextNum = 1; // class variable to determine next avaiable ID
	
	/**
	 * Construtor for Product
	 * @throws Exception thrown by setters if needed
	 */
	public Product(String proMake, String proModel, double proPrice, int proQtyAvailable, boolean proDiscounted) throws Exception {
		this.proCode = nextNum;
		nextNum++; //nextNum incremented by 1 for next instance use
		
		//Setter used on construction to validate parameters
		setProMake(proMake);
		setProModel(proModel);
		setProPrice(proPrice);
		setProQty(proQtyAvailable);
		setProDiscontinued(proDiscontinued);
	}
	
	//Getters
	/**
	 * This method is used to streamline the product console output
	 * @return String - formatted for console print
	 */
	public String getProDetails() {
		return  "\n" +
				"- Make: " + this.proMake + " (Pro No. " + this.proCode + ")"+ "\n" +
				"  Model: " + this.proModel + "\n" + 
				"  Price: " + String.format("£%.2f", this.proPrice) +  "\n" +
				"  Qty: " + this.proQtyAvailable + "\n" +
				"  Discontinued: " + this.proDiscontinued;
	}
	
	/**
	 * Accessor for proCode
	 * @return int - the product instance id
	 */
	public int getProCodeNum() {
		return this.proCode;
	}
	/**
	 * Accessor for proMake
	 * @return String - the product make
	 */
	public String getProMake() {
		return this.proMake;
	}
	
	/**
	 * Accessor for proModel
	 * @return String - the product model
	 */
	public String getProModel() {
		return this.proModel;
	}
	
	/**
	 * Accessor for proPrice
	 * @return double - value of product
	 */
	public double getProPrice() {
		//price validation
		return this.proPrice;
	}
	
	/**
	 * Accessor for proQty
	 * @return int - product quantity
	 */
	public int getProQty() {
		return this.proQtyAvailable;
	}
	
	/**
	 * Accessor for proDiscontinued
	 * @return boolean - discontinued product
	 */
	public boolean getDiscontinued() {
		return this.proDiscontinued;
	}
	
	//Setters
	/**
	 * Protected mutator for proMake
	 * @param proMake - the product make
	 * @throws Exception - thrown if parameter data is null or empty
	 */
	protected void setProMake(String proMake) throws Exception {
		if(proMake != null && !proMake.isEmpty()) {
			this.proMake = proMake;
		}
		else {
			throw new Exception("(!) Product make must be a non-empty string");
		}
	}
	
	/**
	 * Protected mutator for proModel
	 * @param proModel - the product model
	 * @throws Exception thrown if parameter data is null or empty
	 */
	protected void setProModel(String proModel) throws Exception {
		if(proModel != null && !proModel.isEmpty()) {
			this.proModel = proModel;
		}
		else {
			throw new Exception("(!) Product make must be a non-empty string");
		}
	}
	
	/**
	 * Mutator for proPrice
	 * @param proPrice - the product price
	 * @throws Exception thrown if parameter data is not a positive number
	 */
	public void setProPrice(double proPrice) throws Exception {
		if(proPrice >= 0.00) {
			this.proPrice = proPrice;
		}
		else {
			throw new Exception("(!) Product price must be positve");
		}
	}
	
	/**
	 * Mutator for proQty
	 * @param proQtyAvailable - the quantity available
	 * @throws Exception - thrown if parameter data is less than 0
	 */
	public void setProQty(int proQtyAvailable) throws Exception {
		if (proQtyAvailable >= 0) {
			this.proQtyAvailable = proQtyAvailable;
		}
		else {
			throw new Exception("(!) Product quantity must be atleast 0");
		}
	}
	
	/**
	 * Mutator for proDiscontinued
	 * @param proDiscontinued - the product discontinued status
	 * @throws Exception - thrown if boolean is null
	 */
	public void setProDiscontinued(Boolean proDiscontinued) throws Exception {
		if(proDiscontinued != null) {
			this.proDiscontinued = proDiscontinued;		
		}
		else {
			throw new Exception("(!) Discontinued is set to null");
		}
	}
}
