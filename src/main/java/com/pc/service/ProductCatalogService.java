package com.pc.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.entity.ProductEntity;
import com.pc.exception.ProductNotFoundException;
import com.pc.repository.ProductCatalogRepository;

@Service
public class ProductCatalogService {
	
	Logger logger = LoggerFactory.getLogger(ProductCatalogService.class);
	
	@Autowired
	private ProductCatalogRepository repository;
	
	public ProductEntity saveProduct(ProductEntity entity) {
		return repository.save(entity);
	}
	
	public List<ProductEntity> getProductsByGroup(String groupType, String value) {
		List<ProductEntity> responseList = new ArrayList<ProductEntity>();
		
		switch(groupType) {
			case "brand":
				responseList = repository.findByBrandName(value);
				break;
			case "cost":
				responseList = repository.findByCost(value);
				break;
			case "colour":
				responseList = repository.findByColour(value);
				break;
			case "size":
				responseList = repository.findBySize(value);
				break;
			default:
				logger.error("Could not identify the type");
				throw new ProductNotFoundException("Type could not be found", groupType);
		}
		
		if(responseList.isEmpty()) {
			throw new ProductNotFoundException("No Product found for the given group and value", groupType + " & " + value);
		}
		return responseList;
		
		
	}

	public List<ProductEntity> getAllProducts() {
		 List<ProductEntity> productList = repository.findAll();
		
		if(productList.size() > 0) {
            return productList;
        } else {
            return new ArrayList<ProductEntity>();
        }
	}

}
