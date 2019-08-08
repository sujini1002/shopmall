package com.cafe24.shopmall.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.vo.ProductToVo;

@Repository
public class ProductProvider {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	private final String URL = "http://localhost:8888/shopmall/";

	public ProductToVo getProduct(Long no) {
		JSONResultProductVo jsonResult = restTemplate.getForObject(URL+"api/product/view/"+no, JSONResultProductVo.class);
		return jsonResult.getData();
	}
	
	private static class JSONResultProductVo extends JSONResult<ProductToVo>{}; 
}
