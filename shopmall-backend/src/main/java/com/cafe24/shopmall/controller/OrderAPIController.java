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
import com.cafe24.shopmall.service.OrderService;
import com.cafe24.shopmall.vo.OrderVo;

import io.swagger.annotations.Api;

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
	
	@PostMapping(value="")
	public ResponseEntity<JSONResult> add(@RequestBody @Valid OrderVo orderVo, BindingResult errors) {

		if (errors.hasErrors()) {
			Map<String, String> errorMessages = new HashMap<String, String>();
			for (ObjectError index : errors.getAllErrors()) {
				FieldError fe = (FieldError) index;
				errorMessages.put(fe.getField(), fe.getDefaultMessage());
			}
			return new ResponseEntity<JSONResult>(JSONResult.fail("입력형식이 유효하지 않습니다.", errorMessages),HttpStatus.BAD_REQUEST);
		}
		
		Boolean result = orderService.add(orderVo);
		
		return new ResponseEntity<JSONResult>(JSONResult.success(result), HttpStatus.OK);
	}
}
