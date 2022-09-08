package com.spring.shop.notice;

public class NoticeJoinDto {
	private String board_type;
	private int board_id;
	private int file_num;
	private String file_name;
	private String saved_file_name;
	private int file_size;
	private String upload_dt;
	private String insert_dt;
	private String ni_writer;
	private int ni_no;
	private String ni_title;
	private String ni_content;
	
	public NoticeJoinDto() {
		// TODO Auto-generated constructor stub
	}
	
	public NoticeJoinDto(String board_type, int board_id, int file_num, String file_name, String saved_file_name,
			int file_size, String upload_dt, String insert_dt, String ni_writer, int ni_no, String ni_title,
			String ni_content) {
		super();
		this.board_type = board_type;
		this.board_id = board_id;
		this.file_num = file_num;
		this.file_name = file_name;
		this.saved_file_name = saved_file_name;
		this.file_size = file_size;
		this.upload_dt = upload_dt;
		this.insert_dt = insert_dt;
		this.ni_writer = ni_writer;
		this.ni_no = ni_no;
		this.ni_title = ni_title;
		this.ni_content = ni_content;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
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
	public String getUpload_dt() {
		return upload_dt;
	}
	public void setUpload_dt(String upload_dt) {
		this.upload_dt = upload_dt;
	}
	public String getInsert_dt() {
		return insert_dt;
	}
	public void setInsert_dt(String insert_dt) {
		this.insert_dt = insert_dt;
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
}
