
package com.impetus.authorization.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.impetus.authorization.util.JwtUtil;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		String jwt = null;
		String userId = null;
		if (authHeader != null && authHeader.startsWith("Bearer")) {
			jwt = authHeader.substring(7);
			userId = jwtUtil.getUsernameFromToken(jwt);
		}
		if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = customerDetailsService.loadUserByUsername(userId);
			boolean tokenValidation = jwtUtil.validateToken(jwt, userDetails);
			if (tokenValidation) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				RequestContext.getCurrentContext().addZuulRequestHeader("userId",userDetails.getUsername());
				SimpleGrantedAuthority sga=(SimpleGrantedAuthority) userDetails.getAuthorities().toArray()[0];
				RequestContext.getCurrentContext().addZuulRequestHeader("roleId",sga.getAuthority());
				log.debug("header for user and role id added");
			}
		}
		filterChain.doFilter(request, response);

	}

}
