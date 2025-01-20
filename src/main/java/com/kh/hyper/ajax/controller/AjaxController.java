package com.kh.hyper.ajax.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.hyper.board.model.service.BoardService;
import com.kh.hyper.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AjaxController {
	
	@Autowired
	private BoardService service;
	
	/*
	// 1. HttpServeltResponse 객체로 응답데이터 응답하기
	@GetMapping("ajax1.do")
	public void ajaxMethod1(HttpServletResponse response, String userId) throws IOException {
		//log.info(userId);
		// 요청 처리 다했다~ 요청할 페이지에 데이터를 반환 해줘야함!
		// "있어요!"
		
		// 응답형식 지정
		response.setContentType("text/html; charset=UTF-8");
		// 출력 / 예외 던지기
		response.getWriter().print("있어요!");
	}
	*/
	// 2. 응답할 데이터를 문자열로 반환
	/*
	 * => HttpServletResponse를 사용하지 않는 방식
	 * => 반환타입을 String
	 * => ModelAndView의 viewName 필드에 들어감
	 * => DispatcherServlet
	 * => ViewResolver를 찾는다 => prefix, suffix 찾음
	 * 
	 * 내가 반환하는 String타입의 값이 뷰의 정보가 아니다!!! 응답데이터다!!!
	 * => MessageConverter로 이동하게끔!!
	 * 
	 * @ResponseBody
	 */
	@ResponseBody
	@GetMapping(value="ajax1.do", produces="text/html; charset=UTF-8")
	public String ajaxMethod1(String userId) {
		// 한글이 ???로 나온다 => 응답형식 지정 produces
		return userId + "있어요!";
	}
	
	@ResponseBody
	@GetMapping(value="ajax2.do", produces="application/json; charset=UTF-8")
	public Board ajaxMethod2(Long no) {
		
		Map<String, Object> response = service.selectById(no);
		
		log.info("조회된 게시글 정보 : {}", response.get("board"));
		
		return (Board)response.get("board");
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
