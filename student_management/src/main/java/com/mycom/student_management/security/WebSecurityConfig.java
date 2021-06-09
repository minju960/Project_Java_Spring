package com.mycom.student_management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationProvider costomAuthenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(costomAuthenticationProvider);	// 인증 구현체
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/user/**").permitAll()	// 인증 필요 없음
//				.antMatchers("/resources/**","/static/**", "/webjars/**").permitAll()
				.antMatchers("/webjars/**", "/css/**", "/js/**", "/images/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()	// 나머지 요청사항은 인증 받아야함
				.and()
			.formLogin()
				.loginPage("/user/login")
				.defaultSuccessUrl("/user/login_result")
				.and()
			.logout()
				.logoutUrl("/user/logout_result")
				.logoutSuccessUrl("/user/logout_result")
				.invalidateHttpSession(true)	// 세션 초기화
				.deleteCookies();	// 쿠키 삭제
	}	
	
}
