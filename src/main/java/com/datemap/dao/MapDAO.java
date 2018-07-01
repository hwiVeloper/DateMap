package com.datemap.dao;

import java.util.List;

import com.datemap.dto.MapDTO;
import com.datemap.dto.PostDTO;
import com.datemap.vo.MapRegisterVO;


public interface MapDAO {
	public void createMap(MapDTO dto)throws Exception;
	public int createPost(PostDTO dto)throws Exception;
	List<MapRegisterVO> list(MapRegisterVO vo) throws Exception;
}
