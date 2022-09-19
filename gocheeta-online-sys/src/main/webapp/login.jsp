<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
		<link rel="stylesheet" href="css/default.css" type="text/css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
		<title>GoCheeta Control Panel</title>
	</head>
	<body class="bg-light">
		<div class="container-fluid login">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-7 col-lg-7 col-xl-7 m-auto bg-white mt-5 rounded-1 shadow-sm">
					<div class="row">
						<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 p-3 border-end">
							<img alt="login-page-image" src="images/login.png" class="img-responsive d-block mx-auto mt-4" width="250">
						</div>
						<div class="col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 p-3">
							<h3 class="text-center mt-3" style="font-style: italic">GoCheeta</h3>
							<p class="text-end fw-bold" style="font-size:12px;margin: -15px 58px 0 0;font-style:italic;">Cab Service</p>
							<tag:if test="${message != null}">
								<div class="alert alert-danger p-1 mt-2">
									<p class="mt-2"><tag:out value="${message}"></tag:out></p>
								</div>
							</tag:if>
							<form action="login?type=submit" method="post" class="mt-2">
								<div class="form-group mb-2">
									<label for="userName" class="form-label">Username <span class="text-danger">*</span></label>
									<input type="text" name="userName" id="userName" class="form-control" value="" autocomplete="off" />
									<span class="text-danger" style="font-size: 14px;font-weight: 400;"><tag:out value="${userNameError}"></tag:out></span>	
								</div>
								<div class="form-group mb-2">
									<label for="password" class="form-label">Password <span class="text-danger">*</span></label>
									<input type="password" name="password" id="password" value="" class="form-control" />
									<span class="text-danger" style="font-size: 14px;font-weight: 400;"><tag:out value="${passwordError}"></tag:out></span>	
								</div>
								<div class="form-group">
									<button type="submit" name="login" value="Login" class="btn w-100 save-btn">Login</button>
								</div>
							</form>
							<p class="text-muted m-3 text-center" style="font-size: 12px;">&copy;GoCheeta Booking Management System(v1.0)</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>