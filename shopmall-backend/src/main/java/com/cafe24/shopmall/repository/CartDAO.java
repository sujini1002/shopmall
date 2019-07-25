package com.cafe24.shopmall.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public Long findInventroyNo(String opt_value, Long prd_no) {
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("opt_value", opt_value);
		params.put("prd_no", prd_no);
		
		return sqlSession.selectOne("cart.findInventoryNo", params);
	}

}
