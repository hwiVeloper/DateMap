package com.datemap.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.datemap.dao.MypageDAO;
import com.datemap.vo.MyPageListVO;

@Service
public class MypageServiceImpl implements MypageService {
	@Inject
	private MypageDAO dao;

	@Override
	public List<MyPageListVO> selectMyList(String id) throws Exception {
		return dao.selectMyList(id);
	}
}
