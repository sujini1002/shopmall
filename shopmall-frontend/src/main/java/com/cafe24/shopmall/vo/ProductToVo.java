package com.cafe24.shopmall.vo;

import java.util.List;

public class ProductToVo {
	/**
	 *  상품 테이블
	 *  - no, title, price, detail, prod_date, catg_no
	 *  상품 이미지
	 *  - no, prd_no, url, istitle
	 *  옵션
	 *  - no, prd_no, name
	 *  옵션 상세
	 *  - no, opt_no, value
	 *  상품 재고
	 *  - prd_inven_no, prd_no, item_value, inventory, isdisplay, issale
	 */
	private Long no;
	
	private String title;
	
	private Integer price;
	
	private String detail;
	
	private String prod_date;
	
	private Integer	cate_no;
	
	private List<ProdImgVo> prodImgList;
	
	private List<OptionVo> optionList;
	
	private List<ProdInventoryVo> prodIventoryList;
	
	private Boolean issale;
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getProd_date() {
		return prod_date;
	}
	public void setProd_date(String prod_date) {
		this.prod_date = prod_date;
	}
	public Integer getCate_no() {
		return cate_no;
	}
	public void setCate_no(Integer cate_no) {
		this.cate_no = cate_no;
	}
	public List<ProdImgVo> getprodImgList() {
		return prodImgList;
	}
	public void setprodImgList(List<ProdImgVo> prodImgList) {
		this.prodImgList = prodImgList;
	}
	public List<OptionVo> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<OptionVo> optionList) {
		this.optionList = optionList;
	}
	public List<ProdInventoryVo> getProdIventoryList() {
		return prodIventoryList;
	}
	public void setProdIventoryList(List<ProdInventoryVo> prodIventoryList) {
		this.prodIventoryList = prodIventoryList;
	}
	public Boolean getIssale() {
		return issale;
	}
	public void setIssale(Boolean issale) {
		this.issale = issale;
	}

	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", title=" + title + ", price=" + price + ", detail=" + detail + ", prod_date="
				+ prod_date + ", cate_no=" + cate_no + ", prodImgList=" + prodImgList + ", optionList=" + optionList
				+ ", prodIventoryList=" + prodIventoryList + ", issale=" + issale + "]";
	}
}
