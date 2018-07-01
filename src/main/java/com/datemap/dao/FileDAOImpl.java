package com.datemap.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datemap.dto.FileDTO;

@Repository
public class FileDAOImpl implements FileDAO {

	@Inject
	private SqlSession session;

	@Autowired
	private static String namespace = "com.datemap.mappers.FileMapper";
	
	@Override
	public void create(FileDTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace + ".fileInsert", dto);
	}

}
