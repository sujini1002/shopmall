package com.cafe24.shopmall.vo;


/**
 *	상품 재고
 *	- 번호, 상품 번호, 품목명, 재고, 진열여부 , 판매여부 
 */

public class ProdInventoryVo {
	private Long no;
	private Long prd_no;
	
	private String opt_value;
	
	private Integer inventory;
	
	private Boolean issale;
	
	public ProdInventoryVo() {}
	
	public ProdInventoryVo(Long no, Long prd_no, String opt_value, Integer inventory,Boolean issale) {
		this.no = no;
		this.prd_no = prd_no;
		this.opt_value = opt_value;
		this.inventory = inventory;
		this.issale = issale;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getPrd_no() {
		return prd_no;
	}
	public void setPrd_no(Long prd_no) {
		this.prd_no = prd_no;
	}
	public String getOpt_value() {
		return opt_value;
	}
	public void setOpt_value(String opt_value) {
		this.opt_value = opt_value;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Boolean getIssale() {
		return issale;
	}
	public void setIssale(Boolean issale) {
		this.issale = issale;
	}
	
	@Override
	public String toString() {
		return "ProdIventoryVo [no=" + no + ", prd_no=" + prd_no + ", opt_value=" + opt_value + ", inventory="
				+ inventory + ", issale=" + issale + "]";
	}
	
}
