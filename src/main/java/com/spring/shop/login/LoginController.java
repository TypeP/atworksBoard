package com.spring.shop.login;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("content", "login.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model,HttpServletRequest request) {
		model.addAttribute("content", "main.jsp");
		request.getSession().invalidate();
		return "home";
	}
	
	@RequestMapping(value = "/resign", method = RequestMethod.GET)
	public String resign(Model model,HttpServletRequest request) {
		model.addAttribute("content", "resign.jsp");
		return "home";
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Model model) {
		model.addAttribute("content", "signUp.jsp");
		return "home";
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model,HttpServletRequest request) {
		model.addAttribute("content", "update.jsp");
		return "home";
	}
		
	//ȸ����� ������������ ������ ������ͼ� DB�� �ִ´�
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(MemberDto dto, Model model, MultipartHttpServletRequest req, @RequestParam("file") MultipartFile file) throws Exception {
		int result = memberService.register(dto, req, file);
		if (result > 0) {
			model.addAttribute("MSG", "ȸ�����Լ���");
			model.addAttribute("content", "login.jsp");
		} else {
			model.addAttribute("MSG", "ȸ�����Խ���");
			model.addAttribute("content", "signUp.jsp");
		}
		return "home";
	}
	
	//�α��� ���̵�� ��й�ȣ�� Ȯ���� �������� �α��� �̷��� ����Ѵ�
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginOk(UserBoardDto dto, Model model,HttpServletRequest request) {
		UserBoardDto loginUser = memberService.login(dto);
		if (loginUser != null) {
			model.addAttribute("MSG", "�α��μ���");
			request.getSession().setAttribute("loginUser", loginUser);
			model.addAttribute("content", "main.jsp");
		} else {
			model.addAttribute("MSG", "�α��ν���");
			model.addAttribute("content", "login.jsp");
		}
		return "home";
	}
	
	//������ ���������� Ȯ���� DB�� ������ �����Ѵ�
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(UserBoardDto dto, Model model, MultipartHttpServletRequest req,	@RequestParam("file") MultipartFile file) throws Exception {
		int result = memberService.update(dto, req, file);
		if (result > 0) {
			UserBoardDto loginUser = memberService.login(dto);
			model.addAttribute("MSG", "ȸ����������");
			req.getSession().setAttribute("loginUser", loginUser);
			model.addAttribute("content", "update.jsp");
		} else {
			model.addAttribute("MSG", "ȸ����������");
			model.addAttribute("content", "update.jsp");
		}
		return "home";
	}
	 
	//ȸ���� ��й�ȣ�� ������ͼ� DB�� �����Ϳ� �´��� Ȯ���� ������ Ż�� �Ѵ�.
	@RequestMapping(value = "/withdrawl", method = RequestMethod.POST)
	public String withdrawl(UserBoardDto dto,Model model,HttpServletRequest req) {		
		int result = memberService.delete(dto, req);
		if (result > 0) {
			model.addAttribute("MSG", "ȸ��Ż�� ����");
			model.addAttribute("content", "main.jsp");
			req.getSession().invalidate();
		} else {
			model.addAttribute("MSG", "ȸ��Ż�� ����");
			model.addAttribute("content", "main.jsp");			
		}
		return "home";
	}
}
