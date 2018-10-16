package mvf.controller;

import java.util.ArrayList;

import mvf.entities.Customer;
import mvf.entities.Order;

public class OrderManager {
	public static Order searchById(ArrayList<Order> orderList, int orderID) {
		for (Order order : orderList) {
			if (order.getOrderId() == orderID) {
				return order;
			}
		}
		return null;
	}

	public static void searchOrderByCustomer(ArrayList<Order> orderList, Customer customer) {
		int index = 1;
		for (Order order : orderList) {
			if (order.getCustomer().equals(customer)) {
				System.out.println("Order " + (index++) + " :");
				System.out.println(order);
			}
		}
	}

	public static void searchAllOrderByOwner(ArrayList<Order> orderList) {
		for (Order order : orderList) {
			System.out.println("Order " + (orderList.indexOf(order) + 1) + " :");
			System.out.println(order);
		}
	}
}
