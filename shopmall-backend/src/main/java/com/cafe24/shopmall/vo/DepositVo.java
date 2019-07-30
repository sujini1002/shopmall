package com.cafe24.shopmall.vo;

public class DepositVo {
	private Long code;
	private Integer price;
	private String bank;
	private String name;
	private String refund_account;
	
	public DepositVo() {}

	public DepositVo(Long code, Integer price, String bank, String name, String refund_account) {
		super();
		this.code = code;
		this.price = price;
		this.bank = bank;
		this.name = name;
		this.refund_account = refund_account;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRefund_account() {
		return refund_account;
	}

	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}

	@Override
	public String toString() {
		return "Deposit [code=" + code + ", price=" + price + ", bank=" + bank + ", name=" + name + ", refund_account="
				+ refund_account + "]";
	}
	
}
