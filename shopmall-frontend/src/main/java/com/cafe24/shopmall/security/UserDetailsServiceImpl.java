package com.cafe24.shopmall.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cafe24.shopmall.repository.UserDao;
import com.cafe24.shopmall.vo.MemberVo;




@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MemberVo memberVo = userDao.get(email);
		
		SecurityUser securityUser = new SecurityUser();
		
		if(memberVo != null) {
			securityUser.setNo(memberVo.getCode());
			securityUser.setName(memberVo.getName());
			securityUser.setUsername(memberVo.getEmail());
			securityUser.setPassword(memberVo.getPassword());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberVo.getRole())));
		}
		
		return securityUser;
	}	
}
