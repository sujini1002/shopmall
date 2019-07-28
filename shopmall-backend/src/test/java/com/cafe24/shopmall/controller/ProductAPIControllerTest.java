package com.cafe24.shopmall.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shopmall.vo.OptionDetailVo;
import com.cafe24.shopmall.vo.OptionVo;
import com.cafe24.shopmall.vo.ProdImgVo;
import com.cafe24.shopmall.vo.ProdInventoryVo;
import com.cafe24.shopmall.vo.ProductVo;
import com.google.gson.Gson;

/**
 * 상품과 관련하여 테스트하는 기능 
 * 1. ADMIN만 가능한 기능
 *  - 상품등록 - 기본 상품 정보 등록 (하나만 가능) 
 *  - 상품 이미지 등록(최소 하나 ~여러개 등록 가능) 
 *  - LIST - 옵션등록 (최소 하나 ~여러개 등록 가능) 
 *  - LIST - 옵션 상세등록 (최소 하나  ~여러개 등록 가능) 
 * 	- LIST - 옵션 별 상품 재고 등록 (최소 하나 ~여러개 등록 가능) - LIST - 상품 수정 - 상품 삭제
 * 
 * 
 * 2. ADMIN과 USER가 모두 가능한 기능이지만 ADMIN에 추가 기능이 있는 기능 
 * - 상품 검색 분류 목록 - 모두 가능 : 상품명,카테고리로 검색
 * - ADMIN만 가능 : 상품등록일 , 진열상태 , 판매상태로 검색 -
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductAPIControllerTest {
	private MockMvc mockMvc;
	
	private Long noOneOption = 111L;
	private Long noMoreOption = 110L;
	

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
		productVo.setCate_no(2);
		productVo.setIssale(true);

		// 상품이미지
		List<ProdImgVo> imgList = new ArrayList<ProdImgVo>();
		imgList.add(new ProdImgVo(null, null, "/images/linentee1.png", true));
		imgList.add(new ProdImgVo(null, null, "/images/linentee2.png", false));
		imgList.add(new ProdImgVo(null, null, "/images/linentee3.png", false));

		productVo.setprodImgList(imgList);
		// 옵션 상세
		List<OptionDetailVo> detailSizeList = new ArrayList<OptionDetailVo>();
		detailSizeList.add(new OptionDetailVo(null, null, "S"));
		detailSizeList.add(new OptionDetailVo(null, null, "M"));
		detailSizeList.add(new OptionDetailVo(null, null, "L"));

		List<OptionDetailVo> detailColorList = new ArrayList<OptionDetailVo>();
		detailColorList.add(new OptionDetailVo(null, null, "블랙"));
		detailColorList.add(new OptionDetailVo(null, null, "화이트"));

		// 상품 옵션
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		optionList.add(new OptionVo(null, null, "사이즈", detailSizeList));
		optionList.add(new OptionVo(null, null, "색상", detailColorList));

		productVo.setOptionList(optionList);

		// 상품 재고
		List<ProdInventoryVo> inventoryList = new ArrayList<ProdInventoryVo>();
		inventoryList.add(new ProdInventoryVo(null, null, "S/블랙", 10,  true));
		inventoryList.add(new ProdInventoryVo(null, null, "M/블랙", 20, true));
		inventoryList.add(new ProdInventoryVo(null, null, "L/블랙", 30,  true));
		inventoryList.add(new ProdInventoryVo(null, null, "S/화이트", 40,  true));
		inventoryList.add(new ProdInventoryVo(null, null, "M/화이트", 50,  true));
		inventoryList.add(new ProdInventoryVo(null, null, "L/화이트", -1,  true));

		productVo.setProdIventoryList(inventoryList);

		return productVo;
	}

	/**
	 * 상품등록 test case 경우 1. 상품 이미지, 옵션, 옵션 상세, 상품 재고 중 하나라도 null 값이나 없을 때 Custom
	 * Valid 필요 2. 옵션이 default일 때 (옵션 상세는 할 필요 없다.) - 상품 재고의 행 추가는 1만 나타나고 옵션의 옵션명 과
	 * 상품재고의 품목명은 default 다. 3. 옵션이 여러개 일 때
	 */

	/**
	 * 1.1 ProductVo 형식이 틀릴 때 (Valid)
	 */
	@Test
	public void testProductAddFail() throws Exception {
		ProdImgVo vo = new ProdImgVo(null, null, "", null);
		List<ProdImgVo> list = new ArrayList<ProdImgVo>();
		list.add(vo);

		List<OptionVo> list2 = new ArrayList<OptionVo>();
		OptionVo vo2 = new OptionVo();

		List<OptionDetailVo> list4 = new ArrayList<OptionDetailVo>();
		vo2.setOptionDetailList(list4);

		list2.add(vo2);
		List<ProdInventoryVo> list3 = new ArrayList<ProdInventoryVo>();
		ProdInventoryVo vo3 = new ProdInventoryVo();
		list3.add(vo3);

		ProductVo productVo = new ProductVo(null, null, null, null, null, 0, null, list2, list3, null);

		ResultActions resultActions = mockMvc.perform(post("/api/admin/product/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVo))).andDo(print());

		resultActions.andExpect(status().isBadRequest()).andExpect(jsonPath("$.result", is("fail")));
	}

	/**
	 * 1.2 옵션이 없는 상품 등록 성공
	 */
	@Rollback(true)
	@Test
	public void testProductAddOptionOneSuccess() throws Exception {

		ProductVo productVo = construtor();

		// 상품 옵션
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		optionList.add(new OptionVo(null, null, "default", null));

		productVo.setOptionList(optionList);

		List<ProdInventoryVo> inventoryList = new ArrayList<ProdInventoryVo>();
		inventoryList.add(new ProdInventoryVo(null, null, "default", 10, true));

		productVo.setProdIventoryList(inventoryList);

		ResultActions resultActions = mockMvc.perform(post("/api/admin/product/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVo))).andDo(print());

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.productNo").exists()).andExpect(jsonPath("$.data.optionNo").exists())
				.andExpect(jsonPath("$.data.ImgInsertCnt", is(productVo.getprodImgList().size())))
				.andExpect(jsonPath("$.data.detailInsertCnt").doesNotExist())
				.andExpect(jsonPath("$.data.inventoryInsertCnt", is(productVo.getProdIventoryList().size())));
	}

	/**
	 * 1.3 옵션이 n개인 상품 등록 성공
	 */
	@Rollback(true)
	@Test
	public void testProductAddOptionsSuccess() throws Exception {

		ProductVo productVo = construtor();

		ResultActions resultActions = mockMvc.perform(post("/api/admin/product/")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVo))).andDo(print());

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data.productNo").exists())
//		.andExpect(jsonPath("$.data.optionNo").exists())
				.andExpect(jsonPath("$.data.ImgInsertCnt", is(productVo.getprodImgList().size())))
				.andExpect(jsonPath("$.data.detailInsertCnt").exists())
				.andExpect(jsonPath("$.data.inventoryInsertCnt", is(productVo.getProdIventoryList().size())));
	}

	/**
	 * 2.1 상품 전체 리스트 가져오기
	 */
	@Test
	public void testProductAllList() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/admin/product")).andDo(print());

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data").exists())
				.andExpect(jsonPath("$.data[0].prodImgList[0].istitle", is(true)))
				.andExpect(jsonPath("$.data[0].optionList").exists());
	}

	/**
	 * 2.1 상품 상세리스트 가져오기
	 */
	@Test
	public void testProductOne() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/admin/product/"+noMoreOption)).andDo(print());

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data").exists()).andExpect(jsonPath("$.data", hasSize(1)))
				.andExpect(jsonPath("$.data[0].prodImgList[0].istitle", is(true)))
				.andExpect(jsonPath("$.data[0].optionList").exists());
	}

	/**
	 * 3.1 상품 수정 - 상품 : 상품명, 상품가격, 상품 상세설명, 판매여부 - 상품이미지 : 삭제하고 다시 insert한다(?) - 기존에
	 * 변경되지 않았던 것은? - 옵션 : 삭제하고 다시 insert / (나중에) 옵션의 길이를 비교해서 같으면 -> 수정 작으면 -> 삭제,
	 * 크면 -> insert - 옵션 상세 : 삭제하고 다시 insert - 상품 재고 : 주문과 엮어 있으므로 지워지면 안되고 insert
	 * 기존에 있는 것의 판매여부는 false로 업데이트한다. - 장바구니에서 결제하지 못하게
	 * 
	 * @throws Exception
	 */

	/**
	 * 3.1.1 기존 옵션이 default인 상품의 옵션을 추가 
	 */
	@Rollback(true)
	@Test
	public void testProductUpdateNoOption() throws Exception {

		ProductVo vo = new ProductVo();
		vo.setNo(noOneOption);
		vo.settitle("린넨 pants");
		vo.setPrice(25000);
		vo.setCate_no(2);
		vo.setDetail("<h1>하의 입니다.</h1>");
		vo.setIssale(true);
		
		// 상품이미지
		List<ProdImgVo> imgList = new ArrayList<ProdImgVo>();
		imgList.add(new ProdImgVo(null, null, "/images/linenpants1.png", true));
		imgList.add(new ProdImgVo(null, null, "/images/linenpants2.png", false));
		imgList.add(new ProdImgVo(null, null, "/images/linenpants3.png", false));
		
		vo.setprodImgList(imgList);

		// 옵션 상세
		List<OptionDetailVo> detailSizeList = new ArrayList<OptionDetailVo>();
		detailSizeList.add(new OptionDetailVo(null, null, "25"));
		detailSizeList.add(new OptionDetailVo(null, null, "26"));
		detailSizeList.add(new OptionDetailVo(null, null, "27"));
		detailSizeList.add(new OptionDetailVo(null, null, "28"));


		// 상품 옵션
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		optionList.add(new OptionVo(null, null, "사이즈", detailSizeList));

		vo.setOptionList(optionList);

		// 상품 재고
		List<ProdInventoryVo> inventoryList = new ArrayList<ProdInventoryVo>();
		inventoryList.add(new ProdInventoryVo(null, null, "25", 0,  true));
		inventoryList.add(new ProdInventoryVo(null, null, "26", -1, true));
		inventoryList.add(new ProdInventoryVo(null, null, "27", -1,  true));
		inventoryList.add(new ProdInventoryVo(null, null, "28", -1,  true));

		vo.setProdIventoryList(inventoryList);

		ResultActions resultActions = mockMvc.perform(
				put("/api/admin/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(vo))
				);
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
//		.andExpect(jsonPath("$.data[0].no", is((long)vo.getNo())))
		.andExpect(jsonPath("$.data[0].title", is(vo.gettitle())))
		.andExpect(jsonPath("$.data[0].price", is(vo.getPrice())))
		.andExpect(jsonPath("$.data[0].issale", is(vo.getIssale())))
		.andExpect(jsonPath("$.data[0].prodImgList", hasSize(imgList.size())))
		.andExpect(jsonPath("$.data[0].optionList", hasSize(optionList.size())))
		.andExpect(jsonPath("$.data[0].prodIventoryList", hasSize(inventoryList.size())))
		;
	}
	
	/**
	 * 3.1.2 기존 옵션이 2개인 옵션을 한개롤 줄이기
	 */
	@Rollback(true)
	@Test
	public void testProductUpdateMoreOption() throws Exception {

		ProductVo vo = new ProductVo();
		vo.setNo(noMoreOption);
		vo.settitle("시원한 티셔츠");
		vo.setPrice(10000);
		vo.setCate_no(1);
		vo.setDetail("<h1>시원한 티셔츠  입니다.</h1>");
		vo.setIssale(true);
		
		// 상품이미지
		List<ProdImgVo> imgList = new ArrayList<ProdImgVo>();
		imgList.add(new ProdImgVo(null, null, "/images/cooltee1.png", true));
		imgList.add(new ProdImgVo(null, null, "/images/cooltee2.png", false));
		imgList.add(new ProdImgVo(null, null, "/images/cooltee3.png", false));
		
		vo.setprodImgList(imgList);

		// 옵션 상세
		List<OptionDetailVo> detailSizeList = new ArrayList<OptionDetailVo>();
		detailSizeList.add(new OptionDetailVo(null, null, "블랙"));
		detailSizeList.add(new OptionDetailVo(null, null, "화이트"));
		detailSizeList.add(new OptionDetailVo(null, null, "핑크"));
		detailSizeList.add(new OptionDetailVo(null, null, "베이지"));


		// 상품 옵션
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		optionList.add(new OptionVo(null, null, "색상", detailSizeList));

		vo.setOptionList(optionList);

		// 상품 재고
		List<ProdInventoryVo> inventoryList = new ArrayList<ProdInventoryVo>();
		inventoryList.add(new ProdInventoryVo(null, null, "블랙", 0,  true));
		inventoryList.add(new ProdInventoryVo(null, null, "화이트", -1, true));
		inventoryList.add(new ProdInventoryVo(null, null, "핑크", -1,  true));
		inventoryList.add(new ProdInventoryVo(null, null, "베이지", -1,  true));

		vo.setProdIventoryList(inventoryList);

		ResultActions resultActions = mockMvc.perform(
				put("/api/admin/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(vo))
				);
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
//		.andExpect(jsonPath("$.data[0].no", is((long)vo.getNo())))
		.andExpect(jsonPath("$.data[0].title", is(vo.gettitle())))
		.andExpect(jsonPath("$.data[0].price", is(vo.getPrice())))
		.andExpect(jsonPath("$.data[0].issale", is(vo.getIssale())))
		.andExpect(jsonPath("$.data[0].prodImgList", hasSize(imgList.size())))
		.andExpect(jsonPath("$.data[0].optionList", hasSize(optionList.size())))
		.andExpect(jsonPath("$.data[0].prodIventoryList", hasSize(inventoryList.size())))
		;
	}
	
	/**
	 * 3.1.3 기존 옵션을 없애기
	 */
	@Rollback(true)
	@Test
	public void testProductUpdateDeleteAllOption() throws Exception {

		ProductVo vo = new ProductVo();
		vo.setNo(noMoreOption);
		vo.settitle("옵션 없는 티셔츠");
		vo.setPrice(10000);
		vo.setCate_no(1);
		vo.setDetail("<h1>옵션 없는 티셔츠  입니다.</h1>");
		vo.setIssale(true);
		
		// 상품이미지
		List<ProdImgVo> imgList = new ArrayList<ProdImgVo>();
		imgList.add(new ProdImgVo(null, null, "/images/nooption1.png", true));
		imgList.add(new ProdImgVo(null, null, "/images/nooption2.png", false));
		imgList.add(new ProdImgVo(null, null, "/images/nooption3.png", false));
		
		vo.setprodImgList(imgList);


		// 상품 옵션
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		optionList.add(new OptionVo(null, null, "default", null));

		vo.setOptionList(optionList);

		// 상품 재고
		List<ProdInventoryVo> inventoryList = new ArrayList<ProdInventoryVo>();
		inventoryList.add(new ProdInventoryVo(null, null, "default", 0,  true));

		vo.setProdIventoryList(inventoryList);

		ResultActions resultActions = mockMvc.perform(
				put("/api/admin/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(vo))
				);
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
//		.andExpect(jsonPath("$.data[0].no", is((long)vo.getNo())))
		.andExpect(jsonPath("$.data[0].title", is(vo.gettitle())))
		.andExpect(jsonPath("$.data[0].price", is(vo.getPrice())))
		.andExpect(jsonPath("$.data[0].issale", is(vo.getIssale())))
		.andExpect(jsonPath("$.data[0].prodImgList", hasSize(imgList.size())))
		.andExpect(jsonPath("$.data[0].optionList", hasSize(optionList.size())))
		.andExpect(jsonPath("$.data[0].prodIventoryList", hasSize(inventoryList.size())))
		;
	}
	
	/**
	 * 3.1.3 상품 정보가 null 이면 수정 되지 않음
	 */
	@Rollback(true)
	@Test
	public void testProductNullUpdate() throws Exception {

		ProductVo vo = new ProductVo();
		vo.setNo(noMoreOption);
		vo.settitle("");
		vo.setPrice(-19);
		vo.setCate_no(null);
		vo.setDetail("");
		vo.setIssale(true);
		
		// 상품이미지
		List<ProdImgVo> imgList = new ArrayList<ProdImgVo>();
		imgList.add(new ProdImgVo(null, null, "/images/nooption1.png", true));
		imgList.add(new ProdImgVo(null, null, "/images/nooption2.png", false));
		imgList.add(new ProdImgVo(null, null, "/images/nooption3.png", false));
		
		vo.setprodImgList(imgList);


		// 상품 옵션
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		optionList.add(new OptionVo(null, null, "default", null));

		vo.setOptionList(optionList);

		// 상품 재고
		List<ProdInventoryVo> inventoryList = new ArrayList<ProdInventoryVo>();
		inventoryList.add(new ProdInventoryVo(null, null, "default", 0,  true));

		vo.setProdIventoryList(inventoryList);

		ResultActions resultActions = mockMvc.perform(
				put("/api/admin/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(vo))
				);
		
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
//		.andExpect(jsonPath("$.data[0].no", is((long)vo.getNo())))
		.andExpect(jsonPath("$.data[0].title", not(vo.gettitle())))
		.andExpect(jsonPath("$.data[0].price", not(vo.getPrice())))
		.andExpect(jsonPath("$.data[0].issale", is(vo.getIssale())))
		.andExpect(jsonPath("$.data[0].prodImgList", hasSize(imgList.size())))
		.andExpect(jsonPath("$.data[0].optionList", hasSize(optionList.size())))
		.andExpect(jsonPath("$.data[0].prodIventoryList", hasSize(inventoryList.size())))
		;
	}
	
	/**
	 * 4. 상품 삭제 
	 *  - 상품의 정보는 issale 만 false로 변경한다.
	 *  - 상품이 삭제 되면 상품의 대표이지미 제외 제거, 옵션, 옵션 상세는 지워진다.
	 *  - 상품 재고는 주문에 연결 되어 있으므로 삭제하지 않고 issale을 false로 바꾼다.
	 * @throws Exception 
	 */
	
	/**
	 * 4.1 삭제 성공 
	 * @throws Exception 
	 */
	@Rollback(true)
	@Test
	public void testDeleteProduct() throws Exception {
		ResultActions resultActions = mockMvc.perform(delete("/api/admin/product/"+noMoreOption));
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result",is("success")))
//		.andExpect(jsonPath("$.data.no", is(noMoreOption)))
		.andExpect(jsonPath("$.data.issale", is(false)))
		.andExpect(jsonPath("$.data.prodImgList",hasSize(1)))
		.andExpect(jsonPath("$.data.optionList",hasSize(0)))
		;
		
	}
	
	/**
	 * 4.2 삭제 실패 ( 없는 번호)
	 */
	@Rollback(true)
	@Test
	public void testDeleteProductFail()  throws Exception{
		ResultActions resultActions = mockMvc.perform(delete("/api/admin/product/"+0));
		resultActions.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.result",is("fail")))
		;
		
	}
}
