package com.cafe24.shopmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.provider.MemberProvider;
import com.cafe24.shopmall.vo.MemberVo;

@Service
public class MemberService {
	
	@Autowired
	private MemberProvider memberProvider;
	
	public Long add(MemberVo memberVo) {
		return memberProvider.insert(memberVo);
	}
}
