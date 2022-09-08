package com.spring.shop.gallery;

import java.util.List;

import com.spring.shop.notice.FileListDto;

public class GalleryDto {

	private int gl_no;
	private String gl_title;
	private String gl_content;
	private String gl_writer;
	private String gl_createdat;
	private List<FileListDto> fileList;
	
	public GalleryDto() {
		// TODO Auto-generated constructor stub
	}
	public GalleryDto(int gl_no, String gl_title, String gl_content, String gl_writer, String gl_createdat,
			List<FileListDto> fileList) {
		super();
		this.gl_no = gl_no;
		this.gl_title = gl_title;
		this.gl_content = gl_content;
		this.gl_writer = gl_writer;
		this.gl_createdat = gl_createdat;
		this.fileList = fileList;
	}
	public int getGl_no() {
		return gl_no;
	}
	public void setGl_no(int gl_no) {
		this.gl_no = gl_no;
	}
	public String getGl_title() {
		return gl_title;
	}
	public void setGl_title(String gl_title) {
		this.gl_title = gl_title;
	}
	public String getGl_content() {
		return gl_content;
	}
	public void setGl_content(String gl_content) {
		this.gl_content = gl_content;
	}
	public String getGl_writer() {
		return gl_writer;
	}
	public void setGl_writer(String gl_writer) {
		this.gl_writer = gl_writer;
	}
	public String getGl_createdat() {
		return gl_createdat;
	}
	public void setGl_createdat(String gl_createdat) {
		this.gl_createdat = gl_createdat;
	}
	public List<FileListDto> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileListDto> fileList) {
		this.fileList = fileList;
	}
}
