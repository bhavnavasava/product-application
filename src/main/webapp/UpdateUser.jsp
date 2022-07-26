<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>	
	<%@page import="com.bean.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update User</title>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
	
	<% UserBean user=(UserBean)request.getAttribute("user");
	String genderValue=user.getGender(); 
	%>

	<h2>Update user </h2>

	<form method="post" action="UpdateUserController">
	<input type="hidden" value="${user.userId}" name="userId">
		FirstName : <input type="text" name="firstName"
			value="${user.firstName}" /> ${firstNameError} <br> <Br>
		LastName : <input type="text" name="lastName" value="${user.lastName}"/>${lastNameError}<br> <br>
		
		Email : <input type="text" name="email" value="${user.email}" disabled="disabled" />
		Password : <input type="password" name="password"  value="${user.password}" />${passwordError}<br><br>
		 Gender : 		 
		 Male <input type="radio" name="gender" value="male"   <%=genderValue!=null&&genderValue.equals("male")?"checked":"" %> />				
		Female:<input type="radio" name="gender" value="female" <%=genderValue!=null&&genderValue.equals("female")?"checked":"" %>/> 
		<br><br>
		<br> <input type="submit" value="Update" />
	</form>



	
	
	
	</body>
	</html>