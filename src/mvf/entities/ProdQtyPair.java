package mvf.entities;

public class ProdQtyPair {
	private Product product;
	private double quantity;

	public ProdQtyPair() {
		super();
		product = new Product();
		quantity = 0;
	}

	public ProdQtyPair(Product product, double quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProdQtyPair [product=" + product + ", quantity=" + quantity + "]";
	}

}
