package com.cafe24.shopmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.vo.CategoryVo;

@Service
public class ProductService {
	
	private final String endPoint = "http://localhost:8888/shopmall";
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	public Map<String,Object> getList(){
		
		Map<String, Object> results = new HashMap<String, Object>();
		
		// 상품 목록 가져오기
		String url = "/api/product";
		JSONResultGoodsList jsonResult = restTemplate.getForObject(endPoint+url, JSONResultGoodsList.class);
		results.put("product", jsonResult.getData());
		
		// 카테고리 가져오기
		url = "/api/category";
		JSONResultCategoryList jsonCategoryResult = restTemplate.getForObject(endPoint+url, JSONResultCategoryList.class);
		results.put("category", jsonCategoryResult.getData());
		
		
		
		return results;
	}
	
	// DTO Class
	private static class JSONResultGoodsList extends JSONResult<List<Map<String,Object>>> {
	}
	
	private static class JSONResultCategoryList extends JSONResult<List<CategoryVo>>{
		
	}
}
