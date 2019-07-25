package com.cafe24.shopmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.repository.CartDAO;

@Service
public class CartService {
	
	@Autowired
	private CartDAO cartDao;

	public Long findInventoryNo(String opt_value, Long prd_no) {
		return cartDao.findInventroyNo(opt_value,prd_no);
	}

}
