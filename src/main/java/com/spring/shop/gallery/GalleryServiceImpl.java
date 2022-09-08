package com.spring.shop.gallery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.notice.FileListDto;
import com.spring.shop.utils.FileUtil;
import com.spring.shop.utils.PagingUtils;
import com.spring.shop.utils.SearchDto;

@Service
public class GalleryServiceImpl implements GalleryService{
	
	@Autowired
	private GalleryDAO galleryDAO;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager; 
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private PagingUtils pagingUtils;
	
	@Override
	public Map<String,Object> listGallery(SearchDto dto) {	//리스트를 맵형식으로 전달
		int totalCnt = galleryDAO.getGalleryTotalCnt(dto);
		Map<String, Object> param = pagingUtils.paging(dto.getCurPage(), dto.getPagePerCnt(), totalCnt); //util에서 현재페이지 페이지당 숫자 마지막 숫자를 받아옴
		
		param.put("keyword", dto.getKeyword());		//검색할 키워드를 여기에서 추가시킴
		param.put("searchType", dto.getSearchType()); 	//어디검색할지 추가함
		List<GalleryFileDto> list = galleryDAO.listGallery(param);	//리스트 형식으로 자료를 전부 넣고
		
		param.put("list", list);
		
		return param;		//리턴으로 해동 내용을 반환
	}
	
	@Override
	public int writeGallery(GalleryDto dto, MultipartHttpServletRequest req, List<MultipartFile>files)throws Exception {	//갤러리에 글작성하는 메서드
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//db트랜잭션을 사용하는 메서드	
		List<Map<String, Object>> paramList = new ArrayList<Map<String,Object>>();		//파람리스트 선언
		try {
			galleryDAO.writeGallery(dto);		//갤러리 테이블에 입력한다
			if(files.get(0).getSize()>0) {		//파일이 있으면 실행
				paramList = fileUtil.imageUpload(req, dto.getGl_no());	//유틸을 통해 서버에 이미지 업로드 하고 이미지 업로드의 내용을 리스트에 담는다
				
				galleryDAO.insertAttachGallery(paramList);		//리스트에 담은 내용을 DB에 입력한다
			}
			platformTransactionManager.commit(status);		//DB트랜잭션을 적용
			
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);		//DB의 트랜잭션을 롤백한다.
			for(Map<String,Object>param:paramList) {		//리스트를 확인한다
				if (files.get(0).getSize() > 0) {			//지워야할 파일이 있는지 확인한다
					fileUtil.deleteFile(req, param.get("saved_file_name").toString());		//유틸에서 서버에 있는 파일을 지운다	
				}
			}
			return 0;
		}
	}
	
	@Override
	public GalleryDto getGalleryDetail(int gl_no) {		
		GalleryDto galleryDto = galleryDAO.getGalleryDetail(gl_no);	//list에 있는 gl_no로 검색하고 그 내용을 상세내역을 dto에 담는다
		List<FileListDto> fileList = galleryDAO.getFileGallery(gl_no);	//gl_no로 검색하고 fileList에 넣어준다
		
		galleryDto.setFileList(fileList);	//gallerydto에 fileList에 받아온 내용을 디티오에 세팅
		
		return galleryDto;
	}
	
	@Override
	public int deleteGallery(int gl_no, HttpServletRequest req) {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//db트랜잭션을 사용하는 메서드
		Map<String,Object> param = new HashMap<String,Object>();		//맵을 선언한다
		try {
			List<FileListDto> fileList = galleryDAO.getFileGallery(gl_no);		//gl_no로 검색하고 fileList에 값을 넣는다
			for(FileListDto dto:fileList) {
				param.put("saved_file_name", dto.getSaved_file_name());		//파일을 파람에 넣고
				fileUtil.deleteFile(req, dto.getSaved_file_name());		//서버에서 파일을 삭제한다
			}
			galleryDAO.deleteGallery(gl_no);		//galler_info db값을 삭제한다.
			galleryDAO.deleteGalleryAttach(gl_no);		//board_attach db 값을 삭제한다
			platformTransactionManager.commit(status);		//db트랜잭션 상태를 적용한다
			
			return 1;
		}catch(Exception e) {
			platformTransactionManager.rollback(status);		//db트랜잭션 롤백
			
			return 0;
		}
	}
	@Override
	public int updateGallery(GalleryDto dto, MultipartHttpServletRequest req, List<MultipartFile> files) throws Exception {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//db트랜잭션을 사용하는 메서드
		Map<String,Object> param = new HashMap<String, Object>();		//맵으로 파람을 선언한다
		try {
			galleryDAO.updateGallery(dto);		//db에 갤러리페이지에 내용을 수정한다
			if(files.get(0).getSize()>0) {		//입력할 파일이 있는지 확인한다
				List<FileListDto> fileList = galleryDAO.getFileGallery(dto.getGl_no());		//fileList만 가지고 온다
				if(!fileList.isEmpty()) {			//db에 파일이 있는지 확인
					for(FileListDto dto1:fileList) {
						param.put("saved_file_name", dto1.getSaved_file_name());		//파람에다가 save_file_name을 넣어주고
						fileUtil.deleteFile(req, dto1.getSaved_file_name());		//서버에서 하나씩 같은 이름을 받아와 삭제한다
					}
					galleryDAO.deleteGalleryAttach(dto.getGl_no());		//boardType테이블에 기존 내용을 삭제한다.
				}
				List<Map<String, Object>> paramList = fileUtil.imageUpload(req, dto.getGl_no());	//파람리스트에 해당하는 숫자에 파일을 넣는다
				
				galleryDAO.insertAttachGallery(paramList);		//boardType테이블에 받아온 사진을 입력한다
			}
			platformTransactionManager.commit(status);		//db트랜잭션 상태를 적용한다.
			
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);		//db트랜잭션을 롤백한다.
			
			return 0;
		}
	}
}


/*
 * for(GalleryDto dto:list) { //리스트의 내용을 for문으로 한문장씩 가지고온다 List<FileListDto>
 * fileList = galleryDAO.getFileGallery(dto.getGl_no()); //list에 있는 seq로 검색하고 그
 * 내용을 list에 담는다
 * 
 * dto.setFileList(fileList); //dto에 값을세팅한 param.put("list", list); //리스트에 값을
 * 전달한다 }
 */