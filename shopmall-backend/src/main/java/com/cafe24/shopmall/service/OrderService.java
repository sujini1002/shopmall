package com.cafe24.shopmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shopmall.repository.OrderDAO;
import com.cafe24.shopmall.vo.OrderProductVo;
import com.cafe24.shopmall.vo.OrderVo;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDao;

	/**
	 * @param orderVo 
	 * 1. 주문 등록
	 *   1.0 상품재고 테이블에서 재고가 있는지 검사한다.(하나라도 없으면 false)
	 *   1.1 결제금액이 주문상품의 가격과 같은지 비교한다.(다르면 false)
	 *   1.2  주문테이블에 먼저 주문등록한다. 
	 *   1.3 주문 번호를 가지고 주문 상품들을 테이블에 등록한다.
	 *   1.4 결제정보가 무통장 입금이면 무통장 정보를 등록 시킨다.
	 *   1.5 재고를 update 한다.
	 */
	@Transactional
	public Boolean add(OrderVo orderVo) {
		

		// 1.0 재고 확인 및 가격 설정
		List<Map<String,Object>> prdInvenList = orderDao.getprdInvenList(orderVo.getOrderProductList());
		
		for (Map<String,Object> piVo : prdInvenList) {
			
			for (OrderProductVo odVo : orderVo.getOrderProductList()) {
				if(Long.parseLong(piVo.get("no").toString())== odVo.getPrd_no().longValue()) {
					
					if(Integer.parseInt(piVo.get("inventory").toString())==-1) {
						odVo.setPrice(Integer.parseInt(piVo.get("price").toString())*odVo.getCount());
						continue;
					}
					else if(Integer.parseInt(piVo.get("inventory").toString()) - odVo.getCount()<=0) 
					{
						return false;
					}else {
						odVo.setPrice(Integer.parseInt(piVo.get("price").toString())*odVo.getCount());
					}
					odVo.setStatus(orderVo.getStatus());
					
				}
				
			}
			
		}
		
		//1.1 orderVo에 있는 가격과 같은지 비교
		Integer totalPrice = 0;
		for(OrderProductVo vo : orderVo.getOrderProductList()) {
			totalPrice += vo.getPrice();
		}
		
		if(totalPrice.intValue() != orderVo.getPayment().intValue()) {
			return false;
		}
		
		//1.2 orders에 insert
		Long order_no = orderDao.insertOrder(orderVo);
		
		//1.3 order_product에 insert 
		Integer resultOrderProduct = orderDao.insertOrderProduct(orderVo.getOrderProductList(),order_no);
		
		if(resultOrderProduct!=orderVo.getOrderProductList().size())return false;
		
		//1.4 무통장입금일 경우 deposit에 insert
		if("무통장입금".equals(orderVo.getPay_way())) {
			Boolean resultDeposit = orderDao.insertDeposit(orderVo.getDepositVo(),order_no);
			
			if(!resultDeposit)return false;
		}
		
		//1.5 재고 업데이트 
		Boolean resultInventory = orderDao.updateInventory(orderVo.getOrderProductList());
		
		if(!resultInventory)return false;
		
		return true;
	}
	
	//회원 주문 목록
	public List<Map<String, Object>> getList(long member_code) {
		return orderDao.getList(member_code,"member");
	}
	//비회원 주문 목록
	public List<Map<String, Object>> getList(String password, String order_code) {
		
		return orderDao.getList(password,order_code,"none");
	}

	//주문 상세보기
	public List<Map<String, Object>> getOrder(Long no) {
		return orderDao.getOrder(no);
	}
	
	//주문 전체 지우기
	@Transactional
	public Boolean deleteAll(Long no) {
		
		// 주문 상태 확인하기 
		if(orderDao.orderStatus(no)>0) {
			return false;
		}
		
		Boolean result = null;
		
		//주문테이블 취소
		result = orderDao.deleteOrder(no);
		//주문상품 취소
		if(result==false) {
			return false;
		}
		
		result =orderDao.deleteOrderProduct(no);
		
		return result;
	}
	@Transactional
	public Boolean deleteOrderProduct(Long no, Long prdIven_no) {
		
		//주문 상품 상태 확인
		if(orderDao.orderProductStatus(no,prdIven_no)>0) {
			return false;
		}
		
		Boolean result = null;
		
		//주문 상품 취소
		result = orderDao.deleteOrderProduct(no,prdIven_no);
		System.out.println(result);
		
		if(!result) return false;
		
		//주문의 상테 수정
		result = orderDao.updateOrderStatus(no);
		
		return result;
	}

}
