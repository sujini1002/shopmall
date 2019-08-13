package com.cafe24.shopmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.provider.AdminOrderProvider;

@Service
public class AdminOrderService {
	
	@Autowired
	private AdminOrderProvider adminOrderProvider;
	
	
	public List<Map<String,Object>> list(){
		List<Map<String,Object>> result = adminOrderProvider.list();
		return result;
	}
}
