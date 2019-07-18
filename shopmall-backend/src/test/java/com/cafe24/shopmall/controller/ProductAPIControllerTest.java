package com.cafe24.shopmall.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shopmall.config.TestAppConfig;
import com.cafe24.shopmall.config.TestWebConfig;
import com.cafe24.shopmall.vo.ProductVo;
import com.google.gson.Gson;

/**
 * 상품과 관련하여 테스트하는 기능
 * 1. ADMIN만 가능한 기능
 * 	- 상품등록
 * 		- 기본 상품 정보 등록 (하나만 가능)
 * 		- 상품 이미지 등록 (최소 하나 ~여러개 등록 가능) - LIST
 * 		- 옵션등록 (최소 하나 ~여러개 등록 가능) - LIST
 * 		- 옵션 상세등록 (최소 하나 ~여러개 등록 가능) - LIST
 * 		- 옵션 별 상품 재고 등록 (최소 하나 ~여러개 등록 가능) - LIST
 * 	- 상품 수정
 * 	- 상품 삭제
 * 
 *  2. ADMIN과 USER가 모두  가능한 기능
 *   - 상품 상세보기
 *   
 *  3. ADMIN과 USER가 모두 가능한 기능이지만 ADMIN에 추가 기능이 있는 기능
 *   - 상품 검색 분류 목록
 *   	- 모두 가능 : 상품명, 카테고리로 검색
 *   	- ADMIN만 가능 : 상품등록일 , 진열상태 , 판매상태로 검색
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, TestWebConfig.class })
@WebAppConfiguration
@Transactional
public class ProductAPIControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	public ProductVo construtor() {
		
		return null;
	}
	
	/**
	 *  상품등록 test case 경우
	 *  1. 상품 이미지, 옵션, 옵션 상세, 상품 재고 중 하나라도 null 값이나 없을 때  Custom Valid 필요
	 *  2. 상품 이미지가 하나일 때
	 *  3. 상품 이미지가 여러개 일 때
	 *  4. 옵션이 없을 때 (옵션 상세는 할 필요 없다.) -  상품 재고의 행 추가는 1만 나타나고 옵션의 옵션명 과 상품재고의  품목명은 default 다.
	 *  5. 옵션이 하나 일 때
	 *  6. 옵션이 둘 이상 일 때
	 *  7. 옵션이 n과 옵션 상세가 m개 일때 상품재고 행 추가 갯수가 n * m 이 되야하는 경우 확인  
	 *  8. 상품, 상품 이미지, 옵션, 옵션 상세, 상품재고 테이블에 정확한 값이 추가될 경우
	 */
	@Test
	public void testProductAdd() throws Exception {
		ProductVo productVo = new ProductVo();
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/admin/product/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVo)))
				.andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		;
	}
}
