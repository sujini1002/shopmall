package com.cafe24.shopmall.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private Long no;
	private String name;

	private String username;
	private String password;
	
	private String phone;
	private String email;
	private String postid;
	private String base_deliver;
	private String detail_deliver;
	private String role;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostid() {
		return postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	public String getBase_deliver() {
		return base_deliver;
	}

	public void setBase_deliver(String base_deliver) {
		this.base_deliver = base_deliver;
	}

	public String getDetail_deliver() {
		return detail_deliver;
	}

	public void setDetail_deliver(String detail_deliver) {
		this.detail_deliver = detail_deliver;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
