package com.cafe24.shopmall.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.repository.CartDAO;
import com.cafe24.shopmall.vo.CartVo;

@Service
public class CartService {
	
	@Autowired
	private CartDAO cartDao;

	public Long findInventoryNo(String opt_value, Long prd_no) {
		return cartDao.findInventroyNo(opt_value,prd_no);
	}
	
	/**
	 *  1. 회원 일 때,
	 *  1.1 세션으로 넘어온 값을 가져가서 비회원 테이블에 있는 장바구니 목록 을 가져온다.
	 *  1.1.1 있으면 비회원 테이블에서 지우고 회원 장바구니 테이블에 insert한다.
	 *  1.1.1.1 insert하려는 inventroyno와 같은지 비교하여 있으면 수량 만큼 update 한다.
	 *  1.1.2 없으면 회원 테이블에서 inventroyno가 존재하는지 검색한다.
	 *  1.1.2.1 존재하면 false로 return 한다.
	 *  
	 */
	public Map<String,Object> add(CartVo cartVo) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		Boolean result = null;
		//비회원
		if(cartVo.getMember_code() == null) {
			Long pk = cartDao.get(cartVo,"none");
			result = pk == null?cartDao.insert(cartVo, "none"):false;
			
			if(!result) {
				map.put("session_id",cartVo.getSession_id());
				map.put("inventory_no",cartVo.getInventory_no());
			}
			
		}
		//회원
		else {
			Long pk = cartDao.get(cartVo, "member");
			result = pk == null?cartDao.insert(cartVo, "member"):false;
			
			if(!result) {
				map.put("member_code",cartVo.getMember_code());
				map.put("inventory_no",cartVo.getInventory_no());
			}
		}
		
		map.put("isOk", result);
		
		return map;
	}
	
	//회원
	public List<CartVo> get(Long member_code) {
		List<CartVo> cartList = cartDao.getList(member_code,"member");
		return cartList;
	}
	//비회원
	public List<CartVo> get(String session_id) {
		List<CartVo> cartList = cartDao.getList(session_id,"none");
		return cartList;
	}
	
	// 수정하기
	public CartVo modify(CartVo cartVo) {
		
		String status = cartVo.getMember_code()==null?"none":"member";
		
		cartDao.update(cartVo,status);
		
		return cartDao.getCart(cartVo, status);
	}

	public Boolean delete(CartVo cartVo) {
		
		String status = cartVo.getMember_code()==null?"none":"member";
		
		Boolean result = cartDao.delete(cartVo,status);
		
		return result;
	}

}
