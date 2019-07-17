package com.cafe24.shopmall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("userAPIController")
@RequestMapping("/api/member")
@Api(value="ShopMall", description="Member")
public class MemberAPIController {
	
	@Autowired
	private MemberService memberService;
	
	//회원 가입 페이지
	@ApiOperation(value="회원 가입 페이지", notes="회원 가입 페이지 API")
	@GetMapping(value="/join")
	public String userjoinform() {
		return "member/join";
	}
	
	//이메일 중복 체크
	@ApiOperation(value="아이디 중복 체크", notes="아이디 중복 체크 API")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="입력한 아이디",required=true,dataType="query",defaultValue="")
	})
	@GetMapping(value="/checkid/{id}")
	public ResponseEntity<JSONResult> usercheckId(@PathVariable(value="id")String id) {
		
		Boolean result = memberService.existId(id);
		return new ResponseEntity<JSONResult>(JSONResult.success(result), HttpStatus.OK);
	}
	//회원 가입 요청
	@ApiOperation(value="회원 가입",notes="회원 가입 하기")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="아이디",required=true,paramType="query",defaultValue="0"),
		@ApiImplicitParam(name="name",value="이름",required=true,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="password",value="비밀번호",required=true,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="phone",value="전화번호",required=true,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="email",value="이메일",required=true,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="postId",value="우편번호",required=false,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="deliverFirst",value="배송지 기본",required=false,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="deliverLast",value="배송지 상세",required=false,paramType="query",defaultValue="")
	})
	@PostMapping(value="")
	public ResponseEntity<JSONResult> userJoin(@RequestBody @Valid MemberVo memberVo, BindingResult error) {
		if(error.hasErrors()) {
			Map<String,String> errorMessages = new HashMap<String, String>();
			//아이디,이름,비밀번호,휴대전화,이메일
			for(ObjectError index : error.getAllErrors()) {
				FieldError fe = (FieldError)index;
				errorMessages.put(fe.getField(), fe.getDefaultMessage());
			}
			return new ResponseEntity<JSONResult>(JSONResult.fail("입력형식이 유효하지 않습니다.",errorMessages),HttpStatus.BAD_REQUEST);
		}
		Long result = memberService.userAdd(memberVo);
		return new ResponseEntity<JSONResult>(JSONResult.success(result), HttpStatus.OK);
	}
	
	//로그인 페이지 요청
	@ApiOperation(value="로그인 페이지 요청")
	@GetMapping(value="/login")
	public String userLonginForm() {
		return "member/login";
	}
	
	//로그인 요청
	@ApiOperation(value="로그인",notes="로그인")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="아이디",required=true,dataType="query",defaultValue=""),
		@ApiImplicitParam(name="password",value="비밀번호",required=true,dataType="query",defaultValue="")
	})
	@PostMapping(value="/login")
	public ResponseEntity<JSONResult> userLogin(@RequestBody Map<String,Object> map) {
		
		if(((String)map.get("id")).equals("") || ((String)map.get("password")).equals("")) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("아이디와 비밀번호를 입력하시오.", false), HttpStatus.BAD_REQUEST);
		}
		
		String result = memberService.login((String)map.get("id"),(String)map.get("password"));
		return new ResponseEntity<JSONResult>(JSONResult.success(result),HttpStatus.OK);
	}
	
	// 회원 정보 가져오기(수정 페이지에서 사용)
	@ApiOperation(value="회원 정보 가져오기",notes="회원 정보 가져오기")
	@ApiImplicitParams({
		@ApiImplicitParam(name="code",value="회원 코드",required=true,dataType="String",defaultValue="")
	})
	@GetMapping(value="/{no}")
	public JSONResult getUserInfo(@PathVariable(value="no")Long code) {
		MemberVo member = memberService.getMemberInfo(code);
		return JSONResult.success(member);
	}
	
	// 회원 정보 수정
	@ApiOperation(value="회원정보 수정",notes="회원정보 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name="code",value="회원 코드",required=true,paramType="query",defaultValue="0"),
		@ApiImplicitParam(name="id",value="아이디",required=true,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="name",value="이름",required=true,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="password",value="비밀번호",required=true,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="phone",value="전화번호",required=true,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="email",value="이메일",required=true,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="postId",value="우편번호",required=false,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="deliverFirst",value="배송지 기본",required=false,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="deliverLast",value="배송지 상세",required=false,paramType="query",defaultValue="")
	})
	@PutMapping(value="")
	public ResponseEntity<JSONResult> modify(@RequestBody @Valid MemberVo vo,BindingResult error) {
		if(error.hasErrors()) {
			Map<String,String> errorMessages = new HashMap<String, String>();
			//아이디,이름,비밀번호,휴대전화,이메일
			for(ObjectError index : error.getAllErrors()) {
				FieldError fe = (FieldError)index;
				// 비빌번호 null 값 체크
				errorMessages.put(fe.getField(), fe.getDefaultMessage());
			}
				return new ResponseEntity<JSONResult>(JSONResult.fail("입력형식이 유효하지 않습니다.",errorMessages),HttpStatus.BAD_REQUEST);
		}
		
		MemberVo result = memberService.modifyMember(vo);
		return new ResponseEntity<JSONResult>(JSONResult.success(result),HttpStatus.OK);
	}
	
	// 회원 탈퇴 페이지
	@ApiOperation(value="회원 탈퇴 페이지", notes="회원 탈퇴 페이지 API")
	@GetMapping(value="/delete")
	public String deleteForm() {
		return "member/deleteForm";
	}
	
	//회원 탈퇴
	@ApiOperation(value="회원 탈퇴", notes="회원 탈퇴 API")
	@ApiImplicitParams({
		@ApiImplicitParam(name="code",value="회원 코드",required=true,dataType="query",defaultValue=""),
		@ApiImplicitParam(name="password",value="비밀번호",required=true,dataType="query",defaultValue="")
	})
	@DeleteMapping(value="")
	public ResponseEntity<JSONResult> delete(@RequestBody Map<String,Object> map){
		Long code =  map.get("code")==null?null:((Integer)map.get("code")).longValue();
		String password = map.get("password")==null?null:map.get("password").toString();
		
		if(code == null || "".equals(password) || password == null) {
			return new ResponseEntity<JSONResult>(JSONResult.fail("아이디와 비밀번호를 입력하시오.", false), HttpStatus.BAD_REQUEST);
		}
		Boolean isSuccess = memberService.delete(code,password);
		return new ResponseEntity<JSONResult>(JSONResult.success(isSuccess),HttpStatus.OK);
	}
	
}
