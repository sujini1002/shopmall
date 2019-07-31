package com.cafe24.shopmall.vo;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.cafe24.shopmall.validator.constraints.ValidCheckMemberNo;
import com.cafe24.shopmall.validator.constraints.ValidEmail;
import com.cafe24.shopmall.validator.constraints.ValidPhone;

public class OrderVo {
	
	private Long no;
	private String order_code;
	
	@NotNull
	private String name;
	
	@ValidCheckMemberNo
	private Long member_code;
	
	private String password;
	
	@NotNull
	private String deliver;
	
	@ValidPhone
	private String phone;
	
	@ValidEmail
	private String email;
	
	@NotNull
	private String rev_name;
	
	@NotNull
	private String rev_deliver;
	
	@ValidPhone
	private String rev_phone;
	
	@NotNull
	private Integer deliver_price;
	
	@NotNull
	private String status;
	private String order_date;
	
	@NotNull
	private Integer payment;
	@NotNull
	private String  pay_way;
	
	//주문 상품
	@Valid
	private List<OrderProductVo> orderProductList;

	//무통장 정보
	private DepositVo depositVo;
	
	public OrderVo() {}


	public OrderVo(Long no, String order_code, Long member_code,String name, String password, @NotNull String deliver, String phone,
			String email, @NotNull String rev_name, @NotNull String rev_deliver, String rev_phone,
			@NotNull Integer deliver_price, @NotNull String status, String order_date, @NotNull Integer payment,
			@NotNull String pay_way, @Valid List<OrderProductVo> orderProductList, DepositVo depositVo) {
		super();
		this.no = no;
		this.order_code = order_code;
		this.name = name;
		this.member_code = member_code;
		this.password = password;
		this.deliver = deliver;
		this.phone = phone;
		this.email = email;
		this.rev_name = rev_name;
		this.rev_deliver = rev_deliver;
		this.rev_phone = rev_phone;
		this.deliver_price = deliver_price;
		this.status = status;
		this.order_date = order_date;
		this.payment = payment;
		this.pay_way = pay_way;
		this.orderProductList = orderProductList;
		this.depositVo = depositVo;
	}


	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public Long getMember_code() {
		return member_code;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setMember_code(Long member_code) {
		this.member_code = member_code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeliver() {
		return deliver;
	}

	public void setDeliver(String deliver) {
		this.deliver = deliver;
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

	public String getRev_deliver() {
		return rev_deliver;
	}

	public void setRev_deliver(String rev_deliver) {
		this.rev_deliver = rev_deliver;
	}

	public String getRev_phone() {
		return rev_phone;
	}

	public void setRev_phone(String rev_phone) {
		this.rev_phone = rev_phone;
	}

	public Integer getDeliver_price() {
		return deliver_price;
	}

	public void setDeliver_price(Integer deliver_price) {
		this.deliver_price = deliver_price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public DepositVo getDepositVo() {
		return depositVo;
	}

	public void setDepositVo(DepositVo depositVo) {
		this.depositVo = depositVo;
	}


	public Integer getPayment() {
		return payment;
	}


	public void setPayment(Integer payment) {
		this.payment = payment;
	}


	public String getPay_way() {
		return pay_way;
	}


	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}


	public List<OrderProductVo> getOrderProductList() {
		return orderProductList;
	}


	public void setOrderProductList(List<OrderProductVo> orderProductList) {
		this.orderProductList = orderProductList;
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", order_code=" + order_code + ", name=" + name + ", member_code=" + member_code
				+ ", password=" + password + ", deliver=" + deliver + ", phone=" + phone + ", email=" + email
				+ ", rev_name=" + rev_name + ", rev_deliver=" + rev_deliver + ", rev_phone=" + rev_phone
				+ ", deliver_price=" + deliver_price + ", status=" + status + ", order_date=" + order_date
				+ ", payment=" + payment + ", pay_way=" + pay_way + ", orderProductList=" + orderProductList
				+ ", depositVo=" + depositVo + "]";
	}

}
