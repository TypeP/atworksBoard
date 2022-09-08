package com.spring.shop.notice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.utils.SearchDto;

public interface NoticeService {
	/*
	 * ����Ʈ�� ��������� �޼���
	 * 
	 * 
	 * ���������� �ۼ��ϴ� �޼���
	 * �������� ������ �־ �Է��Ҽ� �ִ�.
	 * 
	 * 
	 * �� �������� ������ ������ ���� �޼���
	 * �������� ������ ������ �� �� �ִ�.
	 * 
	 * 
	 * �����ϴ� �޼��� 
	 * ������ ������ ������ board_attach���̺��� �ڷḦ �����ʰ�
	 * ������ ������ ������ ������ ������ ���������� �������ִ� �ڷḦ �����ϰ� board_attach�� �Է��Ѱ� ������ ������ �ø���
	 * ������ ������ ������ ������ ������ ���������� board_attach �Է��ϰ� ������ ������ �ø���
	 * 
	 * �����ϴ� �޼��� 
	 * DB���� �ڷḦ �����ϰ� ���������� ������ �����.
	 * 
	 */
	
	
	Map<String, Object> listNotice(SearchDto dto);
	int writeNotice(NoticeDto dto,MultipartHttpServletRequest req, List<MultipartFile> file) throws Exception ;
	NoticeDto getNoticeDetail(int ni_no);
	int updateNotice(NoticeDto dto, MultipartHttpServletRequest req, List<MultipartFile> file)throws Exception;
	int deleteNotice(int seq,HttpServletRequest req);
}
