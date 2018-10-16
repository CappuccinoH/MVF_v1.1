package mvf.controller;

import java.util.ArrayList;

import mvf.entities.PaymentDetail;

public class PaymentManager {
	public static PaymentDetail searchByPaymentId(ArrayList<PaymentDetail> paymentList, int paymentID) {
		for (PaymentDetail paymentDetail : paymentList) {
			if (paymentDetail.getPaymentId() == paymentID) {
				return paymentDetail;
			}
		}
		return null;
	}
}
