package com.cafe24.shopmall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.cafe24.shopmall.vo.UserVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class UserAPIControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	//이메일 중복 체크 (사용불가능)
	@Test
	public void testUserCheckIdTrue() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/user/checkid/{id}","tgif2014").contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data",is(true)));
		;
	}
	//회원가입 요청
	@Test
	public void testUserJoin() throws Exception {
		UserVo userVo = new UserVo("tgif2014","강수진","sujni10","010-5180-3170","aufclakstp@naver.com");
		
		ResultActions resultActions = mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo)));
		resultActions.andExpect(status().is2xxSuccessful()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.id",is(userVo.getId())))
		.andExpect(jsonPath("$.data.name",is(userVo.getName())))
		;
	}
	
	//회원 로그인 요청
	@Test
	public void testUserLogin() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "tgif2014");
		map.put("password", "sujin10");
		
		ResultActions resultActions = mockMvc.perform(post("/api/user/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data",is(true)))
		;
	}
	
	//회원 정보가져오기
	@Test
	public void testUserInfo() throws Exception {
		Long code = 1L;
		ResultActions resultActions = mockMvc.perform(get("/api/user/{no}",code)).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code",is(code.intValue())))
		;
	}
	
	//회원 정보수정
	public void testUserModify() {
		
	}
	
}
