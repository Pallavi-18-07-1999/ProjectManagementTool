
package com.impetus.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.impetus.authorization.exception.JwtAuthenticationEntryPoint;
import com.impetus.authorization.service.CustomerDetailsService;
import com.impetus.authorization.service.JwtRequestFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomerDetailsService userDetailService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return  NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	{
		auth.authenticationProvider(authProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/login").permitAll().
		antMatchers(HttpMethod.POST,"/projects/**").hasAnyAuthority("ADMIN").
		antMatchers(HttpMethod.DELETE,"/remove/{userId}","/projects/**").hasAnyAuthority("ADMIN").
		antMatchers(HttpMethod.PUT,"/projects*").hasAnyAuthority("ADMIN").
		antMatchers(HttpMethod.GET,"/users").hasAnyAuthority("ADMIN").
		anyRequest().authenticated().
		and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().
		exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
		http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
	
		
	}
}
