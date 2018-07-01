package com.datemap.dao;

import java.util.List;

import com.datemap.vo.MyPageListVO;

public interface MypageDAO {
	public List<MyPageListVO> selectMyList(String id) throws Exception;
}
