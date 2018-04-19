package com.brotherhui.tcc.stock.utils;

import com.brotherhui.tcc.stock.domain.entity.ProductEntry;
import com.brotherhui.tcc.stock.domain.entity.StockEntry;
import com.brotherhui.tcc.stock.model.request.ProductRequest;
import com.brotherhui.tcc.stock.model.response.ProductResponse;
import com.brotherhui.tccpoc.participant.core.SequenceID;

public class MappingUtil {

	public static void map2ProductResponse(ProductResponse product, ProductEntry productEntry, StockEntry stockEntry) {
		if(null != productEntry) {
			product.setProductName(productEntry.getProductName());
			product.setId(String.valueOf(productEntry.getId()));
			product.setProductPrice(productEntry.getProductPrice());
		}
		
		if(null != stockEntry) {
			product.setStockNumber(stockEntry.getStockNumber());
		}

	}
	
	public static void map2ProductEntryForCreate(ProductRequest productRequest, ProductEntry productEntry, StockEntry stockEntry) {
		if(null != productRequest) {
			//map productEntry
			productEntry.setShowStatus(1);
			productEntry.setId(SequenceID.nextID());			
			productEntry.setProductName(productRequest.getProductName());
			productEntry.setProductPrice(productRequest.getProductPrice());
			
			//map stockEntry			
			stockEntry.setAvailableStatus(1);
			stockEntry.setId(SequenceID.nextID());
			stockEntry.setProductId(String.valueOf(productEntry.getId()));			
			stockEntry.setStockNumber(productRequest.getStockNumber());

		}
	}
}
