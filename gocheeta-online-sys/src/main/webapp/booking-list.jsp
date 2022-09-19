<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
								<li class="breadcrumb-item active" aria-current="page">View Booking</li>
							</ol>
						</nav>
						<a href="javascript:history.go(-1)" class="btn btn-sm btn-outline-secondary float-end back-btn" title="Back" style="margin-top: -43px;"><i class="bi bi-chevron-double-left"></i>&nbsp;<span>Back</span></a>
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 filter-section">
					<form action="filter?type=filter" method="post" class="row mt-2">
						<div class="form-group col-12 col-sm-12 col-lg-4">
							<label for="branchId" class="form-label">Branch:</label>
							<select name="branchId" id="branchId" class="form-select form-select-sm">
								<option value="0" selected>~~ Select Branch ~~</option>
								<tag:forEach var="branch" items="${branchList}">
									<option value="<tag:out value='${branch.branchId}'></tag:out>"><tag:out value="${branch.branchName}"></tag:out></option>
								</tag:forEach>
							</select>
						</div>
						<div class="form-group col-12 col-sm-12 col-lg-4">
							<label for="startDate" class="form-label">Start Date:</label>
							<input type="datetime-local" name="startDate" id="startDate" class="form-control form-control-sm" value="" />
						</div>
						<div class="form-group col-12 col-sm-12 col-lg-4">
							<label for="endDate" class="form-label">End Date:</label>
							<input type="datetime-local" name="endDate" id="endDate" class="form-control form-control-sm" value="" />
						</div>
						<div class="form-group mt-2">
							<button type="submit" name="filter" value="Filter" class="btn btn-sm save-btn"><i class="bi bi-filter"></i>&nbsp;Filter</button>
						</div>
					</form>
					<tag:if test="${listSize > 0}">
						<table class="table table-sm table-bordered table-striped mt-3">
							<thead>
								<tr>
									<th class="text-center">Reference No</th>
									<th class="text-center">Branch</th>
									<th class="text-center">Customer</th>
									<th class="text-center">Vehicle</th>
									<th class="text-center">Pick Location</th>
									<th class="text-center">Drop Location</th>
									<th class="text-center">Price(LKR)</th>
									<th class="text-center">Status</th>
									<th class="text-center">Action</th>
								</tr>
							</thead>
							<tbody>
								<tag:forEach var="booking" items="${bookingList}">
									<tr>
										<td><tag:out value="${booking.bookingId}"></tag:out></td>
										<td><tag:out value="${booking.branchName}"></tag:out></td>
										<td><tag:out value="${booking.fullName}"></tag:out></td>
										<td><tag:out value="${booking.vehicleNo}"></tag:out></td>
										<td><tag:out value="${booking.pickUp}"></tag:out></td>
										<td><tag:out value="${booking.drop}"></tag:out></td>
										<td class="text-end"><tag:out value="${booking.price}"></tag:out></td>
										<td class="text-center">
											<tag:if test="${booking.bookingStatus == 0}">
												<span class="badge bg-danger rounded-1">New Booking</span>
											</tag:if>
											<tag:if test="${booking.bookingStatus == 1}">
												<span class="badge bg-warning rounded-1">Confirmed</span>
											</tag:if>
											<tag:if test="${booking.bookingStatus == 2}">
												<span class="badge bg-success rounded-1">Complete</span>
											</tag:if>
										</td>
										<td>
											<div class="dropdown">
												<button type="button" class="btn btn-sm btn-outline-secondary dropdown-toggle" data-bs-toggle="dropdown" data-bs-target="#actionBtn" aria-expanded="false">Action</button>
												<div class="dropdown-menu" id="actionBtn">
													<a href="viewbooking?bookingId=<tag:out value='${booking.bookingId}'></tag:out>" class="dropdown-item" target="iframe"><i class="bi bi-eye"></i>&nbsp;Read More</a>
												</div>
											</div>
										</td>
									</tr>
								</tag:forEach>
							</tbody>
						</table>
					</tag:if>
					<tag:if test="${listSize == 0}">
						<div class="m-auto message-section">
							<img alt="Message status image" src="images/f1.png" class="img-responsive d-block mx-auto">
							<p class="text-center text-muted"><strong>No Records Found!</strong><br>Looks like you have no new bookings.</p>
						</div>
					</tag:if>
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