package com.cafe24.shopmall.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.service.CartService;
import com.cafe24.shopmall.vo.CartVo;

import io.swagger.annotations.Api;

/**
 * ------ 장바구니 ----------
 * 1. 장바구니 추가
 * 2. 장바구니 보기
 * 3. 장바구니 수정
 * 4. 장바구니 삭제
 * -----------------------
 * 1. 장바구니 추가
 * 	 1.1  옵션선택에 따른 상품 재고 번호 가져오기
 * 	 1.2  장바구니 추가(List로 받기)
 * 		1.2.1 회원 번호 조회 후 회원/비회원 결정 - DB쿼리에서 변동 예정
 * 		1.2.2 기존 장바구니에 상품 재고 번호 있는지 조회
 * 		1.2.3 있다면 false로 리턴
 * 		1.2.4 없다면 insert 후 true로 리턴
 * 	 1.3  비회원으로 장바구니 저장한 후 로그인 한 상황
 * 		1.3.1 회원의 장바구니의 상품 재고 번호 조회
 * 		1.3.2 같은 상품 재고가 있는 것은 비회원일 때의 상품 재고의 수량 만큼 더하기
 * 		1.3.3 없는 상품 재고는 insert 
 * 		1.3.4 기존 비회원의 장바구니의 목록은 제거
 * 2. 장바구니 리스트 보기
 * 		2.1.1 회원 번호 조회 후 회원/비회원 결정
 *  	2.1.2 해당 사용자의 장바구니 리스트 ( 상품 이미지, 상품 이름, 상품 가격 , 상품 품목명(상품재고) 수량, 총합(장바구니의 가격))
 *  
 * 3. 장바구니 수정
 * 		3.1 동일한 상품 재고을 장바구니를 다시 담을 때
 * 			3.1.1 수량을 입력 된 수량 값만큼 증가 시킨다.
 * 			3.1.2 증가 된 값만큼 장바구니의 가격을 증가 시킨다.
 * 		3.2 장바구니 페이지에서 수량을 변경
 * 			3.2.1 수량을 입력 된 수량 값만큼 증가 시킨다.
 * 			3.2.2 증가 된 값만큼 장바구니의 가격을 증가 시킨다.
 * 4. 장바구니 삭제
 * 		4.1.1 상품재고 번호와 사용자 번호 또는 세션  id 값을 가져온다.
 * 		4.1.2 장바구니에서 해당 상품 재고를 삭제한다.
 */


@RestController("cartAPIController")
@RequestMapping("/api/cart")
@Api(value="ShopMall", description="cart")
public class CartAPIController {
	
	@Autowired
	private CartService cartService;
	
	
	//1.1  옵션선택에 따른 상품 재고 번호 가져오기
	@PostMapping(value="/find")
	public ResponseEntity<JSONResult> findInventoryNo(@RequestBody Map<String,Object> params){
		
		
		//입력 값이 없을 때
		if(params.get("opt_value") == null || "".equals(params.get("opt_value")) || params.get("prd_no")==null) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("해당 상품이 존재하지 않습니다.",null),HttpStatus.BAD_REQUEST);
		}
		
		
		//상품 재고 번호 가져오기
		Long productInventoryNo = cartService.findInventoryNo(params.get("opt_value").toString(),((Integer)params.get("prd_no")).longValue());
		System.out.println(params.get("opt_value").toString());
		
		if(productInventoryNo==null) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("해당 상품이 존재하지 않습니다.",null),HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<JSONResult>(JSONResult.success(productInventoryNo), HttpStatus.OK);
	}
	
	@PostMapping(value="",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResult> add(@RequestBody CartVo cartVo){
		return new ResponseEntity<JSONResult>(JSONResult.success(null), HttpStatus.OK);
	}
}
