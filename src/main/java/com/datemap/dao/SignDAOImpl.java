package com.datemap.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.datemap.dto.MemberDTO;

@Repository
public class SignDAOImpl implements SignDAO {
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.datemap.mapper.SignMapper";
	
	@Override
	public void join(MemberDTO dto) throws Exception{
		session.insert(namespace + ".join", dto);
	}
	
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return session.selectOne(namespace + ".login", dto);
	}
}
