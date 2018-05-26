package com.datemap.service;

import java.util.Date;

import com.datemap.dto.MemberDTO;
import com.datemap.vo.LoginVO;

public interface SignService {
	public void join(MemberDTO dto) throws Exception;
	
	public MemberDTO login(LoginVO vo) throws Exception;
	
	public void keepLogin(String id, String sessionId, Date next) throws Exception;
	
	public MemberDTO checkLoginBefore(String value) throws Exception;
}
