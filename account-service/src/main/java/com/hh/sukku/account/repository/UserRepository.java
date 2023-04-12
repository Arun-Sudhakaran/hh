package com.hh.sukku.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hh.sukku.account.dao.UserDAO;

/**
 * 
 * @author arun.sudhakaran
 *
 * 02-Apr-2023 10:58:02 pm
 */

@Repository
public interface UserRepository 
extends PagingAndSortingRepository<UserDAO, Integer>, JpaSpecificationExecutor<UserDAO> {

	Optional<UserDAO> findByEmail(String email);
	
	Optional<UserDAO> findByPhoneNumber(String phoneNumber);
	
	Optional<UserDAO> findByEmailAndPassword(String email, String password);
}
