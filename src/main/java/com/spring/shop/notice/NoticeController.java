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

//��Ʈ�ѷ� �������̼�
@Controller
public class NoticeController {
	
	//�ڵ� ���� �������̼�
	@Autowired 
	private NoticeService noticeService;
	
	//�������� ù������
	@RequestMapping(value = "/announcement", method = RequestMethod.GET)
	public String announcement(Model model) {
		model.addAttribute("content", "announcement.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/notice/getList", method = RequestMethod.GET)
	@ResponseBody	//�񵿱���� ó�� ������̼�
	public Map<String, Object> getNoticeList(SearchDto dto) {
		return noticeService.listNotice(dto);
	}
	
	//�������� ����������
	@RequestMapping(value= "/write", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("content","writeNotice.jsp");
		return "home";
	}
	
	//�������� ����
	@RequestMapping(value= "/writeNotice", method=RequestMethod.POST)
	public String writeNotice(NoticeDto dto,Model model,MultipartHttpServletRequest req, @RequestParam("file") List<MultipartFile> file) throws Exception {
		int result = noticeService.writeNotice(dto, req, file);
		if (result > 0) {
			model.addAttribute("MSG", "�����ۼ�����");
			model.addAttribute("content", "announcement.jsp");
		} else {
			model.addAttribute("MSG","�����ۼ�����");
			model.addAttribute("content","writeNotice.jsp");
		}
		return "home";
	}
	
	//�������� ������ȸ
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public String content(int seq, Model model) {
		NoticeDto dto = noticeService.getNoticeDetail(seq);
		model.addAttribute("dto", dto);
		model.addAttribute("content","contentNotice.jsp");
		return "home";
	}
	
	//���������� ����
	@RequestMapping(value="/deleteNotice", method=RequestMethod.GET)
	public String deleteNotice(int seq,Model model,HttpServletRequest req) {
		int result=noticeService.deleteNotice(seq,req);
		if (result > 0) {
			model.addAttribute("MSG", "��������");
			model.addAttribute("content", "announcement.jsp");
		} else {
			model.addAttribute("MSG", "������������");
			NoticeDto dto = noticeService.getNoticeDetail(seq);
			model.addAttribute("dto", dto);
			model.addAttribute("content","contentNotice.jsp");
		}
		return "home";
	}
	
	//�������׼���������
	@RequestMapping(value="/modifyNotice", method=RequestMethod.GET)
	public String modifyNotice(int seq, Model model) {
		NoticeDto dto = noticeService.getNoticeDetail(seq);
		model.addAttribute("dto", dto);
		model.addAttribute("content","modifyNotice.jsp");
		return "home";
	}
	
	//�������� ����
	@RequestMapping(value = "/updateNotice", method = RequestMethod.POST)
	public String updateNotice(NoticeDto dto, Model model, MultipartHttpServletRequest req, @RequestPart("file")List<MultipartFile> file) throws Exception {
		int result = noticeService.updateNotice(dto, req, file);
		if (result > 0) {
			NoticeDto dto1 = noticeService.getNoticeDetail(dto.getNi_no());
			model.addAttribute("MSG", "�����Ϸ�");
			model.addAttribute("dto", dto1);
			model.addAttribute("content", "contentNotice.jsp");
		} else {
			model.addAttribute("MSG", "ȸ����������");
			model.addAttribute("content", "modifyNotice.jsp");
		}
		return "home";
	}
}
