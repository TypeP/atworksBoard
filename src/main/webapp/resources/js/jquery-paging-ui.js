let paging = function(options) {
	let defaults = {
		pagePerCnt : 10,
		currentPage : 1,
		totalCnt : 0,
		pageBlock : 10
	};
	
	let subOption = $.extend(true, defaults, options);
	
	let goPageFnName = null;
	
	if( subOption.goPageFnName == undefined || subOption.goPageFnName == null  || subOption.goPageFnName == "" ) {
		goPageFnName = "goPage";
	} else {
		goPageFnName = subOption.goPageFnName;
	}
	
	let currentPage = subOption.currentPage*1;  // 현재 페이지
	let pagePerCnt = subOption.pagePerCnt*1;   // 출력 리스트 수
	let pageBlock = subOption.pageBlock*1;  // 1~10까지 블락
	let totalCnt = subOption.totalCnt*1;  // 총 데이터 수
			
	if (!pagePerCnt) pagePerCnt = 10;     // 출력 리스트수가 없으면 초기값 10으로 설정
	if (!pageBlock) pageBlock = 10;     // 블락 초기값이 없으면 10으로 설정
		
	let pageTotalCnt = Math.ceil(totalCnt/pagePerCnt);
	let pageBlockCnt = Math.ceil(currentPage/pageBlock);
	let sPage, ePage;
	let pgHtml = "";
			
	if (pageBlockCnt > 1) {
		sPage = (pageBlockCnt-1) * pageBlock+1;
	} else {
		sPage = 1;
	}
			
	if ((pageBlockCnt * pageBlock) >= pageTotalCnt) {
		ePage = pageTotalCnt;
	} else {
		ePage = pageBlockCnt * pageBlock;
	} 
			
	if (sPage > 1) { 
		pgHtml += '<a href="#" onclick="' + goPageFnName + '(' + 1 + ');" class="first"><span><i class="fas fa-angle-double-left"></i></span></a>';
		pgHtml += '<a href="#" onclick="' + goPageFnName + '(' + (sPage-pageBlock) + ');" class="prev"><span><i class="fas fa-angle-left"></i></span></a>'; 
	}
			
	for (let i=sPage; i<=ePage; i++) {
		if (currentPage == i) { 
			pgHtml += '<a href="#" class="active">'+i+'</a>';   
		} else {
			pgHtml += '<a href="#" onclick="' + goPageFnName + '(' + i + ');">'+i+'</a>' 
		}
	}
			
	if (ePage < pageTotalCnt) {
		pgHtml += '<a href="#" onclick="' + goPageFnName + '(' + (sPage+pageBlock) + ');" class="next"><span><i class="fas fa-angle-right"></i></span></a>'
		pgHtml += '<a href="#" onclick="' + goPageFnName + '(' + pageTotalCnt + ');" class="last"><span><i class="fas fa-angle-double-right"></i></span></a>'
	}
			
	pgHtml += '';
	
	return pgHtml;
}
