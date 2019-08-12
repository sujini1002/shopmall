package com.cafe24.shopmall.vo;

public class CartVo {
	private Long no; //상품번호
	private String opt_value;
	private Integer count;
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOpt_value() {
		return opt_value;
	}
	public void setOpt_value(String opt_value) {
		this.opt_value = opt_value;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", opt_value=" + opt_value + ", count=" + count + "]";
	}
	
}
