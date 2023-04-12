package com.hh.sukku.auth.bean;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.hh.sukku.account.dao.RolePrivilegesDAO;
import com.hh.sukku.account.dao.UserDAO;

/**
 * @author arun.sudhakaran
 *
 * 08-Apr-2023 9:27:37 pm
 */
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private UserDAO user;
	
	private List<RolePrivilegesDAO> rolePrivileges; 
	
	public CustomUserDetails(UserDAO user, List<RolePrivilegesDAO> rolePrivileges) {
		
		super();
		this.user = user;
		this.rolePrivileges = rolePrivileges;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<Integer> privileges = rolePrivileges.stream().map(RolePrivilegesDAO::getFeatureId).collect(Collectors.toList());
		
		String authorities = StringUtils.collectionToDelimitedString(privileges, ",");
		
		return Collections.singleton(new SimpleGrantedAuthority(authorities));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
