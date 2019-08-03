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

	public Boolean insertDeposit(DepositVo depositVo, Long order_no) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("depositVo", depositVo);
		params.put("order_no", order_no);
		return 1==sqlSession.insert("orders.insertDeposit", params);
	}

	public Boolean updateInventory(List<OrderProductVo> orderProductList) {
		return 0 < sqlSession.update("orders.updateInventory", orderProductList);
	}

	public List<Map<String, Object>> getList(long member_code,String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("member_code", member_code);
		params.put("status", status);
		return sqlSession.selectList("orders.getList", params);
	}

	public List<Map<String, Object>> getList(String password, String order_code, String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("password", password);
		params.put("order_code", order_code);
		params.put("status", status);
		return sqlSession.selectList("orders.getList", params);
	}

	public List<Map<String, Object>> getOrder(Long no) {
		return sqlSession.selectList("orders.getOrder", no);
	}
	
	public Integer orderStatus(Long no) {
		return sqlSession.selectOne("orders.orderStatus", no);
	}
	public Integer orderProductStatus(Long no, Long prdIven_no) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("order_no", no);
		params.put("prd_no", prdIven_no);
		return sqlSession.selectOne("orders.orderProductStatus",params);
	}

	public Boolean deleteOrder(Long no) {
		int result = sqlSession.update("orders.updateOrders", no);
		return result > 0;
	}

	public Boolean deleteOrderProduct(Long no) {
		int result = sqlSession.update("orders.updateOrderProductAll", no);
		return result > 0;
	}

	public Boolean deleteOrderProduct(Long no, Long prdIven_no) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("order_no", no);
		params.put("prd_no", prdIven_no);
		
		int result = sqlSession.update("orders.updateOrderProduct",params);
		return 1 == result;
	}

	public Boolean updateOrderStatus(Long no) {
		int result = sqlSession.update("orders.updateOrderStatus", no);
		return result == 1;
	}
}
