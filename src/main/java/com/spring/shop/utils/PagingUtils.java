package com.spring.shop.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PagingUtils {
	public Map<String, Object> paging(int curPage, int pagePerCnt, int totalCnt) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		int offset = (curPage - 1) * pagePerCnt;	//현재페이지-1 * 몇페이지 출력하는지 3페이지면 2페이지 출력 
		int limit = ((curPage - 1) * pagePerCnt) + pagePerCnt;	//현재페이지-1 *몇페이지출력하는지 +갯수 
		
		param.put("limit", limit);
		param.put("offset", offset);
		param.put("totalCnt", totalCnt);
		param.put("curPage", curPage);
		param.put("pagePerCnt", pagePerCnt);
		
		return param;
	}
}
