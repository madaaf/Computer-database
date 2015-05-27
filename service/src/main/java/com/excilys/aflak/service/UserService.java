package com.excilys.aflak.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.aflak.persistence.dao.impl.UserDAO;
import com.excilys.aflak.model.Authority;

@Service(value = "UserService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Transactional
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		com.excilys.aflak.model.User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildAuthority(user.getUserRole());
		return buildUserForAuthentication(user, authorities);

	}

	// Converts com.excilys.aflak.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.excilys.aflak.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildAuthority(Set<Authority> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// Build user's authorities
		for (Authority userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(
				setAuths);
		return result;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

}
