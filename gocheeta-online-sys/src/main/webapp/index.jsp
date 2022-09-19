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
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
		<link rel="stylesheet" href="css/index.css" type="text/css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js" type="text/javascript"></script>
		<title>GoCheeta Control Panel</title>
	</head>
	<body>
		<div class="wrapper d-flex w-100">
			<div class="sidemenu" id="sidemenu">
				<div class="sidemenu-header">
					<h3 class="text-center p-3">GoCheeta</h3>
					<p class="text-white text-end" style="font-size:12px;margin: -29px 45px 0 0;font-style:italic;">Cab Service</p>
				</div>
				<div class="sidemenu-profile-section p-3">
					<span class="profile-upper-letter"><tag:out value="${firstLetterUppercase}"></tag:out></span>
					<div class="text-section">
						<h6><tag:out value="${upperUserName}"></tag:out></h6>
						<p><tag:out value="${login.userType}"></tag:out></p>
					</div>
				</div>
				<div class="sidemenu-navigation-bar p-2">
					<a href="dashboard.jsp" class="nav-link shadow-sm rounded-1 p-2 active" target="iframe"><i class="bi bi-speedometer2"></i>&nbsp;<span>Dashboard</span></a>
					<ul class="accordion mt-3 nav flex-column" id="accordion">
						<li class="nav-item">
							<a href="#" class="nav-link link"><i class="bi bi-book"></i> <span>Booking <i class="bi bi-chevron-right float-end"></i></span></a>
							<ul class="nav flex-column submenu">
								<li class="nav-item"><a href="booking" class="nav-link" target="iframe"><i class="bi bi-list-stars"></i> View Booking</a></li>
							</ul>
						</li>
						<li class="nav-item">
							<a href="#" class="nav-link link"><i class="bi bi-share"></i> <span>Branch <i class="bi bi-chevron-right float-end"></i></span></a>
							<ul class="nav flex-column submenu">
								<li class="nav-item"><a href="add-branch.jsp" class="nav-link" target="iframe"><i class="bi bi-plus"></i> Add Branches</a></li>
								<li class="nav-item"><a href="branch" class="nav-link" target="iframe"><i class="bi bi-list-stars"></i> Manage Branches</a></li>
							</ul>
						</li>
						<li class="nav-item">
							<a href="#" class="nav-link link"><i class="bi bi-people"></i> <span>Customers <i class="bi bi-chevron-right float-end"></i></span></a>
							<ul class="nav flex-column submenu">
								<li class="nav-item"><a href="customer" class="nav-link" target="iframe"><i class="bi bi-list-stars"></i> View Customers</a></li>
							</ul>
						</li>
						<li class="nav-item">
							<a href="#" class="nav-link link"><i class="bi bi-boxes"></i> <span>Category <i class="bi bi-chevron-right float-end"></i></span></a>
							<ul class="nav flex-column submenu">
								<li class="nav-item"><a href="add-category.jsp" class="nav-link" target="iframe"><i class="bi bi-plus"></i> Add Categories</a></li>
								<li class="nav-item"><a href="category" class="nav-link" target="iframe"><i class="bi bi-list-stars"></i> Manage Categories</a></li>
							</ul>
						</li>
						<li class="nav-item">
							<a href="#" class="nav-link link"><i class="bi bi-person-workspace"></i> <span>Drivers <i class="bi bi-chevron-right float-end"></i></span></a>
							<ul class="nav flex-column submenu">
								<li class="nav-item"><a href="newdriver" class="nav-link" target="iframe"><i class="bi bi-plus"></i> Add Drivers</a></li>
								<li class="nav-item"><a href="driver" class="nav-link" target="iframe"><i class="bi bi-list-stars"></i> Manage Drivers</a></li>
							</ul>
						</li>
						<li class="nav-item">
							<a href="#" class="nav-link link"><i class="bi bi-geo-alt"></i> <span>Location <i class="bi bi-chevron-right float-end"></i></span></a>
							<ul class="nav flex-column submenu">
								<li class="nav-item"><a href="newlocation" class="nav-link" target="iframe"><i class="bi bi-plus"></i> Add Location</a></li>
								<li class="nav-item"><a href="location" class="nav-link" target="iframe"><i class="bi bi-list-stars"></i> Manage Location</a></li>
							</ul>
						</li>
						<li class="nav-item">
							<a href="#" class="nav-link link"><i class="bi bi-person-plus"></i> Users <i class="bi bi-chevron-right float-end"></i></a>
							<ul class="nav flex-column submenu">
								<li class="nav-item"><a href="new" class="nav-link" target="iframe"><i class="bi bi-plus"></i> Add Users</a></li>
								<li class="nav-item"><a href="user" class="nav-link" target="iframe"><i class="bi bi-list-stars"></i> Manage Users</a></li>
							</ul>
						</li>
						<li class="nav-item">
							<a href="#" class="nav-link link"><i class="bi bi-car-front"></i> Vehicles <i class="bi bi-chevron-right float-end"></i></a>
							<ul class="nav flex-column submenu">
								<li class="nav-item"><a href="newvehicle" class="nav-link" target="iframe"><i class="bi bi-plus"></i> Add Vehicles</a></li>
								<li class="nav-item"><a href="vehicle" class="nav-link" target="iframe"><i class="bi bi-list-stars"></i> Manage Vehicles</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<div class="right-panel w-100">
				<div class="right-panel-top__section w-100 shadow-sm">
					<a type="button" class="ms-3 mt-4 screen-resizer" id="screenResizer" title="Minimize sidemenu"><i class="bi bi-fullscreen-exit"></i></a>
					<a type="button" class="sidemenu-open-btn d-block d-md-none d-lg-none d-xl-none" id="openSideMenu"><i class="bi bi-list"></i></a>
					<div class="logo d-block d-md-none d-lg-none d-xl-none">
						<h3>GoCheeta</h3>
						<p class="">Cab Service</p>
					</div>
					<ul class="nav float-end me-3 mt-3">
						<li class="dropdown">
							<a type="button" class="row" data-bs-toggle="dropdown" data-bs-target="profileBtn" aria-expanded="false">
								<div class="col-3 profile-pic">
									<span class="profile-upper-letter"><tag:out value="${firstLetterUppercase}"></tag:out></span>
								</div>
								<div class="col-9 d-none d-md-block d-lg-block d-xl-block profile-info">
									<h6 class="ms-2"><tag:out value="${upperUserName}"></tag:out></h6>
									<p class="ms-2"><tag:out value="${login.userType}"></tag:out></p>
								</div>
							</a>
							<ul class="dropdown-menu dropdown-menu-right" aria-labelledby="profileBtn">
								<li><a href="#" class="dropdown-item"><i class="bi bi-person-circle"></i>&nbsp;Profile</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a href="logout" class="dropdown-item"><i class="bi bi-box-arrow-left"></i>&nbsp;Logout</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="right-panel-panel__framew-100">
					<iframe name="iframe" frameborder="0" class="w-100 mt-1" src="dashboard.jsp"></iframe>
				</div>
			</div>
		</div>
		<script src="js/maxScreen.js" type="text/javascript"></script>
		<script src="js/accordion.js" type="text/javascript"></script>
	</body>
</html>
