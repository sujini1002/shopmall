package com.cafe24.shopmall.vo;

import javax.validation.constraints.Pattern;

public class MemberVo {
	
	
	private Long code;
	
	@Pattern(regexp="^[a-zA-Z0-9_]{6,15}$",message="영문자,숫자,'_'로만 이루어진 6~15글자를 입력하시오.") 
	private String id;
	
	@Pattern(regexp="^[\uAC00-\uD7A3xfe0-9a-zA-Z\\s]{1,20}$",message="이름에 특수문자를 제외한 1~20글자만 입력하시오.")
	private String name;
	
	@Pattern(regexp= "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{8,16}$",message="영문소문자,영문대문자,숫자,특수문자를 조합하여 8~16글자를 입력하시오.") 
	private String password;
	
	@Pattern(regexp="^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message="000-0000(또는000)-0000 형식으로 입력하시오.")
	private String phone;
	
	@Pattern(regexp="^[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+$", message="example@shopmall.com 형식으로 입력하시오.")
	private String email;
	
	public MemberVo() {}
	public MemberVo(String id, String name, String password, String phone, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}
	
	public MemberVo(Long code,String id, String name, String password, String phone, String email) {
		this.code = code;
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
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
	
	@Override
	public String toString() {
		return "UserVo [code=" + code + ", id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + "]";
	}
	
}
