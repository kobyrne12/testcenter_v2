<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter</title>
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
			<a href="index.html">Home</a> |
			<a href="newtestplan.html">Reports</a> |
			<a href="newtestplan.html">New Test Plan</a> |			
			<a href="viewtestplan.html">View Test plans</a> 
		<hr>
		<c:if test="${fn:length(errormessage) > 0}">  		
		 	<font color="red">${errormessage}</font> <hr>
		</c:if>
			<!-- Button menu  -->
	</body>
</html>