package com.brotherhui.tcc.order.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderItemEntry {

    @Id
    private long id;

    private int amount;

    private String productId;

    private int productNumber;

    private int productPrice;
    
    private long orderId;
    
    private int availableStatus; //0: no show 1: show

	public int getShowStatus() {
		return availableStatus;
	}

	public void setShowStatus(int availableStatus) {
		this.availableStatus = availableStatus;
	}
    
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

}
