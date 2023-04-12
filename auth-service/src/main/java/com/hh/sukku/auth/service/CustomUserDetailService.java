package com.hh.sukku.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hh.sukku.account.dao.UserDAO;
import com.hh.sukku.account.repository.RolePrivilegesRepository;
import com.hh.sukku.account.repository.UserRepository;
import com.hh.sukku.auth.bean.CustomUserDetails;

/**
 * @author arun.sudhakaran
 *
 * 08-Apr-2023 9:58:08 pm
 */

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolePrivilegesRepository rolePrivilegesRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDAO user = userRepository.findByEmail(username)
		.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
		
		return new CustomUserDetails(user, rolePrivilegesRepository.findByRoleId(user.getRoleId()));
	}
}
