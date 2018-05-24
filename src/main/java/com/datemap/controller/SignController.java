package com.datemap.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.datemap.dto.MemberDTO;
import com.datemap.service.SignService;
import com.datemap.vo.LoginVO;

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
		
		try {
			LoginVO vo = new LoginVO();
			
			vo.setId(req.getParameter("email"));
			vo.setPassword(req.getParameter("password"));
			vo.setUseCookie(Boolean.parseBoolean(req.getParameter("useCookie")));
			
			MemberDTO resultMember = service.login(vo);
			
			if (resultMember == null) {
				ra.addFlashAttribute("loginFailMsg", "로그인 정보가 없습니다.");
				return "redirect:/login";
			} else {
				logger.info("로그인 성공");
				
				// 로그인 성공 후 세션 정보 세팅
				session.setAttribute("session", resultMember);
				
				if (vo.isUseCookie()) {
					int amount = 60 * 60 * 24 * 7;
					
					Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
					
					service.keepLogin(vo.getId(), session.getId(), sessionLimit);
				}
				
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
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes ra, Model model) {
		Object obj = session.getAttribute("session");
		
		if (obj != null) {
			session.removeAttribute("session");
			session.invalidate();
		}
		
		if (obj != null) {
			MemberDTO dto = (MemberDTO) obj;
			
			session.removeAttribute("session");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
//				service.keepLogin(dto.getId(), session.getId(), new Date());
			}
		}
		
		ra.addFlashAttribute("logoutMsg", "로그아웃되었습니다.");
		
		return "redirect:/login";
	}
}
