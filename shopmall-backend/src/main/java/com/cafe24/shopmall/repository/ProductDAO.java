package com.cafe24.shopmall.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.vo.OptionDetailVo;
import com.cafe24.shopmall.vo.OptionVo;
import com.cafe24.shopmall.vo.ProdImgVo;
import com.cafe24.shopmall.vo.ProdInventoryVo;
import com.cafe24.shopmall.vo.ProductVo;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public Long insertProduct(ProductVo productVo) {
		sqlSession.insert("product.insertProduct", productVo);
		return productVo.getNo();
	}

	public Integer insertProdImg(List<ProdImgVo> prodImgList, Long productNo) {
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("prd_no", productNo);
		params.put("prodImgList", prodImgList);
		
		return sqlSession.insert("product.insertProdImg", params);
	}

	public Long insertOption(OptionVo optionVo, Long productNo) {
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("prd_no", productNo);
		params.put("option", optionVo);
		
		
		sqlSession.insert("product.insertOption",params);
		
		return (Long)params.get("no");
	}

	public Integer insertOptionDetail(List<OptionDetailVo> optionDetailList) {
		return sqlSession.insert("product.insertOptionDetail", optionDetailList);
	}

	public Integer insertProdInventory(List<ProdInventoryVo> prodIventoryList, Long productNo) {
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("prd_no", productNo);
		params.put("prodIventoryList", prodIventoryList);
		
		return sqlSession.insert("product.insertProdInventory", params);
	}

	public List<ProductVo> getlist(Long prd_no) {
		List<ProductVo> list = sqlSession.selectList("product.getList",prd_no);
		return list;
	}

	public Integer updateProduct(ProductVo productVo) {
		return sqlSession.update("product.updateProduct", productVo);
	}

	public Integer deleteImg(Long no, String string) {

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("prd_no", no);
		params.put("status", string);
		
		return sqlSession.delete("product.deleteProductImg", params);
	}

	public Integer deleteOption(Long no) {
		return sqlSession.delete("product.deleteOption", no);
	}

	public Integer deleteInventory(Long no) {
		return sqlSession.update("product.deleteInventory", no);
	}

	public Integer deleteProduct(Long no) {
		return sqlSession.update("product.deleteProduct", no);
	}

	public ProductVo getlistFalse(Long no) {
		return sqlSession.selectOne("product.getDeleteList",no);
	}

}
