package com.ust.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ust.spring.entity.MyUser;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MyUser user = userService.readById(Long.valueOf(username));
		if(user != null) {
			return new User(String.valueOf(user.getId()), user.getPassword(), new ArrayList<>());
		}
		else{
			throw new UsernameNotFoundException("not found "+username);
		}
			
	}
}
