package com.spring.shop.utils;

public class SearchDto {
	private int curPage;
	private int pagePerCnt;
	private String keyword;
	private String searchType;
	
	public SearchDto() {
		// TODO Auto-generated constructor stub
	}

	public SearchDto(int curPage, int pagePerCnt, String keyword, String searchType) {
		super();
		this.curPage = curPage;
		this.pagePerCnt = pagePerCnt;
		this.keyword = keyword;
		this.searchType = searchType;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPagePerCnt() {
		return pagePerCnt;
	}

	public void setPagePerCnt(int pagePerCnt) {
		this.pagePerCnt = pagePerCnt;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
}
