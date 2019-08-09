package com.cafe24.shopmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.shopmall.service.AdminMemberService;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
	
	@Autowired
	private AdminMemberService memberService;
	
	@GetMapping("")
	public String getList(Model model) {
		model.addAttribute("memberList", memberService.getList());
		return "admin/memberlist";
	}
}
