package com.spring.shop.login;

import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.spring.shop.notice.NoticeDto;

public interface MemberDAO {
	int register(MemberDto dto);
	UserBoardDto login(UserBoardDto dto);
	int update(UserBoardDto dto);
	int insertAttach(Map<String, Object> param);
	int updateAttach(Map<String, Object> param);
	int delete(UserBoardDto dto);
	int deleteAttach(UserBoardDto dto);
}
