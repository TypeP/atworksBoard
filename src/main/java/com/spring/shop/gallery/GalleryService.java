package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.utils.SearchDto;

public interface GalleryService {
	/*
	 * ����Ʈ�� ��������� �޼���
	 * 
	 * ������ �Խù��� �ۼ��ϴ� �޼���
	 * �������� ������ ������ �ִ�
	 * 
	 * ������ �� ���� Ȯ���ϴ� �޼���
	 * � ������ ���Ӵ��� Ȯ�ΰ����ϴ�
	 * 
	 * �����ϴ� �޼��� 
	 * db�� �ִ� �����Ϳ� ������ �ִ� ������ �����Ѵ�
	 * 
	 * �����ϴ� �޼���
	 * ������ ������ ������ board_attach���̺��� �ڷḦ �����ʰ�
	 * ������ ������ ������ ������ ������ ���������� �������ִ� �ڷḦ �����ϰ� board_attach�� �Է��Ѱ� ������ ������ �ø���
	 * ������ ������ ������ ������ ������ ���������� board_attach �Է��ϰ� ������ ������ �ø���
	 */
	
	Map<String,Object> listGallery(SearchDto dto);
	int writeGallery(GalleryDto dto, MultipartHttpServletRequest req, List<MultipartFile> files)throws Exception;
	GalleryDto getGalleryDetail(int gl_no);
	int deleteGallery(int gl_no, HttpServletRequest req);
	int updateGallery(GalleryDto dto, MultipartHttpServletRequest req, List<MultipartFile> files)throws Exception;
}
