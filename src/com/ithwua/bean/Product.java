package com.ithwua.bean;

public class Product {
    private long id;
	private String name;     
	private String description;  //��Ʒ����
	private double price;  
	private long stock;          //��Ʒ���
	private long categoryId;        //�����Ĵ�����
	private String categoryName;       
	private long childCategoryId;   //������С����
	private String childCategoryName;
	private String fileName;        //��ƷͼƬ
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getChildCategoryId() {
		return childCategoryId;
	}
	public void setChildCategoryId(long childCategoryId) {
		this.childCategoryId = childCategoryId;
	}
	public String getChildCategoryName() {
		return childCategoryName;
	}
	public void setChildCategoryName(String childCategoryName) {
		this.childCategoryName = childCategoryName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", stock="
				+ stock + ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", childCategoryId="
				+ childCategoryId + ", childCategoryName=" + childCategoryName + ", fileName=" + fileName + "]";
	}



}
