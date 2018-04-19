package com.brotherhui.tcc.stock.model.request;

/**
 * @author xiaohui.c.liu
 * @create 2018-04-13 3:39 PM
 */

public class ProductRequest {


    private String productName;
    
    private int productPrice;

    private int stockNumber;
    

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

	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}
    
    

    
}
