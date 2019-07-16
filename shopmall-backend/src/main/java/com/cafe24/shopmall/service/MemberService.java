package com.cafe24.shopmall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.repository.MemberDAO;
import com.cafe24.shopmall.vo.MemberVo;

@Service
public class MemberService {
	
	List<MemberVo> memberList = new ArrayList<MemberVo>();
	
	@Autowired
	private MemberDAO memberDao;

	public Boolean existId(String id) {
		return memberDao.isIdExist(id);
	}
	/**
	 * -- 회원 가입
	 * 1. 우편번호 또는 배송지가 없으면 member 테이블만 저장
	 * 2. 존재한다면 member 저장 후 delivers 테이블에 member의 code 값을 가져가서 저장 시킴 
	 */
	public Map<String,Long> userAdd(MemberVo memberVo) {
		Map<String,Long> result = new HashMap<String,Long>();
		
		Long memberCode = memberDao.insertMember(memberVo);
		result.put("memberCode", memberCode);
		
		if( !"".equals(memberVo.getPostId()) && !"".equals(memberVo.getDeliverFirst()) && !"".equals(memberVo.getDeliverLast())) {
			memberVo.setCode(memberCode);
			memberVo.setDeliver("("+memberVo.getPostId()+") "+ memberVo.getDeliverFirst() + memberVo.getDeliverLast());
			
			Long deliverCode = memberDao.insertDeliver(memberVo);
			result.put("deliverCode", deliverCode);
		}
		return  result;
	}

	public Boolean login(String id, String password) {
//		UserVo vo = dao.selectUserByIdPw(id,password);
//		return vo == null;
		MemberVo vo = memberList.get(0);
		
		return vo.getId().equals(id) && vo.getPassword().equals(password);
	}

	public MemberVo getMemberInfo(Long no) {
		MemberVo result = null;
		for(MemberVo vo : memberList) {
			if(vo.getCode()==no) {
				result = vo;
				break;
			}
		}
		return result;
	}

	public MemberVo modifyMember(MemberVo vo) {
		MemberVo result = null;
		for(MemberVo temp : memberList) {
			if(temp.getCode()==vo.getCode()) {
				result = vo;
				break;
			}
		}
		return result;
	}

	public Boolean delete(Long code, String password) {
		for(MemberVo vo : memberList) {
			if(vo.getCode() == code && vo.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
}
