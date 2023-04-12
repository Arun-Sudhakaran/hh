package com.hh.sukku.account.service;

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

import com.hh.sukku.account.dao.UserDAO;
import com.hh.sukku.account.dto.UserDTO;
import com.hh.sukku.account.repository.UserRepository;
import com.hh.sukku.common.beans.PagedResponse;
import com.hh.sukku.common.exception.CommonException;

/**
 * 
 * @author arun.sudhakaran
 *
 * 02-Apr-2023 10:59:38 pm
 */

@Service
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void create(UserDTO userDTO) {
		
		if(userRepository.findByEmail(userDTO.getEmail()).isPresent())
			throw new CommonException("User with the same email already exists!");
		
		if(userRepository.findByPhoneNumber(userDTO.getPhoneNumber()).isPresent())
			throw new CommonException("User with the same phone number already exists!");
		
		UserDAO userDAO = modelMapper.map(userDTO, UserDAO.class);
		
		if(LOG.isInfoEnabled())
			LOG.info("User DTO mapped to DAO");
		
		userRepository.save(userDAO);
		
		if(LOG.isInfoEnabled())
			LOG.info("User created with id {}", userDAO.getId());
	}
	
	public PagedResponse<UserDTO> findAll(Specification<UserDAO> spec, Pageable pageable) {
		
		Page<UserDAO> pages = userRepository.findAll(spec, pageable);
		
		if (pages.getNumberOfElements() == 0) {

			return new PagedResponse<>(Collections.emptyList(), pages.getNumber(), pages.getSize(),
					pages.getTotalElements(), pages.getTotalPages(), pages.isLast());
		}
		
		List<UserDTO> users = pages.stream().map(userDAO -> {
			
			return modelMapper.map(userDAO, UserDTO.class);

		}).collect(Collectors.toList());

		return new PagedResponse<>(users, pages.getNumber(), pages.getSize(), pages.getTotalElements(),
				pages.getTotalPages(), pages.isLast());
	}
}
