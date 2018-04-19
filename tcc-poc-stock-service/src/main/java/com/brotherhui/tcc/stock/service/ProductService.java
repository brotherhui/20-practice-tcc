package com.brotherhui.tcc.stock.service;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brotherhui.tccpoc.participant.core.TimeUtil;
import com.brotherhui.tcc.stock.domain.entity.ProductEntry;
import com.brotherhui.tcc.stock.domain.entity.StockEntry;
import com.brotherhui.tcc.stock.domain.entity.StockParticipantEntry;
import com.brotherhui.tcc.stock.domain.repository.ProductRepository;
import com.brotherhui.tcc.stock.domain.repository.StockParticipantRepository;
import com.brotherhui.tcc.stock.domain.repository.StockRepository;
import com.brotherhui.tcc.stock.model.request.StockRequest;
import com.brotherhui.tccpoc.participant.core.DataBaseException;
import com.brotherhui.tccpoc.participant.core.NoRecordFoundException;
import com.brotherhui.tccpoc.participant.core.SequenceID;

@Service
public class ProductService{

	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	StockParticipantRepository stockParticipantRepository;
	
	
	public ProductEntry getProductEntry(String id, int showStatus) {
		ProductEntry productEntry = null;
		try {
			productEntry = productRepository.findOneByIdAndAvailableStatus(Long.parseLong(id),showStatus);
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataBaseException("There are some error when get from product database");
		}
		if(productEntry == null) {
			throw new NoRecordFoundException("No record found for product "+id);
		}
		return productEntry;
	}
	
	public StockEntry getStockEntry(String id, int showStatus) {
		StockEntry stockEntry = null;
		try {
			stockEntry = stockRepository.findOneByProductIdAndAvailableStatus(id,showStatus);
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataBaseException("There are some error when get from stock database");
		}
		if(stockEntry == null) {
			throw new NoRecordFoundException("No record found for stock "+id);
		}
		return stockEntry;
	}
	
	
	public boolean checkStock(String productId, int number, int upOrDown) {
		boolean result = true;
		StockEntry stockEntry = getStockEntry(productId, 1);
		if(upOrDown == 1 ) {
			//1 is decrease, need to check
			if(stockEntry.getStockNumber()-number < 0) {
				result = false;
			}
		}
		return result;
	}
	
	
	
	@Transactional
	public void changeStock(String txid, List<StockRequest> stockRequestList) {
		for(StockRequest stockRequest: stockRequestList) {
			StockParticipantEntry stockParticipantEntry = new StockParticipantEntry();
			stockParticipantEntry.setTxId(txid);
			stockParticipantEntry.setId(SequenceID.nextID());
			stockParticipantEntry.setAction(1);
			stockParticipantEntry.setProductId(stockRequest.getProductId());
			stockParticipantEntry.setTccStatus(0);
			stockParticipantEntry.setNumber(stockRequest.getNumber());
			stockParticipantEntry.setUpOrDown(stockRequest.getUpOrDown());
			stockParticipantEntry.setExpiredTime(TimeUtil.getExpiredTime());
			
			//make all these in same transaction
			try {
				stockParticipantRepository.save(stockParticipantEntry);
				if(stockRequest.getUpOrDown() == 0) {
					stockRepository.addStock(stockRequest.getProductId(), stockRequest.getNumber());
				}else {
					stockRepository.reduceStock(stockRequest.getProductId(), stockRequest.getNumber());
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				throw new DataBaseException("There are some error when changing stock");
			}
		}
	}
	
	@Transactional
	public void confirmStock(String txid) {

		try {
			stockParticipantRepository.confirmStatus(txid);
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataBaseException("There are some error when confirm stock");
		}
	}
	
	@Transactional
	public void cancelStock(String txid) {

		try {
			//1. get what we did before
			StockParticipantEntry stockParticipantEntry = stockParticipantRepository.findOneByTxId(txid);
			//2. rollback based on the history
			if(stockParticipantEntry.getUpOrDown() == 0) {
				//because rollback -- if it is 0 (previous add), now reduce
				stockRepository.reduceStock(stockParticipantEntry.getProductId(), stockParticipantEntry.getNumber());
			}else {
				stockRepository.addStock(stockParticipantEntry.getProductId(), stockParticipantEntry.getNumber());
			}			
			stockParticipantRepository.cancelStatus(txid);
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataBaseException("There are some error when cancel stock");
		}
	}
	
	@Transactional
	public void createProduct(ProductEntry productEntry, StockEntry stockEntry) {

		try {
			productRepository.save(productEntry);
			stockRepository.save(stockEntry);
		}catch(Exception e) {
			e.printStackTrace();
			throw new DataBaseException("There are some error when create product");
		}
	}
}
