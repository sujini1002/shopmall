package com.cafe24.shopmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
import com.cafe24.shopmall.vo.CategoryVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, TestWebConfig.class })
@WebAppConfiguration
@Transactional
public class CategoryAPIControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 *  1. 카테고리 등록
	 *  1.1.1 최상위 카테고리 등록 성공
	 */
	@Rollback(true)
	@Test
	public void testCatgoryAddSuccess() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName("하의");
		
		ResultActions resultActions = mockMvc.perform(post("/api/admin/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo))); 
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data").exists())
		;
		
	}
	
	/**
	 *  1.1.2 최상위 카테고리 등록 실패
	 */
	@Test
	public void testCatgoryAddFail() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName(null);
		
		ResultActions resultActions = mockMvc.perform(post("/api/admin/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo))); 
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data.name").exists())
		;
	}
	
	/**
	 *  1.2.1 하위 카테고리 등록 성공
	 */
	@Rollback(true)
	@Test
	public void testCatgoryAddButtomSuccess() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setCatg_top_no(1);
		categoryVo.setName("블라우스");
		
		ResultActions resultActions = mockMvc.perform(post("/api/admin/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo))); 
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data").exists())
		;
	}
	
	/**
	 *  1.2.2 하위 카테고리 등록 실패
	 */
	@Rollback(true)
	@Test
	public void testCatgoryAddButtomFail() throws Exception {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setCatg_top_no(0);
		categoryVo.setName("블라우스");
		
		ResultActions resultActions = mockMvc.perform(post("/api/admin/category")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(categoryVo))); 
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data.catg_top_no").exists())
		;
	}
}
