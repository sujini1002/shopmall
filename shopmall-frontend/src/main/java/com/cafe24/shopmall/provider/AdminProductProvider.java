package com.cafe24.shopmall.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.vo.ProductToVo;

@Repository
public class AdminProductProvider {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	private final String URL = "http://localhost:8888/shopmall/";

	public Boolean insert(ProductToVo productToVo) {
		JSONResultObject jsonResult = restTemplate.postForObject(URL+"api/admin/product", productToVo, JSONResultObject.class);
		return Boolean.parseBoolean(jsonResult.getData().toString());
	}
	
	public static class JSONResultObject extends JSONResult<Object>{}
}
