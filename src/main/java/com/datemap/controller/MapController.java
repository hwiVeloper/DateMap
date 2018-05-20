package com.datemap.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.datemap.dao.MapDAO;
import com.datemap.dto.MapDTO;
import com.datemap.dto.PostDTO;
import com.datemap.vo.MapRegisterVO;

@Controller
public class MapController {
	@Inject
	private MapDAO mapdao;
	
private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String register(Locale locale, Model model) throws Exception {
			
			MapRegisterVO vo = new MapRegisterVO();
			vo.setLat("37.579617000000");
			vo.setLng("126.977041000000");

			List<MapRegisterVO> list = mapdao.list(vo);
			model.addAttribute("list",list);
			logger.debug(list.toString());
			return "list";
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody MapRegisterVO vo) {
		ResponseEntity<String> entity = null;
		try {
			logger.info("****"+vo.toString());
			MapDTO mapDto = new MapDTO();
			mapDto.setLatitude(vo.getLat());
			mapDto.setLongtitude(vo.getLng());
			mapDto.setPlaceName(vo.getPlaceName());
			
			
			mapdao.createMap(mapDto);
			
			PostDTO postDto = new PostDTO();
			postDto.setTitle(vo.getTitle());
			postDto.setContent(vo.getContent());
			postDto.setMapIdx(1);
			postDto.setMemberId("hwi");
		
			mapdao.createPost(postDto);
			
			//model.addAttribute("event", event);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
