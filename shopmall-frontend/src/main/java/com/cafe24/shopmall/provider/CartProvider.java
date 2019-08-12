package com.cafe24.shopmall.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.vo.CartToVo;

@Repository
public class CartProvider {
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	private final String URL = "http://localhost:8888/shopmall/";
	
	
	//상품 재고번호 찾기
	public Long find(String opt_value, Long no) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("opt_value", opt_value);
		params.put("prd_no", no);
		JSONResultObject jsonResult = restTemplate.postForObject(URL+"api/cart/find", params, JSONResultObject.class);
		return Long.parseLong(jsonResult.getData().toString());
	}
	
	//장바구니 추가
	public Map<String,Object> add(CartToVo cartVo){
		JSONResultCartMap jsonResult = restTemplate.postForObject(URL+"api/cart",cartVo, JSONResultCartMap.class);
		return jsonResult.getData();
	}
	
	//장바구니 수정
	public void update(CartToVo cartToVo) {
		restTemplate.put(URL+"api/cart", cartToVo);
	}
	
	//장바구니 리스트
	public List<Map<String, Object>> getList(Long no) {
		JSONResultListMap jsonResult = restTemplate.getForObject(URL+"api/cart/"+no, JSONResultListMap.class);
		return jsonResult.getData();
	}
	
	//DTO Class
	private static class JSONResultCartMap extends JSONResult<Map<String,Object>>{}
	private static class JSONResultObject extends JSONResult<Object>{}
	private static class JSONResultListMap extends JSONResult<List<Map<String,Object>>>{}
	
	

	
}
