package com.cafe24.shopmall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.cafe24.shopmall.service.CategoryService;
import com.cafe24.shopmall.vo.CategoryVo;

import io.swagger.annotations.Api;

/**
 *  카테고리 Controller
 *   - 상품을 분류하기 위한 기능이다.
 *   - 하위카테고리들은 상위카테고리 번호를 가지고 있다.
 *   - 최상위 카테고리들은 상위카테고리 번호가 null 이다.
 *   
 *   - 필드 : 카테고리 번호 (pk), 상위카테고리 번호, 카테고리명
 *   
 *  1. ADMIN만 가능한 기능 
 *  	- 카테고리 등록 
 *  		- 카테고리 명은  null 허용 X
 *  		- 하위카테고리의 상위카테고리 번호는 존재해야 한다.
 *  	- 카테고리 수정
 *  	- 카테고리 삭제
 *  
 *  2. USER와 ADMIN 모두 가능한 기능
 *  	- 카테고리 조회
 *
 */

@RestController("categoryAPIController")
@RequestMapping("/api")
@Api(value="ShopMall", description="category")
public class CategoryAPIController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(value="/admin/category")
	public ResponseEntity<JSONResult> add(@RequestBody @Valid CategoryVo categoryVo, BindingResult errors){
		
		if(errors.hasErrors()) {
			Map<String,String> errorMessages = new HashMap<String, String>();
			for(ObjectError index : errors.getAllErrors()) {
				FieldError fe = (FieldError)index;
				errorMessages.put(fe.getField(), fe.getDefaultMessage());
			}
			return new ResponseEntity<JSONResult>(JSONResult.fail("입력 값이 올바르지 않습니다.",errorMessages),HttpStatus.BAD_REQUEST);
		}
		
		Integer no = categoryService.add(categoryVo);
		return new ResponseEntity<JSONResult>(JSONResult.success(no),HttpStatus.OK);
	}
	
}
