<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container" style="margin-top: 20px;">
	<main>
		<div class="row g-5">
			<h4 class="mb-3">공지사항</h4>
			<div class="row g-3">
				<div class="col-sm-12">
					<label for="ni_title" class="form-label">글제목</label> 
						<input type="text" name="ni_title" class="form-control" id="ni_title" value="${dto.ni_title}"  readonly>
					<div class="invalid-feedback">
					</div>
				</div>
				<div class="col-sm-12" >
					<label for="ni_content" class="form-label">글내용</label> 
					<br>
						<textarea rows="5" cols="115" name="ni_content" id="ni_content"  readonly>${dto.ni_content}</textarea>
				</div>
				<c:choose>
					<c:when test="${fn:length(dto.fileList) > 0}">
						<div class="col-sm-12" >
							<label for="file_name" class="form-label">첨부파일</label> 
							<br>
							<c:forEach items="${dto.fileList }" var="file">
								<div>${file.file_name }</div>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<label for="file_name" class="form-label">첨부파일이 없습니다.</label> 
						<br>                                
						<div></div>
					</c:otherwise>
				</c:choose>
				<div class="col-sm-6">
					<!-- 로그인한 유저가 있을때 수정 삭제가 허용됨  -->
					<c:choose>
                     	<c:when test="${loginUser ne null }">
                     		<!-- 리스트로 넘어오는 값이어서 c:forEach items으로 List의 for문으로 값을 출력해야함 -->
							<a href="/modifyNotice?seq=${dto.ni_no}"><button class="w-49 btn btn-primary btn-lg" >게시글 수정</button></a>
							<!-- 게시글삭제버튼을 클릭하면 삭제모달로 넘어감 -->
							<a href="#" data-toggle="modal" data-target="#deleteModal"><button class="w-49 btn btn-primary btn-lg" >게시글 삭제</button></a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</main>
</div>
<!-- 삭제모달 Modal-->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">공지사항을 삭제하시겠습니까?</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">공지사항을 삭제하면 서버에있는 파일까지 사라집니다</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
				<!-- 리스트로 넘어오는 값이어서 c:forEach items으로 List의 for문으로 값을 출력해야함 -->
				<a class="btn btn-primary" href="/deleteNotice?seq=${dto.ni_no}">삭제</a>
			</div>
		</div>
	</div>
</div>

<script>
	let data = "${dto.fileList }";
	
	console.log(data);
</script>
