package com.pc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc.entity.ProductEntity;

public interface ProductCatalogRepository extends JpaRepository<ProductEntity, Long>{

	List<ProductEntity> findByBrandName(String value);

	List<ProductEntity> findBySize(String value);

	List<ProductEntity> findByColour(String value);

	List<ProductEntity> findByCost(String value);

}
