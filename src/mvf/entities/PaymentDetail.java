package mvf.entities;

import java.util.Date;

public class PaymentDetail {
	private int paymentId;
	private Date paymentTime;
	private double totalCost;

	public PaymentDetail() {
		super();
		this.paymentId = 0;
		this.paymentTime = new Date(System.currentTimeMillis());
		this.totalCost = 0;
	}

	public PaymentDetail(int paymentId, Date paymentTime, double totalCost) {
		super();
		this.paymentId = paymentId;
		this.paymentTime = paymentTime;
		this.totalCost = totalCost;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "PaymentDetail [paymentId=" + paymentId + ", paymentTime=" + paymentTime + ", totalCost=" + totalCost
				+ "]";
	}

}
