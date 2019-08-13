package com.cafe24.shopmall.provider;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.dto.JSONResult;

@Repository
public class AdminOrderProvider {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	private final String URL = "http://localhost:8888/shopmall/";
	
	public List<Map<String,Object>> list(){
		JSONResultListMap jsonResult = restTemplate.getForObject(URL+"api/admin/order", JSONResultListMap.class);
		return jsonResult.getData();
	}
	
	private static class JSONResultListMap extends JSONResult<List<Map<String,Object>>>{}
}
