package com.cafe24.shopmall.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import com.cafe24.shopmall.dto.Goods;
import com.cafe24.shopmall.dto.JSONResult;

@Service
public class GoodsService {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;

	public Goods getList(Long no){
		String endpoint = "http://localhost:8888/shopmall/hello";
		JSONResultGoods jsonResult = restTemplate.getForObject(endpoint, JSONResultGoods.class);
		return jsonResult.getData();
	}

	
	public String getList(){
		String endpoint = "http://localhost:8888/shopmall/hello";
		JSONResultGoodsList jsonResult = restTemplate.getForObject(endpoint, JSONResultGoodsList.class);
		System.out.println( jsonResult );
		return jsonResult.getData();
	}
	
	// DTO Class
	private static class JSONResultGoods extends JSONResult<Goods> {
	}
	
	private static class JSONResultGoodsList extends JSONResult<String> {
	}
}
