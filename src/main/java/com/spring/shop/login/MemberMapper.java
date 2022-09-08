package com.spring.shop.login;

import java.util.Map;

import com.spring.shop.notice.NoticeDto;

public interface MemberMapper {
	int register(MemberDto dto);
	UserBoardDto login(UserBoardDto dto);
	int update(UserBoardDto dto);
	int insertAttach(Map<String, Object> param);
	int updateAttach(Map<String, Object> param);
	int delete(UserBoardDto dto);
	int deleteAttach(UserBoardDto dto);
}
