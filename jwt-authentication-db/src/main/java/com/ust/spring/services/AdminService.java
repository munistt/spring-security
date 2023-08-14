package com.ust.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ust.spring.dto.AdminDto;
import com.ust.spring.model.Admin;
import com.ust.spring.repository.AdminRepository;

@Service
public class AdminService implements UserDetailsService{
	
	@Autowired
	private AdminRepository ar;

	
	
//	public AdminDto convertToDto(Admin admin) {
//		adminDto.setUsername(admin.getUsername());
//		adminDto.setEmail(admin.getEmail());
//		return adminDto;
//	}
	
	
	public Admin create(Admin admin) {
		return ar.save(admin);
	}
	
	
	public List<Admin> read() {
		return ar.findAll();
	}
	
	public Admin readByUsername(String username) {
		Optional<Admin> temp = ar.findById(username);
		Admin admin = null;
		if(temp.isPresent()) {
			admin = temp.get();
		}
		return admin;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Admin admin = readByUsername(username);
		return admin;
	}
	
	

}
