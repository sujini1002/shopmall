package com.cafe24.shopmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shopmall.repository.ProductDAO;
import com.cafe24.shopmall.vo.OptionDetailVo;
import com.cafe24.shopmall.vo.OptionVo;
import com.cafe24.shopmall.vo.ProductVo;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDao;
	
	@Transactional
	public Map<String,Object> add(ProductVo productVo) {
		
		Map<String,Object> results = new HashMap<String, Object>();
		
		//1. 상품 정보 등록 - insert한 상품의 no 값 받기
		Long productNo = productDao.insertProduct(productVo);
		results.put("productNo",productNo);
		
		//2. 상품 이미지 등록
		Integer ImgInsertCnt = productDao.insertProdImg(productVo.getprodImgList(),productNo);
		results.put("ImgInsertCnt", ImgInsertCnt);
		
		
		//3. 상품 옵션 및 상세 등록 - 옵션 번호가 필요
		if(productVo.getOptionList().size()==1 && "default".equals(productVo.getOptionList().get(0).getName())) {
			Long optionNo = productDao.insertOption(productVo.getOptionList().get(0),productNo);
			results.put("optionNo", optionNo);
			
		}else {
			for(OptionVo optionVo : productVo.getOptionList()) {
				Long optionNo = productDao.insertOption(optionVo,productNo);
				
				for(OptionDetailVo detailVo : optionVo.getOptionDetailList()) {
					detailVo.setOpt_no(optionNo);
				}
				Integer insertCount = productDao.insertOptionDetail(optionVo.getOptionDetailList());
				results.put("detailInsertCnt", insertCount);
			}
		}
		
		//4. 상품 재고 등록
		Integer inventoryInsertCnt = productDao.insertProdInventory(productVo.getProdIventoryList(),productNo);
		results.put("inventoryInsertCnt", inventoryInsertCnt);
		
		return results;
	}

	public List<ProductVo> list(Long prd_no) {
		List<ProductVo> list = productDao.getlist(prd_no);
		return list;
	}

	@Transactional
	public List<ProductVo> modify(ProductVo productVo) {
		// 상품 정보 수정
		productDao.updateProduct(productVo);
		
		// 상품 이지미 삭제
		if(productVo.getprodImgList() != null && productVo.getprodImgList().size()==0) {
			productDao.deleteImg(productVo.getNo(),"CHANGE");
			// 상품 이미지 insert
			productDao.insertProdImg(productVo.getprodImgList(),productVo.getNo());
		}
		
		// 상품 옵션  및 옵션 상세 삭제
		productDao.deleteOption(productVo.getNo());
		// 상품 옵션 insert
		if(productVo.getOptionList().size()==1 && "default".equals(productVo.getOptionList().get(0).getName())) {
			productDao.insertOption(productVo.getOptionList().get(0),productVo.getNo());
		}else {
			for(OptionVo optionVo : productVo.getOptionList()) {
				Long optionNo = productDao.insertOption(optionVo,productVo.getNo());
				
				for(OptionDetailVo detailVo : optionVo.getOptionDetailList()) {
					detailVo.setOpt_no(optionNo);
				}
				productDao.insertOptionDetail(optionVo.getOptionDetailList());
			}
		}
		
		// 상품 재고 판매여부 false 변경
		productDao.deleteInventory(productVo.getNo());
		// 상품 재고 insert
		productDao.insertProdInventory(productVo.getProdIventoryList(),productVo.getNo());
		
		
		return productDao.getlist(productVo.getNo());
	}
	
	/**
	 * 4. 상품 삭제 
	 *  - 상품의 정보는 issale 만 false로 변경한다.
	 *  - 상품이 삭제 되면 상품의 대표이지미 제외 제거, 옵션, 옵션 상세는 지워진다.
	 *  - 상품 재고는 주문에 연결 되어 있으므로 삭제하지 않고 issale을 false로 바꾼다.
	 */
	@Transactional
	public ProductVo delete(Long no) {
		
		// 상품 삭제
		productDao.deleteProduct(no);
		// 상품 이미지 삭제
		productDao.deleteImg(no, "DELETE");
		// 상품 옵션 및 상세 삭제
		productDao.deleteOption(no);
		// 상품 재고 삭제
		productDao.deleteInventory(no);
		
		return productDao.getlistFalse(no);
	}

}
