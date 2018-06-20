package com.framework.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.framework.api.security.filters.LoggedInUserDetails;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableWebSecurity
@EnableTransactionManagement
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class BasicBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicBootApplication.class, args);
	}

	@Bean
	public AuditorAware<String> createAuditorAware() {
		return new SecurityAuditor();
	}

	public static class SecurityAuditor implements AuditorAware<String> {
		
		@Override
		public String getCurrentAuditor() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			String username = null;
			if(authentication!=null) {
				if(authentication.getPrincipal() instanceof UserDetails) {
					UserDetails userDetails = (UserDetails) authentication.getPrincipal();
					username = userDetails.getUsername();
				} else if(authentication.getPrincipal() instanceof String) {
					username = (String) authentication.getPrincipal();
				} else if(authentication.getPrincipal() instanceof LoggedInUserDetails) {
					LoggedInUserDetails userDetails = (LoggedInUserDetails) authentication.getPrincipal();
					return userDetails.getUsername();
				} else {
					username = authentication.getPrincipal().toString();
				}
			}
			return username;
		}
	}
}
