package com.cafe24.shopmall.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<Map<String,Object>> list() {
		return sqlSession.selectList("product.getMainList");
	}
}
