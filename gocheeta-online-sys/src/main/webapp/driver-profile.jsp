<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="com.gocheeta.model.Login" %>
<%

Login checkLogin = (Login) session.getAttribute("login");
if(checkLogin == null){
	response.sendRedirect("login.jsp");
}

%>   
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
		<link rel="stylesheet" href="css/default.css" type="text/css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
		<title>GoCheeta Control Panel</title>
	</head>
	<body>
		<div class="container-fluid table-content">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-xl-12 col-lg-12 page-header">
					<div class="bg-white shadow-sm p-2">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb mt-2" style="font-size: 15px;">
								<li class="breadcrumb-item"><a href="dashboard.jsp" target="iframe" class="home-btn"><i class="bi bi-house-door"></i></a></li>
								<li class="breadcrumb-item active" aria-current="page">Driver Profile</li>
							</ol>
						</nav>
						<a href="javascript:history.go(-1)" class="btn btn-sm btn-outline-secondary float-end back-btn" title="Back" style="margin-top: -43px;"><i class="bi bi-chevron-double-left"></i>&nbsp;<span>Back</span></a>
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mt-2 table-content-inner">
					<div class="bg-white p-3 shadow-sm">
						<div class="row">
							<div class="bg-light fw-bold border-end border-bottom border-2 border-white col-3 p-2">First Name</div>
							<div class="col-3 border-end border-bottom border-2 border-white p-2"><tag:out value="${driver.firstName}"></tag:out></div>
							<div class="bg-light fw-bold border-end border-bottom border-2 border-white col-3 p-2">Last Name</div>
							<div class="col-3 border-end border-bottom border-2 border-white p-2"><tag:out value="${driver.lastName}"></tag:out></div>
						</div>
						<div class="row">
							<div class="bg-light fw-bold border-end border-bottom border-2 border-white col-3 p-2">Contact No</div>
							<div class="col-3 border-end border-bottom border-2 border-white p-2"><tag:out value="${driver.contactNo}"></tag:out></div>
							<div class="bg-light fw-bold border-end border-bottom border-2 border-white col-3 p-2">Vehicle No</div>
							<div class="col-3 border-end border-bottom border-2 border-white p-2"><tag:out value="${driver.vehicleNo}"></tag:out></div>
						</div>
						<div class="row">
							<div class="bg-light fw-bold border-end border-bottom border-2 border-white col-3 p-2">NIC(User Name)</div>
							<div class="col-3 border-end border-bottom border-2 border-white p-2"><tag:out value="${driver.nic}"></tag:out></div>
							<div class="bg-light fw-bold border-end border-bottom border-2 border-white col-3 p-2">Password</div>
							<div class="col-3 border-end border-bottom border-2 border-white p-2"><tag:out value="${driver.password}"></tag:out></div>
						</div>
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 page-footer">
					<div class="bg-white">
						<p class="text-center mt-2 text-muted" style="font-size: 12px;">&copy;GoCheeta Booking Management System(v1.0)</p>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>