package com.cafe24.shopmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.provider.ProductProvider;
import com.cafe24.shopmall.vo.ProductToVo;

@Service
public class ProductService {
	
	@Autowired
	private ProductProvider productProvider;
	
	public ProductToVo getProduct(Long no) {
		ProductToVo productVo = productProvider.getProduct(no);
		System.out.println(productVo);
		return productVo;
	}
}
