<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<!-- Outer Row -->
	<div class="row justify-content-center">
		<div class="col-xl-10 col-lg-12 col-md-9">
			<div class="card o-hidden border-0 shadow-lg my-5">
				<div class="card-body p-0">
					<!-- Nested Row within Card Body -->
					<div class="row">
						<div class="col-lg-6">
							<div class="p-5">
								<div class="text-center">
									<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
								</div>
								<form class="user" action="/login" method="post">
									<div class="form-group">
										<input name="mi_id" class="form-control" id="mi_id" placeholder="story">
									</div>
									<div class="form-group">
										<input type="password" name="mi_pw" class="form-control" id="mi_pw" placeholder="Password">
									</div>
									<div class="form-group">
										<div class="custom-control custom-checkbox small">
											<input type="checkbox" class="custom-control-input" id="customCheck"> 
											<label class="custom-control-label" for="customCheck">Remember Me</label>
										</div>
									</div>
									<button class="btn btn-primary btn-user btn-block" type="submit">Login </button>
									<a class="btn btn-primary btn-user btn-block" href="/signUp">SignUp </a>
								</form>
								<hr>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>