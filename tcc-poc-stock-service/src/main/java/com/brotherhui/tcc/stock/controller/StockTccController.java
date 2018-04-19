package com.brotherhui.tcc.stock.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brotherhui.tcc.stock.domain.entity.StockEntry;
import com.brotherhui.tcc.stock.domain.entity.StockParticipantEntry;
import com.brotherhui.tcc.stock.model.request.StockRequest;
import com.brotherhui.tcc.stock.service.ProductService;
import com.brotherhui.tccpoc.participant.core.CompositeTransactionParticipantController;



@RestController
public class StockTccController extends CompositeTransactionParticipantController{

	private final static Logger log = LoggerFactory.getLogger(StockTccController.class);
	
	@Autowired
	ProductService productService;

	@Override
	protected void doCancel(String participantid) {
		productService.cancelStock(participantid);
		
	}

	@Override
	protected void doConfirm(String participantid) {
		productService.confirmStock(participantid);
		
	}

    @RequestMapping(method = RequestMethod.PUT, value = "/stock/{txid}", consumes = "application/json")
    public String stockChange(
    		@PathVariable("txid") String txid, @RequestBody List<StockRequest> stockRequestList
    ) {
    	log.info("Trying to change stock [{}]", stockRequestList);
        String result = doStockChange(txid, stockRequestList);
        log.info("stock [{}] changed but not confirmed", stockRequestList);
        return result;
    }
	
	protected String doStockChange(String txid, List<StockRequest> stockRequestList) {
		for(StockRequest stockRequest: stockRequestList) {
			 if(productService.checkStock(stockRequest.getProductId(), stockRequest.getNumber(), stockRequest.getUpOrDown())) {
				 //if check success, do nothing
			 }else {
				 throw new ValidationException("Stock " +stockRequest.getProductId()+" is not enough");
			 }
		}
		productService.changeStock(txid, stockRequestList);
		return txid;
	}
}
