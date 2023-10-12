package net.java.naved.model;


public class Order {
    private String itemId;
    private long quantity;
    
    // Additional fields to store item name and price
    private String itemName;
    private long itemPrice;
    
    private long value;

    public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(long itemPrice) {
        this.itemPrice = itemPrice;
    }

    // Method to calculate total amount
    public long calculateTotalAmount() {
        return itemPrice * quantity;
    }
    
    // Constructors, getters, and setters
}
