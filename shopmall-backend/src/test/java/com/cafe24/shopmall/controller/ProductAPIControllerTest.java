package com.cafe24.shopmall.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.cafe24.shopmall.vo.OptionDetailVo;
import com.cafe24.shopmall.vo.OptionVo;
import com.cafe24.shopmall.vo.ProdImgVo;
import com.cafe24.shopmall.vo.ProdInventoryVo;
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
 *   
 *  2. ADMIN과 USER가 모두 가능한 기능이지만 ADMIN에 추가 기능이 있는 기능
 *   - 상품 검색 분류 목록
 *   	- 모두 가능 : 상품명, 카테고리로 검색
 *   	- ADMIN만 가능 : 상품등록일 , 진열상태 , 판매상태로 검색
 *   -
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
		ProductVo productVo = new ProductVo();
		productVo.settitle("린넨tee");
		productVo.setPrice(13000);
		productVo.setDetail("<html><head><title>Ola's blog</title></head><body><h1>린넨tee</h1></body></html>");
		productVo.setCate_no(27);
		
		//상품이미지
		List<ProdImgVo> imgList = new ArrayList<ProdImgVo>();
		imgList.add(new ProdImgVo(null,null,"/images/linentee1.png",true));
		imgList.add(new ProdImgVo(null,null,"/images/linentee2.png",false));
		imgList.add(new ProdImgVo(null,null,"/images/linentee3.png",false));
		
		productVo.setprodImgList(imgList);
		//옵션 상세
		List<OptionDetailVo> detailSizeList = new ArrayList<OptionDetailVo>();
		detailSizeList.add(new OptionDetailVo(null,null,"S"));
		detailSizeList.add(new OptionDetailVo(null,null,"M"));
		detailSizeList.add(new OptionDetailVo(null,null,"L"));
		
		List<OptionDetailVo> detailColorList = new ArrayList<OptionDetailVo>();
		detailColorList.add(new OptionDetailVo(null,null,"블랙"));
		detailColorList.add(new OptionDetailVo(null,null,"화이트"));
		
		//상품 옵션
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		optionList.add(new OptionVo(null,null,"사이즈",detailSizeList));
		optionList.add(new OptionVo(null,null,"색상",detailColorList));
		
		productVo.setOptionList(optionList);
		
		
		//상품 재고
		List<ProdInventoryVo> inventoryList = new ArrayList<ProdInventoryVo>();
		inventoryList.add(new ProdInventoryVo(null, null, "S/블랙", 10, true, true));
		inventoryList.add(new ProdInventoryVo(null, null, "M/블랙", 20, true, true));
		inventoryList.add(new ProdInventoryVo(null, null, "L/블랙", 30, true, true));
		inventoryList.add(new ProdInventoryVo(null, null, "S/화이트", 40, true, true));
		inventoryList.add(new ProdInventoryVo(null, null, "M/화이트", 50, true, true));
		inventoryList.add(new ProdInventoryVo(null, null, "L/화이트", -1, true, true));
		
		productVo.setProdIventoryList(inventoryList);
		
		return productVo;
	}
	
	
	/**
	 *  상품등록 test case 경우
	 *  1. 상품 이미지, 옵션, 옵션 상세, 상품 재고 중 하나라도 null 값이나 없을 때  Custom Valid 필요
	 *  2. 옵션이 default일 때 (옵션 상세는 할 필요 없다.) -  상품 재고의 행 추가는 1만 나타나고 옵션의 옵션명 과 상품재고의  품목명은 default 다.
	 *  3. 옵션이 여러개 일 때
	 */
	
	/**
	 * 1.1 ProductVo 형식이 틀릴 때 (Valid) 
	 */
	@Test
	public void testProductAddFail() throws Exception {
		ProdImgVo vo = new ProdImgVo(null,null,"",null);
		List<ProdImgVo> list = new ArrayList<ProdImgVo>();
		list.add(vo);
		
		List<OptionVo> list2 = new ArrayList<OptionVo>();
		OptionVo vo2 = new OptionVo();
		
		List<OptionDetailVo> list4 = new ArrayList<OptionDetailVo>();
		vo2.setOptionDetailList(list4);
		
		list2.add(vo2);
		List<ProdInventoryVo> list3 = new ArrayList<ProdInventoryVo>();
		ProdInventoryVo vo3 =new ProdInventoryVo();
		list3.add(vo3);
		
		ProductVo productVo = new ProductVo(null,null,null,null,null,null,list,list2,list3);
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/admin/product/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVo)))
				.andDo(print());
		
		resultActions.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result",is("fail")))
		;
	}
	
	/**
	 * 1.2 옵션이 없는 상품 등록 성공
	 */
	@Rollback(true)
	@Test
	public void testProductAddOptionOneSuccess() throws Exception {
		
		
		ProductVo productVo = construtor();
		
		//상품 옵션
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		optionList.add(new OptionVo(null,null,"default",null));
		
		productVo.setOptionList(optionList);
		
		List<ProdInventoryVo> inventoryList = new ArrayList<ProdInventoryVo>();
		inventoryList.add(new ProdInventoryVo(null, null, "default", 10, true, true));
		
		productVo.setProdIventoryList(inventoryList);
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/admin/product/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVo)))
				.andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.productNo").exists())
		.andExpect(jsonPath("$.data.optionNo").exists())
		.andExpect(jsonPath("$.data.ImgInsertCnt",is(productVo.getprodImgList().size())))
		.andExpect(jsonPath("$.data.detailInsertCnt").doesNotExist())
		.andExpect(jsonPath("$.data.inventoryInsertCnt",is(productVo.getProdIventoryList().size())))
		;
	}
	
	/**
	 * 1.3 옵션이 n개인 상품 등록 성공
	 */
	@Rollback(true)
	@Test
	public void testProductAddOptionsSuccess() throws Exception {
		
		
		ProductVo productVo = construtor();
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/admin/product/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVo)))
				.andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data.productNo").exists())
//		.andExpect(jsonPath("$.data.optionNo").exists())
		.andExpect(jsonPath("$.data.ImgInsertCnt",is(productVo.getprodImgList().size())))
		.andExpect(jsonPath("$.data.detailInsertCnt").exists())
		.andExpect(jsonPath("$.data.inventoryInsertCnt",is(productVo.getProdIventoryList().size())))
		;
	}
	
	/**
	 * 2.1 상품 전체 리스트 가져오기 
	 */
	@Test
	public void testProductAllList() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/admin/product"))
									 .andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data[0].prodImgList[0].istitle", is(true)))
		.andExpect(jsonPath("$.data[0].optionList").exists())
		;
	}
	
	/**
	 * 2.1 상품 상세리스트 가져오기
	 */
	@Test
	public void testProductOne() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/admin/product/86"))
									 .andDo(print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data", hasSize(1)))
		.andExpect(jsonPath("$.data[0].prodImgList[0].istitle", is(true)))
		.andExpect(jsonPath("$.data[0].optionList").exists())
		;
	}
}
