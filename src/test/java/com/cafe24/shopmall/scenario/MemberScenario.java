package com.cafe24.shopmall.scenario;

import static org.hamcrest.Matchers.is;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shopmall.config.AppConfig;
import com.cafe24.shopmall.config.TestWebConfig;
import com.cafe24.shopmall.vo.MemberVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class MemberScenario {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// 회원가입 잘못된 입력 값 테스트
	@Test
	public void testJoinError() throws Exception {
		MemberVo memberVo = new MemberVo("tgif2014", "강수#진", "Sujni102", "01-5555-3777", "aufclakstp@naver.");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)))
				.andDo(print());

		resultActions.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.result", is("fail")))
				.andExpect(jsonPath("$.data.id").doesNotExist())
				.andExpect(jsonPath("$.data.name").exists())
				.andExpect(jsonPath("$.data.password").exists())
				.andExpect(jsonPath("$.data.phone").exists())
				.andExpect(jsonPath("$.data.email").exists());
	}

	// 정상적인 회원 가입
	@Test
	public void testJoinSuccess() throws Exception {
		MemberVo memberVo = new MemberVo("tgif2014", "강수진", "Sujni102!", "010-5555-3777", "auclakst@naver.com");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)))
				.andDo(print());

		resultActions.andExpect(status().isOk())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.code").exists())
				.andExpect(jsonPath("$.data.id", is(memberVo.getId())))
				.andExpect(jsonPath("$.data.name", is(memberVo.getName())))
				.andExpect(jsonPath("$.data.password", is(memberVo.getPassword())))
				.andExpect(jsonPath("$.data.phone", is(memberVo.getPhone())))
				.andExpect(jsonPath("$.data.email", is(memberVo.getEmail())));
	}

	// 이메일 중복 확인(중복일 때)
	@Test
	public void testCheckEmailExist() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/api/member/checkid/{id}", "tgif2014").contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)));
		;
	}

	// 이메일 중복 확인(중복일 때)
	@Test
	public void testCheckEmailNotExist() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/api/member/checkid/{id}", "aufclakstp").contentType(MediaType.APPLICATION_JSON));
		
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(false)));
		;
	}
	
	// 로그인 형식 실패
	@Test
	public void testMemberLoginFailPattern() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "tgif이공일사");
		map.put("password", "");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data",is(false)))
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
		.andExpect(jsonPath("$.data",is(false)))
		;
	}
	
	// 로그인 성공
	@Test
	public void testMemberLoginSuccess() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "tgif2014");
		map.put("password", "Sujin10!");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data",is(true)))
		;
	}
	
	// 회원정보 가져오기 실패
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
	@Test
	public void testMemeberInfoSuccess() throws Exception {
		Long code = 1L;
		ResultActions resultActions = mockMvc.perform(get("/api/member/{no}",code)).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code",is(code.intValue())))
		;
	}
	
	// 회원정보 수정 실패
	
	
	// 회원정보 수정 성공
	@Test
	public void testUserModify() throws Exception {
		MemberVo vo = new MemberVo(2L, "tgif2013", "수지니♥", "jini10", "010-5489-4164", "tgif2014@gmail.com");
			
		ResultActions resultActions = mockMvc.perform(put("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo))).andDo(print());
			
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code",is(vo.getCode().intValue())))
		.andExpect(jsonPath("$.data.id",is(vo.getId())))
		.andExpect(jsonPath("$.data.name",is(vo.getName())))
		.andExpect(jsonPath("$.data.password",is(vo.getPassword())))
		.andExpect(jsonPath("$.data.phone",is(vo.getPhone())))
		.andExpect(jsonPath("$.data.email",is(vo.getEmail())))
		;
	}
}
