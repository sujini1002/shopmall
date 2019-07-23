package com.cafe24.shopmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.repository.ProductDAO;
import com.cafe24.shopmall.vo.OptionDetailVo;
import com.cafe24.shopmall.vo.OptionVo;
import com.cafe24.shopmall.vo.ProductVo;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDao;
	
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

	public List<ProductVo> list() {
		List<ProductVo> list = productDao.getlist();
		return list;
	}

}
