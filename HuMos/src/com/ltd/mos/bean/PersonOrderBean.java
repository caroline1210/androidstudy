package com.ltd.mos.bean;

import java.util.ArrayList;

public class PersonOrderBean {

	private String orderId; // 订单标识
	private String statusId; // 订单当前状态标识
	private String statusDescription; // 订单当前状态描述
	private String orderDate; // 下单日期
	private String shipDate; // 送货日期 ,
	private String grandTotal; // 订单总额
	private ArrayList<OrderItemList> orderItemList;
	private ArrayList<StatusHistoryList> statusHistoryList;
	private ArrayList<ShippingAddress> shippingAddress;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public ArrayList<OrderItemList> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(ArrayList<OrderItemList> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public ArrayList<StatusHistoryList> getStatusHistoryList() {
		return statusHistoryList;
	}

	public void setStatusHistoryList(ArrayList<StatusHistoryList> statusHistoryList) {
		this.statusHistoryList = statusHistoryList;
	}

	public ArrayList<ShippingAddress> getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ArrayList<ShippingAddress> shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}
