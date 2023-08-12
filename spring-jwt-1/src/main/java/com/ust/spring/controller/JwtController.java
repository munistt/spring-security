package com.ust.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.spring.jwtUtil.JwtUtil;
import com.ust.spring.model.AuthRequest;
import com.ust.spring.model.AuthResponse;
import com.ust.spring.service.CustomUserDetailsService;

// to create the token first time

@RestController
@RequestMapping("/token")
public class JwtController {
	
	@Autowired
	private AuthenticationManager am;
	
	@Autowired
	private CustomUserDetailsService cuds;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest ar) throws Exception{
		
		System.out.println(ar);
		try {
			
			this.am.authenticate(new UsernamePasswordAuthenticationToken(ar.getUsername(), ar.getPassword()));
			
		} catch (UsernameNotFoundException e) {
			
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userDetails = this.cuds.loadUserByUsername(ar.getUsername());
		
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("jwt token"+token);
		
		// token = {"token":"value"}
		
		return ResponseEntity.ok().body(new AuthResponse(token));
		
		
		
	}

}
