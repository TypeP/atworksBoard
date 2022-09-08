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
		
	//회원등록 폼페이지에서 정보를 가지고와서 DB에 넣는다
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(MemberDto dto, Model model, MultipartHttpServletRequest req, @RequestParam("file") MultipartFile file) throws Exception {
		int result = memberService.register(dto, req, file);
		if (result > 0) {
			model.addAttribute("MSG", "회원가입성공");
			model.addAttribute("content", "login.jsp");
		} else {
			model.addAttribute("MSG", "회원가입실패");
			model.addAttribute("content", "signUp.jsp");
		}
		return "home";
	}
	
	//로그인 아이디와 비밀번호를 확인후 세션으로 로그인 이력을 등록한다
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginOk(UserBoardDto dto, Model model,HttpServletRequest request) {
		UserBoardDto loginUser = memberService.login(dto);
		if (loginUser != null) {
			model.addAttribute("MSG", "로그인성공");
			request.getSession().setAttribute("loginUser", loginUser);
			model.addAttribute("content", "main.jsp");
		} else {
			model.addAttribute("MSG", "로그인실패");
			model.addAttribute("content", "login.jsp");
		}
		return "home";
	}
	
	//수정할 유저정보를 확인후 DB에 정보를 수정한다
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(UserBoardDto dto, Model model, MultipartHttpServletRequest req,	@RequestParam("file") MultipartFile file) throws Exception {
		int result = memberService.update(dto, req, file);
		if (result > 0) {
			UserBoardDto loginUser = memberService.login(dto);
			model.addAttribute("MSG", "회원수정성공");
			req.getSession().setAttribute("loginUser", loginUser);
			model.addAttribute("content", "update.jsp");
		} else {
			model.addAttribute("MSG", "회원수정실패");
			model.addAttribute("content", "update.jsp");
		}
		return "home";
	}
	 
	//회원의 비밀번호를 가지고와서 DB에 데이터와 맞는지 확인후 맞으면 탈퇴를 한다.
	@RequestMapping(value = "/withdrawl", method = RequestMethod.POST)
	public String withdrawl(UserBoardDto dto,Model model,HttpServletRequest req) {		
		int result = memberService.delete(dto, req);
		if (result > 0) {
			model.addAttribute("MSG", "회원탈퇴 성공");
			model.addAttribute("content", "main.jsp");
			req.getSession().invalidate();
		} else {
			model.addAttribute("MSG", "회원탈퇴 실패");
			model.addAttribute("content", "main.jsp");			
		}
		return "home";
	}
}
