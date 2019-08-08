package com.cafe24.shopmall.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductVo {
	
	private String title;
	private Integer price;
	private String detail;
	private MultipartFile titleimage;
	private List<MultipartFile> images;
	private Integer topCategoryNo;
	private Integer bottomCategoryNo;
	private String oneOption;
	private String twoOption;
	private List<String> oneDetail;
	private List<String> twoDetail;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
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
	public MultipartFile getTitleimage() {
		return titleimage;
	}
	public void setTitleimage(MultipartFile titleimage) {
		this.titleimage = titleimage;
	}
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	public Integer getTopCategoryNo() {
		return topCategoryNo;
	}
	public void setTopCategoryNo(Integer topCategoryNo) {
		this.topCategoryNo = topCategoryNo;
	}
	public Integer getBottomCategoryNo() {
		return bottomCategoryNo;
	}
	public void setBottomCategoryNo(Integer bottomCategoryNo) {
		this.bottomCategoryNo = bottomCategoryNo;
	}
	public String getOneOption() {
		return oneOption;
	}
	public void setOneOption(String oneOption) {
		this.oneOption = oneOption;
	}
	public String getTwoOption() {
		return twoOption;
	}
	public void setTwoOption(String twoOption) {
		this.twoOption = twoOption;
	}
	public List<String> getOneDetail() {
		return oneDetail;
	}
	public void setOneDetail(List<String> oneDetail) {
		this.oneDetail = oneDetail;
	}
	public List<String> getTwoDetail() {
		return twoDetail;
	}
	public void setTwoDetail(List<String> twoDetail) {
		this.twoDetail = twoDetail;
	}
	
	@Override
	public String toString() {
		return "ProductVo [title=" + title + ", price=" + price + ", detail=" + detail + ", titleimage=" + titleimage
				+ ", images=" + images + ", topCategoryNo=" + topCategoryNo + ", bottomCategoryNo=" + bottomCategoryNo
				+ ", oneOption=" + oneOption + ", twoOption=" + twoOption + ", oneDetail=" + oneDetail + ", twoDetail="
				+ twoDetail + "]";
	}
	
}
