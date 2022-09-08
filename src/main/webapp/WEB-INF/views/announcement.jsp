<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">공지사항</h1>
	<p class="mb-4">
		이곳에는 앞으로 atworks의 모든 공지사항이 올라옵니다 atworks근무자들은 이 게시물을 확인해주세요
	</p>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">공지사항 게시판</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
					<div class="row">
						<div class="col-sm-12 col-md-12">
							<div id="dataTable_filter" class="dataTables_filter">
								<label>Search:</label>
								<!-- 검색어 입력하는곳 각각의 value로 내용을 가지고옴  -->
								<select id="searchType">
									<option value="all">전체</option>
									<option value="ni_title">제목</option>	<!--밸류에 dto와 같이 맞추어주어야함-->
									<option value="ni_writer">작성자</option>
								</select>
								<input type="text" id='search' class="form-control form-control-sm" placeholder="" aria-controls="dataTable">
								<input type="button" id="searchbutton" class="w-49 btn btn-primary btn-lg" value="검색">
							</div>
						</div>
					</div><br>
					<div class="row">
						<div class="col-sm-12">
							<table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
								<thead>
									<tr role="row">
										<th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 50px;">글 번호</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 408px;">제목</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 115px;">작성자</th>
									</tr>
								</thead>
								<!-- ajax 비동기 통신으로 넘어간 데이터가 나타나는곳 -->
								<tbody class="list">
								</tbody>
							</table>
							<!-- page처리되는자료가 나타나는곳 -->
							<div class="pageNav"></div>
							<!-- 로그인한 유저가 있을때 공지 작성버튼이 나타남 -->
							<c:choose>
                        		<c:when test="${loginUser ne null }">
									<a class="btn btn-primary btn-user btn-block" href="/write">공지사항작성 </a>
								</c:when>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
/* 비동기통신 처음시작 */
$(document).ready(function() {
	getList(1);		//1페이지를 선택하면 getList function을 호출\
	
	 $('#searchbutton').click(function(){ //버튼을 클릭하면 다시한번 넘김
		getList(1);
	});   
});

/* getList펑션 */
function getList(selectPage) {
	let data = {};		//데이터 배열선언
	data.pagePerCnt = 3;		//data에 3페이지까지 출력
	data.curPage = selectPage;		//현재페이지는 선택한페이지
	data.keyword = $("#search").val();   //키워드를 변수에 넣고
	data.searchType = $("#searchType option:selected").val(); //select로 선택한 값을 데이터에 넣는다
	$.ajax({			//ajax사용
		type : 'GET',		//get방식
		url : "/notice/getList",		//이동할주소
		data : data,			//데이터방식
		error : function(error) {	//에러가뜨면 처리한다
			alert("Error!");
		},
		success : function(value) {
			let html = "";		//html 변수선언
			$(".list").children().remove();		//list의 내용을 전부 제거한다
			if(value.length < 1) {				//value에 있는 길이가 1보다 작으면
				html += '<span>공지사항 목록이 없습니다</span>';		//내용을 스트링으로 입력
			}else {				//배열에 값이 있으면
				for (let i = 0; i < value.list.length; i++) {		//value에 리스트 값을 가지고 온다
					html += '<tr><td>'+	value.list[i].ni_no + '</td>';		//html태그를 String배열로 해당 내용을 출력
					html += '<td><a href="/content?seq='+ value.list[i].ni_no +'">'+ value.list[i].ni_title + '</a></td>';	//content라인
					html += '<td>'+ value.list[i].ni_writer + '</td>';
					html += '</tr>';
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