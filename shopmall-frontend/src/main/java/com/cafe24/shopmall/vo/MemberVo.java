package com.cafe24.shopmall.vo;

import com.cafe24.shopmall.validator.constraints.ValidEmail;
import com.cafe24.shopmall.validator.constraints.ValidID;
import com.cafe24.shopmall.validator.constraints.ValidName;
import com.cafe24.shopmall.validator.constraints.ValidPassword;
import com.cafe24.shopmall.validator.constraints.ValidPhone;
import com.cafe24.shopmall.validator.constraints.ValidPostId;

public class MemberVo {
	private Long code;
	
	@ValidID
	private String id;
	
	@ValidName
	private String name;

	@ValidPassword
	private String password;

	@ValidPhone
	private String phone;

	@ValidEmail
	private String email;
	
	@ValidPostId
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

	public MemberVo() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
