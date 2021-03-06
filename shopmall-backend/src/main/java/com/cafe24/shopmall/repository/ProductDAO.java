package com.cafe24.shopmall.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.vo.ProductVo;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<Map<String,Object>> list() {
		return sqlSession.selectList("product.getMainList");
	}
	
	public ProductVo getProduct(Long no){
		return sqlSession.selectOne("product.memberProduct", no);
	}
	
	public List<Map<String,Object>> getCategoryList(Long no) {
		return sqlSession.selectList("product.getCategoryList",no);
	}
}
