package com.hh.sukku.account.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hh.sukku.account.dao.RolePrivilegesDAO;

/**
 * 
 * @author arun.sudhakaran
 *
 * 04-Apr-2023 9:09:18 pm
 */

@Repository
public interface RolePrivilegesRepository 
extends PagingAndSortingRepository<RolePrivilegesDAO, Integer>, JpaSpecificationExecutor<RolePrivilegesDAO> {

	Optional<RolePrivilegesDAO> findByRoleIdAndFeatureId(int roleId, int featureId);
	
	List<RolePrivilegesDAO> findByRoleId(int roleId);
}
