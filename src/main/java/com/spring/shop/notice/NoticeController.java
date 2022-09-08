package com.spring.shop.notice;

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

import com.spring.shop.utils.SearchDto;

//컨트롤러 에노테이션
@Controller
public class NoticeController {
	
	//자동 연결 에노테이션
	@Autowired 
	private NoticeService noticeService;
	
	//공지사항 첫페이지
	@RequestMapping(value = "/announcement", method = RequestMethod.GET)
	public String announcement(Model model) {
		model.addAttribute("content", "announcement.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/notice/getList", method = RequestMethod.GET)
	@ResponseBody	//비동기통신 처리 어노테이션
	public Map<String, Object> getNoticeList(SearchDto dto) {
		return noticeService.listNotice(dto);
	}
	
	//공지사항 쓰기페이지
	@RequestMapping(value= "/write", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("content","writeNotice.jsp");
		return "home";
	}
	
	//공지사항 쓰기
	@RequestMapping(value= "/writeNotice", method=RequestMethod.POST)
	public String writeNotice(NoticeDto dto,Model model,MultipartHttpServletRequest req, @RequestParam("file") List<MultipartFile> file) throws Exception {
		int result = noticeService.writeNotice(dto, req, file);
		if (result > 0) {
			model.addAttribute("MSG", "공지작성성공");
			model.addAttribute("content", "announcement.jsp");
		} else {
			model.addAttribute("MSG","공지작성실패");
			model.addAttribute("content","writeNotice.jsp");
		}
		return "home";
	}
	
	//공지사항 내용조회
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public String content(int seq, Model model) {
		NoticeDto dto = noticeService.getNoticeDetail(seq);
		model.addAttribute("dto", dto);
		model.addAttribute("content","contentNotice.jsp");
		return "home";
	}
	
	//공지사항을 삭제
	@RequestMapping(value="/deleteNotice", method=RequestMethod.GET)
	public String deleteNotice(int seq,Model model,HttpServletRequest req) {
		int result=noticeService.deleteNotice(seq,req);
		if (result > 0) {
			model.addAttribute("MSG", "공지삭제");
			model.addAttribute("content", "announcement.jsp");
		} else {
			model.addAttribute("MSG", "공지삭제실패");
			NoticeDto dto = noticeService.getNoticeDetail(seq);
			model.addAttribute("dto", dto);
			model.addAttribute("content","contentNotice.jsp");
		}
		return "home";
	}
	
	//공지사항수정페이지
	@RequestMapping(value="/modifyNotice", method=RequestMethod.GET)
	public String modifyNotice(int seq, Model model) {
		NoticeDto dto = noticeService.getNoticeDetail(seq);
		model.addAttribute("dto", dto);
		model.addAttribute("content","modifyNotice.jsp");
		return "home";
	}
	
	//공지사항 수정
	@RequestMapping(value = "/updateNotice", method = RequestMethod.POST)
	public String updateNotice(NoticeDto dto, Model model, MultipartHttpServletRequest req, @RequestPart("file")List<MultipartFile> file) throws Exception {
		int result = noticeService.updateNotice(dto, req, file);
		if (result > 0) {
			NoticeDto dto1 = noticeService.getNoticeDetail(dto.getNi_no());
			model.addAttribute("MSG", "수정완료");
			model.addAttribute("dto", dto1);
			model.addAttribute("content", "contentNotice.jsp");
		} else {
			model.addAttribute("MSG", "회원수정실패");
			model.addAttribute("content", "modifyNotice.jsp");
		}
		return "home";
	}
}
