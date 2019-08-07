package com.cafe24.shopmall.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cafe24.shopmall.provider.MemberProvider;
import com.cafe24.shopmall.vo.MemberVo;




@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private MemberProvider memberProvider;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberVo memberVo = memberProvider.login(id);
		
		System.out.println(memberVo);
		
		SecurityUser securityUser = new SecurityUser();
		
		if(memberVo != null) {
			securityUser.setNo(memberVo.getCode());
			securityUser.setName(memberVo.getName());
			securityUser.setUsername(memberVo.getId());
			securityUser.setPhone(memberVo.getPhone());
			securityUser.setEmail(memberVo.getEmail());
			securityUser.setPostid(memberVo.getPostid());
			securityUser.setBase_deliver(memberVo.getBase_deliver());
			securityUser.setDetail_deliver(memberVo.getDetail_deliver());
			securityUser.setRole(memberVo.getRole());
			securityUser.setPassword(memberVo.getPassword());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberVo.getRole())));
		}
		
		return securityUser;
	}	
}
