package com.hh.sukku.account.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hh.sukku.account.dao.RoleDAO;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 7:49:19 pm
 */

@Repository
public interface RoleRepository 
extends PagingAndSortingRepository<RoleDAO, Integer>, JpaSpecificationExecutor<RoleDAO> {

}
