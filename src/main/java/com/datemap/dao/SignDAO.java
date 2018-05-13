package com.datemap.dao;

import com.datemap.dto.MemberDTO;

public interface SignDAO {
	public void join(MemberDTO dto) throws Exception;
	
	public MemberDTO login(MemberDTO dto) throws Exception;
}
