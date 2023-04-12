package com.hh.sukku.product.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 9:25:02 pm
 */

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ProductDTO {

	private int id;
	
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	private int clientId;
	
	@NotEmpty(message = "Invalid document")
	private String docId;
	
	private int createdBy;
	
	private Date createdDate;
	
	private int updatedBy;
	
	private Date updatedDate;
}
