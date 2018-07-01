package com.datemap.service;

import java.util.List;

import com.datemap.vo.MyPageListVO;

public interface MypageService {
	public List<MyPageListVO> selectMyList(String id) throws Exception;
}
