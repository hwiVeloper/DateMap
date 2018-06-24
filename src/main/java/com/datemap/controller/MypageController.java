package com.datemap.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.datemap.dto.MemberDTO;
import com.datemap.service.MypageService;

@Controller
@RequestMapping(value="/mypage/*")
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Inject
	private MypageService service;
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String mypageMain(Model model, HttpSession session) throws Exception{
		
		model.addAttribute("session", session.getAttribute("session"));
		
		MemberDTO sess = (MemberDTO) session.getAttribute("session");
		
		logger.info(sess.toString());
		
		model.addAttribute("mypageList", service.selectMyList(sess.getId()));
		
		return "mypage/main";
	}
	
	@RequestMapping(value="/detail/{idx}", method=RequestMethod.GET)
	public String view(Model model, HttpSession session, @PathVariable("idx") Integer postIdx) throws Exception {
		
		
		
		return "mypage/detail";
	}
}
