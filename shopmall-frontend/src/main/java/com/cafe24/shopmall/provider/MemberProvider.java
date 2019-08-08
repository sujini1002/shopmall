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
	
	// 아이디 중복 체크
	public Boolean checkId(String id) {
		JSONResultObject jsonResult = restTemplate.getForObject(URL+"api/member/checkid/"+id,JSONResultObject.class);
		return Boolean.parseBoolean(jsonResult.getData().toString());
	}
	
	//로그인
	public MemberVo login(String id) {
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		
		JSONResultMemberVo  jsonResult = restTemplate.postForObject(URL+"api/member/login",vo,JSONResultMemberVo.class );
		
		return jsonResult.getData();
	}
	
	public Long insert(MemberVo memberVo) {
		JSONResultObject  jsonResult = restTemplate.postForObject(URL+"api/member",memberVo,JSONResultObject.class );
		return Long.parseLong(jsonResult.getData().toString());
	}
	
	public static class JSONResultMemberVo extends JSONResult<MemberVo>{}
	public static class JSONResultObject extends JSONResult<Object>{}
	
}
