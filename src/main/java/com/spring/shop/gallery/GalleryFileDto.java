package com.spring.shop.gallery;

public class GalleryFileDto {
	private int gl_no;
	private String gl_title;
	private String gl_content;
	private String gl_writer;
	private String gl_createdat;
	private String board_type;
	private int file_num;
	private String file_name;
	private String saved_file_name;
	private int file_size;
	
	public GalleryFileDto() {
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

	public String getBoard_type() {
		return board_type;
	}

	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}

	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getSaved_file_name() {
		return saved_file_name;
	}

	public void setSaved_file_name(String saved_file_name) {
		this.saved_file_name = saved_file_name;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	public GalleryFileDto(int gl_no, String gl_title, String gl_content, String gl_writer, String gl_createdat,
			String board_type, int file_num, String file_name, String saved_file_name, int file_size) {
		super();
		this.gl_no = gl_no;
		this.gl_title = gl_title;
		this.gl_content = gl_content;
		this.gl_writer = gl_writer;
		this.gl_createdat = gl_createdat;
		this.board_type = board_type;
		this.file_num = file_num;
		this.file_name = file_name;
		this.saved_file_name = saved_file_name;
		this.file_size = file_size;
	}
}
