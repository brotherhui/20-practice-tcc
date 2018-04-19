package com.brotherhui.tcc.stock.controller;

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

import com.brotherhui.tcc.stock.domain.entity.ProductEntry;
import com.brotherhui.tcc.stock.domain.entity.StockEntry;
import com.brotherhui.tcc.stock.model.request.ProductRequest;
import com.brotherhui.tcc.stock.model.response.ProductResponse;
import com.brotherhui.tcc.stock.service.ProductService;
import com.brotherhui.tcc.stock.utils.MappingUtil;
import com.brotherhui.tcc.stock.utils.ValidationUtil;
import com.brotherhui.tccpoc.participant.core.SequenceID;



@RestController
@RequestMapping("/")
public class ProductController {

	private final static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/product/{id}", consumes = "application/json")
	public ProductResponse get(
			@PathVariable("id") String id
			) {
		log.info("Trying to get product [{}] outside any transaction", id);
		ValidationUtil.validateLongId(id);
		ProductResponse result = new ProductResponse();
		ProductEntry productEntry = productService.getProductEntry(id, 1);
		StockEntry stockEntry = productService.getStockEntry(id, 1);
		MappingUtil.map2ProductResponse(result, productEntry, stockEntry);
		return result;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/product", consumes = "application/json")
	public String create(
			@RequestBody ProductRequest productRequest
			) {
		log.info("Trying to create product [{}] outside any transaction");

		String result = doCreate(productRequest);
		
		return result;
	}
	
    protected String doCreate(ProductRequest productRequest) {
		ProductEntry productEntry = new ProductEntry();
		StockEntry stockEntry = new StockEntry();
		productEntry.setId(SequenceID.nextID());
		stockEntry.setId(SequenceID.nextID());
		
        MappingUtil.map2ProductEntryForCreate(productRequest, productEntry, stockEntry);

        productService.createProduct(productEntry, stockEntry);

        return String.valueOf(productEntry.getId());
    }
}
