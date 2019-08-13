package com.cafe24.shopmall.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.cafe24.shopmall.validator.constraints.ValidEmail;
import com.cafe24.shopmall.validator.constraints.ValidPhone;
import com.cafe24.shopmall.validator.constraints.ValidPostId;

public class OrderVo {
	
	@NotNull
	private List<Long> inventory_no;
	
	@NotNull
	private List<Integer> count;
	
	@NotNull
	private List<Integer> price;
	
	@NotNull
	private Integer payment;
	
	private Long member_code;
	
	@NotNull
	private String name;
	
	@ValidPostId
	private String postid;
	
	@NotNull
	private String base_deliver;
	
	@NotNull
	private String detail_deliver;
	
	@ValidPhone
	private String phone;
	
	@ValidEmail
	private String email;
	
	@NotNull
	private String rev_name;
	
	@ValidPostId
	private String rev_postid;
	
	@NotNull
	private String rev_base_deliver;
	@NotNull
	private String rev_detail_deliver;
	
	@ValidPhone
	private String rev_phone;
	
	@NotNull
	private String pay_way;
	
	@NotNull
	private Integer deliver_price;
	
	private String bank;
	private String deposit_name;
	private String refund_account;
	
	
	public List<Long> getInventory_no() {
		return inventory_no;
	}
	public void setInventory_no(List<Long> inventory_no) {
		this.inventory_no = inventory_no;
	}
	public List<Integer> getCount() {
		return count;
	}
	public void setCount(List<Integer> count) {
		this.count = count;
	}
	public List<Integer> getPrice() {
		return price;
	}
	public void setPrice(List<Integer> price) {
		this.price = price;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public Long getMember_code() {
		return member_code;
	}
	public void setMember_code(Long member_code) {
		this.member_code = member_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getBase_deliver() {
		return base_deliver;
	}
	public void setBase_deliver(String base_deliver) {
		this.base_deliver = base_deliver;
	}
	public String getDetail_deliver() {
		return detail_deliver;
	}
	public void setDetail_deliver(String detail_deliver) {
		this.detail_deliver = detail_deliver;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRev_name() {
		return rev_name;
	}
	public void setRev_name(String rev_name) {
		this.rev_name = rev_name;
	}
	public String getRev_postid() {
		return rev_postid;
	}
	public void setRev_postid(String rev_postid) {
		this.rev_postid = rev_postid;
	}
	public String getRev_base_deliver() {
		return rev_base_deliver;
	}
	public void setRev_base_deliver(String rev_base_deliver) {
		this.rev_base_deliver = rev_base_deliver;
	}
	public String getRev_detail_deliver() {
		return rev_detail_deliver;
	}
	public void setRev_detail_deliver(String rev_detail_deliver) {
		this.rev_detail_deliver = rev_detail_deliver;
	}
	public String getRev_phone() {
		return rev_phone;
	}
	public void setRev_phone(String rev_phone) {
		this.rev_phone = rev_phone;
	}
	public String getPay_way() {
		return pay_way;
	}
	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}
	public Integer getDeliver_price() {
		return deliver_price;
	}
	public void setDeliver_price(Integer deliver_price) {
		this.deliver_price = deliver_price;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getDeposit_name() {
		return deposit_name;
	}
	public void setDeposit_name(String deposit_name) {
		this.deposit_name = deposit_name;
	}
	public String getRefund_account() {
		return refund_account;
	}
	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}
	@Override
	public String toString() {
		return "OrderVo [inventory_no=" + inventory_no + ", count=" + count + ", price=" + price + ", payment="
				+ payment + ", member_code=" + member_code + ", name=" + name + ", postid=" + postid + ", base_deliver="
				+ base_deliver + ", detail_deliver=" + detail_deliver + ", phone=" + phone + ", email=" + email
				+ ", rev_name=" + rev_name + ", rev_postid=" + rev_postid + ", rev_base_deliver=" + rev_base_deliver
				+ ", rev_detail_deliver=" + rev_detail_deliver + ", rev_phone=" + rev_phone + ", pay_way=" + pay_way
				+ ", deliver_price=" + deliver_price + ", bank=" + bank + ", deposit_name=" + deposit_name
				+ ", refund_account=" + refund_account + "]";
	}
	
}
