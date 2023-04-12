package com.hh.sukku.account.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author arun.sudhakaran
 *
 * 02-Apr-2023 10:56:58 pm
 */

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UserDTO {

	private int id;
	
	@NotEmpty(message = "First name cannot be empty")
	private String firstName;

	@NotEmpty(message = "Last name cannot be empty")
	private String lastName;
	
	@NotEmpty(message = "Email cannot be empty")
	private String email;
	
	@NotEmpty(message = "Phone number cannot be empty")
	private String phoneNumber;
	
	@NotEmpty(message = "Password cannot be empty")
	private String password;
	
	@Min(value = 1, message = "Invalid role")
	private int roleId;
	
	@NotEmpty(message = "Invalid document")
	private String docId;
	
	@Min(value = 1, message = "Invalid user type")
	private int typeOfUser;
	
	private boolean isVerified;
	
	private boolean isApproved;
	
	private int createdBy;
	
	private Date createdDate;
	
	private int updatedBy;
	
	private Date updatedDate;
}
