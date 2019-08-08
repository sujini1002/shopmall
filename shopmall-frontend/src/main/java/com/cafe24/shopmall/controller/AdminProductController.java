package com.cafe24.shopmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.shopmall.service.AdminProductService;
import com.cafe24.shopmall.vo.ProductVo;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
	
	@Autowired
	private AdminProductService adminProductService;

	//상품등록페이지
	@GetMapping("")
	public String addform(Model model) {
		model.addAttribute("category", adminProductService.getCategory());
		return "admin/productform";
	}
	
	//상품등록
	@PostMapping("")
	public String add(@ModelAttribute ProductVo productVo) {
		
		System.out.println(productVo);
		Boolean result = adminProductService.add(productVo);
		
		if(result) {
			return "admin/index";
		}
		
		return null;
	}
	
}
