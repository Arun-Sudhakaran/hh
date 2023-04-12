package com.hh.sukku.account.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 7:47:42 pm
 */

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoleDTO {

	private int id;
	
	private String name;
	
	private int createdBy;
	
	private Date createdDate;
	
	private int updatedBy;
	
	private Date updatedDate;
}
