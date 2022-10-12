package part02;

import java.util.ArrayList;

/**
 * This is a class which should not be instansiated - hence the abstract class declaration. This class contains usefully methods of 
 * similar concept
 * @author karha
 *
 */
public abstract class Utility {

	public static int linearSearch(int supCode, ArrayList<Supplier> suppliers) {
		for(int i = 0; i < suppliers.size(); i++) {
			if(suppliers.get(i).getSupCodeNum() == supCode) {
				return i;
			}
		}
		return -1;
	}
	
	public static CProduct[] bubbleSortProducts(CProduct[] products, boolean ascending) {
		int swaps;
		do {
			swaps = 0;
			for (int index = 0; index < products.length - 2; index++) {
				if (products[index].getProCodeNum() > products[index + 1].getProCodeNum() && ascending || products[index].getProCodeNum() < products[index + 1].getProCodeNum() && !ascending) {
					CProduct temp = products[index];
					products[index] = products[index + 1];
					products[index + 1] = temp;
					swaps++;
				}
			}
		} while (swaps > 0);
		return products;
	}
	
	public static boolean productListContains(ArrayList<CProduct> product, int proCode) {
		return -1 < indexOfProduct(product, proCode);
	}
	
	public static int indexOfProduct(ArrayList<CProduct> product, int proCode) {
		for (int i = 0; i < product.size(); i++) {
			if (proCode == product.get(i).getProCodeNum()) { //linear search
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOfSupplier(ArrayList<Supplier> supplier, int supCode) {
		for (int i = 0; i < supplier.size(); i++) {
			if (supCode == supplier.get(i).getSupCodeNum()) {
				return i;
			}
		}
		return -1;
	}
	
	public static Address parseAddressStr(String address) {
		String[] addressArray = address.split(",");
		try {
			return new Address(Integer.parseInt(addressArray[0].trim()), addressArray[1].trim(),
					addressArray[2].trim(), addressArray[3].trim(), addressArray[4].trim());
		} catch (NumberFormatException e) {
			System.out.println("\n" + e.getMessage() + "\n");
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage() + "\n");
		}
		return null;
	}

}
