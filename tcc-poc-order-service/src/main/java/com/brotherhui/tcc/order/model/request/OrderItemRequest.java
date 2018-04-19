package com.brotherhui.tcc.order.model.request;


/**
 * @author xiaohui.c.liu
 * @create 2018-04-13 4:03 PM
 */

public class OrderItemRequest {

    private int amount;

    private String productId;

    private int productNumber;

    private int productPrice;


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
