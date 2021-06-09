package com.mycom.student_management.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.student_management.entity.Role;
import com.mycom.student_management.entity.User;
import com.mycom.student_management.jparepository.RoleJpaRepository;
import com.mycom.student_management.jparepository.UserJpaRepository;



@Controller
public class MembershipController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	RoleJpaRepository roleJpaRepository;
	
	
	// 회원가입
	@GetMapping("/user/register")
	public String register() {
		return "contents/security/register";
	}
	
	@PostMapping("/user/register")
	public String register_process(
			@RequestParam("username") String username,
			@RequestParam("password") String password
			) {
		
		User user = new User();
		user.setUsername(username);
		
		Role role = new Role();
		role.setUsername(username);
		role.setRolename("ROLE_USER");
		roleJpaRepository.save(role);
		
		// 암호화
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		user.setPassword(encodedPassword);
		
		user = userJpaRepository.save(user);
		if(user != null) {
			return "contents/security/register_result";
		}
		
		return "contents/security/register_result";
	}
	
	// 로그인
	@GetMapping("/user/login")
	public String login() {
		
		return "contents/security/login";
	}
	
	@GetMapping("/user/login_result")
	public String login_process(
			Model model
			) {
		
		String username = (String) session.getAttribute("username");
		
		String hellouser = String.format("%s님, 반갑습니다.", username);
		
		model.addAttribute("hellouser", hellouser);
		
		return "contents/security/login_result";
	}
	
	// 로그아웃
	@GetMapping("/user/logout")
	public String logout() {
		
		return "contents/security/logout";
	}
	
	@GetMapping("/user/logout_result")
	public String logout_process() {
		
		return "contents/security/logout_result";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
