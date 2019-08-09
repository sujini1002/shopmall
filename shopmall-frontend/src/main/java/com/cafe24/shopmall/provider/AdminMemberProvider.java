package com.cafe24.shopmall.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Repository;

import com.cafe24.shopmall.dto.JSONResult;
import com.cafe24.shopmall.vo.MemberVo;

@Repository
public class AdminMemberProvider {
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	private final String URL = "http://localhost:8888/shopmall/";
	
	public List<MemberVo> getList(){
		JSONResultListMember jsonResult = restTemplate.getForObject(URL+"api/admin/member", JSONResultListMember.class);
		return jsonResult.getData();
	}
	
	private static class JSONResultListMember extends JSONResult<List<MemberVo>>{}
}
