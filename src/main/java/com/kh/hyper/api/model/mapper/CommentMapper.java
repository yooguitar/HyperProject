package com.kh.hyper.api.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.kh.hyper.api.model.dto.CommentDTO;

@Mapper
public interface CommentMapper {
	
	@Insert("INSERT INTO TB_FOOD_REPLY VALUES(#{writer}, #{content}, #{foodNo})")
	void save(CommentDTO comment);

	List<CommentDTO> getComment(Long foodNo);
	
}
