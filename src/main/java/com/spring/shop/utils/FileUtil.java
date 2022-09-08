package com.spring.shop.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileUtil {
	//반환값은 리스트 맵타입이며 imageUpload메서드로 MultipartHttpServletRequest,id값을 받는다
	public List<Map<String, Object>> imageUpload(MultipartHttpServletRequest req, int id) throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		List<MultipartFile> fileList = mr.getFiles("file");	//file에 여러개의 값이 들어가 있다
		
		List<Map<String, Object>> paramList = new ArrayList<Map<String,Object>>();	//생성하고
		
		String path = mr.getSession().getServletContext().getRealPath("/resources/file");	//실제로 저장될 서버주소
		File saveDir = new File(path);	//서버 디렉토리
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		
		for (MultipartFile file : fileList) {	//multipartfile을 리스트로 가지고온다
			Map<String, Object> param = new HashMap<String, Object>();//값을 초기화 한다음 
			
			String originalName = file.getOriginalFilename();	//파일의 본래이름을 저장해둔다
			long fileSize = file.getSize();		//파일의 크기를 저장한다
			File destination = File.createTempFile("F_"+System.currentTimeMillis(), originalName.substring(originalName.lastIndexOf(".")), saveDir);	//저장된 실제 경로값의 이름을 저장
			String saved_file_name = destination.getName();		//경로값의 이름을 saved_file_name으로 저장한다
			
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));	//파일을 서버에 카피해서 저장한다.
			
			param.put("file_name", originalName);
			param.put("saved_file_name", saved_file_name);
			param.put("file_size", fileSize);
			param.put("user_id", id);
			
			paramList.add(param);
		}
		return paramList;//2차원 배열로 넘겨줌
	}
	
	public void deleteFile(HttpServletRequest req, String saved_file_name) {
		File deleteFile = new File(req.getSession().getServletContext().getRealPath("/resources/file/")+ saved_file_name);
		deleteFile.delete();
	}
}
