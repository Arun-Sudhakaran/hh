package com.hh.sukku.account.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author arun.sudhakaran
 *
 * 02-Apr-2023 10:49:21 pm
 */

@Entity
@Table(name = "HARAJI_USERS")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="haraji_users_id_seq",
						sequenceName="haraji_users_id_seq",
						allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator="haraji_users_id_seq")
	@Column(name = "ID", updatable = false)
	private int id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE_ID")
	private int roleId;
	
	@Column(name = "DOC_ID")
	private String docId;

	@Column(name = "TYPE_OF_USER")
	private int typeOfUser;

	@Column(name = "IS_VERIFIED")
	private boolean isVerified;

	@Column(name = "IS_APPROVED")
	private boolean isApproved;

	@Column(name = "CREATED_BY")
	private int createdBy;

	@Basic(optional = false)
	@Column(name = "CREATED_DATE", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "UPDATED_BY")
	private int updatedBy;

	@Basic(optional = false)
	@Column(name = "UPDATED_DATE", insertable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
}
