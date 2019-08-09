package com.cafe24.shopmall.provider;

import java.util.List;

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

	//상품 등록
	public Boolean insert(ProductToVo productToVo) {
		JSONResultObject jsonResult = restTemplate.postForObject(URL+"api/admin/product", productToVo, JSONResultObject.class);
		return Boolean.parseBoolean(jsonResult.getData().toString());
	}
	
	//상품 리스트
	public List<ProductToVo> getList() {
		JSONResultListProduct jsonResult = restTemplate.getForObject(URL+"api/admin/product", JSONResultListProduct.class);
		return jsonResult.getData();
	}
	
	public static class JSONResultObject extends JSONResult<Object>{}
	public static class JSONResultListProduct extends JSONResult<List<ProductToVo>>{}
	
}
