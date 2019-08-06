package com.cafe24.shopmall.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shopmall.domain.User;
import com.cafe24.shopmall.dto.JSONResult;

@RestController
//@RequestMapping("/api")
public class MainAPIController {
	@GetMapping("/hello")
	public ResponseEntity<com.cafe24.shopmall.dto.JSONResult> hello(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success("Hello World"));
	}

	@PostMapping("/hello2")
	public ResponseEntity<JSONResult> hello2(@RequestBody User user){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success("Hello World2"));
	}

	@GetMapping("/hello3")
	public ResponseEntity<JSONResult> hello3(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JSONResult.success("Hello World3"));
	}
}
