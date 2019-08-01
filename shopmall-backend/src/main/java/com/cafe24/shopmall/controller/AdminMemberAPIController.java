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


@RestController("AdminMemberAPIController")
@RequestMapping("/api/admin/member")
@Api(value = "ShopMall", description = "adminMember")
public class AdminMemberAPIController {
	
	//고객 목록 조회
	@ApiOperation(value="고객 목록 보기", notes="고객 목록 보기 API")
	@GetMapping(value= {""})
	public ResponseEntity<JSONResult> getList(){
		return null;
	}
	//고객 상세보기
	@ApiOperation(value="고객 상세 보기", notes="고객 상세 보기 API")
	@GetMapping(value= {"no"})
	public ResponseEntity<JSONResult> getMember(){
		return null;
	}
	//고객 수정
	@ApiOperation(value="고객 정보 수정", notes="고객 정보 수정 API")
	@PutMapping(value= {""})
	public ResponseEntity<JSONResult> modify(){
		return null;
	}
	
	//고객 탈퇴 처리
	@ApiOperation(value="고객 탈퇴", notes="고객 탈퇴 API")
	@DeleteMapping(value= {""})
	public ResponseEntity<JSONResult> delete(){
		return null;
	}
}
