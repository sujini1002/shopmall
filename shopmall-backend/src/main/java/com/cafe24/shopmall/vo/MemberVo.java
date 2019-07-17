package com.cafe24.shopmall.vo;

import javax.validation.constraints.Pattern;

import com.cafe24.shopmall.validator.constraints.ValidID;
import com.cafe24.shopmall.validator.constraints.ValidName;
import com.cafe24.shopmall.validator.constraints.ValidPassword;
import com.cafe24.shopmall.validator.constraints.ValidPhone;

public class MemberVo {

	private Long code;

	@ValidID
	private String id;

//	@Pattern(regexp = "^[\uAC00-\uD7A3xfe0-9a-zA-Z\\s]{1,20}$", message = "이름에 특수문자를 제외한 1~20글자만 입력하시오.")
	@ValidName
	private String name;

//	@Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{8,16}$", message = "영문소문자,영문대문자,숫자,특수문자를 조합하여 8~16글자를 입력하시오.")
	@ValidPassword
	private String password;

	@ValidPhone
	private String phone;

	@Pattern(regexp = "^[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+$", message = "example@shopmall.com 형식으로 입력하시오.")
	private String email;
	
	@Pattern(regexp = "^[0-9-]{0,7}$", message = "숫자,'-'로만 이루어진 값만 입력하시오. ")
	private String postid;
	
	private String base_deliver;
	private String detail_deliver;
	
	

	public MemberVo() {
	}

	public MemberVo(String id, String name, String password, String phone, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}

	public MemberVo(Long code, String id, String name, String password, String phone, String email) {
		this.code = code;
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
	}

	public MemberVo(String id, String name, String password, String phone, String email, String postid,
			String base_deliver, String detail_deliver) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.postid = postid;
		this.base_deliver = base_deliver;
		this.detail_deliver = detail_deliver;
	}

	public MemberVo(Long code,String id, String name, String password, String phone, String email, String postid,
			String base_deliver, String detail_deliver) {
		this.code = code;
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.postid = postid;
		this.base_deliver = base_deliver;
		this.detail_deliver = detail_deliver;
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

	@Override
	public String toString() {
		return "MemberVo [code=" + code + ", id=" + id + ", name=" + name + ", password=" + password + ", phone="
				+ phone + ", email=" + email + ", postid=" + postid + ", base_deliver="
				+ base_deliver + ", detail_deliver=" + detail_deliver + "]";
	}
	
}
