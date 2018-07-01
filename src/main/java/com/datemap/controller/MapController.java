package com.datemap.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.imgscalr.Scalr;
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
import com.datemap.dto.MemberDTO;
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
	public ResponseEntity<String> register(@RequestParam("file") MultipartFile uploadFile,MultipartHttpServletRequest req, HttpSession session, Model model) {
		ResponseEntity<String> entity = null;
		
		// session example
		MemberDTO sess = (MemberDTO) session.getAttribute("session");
		
		model.addAttribute("sessionData", sess);
		
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
			postDto.setMemberId(sess.getId());
		
			mapdao.createPost(postDto);
			
			int insertedPostId = postDto.getIdx();
			
			logger.info("inserted post id ===> " + insertedPostId);
			
			fileName = String.valueOf(insertedPostId);
			
			int pos = file.getOriginalFilename().lastIndexOf( "." );
			String extension = file.getOriginalFilename().substring( pos + 1 );
			System.out.println("fileType*********"+file.getOriginalFilename());
			String[] arr = file.getOriginalFilename().split("\\.");
			String ext = arr[arr.length-1];
			fileName += ".".concat(extension);
			
			
            byte[] bytes = uploadFile.getBytes();

            
            String uploadPath = req.getSession().getServletContext().getRealPath("/upload");
            File filecheck = new File(uploadPath);
            String orgImg = uploadPath+insertedPostId+"."+ext;//원본파일
            String thumbImg = uploadPath+insertedPostId+"_s"+ext;//썸네일파일
            int thumbWidth = 160 ;//썸네일 가로
            int thumbHeight = 160 ;//썸네일 세로
            
            System.out.println("UtilFile fileUpload final fileName : " + fileName);
            System.out.println("UtilFile fileUpload file : " + file);
            
            out = new FileOutputStream(filecheck + "/" + fileName);
            
            System.out.println("UtilFile fileUpload out : " + out);

            out.write(bytes);
            
            BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
    		
    		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
    		
    		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
    		
    		File newFile = new File(thumbnailName);
    		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
    		
    		ImageIO.write(destImg, formatName.toUpperCase(), newFile);

      
			
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
