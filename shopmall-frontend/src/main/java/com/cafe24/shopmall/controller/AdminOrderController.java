package com.cafe24.shopmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.shopmall.service.AdminOrderService;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
	
	@Autowired
	private AdminOrderService adminorderService;
	
	@GetMapping(value="")
	public String list(Model model) {
		model.addAttribute("orders", adminorderService.list());
		return "admin/orderlist";
	}
}
