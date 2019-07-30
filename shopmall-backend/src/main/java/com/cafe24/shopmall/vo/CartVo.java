package com.cafe24.shopmall.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cafe24.shopmall.validator.constraints.ValidCheckMemberNo;


//import com.cafe24.shopmall.validator.constraints.ValidCheckInventroyNo;

public class CartVo {
	
	@ValidCheckMemberNo
	private Long member_code;
	
//	@ValidCheckInventoryNO
	@NotNull
	private Long inventory_no;
	
	@NotNull
	private String session_id;
	
	@NotNull
	@Min(1)
	private Integer count;
	
	public CartVo(Long member_code, Long inventory_no, String session_id, Integer count) {
		this.member_code = member_code;
		this.inventory_no = inventory_no;
		this.session_id = session_id;
		this.count = count;
	}
	
	public Long getMember_code() {
		return member_code;
	}
	public void setMember_code(Long member_code) {
		this.member_code = member_code;
	}
	public Long getInventory_no() {
		return inventory_no;
	}
	public void setInventory_no(Long inventory_no) {
		this.inventory_no = inventory_no;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "CartVo [member_code=" + member_code + ", inventory_no=" + inventory_no + ", session_id=" + session_id
				+ ", count=" + count +"]";
	}
	
}
