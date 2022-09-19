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
		<link rel="stylesheet" href="css/default.css" type="text/css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
		<title>GoCheeta Control Panel</title>
	</head>
	<body>
		<div class="container-fluid form-content">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-xl-12 col-lg-12 page-header">
					<div class="bg-white shadow-sm p-2">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb mt-2" style="font-size: 15px;">
								<li class="breadcrumb-item"><a href="dashboard.jsp" target="iframe" class="home-btn"><i class="bi bi-house-door"></i></a></li>
								<tag:if test="${driverId == null}">
									<li class="breadcrumb-item active" aria-current="page">Add Branch</li>
								</tag:if>
								<tag:if test="${driverId != null}">
									<li class="breadcrumb-item active" aria-current="page">Edit Branch</li>
								</tag:if>
							</ol>
						</nav>
						<a href="javascript:history.go(-1)" class="btn btn-sm btn-outline-secondary float-end back-btn" title="Back" style="margin-top: -43px;"><i class="bi bi-chevron-double-left"></i>&nbsp;<span>Back</span></a>
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mt-2 form-content-inner">
					<div class="bg-white shadow-sm p-2">
						<tag:choose>
							<tag:when test="${successMessage != null}">
								<div class="alert alert-success d-flex p-0 rounded-0 shadow-sm border-0" style="background: #ccffcc;">
									<i class="bi bi-check-circle p-3 bg-success text-white"></i>
									<p class="ps-2 mt-3 text-success" style="font-size: 14px;"><tag:out value="${successMessage}"></tag:out></p>
								</div>
							</tag:when>
							<tag:when test="${message != null}">
								<div class="alert alert-error d-flex p-0 rounded-0 shadow-sm border-0" style="background: #ffcccc;">
									<i class="bi bi-exclamation-triangle p-3 bg-danger text-white"></i>
									<p class="ps-2 mt-3 text-danger" style="font-size: 14px;"><tag:out value="${message}"></tag:out></p>
								</div>
							</tag:when>
						</tag:choose>
						<tag:if test="${driverId == null}">
						<form action="newdriver?type=submit" method="post">
						</tag:if>
						<tag:if test="${driverId != null}">
							<form action="driver?type=update" method="post">
						</tag:if>
							<div class="form-group row mb-2">
								<label for="branchId" class="form-label col-10 col-sm-10 col-md-3 col-lg-3 col-xl-3">Branch <span class="text-danger">*</span></label>
								<label class="col-2 col-sm-2 col-md-1 col-lg-1 col-xl-1">:</label>
								<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
									<select name="branchId" id="branchId" class="form-select form-select-sm">
										<option value="0" selected>~~ Select Branch ~~</option>
										<tag:forEach var="branch" items="${branchList}">
											<option value="<tag:out value='${branch.branchId}'></tag:out>" <tag:if test='${branch.branchId == driver.branchId}'>selected</tag:if>><tag:out value="${branch.branchName}"></tag:out></option>
										</tag:forEach>
									</select>
									<span class="text-danger" style="font-size: 14px;font-weight: 400;"><tag:out value="${branchIdError}"></tag:out></span>
								</div>
							</div>
							<div class="form-group row mb-2">
								<label for="vehicleId" class="form-label col-10 col-sm-10 col-md-3 col-lg-3 col-xl-3">Vehicle <span class="text-danger">*</span></label>
								<label class="col-2 col-sm-2 col-md-1 col-lg-1 col-xl-1">:</label>
								<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
									<select name="vehicleId" id="vehicleId" class="form-select form-select-sm">
										<option value="0" selected>~~ Select Vehicle ~~</option>
										<tag:forEach var="vehicle" items="${vehicleList}">
											<option value="<tag:out value='${vehicle.vehicleId}'></tag:out>" <tag:if test="${vehicle.vehicleId == driver.vehicleId}">selected</tag:if>><tag:out value="${vehicle.vehicleNo}"></tag:out></option>
										</tag:forEach>
									</select>
									<span class="text-danger" style="font-size: 14px;font-weight: 400;"><tag:out value="${vehicleIdError}"></tag:out></span>
								</div>
							</div>
							<div class="form-group row mb-2">
								<label for="firstName" class="form-label col-10 col-sm-10 col-md-3 col-lg-3 col-xl-3">First Name <span class="text-danger">*</span></label>
								<label class="col-2 col-sm-2 col-md-1 col-lg-1 col-xl-1">:</label>
								<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
									<input type="text" name="firstName" id="firstName" class="form-control form-control-sm" autocomplete="off" value="<tag:if test='${driverId != null}'><tag:out value='${driver.firstName}'></tag:out></tag:if>" />
									<span class="text-danger" style="font-size: 14px;font-weight: 400;"><tag:out value="${firstNameError}"></tag:out></span>
								</div>
							</div>
							<div class="form-group row mb-2">
								<label for="lastName" class="form-label col-10 col-sm-10 col-md-3 col-lg-3 col-xl-3">Last Name <span class="text-danger">*</span></label>
								<label class="col-2 col-sm-2 col-md-1 col-lg-1 col-xl-1">:</label>
								<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
									<input type="text" name="lastName" id="lastName" class="form-control form-control-sm" autocomplete="off" value="<tag:if test='${driverId != null}'><tag:out value='${driver.lastName}'></tag:out></tag:if>" />
									<span class="text-danger" style="font-size: 14px;font-weight: 400;"><tag:out value="${lastNameError}"></tag:out></span>
								</div>
							</div>
							<div class="form-group row mb-2">
								<label for="nic" class="form-label col-10 col-sm-10 col-md-3 col-lg-3 col-xl-3">NIC <span class="text-danger">*</span></label>
								<label class="col-2 col-sm-2 col-md-1 col-lg-1 col-xl-1">:</label>
								<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
									<input type="text" name="nic" id="nic" class="form-control form-control-sm" autocomplete="off" value="<tag:if test='${driverId != null}'><tag:out value='${driver.nic}'></tag:out></tag:if>" />
									<span class="text-danger" style="font-size: 14px;font-weight: 400;"><tag:out value="${nicError}"></tag:out></span>
								</div>
							</div>
							<div class="form-group row mb-2">
								<label for="contactNo" class="form-label col-10 col-sm-10 col-md-3 col-lg-3 col-xl-3">Contact No <span class="text-danger">*</span></label>
								<label class="col-2 col-sm-2 col-md-1 col-lg-1 col-xl-1">:</label>
								<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
									<input type="text" name="contactNo" id="contactNo" class="form-control form-control-sm" autocomplete="off" value="<tag:if test='${driverId != null}'><tag:out value='${driver.contactNo}'></tag:out></tag:if>" />
									<tag:if test="${driverId != null}">
										<input type="hidden" name="driverId" value="<tag:out value='${driver.driverId}'></tag:out>">
									</tag:if>
									<span class="text-danger" style="font-size: 14px;font-weight: 400;"><tag:out value="${contactNoError}"></tag:out></span>
								</div>
							</div>
							<div class="offset-md-4 col-md-4">
								<tag:if test="${driverId == null}">
									<button type="submit" name="submit" value="Submit" class="btn btn-sm save-btn"><i class="bi bi-save"></i>&nbsp;Submit</button>
								</tag:if>
								<tag:if test="${driverId != null}">
									<button type="submit" name="update" value="Update" class="btn btn-sm save-btn"><i class="bi bi-save"></i>&nbsp;Update</button>
								</tag:if>
							</div>
						</form>
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