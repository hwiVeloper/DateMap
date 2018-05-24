package com.datemap.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.datemap.dto.MemberDTO;
import com.datemap.vo.LoginVO;

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
	public MemberDTO login(LoginVO vo) throws Exception {
		return session.selectOne(namespace + ".login", vo);
	}
	
	@Override
	public void keepLogin(String id, String sessionId, Date next) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);
		
		session.update(namespace + ".keepLogin", paramMap);
	}
	
	@Override
	public MemberDTO checkUserWithSessionKey(String value) throws Exception {
		return session.selectOne(namespace + ".checkUserWithSessionKey", value);
	}
}
