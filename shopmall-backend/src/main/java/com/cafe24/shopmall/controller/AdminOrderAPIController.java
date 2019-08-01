package com.cafe24.shopmall.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shopmall.dto.JSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("AdminOrderAPIController")
@RequestMapping("/api/admin/order")
@Api(value = "ShopMall", description = "adminOrder")
public class AdminOrderAPIController {
	// 주문 목록 조회
	@ApiOperation(value="주문 목록 조회", notes="주문 목록 조회 API")
	@GetMapping(value= {""})
	public ResponseEntity<JSONResult> getList(){
		return null;
	}
	// 주문 상세보기
	@ApiOperation(value="주문 상세 정보", notes="주문 상세 정보 API")
	@GetMapping(value= {"no"})
	public ResponseEntity<JSONResult> getOrder(){
		return null;
	}
	// 주문 수정
	@ApiOperation(value="주문 정보 수정", notes="주문 정보 수정 API")
	@PutMapping(value= "")
	public ResponseEntity<JSONResult> modify(){
		return null;
	}
	// 주문 취소
	@ApiOperation(value="주문 취소", notes="주문 취소API")
	@DeleteMapping(value= "")
	public ResponseEntity<JSONResult> delete(){
		return null;
	}
}
