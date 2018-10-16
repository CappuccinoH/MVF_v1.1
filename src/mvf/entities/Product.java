package mvf.entities;

import java.util.Date;

public class Product {
	private String prodId;
	private String prodName;
	private String saleUnit;
	private double prodQty;
	private double prodPrice;
	private Date bestBefore;
	private String orignalPlace;

	public Product() {
		super();
		this.prodId = "";
		this.prodName = "";
		this.saleUnit = "";
		this.prodQty = 0;
		this.prodPrice = 0;
		this.bestBefore = new Date(System.currentTimeMillis());
		this.orignalPlace = "";
	}

	public Product(String prodId, String prodName, String saleUnit, double prodQty, double prodPrice, Date bestBefore,
			String orignalPlace) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.saleUnit = saleUnit;
		this.prodQty = prodQty;
		this.prodPrice = prodPrice;
		this.bestBefore = bestBefore;
		this.orignalPlace = orignalPlace;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getSaleUnit() {
		return saleUnit;
	}

	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}

	public double getProdQty() {
		return prodQty;
	}

	public void setProdQty(double prodQty) {
		this.prodQty = prodQty;
	}

	public double getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Date getBestBefore() {
		return bestBefore;
	}

	public void setBestBefore(Date bestBefore) {
		this.bestBefore = bestBefore;
	}

	public String getOrignalPlace() {
		return orignalPlace;
	}

	public void setOrignalPlace(String orignalPlace) {
		this.orignalPlace = orignalPlace;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("\tProduct ID: " + prodId + "\r\n");
		sb.append("\tProduct Name: " + prodName + "\r\n");
		sb.append("\tProduct Sale Unit: " + saleUnit + "\r\n");
		sb.append("\tProduct Quantity: " + prodQty + "\r\n");
		sb.append("\tProduct Price: " + prodPrice + "\r\n");
		sb.append("\tProduct Best Before: " + bestBefore + "\r\n");
		sb.append("\tProduct Orignal Place: " + orignalPlace + "\r\n");
		return sb.toString();
	}

}
