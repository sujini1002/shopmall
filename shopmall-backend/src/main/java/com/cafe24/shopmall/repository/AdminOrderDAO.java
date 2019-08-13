package com.cafe24.shopmall.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminOrderDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//주문리스트
	public List<Map<String,Object>> list(){
		return sqlSession.selectList("orders.getAllList");
	}
}
