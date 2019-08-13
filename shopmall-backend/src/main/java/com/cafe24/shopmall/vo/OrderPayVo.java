package com.cafe24.shopmall.vo;

import javax.validation.constraints.NotNull;

public class OrderPayVo {
	private Long code;
	private Long order_no;
	@NotNull
	private String pay_way;
	@NotNull
	private Integer payment;
	
	public OrderPayVo() {}

	public OrderPayVo(Long code, Long order_no, String pay_way, Integer payment) {
		this.code = code;
		this.order_no = order_no;
		this.pay_way = pay_way;
		this.payment = payment;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Long order_no) {
		this.order_no = order_no;
	}

	public String getPay_way() {
		return pay_way;
	}

	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "OderPayVo [code=" + code + ", order_no=" + order_no + ", pay_way=" + pay_way + ", payment=" + payment
				+ "]";
	}
	
}

