package com.spring.shop.notice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.spring.shop.utils.FileUtil;
import com.spring.shop.utils.PagingUtils;
import com.spring.shop.utils.SearchDto;

//서비스 에노테이션
@Service
public class NoticeServiceImpl implements NoticeService {
	
	//자동주입 에노테이션
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private PagingUtils pagingUtils;
	
	//오버라이딩 
	@Override
	public Map<String, Object> listNotice(SearchDto dto) {
		int totalCnt = noticeDAO.getNoticeTotalCnt(dto);//DB에 입력된총 갯수를 받아옴
		
		Map<String, Object> param = pagingUtils.paging(dto.getCurPage(), dto.getPagePerCnt(), totalCnt); //util에서 현재페이지 페이지당 숫자 마지막 숫자를 받아옴
		
		param.put("keyword", dto.getKeyword());		//검색할 키워드를 여기에서 추가시킴
		param.put("searchType", dto.getSearchType()); 	//어디검색할지 추가함
		
		List<NoticeDto> list = noticeDAO.listNotice(param); //파람으로 받아온 값을 받아옴
		
		param.put("list", list);
		
		return param;
	}
	
	@Override
	public NoticeDto getNoticeDetail(int ni_no) {
		NoticeDto noticeDto = noticeDAO.getNoticeDetail(ni_no);	//ni_no로 넘겨준 값을 noticeDto에다가 담는다
		
		List<FileListDto> fileList = noticeDAO.getFileList(ni_no); //fileListdto형식으로 fileList를 넣어준다  
		
		noticeDto.setFileList(fileList);	//nicedto에 filelist에 위에 받아온 파일들은 세팅
		
		return noticeDto; 
	}
	
	@Override
	public int writeNotice(NoticeDto dto, MultipartHttpServletRequest req, List<MultipartFile> files)throws Exception {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//db트랜잭션을 사용하는 메서드
		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		try {
			noticeDAO.writeNotice(dto);
			if (files.get(0).getSize() > 0) {			//입력할 파일이 있는지 확인한다
				paramList = fileUtil.imageUpload(req, dto.getNi_no());	//만약 파일이 있다면 fileutill클래스에서 imageUpload메서드를 찾고 맵리스트로 반환한다
				
				noticeDAO.insertAttachNotice(paramList);		//data에 입력한다
			}
			platformTransactionManager.commit(status);		//db트랜잭션 상태를 적용한다
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);		//위의 메소드가하나라도 실행이 안되면 db트랜잭션 상태를 뒤로돌린다
			for(Map<String,Object>param:paramList) {
				if (files.get(0).getSize() > 0) {			//입력한 파일이 있는지 확인한다
					fileUtil.deleteFile(req, param.get("saved_file_name").toString());	
				}
			}
			return 0;
		}
	}
	
	@Override
	public int updateNotice(NoticeDto dto, MultipartHttpServletRequest req, List<MultipartFile> files) throws Exception {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		Map<String,Object> param= new HashMap<String, Object>();		//맵으로 파람을 선언한다
		try {
			noticeDAO.updateNotice(dto);	
			if (files.get(0).getSize() > 0) {		//입력할 파일이 있는지 확인한다
				List<FileListDto> fileList = noticeDAO.getFileList(dto.getNi_no());		//fileList만 가지고온다
				if(!fileList.isEmpty()) {					//db에 파일이 있는지 확인
					for(FileListDto dto1:fileList) {		
						param.put("saved_file_name", dto1.getSaved_file_name());	//파람에다가 saved_file_name을 넣어주고
						fileUtil.deleteFile(req,dto1.getSaved_file_name());		//서버에서 하나씩 받아와 삭제한다
					}
					noticeDAO.deleteNoticeAttach(dto.getNi_no());	//boardType테이블에 기존 내용을 삭제한다
				}
				List<Map<String, Object>> paramList = fileUtil.imageUpload(req, dto.getNi_no());	//파람리스트에 해당하는 숫자에 파일을 넣는다
				
				noticeDAO.insertAttachNotice(paramList);		//데이터 테이블에 인서트문으로 입력한다.
			}
			platformTransactionManager.commit(status);		//db트랜잭션 상태를 적용한다

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);		//위의 메소드가하나라도 실행이 안되면 db트랜잭션 상태를 뒤로돌린다
			
			return 0;
		}
	}
	
	@Override
	public int deleteNotice(int seq, HttpServletRequest req) {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//db트랜잭션을 사용하는 메서드
		Map<String,Object> param = new HashMap<String, Object>();		// 맵을 선언한다
		try {
			List<FileListDto> fileList = noticeDAO.getFileList(seq);		//fileList내용을 가지고 온다
			for(FileListDto dto:fileList) {
				param.put("saved_file_name", dto.getSaved_file_name());		//파일을 파람에다가 넣고
				fileUtil.deleteFile(req,dto.getSaved_file_name());		//서버에 파일을 삭제한다
			}
			noticeDAO.deleteNotice(seq);						//notice_info db값을 삭제한다
			noticeDAO.deleteNoticeAttach(seq);			//board_attach db값을 삭제한다
			
			platformTransactionManager.commit(status);			//db트랜잭션 상태를 적용한다
			
			return 1;
		}catch(Exception e) {
			platformTransactionManager.rollback(status);			//위의 메소드가하나라도 실행이 안되면 db트랜잭션 상태를 뒤로돌린다
			
			return 0;
		}
	}
}
