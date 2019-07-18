package com.cafe24.shopmall.vo;


/**
 * 옵션 
 * - 옵션 번호 , 상품 번호, 옵션명 
 *
 */
public class OptionVo {
	private Long no;
	private Long Prd_no;
	private String name;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getPrd_no() {
		return Prd_no;
	}
	public void setPrd_no(Long prd_no) {
		Prd_no = prd_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "OptionVo [no=" + no + ", Prd_no=" + Prd_no + ", name=" + name + "]";
	}

}
