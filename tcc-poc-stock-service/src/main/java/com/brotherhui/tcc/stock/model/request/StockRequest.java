package com.brotherhui.tcc.stock.model.request;


/**
 * @author xiaohui.c.liu
 * @create 2018-04-13 4:03 PM
 */

public class StockRequest {


    private String productId;
    
    private int number;
    
    private int upOrDown;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getUpOrDown() {
		return upOrDown;
	}

	public void setUpOrDown(int upOrDown) {
		this.upOrDown = upOrDown;
	}


    
}
