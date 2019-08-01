package com.cafe24.shopmall.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shopmall.dto.JSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("productAPIController")
@RequestMapping("/api/product")
@Api(value = "ShopMall", description = "product")
public class ProductAPIController {
	//상품 검색
	@ApiOperation(value="상품 검색", notes="상품 검색 API")
	@GetMapping(value= "")
	public ResponseEntity<JSONResult> getList(){
		return null;
	}
	
	// 상품 상세 보기 
	@ApiOperation(value = "상품 상세보기", notes = "상품 상세보기 API")
	@GetMapping(value = "{no}")
	public ResponseEntity<JSONResult> getProduct() {
		return null;
	}
	
	//상품 카테고리 검색
	@ApiOperation(value = "카테고리 검색", notes = "카테고리 검색 API")
	@GetMapping(value = "/category/{no}")
	public ResponseEntity<JSONResult> getCategoryList() {
		return null;
	}
}
