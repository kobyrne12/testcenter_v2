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
			<a href="index.html">Home</a> |
			<a href="newtestplan.html">Reports</a> |
			<a href="testconfig.html">Tests</a> |	
			<a href="newtestplan.html">New Test plan</a>  | 			
			<a href="viewtestplan.html">Test plans</a>	|
			<a href="edittestcases.html">Enter Results</a>				
		<hr>
		<!-- 
		<font size="1" color="blue">
			<a href="index.html">Home</a> >
			<a href="testconfig.html">Test Config</a> >
			<a href="viewtestplan.html">Test plans</a> >
			New Test Case
		</font>
		<br>
		-->
		<c:if test="${fn:length(errormessage) > 0}">  	
			<hr>	
		 		<font color="red">${errormessage}</font> 
		 	<hr>
		</c:if>
		<form method="post">
			${testplanName}		
			<input hidden="true" name="testplanID" value="${testplanID}">				
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