package com.cafe24.shopmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

	@GetMapping("")
	public String add() {
		return "admin/productform";
	}
	
}
