<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - New Test Plan</title>
		<style type="text/css">
		body {
			font-family: Trebuchet MS, Verdana;
			font-size: 12px;
		}
		</style>
	</head>
	<body>		
		<a href="index.html"><img src="images/title.png"></a>
		<a href="j_spring_security_logout">Logout <security:authentication property="principal.username"/></a>
		<hr>
		<a href="newtestcase.html">New Test Case</a> |		
		<a href="viewtestplan.html">View Test plans</a> 
		<hr>		
		 <c:if test="${message}"> <font color="red">${message}</font> <hr></c:if>
		<form method="post">
			<input hidden="true" name="testername" value="<security:authentication property="principal.username"/>">
				
			Test Plan:<br>
			<textarea name="testplanName" cols="50" rows="1"></textarea>			
			<br />			
			<input type="submit">
		</form>
		<hr>
	
	</body>
</html>