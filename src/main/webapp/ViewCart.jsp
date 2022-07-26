<%@page import="com.bean.CartProductBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.bean.CartBean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Cart</title>
</head>
<body>

	<jsp:include page="UserMenu.jsp"></jsp:include>
	<%
	ArrayList<CartProductBean> carts = (ArrayList<CartProductBean>) request.getAttribute("carts");
	%>


	<div class="container-fluide">
		<div class="row">
			<div class="col-md-2"></div>

			<div class="col-md-8">
				<table class="display table table-hover" id="carts">
					<thead class="thead-dark">
						<tr>
							<th>ProductId</th>
							<th>ProductName</th>
							<th>Price</th>
							<th>Action</th>


						</tr>
					</thead>
					<tbody>
						<%
						int total=0;
						for (CartProductBean cart : carts) {
							total=total+cart.getPrice();
						%>
						<tr>

							<td><%=cart.getpId()%></td>
							<td><%=cart.getpName()%></td>
							<td><%=cart.getPrice()%></td>
							<td><a
								href="DeleteCartController?cartid=<%=cart.getCartId()%>">Delete</a>
							</td>

							<%
							}
							%>
						</tr>
					</tbody>

				</table>
				<h2> Total=<%=total %></h2>
				<br>
				<a href="CheckoutController" class="btn btn-success">CheckOut-Place Order</a>

				<div class="row">${message }</div>

			</div>
		</div>
	</div>
	
</body>
</html>