package mvf.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import mvf.boundary.View;
import mvf.entities.Customer;
import mvf.entities.Order;
import mvf.entities.OrderDetail;
import mvf.entities.PaymentDetail;
import mvf.entities.ProdQtyPair;
import mvf.entities.Product;
import mvf.entities.ShoppingCart;
import mvf.entities.User;

public class ShoppingCartManager {
	public static ShoppingCart searchByCart(ArrayList<ShoppingCart> cartList, int cartID) {
		for (ShoppingCart cart : cartList) {
			if (cart.getCartId() == cartID) {
				return cart;
			}
		}
		return null;
	}

	public static double calculatePrice(ArrayList<ProdQtyPair> prodArray) {
		double price = 0;
		for (ProdQtyPair pair : prodArray) {
			double prodPrice = pair.getProduct().getProdPrice();
			double quantity = pair.getQuantity();
			price += prodPrice * quantity;
		}
		return price;
	}

	public static void addToCart(ShoppingCart cart, ArrayList<Product> prodList, ArrayList<Order> orderList,
			ArrayList<OrderDetail> orderDetailList, ArrayList<PaymentDetail> paymentList, ArrayList<User> userList) {
		for (Product produt : prodList) {
			System.out.println(produt);
		}
		System.out.println("Here is your Cart History:\r\n");
		System.out.println(cart);
		System.out.println();
		Scanner console = new Scanner(System.in);
		while (true) {
			View view = new View();
			view.cartMenuForCustomer();
			String option = console.nextLine();
			switch (option) {
			case "1":
				System.out.println("Please enter the prod ID");
				String prodID = console.nextLine();
				while (ProductManager.searchByProdId(prodList, prodID) == null) {
					System.out.println("Please Re-enter Product ID");
					prodID = console.nextLine();
				}
				Product product = ProductManager.searchByProdId(prodList, prodID);
				System.out.println("Please enter the quantity you want (Maximum is " + product.getProdQty() + ")");
				String qty = console.nextLine();
				while (!qty.matches("[0-9]{1,}.{0,1}[0-9]{0,}") || Double.parseDouble(qty) > product.getProdQty()) {
					System.out.println("Please re-enter quantity");
					qty = console.nextLine();
				}
				double quantity = Double.parseDouble(qty);
				cart.setPrice(cart.getPrice() + product.getProdPrice() * quantity);
				product.setProdQty(product.getProdQty() - quantity);
				cart.getProdArray().add(new ProdQtyPair(product, quantity));
				break;
			case "2":
				System.out.println("Please enter the product ID");
				String id = console.nextLine();
				Product prod = ProductManager.searchByProdId(prodList, id);
				boolean flag = false;
				for (ProdQtyPair pair : cart.getProdArray()) {
					if (pair.getProduct().equals(prod)) {
						flag = true;
					}
				}
				while (prod == null || !flag) {
					System.out.println("Please re-enter the product ID");
					id = console.nextLine();
					prod = ProductManager.searchByProdId(prodList, id);
					for (ProdQtyPair pair : cart.getProdArray()) {
						if (pair.getProduct().equals(prod)) {
							flag = true;
						}
					}
				}
				System.out.println("Do you want to remove the product in the cart? Y or any other keys to cancle");
				String choice = console.nextLine();
				if (choice.equals("Y") || choice.equals("y")) {
					Iterator<ProdQtyPair> iterator = cart.getProdArray().iterator();
					while (iterator.hasNext()) {
						ProdQtyPair next = iterator.next();
						if (next.getProduct().equals(prod)) {
							iterator.remove();
							cart.setPrice(cart.getPrice() - next.getQuantity() * next.getProduct().getProdPrice());
							next.getProduct().setProdQty(next.getQuantity() + next.getProduct().getProdQty());
							System.out.println("Remove successfully");
						}
					}
				} else {
					System.out.println("Cancelled");
				}

				break;
			case "3":
				System.out.println("Here is your Cart History:\r\n");
				System.out.println(cart);
				break;
			case "4":
				System.out.println("Are you sure to generate an order and pay? Y/N");
				String options = console.nextLine();
				while (true) {
					if (options.equals("Y") || options.equals("y")) {
						Order order = new Order();
						if (orderList.size() == 0) {
							order.setOrderId(1000);
						} else {
							order.setOrderId(orderList.get(orderList.size() - 1).getOrderId() + 1);
						}
						Customer customer = new Customer();
						for (User user : userList) {
							if (user.getStatus().equals("C")) {
								Customer tempCust = (Customer) user;
								if (tempCust.getShoppingCart().getCartId() == cart.getCartId()) {
									customer = tempCust;
								}
							}
						}
						OrderDetail orderDetail = new OrderDetail();
						if (orderDetailList.size() == 0) {
							orderDetail.setOrderDetailId(1000);
						} else {
							orderDetail.setOrderDetailId(
									orderDetailList.get(orderDetailList.size() - 1).getOrderDetailId() + 1);
						}
						orderDetail.setProdArray(cart.getProdArray());
						PaymentDetail paymentDetail = new PaymentDetail();
						if (paymentList.size() == 0) {
							paymentDetail.setPaymentId(100);
						} else {
							paymentDetail.setPaymentId(paymentList.get(paymentList.size() - 1).getPaymentId() + 1);
						}
						paymentDetail.setPaymentTime(new Date(System.currentTimeMillis()));
						paymentDetail.setTotalCost(cart.getPrice());
						// set parameters
						order.setCustomer(customer);
						order.setOrderDetail(orderDetail);
						order.setPaymentDetail(paymentDetail);
						// clear cart info
						cart.setProdArray(new ArrayList<ProdQtyPair>());
						cart.setPrice(0);
						// save info into list
						orderDetailList.add(orderDetail);
						paymentList.add(paymentDetail);
						orderList.add(order);
						System.out.println("New Order:");
						System.out.println(order);
						break;
					} else if (options.equals("N") || options.equals("n")) {
						System.out.println("Cancelled");
						break;
					} else {
						System.out.println("Invaild enter");
						System.out.println("Please re-enter:");
						options = console.nextLine();
					}

				}
				break;
			case "x":
				return;
			default:
				System.out.println("Please enter correct option");
				break;
			}
		}
	}
}
