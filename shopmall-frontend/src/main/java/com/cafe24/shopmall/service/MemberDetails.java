package com.cafe24.shopmall.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MemberDetails implements UserDetails {
	
	private Long code;
	
	private String id;
	
	private String name;

	private String password;

	private String phone;
	private String email;
	private String postid;
	
	private String base_deliver;
	private String detail_deliver;
	
	private Boolean isdrop;
	
	private String role;
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public Boolean getIsdrop() {
		return isdrop;
	}

	public void setIsdrop(Boolean isdrop) {
		this.isdrop = isdrop;
	}

	@Override
	public String toString() {
		return "MemberVo [code=" + code + ", id=" + id + ", name=" + name + ", password=" + password + ", phone="
				+ phone + ", email=" + email + ", postid=" + postid + ", base_deliver=" + base_deliver
				+ ", detail_deliver=" + detail_deliver + ", isdrop=" + isdrop + ", role=" + role + "]";
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
