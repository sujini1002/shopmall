package com.cafe24.shopmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.service.OrderService;
import com.cafe24.shopmall.vo.OrderVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 1. 주문 완료 - 주문한 상품들의 재고를 확인한다. (-1이면 재고 감소 안함) - 주문한 상품들이 다 재고가 있으면 insert한다.
 * - 주문한 상품 재고의 수량을 차감한다. (무한정 재고는 안함) - 결제정보를 insert한다. - 결제 수단이 무통장 입금이면 무통장
 * 테이블에 insert
 *
 */

@RestController("orderAPIController")
@RequestMapping("/api/order")
@Api(value = "ShopMall", description = "order")
public class OrderAPIController {

	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value="주문 등록", notes="주문 등록API")
	@ApiImplicitParams({
		@ApiImplicitParam(name="orderVo",value="주문 정보들",required=true,dataType="query",defaultValue="")
	})
	@PostMapping(value="")
	public ResponseEntity<JSONResult> add(@RequestBody @Valid OrderVo orderVo, BindingResult errors) {
		
		//회원 비회원 구분 모호
		if((orderVo.getMember_code()==null && orderVo.getPassword()==null)||(orderVo.getMember_code()!=null && orderVo.getPassword()!=null)) 
		{
			return new ResponseEntity<JSONResult>(JSONResult.fail("입력형식이 유효하지 않습니다.", null),HttpStatus.BAD_REQUEST);
		}

		if (errors.hasErrors()) {
			Map<String, String> errorMessages = new HashMap<String, String>();
			for (ObjectError index : errors.getAllErrors()) {
				FieldError fe = (FieldError) index;
				errorMessages.put(fe.getField(), fe.getDefaultMessage());
			}
			return new ResponseEntity<JSONResult>(JSONResult.fail("입력형식이 유효하지 않습니다.", errorMessages),HttpStatus.BAD_REQUEST);
		}
		
		Boolean result = orderService.add(orderVo);
		
		if(result==false) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("재고가 없습니다.", null),HttpStatus.OK);
		}
		
		return new ResponseEntity<JSONResult>(JSONResult.success(result), HttpStatus.OK);
	}
	
	//회원 주문 목록 조회  
	@ApiOperation(value="주문 목록 조회 및 상세보기", notes="주문 목록 조회 및 상세보기API")
	@ApiImplicitParams({
		@ApiImplicitParam(name="no",value="회원 번호",required=true,dataType="query",defaultValue="")
	})
	@GetMapping(value= "/member/{no}")
	public ResponseEntity<JSONResult> getList(@PathVariable(value="no")Long no){
		
		List<Map<String,Object>>  result = orderService.getList(no);
		
		if(result.size()==0) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("회원의 주문내역이 존재하지 않습니다.", null), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<JSONResult>(JSONResult.success(result), HttpStatus.OK);
	}
	
	//비회원 주문 목록 
	@ApiOperation(value="주문 목록 조회 및 상세보기", notes="주문 목록 조회 및 상세보기API")
	@ApiImplicitParams({
		@ApiImplicitParam(name="password",value="비밀 번호",required=true,dataType="query",defaultValue=""),
		@ApiImplicitParam(name="order_code",value="주문 번호",required=true,dataType="query",defaultValue="")
	})
	@PostMapping(value= "/none")
	public ResponseEntity<JSONResult> getNoeList(@RequestBody Map<String,String> params){
		
		List<Map<String,Object>>  result = orderService.getList(params.get("password"),params.get("order_code"));
		
		if(result.size()==0) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("회원의 주문내역이 존재하지 않습니다.", null), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<JSONResult>(JSONResult.success(result), HttpStatus.OK);
	}
	
	//주문 상세보기
	@ApiOperation(value="주문 목록 조회 및 상세보기", notes="주문 목록 조회 및 상세보기API")
	@ApiImplicitParams({
		@ApiImplicitParam(name="no",value="비밀 번호",required=true,dataType="query",defaultValue=""),
	})
	@GetMapping(value= "/{no}")
	public ResponseEntity<JSONResult> getView(@PathVariable(value="no")Long no){
		
		List<Map<String,Object>>  result = orderService.getOrder(no);
		
		if(result.size()==0) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("회원의 주문내역이 존재하지 않습니다.", null), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<JSONResult>(JSONResult.success(result), HttpStatus.OK);
	}
	
	//주문 취소
	@ApiOperation(value = "주문 취소", notes = "주문 취소API")
	@DeleteMapping(value= {"/{no}","/{no}/{prd_no}"})
	public ResponseEntity<JSONResult> delete(@PathVariable(value="no") Long no,
											 @PathVariable(value="prd_no") Optional<Long> prd_no) {
		
		Long prdIven_no = prd_no.isPresent()?prd_no.get().longValue():null;
		
		Boolean result = null;
		
		if(prdIven_no==null) {
			result = orderService.deleteAll(no);
		}else {
			result = orderService.deleteOrderProduct(no,prdIven_no);
		}
		
		//결과확인
		if(result == false) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("이미 배송처리 된 상품입니다. 자세한 사항은 고객센터에 문의해 주세요.", null), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<JSONResult>(JSONResult.success(result), HttpStatus.OK);
	}
}
