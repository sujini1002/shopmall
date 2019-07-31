package com.cafe24.shopmall.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.vo.DepositVo;
import com.cafe24.shopmall.vo.OrderProductVo;
import com.cafe24.shopmall.vo.OrderVo;

@Repository
public class OrderDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public List<Map<String,Object>> getprdInvenList(List<OrderProductVo> orderProductList) {
		return sqlSession.selectList("orders.getprdInvenList", orderProductList);
	}

	public Long insertOrder(OrderVo orderVo) {
		sqlSession.insert("orders.insertOrder", orderVo);
		return orderVo.getNo();
	}

	public Integer insertOrderProduct(List<OrderProductVo> orderProductList, Long order_no) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orderProductList", orderProductList);
		params.put("order_no", order_no);
		
		return sqlSession.insert("orders.insertOrderProduct", params);
	}

	public Integer insertDeposit(DepositVo depositVo, Long order_no) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("depositVo", depositVo);
		params.put("order_no", order_no);
		
		return sqlSession.insert("orders.insertDeposit", params);
	}

}
