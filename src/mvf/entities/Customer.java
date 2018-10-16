package mvf.entities;

public class Customer extends User {
	private ShoppingCart shoppingCart;

	public Customer() {
		super();
		this.setStatus("C");
		shoppingCart = new ShoppingCart();
	}

	public Customer(String account, String password, ShoppingCart shoppingCart) {
		super(account, password, "C");
		this.shoppingCart = shoppingCart;
	}

	public Customer(String account, String password) {
		super(account, password, "C");
		shoppingCart = new ShoppingCart();
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Account: " + super.getAccount() + "\r\n");
		sb.append("Password: " + super.getPassword() + "\r\n");
		sb.append("Status: " + super.getStatus() + "\r\n");
		sb.append("Extre Info. :" + shoppingCart + "\r\n");
		return sb.toString();
	}

}
