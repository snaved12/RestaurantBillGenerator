package net.java.naved.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Menu {
	@Id
	private String id;
	private String item_name;
	private long price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	
}
