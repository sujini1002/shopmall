package com.cafe24.shopmall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 */
	@Transactional
	public Long userAdd(MemberVo memberVo) {
		Long memberCode = memberDao.insertMember(memberVo);
		return  memberCode;
	}
	/**
	 *  회원 로그인
	 */
	public MemberVo login(String id) {
		return memberDao.selectUserById(id);
	}
	/**
	 *  회원정보 수정시에 기존 회원정보를 가져오는 메서드 이다.
	 */
	@Transactional
	public MemberVo getMemberInfo(Long no) {
		return memberDao.getMemberInfo(no);
	}
	
	/**
	 * 회원 정보 수정하는 메서드 
	 * 1. 비밀번호 null 값이면 변경하지 않는다.
	 */
	@Transactional
	public MemberVo modifyMember(MemberVo memberVo) {
		//회원 정보 수정
		memberDao.updateMember(memberVo);
		
		return memberDao.getMemberInfo(memberVo.getCode());
	}
	
	@Transactional
	public Boolean delete(Long code, String password) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("code", code);
		param.put("password", password);
		
		return memberDao.deleteMember(param);
	}
	
}
