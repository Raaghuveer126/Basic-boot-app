package com.framework.api.config;

import static com.framework.api.constants.RestMappingConstants.APP_BASE;
import static com.framework.api.constants.RestMappingConstants.REQUEST;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.framework.api.constants.RestMappingConstants.CategoryConstants;
import com.framework.api.constants.RestMappingConstants.UserConstants;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebMvcSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final String SAMPLE_REQUEST_END_POINTS = APP_BASE + "/*" + REQUEST + "/**";
	private final String TOKEN_BASED_END_POINTS = APP_BASE + "/**";

	private String[] permitAllPatterns = { 
			SAMPLE_REQUEST_END_POINTS, 
			};

	private String[] authenticateAllPatterns = { TOKEN_BASED_END_POINTS };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(permitAllPatterns).permitAll().and().authorizeRequests()
				.antMatchers(authenticateAllPatterns).authenticated().and().formLogin()
				.authenticationDetailsSource(authenticationDetailsSource())
				.successHandler(authenticationSuccessHandler()).failureHandler(failureHandler()).loginPage("/login")
				.usernameParameter("usernameCustom").passwordParameter("passwordCustom").permitAll().and().logout()
				.permitAll().and().rememberMe().rememberMeServices(rememberMeServices());
	}

	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource() {

		return new AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>() {

			@Override
			public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
				return new WebAuthenticationDetails(request);
			}

		};
	}

	@Bean
	RememberMeServices rememberMeServices() {
		RememberMeServices rememberMeServices = new RememberMeServices() {

			@Override
			public void loginSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2) {

			}

			@Override
			public void loginFail(HttpServletRequest arg0, HttpServletResponse arg1) {

			}

			@Override
			public Authentication autoLogin(HttpServletRequest arg0, HttpServletResponse arg1) {
				return null;
			}
		};
		return rememberMeServices;
	}

	@Bean
	AuthenticationFailureHandler failureHandler() {

		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res,
					AuthenticationException arg2) throws IOException, ServletException {
				req.setAttribute("error", "forward");
				req.getRequestDispatcher("/homedefault").forward(req, res);
			}

		};
	}

	@Bean
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication arg2)
					throws IOException, ServletException {
				res.sendRedirect("homedefault");
			}
		};
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
}
