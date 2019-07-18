package com.cafe24.shopmall.vo;


/**
 *  상품이미지
 *  - 번호, 상품번호, url, 타이틀 여부 
 *
 */
public class ProdImgVo {
	
	private Long no;
	private Long prd_no;
	private String url;
	private Boolean istitle;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getIstitle() {
		return istitle;
	}
	public void setIstitle(Boolean istitle) {
		this.istitle = istitle;
	}
	
	@Override
	public String toString() {
		return "ProdImgVo [no=" + no + ", prd_no=" + prd_no + ", url=" + url + ", istitle=" + istitle + "]";
	}
	
}
