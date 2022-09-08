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
	
	//�������� �������� �̵�
	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String gallery(Model model) {
		model.addAttribute("content", "gallery.jsp");
		return "home";
	}

	@RequestMapping(value = "/notice/getGalleryList", method = RequestMethod.GET)
	@ResponseBody	//�񵿱���� ó�� ������̼�
	public Map<String,Object> getGalleryList(SearchDto dto) {
		return galleryService.listGallery(dto);
	}
	
	//������ ��ȸ
	@RequestMapping(value = "/contentGallery", method = RequestMethod.GET)
	public String contentGallery(int gl_no, Model model) {
		GalleryDto dto= galleryService.getGalleryDetail(gl_no);
		model.addAttribute("dto",dto);
		model.addAttribute("content","contentGallery.jsp");
		return "home";
	}
	
	//������ ���� ������ �̵�
	@RequestMapping(value = "/modifyBoard", method = RequestMethod.GET)
	public String modifyBoard(int gl_no,Model model) {
		GalleryDto dto= galleryService.getGalleryDetail(gl_no);
		model.addAttribute("dto",dto);
		model.addAttribute("content","modifyGallery.jsp");
		return "home";
	}
	
	//������ ����
	@RequestMapping(value = "/updateGallery", method = RequestMethod.POST)
	public String updateGallery(GalleryDto dto, Model model, MultipartHttpServletRequest req, @RequestPart("file") List<MultipartFile> file) throws Exception {
		int result = galleryService.updateGallery(dto, req, file);
		if (result > 0) {
			GalleryDto dto1 = galleryService.getGalleryDetail(dto.getGl_no());
			model.addAttribute("MSG", "�����Ϸ�");
			model.addAttribute("dto", dto1);
			model.addAttribute("content", "contentGallery.jsp");
		} else {
			model.addAttribute("MSG", "ȸ����������");
			model.addAttribute("content", "modifyGallery.jsp");
		}
		return "home";
	}
	
	//������ �۾��� ������ �̵�
	@RequestMapping(value = "/writeBoard", method = RequestMethod.GET)
	public String writeBoard(Model model) {
		model.addAttribute("content","writeGallery.jsp");
		return "home";
	}
	
	//������ �۾���
	@RequestMapping(value = "/writeGallery", method = RequestMethod.POST)
	public String writeGallery(GalleryDto dto,Model model, MultipartHttpServletRequest req,@RequestParam("file")List<MultipartFile> file) throws Exception {
		int result = galleryService.writeGallery(dto,req,file);
		if(result>0) {
			model.addAttribute("MSG", "���ۼ�����");
			model.addAttribute("content","gallery.jsp");
		} else {
			model.addAttribute("MSG", "���ۼ�����");
			model.addAttribute("content","writeGallery.jsp");
		}
		
		return "home";
	}
	
	//�������Խù��� ����
	@RequestMapping(value="deleteGallery", method=RequestMethod.GET)
	public String deleteGallery(int gl_no,Model model, HttpServletRequest req) {
		int result=galleryService.deleteGallery(gl_no,req);
		if (result > 0) {
			model.addAttribute("MSG", "��������������");
			model.addAttribute("content", "gallery.jsp");
		} else {
			model.addAttribute("MSG", "��������������");
			GalleryDto dto = galleryService.getGalleryDetail(gl_no);
			model.addAttribute("dto", dto);
			model.addAttribute("content","contentNotice.jsp");
		}
		return "home";	
	}
}
