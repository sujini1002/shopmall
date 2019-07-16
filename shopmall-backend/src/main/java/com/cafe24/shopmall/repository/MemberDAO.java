package com.cafe24.shopmall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.vo.MemberVo;

@Repository
public class MemberDAO {
	
	public List<MemberVo> memberList;
	
	@Autowired
	private SqlSession sqlSession;

	public Boolean isIdExist(String id) {
		Long cnt = sqlSession.selectOne("member.selectById", id);
		return cnt == 1;
	}

	public Long insertMember(MemberVo memberVo) {
		sqlSession.insert("member.insertMember", memberVo);
		return memberVo.getCode();
	}

	public Long insertDeliver(MemberVo memberVo) {
		sqlSession.insert("member.insertDeliver",memberVo);
		return memberVo.getDeliverCode();
	}
}
