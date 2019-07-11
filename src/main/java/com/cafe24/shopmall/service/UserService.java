package com.cafe24.shopmall.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.shopmall.vo.UserVo;

@Service
public class UserService {
	
	public List<UserVo> userList;
	
	private UserService() {
		userList = new ArrayList<UserVo>();
		userList.add(new UserVo(1L,"tgif2014","강수진","sujin10","010-5555-1234","aufclakstp@naver.com"));
	}

	public Boolean existId(String id) {
//		UserVo vo = dao.selectById(id);
//		return vo == null;
		UserVo vo = userList.get(0);
		return vo.getId().equals(id);
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
		UserVo vo = userList.get(0);
		
		return vo.getId().equals(id) && vo.getPassword().equals(password);
	}

	public UserVo getUserInfo(Long no) {
		UserVo result = null;
		for(UserVo vo : userList) {
			if(vo.getCode()==no) {
				result = vo;
				break;
			}
		}
		return result;
	}
	
}
