package com.cafe24.shopmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/member"  )
public class UserController {
	
	@GetMapping( "/login" )
	public String login() {
		return "member/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
}
