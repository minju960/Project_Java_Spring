package com.mycom.student_management.security;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycom.student_management.entity.Role;
import com.mycom.student_management.entity.User;
import com.mycom.student_management.jparepository.RoleJpaRepository;
import com.mycom.student_management.jparepository.UserJpaRepository;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	RoleJpaRepository roleJpaRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		User user = userJpaRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username");
		}
		
		// 암호화
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		Optional<List<Role>> roleListOptional = null;
		if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
			System.out.println("인증됨");
			roleListOptional = roleJpaRepository.findByUsername(username);
		}
		else {
			throw new BadCredentialsException("Invalid username");
		}
		
		// 인증 토큰
//		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		Set<GrantedAuthority> grantedAuthorityList = new HashSet<>();
		if(roleListOptional.isPresent()) {
			for (Role role : roleListOptional.get()) {
				grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRolename()));
			}
		}
		session.setAttribute("username", username);
		return new UsernamePasswordAuthenticationToken (username, password, grantedAuthorityList);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);	// 다른 곳에서 비정상적으로 접근하는 것 방지?
	}

}
