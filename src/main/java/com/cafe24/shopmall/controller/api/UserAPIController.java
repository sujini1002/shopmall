package com.cafe24.shopmall.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.service.UserService;
import com.cafe24.shopmall.vo.UserVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("userAPIController")
@RequestMapping("/api/user")
public class UserAPIController {
	
	@Autowired
	private UserService userService;
	
	//회원 가입 페이지
	@ApiOperation(value="회원 가입 페이지")
	@GetMapping(value="/join")
	public String userjoinform() {
		return "user/join";
	}
	
	//이메일 중복 체크
	@ApiOperation(value="아이디 중복 체크")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="입력한 아이디",required=true,dataType="String")
	})
	@GetMapping(value="/checkid/{id}")
	public JSONResult usercheckId(@PathVariable(value="id")String id) {
		Boolean result = userService.existId(id);
		return JSONResult.success(result);
	}
	//회원 가입 요청
	@ApiOperation(value="회원 가입")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userVo",value="회원 입력 사항",required=true,dataType="UserVo")
	})
	@PostMapping(value="")
	public JSONResult userJoin(@RequestBody UserVo userVo) {
		UserVo vo = userService.userAdd(userVo);
		return JSONResult.success(vo);
	}
	
	//로그인 페이지 요청
	@ApiOperation(value="로그인 페이지 요청")
	@GetMapping(value="/login")
	public String userLonginForm() {
		return "redirect:/";
	}
	
	//로그인 요청
	@PostMapping(value="/login")
	public JSONResult userLogin(@RequestBody Map<String,Object> map) {
		Boolean isExist = userService.login((String)map.get("id"),(String)map.get("password"));
		return JSONResult.success(isExist);
	}
	
	// 회원 정보 가져오기(수정 페이지에서 사용)
	@GetMapping(value="/{no}")
	public JSONResult getUserInfo(@PathVariable(value="no")Long no) {
		UserVo user = userService.getUserInfo(no);
		return JSONResult.success(user);
	}
	
	// 회원 정보 수정
	
}
