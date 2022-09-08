package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.notice.NoticeDto;
import com.spring.shop.utils.SearchDto;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	//공지사항 페이지로 이동
	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String gallery(Model model) {
		model.addAttribute("content", "gallery.jsp");
		return "home";
	}

	@RequestMapping(value = "/notice/getGalleryList", method = RequestMethod.GET)
	@ResponseBody	//비동기통신 처리 어노테이션
	public Map<String,Object> getGalleryList(SearchDto dto) {
		return galleryService.listGallery(dto);
	}
	
	//갤러리 조회
	@RequestMapping(value = "/contentGallery", method = RequestMethod.GET)
	public String contentGallery(int gl_no, Model model) {
		GalleryDto dto= galleryService.getGalleryDetail(gl_no);
		model.addAttribute("dto",dto);
		model.addAttribute("content","contentGallery.jsp");
		return "home";
	}
	
	//갤러리 수정 페이지 이동
	@RequestMapping(value = "/modifyBoard", method = RequestMethod.GET)
	public String modifyBoard(int gl_no,Model model) {
		GalleryDto dto= galleryService.getGalleryDetail(gl_no);
		model.addAttribute("dto",dto);
		model.addAttribute("content","modifyGallery.jsp");
		return "home";
	}
	
	//갤러리 수정
	@RequestMapping(value = "/updateGallery", method = RequestMethod.POST)
	public String updateGallery(GalleryDto dto, Model model, MultipartHttpServletRequest req, @RequestPart("file") List<MultipartFile> file) throws Exception {
		int result = galleryService.updateGallery(dto, req, file);
		if (result > 0) {
			GalleryDto dto1 = galleryService.getGalleryDetail(dto.getGl_no());
			model.addAttribute("MSG", "수정완료");
			model.addAttribute("dto", dto1);
			model.addAttribute("content", "contentGallery.jsp");
		} else {
			model.addAttribute("MSG", "회원수정실패");
			model.addAttribute("content", "modifyGallery.jsp");
		}
		return "home";
	}
	
	//갤러리 글쓰기 페이지 이동
	@RequestMapping(value = "/writeBoard", method = RequestMethod.GET)
	public String writeBoard(Model model) {
		model.addAttribute("content","writeGallery.jsp");
		return "home";
	}
	
	//갤러리 글쓰기
	@RequestMapping(value = "/writeGallery", method = RequestMethod.POST)
	public String writeGallery(GalleryDto dto,Model model, MultipartHttpServletRequest req,@RequestParam("file")List<MultipartFile> file) throws Exception {
		int result = galleryService.writeGallery(dto,req,file);
		if(result>0) {
			model.addAttribute("MSG", "글작성성공");
			model.addAttribute("content","gallery.jsp");
		} else {
			model.addAttribute("MSG", "글작성실패");
			model.addAttribute("content","writeGallery.jsp");
		}
		
		return "home";
	}
	
	//갤러리게시물을 삭제
	@RequestMapping(value="deleteGallery", method=RequestMethod.GET)
	public String deleteGallery(int gl_no,Model model, HttpServletRequest req) {
		int result=galleryService.deleteGallery(gl_no,req);
		if (result > 0) {
			model.addAttribute("MSG", "갤러리삭제성공");
			model.addAttribute("content", "gallery.jsp");
		} else {
			model.addAttribute("MSG", "갤러리삭제실패");
			GalleryDto dto = galleryService.getGalleryDetail(gl_no);
			model.addAttribute("dto", dto);
			model.addAttribute("content","contentNotice.jsp");
		}
		return "home";	
	}
}
