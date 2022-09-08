package com.spring.shop.login;

public class UserBoardDto {
	private String board_type;
	private int board_id;
	private int file_num;
	private String file_name;
	private String saved_file_name;
	private int file_size;
	private String upload_dt;
	private String insert_dt;
	private String mi_id;
	private String mi_pw;
	private String mi_name;
	private String mi_nickname;
	private String mi_email;
	private String mi_mobile;
	private int mi_postcode;
	private String mi_roadaddress;
	private String mi_detailaddress;
	private String mi_createdat;
	private int mi_seq;
	
	public UserBoardDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UserBoardDto(String board_type, int board_id, int file_num, String file_name, String saved_file_name,
			int file_size, String upload_dt, String insert_dt, String mi_id, String mi_pw, String mi_name,
			String mi_nickname, String mi_email, String mi_mobile, int mi_postcode, String mi_roadaddress,
			String mi_detailaddress, String mi_createdat, int mi_seq) {
		super();
		this.board_type = board_type;
		this.board_id = board_id;
		this.file_num = file_num;
		this.file_name = file_name;
		this.saved_file_name = saved_file_name;
		this.file_size = file_size;
		this.upload_dt = upload_dt;
		this.insert_dt = insert_dt;
		this.mi_id = mi_id;
		this.mi_pw = mi_pw;
		this.mi_name = mi_name;
		this.mi_nickname = mi_nickname;
		this.mi_email = mi_email;
		this.mi_mobile = mi_mobile;
		this.mi_postcode = mi_postcode;
		this.mi_roadaddress = mi_roadaddress;
		this.mi_detailaddress = mi_detailaddress;
		this.mi_createdat = mi_createdat;
		this.mi_seq = mi_seq;
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
	public String getMi_id() {
		return mi_id;
	}
	public void setMi_id(String mi_id) {
		this.mi_id = mi_id;
	}
	public String getMi_pw() {
		return mi_pw;
	}
	public void setMi_pw(String mi_pw) {
		this.mi_pw = mi_pw;
	}
	public String getMi_name() {
		return mi_name;
	}
	public void setMi_name(String mi_name) {
		this.mi_name = mi_name;
	}
	public String getMi_nickname() {
		return mi_nickname;
	}
	public void setMi_nickname(String mi_nickname) {
		this.mi_nickname = mi_nickname;
	}
	public String getMi_email() {
		return mi_email;
	}
	public void setMi_email(String mi_email) {
		this.mi_email = mi_email;
	}
	public String getMi_mobile() {
		return mi_mobile;
	}
	public void setMi_mobile(String mi_mobile) {
		this.mi_mobile = mi_mobile;
	}
	public int getMi_postcode() {
		return mi_postcode;
	}
	public void setMi_postcode(int mi_postcode) {
		this.mi_postcode = mi_postcode;
	}
	public String getMi_roadaddress() {
		return mi_roadaddress;
	}
	public void setMi_roadaddress(String mi_roadaddress) {
		this.mi_roadaddress = mi_roadaddress;
	}
	public String getMi_detailaddress() {
		return mi_detailaddress;
	}
	public void setMi_detailaddress(String mi_detailaddress) {
		this.mi_detailaddress = mi_detailaddress;
	}
	public String getMi_createdat() {
		return mi_createdat;
	}
	public void setMi_createdat(String mi_createdat) {
		this.mi_createdat = mi_createdat;
	}
	public int getMi_seq() {
		return mi_seq;
	}
	public void setMi_seq(int mi_seq) {
		this.mi_seq = mi_seq;
	}
}
