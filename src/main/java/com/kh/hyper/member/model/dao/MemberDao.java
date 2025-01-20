package com.kh.hyper.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.hyper.member.model.vo.Member;

@Repository // 저장소 관련된 작업(영속성작업)을 처리하겠다는 뜻
public class MemberDao {

	public Member login(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.selectOne("memberMapper.login", member);
	}
	
	public int join(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.insert("memberMapper.join", member);
	}
	
	
	// MemberMapper로 대체
	// => DAO에 인터페이스를 사용하는 방법
	
	
	
	
	
}
