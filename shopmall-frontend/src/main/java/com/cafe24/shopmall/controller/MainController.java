package com.cafe24.shopmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.shopmall.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService productService;
	
	@RequestMapping( {"/", "/main"} )
	public String main(Model model) {
		
		//상품 리스트
		model.addAttribute("result", productService.getList());
		
		return "main/index";
	}
}
