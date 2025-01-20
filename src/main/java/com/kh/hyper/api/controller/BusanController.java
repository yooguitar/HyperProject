package com.kh.hyper.api.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(produces="application/json; charset=UTF-8")
public class BusanController {

	@GetMapping("/busan")
	public ResponseEntity<?> getBusanFood(int page){
		String requestUrl = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
		   requestUrl += "?serviceKey=ElI9%2FMOuIP0qtq%2FWVgG%2F8vBVcXmnts24r6z4GtxQ1lKkjgoBlf4K69EsyiQZOahN8%2FWp%2F%2BIDkzSqVJfsw%2B0D5Q%3D%3D";
		   requestUrl += "&numOfRows=10";
		   requestUrl += "&pageNo="+page;
		   requestUrl += "&resultType=json";
		   URI uri = null;
		   try {
			   uri = new URI(requestUrl);
		   }catch(URISyntaxException e) {
			   e.printStackTrace();
		   }
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(uri, String.class);
		//System.out.println(response.getClass()); // String
		return ResponseEntity.ok(response);
	}
	
	
	
}
