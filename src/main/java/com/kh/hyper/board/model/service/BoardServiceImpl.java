package com.kh.hyper.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.kh.hyper.board.model.dao.BoardMapper;
import com.kh.hyper.board.model.vo.Board;
import com.kh.hyper.board.model.vo.Reply;
import com.kh.hyper.common.model.vo.PageInfo;
import com.kh.hyper.common.template.Pagination;
import com.kh.hyper.exception.BoardNotFoundException;
import com.kh.hyper.exception.InvalidParameterException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
	/*
	 * 결론적으로 뭘 해야 하지??
	 * 1. 컨트롤러 bean 등록 -> @Service
	 * 2. 주입받기 -> 생성자 주입 -> 롬복 @RequiredArgsConstructor 
	 * 3. 매퍼 필드 선언
	 */
	private final BoardMapper mapper;
	
	
	/*----------------------------------------------------------------------------------------*/
	/*---------------------------------------- 메소드 분리 ---------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	// 너무 많은 기능을 하고 있는 메소드를 분리하여 책임을 나눴다.
	
	private int getTotalCount() {
		int totalCount = mapper.selectTotalCount();
		
		if(totalCount == 0) {
			throw new BoardNotFoundException("게시글 없어~");
		}
		return totalCount;
	}
	
	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 5, 5);
	}
	
	private List<Board> getBoardList(PageInfo pi){
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectBoardList(rowBounds);
	}
	/*----------------------------------------------------------------------------------------*/
	/*---------------------------------------- 메소드 분리 ---------------------------------------*/
	/*----------------------------------------------------------------------------------------*/
	
	
	
	@Override
	public Map<String, Object> selectBoardList(int currentPage) {
		/*
		 * 총 개수 					-> DB가서 조회
		 * 요청 페이지					-> currentPage
		 * 한 페이지에 게시글 몇 개?		-> 5
		 * 페이징바 몇 개?				-> 5
		 */
		int totalCount = getTotalCount();
		// log.info("게시글 개수 : {}", totalCount);
		// log.info("요청 페이지 : {}", currentPage);
		PageInfo pi = getPageInfo(totalCount, currentPage);
		List<Board> boards = getBoardList(pi);
		log.info("게시글목록 : {}", boards);
		
		Map<String, Object> map = new HashMap();
		map.put("boards", boards);
		map.put("pageInfo", pi);
		
		return map;
	}

	@Override
	public void insertBoard(Board board) {

	}
	
	// setChangeName(/hyper/ 절대경로 포함)
	
	
	/*-----------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------*/
	private void validateBoardNo(Long boardNo) {
		if(boardNo == null || boardNo <= 0) {
			throw new InvalidParameterException("유효하지 않은 게시글 번호다");
		}
	}
	private void incrementViewCount(Long boardNo) {
		int result = mapper.increaseCount(boardNo);
		if(result < 1) {
			throw new BoardNotFoundException("게시글이 존재하지 않음");
		}
	}
	private Board findBoardById(Long boardNo) {
		Board board = mapper.selectById(boardNo);
		if(board == null) {
			throw new BoardNotFoundException("게시글을 찾을 수 없습니다.");
		}
		return board;
	}
	
	/*------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------*/

	@Override
	public Map<String, Object> selectById(Long boardNo) {
		// 번호가 0보다 큰 수 인지 검증
		validateBoardNo(boardNo);
		
		// 조회수 증가
		incrementViewCount(boardNo);
		
		// 사용자가 요청 보낼 때
		// 게시글 번호를 가지고 있는지 없는지
		Board board = findBoardById(boardNo);
		
		// 있다면 보드 VO에 필드에 돌아온 데이터를 담아주기
		Map<String, Object> responseData = new HashMap();
		responseData.put("board", board);
		
		return responseData;
	}

//	@Override
//	public void updateBoard(Board board, MultipartFile upfile) {
//		validateBoardNo(board.getBoardNo());
//		findBoardById(board.getBoardNo());
//		
//		// 새 파일을 첨부 했는지
//		if(!upfile.getOriginalFilename().equals("")) {
//			
//			if(board.getChangeName() != null) {
//				// 기존 첨부파일 존재했는지 체크 후 삭제
//				new File(context.getRealPath(board.getChangeName())).delete();
//			}
//			
//			handleFileUpload(board, upfile);
//		}
//		
//		int result = mapper.updateBoard(board);
//		
//		if(result > 0) {
//			throw new BoardNotFoundException("업데이트 실패");
//			
//		}
//		
//	}

	@Override
	public void deleteBoard(Long boardNo, String changeName) {
		validateBoardNo(boardNo);
		Board board = findBoardById(boardNo);
		// 원래 board에 있는 BoardWriter랑 login유저의 userId를 비교하는 로직 있어야함
		// => 인터셉터에서 처리했다. 생략
		
		int result = mapper.deleteBoard(boardNo);
		
		if(result <= 0) {
			throw new BoardNotFoundException("게시글 삭제 실패");
		}
		
		// 파일 삭제
		// 보드를 'N'으로 바꾸고 왔다. 파일이 있다면 지운다
		if(!("".equals(changeName))) {
//			try {
//			new File(context.getRealPath(changeName)).delete(); 
//			} catch(RuntimeException e) {
//				throw new BoardNotFoundException("파일을 찾을 수 없습니다.");
//			} ***** 빈 부분 채우고 주석 풀기
		}
		
		
	}

	@Override
	public int insertReply(Reply reply) {
		
		return mapper.insertReply(reply);
	}

	@Override
	public List<Reply> selectReplyList(Long boardNo) {
		
		return mapper.selectReplyList(boardNo);
	}
	
	
	
	
	
	
	
	
	
	

}
