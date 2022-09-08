package com.spring.shop.notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OperatorBetween;
import org.springframework.stereotype.Repository;

import com.spring.shop.utils.SearchDto;

@Repository
public class NoticeDAOImpl implements NoticeDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<NoticeDto> listNotice(Map<String, Object> param) {
		return sqlSession.getMapper(NoticeMapper.class).listNotice(param);
	}
	@Override
	public int getNoticeTotalCnt(SearchDto dto) {
		return sqlSession.getMapper(NoticeMapper.class).getNoticeTotalCnt(dto);
	}
	@Override
	public int writeNotice(NoticeDto dto) {
		return sqlSession.getMapper(NoticeMapper.class).writeNotice(dto);	
	}
	@Override
	public int insertAttachNotice(List<Map<String, Object>> param) {
		return sqlSession.getMapper(NoticeMapper.class).insertAttachNotice(param);
	}
	@Override
	public NoticeDto getNoticeDetail(int ni_no) {
		return sqlSession.getMapper(NoticeMapper.class).getNoticeDetail(ni_no);
	}
	@Override
	public List<FileListDto> getFileList(int ni_no) {
		return sqlSession.getMapper(NoticeMapper.class).getFileList(ni_no);
	}
	@Override
	public int updateNotice(NoticeDto dto) {
		return sqlSession.getMapper(NoticeMapper.class).updateNotice(dto);
	}
	@Override
	public int updateAttachNotice(Map<String, Object> param) {
		return sqlSession.getMapper(NoticeMapper.class).updateNoticeAttach(param);
	}
	@Override
	public int deleteNotice(int seq) {
		return sqlSession.getMapper(NoticeMapper.class).deleteNotice(seq);
	}
	@Override
	public int deleteNoticeAttach(int seq) {
		return sqlSession.getMapper(NoticeMapper.class).deleteNoticeAttach(seq);
	}
}
