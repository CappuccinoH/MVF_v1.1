package mvf.entities;

import java.util.ArrayList;

public class ShoppingCart {
	private int cartId;
	private ArrayList<ProdQtyPair> prodArray;
	private double price;

	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCart(int cartId, ArrayList<ProdQtyPair> prodArray, double price) {
		super();
		this.cartId = cartId;
		this.prodArray = prodArray;
		this.price = price;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public ArrayList<ProdQtyPair> getProdArray() {
		return prodArray;
	}

	public void setProdArray(ArrayList<ProdQtyPair> prodArray) {
		this.prodArray = prodArray;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cart ID: " + cartId + "\r\n");
		if (prodArray.size() == 0) {
			sb.append("\tNo products in cart\r\n");
		} else {
			for (ProdQtyPair pair : prodArray) {
				sb.append("\t" + pair.getProduct().getProdId() + "\t" + "Product Name: "
						+ pair.getProduct().getProdName() + "\t" + "Quantity:" + pair.getQuantity());
				sb.append("\r\n");
			}
		}
		sb.append("Total cost: $" + price);
		return sb.toString();
	}

}
