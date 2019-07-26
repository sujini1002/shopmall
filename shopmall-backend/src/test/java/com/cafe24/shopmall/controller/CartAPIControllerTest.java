package com.cafe24.shopmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.cafe24.shopmall.vo.CartVo;
import com.google.gson.Gson;


/**
 * ------ 장바구니 ----------
 * 1. 장바구니 추가
 * 2. 장바구니 보기
 * 3. 장바구니 수정
 * 4. 장바구니 삭제
 * -----------------------
 * 1. 장바구니 추가
 * 	 1.1  옵션선택에 따른 상품 재고 번호 가져오기 (Ajax)
 * 	 1.2  장바구니 추가(List로 받기)
 * 		1.2.1 회원 번호 조회 후 회원/비회원 결정 - DB쿼리에서 변동 예정
 * 		1.2.2 기존 장바구니에 상품 재고 번호 있는지 조회
 * 		1.2.3 있다면 false로 리턴
 * 		1.2.4 없다면 insert 후 true로 리턴
 * 	 1.3  비회원으로 장바구니 저장한 후 로그인 한 상황
 * 		1.3.1 회원의 장바구니의 상품 재고 번호 조회
 * 		1.3.2 같은 상품 재고가 있는 것은 비회원일 때의 상품 재고의 수량 만큼 더하기
 * 		1.3.3 없는 상품 재고는 insert 
 * 		1.3.4 기존 비회원의 장바구니의 목록은 제거
 * 2. 장바구니 리스트 보기
 * 		2.1.1 회원 번호 조회 후 회원/비회원 결정
 *  	2.1.2 해당 사용자의 장바구니 리스트 ( 상품 이미지, 상품 이름, 상품 가격 , 상품 품목명(상품재고) 수량, 총합(장바구니의 가격))
 *  
 * 3. 장바구니 수정
 * 		3.1 동일한 상품 재고을 장바구니를 다시 담을 때
 * 			3.1.1 수량을 입력 된 수량 값만큼 증가 시킨다.
 * 			3.1.2 증가 된 값만큼 장바구니의 가격을 증가 시킨다.
 * 		3.2 장바구니 페이지에서 수량을 변경
 * 			3.2.1 수량을 입력 된 수량 값만큼 증가 시킨다.
 * 			3.2.2 증가 된 값만큼 장바구니의 가격을 증가 시킨다.
 * 4. 장바구니 삭제
 * 		4.1.1 상품재고 번호와 사용자 번호 또는 세션  id 값을 가져온다.
 * 		4.1.2 장바구니에서 해당 상품 재고를 삭제한다.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, TestWebConfig.class })
@WebAppConfiguration
@Transactional
public class CartAPIControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * 1.1.1 장바구니 상품 재고 번호 추가를 위한 상품 재고 번호 찾기 성공
	 */
	@Test
	public void testFindProductInventoryNo() throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("opt_value", "L/블랙");
		params.put("prd_no", 114L);
		
		ResultActions resultActions = mockMvc.perform(post("/api/cart/find")
														.contentType(MediaType.APPLICATION_JSON)
														.content(new Gson().toJson(params)));
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data").exists())
		;
	}
	/**
	 * 1.1.2 없는 상품 재고 번호 (실패) 
	 */
	@Test
	public void testFindProductInventoryNoFail() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("opt_value", "L/핑크");
		params.put("prd_no", 114L);
		
		ResultActions resultActions = mockMvc.perform(post("/api/cart/find")
														.contentType(MediaType.APPLICATION_JSON)
														.content(new Gson().toJson(params)));
		
		resultActions.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data").doesNotExist())
		;
	}
	
	/**
	 * 1.1.3 null 값
	 */
	@Test
	public void testFindProductInventoryNoFailNull() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("opt_value", "");
		params.put("prd_no", null);
		
		ResultActions resultActions = mockMvc.perform(post("/api/cart/find")
														.contentType(MediaType.APPLICATION_JSON)
														.content(new Gson().toJson(params)));
		
		resultActions.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data").doesNotExist())
		;
	}
	
	/**
	 * 1.2.1 회원 장바구니 추가 성공
	 */
	@Rollback(true)
	@Test
	public void testMemberCartInsert() throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberNo", 2L);
		
		List<CartVo> cartList = new ArrayList<CartVo>();
		
		cartList.add(new CartVo(null, 238L, null, 1, 13000));
		cartList.add(new CartVo(null, 239L, null, 2, 26000));
		
		params.put("cartList", cartList);
		
		
		
		ResultActions resultActions = mockMvc.perform(post("/api/cart")
													.contentType(MediaType.APPLICATION_JSON)
													.content(new Gson().toJson(params)));
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
//		.andExpect(jsonPath("$.data").exists())
		;
	}
	
}
