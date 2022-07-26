<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@page import="com.bean.OrderDetailProductBean"%>
    	<%@page import="java.util.ArrayList"%>
    	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Details</title>
</head>
<body>
<jsp:include page="UserMenu.jsp"></jsp:include>

	<%
		ArrayList<OrderDetailProductBean> orderDetail = (ArrayList<OrderDetailProductBean>) request.getAttribute("orderDetail");
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<h2 align="center">Order Detail</h2>

				<table class="display table table-hover" id="carts">
					<thead class="thead-light">
						<tr>
							<th>Name</th>
							<th>Price</th>
							<th>Image</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (OrderDetailProductBean orderDetails : orderDetail) {
						%>
						<tr>
							<td><%=orderDetails.getpName()%></td>
							<td><%=orderDetails.getPrice()%></td>
							<td><img height="100px" width="100px" src="<%=orderDetails.getImgUrl()%>" /></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>

</body>
</html>