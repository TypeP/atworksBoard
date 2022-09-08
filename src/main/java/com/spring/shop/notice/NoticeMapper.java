package com.spring.shop.notice;

import java.util.List;
import java.util.Map;

import com.spring.shop.utils.SearchDto;

public interface NoticeMapper {
	List<NoticeDto> listNotice(Map<String, Object> param);
	int writeNotice(NoticeDto dto);
	int insertAttachNotice(List<Map<String, Object>> param);
	int updateNotice(NoticeDto dto);
	int updateNoticeAttach(Map<String, Object> param);
	int deleteNotice(int seq);
	int deleteNoticeAttach(int seq);
	List<FileListDto> getFileList(int ni_no);
	NoticeDto getNoticeDetail(int ni_no);
	int getNoticeTotalCnt(SearchDto dto);
}
