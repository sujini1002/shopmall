package com.cafe24.shopmall.vo;


public class OrderProductVo {
	
	private Long order_no;
	
	private Long prd_no;
	
	private Integer count;
	
	private String status;
	
	private Integer price;
	
	public OrderProductVo() {}
	
	public OrderProductVo(Long order_no, Long prd_no, Integer count, String status, Integer price) {
		this.order_no = order_no;
		this.prd_no = prd_no;
		this.count = count;
		this.status = status;
		this.price = price;
	}

	public Long getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}
	public Long getPrd_no() {
		return prd_no;
	}
	public void setPrd_no(Long prd_no) {
		this.prd_no = prd_no;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderProductVo [order_no=" + order_no + ", prd_no=" + prd_no + ", count=" + count + ", status=" + status
				+ ", price=" + price + "]";
	}
	
}
