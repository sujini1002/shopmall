package com.cafe24.shopmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shopmall.provider.AdminMemberProvider;
import com.cafe24.shopmall.vo.MemberVo;

@Service
public class AdminMemberService {
	
	@Autowired
	private AdminMemberProvider memberProvider;
	
	public List<MemberVo> getList(){
		return memberProvider.getList();
	}
}
