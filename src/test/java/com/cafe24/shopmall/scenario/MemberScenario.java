package com.cafe24.shopmall.scenario;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

		resultActions.andExpect(status().is4xxClientError()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.id").doesNotExist()).andExpect(jsonPath("$.data.name").exists())
				.andExpect(jsonPath("$.data.password").exists()).andExpect(jsonPath("$.data.phone").exists())
				.andExpect(jsonPath("$.data.email").exists());
	}

	// 정상적인 회원 가입
	@Test
	public void testJoinSuccess() throws Exception {
		MemberVo memberVo = new MemberVo("tgif2014", "강수진", "Sujni102!", "010-5555-3777", "auclakst@naver.com");

		ResultActions resultActions = mockMvc.perform(
				post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)))
				.andDo(print());

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.code").exists()).andExpect(jsonPath("$.data.id", is(memberVo.getId())))
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
		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")))
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
}
