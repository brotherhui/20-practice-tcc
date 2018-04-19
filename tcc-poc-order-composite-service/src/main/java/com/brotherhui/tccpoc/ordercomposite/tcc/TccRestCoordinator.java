package com.brotherhui.tccpoc.ordercomposite.tcc;

import com.atomikos.tcc.rest.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;

@Service
public class TccRestCoordinator {

    @Resource
    private RestTemplate restTemplate;

    @Value("${tcc.coordinator.url}")
    private String tccCoordinatorUrl;

    public ResponseEntity<String> commit(Transaction transaction) {
        RequestEntity<Transaction> requestEntity = RequestEntity.put(buildUrl("confirm"))
                .contentType(new MediaType("application", "tcc+json"))
                .body(transaction);
        return restTemplate.exchange(requestEntity, String.class);
    }

    public ResponseEntity<String> rollback(Transaction transaction) {
        RequestEntity<Transaction> requestEntity = RequestEntity.put(buildUrl("cancel"))
                .contentType(new MediaType("application", "tcc+json"))
                .body(transaction);
        return restTemplate.exchange(requestEntity, String.class);
    }

    private URI buildUrl(String path) {
        return UriComponentsBuilder.fromHttpUrl(tccCoordinatorUrl).path(path).build().toUri();
    }
}
