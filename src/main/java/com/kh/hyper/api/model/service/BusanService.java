package com.kh.hyper.api.model.service;

import java.util.List;

import com.kh.hyper.api.model.dto.CommentDTO;

public interface BusanService {
	// 공공데이터 API서버로 요청을 보내서
	// 응답(목록)을 반환해주는 메소드
	String getBusan(int page);
	
	// 공공데이터 API서버로 요청을 보내서
	// 응답(상세보기)을 반환해주는 메소드
	String getBusanDetail(int pk);

	// 상세보기 시 댓글을 조회해서 반환해주는 메소드
	List<CommentDTO> getComments(Long foodNo);
	// 상세보기 시 댓글을 작성할 수 있는 메소드
	void save(CommentDTO comment);
	
}
