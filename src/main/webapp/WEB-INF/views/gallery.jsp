<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div id="dataTable_filter" class="dataTables_filter">
		<label>Search:</label>
		<!-- 검색어 입력하는곳 각각의 value로 내용을 가지고옴  -->
		<select id="searchType">
			<option value="all">전체</option>
			<option value="ni_title">제목</option>
			<!--밸류에 dto와 같이 맞추어주어야함-->
			<option value="ni_writer">작성자</option>
		</select> 
			<input type="text" id='search' class="form-control form-control-sm"	placeholder="" aria-controls="dataTable"> 
			<input type="button" id="searchbutton" class="w-49 btn btn-primary btn-lg" value="검색">
	</div>
	<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
		<div class="list">
		</div>
	</div>
	<div class="pageNav">
	</div>  
	<c:choose>
        <c:when test="${loginUser ne null }">
           	<div><br>
			<a class="btn btn-primary btn-user btn-block" href="/writeBoard">갤러리게시글작성 </a>
			</div>
		</c:when>
	</c:choose>
</div>

<script>
$(document).ready(function() {
	getList(1);		//1페이지를 선택하면 getList function을 호출\
});

function getList(selectPage) {
	let data = {};		//데이터 배열선언
	data.pagePerCnt = 3;		//data에 3페이지까지 출력
	data.curPage = selectPage;	
	$.ajax({			//ajax사용
		type : 'GET',		//get방식
		url : "/notice/getGalleryList",		//이동할주소
		data : data,			//데이터방식
		error : function(error) {	//에러가뜨면 처리한다
			alert("Error!");
		},
		success : function(value) {
			console.log(value);
			let html = "";		//html 변수선언
			$(".list").children().remove();		//list의 내용을 전부 제거한다
			if(value.length < 1) {				//value에 있는 길이가 1보다 작으면
				html += '<span>공지사항 목록이 없습니다</span>';		//내용을 스트링으로 입력
			}else {				//배열에 값이 있으면
				for (let i = 0; i < value.list.length; i++) {		//value에 리스트 값을 가지고 온다
					html += '<div class="col">'; 
					html += '<div class="card shadow-sm">'; 
					html += '<svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">';
					html += '<title>Placeholder</title>';
					html += '<image width="250" href="/resources/file/'+value.list[i].saved_file_name+'"></svg>';
					html += '<div class="card-body">';
					html += '<p class="card-text">'+value.list[i].gl_title+'</p>';
					html += '<div class="d-flex justify-content-between align-items-center">';
					html += '<div class="btn-group">';
					html += '<a href="/contentGallery?gl_no='+value.list[i].gl_no+'"><button type="button" class="btn btn-sm btn-outline-secondary">View</button></a>';
					html += '<a href="/modifyBoard?gl_no='+value.list[i].gl_no+'"><button type="button" class="btn btn-sm btn-outline-secondary">Edit</button></a>';
					html +=	'</div>'
					html += '<small class="text-muted">9 mins</small>'
					html += '</div>'
					html += '</div>'
					html += '</div>'
					html += '</div>'
				}
			}
			$(".list").append(html);		//list에 위 내용을 출력한다
			
			let pagingHtml = paging({		//paginghtml 페이지에 위 내용을 넣는다
				pagePerCnt : value.pagePerCnt,	//몇개나 출력이 될지 내용에 있는 페이지를 넣고
				currentPage : value.curPage,	//현재페이지의 번호를 갖고오며
				totalCnt : value.totalCnt		//총 몇개가 되는지 가지고온다
			});
			
			$(".pageNav").empty().append(pagingHtml);		//네비게이션을 초기화하고 페이징 처리를 다시함
		}
	});
}

function goPage(selectPage){
	getList(selectPage);
}

</script>

<!-- <div class="col">
			<div class="card shadow-sm">
				<svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><rect width="100%" height="100%"fill="#55595c"></rect>
					<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
				<div class="card-body">
					<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
					<div class="d-flex justify-content-between align-items-center">
						<div class="btn-group">
							<button type="button" class="btn btn-sm btn-outline-secondary">View</button>
							<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
						</div>
						<small class="text-muted">9 mins</small>
					</div>
				</div>
			</div>
		</div> -->