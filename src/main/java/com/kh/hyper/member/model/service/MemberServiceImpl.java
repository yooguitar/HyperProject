package com.kh.hyper.member.model.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.kh.hyper.exception.ComparePasswordException;
import com.kh.hyper.exception.UserIdNotFoundException;
import com.kh.hyper.member.model.dao.MemberMapper;
import com.kh.hyper.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

// @Component == Bean으로 등록하겠다.
@EnableTransactionManagement
@RequiredArgsConstructor
@Service 	// Component보다 더 구체적으로 ServiceBean으로 등록
public class MemberServiceImpl implements MemberService {
	// private final MemberDao memberDao;
	// private final SqlSessionTemplate sqlSession; // 기존의 마이바티스 sqlSession대체
	private final MemberMapper mapper;
	private final PasswordEncryptor passwordEncoder;	// 인코더를 쓰다가 중간에 인크립터 클래스로 타입만 변경함. 변수명이 그대로인것 => 책임을 나눴다
	private final MemberValidator validator;	
	
	/*
	@Autowired
	public MemberServiceImpl(MemberDao memberDao, SqlSessionTemplate sqlSession) {
		this.memberDao = memberDao;
		this.sqlSession = sqlSession;
	}
	*/
	
	@Override
	public Member login(Member member) {
		/*
		 * SqlSession sqlSession = getSqlSessioin();
		 * 
		 * Member member = new MemberDao().login(sqlSession, member);
		 * 
		 * sqlSession.close();
		 * 
		 * return member;
		 */
		// SqlSession은 Bean에 등록 해뒀음
		// Member loginUser = memberDao.login(sqlSession, member);
		// 스프링이 사용 후 자동으로 객체를 반납해주기 때문에 close() 호출하지 않음!
		
		//return memberDao.login(sqlSession, member);
		
		
		
		// 1. 아이디가 존재하지 않는 경우
		// 2. 비밀번호가 잘못된 경우
		// 3. 둘 다 통과해서 정상적으로 조회
		
		Member loginMember =  mapper.login(member);
		
		// 1. 아이디가 존재하지 않을 경우
		// **ExceptionHandler는 예외 클래스의 타입만 본다 
		// 		각각의 예외가 발생했을 때 사용자에게 알려주고 싶은 핸들링 방법이 다르다
		//		=> 핸들러를 세분화 해서 만든다(예외클래스 만들기)
		if(loginMember == null) {
			throw new UserIdNotFoundException("존재하지 않는 아이디로 접속 요청");
		}
		
		// 2. 비밀번호가 일치하지 않을 경우
		if(!(passwordEncoder.matches(member.getUserPwd(), loginMember.getUserPwd()))) {
			throw new ComparePasswordException("비밀번호가 일치하지 않습니다.");
		} else {
			return loginMember;
			// 두가지 조건검사를 모두 완료 했다면 return;
			// 실패 했다면 중간에 해당 처리페이지로 포워딩됨 
		}
	
	}

	@Override
	@Transactional
	public void join(Member member) {
		// 커넥션 만들기
		// DAO호출
		// 트랜잭션 처리
		// 자원반납
		// 결과반환
		// int result = memberDao.join(sqlSession, member);
		// sqlSessionTemplate이 트랜잭션 자동커밋 해줌, 자원반납도 해줌
		// return memberDao.join(sqlSession, member);
		// 예외처리
		/*
		try {
			mapper.test();
			mapper.join(member);
			return 1;
		} catch(DuplicateKeyException e) {
			return 0;
		}
		test()는 pk중복으로 예외를 발생시킨다
		pk제약조건 위반으로 예외가 발생할 경우 회원가입 실패 페이지로 이동
		*/
		
		
		// case 1. 사용자가 입력한 아이디 값이 userId컬럼에 존재하면 안됨
		// case 2. 사용자가 입력한 아이디 값이 30글자가 넘어가면 안됨
		// case 3. 위 조건들을 만족했다면 사용자가 입력한 비밀번호 값을 암호화 해서 DB에 INSERT
		Member userInfo = mapper.login(member);
		/*
		validator.validateDuplicateMember(member);
		validator.validateIdLength(member);
		
		if(userInfo != null && member.getUserId().equals(userInfo.getUserId())) {
			throw new UserFoundException("이미 존재하는 아이디입니다.");
		}
		if(member.getUserId().length() > 30) {
			throw new TooLargeValueException("값의 길이가 너무 깁니다.");
		}
		*/
		validator.validateJoinMember(member);
		member.setUserPwd(passwordEncoder.encode(member.getUserPwd()));
		mapper.join(member);
		
	}

	
	@Override
	public void updateMember(Member member, HttpSession session) {
		
		// 세션도 같이 받아서 앞단에서 넘어온 userId와 loginUser키 값의 userId 필드 값이 동일한지 확인
		// => 사용자가 입력한 userId값이 DB컬럼에 존재 하는지(일단 이거만 수행)
		// 사용자가 입력한 업데이트 값들이 컬럼의 크기가 넘치게 하지 않는지 || 제약조건에 부합하는지
		/*
		Member userInfo = mapper.login(member);
		if(userInfo == null) {
			throw new UserIdNotFoundException("존재하지 않는 사용자 입니다.");
		}
		*/
		validator.validateMemberExists(member);
		mapper.updateMember(member);
		session.setAttribute("loginUser", mapper.login(member));
		// 요청 처리 성공하면 Controller 호출
		// 실패하면 예외
		// return은 필요 없다. 반환타입 void
	}

	
	@Override
	public void deleteMember(String userPwd, HttpSession session) {
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		loginUser.setUserPwd(userPwd);
		Member userInfo = mapper.login(loginUser);
		
		if(userInfo == null) {
			throw new UserIdNotFoundException("존재하지 않는 사용자입니다.");
		}
		if(!(passwordEncoder.matches(loginUser.getUserPwd(), userInfo.getUserPwd()))) {
			throw new ComparePasswordException("비밀번호가 일치하지 않습니다.");
		}
		
		mapper.deleteMember(userInfo);
		
	}
	
	
	@Override
	public String checkId(String userId) {
		
		return mapper.checkId(userId) > 0 ? "NNNNN" : "NNNNY";
		
	}
	
	
	
	
	
	
	
}
