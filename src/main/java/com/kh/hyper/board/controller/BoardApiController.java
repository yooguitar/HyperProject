package com.kh.hyper.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.hyper.board.model.service.BoardService;
import com.kh.hyper.board.model.vo.Reply;
import com.kh.hyper.common.model.vo.ResponseData;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reply")
@RequiredArgsConstructor
public class BoardApiController {
	
	private final BoardService boardService;
	
	@PostMapping
	public ResponseEntity<ResponseData> ajaxInsertReply(Reply reply) {
		// ResponseEntity responseData;
		int result = boardService.insertReply(reply);
		
		ResponseData response = ResponseData.builder()
											.message("댓글 등록에 성공했습니다!")
											.status(HttpStatus.OK.toString())
											.data(result)
											.build();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<ResponseData> ajaxSelectReply(Long boardNo){
		
		List<Reply> replies = boardService.selectReplyList(boardNo);
		
		ResponseData response = ResponseData.builder()
											.message("댓글 조회에 성공했습니다!")
											.status(HttpStatus.OK.toString())
											.data(replies)
											.build();
		
		return new ResponseEntity<ResponseData>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
