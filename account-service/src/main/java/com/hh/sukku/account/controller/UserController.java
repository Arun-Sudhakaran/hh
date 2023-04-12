package com.hh.sukku.account.controller;

import static com.hh.sukku.common.util.ErrorCodes.SUCCESS;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hh.sukku.account.dao.UserDAO;
import com.hh.sukku.account.dto.UserDTO;
import com.hh.sukku.account.service.UserService;
import com.hh.sukku.common.beans.PagedResponse;
import com.hh.sukku.common.beans.Response;
import com.hh.sukku.common.util.Constants;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

/**
 * 
 * @author arun.sudhakaran
 *
 * 02-Apr-2023 11:14:15 pm
 */

@RestController
@RequestMapping("v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> create(@Valid @RequestBody UserDTO request) {
		
		userService.create(request);
		
		Response response = new Response(SUCCESS, "Success");
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedResponse<UserDTO> get(
			@RequestParam(value = "page", defaultValue = Constants.PAGE_DEFAULT_NUMBER, required = false) int page,
			@RequestParam(value = "size", defaultValue = Constants.PAGE_DEFAULT_SIZE, required = false) int size,
			@RequestParam(value = "sort", defaultValue = "firstName", required = false) String sort,
			@RequestParam(value = "order", defaultValue = Constants.SORT_DIR, required = false) String order,
			
			@Conjunction({ @Or(@Spec(path = "id", params = "id", spec = Equal.class)),
				@Or(@Spec(path = "firstName", params = "firstName", spec = Like.class)),
				@Or(@Spec(path = "lastName", params = "lastName", spec = Like.class)),
				@Or(@Spec(path = "email", params = "email", spec = Equal.class)),
				@Or(@Spec(path = "phoneNumber", params = "phoneNumber", spec = Equal.class)),
				
				@Or({ @Spec(path = "id", params = "keyword", spec = Equal.class),
					@Spec(path = "firstName", params = "keyword", spec = Equal.class),
					@Spec(path = "lastName", params = "keyword", spec = Equal.class), }) }) Specification<UserDAO> spec) {
	
		Pageable pageable = (size != 0
				? PageRequest.of(page - 1, size,
						order.trim().equals(Constants.SORT_DIR) ? Sort.Direction.DESC : Sort.Direction.ASC, sort)
				: Pageable.unpaged());
		
		return userService.findAll(spec, pageable);
	}
}
