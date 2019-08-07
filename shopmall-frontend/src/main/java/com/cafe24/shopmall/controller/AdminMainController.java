package com.cafe24.shopmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMainController {
	
	@GetMapping(value= {"/",""})
	public String main() {
		return "admin/index";
	}
}
