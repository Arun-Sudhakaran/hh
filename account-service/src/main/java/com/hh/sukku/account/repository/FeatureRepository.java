package com.hh.sukku.account.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hh.sukku.account.dao.FeatureDAO;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 9:09:24 pm
 */

@Repository
public interface FeatureRepository 
extends PagingAndSortingRepository<FeatureDAO, Integer>, JpaSpecificationExecutor<FeatureDAO> {

}
