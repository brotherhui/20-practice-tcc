package com.brotherhui.tcc.order.utils;

import java.util.ArrayList;
import java.util.List;

import com.brotherhui.tcc.order.domain.entity.OrderEntry;
import com.brotherhui.tcc.order.domain.entity.OrderItemEntry;
import com.brotherhui.tcc.order.domain.entity.OrderItemParticipantEntry;
import com.brotherhui.tcc.order.domain.entity.OrderParticipantEntry;
import com.brotherhui.tcc.order.model.request.OrderItemRequest;
import com.brotherhui.tcc.order.model.request.OrderRequest;
import com.brotherhui.tcc.order.model.response.OrderItemResponse;
import com.brotherhui.tcc.order.model.response.OrderResponse;
import com.brotherhui.tccpoc.participant.core.SequenceID;
import com.brotherhui.tccpoc.participant.core.TimeUtil;

public class MappingUtil {

	public static void map2OrderResponse(OrderResponse order, OrderEntry orderEntry, List<OrderItemEntry> orderItemEntryList) {
		if(null != orderEntry) {
			order.setAmount(orderEntry.getAmount());
			order.setId(String.valueOf(orderEntry.getId()));
		}
		List<OrderItemResponse> items = new ArrayList<OrderItemResponse>();
		if(orderItemEntryList.size()>0) {
			for(OrderItemEntry entry: orderItemEntryList) {
				OrderItemResponse item = new OrderItemResponse();
				item.setAmount(entry.getAmount());
				item.setId(String.valueOf(entry.getId()));
				item.setProductId(entry.getProductId());
				item.setProductNumber(entry.getProductNumber());
				item.setProductPrice(entry.getProductPrice());
				items.add(item);
			}
			order.setItems(items);
		}
	}
	
	public static void map2OrderEntryForCreate(String txId, OrderRequest orderRequest, OrderEntry orderEntry, List<OrderItemEntry> orderItemEntryList, OrderParticipantEntry orderParticipantEntry, List<OrderItemParticipantEntry> orderItemParticipantEntryList) {
		
		if(null != orderRequest) {
			//map orderEntry
			orderEntry.setAvailableStatus(0);
			orderEntry.setAmount(orderRequest.getAmount());
			//map orderParticipantEntry
			orderParticipantEntry.setId(SequenceID.nextID());
			orderParticipantEntry.setTxId(txId);
			orderParticipantEntry.setTccStatus(0);
			orderParticipantEntry.setAction(0);
			orderParticipantEntry.setTotalPrice(orderRequest.getAmount());
			orderParticipantEntry.setOrderId(orderEntry.getId());
			orderParticipantEntry.setExpiredTime(TimeUtil.getExpiredTime());
		}
	
		if(orderRequest.getItems()!=null && orderRequest.getItems().size()>0) {
			//map orderItemEntry
			//map orderItemParticipantEntry
			for(OrderItemRequest item: orderRequest.getItems()) {
				OrderItemEntry entry = new OrderItemEntry();
				OrderItemParticipantEntry eventEntry = new OrderItemParticipantEntry();
				entry.setShowStatus(0);
				entry.setId(SequenceID.nextID());
				entry.setAmount(item.getAmount());
				entry.setProductId(item.getProductId());
				entry.setProductNumber(item.getProductNumber());
				entry.setProductPrice(item.getProductPrice());
				entry.setOrderId(orderEntry.getId());
				orderItemEntryList.add(entry);
				
				eventEntry.setTxId(txId);
				eventEntry.setTccStatus(0);
				eventEntry.setId(SequenceID.nextID());
				eventEntry.setProductId(item.getProductId());
				eventEntry.setProductNumber(item.getProductNumber());
				eventEntry.setProductPrice(item.getProductPrice());
				eventEntry.setOrderId(orderEntry.getId());
				eventEntry.setOrderItemId(entry.getId());
				eventEntry.setExpiredTime(TimeUtil.getExpiredTime());
				orderItemParticipantEntryList.add(eventEntry);
			}
			
		}
	}
}
