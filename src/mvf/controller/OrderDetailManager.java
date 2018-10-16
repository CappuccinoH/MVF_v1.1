package mvf.controller;

import java.util.ArrayList;

import mvf.entities.OrderDetail;

public class OrderDetailManager {
	public static OrderDetail searchByOrderDetailId(ArrayList<OrderDetail> orderDetailList, int orderDetailID) {
		for (OrderDetail orderDetail : orderDetailList) {
			if (orderDetail.getOrderDetailId() == orderDetailID) {
				return orderDetail;
			}
		}
		return null;
	}
}
