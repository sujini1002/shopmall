package com.cafe24.shopmall.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.provider.CartProvider;
import com.cafe24.shopmall.vo.CartToVo;
import com.cafe24.shopmall.vo.CartVo;

@Service
public class CartService {
	
	@Autowired
	private CartProvider cartProvider;

	public Boolean add(CartVo cartVo, Long member_code) {
		
		CartToVo cartToVo = new CartToVo();
		
		// 상품재고 번호 가져오기
		Long prd_inven_no = cartProvider.find(cartVo.getOpt_value(),cartVo.getNo());
		cartToVo.setInventory_no(prd_inven_no);
		
		
		//장바구니 insert 하기
		cartToVo.setCount(cartVo.getCount());
		cartToVo.setMember_code(member_code);
		
		
		Map<String, Object> result = cartProvider.add(cartToVo);
		
		if(Boolean.parseBoolean(result.get("isOk").toString())== false) {
			cartToVo.setCount(cartVo.getCount() + Integer.parseInt(result.get("count").toString()));
			cartProvider.update(cartToVo);
		}
		
		return true;
	}

}
