package com.cafe24.shopmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shopmall.config.TestAppConfig;
import com.cafe24.shopmall.config.TestWebConfig;
import com.cafe24.shopmall.vo.MemberVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, TestWebConfig.class })
@WebAppConfiguration
@Transactional
public class MemberAPIControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	// 이메일 중복 확인(중복일 때)
	@Test
	public void testCheckEmailExist() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/api/member/checkid/{id}", "tgif2014").contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)))
		;
	}

	// 이메일 중복 확인(사용가능)
	@Test
	public void testMemberCheckIdTrue() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/member/checkid/{id}","yuri1234").contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data",is(false)));
		;
	}
	
	// 회원가입 잘못된 입력 값 테스트
	@Test
	public void testJoinError() throws Exception {
		MemberVo memberVo = new MemberVo("tgi@#$f20$14", "강수#진", "Sujni102", "01-5555-3777", "aufclakstp@naver.","02가234","","");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)))
				.andDo(print());

		resultActions.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.data.id").exists())
				.andExpect(jsonPath("$.data.name").exists())
				.andExpect(jsonPath("$.data.password").exists())
				.andExpect(jsonPath("$.data.phone").exists())
				.andExpect(jsonPath("$.data.email").exists())
				.andExpect(jsonPath("$.data.postid").exists())
				.andExpect(jsonPath("$.data.base_deliver").doesNotExist())
				.andExpect(jsonPath("$.data.detail_deliver").doesNotExist())
				;
	}

	// 정상적인 회원 가입
	/**
	 * 회원가입 요청
	 * - 우편번호와 배송지는 빈값으로 들어와도 예외처리 되지 않는다.
	 */
//	@Ignore
	@Rollback(true)
	@Test
	public void testMemberJoin() throws Exception {
		MemberVo memberVo = new MemberVo("connan12","내이름은코난","sujni102!S","010-7777-1234","connan@gmail.com","","","");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo))).andDo(print());
		resultActions.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data").exists())
		;
	}
	
//	@Ignore
	@Rollback(true)
	@Test
	public void testMemberJoinWithDelivers() throws Exception {
		MemberVo memberVo = new MemberVo("rose123","홍장미","sujni102!S","010-2222-9999","roseheart@naver.com","02546","서울시 서초대로 23","비트교육센터 4층");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo))).andDo(print());
		resultActions.andExpect(status().is2xxSuccessful()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data").exists())
		;
	}
	
	// 로그인 형식 실패
	@Test
	public void testMemberLoginFailPattern() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "");
		map.put("password", "");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.message",is("아이디와 비밀번호를 입력하시오.")))
		;
	}
	
	// 로그인 아이디 비밀번호 인증 실패
	@Test
	public void testMemberLoginFail() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "tgif2014");
		map.put("password", "qkqhzz");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data").doesNotExist())
		;
	}
	
	// 로그인 성공
	@Test
	public void testMemberLogin() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "tgif2014");
		map.put("password", "Enomhoot%12^^");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data",is("ROLE_USER")))
		;
	}
	
	// 회원정보 가져오기 실패
	@Test
	public void testMemeberInfoFail() throws Exception {
		Long code = 0L;
		ResultActions resultActions = mockMvc.perform(get("/api/member/{no}",code)).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code").doesNotExist())
		;
	}
	
	// 회원정보 가져오기 성공
	@Test
	public void testMemeberInfoSuccess() throws Exception {
		Long code = 3L;
		ResultActions resultActions = mockMvc.perform(get("/api/member/{no}",code)).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code",is(code.intValue())))
		.andExpect(jsonPath("$.data.id").exists())
		.andExpect(jsonPath("$.data.name").exists())
		.andExpect(jsonPath("$.data.password").doesNotExist())
		.andExpect(jsonPath("$.data.phone").exists())
		.andExpect(jsonPath("$.data.email").exists())
		.andExpect(jsonPath("$.data.postid").exists())
		.andExpect(jsonPath("$.data.base_deliver").exists())
		.andExpect(jsonPath("$.data.detail_deliver").exists())
		;
	}
	
	/**
	 * 회원정보 수정 실패 경우 : 
	 * 1. 휴대전화 번호가 형식에 맞지 않거나 빈 값일 때
	 * 2. 이메일 형식이 형식에 맞지 않거나 빈 값일 때
	 * 3. 비밀번호 값이 형식에 맞지 않을 때
	 * 4. 우편번호 값이 형식에 맞지 않을 때
	 * - 비고 : 비밀번호가 빈 값이면 변경하지 않는 걸로 간주한다.
	 * - 비고 : 우편번호는 빈 값을 허용한다.
	 */
	// 회원 정보 수정  형식 실패
	@Test
	public void testMemberModifyFailPattern() throws Exception {
		MemberVo vo = new MemberVo(3L, "tgif2014", "수지니#", "", "01-5489-4164", "tgif2014@gmail.","23$32","","");
			
		ResultActions resultActions = mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo))).andDo(print());
			
		resultActions.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.result",is("fail")))
		.andExpect(jsonPath("$.data.code").doesNotExist())
		.andExpect(jsonPath("$.data.id").doesNotExist())
		.andExpect(jsonPath("$.data.name").exists())
		.andExpect(jsonPath("$.data.password").exists())
		.andExpect(jsonPath("$.data.phone").exists())
		.andExpect(jsonPath("$.data.email").exists())
		.andExpect(jsonPath("$.data.postid").exists())
		.andExpect(jsonPath("$.data.base_deliver").doesNotExist())
		.andExpect(jsonPath("$.data.detail_deliver").doesNotExist())
		;
	}
	
	// 회원 정보 수정  형식 실패(회원정보가 없는 경우)
	@Test
	public void testMemberModifyFail() throws Exception {
		MemberVo vo = new MemberVo(0L, "aufclaktp", "수지니", "Sjini10!", "010-5489-4164", "tgif2014@gmail.com");
		
		ResultActions resultActions = mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo))).andDo(print());
			
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data").doesNotExist())
		;
	}
	
	// 회원정보 수정 성공
//	@Ignore
	@Test
	public void testMemberModifySuccess() throws Exception {
		MemberVo vo = new MemberVo(3L, "aufclakspt", "홍길동", "Suzini!!324", "010-1234-1234", "kixxit9512@gmail.com","02614","서울시 강남구 대치도로23","비트교육센터 4층");
			
		ResultActions resultActions = mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo))).andDo(print());
			
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code",is(vo.getCode().intValue())))
		.andExpect(jsonPath("$.data.id",is(vo.getId())))
		.andExpect(jsonPath("$.data.name",is(vo.getName())))
		.andExpect(jsonPath("$.data.phone",is(vo.getPhone())))
		.andExpect(jsonPath("$.data.email",is(vo.getEmail())))
		.andExpect(jsonPath("$.data.postid",is(vo.getPostid())))
		.andExpect(jsonPath("$.data.base_deliver",is(vo.getBase_deliver())))
		.andExpect(jsonPath("$.data.detail_deliver",is(vo.getDetail_deliver())))
		;
	}
	//회원 탈퇴 null 값
	@Test
	public void testMemberDeleteFailNull() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", null);
		map.put("password", null);
		
		ResultActions resultActions = mockMvc.perform(delete("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map))).andDo(print());
		
		resultActions.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data",is(false)))
		;
	}
	//회원 탈퇴 (회원 인증 실패)
	@Test
	public void testMemberDeleteFailAuth() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 3L);
		map.put("password", "sjjin##W");
		
		ResultActions resultActions = mockMvc.perform(delete("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map))).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data",is(false)))
		;
	}
	
	//회원 탈퇴 성공
//	@Ignore
	@Test
	public void testMemberDeleteSuccess() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 3L);
		map.put("password", "Enomhoot%12^^");
		
		ResultActions resultActions = mockMvc.perform(delete("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map))).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data",is(true)))
		;
	}
}
