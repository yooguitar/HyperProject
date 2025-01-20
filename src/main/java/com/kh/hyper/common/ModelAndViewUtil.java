package com.kh.hyper.common;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ModelAndViewUtil {
	// static을 사용하는 방법도 있지만
	// static을 사용하면 의존성이 명확해지는 단점이 생김
	// spring에서는 의존성을 줄여보려 하기때문에 다른 방법을 사용해보자

	
	
	public ModelAndView setViewNameAndData(String viewName, Map<String, Object> modelData) {
		// 자바에서 여러쌍의 키,밸류를 관리하기 위해서는 MAP을 사용
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName(viewName);
		if(modelData != null) {
			mv.addAllObjects(modelData);
		}
		return mv;
		
	}
	
}
