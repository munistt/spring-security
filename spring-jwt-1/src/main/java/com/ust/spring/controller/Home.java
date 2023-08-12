package com.ust.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class Home {
	
	
	@GetMapping
	public String welcome() {
		return "Hello, this is the page";
	}

}
