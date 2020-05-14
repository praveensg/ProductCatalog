
package com.pc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pc.entity.ProductEntity;
import com.pc.service.ProductCatalogService;

@RestController
@RequestMapping("/products")
public class ProductCatalogController {

	@Autowired
	private ProductCatalogService service;
	
	@PostMapping
	public ResponseEntity<String> saveProducts(@RequestBody ProductEntity productEntity) {
		
		service.saveProduct(productEntity);
		return new ResponseEntity<String>("Successfully added.", new HttpHeaders(), HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<ProductEntity>> getAllProducts() {
		List<ProductEntity> result = service.getAllProducts();
		return new ResponseEntity<List<ProductEntity>>(result, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/byGroup")
	public ResponseEntity<List<ProductEntity>> getProductsBasedOnSize(
			@RequestParam String groupType, @RequestParam String value) {
		List<ProductEntity> result = service.getProductsByGroup(groupType, value);
		return new ResponseEntity<List<ProductEntity>>(result, new HttpHeaders(), HttpStatus.OK);
	}
}
