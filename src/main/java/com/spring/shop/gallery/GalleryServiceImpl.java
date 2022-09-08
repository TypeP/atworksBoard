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
	public Map<String,Object> listGallery(SearchDto dto) {	//����Ʈ�� ���������� ����
		int totalCnt = galleryDAO.getGalleryTotalCnt(dto);
		Map<String, Object> param = pagingUtils.paging(dto.getCurPage(), dto.getPagePerCnt(), totalCnt); //util���� ���������� �������� ���� ������ ���ڸ� �޾ƿ�
		
		param.put("keyword", dto.getKeyword());		//�˻��� Ű���带 ���⿡�� �߰���Ŵ
		param.put("searchType", dto.getSearchType()); 	//���˻����� �߰���
		List<GalleryFileDto> list = galleryDAO.listGallery(param);	//����Ʈ �������� �ڷḦ ���� �ְ�
		
		param.put("list", list);
		
		return param;		//�������� �ص� ������ ��ȯ
	}
	
	@Override
	public int writeGallery(GalleryDto dto, MultipartHttpServletRequest req, List<MultipartFile>files)throws Exception {	//�������� ���ۼ��ϴ� �޼���
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//dbƮ������� ����ϴ� �޼���	
		List<Map<String, Object>> paramList = new ArrayList<Map<String,Object>>();		//�Ķ�����Ʈ ����
		try {
			galleryDAO.writeGallery(dto);		//������ ���̺� �Է��Ѵ�
			if(files.get(0).getSize()>0) {		//������ ������ ����
				paramList = fileUtil.imageUpload(req, dto.getGl_no());	//��ƿ�� ���� ������ �̹��� ���ε� �ϰ� �̹��� ���ε��� ������ ����Ʈ�� ��´�
				
				galleryDAO.insertAttachGallery(paramList);		//����Ʈ�� ���� ������ DB�� �Է��Ѵ�
			}
			platformTransactionManager.commit(status);		//DBƮ������� ����
			
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);		//DB�� Ʈ������� �ѹ��Ѵ�.
			for(Map<String,Object>param:paramList) {		//����Ʈ�� Ȯ���Ѵ�
				if (files.get(0).getSize() > 0) {			//�������� ������ �ִ��� Ȯ���Ѵ�
					fileUtil.deleteFile(req, param.get("saved_file_name").toString());		//��ƿ���� ������ �ִ� ������ �����	
				}
			}
			return 0;
		}
	}
	
	@Override
	public GalleryDto getGalleryDetail(int gl_no) {		
		GalleryDto galleryDto = galleryDAO.getGalleryDetail(gl_no);	//list�� �ִ� gl_no�� �˻��ϰ� �� ������ �󼼳����� dto�� ��´�
		List<FileListDto> fileList = galleryDAO.getFileGallery(gl_no);	//gl_no�� �˻��ϰ� fileList�� �־��ش�
		
		galleryDto.setFileList(fileList);	//gallerydto�� fileList�� �޾ƿ� ������ ��Ƽ���� ����
		
		return galleryDto;
	}
	
	@Override
	public int deleteGallery(int gl_no, HttpServletRequest req) {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//dbƮ������� ����ϴ� �޼���
		Map<String,Object> param = new HashMap<String,Object>();		//���� �����Ѵ�
		try {
			List<FileListDto> fileList = galleryDAO.getFileGallery(gl_no);		//gl_no�� �˻��ϰ� fileList�� ���� �ִ´�
			for(FileListDto dto:fileList) {
				param.put("saved_file_name", dto.getSaved_file_name());		//������ �Ķ��� �ְ�
				fileUtil.deleteFile(req, dto.getSaved_file_name());		//�������� ������ �����Ѵ�
			}
			galleryDAO.deleteGallery(gl_no);		//galler_info db���� �����Ѵ�.
			galleryDAO.deleteGalleryAttach(gl_no);		//board_attach db ���� �����Ѵ�
			platformTransactionManager.commit(status);		//dbƮ����� ���¸� �����Ѵ�
			
			return 1;
		}catch(Exception e) {
			platformTransactionManager.rollback(status);		//dbƮ����� �ѹ�
			
			return 0;
		}
	}
	@Override
	public int updateGallery(GalleryDto dto, MultipartHttpServletRequest req, List<MultipartFile> files) throws Exception {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//dbƮ������� ����ϴ� �޼���
		Map<String,Object> param = new HashMap<String, Object>();		//������ �Ķ��� �����Ѵ�
		try {
			galleryDAO.updateGallery(dto);		//db�� �������������� ������ �����Ѵ�
			if(files.get(0).getSize()>0) {		//�Է��� ������ �ִ��� Ȯ���Ѵ�
				List<FileListDto> fileList = galleryDAO.getFileGallery(dto.getGl_no());		//fileList�� ������ �´�
				if(!fileList.isEmpty()) {			//db�� ������ �ִ��� Ȯ��
					for(FileListDto dto1:fileList) {
						param.put("saved_file_name", dto1.getSaved_file_name());		//�Ķ����ٰ� save_file_name�� �־��ְ�
						fileUtil.deleteFile(req, dto1.getSaved_file_name());		//�������� �ϳ��� ���� �̸��� �޾ƿ� �����Ѵ�
					}
					galleryDAO.deleteGalleryAttach(dto.getGl_no());		//boardType���̺� ���� ������ �����Ѵ�.
				}
				List<Map<String, Object>> paramList = fileUtil.imageUpload(req, dto.getGl_no());	//�Ķ�����Ʈ�� �ش��ϴ� ���ڿ� ������ �ִ´�
				
				galleryDAO.insertAttachGallery(paramList);		//boardType���̺� �޾ƿ� ������ �Է��Ѵ�
			}
			platformTransactionManager.commit(status);		//dbƮ����� ���¸� �����Ѵ�.
			
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);		//dbƮ������� �ѹ��Ѵ�.
			
			return 0;
		}
	}
}


/*
 * for(GalleryDto dto:list) { //����Ʈ�� ������ for������ �ѹ��徿 ������´� List<FileListDto>
 * fileList = galleryDAO.getFileGallery(dto.getGl_no()); //list�� �ִ� seq�� �˻��ϰ� ��
 * ������ list�� ��´�
 * 
 * dto.setFileList(fileList); //dto�� ���������� param.put("list", list); //����Ʈ�� ����
 * �����Ѵ� }
 */