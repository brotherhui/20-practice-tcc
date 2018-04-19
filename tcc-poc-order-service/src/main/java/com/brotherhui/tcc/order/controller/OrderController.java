package com.brotherhui.tcc.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brotherhui.tcc.order.domain.entity.OrderEntry;
import com.brotherhui.tcc.order.domain.entity.OrderItemEntry;
import com.brotherhui.tcc.order.model.response.OrderResponse;
import com.brotherhui.tcc.order.service.OrderService;
import com.brotherhui.tcc.order.utils.MappingUtil;
import com.brotherhui.tcc.order.utils.ValidationUtil;



@RestController
@RequestMapping("/order")
public class OrderController {

	private final static Logger log = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = "application/json")
	public OrderResponse get(
			@PathVariable("id") String id
			) {
		log.info("Trying to get order [{}] outside any transaction", id);
		ValidationUtil.validateLongId(id);
		OrderResponse result = new OrderResponse();
		OrderEntry orderEntry = orderService.getOrderEntry(id, 1);
		List<OrderItemEntry> orderItemEntryList = orderService.getOrderItemEntryList(id, 1);
		MappingUtil.map2OrderResponse(result, orderEntry, orderItemEntryList);
		return result;
	}
	
}
