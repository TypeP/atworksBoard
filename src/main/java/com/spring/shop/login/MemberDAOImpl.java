package com.spring.shop.login;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.shop.notice.NoticeDto;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int register(MemberDto dto) {
		return sqlSession.getMapper(MemberMapper.class).register(dto);
	}
	@Override
	public UserBoardDto login(UserBoardDto dto) {
		return sqlSession.getMapper(MemberMapper.class).login(dto);
	}
	@Override
	public int update(UserBoardDto dto) {
		return sqlSession.getMapper(MemberMapper.class).update(dto);
	}
	@Override
	public int insertAttach(Map<String, Object> param) {
		return sqlSession.getMapper(MemberMapper.class).insertAttach(param);
	}
	@Override
	public int updateAttach(Map<String, Object> param) {
			return sqlSession.getMapper(MemberMapper.class).updateAttach(param);
	}
	@Override
	public int delete(UserBoardDto dto) {
		return sqlSession.getMapper(MemberMapper.class).delete(dto);
	}
	@Override
	public int deleteAttach(UserBoardDto dto) {
		return sqlSession.getMapper(MemberMapper.class).deleteAttach(dto);
	}
	
}
