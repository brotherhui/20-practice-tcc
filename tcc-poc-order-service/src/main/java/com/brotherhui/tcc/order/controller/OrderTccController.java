package com.brotherhui.tcc.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brotherhui.tcc.order.domain.entity.OrderEntry;
import com.brotherhui.tcc.order.domain.entity.OrderItemEntry;
import com.brotherhui.tcc.order.domain.entity.OrderItemParticipantEntry;
import com.brotherhui.tcc.order.domain.entity.OrderParticipantEntry;
import com.brotherhui.tcc.order.model.request.OrderRequest;
import com.brotherhui.tcc.order.service.OrderService;
import com.brotherhui.tcc.order.utils.MappingUtil;
import com.brotherhui.tccpoc.participant.core.CompositeTransactionParticipantController;
import com.brotherhui.tccpoc.participant.core.SequenceID;


@RestController
@RequestMapping("/")
public class OrderTccController extends CompositeTransactionParticipantController {

    private final static Logger log = LoggerFactory.getLogger(OrderTccController.class);

    @Autowired
    OrderService orderService;

    @Override
    protected void doCancel(String participantid) {
        orderService.cancel(participantid);
    }

    @Override
    protected void doConfirm(String participantid) {
        orderService.confirmOrder(participantid);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/order/{txid}", consumes = "application/json")
    public String create(
            @PathVariable("txid") String txid, @RequestBody OrderRequest orderRequest
    ) {
        log.info("Trying to create record [{}]", orderRequest);
        String result = doCreate(txid, orderRequest);
        log.info("Record [{}] created but cannot be seen", orderRequest);
        return result;
    }

    protected String doCreate(String txid, OrderRequest orderRequest) {
        //getOrderEntry details

        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setId(SequenceID.nextID());
        OrderParticipantEntry orderEventEntry = new OrderParticipantEntry();
        List<OrderItemEntry> orderItemEntryList = new ArrayList<OrderItemEntry>();
        List<OrderItemParticipantEntry> orderItemEventEntryList = new ArrayList<OrderItemParticipantEntry>();

        MappingUtil.map2OrderEntryForCreate(txid, orderRequest, orderEntry, orderItemEntryList, orderEventEntry, orderItemEventEntryList);

        orderService.createOrder(orderEntry, orderItemEntryList, orderEventEntry, orderItemEventEntryList);

        return String.valueOf(orderEntry.getId());
    }
}
