package com.datemap.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.datemap.vo.MyPageListVO;

@Repository
public class MypageDAOImpl implements MypageDAO {
	
	@Inject
	SqlSession session;
	
	private static String namespace = "com.datemap.mappers.MypageMapper";

	@Override
	public List<MyPageListVO> selectMyList(String id) throws Exception {
		return session.selectList(namespace + ".selectMyList", id);
	}
	
}
