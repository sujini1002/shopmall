package com.cafe24.shopmall.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.repository.ProductDAO;
import com.cafe24.shopmall.vo.OptionVo;
import com.cafe24.shopmall.vo.ProductVo;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDao;
	
	public void add(ProductVo productVo) {
		
		Map<String,Object> results = new HashMap<String, Object>();
		
		//1. 상품 정보 등록 - insert한 상품의 no 값 받기
		Long productNo = productDao.insertProduct(productVo);
		results.put("productNo",productNo);
		
		//2. 상품 이미지 등록
		Long lastImgListNo = productDao.insertProdImg(productVo.getprodImgList(),productNo);
		
		
		
		//3. 상품 옵션 및 상세 등록 - 옵션 번호가 필요
		for(OptionVo optionVo : productVo.getOptionList()) {
			Long optionNo = productDao.insertOption(optionVo,productNo);
			
		}
		
		//4. 옵션 상세 등록 - 
		
		
	}

}
