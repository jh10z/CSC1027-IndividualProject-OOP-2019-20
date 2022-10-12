package part01;

public class TestSupplierApp {
	private static Supplier testSupplier;

	public static void main(String[] args) {
		//Unit Tests for Product - each method correlates to each row in testing document (named respectively)
		try {
			System.out.println("Stored Supplier: ");	
			Address testAddress = new Address(10, "Victory Street", "Belfast", "BT1 0TN", "Northern Ireland");
			testSupplier = new Supplier("Tesco", testAddress, Region.values()[2]);
			System.out.println(testSupplier.getSupName());
			System.out.println(testSupplier.getSupAddress());
			System.out.println(testSupplier.getSupRegion() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TCase_31();
		TCase_32();
		TCase_33();
		TCase_34();
		TCase_35();
		TCase_36();
		TCase_37();
		TCase_38();	
		TCase_39();
		TCase_40();
		TCase_41();
		TCase_42();
		TCase_43();
		TCase_44();
		TCase_45();
		TCase_46();
		TCase_47();
		TCase_48();
	}
	/**
	 * @category Integration Testing - Supplier Class (Address, Product, Region)
	 */
	private static void TCase_31() {
		try {
			System.out.println("TCase_31: ");
			System.out.println("Previous Supplier Name: " + testSupplier.getSupName());	
			testSupplier.setSupName("Tesla");
			System.out.println("Changed to: " + testSupplier.getSupName() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_32() {
		try {
			System.out.println("TCase_32: ");
			System.out.println("Previous Supplier Name: " + testSupplier.getSupName());	
			System.out.println("Test Data: [Empty String]");	
			testSupplier.setSupName("");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Supplier remains: " + testSupplier.getSupName() + "\n");
		}	
	}
	
	private static void TCase_33() {
		try {
			System.out.println("TCase_33: ");
			System.out.println("Previous Supplier Address: " + testSupplier.getSupAddress());
			testSupplier.setSupAddress("21,Fail Street,Belslow,BT2 0VN,Russia");
			System.out.println("Changed to: " + testSupplier.getSupAddress()+ "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_34() {
		try {
			System.out.println("TCase_34: ");
			System.out.println("Previous Supplier Address: " + testSupplier.getSupAddress());
			testSupplier.setSupAddress("");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Supplier address remains: " + testSupplier.getSupAddress() + "\n");
		}
	}
	
	private static void TCase_35() {
		try {
			System.out.println("TCase_35: ");
			System.out.println("Previous Region: " + testSupplier.getSupRegion());
			testSupplier.setSupRegion(Region.values()[0]);
			System.out.println("Changed to: " + testSupplier.getSupRegion() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_36() {
		try {
			System.out.println("TCase_36: ");
			System.out.println("Previous Region: " + testSupplier.getSupRegion());
			testSupplier.setSupRegion(Region.values()[1]);
			System.out.println("Changed to: " + testSupplier.getSupRegion() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_37() {
		try {
			System.out.println("TCase_37: ");
			System.out.println("Previous Region: " + testSupplier.getSupRegion());
			testSupplier.setSupRegion(Region.values()[2]);
			System.out.println("Changed to: " + testSupplier.getSupRegion() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_38() {
		try {
			System.out.println("TCase_38: ");
			System.out.println("Previous Region: " + testSupplier.getSupRegion());
			testSupplier.setSupRegion(null);
			System.out.println("Changed to: " + testSupplier.getSupRegion() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Supplier Region Remains: " + testSupplier.getSupRegion() + "\n");
		}
	}
	
	private static void TCase_39() {
		try {
			Product testProduct = new Product("Tesla", "Model X", 2.00, 100, false);
			System.out.println("TCase_39: ");
			System.out.println("Product list size: " + testSupplier.getSupProducts().length);
			testSupplier.addProduct(testProduct);
			System.out.println("Product added: " + testSupplier.getSupProducts()[0].getProMake());
			System.out.println("Product list size: " + testSupplier.getSupProducts().length + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_40() {
		try {
			System.out.println("TCase_40: ");
			System.out.println("Product list size: " + testSupplier.getSupProducts().length);
			testSupplier.addProduct(null);
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Product list size: " + testSupplier.getSupProducts().length + "\n");
		}
	}
	
	private static void TCase_41() {
		try {
			System.out.println("TCase_41: ");
			System.out.println("Product list size: " + testSupplier.getSupProducts().length);
			Product testProduct = testSupplier.getSupProducts()[0];
			if(testSupplier.removeProduct(testProduct)) {
				System.out.println("Product removed: " + testProduct.getProMake());
				System.out.println("Product list size: " + testSupplier.getSupProducts().length + "\n");
			}
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_42() {
		try {
			System.out.println("TCase_42: ");
			System.out.println("Product list size: " + testSupplier.getSupProducts().length);
			if(!testSupplier.removeProduct(null)) {
				System.out.println("Product cannot be removed");
				System.out.println("Product list size: " + testSupplier.getSupProducts().length + "\n");
			}
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_43() {
		try {
			System.out.println("TCase_43: ");
			Product testProduct = new Product("Tesla", "Model X", 2.00, 100, false);
			testSupplier.addProduct(testProduct);
			System.out.println("Product list size: " + testSupplier.getSupProducts().length);
			System.out.println(testSupplier.getProductList());
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_44() {
		try {
			System.out.println("TCase_44: ");
			System.out.println(testSupplier.getSupCodeNum());
			System.out.println(testSupplier.getSupAddress());
			System.out.println(testSupplier.getSupRegion());
			System.out.println(testSupplier.getProductList());
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_45() {
		try {
			System.out.println("TCase_45: ");
			System.out.println("Supplier ID: " + testSupplier.getSupCodeNum() + "\n");;
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_46() {
		try {
			System.out.println("TCase_46: ");
			Address testAddress = new Address(10, "Victory Street", "Belfast", "BT1 0TN", "Northern Ireland");
			Supplier testSupplierTwo = new Supplier("Victory X", testAddress, Region.values()[1]);
			System.out.println("Supplier ID: " + testSupplierTwo.getSupCodeNum() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_47() {
		try {
			System.out.println("TCase_47: ");
			Product testProduct = new Product("Tesla", "Model X", 2.00, 100, false);
			testSupplier.addProduct(testProduct);
			System.out.println("Is Array?: " + testSupplier.getSupProducts().getClass().isArray() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	private static void TCase_48() {
		try {
			System.out.println("TCase_48: ");
			Supplier testSupplierTwo = new Supplier("Victory X", null, Region.values()[1]);
			System.out.println(testSupplierTwo.getSupCodeNum());
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Object not created");
		}
	}
	

}
