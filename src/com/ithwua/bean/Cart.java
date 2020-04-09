package com.ithwua.bean;

public class Cart {
	private long id;
	private long productId;
	private String productName;
	private double productPrice;
	private long userId;
	private String userName;
	private long quantity;
	private String fileName;



	
	
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + ", userId=" + userId + ", userName=" + userName + ", quantity=" + quantity
				+ ", fileName=" + fileName + "]";
	}

	

}
