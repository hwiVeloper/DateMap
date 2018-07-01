package com.datemap.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datemap.dao.MapDAO;
import com.datemap.dto.MapDTO;
import com.datemap.dto.PostDTO;
import com.datemap.vo.MapRegisterVO;

@Repository
public class MapDAOImpl implements MapDAO{
	
	@Inject
	private SqlSession session;
	
	@Autowired
	private static String namespace = "com.datemap.mappers.MapMapper";
	
	@Override
	public List<MapRegisterVO> list(MapRegisterVO vo) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".list",vo);
	}
	@Override
	public void createMap(MapDTO dto) throws Exception {
		session.insert(namespace + ".createMap", dto);
	}
	
	@Override
	public int createPost(PostDTO dto) throws Exception {
		int id = session.insert(namespace + ".postMap", dto);
		
		return id;
		
	}
}
