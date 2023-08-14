package com.ust.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.spring.model.Admin;
import com.ust.spring.model.AuthRequest;
import com.ust.spring.model.AuthResponse;
import com.ust.spring.services.AdminService;
import com.ust.spring.util.JwtUtil;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
	@Autowired
	private AuthenticationManager am;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AdminService as;
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> adminLogin(@RequestBody AuthRequest ar) throws Exception {
		
		System.out.println(ar);
		
		Admin admin = as.readByUsername(ar.getUsername());
		if(admin != null) {
				try {
					
					this.am.authenticate(new UsernamePasswordAuthenticationToken(ar.getUsername(), ar.getPassword()));
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("Bad Credentials");
				}
				
				UserDetails username = this.as.loadUserByUsername(ar.getUsername());

				String token = this.jwtUtil.generateToken(username);
				System.out.println("token"+token);
				
				
				return ResponseEntity.ok().body(new AuthResponse(token));
			
		}else {
			return null;
		}
		

		
	}

	@PostMapping("/signup")
	public Admin add(@RequestBody Admin admin) {
		return as.create(admin);
	}
	
	@GetMapping("/retrieve")
	public List<Admin> getAll() {
		return as.read();
	}
	
	@GetMapping("/{username}")
	public Admin getById(@PathVariable String username) {
		return as.readByUsername(username);
		
	}
	
	@GetMapping("/home")
	public String home() {
		return "Hello, Welcome to page";
	}
	
}
