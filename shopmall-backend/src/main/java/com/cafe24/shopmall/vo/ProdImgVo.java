package com.cafe24.shopmall.vo;


import javax.validation.constraints.NotNull;

/**
 *  상품이미지
 *  - 번호, 상품번호, url, 타이틀 여부 
 *
 */
public class ProdImgVo {
	
	private Long no;
	private Long prd_no;
	
	private String url;
	@NotNull
	private Boolean istitle;
	
	public ProdImgVo() {}
	
	public ProdImgVo(Long no, Long prd_no, String url, Boolean istitle) {
		super();
		this.no = no;
		this.prd_no = prd_no;
		this.url = url;
		this.istitle = istitle;
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
