package mvf.entities;

public class Order {
	private int orderId;
	private Customer customer;
	private OrderDetail orderDetail;
	private PaymentDetail paymentDetail;

	public Order() {
		super();
		this.orderDetail = new OrderDetail();
		this.paymentDetail = null;
	}

	public Order(int orderId, Customer customer, OrderDetail orderDetail) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.orderDetail = orderDetail;
		this.paymentDetail = null;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public PaymentDetail getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(PaymentDetail paymentDetail) {
		this.paymentDetail = paymentDetail;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		System.out.println("\tOrder ID: " + orderId + "\r\n");
		System.out.println("\tCustomer Account: " + customer.getAccount() + "\r\n");
		System.out.println("\tTime: " + paymentDetail.getPaymentTime() + "\r\n");
		System.out.println("\tTotal Cost: " + paymentDetail.getTotalCost() + "\r\n");
		return sb.toString();
	}

}
