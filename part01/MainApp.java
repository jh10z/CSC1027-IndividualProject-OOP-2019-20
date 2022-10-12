package part01;

import java.util.ArrayList;

/**
 * This is the main class where all the classes are brought together as one system.
 * @author 40259391 | Kar Hay Ho
 *
 */
public class MainApp {
	
	private static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();

	public static void main(String[] args) {
		do {
			processMMOption(menuProcess(new Menu("QUB Supplier and Product Management Tool", 
					new String[] {"Print All Products", "Add New Supplier", "Add New Product", "Exit Application" })));		
		}while(true);
	}
	
	//Menu section
	private static int menuProcess(Menu menu) {
		return menu.processMenuChoice(); 
		// Moved Body to Menu class, signiture and return is intentional to keep the one line code
	}
		
	private static void processMMOption(int option) {
		switch(option) {
			case 1: displayAllProducts();
		break;
		
			case 2: addSupplier();
		break;
		
			case 3: addProduct();
		break;
		
			case 4:
				System.out.println("\n" + "(!) Console Terminated");
				System.exit(0);
 		break;		
		}
	}
	
	/**
	 * @category Print All Products
	 * This sole method displays all products stored in the application grouped by their supplier
	 */
	public static void displayAllProducts() {
		System.out.println("\n" + "Products Grouped By Suppliers"); //Header
		System.out.println("-----------------------------"); //Header Divider
		
		if(suppliers.size() > 0) { //Checks if there are suppliers to print from
			for(int supNum = 0; supNum < suppliers.size(); supNum++) { //for-loop to iterate through each supplier
				//If products null
				System.out.println("\n" + "--------[" + suppliers.get(supNum).getSupName() + " (Sup No. " + suppliers.get(supNum).getSupCodeNum() + ")]--------"); // print title of each supplier
				if(suppliers.get(supNum).getProductList().length() > 0 ) { 
					System.out.println(suppliers.get(supNum).getProductList()); //calls method for each supplier if products exist in the supplier instance
				}
				else {//if there are no products in the supplier instance, the supplier will still display but with the following message
					System.out.println("- Currently No Products"); 
				}
			}
			return; //returns void to end method
		}
		System.out.println("\n" + "There are currently no suppliers to print from");
		//This point is only reachable if there are no suppliers 
	}
	
	/**
	 * @category Add New Supplier
	 * The sole method, addSupplier, allows a new supplier to be added to a list of suppliers defined in this class,
	 * which will be stored for the duration of the program.  
	 */
	private static void addSupplier() {
		try {
			System.out.println("\n" + "Add New Supplier Section"); //Header
			System.out.println("------------------------" + "\n"); //Header Divider
		
			//The following block is calling a static method from the Menu class. Each with a respective 
			//return type but very similar in concept - view menu for more info
			String supName = Menu.getStringPrompt("Enter Supplier Name: ");
			int bldNum = Menu.getIntPrompt("Enter Street Number: ", 1); 
			String bldStreet = Menu.getStringPrompt("Enter Street Name: "); 
			String bldTown = Menu.getStringPrompt("Enter Town Name: ");
			String bldPCode = Menu.getStringPrompt("Enter Postcode: ");
			String bldCountry = Menu.getStringPrompt("Enter Country: ");
			//To sate the UML specification, an address object is created with the information gathered above
			Address address = new Address(bldNum, bldStreet, bldTown, bldPCode, bldCountry);
			
			int regionNum = menuProcess(new Menu("Select the Region of the Supplier", 
					new String[] {"United Kingdom", "Europe", "Outside the EU"})); // return 1, 2, 3
			
			//As you can see, the supplier constructor takes an address object
			suppliers.add(new Supplier(supName, address, Region.values()[regionNum - 1])); 
			//To make direct use of the region menu used, I used Region.values() to get the respective status stored
			//in the Region enumuration class
			System.out.println("\n" + "(+) Successfully Added New Supplier!");
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
			//Some setters throw generic exceptions with specific messages which is why a try-catch is neccessary
		}
	}
	
	/**
	 * @category Add a New Product
	 * The following methods are used in cohesion to allow the creation of the product 
	 */
	private static void addProduct() {	
		try {
			if(suppliers.size() > 0) {
				System.out.println("\n" + "Add New Product Section"); //Header
				System.out.println("-----------------------" + "\n"); //Header Divider
				
				//The same use of the static menu methods are used to gather the necessary product information
				String proMake = Menu.getStringPrompt("Enter Make: ");
				String proModel = Menu.getStringPrompt("Enter Model: ");
				double proPrice =  Menu.getPricePrompt("Enter Price: ");
				int proQtyAvailable = Menu.getIntPrompt("Enter Quantity: ", 0); 
				int boolOption = menuProcess(new Menu("Discontinued?", 
						new String[] {"Yes", "No"})); //Returns 1 if yes; 2 if no
				Boolean proDiscontinued = (boolOption == 1); //To keeps things short, I used the == operator
				//to return true if 1 is chosen, anything else if false but the code is user validated to only otherwise accept 2
				addProductToSuppliers(new Product(proMake, proModel, proPrice, proQtyAvailable,
						proDiscontinued)); //Once a unique instance is created, it is passed to be added to selected suppliers
			}
			else {
				System.out.println("\n" + "(!) There must be at least one supplier to add a product");
			}
			
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
	}
	
	/**
	 * By passing the unique instance of product, I can effectively add the same instance to mutliple suppliers
	 * @param supProduct - newly created product instance
	 */
	private static void addProductToSuppliers(Product supProduct) { 	
		ArrayList<Supplier> availableSuppliers = new ArrayList<>(suppliers); //List of Suppliers 
		boolean addedSupplier = false;
		try {
			do {
				Menu supplierMenu = new Menu("Select Suppliers for the Product", 
						supplierNameArray(availableSuppliers)); //Making use of the menu class to create a supplier
				//selection menu
				
				int supChoice = menuProcess(supplierMenu); //the choice goes through a process of user validation
				
				if(supChoice != availableSuppliers.size() + 1) { //If the choice is not the last choice displayed in the menu
					int supListPos = linearSearch(availableSuppliers.get(supChoice - 1).getSupCodeNum()); //search for the postion of the
					//selected supplier from the available suppliers in the actually suppliers list - this is important because the deduction nature of the menu
					if (supListPos >= 0 ) { 
						suppliers.get(supListPos).addProduct(supProduct); //If the position is found, use that to add the product instance
						availableSuppliers.remove(supChoice - 1); //Remove the ability to select the same supplier
						addedSupplier = true;
					}
				}
				else if (!addedSupplier){
					System.out.println("\n" + "(!) You must add at least one supplier to create a product ");
				}
				else {
					System.out.println("\n" + "(!) Finished Adding Suppliers"); //If there are no more suppliers, the process will automatically end
					return; 
				}
			} while(availableSuppliers.size() > 0);
		} catch (Exception e) {	
			System.out.println("\n" + e.getMessage() + "\n");
			//Try-catch for any thrown exceptions
		}
	}
	
	/**
	 * supplierNameArray - used for the to generate the menu for supplier selection
	 * @param availableSuppliers - updated list of available supplier objects
	 * @return array names of available suppliers
	 */
	private static String[] supplierNameArray(ArrayList<Supplier> availableSuppliers) { 
		String[] supNames = new String[availableSuppliers.size() + 1]; // + 1 for addition option
		for(int i = 0; i < availableSuppliers.size(); i++ ) {
			supNames[i] = availableSuppliers.get(i).getSupName(); // Each available supplier will be displayed by name
		}
		supNames[supNames.length - 1] = "Finished"; //Additional option to end selection early
		return supNames; //return this to be used in the menu construtor (array)
	}
	
	//Search Method - used to find the index positon of the supplier
	private static int linearSearch(int supCode) {
		for(int i = 0; i < suppliers.size(); i++) {
			if(suppliers.get(i).getSupCodeNum() == supCode) {
				return i;
			}
		}
		return -1; 
	}
}
