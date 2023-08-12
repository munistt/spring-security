package com.ust.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.spring.entity.MyUser;
import com.ust.spring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository ur;
	
	
	public MyUser create(MyUser user) {
		return ur.save(user);
	}
	public List<MyUser> read() {
		return ur.findAll();
	}
	public MyUser readById(Long id) {
		Optional<MyUser> temp = ur.findById(id);
		MyUser user = null;
		if(temp.isPresent()) {
			user = temp.get();
		}
		return user;
		
	}
	public MyUser update(MyUser user, Long id) {
		MyUser tempUser = readById(id);
		if(tempUser != null) {
			tempUser = user;
			ur.save(tempUser);
		}
		return tempUser;
		
	}
	public MyUser delete(Long id) {
		MyUser temp = readById(id);
		if(temp != null) {
			ur.delete(temp);
		}
		return temp;
	}
	

}
