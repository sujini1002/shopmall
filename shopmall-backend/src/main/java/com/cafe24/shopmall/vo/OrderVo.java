package com.cafe24.shopmall.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.cafe24.shopmall.validator.constraints.ValidCheckMemberNo;
import com.cafe24.shopmall.validator.constraints.ValidEmail;
import com.cafe24.shopmall.validator.constraints.ValidPassword;
import com.cafe24.shopmall.validator.constraints.ValidPhone;

public class OrderVo {
	
	private Long no;
	private String order_code;
	
	@ValidCheckMemberNo
	private Long member_code;
	
	@ValidPassword
	private String password;
	
	@NotNull
	private String deliver;
	
	@ValidPhone
	private String phone;
	
	@ValidEmail
	private String eamil;
	
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
	
	//주문 상품
	@Valid
	private List<OrderProductVo> orderProudctList;
	//결제 정보
	@Valid
	private OrderPayVo orderPayVo; 
	//무통장 정보
	private DepositVo depositVo;
	
	public OrderVo() {}
	
	
	

	public OrderVo(Long no, String order_code, Long member_code, String password, String deliver, String phone,
			String eamil, String rev_name, String rev_deliver, String rev_phone, Integer deliver_price, String status,
			String order_date, List<OrderProductVo> orderProudctList, OrderPayVo orderPayVo, DepositVo depositVo) {
		this.no = no;
		this.order_code = order_code;
		this.member_code = member_code;
		this.password = password;
		this.deliver = deliver;
		this.phone = phone;
		this.eamil = eamil;
		this.rev_name = rev_name;
		this.rev_deliver = rev_deliver;
		this.rev_phone = rev_phone;
		this.deliver_price = deliver_price;
		this.status = status;
		this.order_date = order_date;
		this.orderProudctList = orderProudctList;
		this.orderPayVo = orderPayVo;
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

	public String getEamil() {
		return eamil;
	}

	public void setEamil(String eamil) {
		this.eamil = eamil;
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

	public List<OrderProductVo> getOrderProudctList() {
		return orderProudctList;
	}

	public void setOrderProudctList(List<OrderProductVo> orderProudctList) {
		this.orderProudctList = orderProudctList;
	}

	public OrderPayVo getOrderPayVo() {
		return orderPayVo;
	}

	public void setOrderPayVo(OrderPayVo orderPayVo) {
		this.orderPayVo = orderPayVo;
	}

	public DepositVo getDepositVo() {
		return depositVo;
	}

	public void setDepositVo(DepositVo depositVo) {
		this.depositVo = depositVo;
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", order_code=" + order_code + ", member_code=" + member_code + ", password="
				+ password + ", deliver=" + deliver + ", phone=" + phone + ", eamil=" + eamil + ", rev_name=" + rev_name
				+ ", rev_deliver=" + rev_deliver + ", rev_phone=" + rev_phone + ", deliver_price=" + deliver_price
				+ ", status=" + status + ", order_date=" + order_date + ", orderProudctList=" + orderProudctList
				+ ", orderPayVo=" + orderPayVo + ", depositVo=" + depositVo + "]";
	}
	
}
