package mvf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import mvf.entities.Product;

public class ProductManager {
	public static Product searchByProdId(ArrayList<Product> prodList, String prodID) {
		for (Product product : prodList) {
			if (product.getProdId().equals(prodID)) {
				return product;
			}
		}
		return null;
	}

	public static void addProduct(ArrayList<Product> prodList) {
		Scanner console = new Scanner(System.in);
		System.out.println("Please enter Product ID:");
		// id
		String prodId = console.nextLine();
		while (!validateProdIdPK(prodList, prodId)) {
			System.out.println("Please enter Product ID:");
			prodId = console.nextLine();
		}

		// name
		System.out.println("Please enter Product Name:");
		String prodName = console.nextLine();
		while (!validateProdName(prodName)) {
			System.out.println("Please enter Product Name:");
			prodName = console.nextLine();
		}

		// packing type
		System.out.println("Please enter Product Packing Type(sale unit):");
		String packingType = console.nextLine();
		while (!validatePackingType(packingType)) {
			System.out.println("Please enter Product Packing Type:");
			packingType = console.nextLine();
		}

		// qty
		System.out.println("Please enter Product Quantity:");
		String strQty = console.nextLine();
		while (!validateQTY(strQty)) {
			System.out.println("Please enter Product Price:");
			strQty = console.nextLine();
		}
		double prodQty = Double.parseDouble(strQty);

		// price
		System.out.println("Please enter Product Price:");
		String strPrice = console.nextLine();
		while (!validatePrice(strPrice)) {
			System.out.println("Please enter Product Price:");
			strPrice = console.nextLine();
		}
		double prodPrice = Double.parseDouble(strPrice);

		// shelf life
		System.out.println("Please enter Product Shelf Life: (format: xx (days))");
		String strDays = console.nextLine();
		while (!validateShelfLife(strDays)) {
			strDays = console.nextLine();
		}
		int days = Integer.parseInt(strDays);
		Date shelfLife = new Date(System.currentTimeMillis() + days * 1000 * 60 * 60 * 24);

		// source
		System.out.println("Please enter Product Source:(local or overseas)");
		String prodSource = console.nextLine();
		while (!validateSource(prodSource)) {
			System.out.println("Please enter Source:");
			prodSource = console.nextLine();
		}
		prodList.add(new Product(prodId, prodName, packingType, prodQty, prodPrice, shelfLife, prodSource));
	}

	public static void removeProduct(ArrayList<Product> prodList) {
		System.out.println("You want to remove product by No. or Product ID?");
		System.out.println("1. By NO.");
		System.out.println("2. By ID");
		Scanner console = new Scanner(System.in);
		String option = console.nextLine();
		while (!option.equals("1") && !option.equals("2")) {
			System.out.println("You can only choose 1 or 2");
			option = console.nextLine();
		}
		if (option.equals("1")) {
			System.out.println("Enter the No . of product which you want to remove:");
			String choice = console.nextLine();
			while (!choice.matches("[0-9]{1,}")
					|| !(Integer.parseInt(choice) <= prodList.size() && Integer.parseInt(choice) >= 1)) {
				// need to be updated
				System.out.println("Please enter the number of product");
				choice = console.nextLine();
			}
			prodList.remove(Integer.parseInt(choice) - 1);
		} else {
			System.out.println("Enter the Product Id which you want to remove:");
			Product product = searchByProdId(prodList, console.nextLine());
			while (product == null) {
				System.out.println("Re-enter the Product ID");
				product = searchByProdId(prodList, console.nextLine());
			}
			prodList.remove(product);
		}
	}

	public static void editProduct(ArrayList<Product> prodList) {
		System.out.println("You want to edit product by No. or Product ID?");
		System.out.println("1. By NO.");
		System.out.println("2. By ID");
		Scanner console = new Scanner(System.in);
		String option = console.nextLine();
		while (!option.equals("1") && !option.equals("2")) {
			System.out.println("You can only choose 1 or 2");
			option = console.nextLine();
		}
		Product product = new Product();
		if (option.equals("1")) {
			System.out.println("Enter the No . of product which you want to edit:");
			String choice = console.nextLine();
			while (!choice.matches("[0-9]{1,}")
					|| !(Integer.parseInt(choice) <= prodList.size() && Integer.parseInt(choice) >= 1)) {
				System.out.println("Please enter the number of product");
				choice = console.nextLine();
			}
			product = prodList.get(Integer.parseInt(choice) - 1);

		} else {
			System.out.println("Enter the Product Id which you want to edit:");
			product = searchByProdId(prodList, console.nextLine());
			while (product == null) {
				System.out.println("Re-enter the Product ID");
				product = searchByProdId(prodList, console.nextLine());
			}
		}
		// enter new data
		System.out.println("Product ID is :" + product.getProdId());
		System.out.println("Please enter new Product ID:");
		// id
		String prodId = console.nextLine();
		while (!validateProdIdPK(prodList, prodId)) {
			System.out.println("Please enter Product ID:");
			prodId = console.nextLine();
		}
		// name
		System.out.println("Product Name is :" + product.getProdName());
		System.out.println("Please enter Product Name:");
		String prodName = console.nextLine();
		while (!validateProdName(prodName)) {
			System.out.println("Please enter Product Name:");
			prodName = console.nextLine();
		}

		// packing type
		System.out.println("Product Packing Type is :" + product.getSaleUnit());
		System.out.println("Please enter Product Packing Type:");
		String packingType = console.nextLine();
		while (!validatePackingType(packingType)) {
			System.out.println("Please enter Product Packing Type:");
			packingType = console.nextLine();
		}

		// qty
		System.out.println("Product Quantity is :" + product.getProdQty());
		System.out.println("Please enter Product Quantity:");
		String strQty = console.nextLine();
		while (!validateQTY(strQty)) {
			System.out.println("Please enter Product Price:");
			strQty = console.nextLine();
		}
		double prodQty = Double.parseDouble(strQty);

		// price
		System.out.println("Product Price is :" + product.getProdPrice());
		System.out.println("Please enter Product Price:");
		String strPrice = console.nextLine();
		while (!validatePrice(strPrice)) {
			System.out.println("Please enter Product Price:");
			strPrice = console.nextLine();
		}
		double prodPrice = Double.parseDouble(strPrice);

		// shelf life
		System.out.println("Product Shelf life is :" + product.getBestBefore());
		System.out.println("Please enter Product Shelf Life: (format: xx (days))");
		String strDays = console.nextLine();
		while (!validateShelfLife(strDays)) {
			strDays = console.nextLine();
		}
		int days = Integer.parseInt(strDays);
		Date shelfLife = new Date(System.currentTimeMillis() + days * 1000 * 60 * 60 * 24);

		// source
		System.out.println("Product Source is :" + product.getOrignalPlace());
		System.out.println("Please enter Product Source:(local or overseas)");
		String prodSource = console.nextLine();
		while (!validateSource(prodSource)) {
			System.out.println("Please enter Source:");
			prodSource = console.nextLine();
		}
		// update info
		product.setProdId(prodId);
		product.setProdName(prodName);
		product.setSaleUnit(packingType);
		product.setProdQty(prodQty);
		product.setProdPrice(prodPrice);
		product.setBestBefore(shelfLife);
		product.setOrignalPlace(prodSource);
	}

	public static void viewAllProduct(ArrayList<Product> prodList) {
		for (Product product : prodList) {
			System.out.println("Product " + (prodList.indexOf(product) + 1) + " : ");
			System.out.println(product);
			System.out.println();
		}
	}

	private static boolean validateProdIdPK(ArrayList<Product> prodList, String prodId) {
		if (prodId.length() != 4) {
			System.out.println("Product ID should be 4 Characters");
			return false;
		}
		for (Product product : prodList) {
			if (product.getProdId().equals(prodId)) {
				System.out.println("Product ID has been used");
				return false;
			}
		}
		return true;
	}

	private static boolean validateProdName(String prodName) {
		if (prodName.length() > 20 || prodName.length() < 1) {
			System.out.println("Product Name should be in 1-20 Characters");
			return false;
		}
		return true;
	}

	private static boolean validateShelfLife(String strDays) {
		char[] charArray = strDays.toCharArray();
		for (char c : charArray) {
			if (!Character.isDigit(c)) {
				System.out.println("Please follow the format:xx(days)");
				return false;
			}
		}
		return true;
	}

	private static boolean validateQTY(String strQty) {
		if (strQty.matches("[0-9]{1,}.{0,1}[0-9]{0,}")) {
			return true;
		}
		System.out.println("Product Quantity should be the double datatype");
		return false;
	}

	private static boolean validatePrice(String strPrice) {
		if (strPrice.matches("[0-9]{1,}.{0,1}[0-9]{0,}")) {
			return true;
		}
		System.out.println("Product Price should be the double datatype");
		return false;
	}

	private static boolean validatePackingType(String packingType) {
		if (packingType.length() < 1 || packingType.length() > 20) {
			System.out.println("Product Packing Type should be 1-20 characters");
			return false;
		}
		return true;
	}

	private static boolean validateSource(String source) {
		if (source.equals("overseas") || source.equals("local")) {
			return true;
		}
		System.out.println("source can only be OVERSEAS or LOCAL");
		return false;
	}
}
