package com.spring.shop.notice;

import java.util.List;

public class NoticeDto {
	private String ni_writer;
	private int ni_no;
	private String ni_title;
	private String ni_content;
	private List<FileListDto> fileList;
	
	public NoticeDto() {
		// TODO Auto-generated constructor stub
	}

	public NoticeDto(String ni_writer, int ni_no, String ni_title, String ni_content, List<FileListDto> fileList) {
		super();
		this.ni_writer = ni_writer;
		this.ni_no = ni_no;
		this.ni_title = ni_title;
		this.ni_content = ni_content;
		this.fileList = fileList;
	}

	public String getNi_writer() {
		return ni_writer;
	}

	public void setNi_writer(String ni_writer) {
		this.ni_writer = ni_writer;
	}

	public int getNi_no() {
		return ni_no;
	}

	public void setNi_no(int ni_no) {
		this.ni_no = ni_no;
	}

	public String getNi_title() {
		return ni_title;
	}

	public void setNi_title(String ni_title) {
		this.ni_title = ni_title;
	}

	public String getNi_content() {
		return ni_content;
	}

	public void setNi_content(String ni_content) {
		this.ni_content = ni_content;
	}

	public List<FileListDto> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileListDto> fileList) {
		this.fileList = fileList;
	}	
}
