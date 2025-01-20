package com.kh.hyper.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.hyper.board.model.vo.Board;
import com.kh.hyper.board.model.vo.Reply;

public interface BoardService {
	
	Map<String, Object> selectBoardList(int currentPage);
	// 보드를 담은 리스트와 만들어진 페이지 인포를 맵에 담아서 반환 할 것
	
	void insertBoard(Board board);
	
	Map<String, Object> selectById(Long boardNo); 
	
	//void updateBoard(Long boardNo, MultipartFile upfile);
	
	void deleteBoard(Long boardNo, String changeName);
	
	int insertReply(Reply reply);
	
	List<Reply> selectReplyList(Long boardNo);
	
}
