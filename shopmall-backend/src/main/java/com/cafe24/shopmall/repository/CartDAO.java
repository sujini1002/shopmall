package com.cafe24.shopmall.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.vo.CartVo;

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

	public boolean isExistInventroyNo(Long value) {
		int result = sqlSession.selectOne("cart.isExistInventroyNo", value);
		return result == 1;
	}

	public Long get(CartVo cartVo, String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cartVo", cartVo);
		params.put("status", status);
		return sqlSession.selectOne("cart.getInventoryNo", params);
	}

	public Long delete(String sessionId, String string) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", string);
		params.put("sessionId", sessionId);
		return (long) sqlSession.delete("cart.delete", params);
	}

	public Boolean insert(CartVo cartVo, String string) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", string);
		params.put("cartVo", cartVo);
		return 1 == sqlSession.insert("cart.insert",params);
		
	}


}
