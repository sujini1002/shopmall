package com.cafe24.shopmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.provider.CategoryProvider;
import com.cafe24.shopmall.provider.ProductProvider;
import com.cafe24.shopmall.vo.CategoryVo;
import com.cafe24.shopmall.vo.ProductToVo;

@Service
public class ProductService {
	
	@Autowired
	private ProductProvider productProvider;
	
	@Autowired
	private CategoryProvider categoryProvider;
	
	public ProductToVo getProduct(Long no) {
		ProductToVo productVo = productProvider.getProduct(no);
		System.out.println(productVo);
		return productVo;
	}
	
	public List<CategoryVo> getCategory(){
		return categoryProvider.getCategory();
	}
}
