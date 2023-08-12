package com.ust.spring.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ust.spring.jwtUtil.JwtUtil;
import com.ust.spring.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// get jwt header
		// check contains Bearer
		// validate
		String requestTokenHeader = request.getHeader("Authorization");
		
		//going to get username and jwttoken form requestTokenHeader
		
		String username = null;
		String jwtToken = null;
		
		//checking if null and format
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken= requestTokenHeader.substring(7);
			
			try {
				
				username = jwtUtil.extractUsername(jwtToken);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
			UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			//security
			if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				// message can be given here
			}
			
			
		}
		// else part can be done
		
		
		
		filterChain.doFilter(request, response);
		
	}

}
