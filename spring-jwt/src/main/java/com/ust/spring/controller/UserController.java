package com.ust.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.spring.entity.MyUser;
import com.ust.spring.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService us;
	
	@PostMapping
	public ResponseEntity<MyUser> createUser(@RequestBody MyUser user) {
		return ResponseEntity.ok().body(us.create(user));
	}
	
	@GetMapping
	public ResponseEntity<List<MyUser>> retrieveAllUser() {
		return ResponseEntity.ok(us.read());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MyUser> retrieveUserById(@PathVariable Long id) {
		return ResponseEntity.ok(us.readById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MyUser> updateUser(@RequestBody MyUser user,@PathVariable Long id) {
		return ResponseEntity.ok(us.update(user, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity< MyUser> deleteUser(@PathVariable Long id) {
		return ResponseEntity.ok().body(us.delete(id));
	}
}
