package com.hh.sukku.product.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hh.sukku.common.beans.PagedResponse;
import com.hh.sukku.common.exception.CommonException;
import com.hh.sukku.product.dao.ProductDAO;
import com.hh.sukku.product.dto.ProductDTO;
import com.hh.sukku.product.repository.ProductRepository;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 9:35:05 pm
 */

@Service
public class ProductService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void create(ProductDTO productDTO) {
		
		if(productRepository.findByName(productDTO.getName()).isPresent())
			throw new CommonException("Product with the same name already exists!");
		
		ProductDAO productDAO = modelMapper.map(productDTO, ProductDAO.class);
		
		if(LOG.isInfoEnabled())
			LOG.info("Product DTO mapped to DAO");
		
		productRepository.save(productDAO);
		
		if(LOG.isInfoEnabled())
			LOG.info("Product created with id {}", productDAO.getId());
	}
	
	public PagedResponse<ProductDTO> findAll(Specification<ProductDAO> spec, Pageable pageable) {
		
		Page<ProductDAO> pages = productRepository.findAll(spec, pageable);
		
		if (pages.getNumberOfElements() == 0) {

			return new PagedResponse<>(Collections.emptyList(), pages.getNumber(), pages.getSize(),
					pages.getTotalElements(), pages.getTotalPages(), pages.isLast());
		}
		
		List<ProductDTO> products = pages.stream().map(productDAO -> {
			
			return modelMapper.map(productDAO, ProductDTO.class);

		}).collect(Collectors.toList());

		return new PagedResponse<>(products, pages.getNumber(), pages.getSize(), pages.getTotalElements(),
				pages.getTotalPages(), pages.isLast());
	}
}
