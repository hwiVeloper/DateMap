package com.datemap.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.datemap.dto.MemberDTO;
import com.datemap.service.SignService;

@Controller
public class SignController {
	private static final Logger logger = LoggerFactory.getLogger(SignController.class);
	
	@Inject
	private SignService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @ModelAttribute("loginFailMsg") final String loginFailMsg,
			@ModelAttribute("logoutMsg") final String logoutMsg) {
		logger.info("===== GET login");
		
		// 로그인 실패 메세지
		if (loginFailMsg != null) {
			model.addAttribute("loginFailMsg", loginFailMsg);
		}
		
		// 로그아웃 메세지
		if (logoutMsg != null) {
			model.addAttribute("logoutMsg", logoutMsg);
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest req, HttpSession session, RedirectAttributes ra, Model model) throws Exception {
		logger.info("===== POST login");
		
		
		try {
			MemberDTO dto = new MemberDTO();
			
			dto.setId(req.getParameter("email"));
			dto.setPassword(req.getParameter("password"));
			
			MemberDTO resultMember = service.login(dto);
			
			if (resultMember == null) {
				ra.addFlashAttribute("loginFailMsg", "로그인 정보가 없습니다.");
				return "redirect:/login";
			} else {
				logger.info("로그인 성공");
				
				// 로그인 성공 후 세션 정보 세팅
				session.setAttribute("session", resultMember);
				
				return "redirect:/";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String register(HttpServletRequest req, HttpSession session, RedirectAttributes ra, Model model) {		
		try {
			MemberDTO dto = new MemberDTO();
			
			dto.setId(req.getParameter("id"));
			dto.setPassword(req.getParameter("password"));
			dto.setName(req.getParameter("name"));
			dto.setNickname(req.getParameter("nickname"));
			dto.setBirthday(req.getParameter("birthday").replaceAll("-", ""));
			dto.setSex(Integer.parseInt(req.getParameter("sex")));
			
			service.join(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ra.addFlashAttribute("가입이 완료되었습니다.");
		
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes ra, Model model) {
		Object obj = session.getAttribute("session");
		
		if (obj != null) {
			session.removeAttribute("session");
			session.invalidate();
		}
		
		ra.addFlashAttribute("logoutMsg", "로그아웃되었습니다.");
		
		return "redirect:/login";
	}
}
