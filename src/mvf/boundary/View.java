package mvf.boundary;

import java.text.SimpleDateFormat;
import java.util.Date;

public class View {
	public void welcome() {
		System.out.println("+==================================================+");
		System.out.println("|                                                  |");
		System.out.println("|         Monash Vegetable & Fruit System          |");
		System.out.println("|                                                  |");
		System.out.println("+==================================================+");
	}

	public void loginView(String account) {
		Date date = new Date(System.currentTimeMillis());
		String timeStr = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
		System.out.println("====================================================");
		System.out.println("                                                  ");
		System.out.println("\tWelcome to MVF");
		System.out.println("\tHi, " + account + "!");
		System.out.println("\tCurrent time:" + timeStr);
	}

	public void logoutView(String account) {
		Date date = new Date(System.currentTimeMillis());
		String timeStr = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
		System.out.println("====================================================");
		System.out.println("                                                  ");
		System.out.println("\tLogout successfully!");
		System.out.println("\tSee you soon, " + account + "!");
		System.out.println("\tCurrent time:" + timeStr);
	}

	public void menuForOwner() {
		System.out.println("====================================================");
		System.out.println();
		System.out.println("Menu for owner:");
		System.out.println();
		System.out.println("1. Add Product");
		System.out.println("2. View Product");
		System.out.println("3. Edit Product");
		System.out.println("4. Remove Product");
		System.out.println("5. Remove Customer");
		System.out.println("6. View Order");
		System.out.println("7. Donate");
		System.out.println("x. Logout");
		System.out.println();
	}

	public void menuForCustomer() {
		System.out.println("====================================================");
		System.out.println();
		System.out.println("Menu for Customer:");
		System.out.println();
		System.out.println("1. View Product");
		System.out.println("2. Start Shopping");
		System.out.println("3. View My Order");
		System.out.println("4. Unregister yourself");
		System.out.println("x. Logout");
		System.out.println();
	}

	public void loginView() {
		System.out.println("\t1. Already have an Account, login now");
		System.out.println("\t2. No Account, register as a customer");
	}

	public void cartMenuForCustomer() {
		System.out.println("====================================================");
		System.out.println();
		System.out.println("Menu for Cart Operation:");
		System.out.println();
		System.out.println("1. Add to Cart");
		System.out.println("2. Remove Product from Cart");
		System.out.println("3. View Cart");
		System.out.println("4. Generate Order");
		System.out.println("x. Exit Cart");
		System.out.println();
	}

	public void startLogin() {
		System.out.println("====================================================");
		System.out.println("Start login...");
	}
}
