package com.cafe24.shopmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.shopmall.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	//상품 상세보기
	@GetMapping("/{no}")
	public String getProduct(@PathVariable(value="no")Long no,Model model) {
		model.addAttribute("product", productService.getProduct(no));
		model.addAttribute("category", productService.getCategory());
		return "product/item";
	}
	
	// 상품 카테고리 검색
	@GetMapping("/category/{no}")
	public String getList(@PathVariable(value="no")Long no,Model model) {
		model.addAttribute("product", productService.getList(no));
		model.addAttribute("category", productService.getCategory());
		return "product/list";
	}
}
