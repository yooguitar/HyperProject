package com.kh.hyper.common.mail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailTest1 {
	
	public static void main(String[] args) {
		/*
		 * 직접 객체를 생성해서 이메일을 보내는 샘플 코드
		 * 필요한 라이브러리 목록
		 * spring-context-support
		 * java mail-api
		 */
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		
		// - 계정설정(SMTP서버가 누구 것 인지?)
		sender.setHost("smtp.gmail.com");
		// 구글 SMTP문서에 포트 587써라 나옴
		sender.setPort(587);
		sender.setUsername("kitae1996");
		sender.setPassword("fcfvosygltjjiqnz");
		
		// - 옵션설정
		Properties options = new Properties();
		options.put("mail.smtp.auth", true);
		options.put("mail.smtp.starttls.enable", true);
		
		sender.setJavaMailProperties(options);
		
		// 메시지 생성
		SimpleMailMessage message = new SimpleMailMessage();
		
		// 메시지 정보 : 제목, 내용, 받는사람, 참조, 숨은참조, 첨부파일(SimpleMailMessage로는 불가능)
		message.setSubject("새해 복 많이 받으세요~");
		message.setText("하하호호~");
		
		//message.setTo("kitae1996@gamil.com");
		String[] to = {"kitae1996@gmail.com"};
		message.setTo(to);
		/*
		 * *참조
		 * message객체.setCc(참조할 주소)
		 * *숨은참조
		 * message객체.setBcc(숨은 참조할 주소)
		 */
		
		// 발송 버튼
		sender.send(message); 
		
		// 메일 잘 감 ㅇㅇ
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
