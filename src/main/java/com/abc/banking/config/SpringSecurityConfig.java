package com.abc.banking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	// Authentication : User --> Roles
	@SuppressWarnings("deprecation")
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("user1").password("password")
				.roles("USER").and().withUser("admin1").password("password")
				.roles("USER", "ADMIN");
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/customers", "/customers/**", "/services", "/counters", "/tokens", "/tokens/**/comment").hasAnyRole("USER", "ADMIN")
		.antMatchers("/tokens/**/cancel", "/tokens/**/complete").hasRole("ADMIN").and()
		.csrf().disable().headers().frameOptions().disable();
	}

}