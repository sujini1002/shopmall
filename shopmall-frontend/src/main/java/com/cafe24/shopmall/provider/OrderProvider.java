package com.cafe24.shopmall.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.vo.OrderToVo;

@Repository
public class OrderProvider {
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	private final String URL = "http://localhost:8888/shopmall/";

	public Boolean insert(OrderToVo orderToVo) {
		JSONResultObejct jsonResult = restTemplate.postForObject(URL+"api/order", orderToVo, JSONResultObejct.class);
		return Boolean.parseBoolean(jsonResult.getData().toString());
	}
	
	private static class JSONResultObejct extends JSONResult<Object>{}
}
