package com.cafe24.shopmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.repository.ProductDAO;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;

	public List<Map<String,Object>> list() {
		List<Map<String,Object>> result = productDAO.list();
		return result;
	}
}
