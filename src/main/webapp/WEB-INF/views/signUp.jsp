<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container" style="margin-top: 20px;">
	<main>
		<div class="row g-5">
			<h4 class="mb-3">회원가입</h4>
			<form class="needs-validation" action="/register"  method="post" enctype="multipart/form-data" novalidate>
				<div class="row g-3">
					<div class="col-sm-12">
						<label for="mi_id" class="form-label">아이디</label> 
						<input type="text" name="mi_id" class="form-control" id="mi_id" 	placeholder="id">
						<div class="invalid-feedback">Valid first name is required.
						</div>
					</div>
					<div class="col-sm-12">
						<label for="mi_pw" class="form-label">비밀번호</label> 
						<input type="password" name="mi_pw" class="form-control" id="mi_pw" placeholder="pwd">
					</div>
					<div class="col-12">
						<label for="mi_name" class="form-label">이름</label> 
						<input type="text" name="mi_name" class="form-control" placeholder="Username" id="mi_name">
					</div>
					<div class="col-12">
						<label for="mi_nickname" class="form-label">닉네임</label> 
							<input type="text" name="mi_nickname" class="form-control" placeholder="Nickname" name="mi_nickname">
					</div>
					<div class="col-12">
						<label for="mi_email" class="form-label">Email </label> 
						<input type="email" name="mi_email" class="form-control" id="mi_email" placeholder="you@example.com">
						<div class="col-12">
							<label for="mi_mobile" class="form-label">휴대폰번호</label> 
							<input type="text" name="mi_mobile" class="form-control" placeholder="010-0000-0000" id="mi_mobile">
						</div>
						<label for="address2" class="form-label">Address</label> 
						<div class="row g-3" style="margin: 10px 0 0 0">
							<div class="col-sm-6">
								<input type="text" id="mi_postcode" name="mi_postcode" class="form-control" placeholder="우편번호">
							</div>
							<div class="col-6">
								<input type="button" onclick="find_address()" class="btn btn-primary" value="우편번호 찾기">
							</div>
						</div>
						<div class="col-12">
							<label for="address" class="form-label"></label> 
							<input type="text" id="mi_roadaddress" name="mi_roadaddress" class="form-control" placeholder="주소">
						</div>
						<div class="col-12" style="margin-bottom: 20px;">
							<label for="address2" class="form-label"></label> 
							<input type="text" id="mi_detailaddress" name="mi_detailaddress" class="form-control" placeholder="상세주소">
						</div>
						<div class="col-12" style="margin-bottom: 20px;">
							<label for="File" class="form-label"></label> 
							<input type="file" id="file" name="file" class="form-control">
						</div>
						<button class="w-100 btn btn-primary btn-lg" type="submit">회원가입</button>
					</div>
				</div>
			</form>
		</div>
	</main>
</div>
<script>
	function find_address() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.

						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('mi_postcode').value = data.zonecode;
						document.getElementById("mi_roadaddress").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("mi_detailaddress")
								.focus();
					}
				}).open();
	}
</script>
