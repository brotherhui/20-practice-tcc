package com.brotherhui.tccpoc.ordercomposite.controller;

import com.brotherhui.tccpoc.ordercomposite.domain.order.Order;
import com.brotherhui.tccpoc.ordercomposite.service.OrderCompositeService;
import com.brotherhui.tccpoc.participant.core.CompositeTransactionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hunter
 * @create 2018-04-13 3:07 PM
 */
@RestController("/order")
public class OrderCompositeController {

    @Resource
    private OrderCompositeService orderCompositeService;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Order order) throws CompositeTransactionException {
        orderCompositeService.create(order);
    }
}
