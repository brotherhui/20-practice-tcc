package com.brotherhui.tccpoc.ordercomposite.domain.order;


import java.util.List;

/**
 * @author hunter
 * @create 2018-04-13 3:39 PM
 */

public class Order {

    private Integer amount;

    private List<OrderItem> items;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

    
}
