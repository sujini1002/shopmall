package com.cafe24.shopmall.provider;

import java.util.List;
import java.util.Map;

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

//	public ProductToVo getProduct(Long no) {
//
//		try {
//			if (true) {
//				Thread.sleep(8000);
//			}
//			JSONResultProductVo jsonResult = restTemplate.getForObject(URL + "api/product/view/" + no,
//					JSONResultProductVo.class);
//			return jsonResult.getData();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//
//	}

	public ProductToVo getProduct(Long no) {
		JSONResultProductVo jsonResult = restTemplate.getForObject(URL + "api/product/view/" + no,
				JSONResultProductVo.class);
		return jsonResult.getData();
	}

	public List<Map<String, Object>> getList(Long no) {
		JSONResultGoodsList jsonResult = restTemplate.getForObject(URL + "api/product/category/" + no,
				JSONResultGoodsList.class);
		return jsonResult.getData();
	}

	private static class JSONResultProductVo extends JSONResult<ProductToVo> {
	};

	// DTO Class
	private static class JSONResultGoodsList extends JSONResult<List<Map<String, Object>>> {
	}

}
