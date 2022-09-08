package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.shop.notice.FileListDto;
import com.spring.shop.utils.SearchDto;

@Repository
public class GalleryDAOImpl implements GalleryDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<GalleryFileDto> listGallery(Map<String, Object> param) {
		return sqlSession.getMapper(GalleryMapper.class).listGallery(param);
	}
	@Override
	public int writeGallery(GalleryDto dto) {
		return sqlSession.getMapper(GalleryMapper.class).writeGallery(dto);
	}
	@Override
	public int insertAttachGallery(List<Map<String, Object>> param) {
		return sqlSession.getMapper(GalleryMapper.class).insertAttachGallery(param);
	}
	@Override
	public GalleryDto getGalleryDetail(int gl_no) {
		return sqlSession.getMapper(GalleryMapper.class).getGalleryDetail(gl_no);
	}
	
	@Override
	public List<FileListDto> getFileGallery(int gl_no) {
		return sqlSession.getMapper(GalleryMapper.class).getFileGallery(gl_no);
	}
	@Override
	public int deleteGallery(int gl_no) {
		return sqlSession.getMapper(GalleryMapper.class).deleteGallery(gl_no);
	}
	@Override
	public int deleteGalleryAttach(int gl_no) {
		return sqlSession.getMapper(GalleryMapper.class).deleteGalleryAttach(gl_no);
	}
	@Override
	public int updateGallery(GalleryDto dto) {
		return sqlSession.getMapper(GalleryMapper.class).updateGallery(dto);
	}
	@Override
	public int getGalleryTotalCnt(SearchDto dto) {
		return sqlSession.getMapper(GalleryMapper.class).getGalleryTotalCnt(dto);
	}
}
