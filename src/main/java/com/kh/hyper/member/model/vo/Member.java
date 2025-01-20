package com.kh.hyper.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Lombok(롬복)
 * 
 * - 자동 코드 생성 라이브러리
 * 
 * Lombok 설치 방법
 * 1. 라이브러리를 다운로드
 * 2. 다운로드 된 .jar파일을 찾아서 설치(작업할 IDE를 체크)
 * 3. IDE 재실행
 * 
 * @Getter
 * @Setter
 * @ToString
 * @NoArgsConstructor
 * ------------------------
 * @Builder
 * @AllArgsConstructor
 * ------------------------
 * @Data  
 * 	=> 다 만들기. 단점은 뺄 수가 없어서 유연성이 떨어짐
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter 
@ToString
public class Member {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;

}
