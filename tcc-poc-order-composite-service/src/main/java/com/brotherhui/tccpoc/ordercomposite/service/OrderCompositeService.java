package com.brotherhui.tccpoc.ordercomposite.service;

import com.brotherhui.tccpoc.ordercomposite.domain.order.Order;
import com.brotherhui.tccpoc.ordercomposite.domain.stock.RequiredStockItem;
import com.brotherhui.tccpoc.ordercomposite.tcc.TccRestCoordinator;
import com.brotherhui.tccpoc.participant.core.CompositeTransactionException;
import com.atomikos.tcc.rest.ParticipantLink;
import com.atomikos.tcc.rest.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author hunter
 * @create 2018-04-13 3:39 PM
 */
@Service
public class OrderCompositeService {

    @Value("${order.service.url}")
    private String orderServiceUrl;

    @Value("${stock.service.url}")
    private String stockServiceUrl;

    @Resource
    private TccRestCoordinator coordinator;

    @Value("${tcc.transaction.timeout}")
    private long transactionTimeout;

    @Autowired
    private RestTemplate restTemplate;

    public void create(Order order) throws CompositeTransactionException {
        List<ParticipantLink> participantLinks = new ArrayList<>(2);
        Transaction transaction = new Transaction(participantLinks);
        try {
            String txId = UUID.randomUUID().toString();
            //1. try order
            String orderParticipantId = restTemplate.postForObject(orderServiceUrl + "/order/" + txId, order, String.class);
            participantLinks.add(new ParticipantLink(buildParticipantTccUrl(orderServiceUrl, orderParticipantId), buildExpire()));
            //2. try stock
            List<RequiredStockItem> requiredStockItems = order.getItems().stream()
                    .map(item -> new RequiredStockItem(item.getProductId(), item.getProductNumber()))
                    .collect(Collectors.toList());
            String stockParticipantId = restTemplate.postForObject(stockServiceUrl, requiredStockItems, String.class);
            participantLinks.add(new ParticipantLink(buildParticipantTccUrl(stockServiceUrl + "/product/" + txId, stockParticipantId), buildExpire()));
            //3. call coordinator to confirm
            coordinator.commit(transaction);
        } catch (Exception e) {
            coordinator.rollback(transaction);
            if (e instanceof HttpStatusCodeException) {
                throw new CompositeTransactionException((((HttpStatusCodeException) e).getResponseBodyAsString()), e);
            }
            throw new CompositeTransactionException(e);
        }
    }

    private long buildExpire() {
        return System.currentTimeMillis() + transactionTimeout;
    }

    String buildParticipantTccUrl(String participantUrl, String participantId) {
        return UriComponentsBuilder.fromHttpUrl(participantUrl).pathSegment("tcc", participantId).build().toString();
    }
}
