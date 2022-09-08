package com.spring.shop.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.notice.NoticeDto;

public interface MemberService {
	int register(MemberDto dto, MultipartHttpServletRequest req, MultipartFile file) throws Exception;
	UserBoardDto login(UserBoardDto dto);
	 int update(UserBoardDto dto,MultipartHttpServletRequest req,MultipartFile file)throws
	 Exception;
	int delete(UserBoardDto dto,HttpServletRequest req);
}
