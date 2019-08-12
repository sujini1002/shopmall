package com.cafe24.shopmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.shopmall.security.SecurityUser;
import com.cafe24.shopmall.service.CartService;
import com.cafe24.shopmall.vo.CartVo;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping(value="")
	public String add(@ModelAttribute CartVo cartVo) {
		//member_code 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser memberVo = (SecurityUser) authentication.getPrincipal();
		// 장바구니 추가
		cartService.add(cartVo,memberVo.getNo());
		
		return "redirect:/cart/"+memberVo.getNo();
	}
	
	//장바구니 리스트
	@GetMapping(value="/{no}")
	public String list(@PathVariable(value="no")Long no,Model model) {
		model.addAttribute("cartList", cartService.getList(no));
		return "cart/cartlist";
	}
}
