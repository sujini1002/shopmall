package com.cafe24.shopmall.repository;

import java.util.HashMap;
import java.util.List;
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

	public Integer get(CartVo cartVo, String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cartVo", cartVo);
		params.put("status", status);
		return sqlSession.selectOne("cart.getCountNo", params);
	}

	public Boolean insert(CartVo cartVo, String string) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", string);
		params.put("cartVo", cartVo);
		return 1 == sqlSession.insert("cart.insert",params);
		
	}

	public List<CartVo> getList(Long member_code, String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", status);
		params.put("member_code", member_code);
		
		return sqlSession.selectList("cart.getList", params);
	}

	public List<CartVo> getList(String session_id, String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", status);
		params.put("session_id", session_id);
		
		return sqlSession.selectList("cart.getList", params);
	}

	public Boolean update(CartVo cartVo, String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", status);
		params.put("cartVo", cartVo);
		
		return 1== sqlSession.update("cart.update", params);
	}
	
	public CartVo getCart(CartVo cartVo,String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", status);
		params.put("cartVo", cartVo);
		
		return sqlSession.selectOne("cart.getCart", params);
	}

	public Boolean delete(CartVo cartVo, String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", status);
		params.put("cartVo", cartVo);
		
		return 1== sqlSession.delete("cart.delete", params);
	}

	public Boolean deleteCartList(String session_id, String string) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("status", string);
		params.put("session_id", session_id);
		
		
		return 1 <= sqlSession.delete("cart.deleteCartList",params);
	}
}
