package recipeMall.order;

import java.sql.Date;

public class OrderDVO {
	private int orderCode;
	private int prodCode;
	private int orderAmount;
	private String prodName;
	private int prodPrice;
	private Date orderDate;

	public OrderDVO() {
	
	}

	public OrderDVO(int orderCode, int prodCode, int orderAmount) {
		this.orderCode = orderCode;
		this.prodCode = prodCode;
		this.orderAmount = orderAmount;
	}
	
	

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getProdCode() {
		return prodCode;
	}

	public void setProdCode(int prodCode) {
		this.prodCode = prodCode;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
}
