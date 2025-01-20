package com.kh.hyper.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.hyper.api.model.dto.CommentDTO;
import com.kh.hyper.api.model.service.BusanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(produces="application/json; charset=UTF-8")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BusanController {

	private final BusanService service;
	
	@GetMapping("/busan")
	public ResponseEntity<String> getBusanFood(int page){
		
		String response = service.getBusan(page);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/busan/{pk}")
	public ResponseEntity<String> getBusanDetail(@PathVariable(name="pk") int pk){
		
		String response = service.getBusanDetail(pk);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/comments")
	public ResponseEntity<String> save(@RequestBody CommentDTO comment){
		
		//System.out.println(comment);
		service.save(comment);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
		// 200: 성공, 201: 생성에 성공
	}
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<List<CommentDTO>> getComments(@PathVariable(name="id") Long foodNo){
		System.out.println("왜못부르는데");
		List<CommentDTO> list = service.getComments(foodNo);
		
		return ResponseEntity.ok(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
