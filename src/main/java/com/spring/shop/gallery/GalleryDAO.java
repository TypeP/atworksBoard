package com.spring.shop.gallery;
import java.util.List;
import java.util.Map;

import com.spring.shop.notice.FileListDto;
import com.spring.shop.utils.SearchDto;

public interface GalleryDAO {
	List<GalleryFileDto> listGallery(Map<String, Object> param);
	int writeGallery(GalleryDto dto);
	int insertAttachGallery(List<Map<String,Object>> paramList);
	GalleryDto getGalleryDetail(int gl_no);
	List<FileListDto>getFileGallery(int gl_no);
	int deleteGallery(int gl_no);
	int deleteGalleryAttach(int gl_no);
	int updateGallery(GalleryDto dto);
	int getGalleryTotalCnt(SearchDto dto);
}
