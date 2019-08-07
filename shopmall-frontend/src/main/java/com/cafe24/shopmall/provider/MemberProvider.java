package com.cafe24.shopmall.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.vo.MemberVo;

@Repository
public class MemberProvider {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	private final String URL = "http://localhost:8888/shopmall/";
	
	
	//로그인
	public MemberVo login(String id) {
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		
		JSONResultMemberVo  jsonResult = restTemplate.postForObject(URL+"api/member/login",vo,JSONResultMemberVo.class );
		
		return jsonResult.getData();
	}
	
	public static class JSONResultMemberVo extends JSONResult<MemberVo>{}
}
