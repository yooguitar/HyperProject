package com.kh.hyper.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Board {
	/*
	 * 참고
	 *	Wrapper Class Long 형
	 *	int형은 약 2,190,000,000까지 
	 *	기본자료형은 NULL값을 가질 수 없다
	 */
	private Long boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String originName;
	private String changeName;
	private int count;
	private String createDate;
	private String status;

}
