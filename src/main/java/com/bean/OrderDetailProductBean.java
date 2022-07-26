package com.bean;

public class OrderDetailProductBean {
	
	int pId;
	String pName;
	String imgUrl;
	int price;
	int orderId;
	int orderDatailId;
	
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderDatailId() {
		return orderDatailId;
	}
	public void setOrderDatailId(int orderDatailId) {
		this.orderDatailId = orderDatailId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	

}
