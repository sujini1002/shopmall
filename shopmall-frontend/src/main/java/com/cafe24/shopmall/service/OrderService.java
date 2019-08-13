package com.cafe24.shopmall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.provider.CartProvider;
import com.cafe24.shopmall.provider.OrderProvider;
import com.cafe24.shopmall.vo.DepositVo;
import com.cafe24.shopmall.vo.OrderProductVo;
import com.cafe24.shopmall.vo.OrderToVo;
import com.cafe24.shopmall.vo.OrderVo;

@Service
public class OrderService {
	
	@Autowired
	private CartProvider cartProvider;
	
	@Autowired
	private OrderProvider orderProvider;
	
	public List<Map<String,Object>> getCartList(Long no){
		return cartProvider.getList(no);
	}

	public Boolean insert(OrderVo orderVo) {
		
		OrderToVo orderToVo = new OrderToVo();
		orderToVo.setMember_code(orderVo.getMember_code());// 회원코드
		orderToVo.setName(orderVo.getName());// 주문자
		orderToVo.setPhone(orderVo.getPhone());// 주문자 휴대전화
		orderToVo.setEmail(orderVo.getEmail());// 주문자 이메일
		orderToVo.setDeliver_price(orderVo.getDeliver_price()); // 배송비
		orderToVo.setPay_way(orderVo.getPay_way());// 결제수단
		orderToVo.setPayment(orderVo.getPayment());// 총결제금액
		orderToVo.setRev_name(orderVo.getRev_name()); // 받는 사람
		orderToVo.setRev_phone(orderVo.getRev_phone());// 받는 사람 휴대전화
		
		// 주문자 주소
		String deliver = "(" + orderVo.getPostid() + ") " + orderVo.getBase_deliver() + " " + orderVo.getDetail_deliver(); 
		orderToVo.setDeliver(deliver);
		
		//배송지
		deliver = "(" + orderVo.getRev_postid() + ") " + orderVo.getRev_base_deliver() + " " + orderVo.getRev_detail_deliver(); 
		orderToVo.setRev_deliver(deliver);
		
		//결제 상태
		if("신용카드".equals(orderVo.getPay_way())) {
			orderToVo.setStatus("결제완료");
		}else {
			orderToVo.setStatus("입금전");
			
			//무통장 입금
			orderToVo.setDepositVo(new DepositVo(null, orderVo.getBank(), orderVo.getDeposit_name(), orderVo.getRefund_account()));
		}
		
		//주문 상품
		List<OrderProductVo> prodList = new ArrayList<OrderProductVo>();
		
		for(int i = 0 ;i < orderVo.getInventory_no().size() ; i++) {
			prodList.add(new OrderProductVo(null, orderVo.getInventory_no().get(i), orderVo.getCount().get(i), orderToVo.getStatus(), orderVo.getPrice().get(i)));
		}
		
		orderToVo.setOrderProductList(prodList);
		
		Boolean result = orderProvider.insert(orderToVo);
		
		return result;
	}

}
