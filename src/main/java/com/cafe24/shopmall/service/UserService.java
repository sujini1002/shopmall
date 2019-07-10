package com.cafe24.shopmall.service;

import org.springframework.stereotype.Service;

import com.cafe24.shopmall.vo.UserVo;

@Service
public class UserService {

	public Boolean existId(String id) {
//		UserVo vo = dao.selectById(id);
//		return vo == null;
		String userId = "tgif2014";
		return userId.equals(id);
	}

	public UserVo userAdd(UserVo userVo) {
//		dao.insert(userVo);
//		UserVo result= dao.selectById(userVo.getId);
//		return  result;
		userVo.setCode(1L);
		return userVo;
	}
	
}
