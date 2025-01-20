package com.kh.hyper.common.mail;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Mailtest4 {
	private final JavaMailSenderImpl sender;
	
	@GetMapping("file-mail")
	public String sendFile() throws MessagingException {
		
		// MimeMessage
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		String[] to = {
				"kitae1996@gmail.com"
		};
		
		helper.setTo(to);
		helper.setSubject("파일받으세요~");
		helper.setText("내용은 없어");
		
		// 파일첨부
		// javax.activation.DataSource : 파일첨부 할 때 사용하는 클래스
		DataSource source = new FileDataSource("C:\\b-class0909\\front-workspace\\02_CSS-workspace\\resources\\bono.jpg");
		helper.addAttachment(source.getName(), source);
		
		// 보내기 버튼
		sender.send(message);
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
