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
 * 04-Apr-2023 8:56:56 pm
 */

@Entity
@Table(name = "HARAJI_FEATURES")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class FeatureDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="haraji_features_id_seq",
						sequenceName="haraji_features_id_seq",
						allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator="haraji_features_id_seq")
	@Column(name = "ID", updatable = false)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
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
