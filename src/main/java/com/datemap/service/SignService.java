package com.datemap.service;

import com.datemap.dto.MemberDTO;

public interface SignService {
	public void join(MemberDTO dto) throws Exception;
	
	public MemberDTO login(MemberDTO dto) throws Exception;
}
