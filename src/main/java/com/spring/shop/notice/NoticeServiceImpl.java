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

//���� �������̼�
@Service
public class NoticeServiceImpl implements NoticeService {
	
	//�ڵ����� �������̼�
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private PagingUtils pagingUtils;
	
	//�������̵� 
	@Override
	public Map<String, Object> listNotice(SearchDto dto) {
		int totalCnt = noticeDAO.getNoticeTotalCnt(dto);//DB�� �Էµ��� ������ �޾ƿ�
		
		Map<String, Object> param = pagingUtils.paging(dto.getCurPage(), dto.getPagePerCnt(), totalCnt); //util���� ���������� �������� ���� ������ ���ڸ� �޾ƿ�
		
		param.put("keyword", dto.getKeyword());		//�˻��� Ű���带 ���⿡�� �߰���Ŵ
		param.put("searchType", dto.getSearchType()); 	//���˻����� �߰���
		
		List<NoticeDto> list = noticeDAO.listNotice(param); //�Ķ����� �޾ƿ� ���� �޾ƿ�
		
		param.put("list", list);
		
		return param;
	}
	
	@Override
	public NoticeDto getNoticeDetail(int ni_no) {
		NoticeDto noticeDto = noticeDAO.getNoticeDetail(ni_no);	//ni_no�� �Ѱ��� ���� noticeDto���ٰ� ��´�
		
		List<FileListDto> fileList = noticeDAO.getFileList(ni_no); //fileListdto�������� fileList�� �־��ش�  
		
		noticeDto.setFileList(fileList);	//nicedto�� filelist�� ���� �޾ƿ� ���ϵ��� ����
		
		return noticeDto; 
	}
	
	@Override
	public int writeNotice(NoticeDto dto, MultipartHttpServletRequest req, List<MultipartFile> files)throws Exception {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//dbƮ������� ����ϴ� �޼���
		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		try {
			noticeDAO.writeNotice(dto);
			if (files.get(0).getSize() > 0) {			//�Է��� ������ �ִ��� Ȯ���Ѵ�
				paramList = fileUtil.imageUpload(req, dto.getNi_no());	//���� ������ �ִٸ� fileutillŬ�������� imageUpload�޼��带 ã�� �ʸ���Ʈ�� ��ȯ�Ѵ�
				
				noticeDAO.insertAttachNotice(paramList);		//data�� �Է��Ѵ�
			}
			platformTransactionManager.commit(status);		//dbƮ����� ���¸� �����Ѵ�
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);		//���� �޼ҵ尡�ϳ��� ������ �ȵǸ� dbƮ����� ���¸� �ڷε�����
			for(Map<String,Object>param:paramList) {
				if (files.get(0).getSize() > 0) {			//�Է��� ������ �ִ��� Ȯ���Ѵ�
					fileUtil.deleteFile(req, param.get("saved_file_name").toString());	
				}
			}
			return 0;
		}
	}
	
	@Override
	public int updateNotice(NoticeDto dto, MultipartHttpServletRequest req, List<MultipartFile> files) throws Exception {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		Map<String,Object> param= new HashMap<String, Object>();		//������ �Ķ��� �����Ѵ�
		try {
			noticeDAO.updateNotice(dto);	
			if (files.get(0).getSize() > 0) {		//�Է��� ������ �ִ��� Ȯ���Ѵ�
				List<FileListDto> fileList = noticeDAO.getFileList(dto.getNi_no());		//fileList�� ������´�
				if(!fileList.isEmpty()) {					//db�� ������ �ִ��� Ȯ��
					for(FileListDto dto1:fileList) {		
						param.put("saved_file_name", dto1.getSaved_file_name());	//�Ķ����ٰ� saved_file_name�� �־��ְ�
						fileUtil.deleteFile(req,dto1.getSaved_file_name());		//�������� �ϳ��� �޾ƿ� �����Ѵ�
					}
					noticeDAO.deleteNoticeAttach(dto.getNi_no());	//boardType���̺� ���� ������ �����Ѵ�
				}
				List<Map<String, Object>> paramList = fileUtil.imageUpload(req, dto.getNi_no());	//�Ķ�����Ʈ�� �ش��ϴ� ���ڿ� ������ �ִ´�
				
				noticeDAO.insertAttachNotice(paramList);		//������ ���̺� �μ�Ʈ������ �Է��Ѵ�.
			}
			platformTransactionManager.commit(status);		//dbƮ����� ���¸� �����Ѵ�

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			platformTransactionManager.rollback(status);		//���� �޼ҵ尡�ϳ��� ������ �ȵǸ� dbƮ����� ���¸� �ڷε�����
			
			return 0;
		}
	}
	
	@Override
	public int deleteNotice(int seq, HttpServletRequest req) {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());	//dbƮ������� ����ϴ� �޼���
		Map<String,Object> param = new HashMap<String, Object>();		// ���� �����Ѵ�
		try {
			List<FileListDto> fileList = noticeDAO.getFileList(seq);		//fileList������ ������ �´�
			for(FileListDto dto:fileList) {
				param.put("saved_file_name", dto.getSaved_file_name());		//������ �Ķ����ٰ� �ְ�
				fileUtil.deleteFile(req,dto.getSaved_file_name());		//������ ������ �����Ѵ�
			}
			noticeDAO.deleteNotice(seq);						//notice_info db���� �����Ѵ�
			noticeDAO.deleteNoticeAttach(seq);			//board_attach db���� �����Ѵ�
			
			platformTransactionManager.commit(status);			//dbƮ����� ���¸� �����Ѵ�
			
			return 1;
		}catch(Exception e) {
			platformTransactionManager.rollback(status);			//���� �޼ҵ尡�ϳ��� ������ �ȵǸ� dbƮ����� ���¸� �ڷε�����
			
			return 0;
		}
	}
}
