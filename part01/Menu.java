package part01;

import java.util.Scanner;

public class Menu {
	private String options[];
	private String title;
	private Scanner input;
	
	private static Scanner staticInput;
	
	public Menu(String title, String options[]) {
		this.title = title;
		copyOptions(options);
		input = new Scanner(System.in);
	}
	
	public int processMenuChoice() {
		do {
			try {
				display();
				System.out.print("Enter choice: ");
				
				if(input.hasNextLine()) {
					String inputStr = input.nextLine(); 
					int choice = Integer.parseInt(inputStr);
						
					if (choice > 0 && choice < options.length + 1) {
						return choice;
					}
					System.out.println("\n"+ "(!) That number entry is invalid. Try again!");
				}
			
			} catch (Exception e) {
			System.out.println("\n"+ "(!) That entry is invalid. Please Enter a Number!");
			}
		}while(true);
	}
	
	public void display() {
		if (title != null && options != null) {

			System.out.println("\n" + title);
			for(int i = 0; i < title.length(); i++) {
				System.out.print("-");
			}
			System.out.println("\n");

			int count = 1;
			for(String str : options) {
				System.out.println(count+". " + str);
				count++;
			}
			System.out.println();
		}
		else {
			System.out.println("Menu not defined.");
		}
	}
	
	private void copyOptions(String data[]) {
		if ( data != null) {
			options = new String[data.length];
			
			for(int index = 0; index < data.length; index++) {
				options[index] = data[index];
			}
		}
		else {
			options = null;
		}
	}
	
	//Input Handling
	public static String getStringPrompt(String prompt) {
		String inputStr = "";
		try {
			do {
				staticInput = new Scanner(System.in);
				System.out.print(prompt);
				
				if(staticInput.hasNextLine()) {
					inputStr = staticInput.nextLine();
					
					if(!inputStr.isEmpty()) {
						return inputStr.trim();
					}
					System.out.println("\n" + "(!) Please Enter a String" +"\n");
				} 
			}while (inputStr.isEmpty());

		}catch(Exception ex) {
			staticInput.nextLine();
		}
		return inputStr;
	}
	
	public static int getIntPrompt(String prompt, int lowestInt) {
		do {
			int inputInt = -1;
			try {
				do {
					staticInput = new Scanner(System.in);
					System.out.print(prompt);
					
					if(staticInput.hasNextLine()) {
						String inputStr = staticInput.nextLine();
						inputInt = Integer.parseInt(inputStr.trim());
					} 
				}while (!validInt(inputInt, lowestInt));
				return inputInt;
		
			}catch(Exception ex) {
				System.out.println("\n" + "(!) Please enter a valid, whole number of atleast "+ lowestInt +"\n");
			}
		}while (true);

	}
	
	public static Double getPricePrompt(String prompt) {
		do {
			double price = -1.00;
			try {
				do {
					staticInput = new Scanner(System.in);
					System.out.print(prompt + "£");
					
					if(staticInput.hasNextLine()) {
						String inputStr = staticInput.nextLine();
						price = Double.parseDouble(inputStr.trim());
					} 
					
				}while (!validPriceDecimalAmt(price));
				return price;
		
			}catch(Exception ex) {
				System.out.println("\n" + "(!) Please enter a valid decimal number!" + "\n");
			}
		} while(true);
	}
	
	private static Boolean validPriceDecimalAmt (Double price) {
		if(price >= 0.00) {
			String[] parts = Double.toString(price).split("\\.");
			if(parts[1].length() > 2) {
				System.out.println("\n" + "(!) Enter a double value with no more than 2 d.p" + "\n");
				return false;
			}
			return true;
		}
		System.out.println("\n" + "(!) Please enter a positive double" +"\n");
		return false;
	}
	
	private static Boolean validInt(int num, int lowestInt) {
		if(num >= lowestInt) {
			return true;
		}
		System.out.println("\n" + "(!) Please enter a whole number of atleast "+ lowestInt +"\n");
		return false;
	}
}
