<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form action="passwordreset" modelAttribute="forgetPasswordDetails">
Please Enter OTP:<form:input path="otp" /><br/>
Enter a new Password:
  <form:password path="password" />
<input type="submit" value="submit">

</form:form>
</body>
</html>