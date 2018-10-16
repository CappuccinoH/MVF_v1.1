package mvf.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import mvf.boundary.View;
import mvf.controller.utility.FileIO;
import mvf.entities.Customer;
import mvf.entities.Order;
import mvf.entities.OrderDetail;
import mvf.entities.Owner;
import mvf.entities.PaymentDetail;
import mvf.entities.ProdQtyPair;
import mvf.entities.Product;
import mvf.entities.ShoppingCart;
import mvf.entities.User;

public class Controller {
	private ArrayList<User> userList;
	private ArrayList<Product> prodList;
	private ArrayList<Order> orderList;

	private ArrayList<ShoppingCart> cartList;

	private ArrayList<OrderDetail> orderDetailList;
	private ArrayList<PaymentDetail> paymentList;

	public Controller() {
		super();
		userList = new ArrayList<User>();
		prodList = new ArrayList<Product>();
		orderList = new ArrayList<Order>();
		cartList = new ArrayList<ShoppingCart>();
		orderDetailList = new ArrayList<OrderDetail>();
		paymentList = new ArrayList<PaymentDetail>();
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public ArrayList<Product> getProdList() {
		return prodList;
	}

	public void setProdList(ArrayList<Product> prodList) {
		this.prodList = prodList;
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public ArrayList<ShoppingCart> getCartList() {
		return cartList;
	}

	public void setCartList(ArrayList<ShoppingCart> cartList) {
		this.cartList = cartList;
	}

	public ArrayList<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(ArrayList<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public ArrayList<PaymentDetail> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(ArrayList<PaymentDetail> paymentList) {
		this.paymentList = paymentList;
	}

	public void saveProd() {
		if (prodList != null) {
			FileIO fileIO = new FileIO("Product.txt");
			StringBuilder sb = new StringBuilder();
			if (prodList.size() != 0) {
				for (Product prod : prodList) {
					String prodId = prod.getProdId();
					String prodName = prod.getProdName();
					String saleUnit = prod.getSaleUnit();
					String prodQty = String.valueOf(prod.getProdQty());
					String prodPrice = String.valueOf(prod.getProdPrice());
					String bestBefore = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(prod.getBestBefore());
					String prodSource = prod.getOrignalPlace();
					sb.append(prodId + "," + prodName + "," + saleUnit + "," + prodQty + "," + prodPrice + ","
							+ bestBefore + "," + prodSource);
					if (prodList.indexOf(prod) != prodList.size() - 1) {
						sb.append("\r\n");
					}
				}
			}
			fileIO.write(sb.toString());
			System.out.println("Saved " + prodList.size() + " product(s)");
		}
	}

	public void loadProd() {
		FileIO fileIO = new FileIO("Product.txt");
		if (!new File(fileIO.getFilePath()).exists()) {
			prodList = new ArrayList<Product>();
			return;
		}
		String prodInfo = fileIO.read();
		String[] prodUnit = prodInfo.split("\r\n");
		if (prodUnit.length == 0) {
			prodList = new ArrayList<Product>();
			return;
		} else if (prodUnit.length > 0 && !prodUnit[0].equals("")) {
			for (int index = 0; index < prodUnit.length; index++) {
				try {
					String[] prodArr = prodUnit[index].split(",");
					String prodId = prodArr[0];
					String prodName = prodArr[1];
					String saleUnit = prodArr[2];
					double prodQty = Double.valueOf(prodArr[3]);
					double prodPrice = Double.valueOf(prodArr[4]);
					Date bestBefore = new Date();
					bestBefore = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(prodArr[5]);
					String orignalPlace = prodArr[6];
					prodList.add(new Product(prodId, prodName, saleUnit, prodQty, prodPrice, bestBefore, orignalPlace));
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("Date format error, initial product failed");
				}
			}
		}
	}

	public void saveUser() {
		if (userList != null) {
			FileIO fileIO = new FileIO("User.txt");
			StringBuilder sb = new StringBuilder();
			for (User user : userList) {
				String account = user.getAccount();
				String password = user.getPassword();
				String status = user.getStatus();
				sb.append(account + "," + password + "," + status);
				if (status.equals("C")) {
					Customer customer = (Customer) user;
					int cartId = customer.getShoppingCart().getCartId();
					sb.append("," + String.valueOf(cartId));
				}
				if (userList.indexOf(user) != userList.size() - 1) {
					sb.append("\r\n");
				}
			}
			fileIO.write(sb.toString());
			System.out.println("Saved " + userList.size() + " User(s)");
		}
	}

	public void loadUser() {
		FileIO fileIO = new FileIO("User.txt");
		if (new File(fileIO.getFilePath()).exists()) {
			String userInfo = fileIO.read();
			String[] userUnit = userInfo.split("\r\n");
			for (int index = 0; index < userUnit.length; index++) {
				String[] userArr = userUnit[index].split(",");
				if (userArr[2].equals("O")) {
					userList.add(new Owner(userArr[0], userArr[1]));
				} else if (userArr[2].equals("C")) {
					ShoppingCart searchByCart = ShoppingCartManager.searchByCart(cartList,
							Integer.parseInt(userArr[3]));
					userList.add(new Customer(userArr[0], userArr[1], searchByCart));
				}
			}
		}
	}

	public void saveCart() {
		FileIO fileIO = new FileIO("ShoppingCart.txt");
		StringBuilder sb = new StringBuilder();
		for (User user : userList) {
			if (user.getStatus().equals("C")) {
				Customer customer = (Customer) user;
				int cartId = customer.getShoppingCart().getCartId();
				sb.append(String.valueOf(cartId) + ",");
				ArrayList<ProdQtyPair> prodArray = customer.getShoppingCart().getProdArray();
				if (prodArray == null || prodArray.size() == 0) {
					sb.append("null");
				} else {
					for (ProdQtyPair pair : prodArray) {
						String prodId = pair.getProduct().getProdId();
						double quantity = pair.getQuantity();
						sb.append(prodId + ":" + String.valueOf(quantity));
						if (prodArray.indexOf(pair) != prodArray.size() - 1) {
							sb.append("|");
						}
					}
				}
				double price = customer.getShoppingCart().getPrice();
				sb.append("," + String.valueOf(price));
				sb.append("\r\n");
			}
		}
		fileIO.write(sb.toString());
	}

	public void loadCart() {
		FileIO fileIO = new FileIO("ShoppingCart.txt");
		String[] cartInfo = fileIO.read().split("\r\n");
		for (String cartUnit : cartInfo) {
			if (!cartUnit.equals("")) {
				String[] cart = cartUnit.split(",");
				int cartID = Integer.parseInt(cart[0]);
				ArrayList<ProdQtyPair> arrPair = new ArrayList<ProdQtyPair>();
				if (!cart[1].equals("null")) {
					String[] p = cart[1].split("\\|");
					for (String str : p) {
						String prodID = str.split(":")[0];
						double quantity = Double.parseDouble(str.split(":")[1]);
						if (ProductManager.searchByProdId(prodList, prodID) != null) {
							arrPair.add(new ProdQtyPair(ProductManager.searchByProdId(prodList, prodID), quantity));
						}
					}
				}
				double price = Double.parseDouble(cart[2]);
				cartList.add(new ShoppingCart(cartID, arrPair, price));
			}
		}
	}

	public void saveOrder() {
		FileIO fileIO = new FileIO("Order.txt");
		StringBuilder sb = new StringBuilder();
		for (Order order : orderList) {
			String orderId = String.valueOf(order.getOrderId());
			String account = order.getCustomer().getAccount();
			String orderDetailId = String.valueOf(order.getOrderDetail().getOrderDetailId());
			String paymentID = null;
			if (order.getPaymentDetail() == null) {
				paymentID = "null";
			} else {
				paymentID = String.valueOf(order.getPaymentDetail().getPaymentId());
			}
			sb.append(orderId + "," + account + "," + orderDetailId + "," + paymentID);
			if (orderList.indexOf(order) != orderList.size() - 1) {
				sb.append("\r\n");
			}
		}
		fileIO.write(sb.toString());
		System.out.println("Saved " + orderList.size() + " Order(s)");
	}

	public void loadOrder() {
		FileIO fileIO = new FileIO("Order.txt");
		String[] orderUnit = fileIO.read().split("\r\n");
		for (String orderInfo : orderUnit) {
			String[] column = orderInfo.split(",");
			int orderID = Integer.valueOf(column[0]);
			Customer customer = UserManager.searchById(userList, column[1]);
			OrderDetail orderDetail = OrderDetailManager.searchByOrderDetailId(orderDetailList,
					Integer.parseInt(column[2]));
			PaymentDetail payment = PaymentManager.searchByPaymentId(paymentList, Integer.parseInt(column[3]));
			Order order = new Order(orderID, customer, orderDetail);
			order.setPaymentDetail(payment);
			orderList.add(order);
		}
	}

	public void saveOrderDetail() {
		FileIO fileIO = new FileIO("OrderDetail.txt");
		StringBuilder sb = new StringBuilder();
		for (Order order : orderList) {
			int orderDetailId = order.getOrderDetail().getOrderDetailId();
			sb.append(String.valueOf(orderDetailId) + ",");
			ArrayList<ProdQtyPair> prodArray = order.getOrderDetail().getProdArray();
			if (prodArray.size() == 0) {
				sb.append("null");
			} else {
				for (ProdQtyPair pair : prodArray) {
					String prodId = pair.getProduct().getProdId();
					double quantity = pair.getQuantity();
					sb.append(prodId + ":" + String.valueOf(quantity));
					if (prodArray.indexOf(pair) != prodArray.size() - 1) {
						sb.append("|");
					}
				}
			}
			if (orderList.indexOf(order) != orderList.size() - 1) {
				sb.append("\r\n");
			}
		}
		fileIO.write(sb.toString());
	}

	public void loadOrderDetail() {
		FileIO fileIO = new FileIO("OrderDetail.txt");
		String detailInfo = fileIO.read();
		String[] detailUnit = detailInfo.split("\r\n");
		for (String unit : detailUnit) {
			String[] colunm = unit.split(",");
			int orderDetailID = Integer.parseInt(colunm[0]);
			ArrayList<ProdQtyPair> pair = new ArrayList<ProdQtyPair>();
			if (!colunm[1].equals("null")) {
				String[] cell = colunm[1].split("\\|");
				for (String s : cell) {
					String prodID = s.split(":")[0];
					double quantity = Double.parseDouble(s.split(":")[1]);
					pair.add(new ProdQtyPair(ProductManager.searchByProdId(prodList, prodID), quantity));
				}
			}
			orderDetailList.add(new OrderDetail(orderDetailID, pair));
		}
	}

	public void savePaymentDetail() {
		FileIO fileIO = new FileIO("Payment.txt");
		StringBuilder sb = new StringBuilder();
		for (Order order : orderList) {
			if (order.getPaymentDetail() != null) {
				String paymentId = String.valueOf(order.getPaymentDetail().getPaymentId());
				Date date = order.getPaymentDetail().getPaymentTime();
				String paymentTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
				String totalCost = String.valueOf(order.getPaymentDetail().getTotalCost());
				sb.append(paymentId + "," + paymentTime + "," + totalCost);
				sb.append("\r\n");
			}
		}
		fileIO.write(sb.toString());
	}

	public void loadPaymentDetail() {
		FileIO fileIO = new FileIO("Payment.txt");
		String paymentInfo = fileIO.read();
		String[] paymentUnit = paymentInfo.split("\r\n");
		for (String unit : paymentUnit) {
			if (!unit.equals("")) {
				String[] column = unit.split(",");
				int paymentID = Integer.parseInt(column[0]);
				Date time = new Date(System.currentTimeMillis());
				try {
					time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(column[1]);
				} catch (ParseException e) {
					e.printStackTrace();
					System.out.println("date format error");
				}
				double price = Double.parseDouble(column[2]);
				paymentList.add(new PaymentDetail(paymentID, time, price));
			}
		}
	}

	public void load() {
		loadProd();
		loadPaymentDetail();
		loadCart();
		loadOrderDetail();
		loadUser();
		loadOrder();
	}

	public void save() {
		saveProd();
		saveCart();
		saveOrderDetail();
		saveUser();
		savePaymentDetail();
		saveOrder();
	}

	public static void main(String[] args) throws InterruptedException {
		Controller controller = new Controller();
		View view = new View();

		controller.load();

		view.welcome();
		view.loginView();
		Scanner console = new Scanner(System.in);
		String choice = console.nextLine();
		while (true) {
			if (choice.equals("1")) {
				break;
			} else if (choice.equals("2")) {
				UserManager.removeCustomerByOwner(controller.getUserList());
			} else {
				System.out.println("Please enter 1 or 2:");
			}
			view.loginView();
			choice = console.nextLine();
		}
		view.startLogin();
		User user = UserManager.login(controller.getUserList());
		while (user == null) {
			user = UserManager.login(controller.getUserList());
		}
		view.loginView(user.getAccount());
		while (true) {
			if (user.getStatus().equals("O")) {
				view.menuForOwner();
				String option = console.nextLine();
				while (true) {
					switch (option) {
					case "1":
						ProductManager.addProduct(controller.getProdList());
						break;
					case "2":
						ProductManager.viewAllProduct(controller.getProdList());
						break;
					case "3":
						ProductManager.editProduct(controller.getProdList());
						break;
					case "4":
						ProductManager.removeProduct(controller.getProdList());
						break;
					case "5":
						UserManager.removeCustomerByOwner(controller.getUserList());
						break;
					case "6":
						OrderManager.searchAllOrderByOwner(controller.getOrderList());
						break;
					case "x":
						view.logoutView(user.getAccount());
						controller.save();
						System.exit(0);
						break;
					default:
						System.out.println("Please enter correct option");
					}
					System.out.println("press ENTER to continue...");
					console.nextLine();
					view.menuForOwner();
					option = console.nextLine();
				}
			} else if (user.getStatus().equals("C")) {
				Customer customer = (Customer) user;
				view.menuForCustomer();
				String option = console.nextLine();
				while (true) {
					switch (option) {
					case "1":
						ProductManager.viewAllProduct(controller.getProdList());
						break;
					case "2":
						ShoppingCartManager.addToCart(customer.getShoppingCart(), controller.getProdList(),
								controller.getOrderList(), controller.orderDetailList, controller.getPaymentList(),
								controller.getUserList());
						break;
					case "3":
						OrderManager.searchOrderByCustomer(controller.getOrderList(), customer);
						break;
					case "4":
						UserManager.removeCustomerByCustomer(controller.getUserList(), customer);
						break;
					case "x":
						view.logoutView(user.getAccount());
						controller.save();
						System.exit(0);
						break;
					default:
						System.out.println("Please enter correct option");
					}
					System.out.println("press ENTER to continue...");
					view.menuForCustomer();
					option = console.nextLine();
				}
			} else {
				System.out.println("Login Error");
			}
		}
	}
}
