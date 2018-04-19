package com.brotherhui.tcc.order.domain.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderEntry {

    @Id
    private long id;
    private Integer amount;
    private int availableStatus; //0: no show 1: show

	public int getAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(int availableStatus) {
		this.availableStatus = availableStatus;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}