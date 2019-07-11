package com.cafe24.shopmall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shopmall.vo.MemberVo;

@Service
public class MemberService {
	
	public List<MemberVo> memberList;
	
	private MemberService() {
		memberList = new ArrayList<MemberVo>();
		memberList.add(new MemberVo(1L,"tgif2014","강수진","sujin10","010-5555-1234","aufclakstp@naver.com"));
		
	}

	public Boolean existId(String id) {
//		UserVo vo = dao.selectById(id);
//		return vo == null;
		MemberVo vo = memberList.get(0);
		return vo.getId().equals(id);
	}

	public MemberVo userAdd(MemberVo memberVo) {
//		dao.insert(userVo);
//		UserVo result= dao.selectById(userVo.getId);
//		return  result;
		memberVo.setCode(1L);
		return memberVo;
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
	
}
