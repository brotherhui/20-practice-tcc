package com.brotherhui.tcc.order.model.response;


import java.util.List;

/**
 * @author xiaohui.c.liu
 * @create 2018-04-13 3:39 PM
 */

public class OrderResponse {

    private String id;

    private Integer amount;

    private List<OrderItemResponse> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public List<OrderItemResponse> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}

    
}
