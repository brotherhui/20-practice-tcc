package com.brotherhui.tccpoc.ordercomposite.domain.stock;

/**
 * @author hunter
 * @create 2018-04-13 4:41 PM
 */
public class RequiredStockItem {

    private String productId;

    private int number;
    
    

	public RequiredStockItem(String productId, int number) {
		super();
		this.productId = productId;
		this.number = number;
	}

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
    
    
}
