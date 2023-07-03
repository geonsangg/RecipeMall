package recipeMall.order;

import java.sql.Date;

public class OrderVO {
	private int orderCode;
	private String id;
	private Date orderDate;
	private int orderStatus;
	private int paymentStatus;
	private int allProdPrice;
	private int deliveryFee;
	private int allOrderPrice;
	private String shipAddr;
	private String shipAddr2;
	private String requestMsg;
	private String name;
	private String phone;
	
	
	public OrderVO() {
		
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getAllProdPrice() {
		return allProdPrice;
	}

	public void setAllProdPrice(int allProdPrice) {
		this.allProdPrice = allProdPrice;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public int getAllOrderPrice() {
		return allOrderPrice;
	}

	public void setAllOrderPrice(int allOrderPrice) {
		this.allOrderPrice = allOrderPrice;
	}

	public String getShipAddr() {
		return shipAddr;
	}

	public void setShipAddr(String shipAddr) {
		this.shipAddr = shipAddr;
	}

	public String getShipAddr2() {
		return shipAddr2;
	}

	public void setShipAddr2(String shipAddr2) {
		this.shipAddr2 = shipAddr2;
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
