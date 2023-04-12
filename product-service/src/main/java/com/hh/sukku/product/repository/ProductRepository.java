package com.hh.sukku.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hh.sukku.product.dao.ProductDAO;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 9:33:42 pm
 */

@Repository
public interface ProductRepository 
extends PagingAndSortingRepository<ProductDAO, Integer>, JpaSpecificationExecutor<ProductDAO> {
	
	Optional<ProductDAO> findByName(String name);
}
