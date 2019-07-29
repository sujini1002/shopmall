package com.cafe24.shopmall.vo;

import javax.validation.constraints.NotNull;

<<<<<<< HEAD
import org.hibernate.validator.constraints.NotBlank;
=======
>>>>>>> shopmall_backup/master

import com.cafe24.shopmall.validator.constraints.ValidCheckCategoryNo;

public class CategoryVo {
	
	private Integer no;
	
	@ValidCheckCategoryNo
	private Integer catg_top_no;
	
<<<<<<< HEAD
	@NotBlank
=======
>>>>>>> shopmall_backup/master
	@NotNull
	private String name;
	
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getCatg_top_no() {
		return catg_top_no;
	}
	public void setCatg_top_no(Integer catg_top_no) {
		this.catg_top_no = catg_top_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", catg_top_no=" + catg_top_no + ", name=" + name + "]";
	}
	
}
