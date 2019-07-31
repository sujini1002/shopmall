package com.cafe24.shopmall.vo;

public class DepositVo {
	private Long code;
	private String bank;
	private String name;
	private String refund_account;
	
	public DepositVo() {}

	public DepositVo(Long code, String bank, String name, String refund_account) {
		super();
		this.code = code;
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
		return "Deposit [code=" + code + ", bank=" + bank + ", name=" + name + ", refund_account="
				+ refund_account + "]";
	}
	
}
