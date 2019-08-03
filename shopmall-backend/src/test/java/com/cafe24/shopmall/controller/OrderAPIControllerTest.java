package com.cafe24.shopmall.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shopmall.vo.DepositVo;
import com.cafe24.shopmall.vo.OrderProductVo;
import com.cafe24.shopmall.vo.OrderVo;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderAPIControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	public OrderVo constructor() {
		
		List<OrderProductVo> orderProductList = new ArrayList<OrderProductVo>();
		
		orderProductList.add(new OrderProductVo(null,1L,1,null,null));
		orderProductList.add(new OrderProductVo(null,2L,1,null,null));
		orderProductList.add(new OrderProductVo(null,3L,1,null,null));
		
		
		
		OrderVo vo = new OrderVo();
		vo.setMember_code(2L);
		vo.setDeliver("서울시 강남구 서초대로23 비트교육 센터 3층");
		vo.setName("강수진");
		vo.setPhone("010-4895-2549");
		vo.setEmail("tgif@naver.com");
		vo.setRev_name("홍길동");
		vo.setRev_deliver("서울시 강남구 서초대로23 비트교육센터 4층");
		vo.setRev_phone("010-5468-3496");
		vo.setDeliver_price(2500);
		vo.setStatus("입금전");
		vo.setPayment(75000);
		vo.setPay_way("무통장입금");
		vo.setOrderProductList(orderProductList);
		vo.setDepositVo(new DepositVo(null,"우리은행", "강수진", "2019-073-120190"));
		
		return vo;
	}
	
	@Rollback(true)
	@Test
	public void 무통장입금주문등록성공() throws Exception {
		
		OrderVo vo = constructor();
		
		ResultActions resultActions = mockMvc.perform(post("/api/order")
													.contentType(MediaType.APPLICATION_JSON)
													.content(new Gson().toJson(vo))
													);
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)))
		;
	}
	
	@Rollback(true)
	@Test
	public void 신용카드주문등록성공() throws Exception {
		
		OrderVo vo = constructor();
		
		vo.setPay_way("신용카드");
		vo.setStatus("결제완료");
		vo.setDepositVo(null);
		
		
		ResultActions resultActions = mockMvc.perform(post("/api/order")
													.contentType(MediaType.APPLICATION_JSON)
													.content(new Gson().toJson(vo))
													);
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)))
		;
	}
	
	@Rollback(true)
	@Test
	public void 주문등록실패회원비회원값없음() throws Exception {
		
		OrderVo vo = new OrderVo(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		
		
		
		
		ResultActions resultActions = mockMvc.perform(post("/api/order")
													.contentType(MediaType.APPLICATION_JSON)
													.content(new Gson().toJson(vo))
													);
		
		resultActions.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		;
	}
	
	@Rollback(true)
	@Test
	public void 주문등록실패회원입력형식() throws Exception {
		
		OrderVo vo = new OrderVo(null, null, 2L, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		
		
		
		
		ResultActions resultActions = mockMvc.perform(post("/api/order")
													.contentType(MediaType.APPLICATION_JSON)
													.content(new Gson().toJson(vo))
													);
		
		resultActions.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data").exists())
		;
	}
	
	@Ignore
	@Rollback(true)
	@Test
	public void 주문등록실패재고없음() throws Exception {
		
		OrderVo vo = constructor();
		
		vo.getOrderProductList().get(0).setPrd_no(6L);
		
		
		ResultActions resultActions = mockMvc.perform(post("/api/order")
													.contentType(MediaType.APPLICATION_JSON)
													.content(new Gson().toJson(vo))
													);
		
		resultActions.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result", is("fail")))
		;
	}
	
	/**
	 * 주문 내역 조회
	 * @throws Exception 
	 */
	@Test
	public void 회원주문목록조회() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/order/member/{no}",2L));
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0]").exists())
		;
	}
	
	@Test
	public void 회원주문목록조회실패없는회원() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/order/member/{no}",0L));
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data[0]").doesNotExist())
		;
	}
	
	@Test
	public void 비회원주문목록조회() throws Exception {
		
		Map<String,String> vo = new HashMap<String,String>();
		vo.put("password", "1234");
		vo.put("order_code", "20190803-0000001");
		
		ResultActions resultActions = mockMvc.perform(post("/api/order/none")
														.contentType(MediaType.APPLICATION_JSON)
														.content(new Gson().toJson(vo))
														);
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0]").exists())
		;
	}
	
	@Test
	public void 비회원주문목록조회실패() throws Exception {
		Map<String,String> vo = new HashMap<String,String>();
		vo.put("password", "0000");
		vo.put("order_code", "20190803-0000001");
		
		ResultActions resultActions = mockMvc.perform(post("/api/order/none")
														.contentType(MediaType.APPLICATION_JSON)
														.content(new Gson().toJson(vo))
														);
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data[0]").doesNotExist())
		;
	}
	
	/**
	 * 주문 상세 보기
	 * @throws Exception 
	 */
	@Test
	public void 주문상세보기() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/order/{no}",1L));
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data[0]").exists())
		;
	}
	
	@Test
	public void 주문상세보기실패없는번호() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/order/{no}",0L));
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data[0]").doesNotExist())
		;
	}
}
