package com.cafe24.shopmall.vo;


/**
 *	옵션 상세 
 *	- 번호, 옵션 번호, 옵션 분류
 */
public class OptionDetailVo {
	private Long no;
	private Long opt_no;
	private String value;
	
	public OptionDetailVo() {}
	
	public OptionDetailVo(Long no, Long opt_no, String value) {
		super();
		this.no = no;
		this.opt_no = opt_no;
		this.value = value;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getOpt_no() {
		return opt_no;
	}
	public void setOpt_no(Long opt_no) {
		this.opt_no = opt_no;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "OptionDetailVo [no=" + no + ", opt_no=" + opt_no + ", value=" + value + "]";
	}
	
}
