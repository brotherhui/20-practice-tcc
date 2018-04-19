package com.brotherhui.tcc.order.service;

import java.sql.Timestamp;

import com.brotherhui.tcc.order.domain.entity.OrderEntry;
import com.brotherhui.tcc.order.domain.entity.OrderParticipantEntry;
import com.brotherhui.tcc.order.domain.entity.OrderItemEntry;
import com.brotherhui.tcc.order.domain.entity.OrderItemParticipantEntry;
import com.brotherhui.tcc.order.domain.repository.OrderParticipantRepository;
import com.brotherhui.tcc.order.domain.repository.OrderItemParticipantRepository;
import com.brotherhui.tcc.order.domain.repository.OrderItemRepository;
import com.brotherhui.tcc.order.domain.repository.OrderRepository;
import com.brotherhui.tccpoc.participant.core.DataBaseException;
import com.brotherhui.tccpoc.participant.core.NoRecordFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.validation.ValidationException;

@Service
public class OrderService{

	private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	OrderParticipantRepository orderParticipantRepository;
	
	@Autowired
	OrderItemParticipantRepository orderItemParticipantRepository;
	
	public OrderEntry getOrderEntry(String id, int availableStatus) {
		OrderEntry orderEntry = null;
		try {
			orderEntry = orderRepository.findOneByIdAndAvailableStatus(Long.parseLong(id),availableStatus);
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataBaseException("There are some error when get from order database");
		}
		if(orderEntry == null) {
			throw new NoRecordFoundException("No record found for order "+id);
		}
		return orderEntry;
	}
	
	public List<OrderItemEntry> getOrderItemEntryList(String id, int availableStatus) {
		List<OrderItemEntry> list = null;
		try {
			list = orderItemRepository.findAllByOrderIdAndAvailableStatus(Long.parseLong(id),availableStatus);
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataBaseException("There are some error when get from order database");
		}
		if(list == null || list.size() == 0) {
			throw new NoRecordFoundException("No record found for order "+id);
		}
		return list;
	}
	
	@Transactional
	public void createOrder(OrderEntry orderEntry, List<OrderItemEntry> orderItemEntryList, OrderParticipantEntry orderParticipantEntry, List<OrderItemParticipantEntry> orderItemParticipantEntryList) {
		//make all these in same transaction
		try {
			//1. create order
			orderRepository.save(orderEntry);
			//2. create order participant
			orderParticipantRepository.save(orderParticipantEntry);
			//3. create orderItem
			orderItemRepository.save(orderItemEntryList);
			//4. create orderItem participant
			orderItemParticipantRepository.save(orderItemParticipantEntryList);
		}catch(Exception e) {
			throw new DataBaseException("There are some error when create order");
		}
	}
	
	@Transactional
	public void confirmOrder(String orderId) {
		OrderParticipantEntry orderParticipantEntry = orderParticipantRepository.findOneByOrderId(Long.parseLong(orderId));
		if(orderParticipantEntry !=null && orderParticipantEntry.getExpiredTime().before(new Timestamp(System.currentTimeMillis()))) {
			throw new ValidationException("Order request has been expired");
		}

		try {
			//1. change order to show
			orderRepository.confirmStatus(Long.parseLong(orderId));
			//2. change order Items to show
			orderItemRepository.confirmStatus(Long.parseLong(orderId));
			//3. add confirm order participant
			orderParticipantRepository.confirmStatus(Long.parseLong(orderId));
			//4. add confirm order items participant
			orderItemParticipantRepository.confirmStatus(Long.parseLong(orderId));
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataBaseException("There are some error when confirm order");
		}
	}

    @Transactional
    public void cancel(String orderId) {
        long id = Long.parseLong(orderId);
        orderRepository.cancelStatus(id);
        orderItemRepository.cancelStatus(id);
        orderParticipantRepository.cancelStatus(id);
        orderItemParticipantRepository.cancelStatus(id);
    }
}
