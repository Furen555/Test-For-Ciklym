package it.anichkin.webapps.TestForCiklum.models;

import java.util.Date;

import it.anichkin.webapps.TestForCiklum.ProductStatus;

public class Product implements Comparable<Product> {

	private int id;
	private String name;
	private int price;
	private String status;
	private Date createdAt;
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int compareTo(Product o) {
		return this.getId() - o.getId();
	}

}
