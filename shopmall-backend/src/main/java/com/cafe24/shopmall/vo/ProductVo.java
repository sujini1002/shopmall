package com.cafe24.shopmall.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.shopmall.validator.constraints.ValidOption;
import com.cafe24.shopmall.validator.constraints.ValidProdImg;
import com.cafe24.shopmall.validator.constraints.ValidProdInventroy;

public class ProductVo {
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
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private Integer price;
	private String detail;
	private String prod_date;
	private Integer	cate_no;
	
//	@ValidProdImg
	private List<ProdImgVo> prodImgList;
//	@ValidOption
	private List<OptionVo> optionList;
	private List<OptionDetailVo> optionDetailList;
//	@ValidProdInventroy
	private List<ProdInventoryVo> prodIventoryList;
	
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
	public List<OptionDetailVo> getOptionDetailList() {
		return optionDetailList;
	}
	public void setOptionDetailList(List<OptionDetailVo> optionDetailList) {
		this.optionDetailList = optionDetailList;
	}
	public List<ProdInventoryVo> getProdIventoryList() {
		return prodIventoryList;
	}
	public void setProdIventoryList(List<ProdInventoryVo> prodIventoryList) {
		this.prodIventoryList = prodIventoryList;
	}
	
	@Override
	public String toString() {
		return "ProductVo [no=" + no + ", title=" + title + ", price=" + price + ", detail=" + detail + ", prod_date="
				+ prod_date + ", cate_no=" + cate_no + ", prodImgList=" + prodImgList + ", optionList=" + optionList
				+ ", optionDetailList=" + optionDetailList + ", prodIventoryList=" + prodIventoryList + "]";
	}
	
	
}
