package com.cafe24.shopmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.repository.AdminOrderDAO;

@Service
public class AdminOrderService {
	
	@Autowired
	private AdminOrderDAO adminOrderDao;
	
	public List<Map<String,Object>> list(){
		return adminOrderDao.list();
	}
}
