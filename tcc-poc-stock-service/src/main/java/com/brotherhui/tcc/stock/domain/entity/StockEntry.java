package com.brotherhui.tcc.stock.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StockEntry {

    @Id
    private long id;

    private int stockNumber;

    private String productId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	////////////basic part
    private int availableStatus; //0: no show 1: show

	public int getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(int availableStatus) {
		this.availableStatus = availableStatus;
	}

}
