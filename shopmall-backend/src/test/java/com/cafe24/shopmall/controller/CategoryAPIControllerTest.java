package com.cafe24.shopmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
	
	/**
	 *  2. 카테고리 조회
	 *  2.1.1 전체카테고리 조회 성공
	 */
	@Test
	public void testCategoryListAllSuccess() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/category"));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data[0].no",is(1)))
		.andExpect(jsonPath("$.data[0].catg_top_no").doesNotExist())
		.andExpect(jsonPath("$.data[0].name",is("상의")))
		;
	}
	
	/**
	 *  2. 카테고리 조회
	 *  2.2.1 하위카테고리 조회 성공
	 */
	@Test
	public void testCategoryListButtomSuccess() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/category/{cate_no}",1));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data[0].no",is(11)))
		.andExpect(jsonPath("$.data[0].catg_top_no",is(1)))
		.andExpect(jsonPath("$.data[0].name",is("블라우스")))
		;
	}
	
	/**
	 *  2. 카테고리 조회
	 *  2.2.2하위카테고리 조회 실패 (없는 번호 또는 최하위 카테고리에서의 조회)
	 */
	@Test
	public void testCategoryListButtomFail() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/category/{cate_no}",-1));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data[0]").doesNotExist())
		;
	}
	
	/**
	 * 3. 카테고리 수정
	 * 3.1.1 
	 */
}
