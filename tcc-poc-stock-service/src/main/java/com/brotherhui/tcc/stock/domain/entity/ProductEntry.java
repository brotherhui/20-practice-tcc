package com.brotherhui.tcc.stock.domain.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductEntry {

    @Id
    private long id;
    
    private String productName;
    
    private int productPrice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
    
	
	////////////basic part
    private int availableStatus; //0: no show 1: show

	public int getAvailableStatus() {
		return availableStatus;
	}

	public void setShowStatus(int availableStatus) {
		this.availableStatus = availableStatus;
	}


}