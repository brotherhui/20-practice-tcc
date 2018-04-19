/*
 * Copyright (c) 2016. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brotherhui.tcc.stock.domain.entity;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StockParticipantEntry {

    @Id
    private long id;
  
    private int number;
    
    private int upOrDown; //0: up 1: down
    
    private String productId;
    

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}



	/////////////////////////basic part
    private String txId;
    private int tccStatus; //0: try 1: confirm 2: cancel
    private int action; //0: create 1: update 2:delete 3:get
    private Timestamp expiredTime;
    
    public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}

	
	public int getTccStatus() {
		return tccStatus;
	}
	public void setTccStatus(int tccStatus) {
		this.tccStatus = tccStatus;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public Timestamp getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Timestamp expiredTime) {
		this.expiredTime = expiredTime;
	}
	
	
}