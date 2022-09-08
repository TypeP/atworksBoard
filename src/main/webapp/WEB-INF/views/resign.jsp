<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container" style="margin-top: 20px;">
	<main>
		<div class="row g-5">
			<h4 class="mb-3">회원탈퇴</h4>
			<form class="needs-validation" action="/withdrawl" method="post" novalidate><br><br>
				<div class="row g-3">
					<div class="col-sm-12">
						<label for="mi_pw" class="form-label">비밀번호</label> 
						<input type="password" name="mi_pw" class="form-control" id="mi_pw" placeholder="pwd">
						<input type="hidden" name="mi_id" value="${loginUser.mi_id}">
						<input type="hidden" name="board_id" value="${loginUser.board_id}" >
						<input type="hidden" name="saved_file_name" value="${loginUser.saved_file_name}" >
					</div>
					<div class="col-12" style="margin-bottom: 20px;"></div>
					<button class="w-100 btn btn-primary btn-lg" type="submit">회원탈퇴</button>
				</div>
			</form>
		</div>
	</main>
</div>
	