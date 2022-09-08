package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.utils.SearchDto;

public interface GalleryService {
	/*
	 * 리스트를 가지고오는 메서드
	 * 
	 * 갤러리 게시물을 작성하는 메서드
	 * 여러개의 사진을 넣을수 있다
	 * 
	 * 갤러리 상세 내역 확인하는 메서드
	 * 어떤 사진이 들어왓는지 확인가능하다
	 * 
	 * 삭제하는 메서드 
	 * db에 있는 데이터와 서버에 있는 사진도 삭제한다
	 * 
	 * 수정하는 메서드
	 * 수정할 파일이 없으면 board_attach테이블에는 자료를 넣지않고
	 * 수정할 파일이 있으면 기존에 파일이 있을때에는 서버에있는 자료를 삭제하고 board_attach에 입력한고 파일을 서버에 올린다
	 * 수정할 파일이 있으면 기존에 파일이 없을때에는 board_attach 입력하고 파일을 서버에 올린다
	 */
	
	Map<String,Object> listGallery(SearchDto dto);
	int writeGallery(GalleryDto dto, MultipartHttpServletRequest req, List<MultipartFile> files)throws Exception;
	GalleryDto getGalleryDetail(int gl_no);
	int deleteGallery(int gl_no, HttpServletRequest req);
	int updateGallery(GalleryDto dto, MultipartHttpServletRequest req, List<MultipartFile> files)throws Exception;
}
