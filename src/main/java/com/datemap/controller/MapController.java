package com.datemap.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.datemap.dao.FileDAO;
import com.datemap.dao.MapDAO;
import com.datemap.dto.FileDTO;
import com.datemap.dto.MapDTO;
import com.datemap.dto.PostDTO;
import com.datemap.vo.MapRegisterVO;

@Controller
public class MapController {
	@Inject
	private MapDAO mapdao;
	
	@Inject
	private FileDAO filedao;
	
private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String register(Locale locale, Model model) throws Exception {
			
			MapRegisterVO vo = new MapRegisterVO();
			vo.setLat(37.579617000000);
			vo.setLng(126.97704099999999);

			List<MapRegisterVO> list = mapdao.list(vo);
			model.addAttribute("list",list);
			logger.info(list.toString());
			return "list";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestParam("file") MultipartFile uploadFile,MultipartHttpServletRequest req) {
		ResponseEntity<String> entity = null;
		
		MultipartFile file = req.getFile("file");
		String path = "";
        String fileName = "";
        
        OutputStream out = null;
        PrintWriter printWriter = null;

		logger.info("title ===> " + reqToDto(req, "title"));
		logger.info("originalFilename ==> " + file.getOriginalFilename());
		logger.info("fileSize ===> " + file.getSize());
		logger.info("contentType ===> " + file.getContentType());
		try {
			MapRegisterVO rvo = new MapRegisterVO();
			rvo.setMapId(reqToDto(req, "mapId"));
		    List<MapRegisterVO> list = mapdao.list(rvo);
		      
		    logger.info("list.size() ===> " + list.size());
		    if(list.size() == 0){
				MapDTO mapDto = new MapDTO();
				mapDto.setId(reqToDto(req, "mapId"));
				mapDto.setLatitude(Double.parseDouble(reqToDto(req, "lat")));
				mapDto.setLongtitude(Double.parseDouble(reqToDto(req, "lng")));
				mapDto.setPlaceName(reqToDto(req, "placeName"));

				mapdao.createMap(mapDto);
		    }
			PostDTO postDto = new PostDTO();
			postDto.setTitle(reqToDto(req, "title"));
			postDto.setContent(reqToDto(req, "content"));
			postDto.setMapId(reqToDto(req, "mapId"));
			postDto.setMemberId("hwi");
		
			mapdao.createPost(postDto);
			
			int insertedPostId = postDto.getIdx();
			
			logger.info("inserted post id ===> " + insertedPostId);
			
			fileName = String.valueOf(insertedPostId);
			
			int pos = file.getOriginalFilename().lastIndexOf( "." );
			String extension = file.getOriginalFilename().substring( pos + 1 );
			
			fileName += ".".concat(extension);
			
			
            byte[] bytes = uploadFile.getBytes();

            
            String uploadPath = req.getSession().getServletContext().getRealPath("/upload");
//            String attachPath = "/upload";
            File filecheck = new File(uploadPath);

            
            System.out.println("UtilFile fileUpload final fileName : " + fileName);
            System.out.println("UtilFile fileUpload file : " + file);
            
            out = new FileOutputStream(filecheck + "/" + fileName);
            
            System.out.println("UtilFile fileUpload out : " + out);
            
            out.write(bytes);
			
			//파일 업로드 db 처리
			FileDTO fileDto = new FileDTO();
			fileDto.setPostIdx(insertedPostId);
			fileDto.setFileName(file.getOriginalFilename());
			fileDto.setFileSize(file.getSize());
			fileDto.setFileType(file.getContentType());
			fileDto.setFilePath("/upload");
			
			filedao.create(fileDto);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} finally {
			try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

		}
		return entity;
	}

	@RequestMapping(value = "/listAjax", method = RequestMethod.POST)
	public ResponseEntity<List<MapRegisterVO>> listAjax(Model model,@RequestBody MapRegisterVO vo) {
		ResponseEntity<List<MapRegisterVO>> entity = null;
		try {
			
			
			MapRegisterVO rvo = new MapRegisterVO();
			logger.info("ajax lat====> " + vo.getLat() + vo.getLng());
			rvo.setMapId(vo.getMapId());

			//rvo.setLat(37.579617000000);
			//rvo.setLng(126.977041000000);

		    List<MapRegisterVO> list = mapdao.list(rvo);
		    
		    logger.info("rvo"+rvo.toString());
		    //model.addAttribute("list",list);
			logger.info("ajaxlist"+ list.toString());
			//return "list";
			
			entity = new ResponseEntity<List<MapRegisterVO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<MapRegisterVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	public String reqToDto(MultipartHttpServletRequest req, String param) {
		return req.getParameter(param);
	}
}
