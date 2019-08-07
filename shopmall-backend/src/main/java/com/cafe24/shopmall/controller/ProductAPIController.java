package com.cafe24.shopmall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("productAPIController")
@RequestMapping("/api/product")
@Api(value = "ShopMall", description = "product")
public class ProductAPIController {
	
	@Autowired
	private ProductService productService;
	
	
	// 상품 상세 보기 
	@ApiOperation(value = "상품 상세보기", notes = "상품 상세보기 API")
	@GetMapping(value = "/view/{no}")
	public ResponseEntity<JSONResult> getProduct() {
		return null;
	}
	
	//상품 카테고리 검색
	@ApiOperation(value = "카테고리 검색", notes = "카테고리 검색 API")
	@GetMapping(value = "/category/{no}")
	public ResponseEntity<JSONResult> getCategoryList() {
		return null;
	}
	
	//상품 목록 조회
	@ApiOperation(value="상품 목록조회", notes="상품 목록 조회 API")
	@GetMapping(value="")
	public ResponseEntity<JSONResult> getList(){
		List<Map<String,Object>> allProduct = productService.list();
		
		return new ResponseEntity<JSONResult>(JSONResult.success(allProduct), HttpStatus.OK);
	}
}
