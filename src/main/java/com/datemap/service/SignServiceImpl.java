package com.datemap.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datemap.dao.SignDAO;
import com.datemap.dto.MemberDTO;

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
	public MemberDTO login(MemberDTO dto) throws Exception {
		return dao.login(dto);
	}
}
