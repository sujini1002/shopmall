package com.cafe24.shopmall.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.vo.MemberVo;

@Repository
public class MemberDAO {
	
	public List<MemberVo> memberList;
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 아이디 중복체크
	 */
	public Boolean isIdExist(String id) {
		Long cnt = sqlSession.selectOne("member.selectById", id);
		return cnt == 1;
	}
	
	/**
	 * member 테이블에 행 추가 
	 */
	public Long insertMember(MemberVo memberVo) {
		sqlSession.insert("member.insertMember", memberVo);
		return memberVo.getCode();
	}
	/**
	 * 로그인 
	 */
	public String selectUserByIdPw(String id, String password) {
		Map<String,String> param = new HashMap<String, String>();
		param.put("id", id);
		param.put("password", password);
		
		String result = sqlSession.selectOne("member.selectUserByIdPw", param);
		return result;
	}
	
	/**
	 * 회원 정보가져오기
	 */
	public MemberVo getMemberInfo(Long no) {
		return sqlSession.selectOne("member.selectMemberByCode", no);
	}

	public Boolean updateMember(MemberVo memberVo) {
		int result = sqlSession.update("member.updateMember", memberVo);
		return result==1;
	}

	public Boolean deleteMember(Map<String, Object> param) {
		int result = sqlSession.update("member.deleteMember", param);
		return result == 1;
	}

}
