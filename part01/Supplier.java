package part01;

import java.util.ArrayList;
/**
 * This class is used to store products, address and region; it is used to represent suppliers
 * @author 40259391 | Kar Hay Ho
 *
 */
public class Supplier {
	private int supCode; //instance supplier id
	private String supName;//supplier name
	private Address supAddress; //supplier address
	private Region supRegion; //supplier region
	private ArrayList<Product> supProducts = new ArrayList<Product>(); //supplier address pro list
	private static int nextNum = 1; // class variable to get the next instance ID
	
	/**
	 * Constructor for Supplier
	 * @param supName - setter called to validate param
	 * @param supRegion - setter called to validate param
	 */
	public Supplier(String supName, Address supAddress, Region supRegion) throws Exception {
		supCode = nextNum;
		nextNum++;
		setSupName(supName);
		this.supAddress = validAddress(supAddress);
		setSupRegion(supRegion);
	}
	
	//Getters
	/**
	 * Iterates through every product stored in supplier and outputs formated string method for each product
	 * @return product details - string format
	 */
	public String getProductList() {
		String str = new String();
		for (Product product : supProducts) {
			str += product.getProDetails() + "\n";
		}	
		return str;
	}
	
	/**
	 * Accessor for supCode
	 * @return inr - supplier instance ID
	 */
	public int getSupCodeNum() {
		return this.supCode;
	}
	
	/**
	 * Accessor for supplier name
	 * @return
	 */
	public String getSupName() {
		return this.supName;
	}
	
	/**
	 * Accessor for supplier address
	 * @return address accessor of stored supplier address object
	 */
	public String getSupAddress() {
		return this.supAddress.getFullAddress();
	}
	
	/**
	 * Accessor for supplier region
	 * @return enumuration status of region
	 */
	public Region getSupRegion() {
		return this.supRegion;
	}
	
	/**
	 * Accessor for product arraylist
	 * @return normal array
	 */
	public Product[] getSupProducts() {
		return supProducts.toArray(new Product[supProducts.size()]);
	}
	
	//Setters
	/**
	 * Mutator for supName
	 * @param supName - supplier name
	 * @throws Exception - thrown if param non-empty string
	 */
	public void setSupName(String supName) throws Exception {
		if (supName != null && !supName.isEmpty()) {
			this.supName = supName;
		} else {
			throw new Exception("(!) Supplier name must be a non-empty string");
		}
	}
	
	/**
	 * Mutator for supAddress
	 * @param supAddress - supplier address
	 * @throws Exception - thrown if invalid region 
	 */
	public void setSupAddress (String supAddress) throws NumberFormatException, Exception {
		if (supAddress != null && !supAddress.isEmpty()) {
			String[] addressArray = supAddress.split(",");
			this.supAddress = new Address(Integer.parseInt(addressArray[0].trim()), addressArray[1].trim(),
					addressArray[2].trim(), addressArray[3].trim(), addressArray[4].trim());
		}
		else {
			throw new Exception("(!) Supplier region must be a valid region");
		}

	}
	
	/**
	 * Mutator for supRegion
	 * @param supRegion - supplier region
	 * @throws Exception - throws if region object is null
	 */
	public void setSupRegion(Region supRegion) throws Exception {
		if(supRegion != null) {
			this.supRegion = supRegion;
		} else {
			throw new Exception("(!) Supplier region must not be null");
		}
	}
	
	/**
	 * Murator for supProducts
	 * @param supProduct - the product instance to be added
	 * @throws Exception - throws exception if supProduct is null or not an instance of CProduct 
	 */
	public void addProduct(Product supProduct) throws Exception {
		if(supProduct != null) {
			supProducts.add(supProduct);
		} else {
			throw new Exception("(!) Unable to add product");
		}
	}
	
	/**
	 * Mutator for supProducts
	 * @param supProduct - the desired instance to be removed
	 * @return - true if product instance was removed, false if not found or null
	 */
	public boolean removeProduct (Product supProduct) {
		if(supProduct != null) {
			for(int i = 0; i < supProducts.size(); i++) {
				if (supProduct.getProCodeNum() == supProducts.get(i).getProCodeNum()) {
					supProducts.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Private method for constructor validation
	 * @param address
	 * @return - if address object not null, it returns
	 * @throws Exception - thrown if adress object is null
	 */
	private Address validAddress(Address address) throws Exception {
		if (address != null) {
			return address;
		}
		throw new Exception ("(!) Invalid Address");		
	}
}
