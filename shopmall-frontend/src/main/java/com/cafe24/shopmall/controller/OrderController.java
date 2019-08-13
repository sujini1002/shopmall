package com.cafe24.shopmall.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.shopmall.security.SecurityUser;
import com.cafe24.shopmall.service.OrderService;
import com.cafe24.shopmall.vo.OrderVo;


@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/write")
	public String orderStatement(Model model) {
		
		//member_code 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser memberVo = (SecurityUser) authentication.getPrincipal();
		
		//장바구니 리스트 가져오기
		model.addAttribute("cartList", orderService.getCartList(memberVo.getNo()));
		model.addAttribute("member", memberVo);
		
		return "order/write";
	}
	
	//주문완료
	@PostMapping(value="")
	public String orderAdd(@ModelAttribute OrderVo orderVo) {
		orderService.insert(orderVo);
		return "redirect:/";
	}
}
