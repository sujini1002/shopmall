package com.cafe24.shopmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class MemberAPIControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	//이메일 중복 체크
	@Test
	public void testMemberCheckIdTrue() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/member/checkid/{id}","tgif2014").contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data",is(true)));
		;
	}
	/**
	 * 회원가입 요청
	 * - 우편번호와 배송지는 빈값으로 들어와도 예외처리 되지 않는다.
	 */
	@Test
	public void testMemberJoin() throws Exception {
		MemberVo memberVo = new MemberVo("tgif2013","강수진","sujni102!S","010-3423-5677","aufclakstp@naver.com",null,null,null);
		
		ResultActions resultActions = mockMvc.perform(post("/api/member").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(memberVo)));
		resultActions.andExpect(status().is2xxSuccessful()).andDo(print())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.id",is(memberVo.getId())))
		.andExpect(jsonPath("$.data.name",is(memberVo.getName())))
		.andExpect(jsonPath("$.data.password",is(memberVo.getPassword())))
		.andExpect(jsonPath("$.data.phone",is(memberVo.getPhone())))
		.andExpect(jsonPath("$.data.email",is(memberVo.getEmail())))
		.andExpect(jsonPath("$.data.postId").doesNotExist())
		.andExpect(jsonPath("$.data.deliverFirst").doesNotExist())
		.andExpect(jsonPath("$.data.deliverLast").doesNotExist())
		;
	}
	
	//회원 로그인 요청
	@Test
	public void testMemberLogin() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "tgif2014");
		map.put("password", "Sujin10!");
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data",is(true)))
		;
	}
	
	//회원 정보가져오기
	@Test
	public void testMemberInfo() throws Exception {
		Long code = 1L;
		ResultActions resultActions = mockMvc.perform(get("/api/member/{no}",code)).andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.code",is(code.intValue())))
		;
	}
	
	//회원 정보수정
	@Test
	public void testMemberModify() throws Exception {
		MemberVo vo = new MemberVo(2L, "tgif2013", "수지니", "Sjini10%", "010-5489-4164", "tgif2014@gmail.com","02546","서울시 관악구 보라매대로23","교육센터 5층");
		
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
	
	//회원 탈퇴
	@Test
	public void testMemberDelete() throws Exception {
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
