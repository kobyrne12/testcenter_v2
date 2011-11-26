<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - New Test Case</title>
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
		<a href="newtestplan.html">New Test plan</a> | <a href="newtestcase.html">New Test Case</a> | 
		<a href="viewtestplan.html">View plan</a>
		<hr>
		<form method="post">
			Test Plan:<br>
			<textarea name="testplan" cols="50" rows="1"></textarea>
			<br>
			Test Title:<br>
			<textarea name="testcasename" cols="50" rows="1"></textarea>
			<br>
			Test Summary:<br>
			<textarea name="testcasesummary" cols="50" rows="3"></textarea>
			<br>
			Test Preconditions:<br>
			<textarea name="testcasepre" cols="50" rows="3"></textarea>
			<br>
			Test Steps:<br>
			<textarea name="testcasesteps" cols="50" rows="6"></textarea>
			<br>
			Test Pass conditions:<br>
			<textarea name="testcasepass" cols="50" rows="3"></textarea>
			<br>
			Test OS:<br>
			<textarea name="testcaseOS" cols="50" rows="3"></textarea>
			<br>
			<input type="submit">
		</form>
		<hr>
	
	</body>
</html>