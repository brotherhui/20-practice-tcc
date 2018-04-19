package com.brotherhui.tcc.order.domain.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItemParticipantEntry {
	
    @Id
    private long id;
    
    //business related
    private String productId;
    private int productNumber;
    private int productPrice;
    private long orderId;
    private long orderItemId;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	
	
	/////////////////////////basic part
    private String txId;
    private int tccStatus; //0: try 1: confirm 2: cancel
    private int action; //0: create 1: update 2:delete 3:get
    private Timestamp expiredTime;
    
    public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}

	
	public int getTccStatus() {
		return tccStatus;
	}
	public void setTccStatus(int tccStatus) {
		this.tccStatus = tccStatus;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public Timestamp getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Timestamp expiredTime) {
		this.expiredTime = expiredTime;
	}
	
}
