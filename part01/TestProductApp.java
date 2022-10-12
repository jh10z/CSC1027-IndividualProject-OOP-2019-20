package part01;

public class TestProductApp {
	private static Product testProduct;

	public static void main(String[] args) {
		//Unit Tests for Product - each method correlates to each row in testing document (named respectively)
		try {
			System.out.println("Stored Product: ");	
			testProduct = new Product("Tesla", "Model X", 2.00, 100, false);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TCase_1();
		TCase_2();
		TCase_3();
		TCase_4();
		TCase_5();
		TCase_6();
		TCase_7();
		TCase_8();
		TCase_9();
		TCase_10();
		TCase_11();
		TCase_12();
		TCase_13();
		TCase_14();
		TCase_15();
	}
	
	/**
	 * @category Unit Testing - Product Class
	 */
	private static void TCase_1() {
		try {
			System.out.println("TCase_1: ");
			testProduct.setProPrice(2.21);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
		
	}

	private static void TCase_2() {
		try {
			System.out.println("TCase_2: ");
			testProduct.setProPrice(0.01);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
		
	}
	
	private static void TCase_3() {
		try {
			System.out.println("TCase_3: ");
			testProduct.setProPrice(0);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
		
	}
	
	private static void TCase_4() {
		try {
			System.out.println("TCase_4: " + "\n");
			System.out.println("Test Data: -0.01");
			testProduct.setProPrice(-0.01);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Double remains: " + testProduct.getProPrice() + "\n");
		}
		
	}
	
	private static void TCase_5() {
		try {
			System.out.println("TCase_5: ");
			testProduct.setProPrice(-10.01);
			System.out.println("Test Data: -10.01");
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Double remains: " + testProduct.getProPrice() + "\n");
		}	
	}
	
	private static void TCase_6() {
		try {
			System.out.println("TCase_6: ");
			testProduct.setProPrice(10);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_7() {
		try {
			System.out.println("TCase_7: ");
			testProduct.setProQty(1);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_8() {
		try {
			System.out.println("TCase_8: ");
			testProduct.setProQty(0);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_9() {
		try {
			System.out.println("TCase_9: ");
			System.out.println("Test Data: -1");
			testProduct.setProQty(-1);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Int remains: " + testProduct.getProPrice() + "\n");
		}	
	}
	
	private static void TCase_10() {
		try {
			System.out.println("TCase_10: ");
			System.out.println("Test Data: -10");
			testProduct.setProQty(-10);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Int remains: " + testProduct.getProPrice() + "\n");
		}	
	}
	
	private static void TCase_11() {
		try {
			System.out.println("TCase_12: ");
			testProduct.setProDiscontinued(true);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_12() {
		try {
			System.out.println("TCase_13: ");
			testProduct.setProDiscontinued(false);
			System.out.println(testProduct.getProDetails() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_13() {
		try {
			System.out.println("TCase_14: ");
			System.out.println(testProduct.getProCodeNum());
			System.out.println(testProduct.getProMake());
			System.out.println(testProduct.getProModel());
			System.out.println(testProduct.getProPrice());
			System.out.println(testProduct.getProQty());
			System.out.println(testProduct.getDiscontinued() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_14() {
		try {
			System.out.println("TCase_15: ");
			//testProduct is first instance so...
			System.out.println("proCode: " + testProduct.getProCodeNum() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
	
	private static void TCase_15() {
		try {
			System.out.println("TCase_16: ");
			//secondProduct - second instance created
			Product secondProduct = new Product("Tesco", "Model S", 2.42, 120, true);
			System.out.println("proCode: " + secondProduct.getProCodeNum() + "\n");
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}	
	}
}
