<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
		<link rel="stylesheet" href="css/dashboard.css" type="text/css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js" type="text/javascript"></script>
		<script src="plugins/owl.carousel.min.js" type="text/javascript"></script>
		<script src="plugins/waypoints.min.js" type="text/javascript"></script>
		<script src="plugins/jquery.counterup.min.js" type="text/javascript"></script>
		<title>GoCheeta Control Panel</title>
	</head>
	<body>
		<div class="container-fluid data-box-section">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-6 col-lg-3 col-xl-3 data-box p-2">
					<div class="data-box-inner data-box-1 p-1 shadow-sm rounded-1 position-relative">
						<p>Total Sales</p>
						<h3>Rs.<span class="counter">100.00</span></h3>
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-6 col-lg-3 col-xl-3 data-box p-2">
					<div class="data-box-inner data-box-2 p-1 shadow-sm rounded-1 position-relative">
						<p>Total Customers</p>
						<h3 class="counter">25</h3>
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-6 col-lg-3 col-xl-3 data-box p-2">
					<div class="data-box-inner data-box-3 p-1 shadow-sm rounded-1 position-relative">
						<p>Total Drivers</p>
						<h3 class="counter">10</h3>
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-6 col-lg-3 col-xl-3 data-box p-2">
					<div class="data-box-inner data-box-4 p-1 shadow-sm rounded-1 position-relative">
						<p>Total Vehicle</p>
						<h3 class="counter">10</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="conatiner-fluid sales-chart">
			<div class="row">
				<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mt-2 sales-chart-content">
					<div class="bg-white shadow-sm rounded-1">
						1
					</div>
				</div>
			</div>
		</div>
		<script src="js/counter.js" type="text/javascript"></script>
	</body>
</html>