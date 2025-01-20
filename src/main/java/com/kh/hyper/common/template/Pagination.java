package com.kh.hyper.common.template;

import com.kh.hyper.common.model.vo.PageInfo;

public class Pagination {

	public static PageInfo getPageInfo(int listCount, 
										int currentPage, 
										int boardLimit, 
										int pageLimit) {
		
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) { endPage = maxPage; }
									
		// return new PageInfo(listCount, currentPage, boardLimit, pageLimit, maxPage, startPage, endPage);
		// builder 패턴을 사용하여 원하는대로 매개변수 생성자를 만드는 방법.
		// 롬복을 사용중이라면 매개변수 생성자 애노테이션을 꼭 써두자
		return PageInfo.builder()
						.listCount(listCount)
						.currentPage(currentPage)
						.boardLimit(boardLimit)
						.pageLimit(pageLimit)
						.maxPage(maxPage)
						.startPage(startPage)
						.endPage(endPage)
						.build();
								
	}
}
