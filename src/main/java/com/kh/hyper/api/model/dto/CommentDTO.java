package com.kh.hyper.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
public class CommentDTO {
	private Long foodNo;
	private String writer;
	private String content;
}

