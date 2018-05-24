package com.datemap.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datemap.dao.SignDAO;
import com.datemap.dto.MemberDTO;
import com.datemap.vo.LoginVO;

@Service
public class SignServiceImpl implements SignService{
	@Inject
	private SignDAO dao; 
	
	@Transactional
	@Override
	public void join(MemberDTO dto) throws Exception {
		dao.join(dto);
	}
	
	@Override
	public MemberDTO login(LoginVO vo) throws Exception {
		return dao.login(vo);
	}
	
	@Override
	public void keepLogin(String id, String sessionId, Date next) throws Exception {
		dao.keepLogin(id, sessionId, next);
	}
	
	@Override
	public MemberDTO checkLoginBefore(String value) throws Exception {
		return dao.checkUserWithSessionKey(value);
	}
}
