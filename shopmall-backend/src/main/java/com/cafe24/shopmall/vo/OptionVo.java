package com.cafe24.shopmall.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

<<<<<<< HEAD
import org.hibernate.validator.constraints.NotEmpty;
=======
>>>>>>> shopmall_backup/master

/**
 * 옵션 
 * - 옵션 번호 , 상품 번호, 옵션명 
 *
 */
public class OptionVo {
	
	private Long no;
	private Long Prd_no;
	
<<<<<<< HEAD
	@NotEmpty
=======
>>>>>>> shopmall_backup/master
	@NotNull
	private String name;
	
	private List<OptionDetailVo> optionDetailList ;
	
	public OptionVo() {}
	
	public OptionVo(Long no, Long prd_no, String name, List<OptionDetailVo> optionDetailList) {
		super();
		this.no = no;
		Prd_no = prd_no;
		this.name = name;
		this.optionDetailList = optionDetailList;
	}

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
	public List<OptionDetailVo> getOptionDetailList() {
		return optionDetailList;
	}
	public void setOptionDetailList(List<OptionDetailVo> optionDetailList) {
		this.optionDetailList = optionDetailList;
	}

	@Override
	public String toString() {
		return "OptionVo [no=" + no + ", Prd_no=" + Prd_no + ", name=" + name + ", optionDetailList=" + optionDetailList
				+ "]";
	}
	
	
}
