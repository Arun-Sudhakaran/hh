package com.hh.sukku.product.dao;

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
 * 04-Apr-2023 9:22:26 pm
 */

@Entity
@Table(name = "HARAJI_PRODUCTS")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ProductDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="haraji_products_id_seq",
						sequenceName="haraji_products_id_seq",
						allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator="haraji_products_id_seq")
	@Column(name = "ID", updatable = false)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CLIENT_ID")
	private int clientId;
	
	@Column(name = "DOC_ID")
	private String docId;
	
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
