<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container" style="margin-top: 20px;">
	<main>
		<div class="row g-5">
			<h4 class="mb-3">갤러리게시판 글쓰기</h4>
			<form class="needs-validation" action="/writeGallery"  method="post" enctype="multipart/form-data" novalidate>
				<input type="hidden" name="gl_writer" value="${loginUser.mi_id }" >
				<div class="row g-3">
					<div class="col-sm-12">
						<label for="gl_title" class="form-label">제목</label> 
						<input type="text" name="gl_title" class="form-control" id="gl_title" 	>
					</div>
					<div class="col-sm-12" >
						<label for="gl_content" class="form-label">내용</label> <br>
						<textarea rows="5" cols="115" name="gl_content" id="gl_content" ></textarea>
					</div>
					<div class="col-12" style="margin-bottom: 20px;">
						<label for="File" class="form-label"></label> 
						<input type="file" multiple="multiple" id="file" name="file" class="form-control">
					</div>
					<div class="col-sm-6">
						<button class="w-100 btn btn-primary btn-lg" type="submit">게시글 작성</button>
					</div>
				</div>
			</form>
		</div>
	</main>
</div>