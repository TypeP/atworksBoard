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
	//��ȯ���� ����Ʈ ��Ÿ���̸� imageUpload�޼���� MultipartHttpServletRequest,id���� �޴´�
	public List<Map<String, Object>> imageUpload(MultipartHttpServletRequest req, int id) throws Exception {
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		List<MultipartFile> fileList = mr.getFiles("file");	//file�� �������� ���� �� �ִ�
		
		List<Map<String, Object>> paramList = new ArrayList<Map<String,Object>>();	//�����ϰ�
		
		String path = mr.getSession().getServletContext().getRealPath("/resources/file");	//������ ����� �����ּ�
		File saveDir = new File(path);	//���� ���丮
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		
		for (MultipartFile file : fileList) {	//multipartfile�� ����Ʈ�� ������´�
			Map<String, Object> param = new HashMap<String, Object>();//���� �ʱ�ȭ �Ѵ��� 
			
			String originalName = file.getOriginalFilename();	//������ �����̸��� �����صд�
			long fileSize = file.getSize();		//������ ũ�⸦ �����Ѵ�
			File destination = File.createTempFile("F_"+System.currentTimeMillis(), originalName.substring(originalName.lastIndexOf(".")), saveDir);	//����� ���� ��ΰ��� �̸��� ����
			String saved_file_name = destination.getName();		//��ΰ��� �̸��� saved_file_name���� �����Ѵ�
			
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));	//������ ������ ī���ؼ� �����Ѵ�.
			
			param.put("file_name", originalName);
			param.put("saved_file_name", saved_file_name);
			param.put("file_size", fileSize);
			param.put("user_id", id);
			
			paramList.add(param);
		}
		return paramList;//2���� �迭�� �Ѱ���
	}
	
	public void deleteFile(HttpServletRequest req, String saved_file_name) {
		File deleteFile = new File(req.getSession().getServletContext().getRealPath("/resources/file/")+ saved_file_name);
		deleteFile.delete();
	}
}
