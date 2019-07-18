package com.cafe24.shopmall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.vo.ProductVo;

import io.swagger.annotations.Api;

/**
 * 상품과 관련 된 기능을 처리하는 Controller
 * 1. ADMIN만 가능한 기능
 * 	- 상품등록
 * 		- 기본 상품 정보 등록 (하나만 가능)
 * 		- 상품 이미지 등록 (최소 하나 ~여러개 등록 가능) - LIST
 * 		- 옵션등록 (최소 하나 ~여러개 등록 가능) - LIST
 * 		- 옵션 상세등록 (최소 하나 ~여러개 등록 가능) - LIST
 * 		- 옵션 별 상품 재고 등록 (최소 하나 ~여러개 등록 가능) - LIST
 * 	- 상품 수정
 * 	- 상품 삭제
 * 
 *  2. ADMIN과 USER가 모두  가능한 기능
 *   - 상품 상세보기
 *   
 *  3. ADMIN과 USER가 모두 가능한 기능이지만 ADMIN에 추가 기능이 있는 기능
 *   - 상품 검색 분류 목록
 *   	- 모두 가능 : 상품명, 카테고리로 검색
 *   	- ADMIN만 가능 : 상품등록일 , 진열상태 , 판매상태로 검색
 */

@RestController("productAPIController")
@RequestMapping("/api")
@Api(value="ShopMall", description="Product")
public class ProductAPIController {
	
	//상품등록
	@RequestMapping(value="/admin/product")
	@PostMapping
	public ResponseEntity<JSONResult> add(@RequestBody ProductVo productVo){
		
		return new ResponseEntity<JSONResult>(JSONResult.success(null), HttpStatus.OK);
	}
	
	
}
