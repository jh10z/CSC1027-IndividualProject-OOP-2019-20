package part02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main class where all the classes are brought together as one system.
 * @author 40259391 | Kar Hay Ho
 *
 */
public class MainApp {
	
	private static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
	private final static String CSV_FILE = "SupplierAppData.csv"; //Defined here for convenience 
		
	public static void main(String[] args) {
		suppliers = importFromFile();
		do {			mainMenuOptions(menuProcess(new Menu("QUB Supplier and Product Management Tool", 
					new String[] {"Print Product(s) Facility", "Product Facility", "Supplier Facility", "Exit Application (Save Data)"})));		
		}while(true);
	}
	
	private static int menuProcess(Menu menu) {
		return menu.processMenuChoice(); 
		//Moved the method body to a method in the menu, kept used code to keep structure the same
	}
		
	private static void mainMenuOptions(int option) {
		switch(option) {
			case 1: printFacility(); 
					break;

			case 2: productFacility(); 
					break;
		
			case 3: supplierFacility();
					break;		
			case 4: 
					if(exportToFile()) {
						System.out.println("\n" + "(!) Console Terminated | Data Saved");
						System.exit(0);
			}		
		}
	}	
	/**
	 * @category The Print Product(s) Facility Section
	 * - displayProductBySupplier() - Core Functionality from part01
	 * - displayProducyByPrice() - Additional Functionality
	 * - produceQuote() - Additional Functionality
	 */
	
	private static void printFacility() { //Sub Menu (Print Product(s) Facility)
		int choice;
		
		do {
			choice = menuProcess(new Menu("Print Products Facility", 
					new String[] {"All By Suppliers", "All By Specific Price Range", "Quote For Products", "Go Back"}));		
		
			//Choice Processing
			switch(choice) {
				case 1: displayProductsBySupplier();
						break;
		
				case 2: displayProductByPrice();
						break;
				
				case 3: produceQuote();
						break;
			}
		
		} while(choice != 4);
		System.out.println("\n" + "(!) Exiting Print Product(s) Facility");
	}
	
	/*
	 * This is the core functionality as seen in part01
	 */
	public static void displayProductsBySupplier() {
		System.out.println("\n" + "Products Grouped By Suppliers");
		System.out.println("-----------------------------");
		
		if(suppliers != null && suppliers.size() > 0) {
			for(int supNum = 0; supNum < suppliers.size(); supNum++) {
				//If products null
				System.out.println("\n" + "--------[" + suppliers.get(supNum).getSupName() + " (Sup No. " + suppliers.get(supNum).getSupCodeNum() + ")]--------");
				if(suppliers.get(supNum).getProductList().length() > 0 ) {
					System.out.print(suppliers.get(supNum).getProductList());
				}
				else {
					System.out.println("\n " + "- Currently No Products");
				}
			}
			return;
		}
		System.out.println("\n" + "(!) There are currently no suppliers to print from");
	}
	
	/**
	 * Point 2 - Displays unqiue product instances within a selected price range, ordered asc by proCode
	 */
	private static void displayProductByPrice() {
		System.out.println("\n" + "Print All Products Within a Selected Price Range");
		System.out.println("------------------------------------------------" + "\n");
		if (getAllProducts().size() > 0) {
			double minPrice = Double.parseDouble(Menu.getPricePrompt("Enter the Minimum Value: ")); //Lower Value Boundary
			double maxPrice = Double.parseDouble(Menu.getPricePrompt("Enter the Maximum Value: ")); //Upper Value Boundary
			//Bubble sort to order the products by proCode (ascending)
			CProduct[] allProducts = Utility.bubbleSortProducts(getAllProducts().toArray(new CProduct[getAllProducts().size()]), true);
			int count = 0;
			if (allProducts != null) { //null validation
				System.out.println("\n" + "Products from " + String.format("£%.2f", minPrice) + " to " + String.format("£%.2f",maxPrice) + " (Ordered by ProCode)");
				System.out.println("-------------------------------------------------" + "\n");
				for (int i = 0; i < allProducts.length; i++) {
					if(allProducts[i].getProPrice() >= minPrice &&
							allProducts[i].getProPrice() <= maxPrice) { //Iterating through each product 
						//As the products are already order, each product that meets the conditions are successively printed out
						System.out.println("ID: " + allProducts[i].getProCodeNum() + " | Make: " + 
						allProducts[i].getProMake() + ", Model: " + allProducts[i].getProModel()
						+ ", Price: " + String.format("£%.2f",allProducts[i].getProPrice()));		
						count++;
					}
				}
				if(count == 0) {
					System.out.println("(!) There are no products within that price range");
				}
			}	
		}
		else { System.out.println("(!) There are no products to execute the search"); }
	}
	
	/**
	 * Point 3 - stock check at product selection, each product selected is multiplied by quantity wanted and added to other products 
	 */
	private static void produceQuote() {
		ArrayList<CProduct> availableProducts = getAllProducts();
		int choice = availableProducts.size();
		double totalCost = 0.00;
		String quoteStr = "\n" + "Quote for All Selected Products" + "\n" +
		"-------------------------------" + "\n";
		do { 	
			if(availableProducts.size() > 0) {
				choice = menuProcess(new Menu("Select Products to Add to Quote", 
						allProStocksNameArray(availableProducts, 2)));	
				
				if (choice <= availableProducts.size()) {
					int qtyWanted = Integer.parseInt(Menu.getIntPrompt("Enter Quantity Wanted: ", 1)); 
					//Quantity has to be one in this case
					quoteStr += qtyWanted + " x Make: " + availableProducts.get(choice - 1).getProMake()
							  + " | Model: " + availableProducts.get(choice - 1).getProModel() + " | Price: "
							  + String.format("£%.2f",availableProducts.get(choice - 1).getProPrice()) + "\n"; 
					//Print for selected products

					totalCost += (availableProducts.get(choice - 1).getProPrice() * qtyWanted);
					availableProducts.remove(choice - 1);
				}
				else if(availableProducts.size() == 0) { //else if order important here
					System.out.println("\n" + "(!) No More Products to Quote");
				}			
				else if(choice == availableProducts.size() + 1 && availableProducts.size() == getAllProducts().size()) {
					System.out.println("\n" + "(!) Please add at least one product to proceed");
					choice = availableProducts.size();
				}
				else {
					System.out.println("\n" + "(!) Exiting Quote Section");
					return;
				}
			}
			else {
				System.out.println("\n" + "(!) No Products to Quote");
				return;
			}
		} while (choice != availableProducts.size() + 1);
		System.out.println(quoteStr);
		System.out.println("Total Cost: " + String.format("£%.2f", totalCost));
	}
	
	/**
	 * Used to help with product selection: mainly for quotes
	 * @param allProducts - for reuse in case it's not strictly unique instance data
	 * @param options - for reuse in other areas, the product quote option can be disabled
	 * @return - returns string array for menu use
	 */
	private static String[] allProStocksNameArray(ArrayList<CProduct> allProducts, int options) {
		String[] supNames = new String[allProducts.size() + options];
		for(int i = 0; i < allProducts.size(); i++ ) {
			supNames[i] = "ID " + allProducts.get(i).getProCodeNum()+": Make: "+ allProducts.get(i).getProMake() 
					+ " | Model: " + allProducts.get(i).getProModel() +" (Stock: " + allProducts.get(i).getProQty() 
					+ " | Price: " + String.format("£%.2f",allProducts.get(i).getProPrice()) + ")";
		}
		supNames[supNames.length - 1] = "Go Back";
		if(options == 2) {
			supNames[supNames.length - 2] = "Produce Quote";
		}
		return supNames;
	}
	
	/**
	 * Widely used throughout the program to get unique instances of product
	 * @return - all unique instance products
	 */
	private static ArrayList<CProduct> getAllProducts() {
		//As the "all products by suppliers" returns repeated products, I have made
		//a natural guess that functionalities that require all products would have
		//a list of unique instances, regardless of supplier
		ArrayList<CProduct> allProducts = new ArrayList<CProduct>();
		for (Supplier sup : suppliers) { //Insertion sort needed because of 
			for (int index = 0; index < sup.getSupProducts().length; index++) {
				if(!Utility.productListContains(allProducts, sup.getSupProducts()[index].getProCodeNum())) {
					allProducts.add((CProduct)sup.getSupProducts()[index]);
				}
			}
		}
		return allProducts;
	}
	/**
	 * @category The Product Facility Section
	 * - addProduct() - Core Functionality
	 * - editProduct() - Additional Functionality
	 * - delProduct() - Addtional Functionality
	 */
	public static void productFacility() { // Sub Menu (Product Facility: Add, Edit, Delete)
		int choice;
		do {
			choice = menuProcess(new Menu("Product Facility", 
					new String[] {"Add Product", "Modify Product", "Delete Product", "Go Back"}));		
		
			//Choice Processing
			switch(choice) {
				case 1: addProduct();
						break;
			
				case 2: editProduct(selectProductMenu("Choose a Product to Edit"));
						break;
			
				case 3: delProduct(selectProductMenu("Choose a Product to Delete"));
						break;
				}
		} while(choice != 4);
		System.out.println("\n" + "(!) Exiting Print Product(s) Facility");
	}
	
	/**
	 * This is core functionality as seen in part01
	 * - minor tweaks to reuse a method
	 */
	private static void addProduct() {
		try {
			if(suppliers.size() > 0) {
				System.out.println("Add New Product Section"); //Header
				System.out.println("-----------------------" + "\n");
				
				//Calling the respective prompt needed
				String proMake = proPrompts(1);
				String proModel = proPrompts(2);
				double proPrice =  Double.parseDouble(proPrompts(3));
				int proQtyAvailable = Integer.parseInt(proPrompts(4)); 
				int boolOption = Integer.parseInt(proPrompts(5));
				Boolean proDiscontinued = (boolOption == 1); //returns true if 1
				CProduct proObj = new CProduct(proMake, proModel, proPrice, proQtyAvailable,
						proDiscontinued); //creating the unique instance 
				addProductToSuppliers(proObj);
			}
			else {
				System.out.println("\n" + "(!) There must be at least one supplier to add a product");
			}
		}catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}

	}
	
	/**
	 * Allows for a specific call of a user input, mainly for edit, but reused appropriately by add
	 * @param choice - decides the specific prompt used
	 * @return - user validated input
	 */
	private static String proPrompts(int choice) { 
		switch(choice) {
		case 1:
			return Menu.getStringPrompt("Enter Make: ");
		case 2:
			return Menu.getStringPrompt("Enter Model: ");
		case 3:
			return Menu.getPricePrompt("Enter Price: ");
		case 4:
			return Menu.getIntPrompt("Enter Quantity: ", 0);
		case 5:
			return Integer.toString(menuProcess(new Menu("Discontinued?", 
					new String[] {"Yes", "No"})));
		}
		return null;
	}
	
	/**
	 * This is core functionality seen in part01
	 * @param supProduct - unique product instance to be added to multiple suppliers
	 */
	private static void addProductToSuppliers(CProduct supProduct) { 	
		ArrayList<Supplier> availableSuppliers = new ArrayList<>(suppliers);
		boolean addedSupplier = false;
		try {
			do {
				Menu supplierMenu = new Menu("Select Suppliers for the Product", 
						supplierNameArray(availableSuppliers, "Finished"));
				
				int supChoice = menuProcess(supplierMenu);
				
				if(supChoice != availableSuppliers.size() + 1) {
					//finds the supplier position of selected available supplier, as this is deducted as you go
					int supListPos = Utility.linearSearch(availableSuppliers.get(supChoice - 1).getSupCodeNum(), suppliers);
					if (supListPos >= 0 ) {
						suppliers.get(supListPos).addProduct(supProduct);
						availableSuppliers.remove(supChoice - 1);
						addedSupplier = true; //Ensures that the user has added a supplier before hitting finish
					}
				}
				else if (!addedSupplier){
					System.out.println("\n" + "(!) You must add at least one supplier to create a product ");
					//The product instance will be eventually dumped if not referenced to at least one supplier
				}
				else {
					
					System.out.println("\n" + "(!) Finished Adding Suppliers");
					return; 
				}
			} while(availableSuppliers.size() > 0);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	/**
	 * Method seen in part01 - called for menu display
	 * @param availableSuppliers - suppliers not already selected
	 * @param lastOption - flexible last option for method reuse
	 * @return
	 */
	private static String[] supplierNameArray(ArrayList<Supplier> availableSuppliers, String lastOption) {
		String[] supNames = new String[availableSuppliers.size() + 1];
		for(int i = 0; i < availableSuppliers.size(); i++ ) {
			supNames[i] = availableSuppliers.get(i).getSupName();
		}
		supNames[supNames.length - 1] = lastOption;
		return supNames;
	}
	
	/**
	 * Point 1 - Modification of a selected product
	 * @param selectedProduct - used to seperately display fields for selection
	 */
	private static void editProduct(CProduct selectedProduct) {
		if (selectedProduct != null) {
			do {
				int choice = menuProcess(new Menu("Choose A Property To Edit",
						new String[] {
								"Product Make: " + selectedProduct.getProMake(), 
								"Product Model: " + selectedProduct.getProModel(), 
								"Price: " + selectedProduct.getProPrice(),
								"Quantity Available: " + selectedProduct.getProQty(),
								"Discontinued: " + selectedProduct.getDiscontinued(),
								"Go Back"}));
				
					if (choice != 6) {
						updateProductField(selectedProduct, choice, proPrompts(choice));
						//proPrompts is called depending on the above menu field choice
					}
					else {
						break;
					}
			} while(true);
		}
		System.out.println("\n" + "(!) Exiting Edit Product Section");
	}
	
	/**
	 * Once the field is chosen and the updated field is user inputted, the following unique instance is updated without having
	 * to update references
	 * @param selectedPro - unique instance
	 * @param choice - the field chosen to update
	 * @param update - the desired user inputted update for the chosen field
	 */
	private static void updateProductField(CProduct selectedPro, int choice, String update) {   
		try {
			switch(choice) {
			case 1:
				selectedPro.setProMake(update); //To access the instance data (due to the uml), I created a sub-class CProduct
				//to access protected mutator methods in product
				return;
			case 2:
				selectedPro.setProModel(update); 
				return;
			case 3:
				selectedPro.setProPrice(Double.parseDouble(update));
				return;
			case 4:
				selectedPro.setProQty(Integer.parseInt(update));
				return;
			case 5:
				selectedPro.setProDiscontinued(Integer.parseInt(update) == 1);
				return;
			}
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
	}
	
	/**
	 * Point 1 - Deletion of Product: This method presents a menu of unique instances to choose. Once chosen,
	 * the product is removed from all supplier arraylist references
	 */
	private static void delProduct(CProduct selectedProduct) {
			if (selectedProduct != null) {
				int count = 0;
				for(Supplier sup : suppliers) {
					if (sup.removeProduct(selectedProduct)) { //Calls the method for each supplier to remove product
						count++; //keeps count of successfully removed products
					}
				}
				System.out.println("\n" + "(+) Successfully Remove From " + count + " Suppliers");		
			}
		System.out.println("\n" + "(!) Exiting Delete Product Section");
	}
	
	private static CProduct selectProductMenu(String title) {
		if (getAllProducts().size() > 0) {
			int choice = menuProcess(new Menu(title, 
					allProStocksNameArray(getAllProducts(), 1)));	
			if(choice == getAllProducts().size() + 1) {
				return null;
			}
			return getAllProducts().get(choice - 1);
			}
		System.out.println("\n" + "(!) There are no products to select from");
		return null;
	}
	
	/**
	 * @category The Supplier Facility Section
	 * addSupplier() - Core
	 * editSupplier() - Additional
	 * delSupplier() - Additional
	 */
	public static void supplierFacility() { // Sub Menu (Supplier Facility)
		int choice;
		do {
			choice = menuProcess(new Menu("Supplier Facility", 
					new String[] {"Add Supplier", "Modify Supplier", "Delete Supplier", "Go Back"}));		
		
			//Choice Processing
			switch(choice) {
			case 1: addSupplier();
					break;
		
			case 2: editSupplier(selectSupplierMenu("Choose a Supplier to Edit"));
					break;
		
			case 3: delSupplier(selectSupplierMenu("Choose a Supplier to Delete"));
					break;
			}
		
		} while(choice != 4);
		System.out.println("\n" + "(!) Exiting Supplier Facility");
	}
	
	/**
	 * This is core functionality seen in part01, however, like addProduct, the supPrompts method was reused
	 */
	private static void addSupplier() {
		try {
			System.out.println("\n" + "Add New Supplier Section");
			System.out.println("------------------------" + "\n");
		
			String supName = supPrompts(1);
			Address address;
			
			address = new Address(Integer.parseInt(supPrompts(2)), supPrompts(3), 
					supPrompts(4), supPrompts(5), supPrompts(6));
			
			int regionNum = menuProcess(new Menu("Select the Region of the Supplier", 
					new String[] {"United Kingdom", "Europe", "Outside the EU"}));
			suppliers.add(new Supplier(supName, address, Region.values()[regionNum - 1]));
			
			System.out.println("\n" + "(+) Successfully Added New Supplier!");
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
	
	}
	
	/**
	 * Additional functionality,
	 * @param selectedSup
	 */
	private static void editSupplier(Supplier selectedSup) {
		if (selectedSup != null) {		
			do {
				Address address = Utility.parseAddressStr(selectedSup.getSupAddress());
				if (address != null) {
					int choice = menuProcess(new Menu("Choose A Property To Edit",
							new String[] {
									"Supplier Name: " + selectedSup.getSupName(), 
									"Street Number: " + address.getBldNum(), 
									"Street Name: " + address.getBldStreet(),
									"Town Name: " + address.getBldTown(),
									"Postcode: " + address.getBldPCode(),
									"Country: " + address.getBldCountry(),
									"Region: " + selectedSup.getSupRegion().getRegionAsString(),
	 								"Go Back"}));
					if(choice != 8) {
						updateSupplierField(selectedSup, choice, supPrompts(choice));
					}
					else {
						break;
					}
				}
			} while (true);	
		}
		System.out.println("\n" + "(!) Exiting Edit Supplier Section");
	}
	
	/**
	 * Used in adding supplier, but mainly for editing specific supplier fields one at time
	 * @param choice
	 * @return
	 */
	private static String supPrompts(int choice) {
		switch(choice) {
		case 1:
			return Menu.getStringPrompt("Enter Supplier Name: ");
		case 2:
			return Menu.getIntPrompt("Enter Street Number: ", 1); //Street number must be a number of at least 1
		case 3:
			return Menu.getStringPrompt("Enter Street Name: ");
		case 4:
			return Menu.getStringPrompt("Enter Town Name: ");
		case 5:
			return Menu.getStringPrompt("Enter Postcode: ");
		case 6:
			return Menu.getStringPrompt("Enter Country: ");
		case 7:
			return String.valueOf(menuProcess(new Menu("Select the Region of the Supplier", 
					new String[] {"United Kingdom", "Europe", "Outside the EU"})));	
		}
		return null;
	}
	
	private static void updateSupplierField(Supplier selectedSup, int choice, String update) {
		try {
			Address supAddress = Utility.parseAddressStr(selectedSup.getSupAddress());
			switch(choice) {
			case 1:
				selectedSup.setSupName(update);
				break;
			case 2:
				supAddress.setBldNum(Integer.parseInt(update));
				selectedSup.setSupAddress(supAddress.getFullAddress());
				break;
			case 3:
				supAddress.setBldStreet(update);
				selectedSup.setSupAddress(supAddress.getFullAddress());
				break;
			case 4:
				supAddress.setBldTown(update);
				selectedSup.setSupAddress(supAddress.getFullAddress());
				break;
			case 5:
				supAddress.setBldPCode(update);
				selectedSup.setSupAddress(supAddress.getFullAddress());
				break;
			case 6:
				supAddress.setBldCountry(update);
				selectedSup.setSupAddress(supAddress.getFullAddress());
				break;
			case 7:
				selectedSup.setSupRegion(Region.values()[Integer.parseInt(update) - 1]);
				break;
			}
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
	}
	
	/**
	 * Additional functionality
	 * @param supplier
	 */
	private static void delSupplier(Supplier supplier) {
		if (supplier != null) {
			suppliers.remove(supplier); //Change to Binary Search?
			
			System.out.println("\n" + "(-) Successfully Deleted Supplier (Supcode: " +
			supplier.getSupCodeNum() + ")");
		}
		System.out.println("\n" + "(!) Exiting Delete Product Section");
	}
	
	/**
	 * Used to facilitate edit and delete 
	 * @param title
	 * @return
	 */
	
	private static Supplier selectSupplierMenu(String title) {
		if (suppliers.size() > 0) {
			int choice = menuProcess(new Menu(title, 
					supplierNameArray(suppliers, "Go Back")));	
			if(choice == suppliers.size() + 1) {
				return null;
			}
			return suppliers.get(choice - 1);	
			}
		System.out.println("\n" + "(!) There are no suppliers to select from");
		return null;
	}
	
	/**
	 * Point 4 - Reading data from file
	 * @return Suppliers read from import are assigned in main method
	 */
	private static ArrayList<Supplier> importFromFile() {
		ArrayList<Supplier> fileSuppliers = new ArrayList<Supplier>();
		try {
			Scanner fileScanner = new Scanner(new File(CSV_FILE));
			boolean onSupSection = true;

			while(fileScanner.hasNextLine()) {
				String[] parts = fileScanner.nextLine().split(",");	
				
				if (parts[0].compareTo("supCode") == 0) {
					 continue; // skips to next loop
				}
				else if (parts[0].compareTo("proCode") == 0){
					onSupSection = false;
					continue; 
				}
				if(onSupSection) {
					//Supplier Section
					Address supAddress = new Address(Integer.parseInt(parts[2].trim()), 
							parts[3].trim(), parts[4].trim(), parts[5].trim(), parts[6].trim());
					Supplier supObj = new Supplier(parts[1].trim(), supAddress, Region.valueOf(parts[7].trim()));
					fileSuppliers.add(supObj);
				}
				else {
					//Product Section
					CProduct newProduct = new CProduct(parts[1].trim(), parts[2].trim(), Double.parseDouble(parts[3].trim()), Integer.parseInt(parts[4].trim()), Boolean.parseBoolean(parts[5].trim()));
					String[] supIndex = parts[6].split(";");
					if(Integer.parseInt(supIndex[0]) != -1) {
						for(String i : supIndex) {
							fileSuppliers.get(Integer.parseInt(i) - 1).addProduct(newProduct);
						}
					}
				}
			}
			fileScanner.close();
			return fileSuppliers;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<Supplier>(); 
		} catch (Exception exp) {
			System.out.println("\n" + exp.getMessage() + "\n");
			return new ArrayList<Supplier>(); 
		}

	}
	
	/**
	 * Point 5 - writing to files
	 * @return Boolean so that application can't be closed if not saved 
	 */
	private static Boolean exportToFile() {
		try {
			PrintWriter filePrinter = new PrintWriter(CSV_FILE);
			
			//Supplier Section
			filePrinter.println("supCode,supName,bldNum,bldStreet,bldTown,bldPCode, "
					+ "bldCountry,supRegion"); //Header
			for(Supplier sup : suppliers) {
				filePrinter.println(sup.getSupCodeNum() + "," + sup.getSupName() + "," 
									+ sup.getSupAddress() + "," + sup.getSupRegion());
			}
		
			//Product Section
			filePrinter.println("proCode,proMake,proModel,proPrice,proQtyAvailable, "
					+ "proDiscontinued,supList"); //Header
			
			for(CProduct pro : getAllProducts()) {
				String supIndexList = "";
				for(int supIndex = 0; supIndex < suppliers.size(); supIndex++) {
					for(int i = 0; i < suppliers.get(supIndex).getSupProducts().length; i++) {
						if(suppliers.get(supIndex).getSupProducts()[i].getProCodeNum() == pro.getProCodeNum()) {
							supIndexList += (supIndex + 1) + ";";
						}
					}
				}
				filePrinter.println(pro.getProCodeNum() + "," + pro.getProMake() + ","
				+ pro.getProModel() + "," + pro.getProPrice() + "," + pro.getProQty() + ","
				+ pro.getDiscontinued() + "," + supIndexList); 
			}
			
			filePrinter.close();
			return true;
		} catch (FileNotFoundException file) {
			System.out.println("\n" + "(!) Error: " + file.toString());
		} 
		return false;
	}
}
