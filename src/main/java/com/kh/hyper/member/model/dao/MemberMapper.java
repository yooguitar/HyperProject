package com.kh.hyper.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.hyper.member.model.vo.Member;

@Mapper		
public interface MemberMapper {

	/*
	 * mybatis의 @Mapper로 DAO인터페이스 구현
	 * 
	 * [ 표현법 ]
	 * 반환받을타입 메소드명(자료형 인자값);
	 * 
	 *  *메소드명을 SQL id(키)값과 맞춰줌
	 *  => mybatis가 알아서 같은걸 찾아서 sqlSession을 전달해서 
	 *     SQL문을 수행하고, 결과도 반환해주고, 커밋도 해줌
	 * 
	 */
	
	Member login(Member member);
	
	int join(Member member);
	
	int updateMember(Member member);
	
	int deleteMember(Member member);
	
	int test();

	int checkId(String userId);
	// mapper에서 int가 반환됨

}
