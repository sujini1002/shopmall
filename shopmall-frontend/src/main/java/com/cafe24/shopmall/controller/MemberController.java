package com.cafe24.shopmall.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.shopmall.service.MemberService;
import com.cafe24.shopmall.vo.MemberVo;

@Controller
@RequestMapping( "/member"  )
public class MemberController {
	
	@Autowired
	private MemberService memberServie;
	
	
	@GetMapping( "/login" )
	public String login() {
		return "member/login";
	}
	
	@GetMapping("/join")
	public String joinform(@ModelAttribute MemberVo memberVo) {
		return "member/join";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute  @Valid MemberVo memberVo, BindingResult errors, Model model) {
		
		if(errors.hasErrors()) {
			model.addAttribute(errors.getModel());
			return "member/join";
		}
		
		model.addAttribute("result",memberServie.add(memberVo));
		return "member/joinsuccess";
	}
}
