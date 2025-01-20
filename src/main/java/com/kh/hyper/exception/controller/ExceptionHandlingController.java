package com.kh.hyper.exception.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.hyper.exception.BoardNotFoundException;
import com.kh.hyper.exception.ComparePasswordException;
import com.kh.hyper.exception.FailToFileUploadException;
import com.kh.hyper.exception.InvalidParameterException;
import com.kh.hyper.exception.TooLargeValueException;
import com.kh.hyper.exception.UserFoundException;
import com.kh.hyper.exception.UserIdNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {
	/*
	 * 예외 발생 시
	 * Dispatcher Servlet이 예외가 발생한곳에서 예외처리 핸들러가 있나 찾는다
	 * 없다면? @ControllerAdvice가 있나 찾는다
	 * 있다면 -> 해당 예외를 처리할 @핸들러가 있나 찾는다
	 */
	
	// 중복 줄이기용 메소드
	private ModelAndView createErrorResponse(String errorMsg, Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", errorMsg)
		.setViewName("common/error_page");
		log.info("발생 예외 : {}", e.getMessage(), e);
		return mv;
	}
	
	
	
	@ExceptionHandler(DuplicateKeyException.class)
	protected ModelAndView handleTransactionError(DuplicateKeyException e) {
		/* 중복코드
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", "잘못된 요청입니다.")
		.setViewName("common/error_page");
		return mv;
		*/
		return createErrorResponse("잘못된 요청입니다", e);
	}

	@ExceptionHandler(UserIdNotFoundException.class)
	protected ModelAndView NoSuchUserIdError(UserIdNotFoundException e) {
		return createErrorResponse("아이디가 존재하지 않습니다.", e);
	}

	@ExceptionHandler(ComparePasswordException.class)
	protected ModelAndView NotMatchingPasswordError(ComparePasswordException e) {
		return createErrorResponse("비밀번호가 일치하지 않습니다.", e);
	}
	
	@ExceptionHandler(UserFoundException.class)
	protected ModelAndView UserExistError(UserFoundException e) {
		return createErrorResponse("이미 존재하는 아이디입니다.", e);
	}
	
	@ExceptionHandler(TooLargeValueException.class)
	protected ModelAndView TooLargeValueException(TooLargeValueException e) {
		return createErrorResponse("유효하지 않은 값을 입력하셨습니다.", e);
	}
	
	
	// Board
	
	@ExceptionHandler(BoardNotFoundException.class)
	protected ModelAndView NoSuchBoardError(BoardNotFoundException e) {
		return createErrorResponse("게시글이 존재하지 않습니다.", e);
	}
	
	@ExceptionHandler(FailToFileUploadException.class)
	protected ModelAndView filetoFileupload(FailToFileUploadException e) {
		return createErrorResponse("파일 업로드 실패", e);
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	protected ModelAndView invalidParameterError(InvalidParameterException e) {
		return createErrorResponse("장난 치지마라", e);
	}
	
	
	public ResponseEntity<String> badRequest(){
		return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> notFound(){
		return new ResponseEntity<String>("Resources Not Found", HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

