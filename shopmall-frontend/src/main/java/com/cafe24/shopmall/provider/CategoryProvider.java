package com.cafe24.shopmall.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.vo.CategoryVo;

@Repository
public class CategoryProvider {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	private final String URL = "http://localhost:8888/shopmall/";
	
	//카테고리 조회
	public List<CategoryVo> getCategory(){
		JSONResultCategoryList categoryList = restTemplate.getForObject(URL+"api/category",JSONResultCategoryList.class);
		return categoryList.getData();
	}
	
	//DTO Class
	private static class JSONResultCategoryList extends JSONResult<List<CategoryVo>>{}
}
