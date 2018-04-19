package com.brotherhui.tcc.order.model.request;


import java.util.List;

/**
 * @author xiaohui.c.liu
 * @create 2018-04-13 3:39 PM
 */

public class OrderRequest {

    private Integer amount;

    private List<OrderItemRequest> items;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public List<OrderItemRequest> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}

    
}
