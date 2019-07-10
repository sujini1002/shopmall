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

	public Boolean login(String id, String password) {
//		UserVo vo = dao.selectUserByIdPw(id,password);
//		return vo == null;
		String userId = "tgif2014";
		String userPassword = "sujin10";
		return userId.equals(id) && userPassword.equals(password);
	}
	
}
