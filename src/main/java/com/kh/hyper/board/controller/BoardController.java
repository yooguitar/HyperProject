package com.kh.hyper.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.hyper.board.model.service.BoardService;
import com.kh.hyper.board.model.vo.Reply;
import com.kh.hyper.common.ModelAndViewUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class BoardController {
	/*
	 * 결론적으로 뭘 해야 하지??
	 * 1. 컨트롤러 bean 등록 -> @Controller
	 * 2. 주입받기 -> 생성자 주입 -> 롬복 @RequiredArgsConstructor
	 * 3. Service, ModelAndViewUtil 필드 선언
	 */
	private final BoardService boardService;
	private final ModelAndViewUtil mv;
	
	// menubar.jsp에서 게시판 클릭 시 => boards => page == 1
	// 페이징바에서 눌렀다 => board?page=요청페이지 => page == 요청페이지
	@GetMapping("boards")
	public ModelAndView selectBoardList(@RequestParam(value="page", defaultValue="1") int page) {
		
		Map<String, Object> map = boardService.selectBoardList(page);
		
		return mv.setViewNameAndData("board/list", map);
	}
	
	
	@GetMapping("boards/{id}")
	public ModelAndView selectById(@PathVariable(name="id") Long id) {
		// log.info("{}", id);
		Map<String, Object> responseData = boardService.selectById(id);
		return mv.setViewNameAndData("board/detail", responseData);
	}
	
	@PostMapping("boards/delete")
	public ModelAndView deleteBoard(Long boardNo, String changeName) {
		boardService.deleteBoard(boardNo, changeName);
		return mv.setViewNameAndData("redirect:/boards", null);
	}
	
	@PostMapping("boards/update-form")
	public ModelAndView updateForm(Long boardNo) {
		Map<String, Object> responseData = boardService.selectById(boardNo);
		return mv.setViewNameAndData("board/update", responseData);
	} // 수정 시 <br> 없애기
	
	
//	@PostMapping("boards/update")
//	public ModelAndView update(Board board, MultipartFile upfile) {
//		//log.info("{} / {}", board, upfile);
//		
//		// DB가서 BOARD 테이블에 UPDATE
//		
//		// BOARD -> boardTitle, boardContent, boardWriter, boardNo
//		// 이 네개는 무조건 있음
//		
//		/*
//		 * 첨부파일은?
//		 * 
//		 * 1. 기존 첨부파일 X, 새 첨부파일 X => 그렇구나~
//		 * 2. 기존 첨부파일 O, 새 첨부파일 O => originName : 새거, changeName : 새거
//		 * 3. 기존 첨부파일 X, 새 첨부파일 O => originName : 새거, changeName : 새거
//		 * 4. 기존 첨부파일 O, 새 첨부파일 X => originName : 기존 첨부파일 정보, changeName : 기존 첨부파일 정보
//		 */
//		boardService.updateBoard(board, upfile);
//		
//		return mv.setViewNameAndData("redirect:/boards/"+board.getBoardNo(), null);
//	}
	
	
	
	/*
	 * 약속이다
	 * SELECT : GET
	 * INSERT : POST
	 * UPDATE : PUT
	 * DELETE : DELETE
	 */
	/*
	 * api controller로 보냄
	 * 
	@ResponseBody
	@PostMapping("reply")
	public int ajaxInsertReply(Reply reply) {
		return boardService.insertReply(reply);
	}
	
	
	@ResponseBody
	@GetMapping(value="reply", produces="application/json; charset=UTF-8") // JSON [{replyNo : 1, replyWriter : 'admin'...}, {replyNo : 1, replyWriter : ...}]
	public List<Reply>ajaxSelectReply(Long boardNo){
		return boardService.selectReplyList(boardNo);
	}
	*/
	
	
	@GetMapping("map")
	public String mapForward() {
		return "common/map";
	}
	
	
	
	
	
	
	
	
	
	
}
