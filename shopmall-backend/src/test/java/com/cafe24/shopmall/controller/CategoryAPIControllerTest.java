package com.cafe24.shopmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
		.andExpect(jsonPath("$.data[0].no").exists())
		.andExpect(jsonPath("$.data[0].catg_top_no").doesNotExist())
		.andExpect(jsonPath("$.data[0].name").exists())
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
		.andExpect(jsonPath("$.data[0].no").exists())
		.andExpect(jsonPath("$.data[0].catg_top_no").exists())
		.andExpect(jsonPath("$.data[0].name").exists())
		;
	}
	
	/**
	 *  2. 카테고리 리스트 조회
	 *  2.2.2하위카테고리 조회 실패 (없는 번호 또는 최하위 카테고리에서의 조회)
	 */
	@Test
	public void testCategoryListButtomFail() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/category/{cate_no}",-1));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result",is("fail")))
		.andExpect(jsonPath("$.data").doesNotExist())
		;
	}
	
	/**
	 * 3. 카테고리 수정
	 * 3.1.1 카테고리 정보 가져오기 성공
	 */
	@Test
	public void testCategoryInfoSuccess() throws Exception {
		Integer no = 1;
		ResultActions resultActions = mockMvc.perform(get("/api/admin/category/{cate_no}",no));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.no",is(no)))
		.andExpect(jsonPath("$.data.catg_top_no").doesNotExist())
		.andExpect(jsonPath("$.data.name").exists())
		;
	}
	
	/**
	 * 3. 카테고리 수정
	 * 3.1.2 카테고리 정보 가져오기 실패 (없는 번호)
	 */
	@Test
	public void testCategoryInfoFail() throws Exception {
		Integer no = 0;
		ResultActions resultActions = mockMvc.perform(get("/api/admin/category/{cate_no}",no));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result",is("fail")))
		.andExpect(jsonPath("$.data").doesNotExist())
		;
	}
	
	/**
	 * 3. 카테고리 수정
	 * 3.2.1 카테고리 수정 성공
	 */
	@Rollback(true)
	@Test
	public void testCategoryUpdateSuccess() throws Exception {
		CategoryVo vo = new CategoryVo();
		vo.setNo(51);
		vo.setCatg_top_no(46);
		vo.setName("린넨바지");
		
		ResultActions resultActions = mockMvc.perform(put("/api/admin/category").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.no",is(vo.getNo())))
		.andExpect(jsonPath("$.data.catg_top_no",is(vo.getCatg_top_no())))
		.andExpect(jsonPath("$.data.name",is(vo.getName())))
		;
	}
	
	/**
	 * 3. 카테고리 수정
	 * 3.2.2 카테고리 수정 실패(상위 카테고리가 없는 번호)
	 */
	@Rollback(true)
	@Test
	public void testCategoryUpdateFail() throws Exception {
		CategoryVo vo = new CategoryVo();
		vo.setNo(50);
		vo.setCatg_top_no(0);
		vo.setName("린넨티셔츠");
		
		ResultActions resultActions = mockMvc.perform(put("/api/admin/category").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result",is("fail")))
		.andExpect(jsonPath("$.data.catg_top_no").exists())
		;
	}
	
	/**
	 *  4.1 카테고리 삭제 성공
	 *  
	 */
	@Rollback(true)
	@Test
	public void testCategoryDeleteSuccess() throws Exception {
		Integer no = 46;
		ResultActions resultActions = mockMvc.perform(delete("/api/admin/category/{no}",no));
		
		resultActions
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data",is(true)))
		;
	}
	
	/**
	 *  4.1 카테고리 삭제 실패 ( 없는 번호)
	 *  
	 */
	@Rollback(true)
	@Test
	public void testCategoryDeleteFail() throws Exception {
		Integer no = 0;
		ResultActions resultActions = mockMvc.perform(delete("/api/admin/category/{no}",no));
		
		resultActions
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result",is("fail")))
		.andExpect(jsonPath("$.data").doesNotExist())
		;
	}
}
