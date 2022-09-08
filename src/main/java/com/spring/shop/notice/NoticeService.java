package com.spring.shop.notice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.utils.SearchDto;

public interface NoticeService {
	/*
	 * 리스트를 가지고오는 메서드
	 * 
	 * 
	 * 공지사항을 작성하는 메서드
	 * 여러개의 파일을 넣어서 입력할수 있다.
	 * 
	 * 
	 * 상세 페이지의 내용을 가지고 오는 메서드
	 * 여러개의 파일을 가지고 올 수 있다.
	 * 
	 * 
	 * 수정하는 메서드 
	 * 수정할 파일이 없으면 board_attach테이블에는 자료를 넣지않고
	 * 수정할 파일이 있으면 기존에 파일이 있을때에는 서버에있는 자료를 삭제하고 board_attach에 입력한고 파일을 서버에 올린다
	 * 수정할 파일이 있으면 기존에 파일이 없을때에는 board_attach 입력하고 파일을 서버에 올린다
	 * 
	 * 삭제하는 메서드 
	 * DB에서 자료를 삭제하고 서버에서도 파일을 지운다.
	 * 
	 */
	
	
	Map<String, Object> listNotice(SearchDto dto);
	int writeNotice(NoticeDto dto,MultipartHttpServletRequest req, List<MultipartFile> file) throws Exception ;
	NoticeDto getNoticeDetail(int ni_no);
	int updateNotice(NoticeDto dto, MultipartHttpServletRequest req, List<MultipartFile> file)throws Exception;
	int deleteNotice(int seq,HttpServletRequest req);
}
