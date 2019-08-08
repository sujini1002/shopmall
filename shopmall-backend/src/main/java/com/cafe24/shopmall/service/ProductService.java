package com.cafe24.shopmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.repository.ProductDAO;
import com.cafe24.shopmall.vo.ProductVo;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;

	public List<Map<String,Object>> list() {
		List<Map<String,Object>> result = productDAO.list();
		return result;
	}
	
	// 상품 상세보기
	public ProductVo getProduct(Long no) {
		return productDAO.getProduct(no);
	}
}
