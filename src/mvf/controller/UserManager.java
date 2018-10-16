package mvf.controller;

import java.util.ArrayList;
import java.util.Scanner;

import mvf.entities.Customer;
import mvf.entities.Order;
import mvf.entities.OrderDetail;
import mvf.entities.PaymentDetail;
import mvf.entities.ShoppingCart;
import mvf.entities.User;

public class UserManager {

	public static Customer searchById(ArrayList<User> userList, String account) {
		for (User user : userList) {
			if (user.getStatus().equals("C") && user.getAccount().equals(account)) {
				return (Customer) user;
			}
		}
		return null;
	}

	public static User searchUserById(ArrayList<User> userList, String account) {
		for (User user : userList) {
			if (user.getAccount().equals(account)) {
				return user;
			}
		}
		return null;
	}

	public static void removeCustomerByOwner(ArrayList<User> userList) {
		for (User user : userList) {
			if (user.getStatus().equals("C")) {
				System.out.println(user);
			}
		}
		Scanner console = new Scanner(System.in);
		System.out.println("Please enter the Customer's Account which you want to remove:");
		String account = console.nextLine();
		while (UserManager.searchById(userList, account) == null) {
			System.out.println("Re-enter account:");
			account = console.nextLine();
		}
		userList.remove(UserManager.searchById(userList, account));
	}

	public static void removeCustomerByCustomer(ArrayList<User> userList, Customer customer) {
		for (User user : userList) {
			if (user.getStatus().equals("C")) {
				Customer c = (Customer) user;
				if (c.equals(customer)) {
					userList.remove(c);
				}
			}
		}
	}

	public void registerCustomer(ArrayList<User> userList, ArrayList<ShoppingCart> cartList)
			throws InterruptedException {
		System.out.println("====================================================");
		System.out.println();
		System.out.print("Start register a Customer Account for MVF");
		for (int i = 0; i < 3; i++) {
			Thread.sleep(500);
			System.out.print(".");
		}
		System.out.println("\r\n\r\nPlease enter account:");
		Scanner console = new Scanner(System.in);
		String account = console.nextLine();
		while (UserManager.searchUserById(userList, account) != null) {
			System.out.println("Account Name: " + account + " has been used! Please use another");
			account = console.nextLine();
		}
		System.out.println("Please enter password:");
		String password = console.nextLine();
		ShoppingCart shoppingCart = new ShoppingCart();
		if (cartList.size() != 0) {
			int cartId = cartList.get(cartList.size() - 1).getCartId();
			shoppingCart.setCartId(cartId + 1);
		} else {
			shoppingCart.setCartId(100);
		}
		Customer customer = new Customer(account, password);
		customer.setShoppingCart(shoppingCart);
		userList.add(customer);
		System.out.println("register successful");
	}

	public static User login(ArrayList<User> userList) {
		Scanner console = new Scanner(System.in);
		System.out.println("Please enter your account");
		String account = console.nextLine();
		System.out.println("Please enter your password");
		String password = console.nextLine();
		for (User user : userList) {
			if (user.getAccount().equals(account) && user.getPassword().equals(password)) {
				return user;
			}
		}
		System.out.println("login failed");
		return null;
	}
}
