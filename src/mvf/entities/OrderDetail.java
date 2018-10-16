package mvf.entities;

import java.util.ArrayList;

public class OrderDetail {
	private int orderDetailId;
	private ArrayList<ProdQtyPair> prodArray;

	public OrderDetail() {
		super();
		orderDetailId = 0;
		prodArray = new ArrayList<ProdQtyPair>();
	}

	public OrderDetail(int orderDetailId, ArrayList<ProdQtyPair> prodArray) {
		super();
		this.orderDetailId = orderDetailId;
		this.prodArray = prodArray;
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public ArrayList<ProdQtyPair> getProdArray() {
		return prodArray;
	}

	public void setProdArray(ArrayList<ProdQtyPair> prodArray) {
		this.prodArray = prodArray;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", prodArray=" + prodArray + "]";
	}

}
