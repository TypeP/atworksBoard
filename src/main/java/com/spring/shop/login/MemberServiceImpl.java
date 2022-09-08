package com.spring.shop.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.shop.utils.FileUtil;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager; 
	
	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public int register(MemberDto dto, MultipartHttpServletRequest req, MultipartFile file) throws Exception {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		List<Map<String, Object>> paramList = new ArrayList<Map<String,Object>>();
		try {
			memberDAO.register(dto);
			if(!file.isEmpty()) {
				paramList = fileUtil.imageUpload(req, dto.getMi_seq()); 
				for(Map<String, Object> param:paramList) {
					memberDAO.insertAttach(param);
				}
			}
			platformTransactionManager.commit(status);
			
			return 1;
		}catch (Exception e) {
			platformTransactionManager.rollback(status);
			for(Map<String, Object> param:paramList) {
				if(param != null) {
					fileUtil.deleteFile(req, param.get("saved_file_name").toString());
				}
			}
			
			return 0;
		}
	}
	@Override
	public UserBoardDto login(UserBoardDto dto) {
		return memberDAO.login(dto);
	}
	
	
	@Override
	public int update(UserBoardDto dto, MultipartHttpServletRequest req,MultipartFile file){ 
		TransactionStatus status =  platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		List<Map<String, Object>> paramList = new ArrayList<Map<String,Object>>();
		try {
			memberDAO.update(dto); 
				if(!file.isEmpty()) { 
					if(!dto.getSaved_file_name().isEmpty()) {
						fileUtil.deleteFile(req, dto.getSaved_file_name());
					}
						paramList=fileUtil.imageUpload(req, dto.getMi_seq());
					for(Map<String, Object> param:paramList) {
						memberDAO.updateAttach(param); 
					}
				}  
			platformTransactionManager.commit(status);
	  
			return 1; 
	  }catch(Exception e) {
		  e.printStackTrace();
		  platformTransactionManager.rollback(status);
		  for(Map<String, Object> param:paramList) {
			  if (param != null) {
				  fileUtil.deleteFile(req, param.get("saved_file_name").toString());
			  }
		  }
		  
		  return 0;
	  }
	}
	
	@Override
	public int delete(UserBoardDto dto, HttpServletRequest req) {
		TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
		Map<String,Object> param= new HashMap<String, Object>();
		try {
			memberDAO.delete(dto);
			memberDAO.deleteAttach(dto);
			
			param.put("saved_file_name",dto.getSaved_file_name());
			
			fileUtil.deleteFile(req, dto.getSaved_file_name());
			
			platformTransactionManager.commit(status);
			
			return 1;
	}catch(Exception e) {
			platformTransactionManager.rollback(status);
			
			return 0;
		}	
	}
}
