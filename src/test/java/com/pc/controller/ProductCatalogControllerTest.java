package com.pc.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pc.entity.ProductEntity;
import com.pc.service.ProductCatalogService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductCatalogController.class)
public class ProductCatalogControllerTest {
	
	Logger logger = LoggerFactory.getLogger(ProductCatalogControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductCatalogService productService;

	List<ProductEntity> productEntityList = new ArrayList<>();

	@Test
	public void getAllProductsWillBeCalledAndReturnedSuccessfully() throws Exception {
		Mockito.when(
				productService.getAllProducts()).thenReturn(productEntityList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/products").accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		logger.info(result.getResponse().getContentAsString());
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void getAllProductsWillReturnProducts() throws Exception {
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(1L);
		productEntity.setProductName("shirt");
		productEntity.setBrandName("adidas");
		productEntity.setCost(1200L);
		productEntity.setSize("m");
		productEntityList.add(productEntity);

		Mockito.when(
				productService.getAllProducts()).thenReturn(productEntityList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/products").accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		logger.info(result.getResponse().getContentAsString());
		String expected = "[{id:1,productName:shirt,brandName:adidas,cost:1200,size:m}]";
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}


}
