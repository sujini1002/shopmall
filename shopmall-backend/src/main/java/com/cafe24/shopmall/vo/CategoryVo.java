package com.cafe24.shopmall.vo;

import javax.validation.constraints.NotNull;

import com.cafe24.shopmall.validator.constraints.ValidCheckCategoryTop;

public class CategoryVo {
	
	private Integer no;
	
	@ValidCheckCategoryTop
	private Integer catg_top_no;
	
	@NotNull
	private String name;
	
	private Integer level;
	
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
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", catg_top_no=" + catg_top_no + ", name=" + name + ", level=" + level + "]";
	}
	
	
	
}
