<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery.min.js"></script>
<script src="Components/payment.js"></script>
<script src="Components/jquery.min.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div class="col-lg-12">
			<div class="card m-b-32">
				<div class="card-body">

					<form id="formUser" name="formUser" method="post"
						action="index.jsp">

						<label>Customer Name</label> <input id="customer_name"
							name="customer_name" type="text"
							class="form-control form-control-sm">
							<br> 
							<label>Account NUmber</label><input id="account_number"
							name="account_number" type="number"
							class="form-control form-control-sm">
						
							<br><label>Date</label> <input id="date" name="date"
							type="date" class="form-control form-control-sm"> 
							
							<br><label>Payment Type</label>
							<select id="payment_type" name="payment_type" class="form-control form-control-sm">
							<option value="credi-card">Credit Card</option>
							<option value="debit-card">Debit Card</option>
							<option value="cash">Cash</option>
							</select>

							<br><label>Total Price</label> <input id="total_price"
							name="total_price" type="number"
							class="form-control form-control-sm">
							
							 <br>
							 <input id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary"> <input type="hidden"
							id="hididSave" name="hididSave" value="">
					</form>
					<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
					
					<div id="divUserGrid">
						<%
						Payment empObj = new Payment();
															out.print(empObj.readPayments());
						%>
					</div>
				</div>
</div>
</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>