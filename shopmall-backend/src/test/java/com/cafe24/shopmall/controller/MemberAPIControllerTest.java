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
import org.junit.Ignore;
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
				.perform(get("/api/member/checkid/{id}", "tgif2013").contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)))
		;
	}

	// 이메일 중복 확인(사용가능)
	@Test
	public void testMemberCheckIdTrue() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/member/checkid/{id}","tgif2014").contentType(MediaType.APPLICATION_JSON));
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
				.andExpect(jsonPath("$.data.postId").exists())
				.andExpect(jsonPath("$.data.deliverFirst").doesNotExist())
				.andExpect(jsonPath("$.data.deliverLast").doesNotExist())
				;
	}

	// 정상적인 회원 가입
	/**
	 * 회원가입 요청
	 * - 우편번호와 배송지는 빈값으로 들어와도 예외처리 되지 않는다.
	 */
	@Rollback(true)
	@Test
	public void testMemberJoin() throws Exception {
		MemberVo memberVo = new MemberVo("zzang9","신짱구","sujni102!S","010-4444-5234","zzang9@naver.com","","","");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo))).andDo(print());
		resultActions.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.memberCode").exists())
		.andExpect(jsonPath("$.data.deliverCode").doesNotExist())
		;
	}
	
	@Rollback(true)
	@Test
	public void testMemberJoinWithDelivers() throws Exception {
		MemberVo memberVo = new MemberVo("zzang9","신짱구","sujni102!S","010-4444-5234","zzang9@naver.com","02546","서울시 서초대로 23","비트교육센터 4층");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo))).andDo(print());
		resultActions.andExpect(status().is2xxSuccessful()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.memberCode").exists())
		.andExpect(jsonPath("$.data.deliverCode").exists())
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
		map.put("id", "tgif2013");
		map.put("password", "sujni102!S");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data",is("MEMBER")))
		;
	}
	
	// 회원정보 가져오기 실패
	@Ignore
	@Test
	public void testMemeberInfoFail() throws Exception {
		Long code = 10L;
		ResultActions resultActions = mockMvc.perform(get("/api/member/{no}",code)).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code").doesNotExist())
		;
	}
	
	// 회원정보 가져오기 성공
	@Ignore
	@Test
	public void testMemeberInfoSuccess() throws Exception {
		Long code = 1L;
		ResultActions resultActions = mockMvc.perform(get("/api/member/{no}",code)).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code",is(code.intValue())))
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
	@Ignore
	@Test
	public void testMemberModifyFailPattern() throws Exception {
		MemberVo vo = new MemberVo(2L, "tgif2013", "수지니#", "", "01-5489-4164", "tgif2014@gmail.","23$32","","");
			
		ResultActions resultActions = mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo))).andDo(print());
			
		resultActions.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.result",is("fail")))
		.andExpect(jsonPath("$.data.code").doesNotExist())
		.andExpect(jsonPath("$.data.id").doesNotExist())
		.andExpect(jsonPath("$.data.name").exists())
		.andExpect(jsonPath("$.data.password").doesNotExist())
		.andExpect(jsonPath("$.data.phone").exists())
		.andExpect(jsonPath("$.data.email").exists())
		.andExpect(jsonPath("$.data.postId").exists())
		.andExpect(jsonPath("$.data.deliverFirst").doesNotExist())
		.andExpect(jsonPath("$.data.deliverLast").doesNotExist())
		;
	}
	
	// 회원 정보 수정  형식 실패(회원정보가 없는 경우)
	@Ignore
	@Test
	public void testMemberModifyFail() throws Exception {
		MemberVo vo = new MemberVo(21L, "tgif2016", "수지니", "Sjini10!", "010-5489-4164", "tgif2014@gmail.com");
		
		ResultActions resultActions = mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo))).andDo(print());
			
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data").doesNotExist())
		;
	}
	
	// 회원정보 수정 성공
	@Ignore
	@Test
	public void testMemberModifySuccess() throws Exception {
		MemberVo vo = new MemberVo(2L, "tgif2013", "수지니", "Sjini10!", "010-5489-4164", "tgif2014@gmail.com","02468","서울시 강남구 테헤란로54","비트교육센터 3층");
			
		ResultActions resultActions = mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo))).andDo(print());
			
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code",is(vo.getCode().intValue())))
		.andExpect(jsonPath("$.data.id",is(vo.getId())))
		.andExpect(jsonPath("$.data.name",is(vo.getName())))
		.andExpect(jsonPath("$.data.password",is(vo.getPassword())))
		.andExpect(jsonPath("$.data.phone",is(vo.getPhone())))
		.andExpect(jsonPath("$.data.email",is(vo.getEmail())))
		.andExpect(jsonPath("$.data.postId",is(vo.getPostId())))
		.andExpect(jsonPath("$.data.deliverFirst",is(vo.getDeliverFirst())))
		.andExpect(jsonPath("$.data.deliverLast",is(vo.getDeliverLast())))
		;
	}
	
	//회원 탈퇴 형식 실패
	@Ignore
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
	@Ignore
	@Test
	public void testMemberDeleteFailAuth() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 4L);
		map.put("password", "sjjin##W");
		
		ResultActions resultActions = mockMvc.perform(delete("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map))).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data",is(false)))
		;
	}
	
	//회원 성공
	@Ignore
	@Test
	public void testMemberDeleteSuccess() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 3L);
		map.put("password", "gilDong$$");
		
		ResultActions resultActions = mockMvc.perform(delete("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map))).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data",is(true)))
		;
	}
}
