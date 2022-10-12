package part01;

public class TestAddressApp {
	private static Address testAddress;

	public static void main(String[] args) {
		//Unit Tests for Product - each method correlates to each row in testing document (named respectively)
		try {
			System.out.println("Stored Address: ");	
			testAddress = new Address(10, "Victory Street", "Belfast", "BT1 0TN", "Northern Ireland");
			System.out.println(testAddress.getFullAddress() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TCase_16();
		TCase_17();
		TCase_18();
		TCase_19();
		TCase_20();
		TCase_21();
		TCase_22();
		TCase_23();
		TCase_24();
		TCase_25();
		TCase_26();
		TCase_27();
		TCase_28();
		TCase_29();
		TCase_30();
	}
	
	/**
	 * @category Unit Testing - Address Class
	 */
	private static void TCase_16() {
		try {
			System.out.println("TCase_16: ");
			System.out.println("Previous Building Num: " + testAddress.getBldNum());	
			testAddress.setBldNum(102);
			System.out.println("Changed to: " + testAddress.getBldNum() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
		
	}
	
	private static void TCase_17() {
		try {
			System.out.println("TCase_17: ");
			System.out.println("Previous Building Num: " + testAddress.getBldNum());	
			testAddress.setBldNum(2);
			System.out.println("Changed to: " + testAddress.getBldNum() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_18() {
		try {
			System.out.println("TCase_18: ");
			System.out.println("Previous Building Num: " + testAddress.getBldNum());	
			testAddress.setBldNum(1);
			System.out.println("Changed to: " + testAddress.getBldNum() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());		}	
	}
	
	private static void TCase_19() {
		try {
			System.out.println("TCase_17: ");
			System.out.println("Previous Building Num: " + testAddress.getBldNum());	
			System.out.println("Test Data: 0");	
			testAddress.setBldNum(0);
			System.out.println("Changed to: " + testAddress.getBldNum() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Building Number Remains:: " + testAddress.getBldNum() + "\n");
		}	
	}
	
	private static void TCase_20() {
		try {
			System.out.println("TCase_20: ");
			System.out.println("Previous Building Num: " + testAddress.getBldNum());	
			System.out.println("Test Data: -105");	
			testAddress.setBldNum(-105);
			System.out.println("Changed to: " + testAddress.getBldNum() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Building Number Remains:: " + testAddress.getBldNum() + "\n");
		}	
	}
	
	private static void TCase_21() {
		try {
			System.out.println("TCase_21: ");
			System.out.println("Previous Street Name: " + testAddress.getBldStreet());	
			System.out.println("Test Data: Christmas");	
			testAddress.setBldStreet("Christmas");
			System.out.println("Changed to: " + testAddress.getBldStreet() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_22() {
		try {
			System.out.println("TCase_22: ");
			System.out.println("Previous Street Name: " + testAddress.getBldStreet());	
			System.out.println("Test Data: [Empty String]");	
			testAddress.setBldStreet("");
			System.out.println("Changed to: " + testAddress.getBldStreet() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Street Name Remains: " + testAddress.getBldStreet() + "\n");

		}	
	}
	
	private static void TCase_23() {
		try {
			System.out.println("TCase_23: ");
			System.out.println("Previous Town Name: " + testAddress.getBldTown());	
			System.out.println("Test Data: Lazy Town");	
			testAddress.setBldTown("Lazy Town");
			System.out.println("Changed to: " + testAddress.getBldTown() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_24() {
		try {
			System.out.println("TCase_24: ");
			System.out.println("Previous Town Name: " + testAddress.getBldTown());	
			System.out.println("Test Data: [Empty String]");	
			testAddress.setBldTown("");
			System.out.println("Changed to: " + testAddress.getBldTown() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Town Name Remains: " + testAddress.getBldTown() + "\n");
		}	
	}
	
	private static void TCase_25() {
		try {
			System.out.println("TCase_25: ");
			System.out.println("Previous Postcode: " + testAddress.getBldPCode());	
			System.out.println("Test Data: BT6 666");	
			testAddress.setBldPCode("BT6 666");
			System.out.println("Changed to: " + testAddress.getBldPCode() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());

		}	
	}
	
	private static void TCase_26() {
		try {
			System.out.println("TCase_26: ");
			System.out.println("Previous Postcode: " + testAddress.getBldPCode());	
			System.out.println("Test Data: [Empty String]");	
			testAddress.setBldPCode("");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Country Name Remains: " + testAddress.getBldPCode() + "\n");

		}	
	}
	
	private static void TCase_27() {
		try {
			System.out.println("TCase_27: ");
			System.out.println("Previous Street Name: " + testAddress.getBldCountry());	
			System.out.println("Test Data: Russia");	
			testAddress.setBldCountry("Russia");
			System.out.println("Changed to: " + testAddress.getBldCountry() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());

		}	
	}
	
	private static void TCase_28() {
		try {
			System.out.println("TCase_28: ");
			System.out.println("Previous Street Name: " + testAddress.getBldCountry());	
			System.out.println("Test Data: [Empty String]");	
			testAddress.setBldCountry("");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Street Name Remains: " + testAddress.getBldCountry() + "\n");

		}	
	}
	
	private static void TCase_29() {
		try {
			System.out.println("TCase_29: ");
			System.out.println(testAddress.getBldNum());
			System.out.println(testAddress.getBldStreet());
			System.out.println(testAddress.getBldTown());
			System.out.println(testAddress.getBldPCode());
			System.out.println(testAddress.getBldCountry() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_30() {
		try {
			System.out.println("TCase_29: ");
			System.out.println(testAddress.getFullAddress() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}

}
