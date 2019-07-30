package com.cafe24.shopmall.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cafe24.shopmall.validator.constraints.ValidCheckInventoryNO;

public class OrderProductVo {
	
	private Long order_no;
	
	@ValidCheckInventoryNO
	private Long prd_no;
	
	@NotNull
	@Min(1)
	private Integer count;
	
	@NotNull
	private String status;
	
	@NotNull
	private Integer price;
	
	public OrderProductVo() {}
	
	public OrderProductVo(Long order_no, Long prd_no, Integer count, String status, Integer price) {
		super();
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
