package com.cafe24.shopmall.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.service.MemberService;
import com.cafe24.shopmall.vo.MemberVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("userAPIController")
@RequestMapping("/api/member")
public class MemberAPIController {
	
	@Autowired
	private MemberService memberService;
	
	//회원 가입 페이지
	@ApiOperation(value="회원 가입 페이지")
	@GetMapping(value="/join")
	public String userjoinform() {
		return "member/join";
	}
	
	//이메일 중복 체크
	@ApiOperation(value="아이디 중복 체크")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="입력한 아이디",required=true,dataType="String")
	})
	@GetMapping(value="/checkid/{id}")
	public JSONResult usercheckId(@PathVariable(value="id")String id) {
	
		Boolean result = memberService.existId(id);
		return JSONResult.success(result);
	}
	//회원 가입 요청
	@ApiOperation(value="회원 가입")
	@ApiImplicitParams({
		@ApiImplicitParam(name="memberVo",value="회원 입력 사항",required=true,dataType="UserVo")
	})
	@PostMapping(value="")
	public ResponseEntity<JSONResult> userJoin(@RequestBody @Valid MemberVo userVo, BindingResult error) {
		if(error.hasErrors()) {
			Map<String,String> errorMessages = new HashMap<String, String>();
			//아이디,이름,비밀번호,휴대전화,이메일
			for(ObjectError index : error.getAllErrors()) {
				FieldError fe = (FieldError)index;
				errorMessages.put(fe.getField(), fe.getDefaultMessage());
			}
			return new ResponseEntity<JSONResult>(JSONResult.fail("입력형식이 유효하지 않습니다.",errorMessages),HttpStatus.BAD_REQUEST);
		}
		MemberVo vo = memberService.userAdd(userVo);
		return new ResponseEntity<JSONResult>(JSONResult.success(vo), HttpStatus.OK);
	}
	
	//로그인 페이지 요청
	@ApiOperation(value="로그인 페이지 요청")
	@GetMapping(value="/login")
	public String userLonginForm() {
		return "member/login";
	}
	
	//로그인 요청
	@PostMapping(value="/login")
	public ResponseEntity<JSONResult> userLogin(@RequestBody Map<String,Object> map) {
		
		if(((String)map.get("id")).equals("") || ((String)map.get("password")).equals("")) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("아이디와 비밀번호를 입력하시오.", false), HttpStatus.BAD_REQUEST);
		}
		
		Boolean isExist = memberService.login((String)map.get("id"),(String)map.get("password"));
		return new ResponseEntity<JSONResult>(JSONResult.success(isExist),HttpStatus.OK);
	}
	
	// 회원 정보 가져오기(수정 페이지에서 사용)
	@GetMapping(value="/{no}")
	public JSONResult getUserInfo(@PathVariable(value="no")Long no) {
		MemberVo member = memberService.getMemberInfo(no);
		return JSONResult.success(member);
	}
	
	// 회원 정보 수정
	@PutMapping(value="")
	public ResponseEntity<JSONResult> modify(@RequestBody @Valid MemberVo vo,BindingResult error) {
		
		if(error.hasErrors()) {
			Map<String,String> errorMessages = new HashMap<String, String>();
			//아이디,이름,비밀번호,휴대전화,이메일
			for(ObjectError index : error.getAllErrors()) {
				FieldError fe = (FieldError)index;
				// 비빌번호 null 값 체크
				if("password".equals(fe.getField()) && "".equals(fe.getRejectedValue())){continue;}
				errorMessages.put(fe.getField(), fe.getDefaultMessage());
			}
			return new ResponseEntity<JSONResult>(JSONResult.fail("입력형식이 유효하지 않습니다.",errorMessages),HttpStatus.BAD_REQUEST);
		}
		
		MemberVo result = memberService.modifyMember(vo);
		return new ResponseEntity<JSONResult>(JSONResult.success(result),HttpStatus.OK);
	}
}
