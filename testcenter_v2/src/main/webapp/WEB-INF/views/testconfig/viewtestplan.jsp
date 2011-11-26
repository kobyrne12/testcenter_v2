<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - View Test Plans</title>
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
		<a href="edittestcases.html">Edit Test Cases</a>
		<hr>
			List of current Test Plans (total: ${fn:length(testplans)}) :
		<table  cellpadding="5" cellspacing="3">			
			<tr style="background-color:#7F94B0">
				<!--  <td>${index.count}.</td>
				<td width="10"></td>-->
				<td>
					<font color="#ffffff">ID</font>
				</td>
				<td>
					<font color="#ffffff">Testplan Name</font>
				</td>
				<td>
					<font color="#ffffff">Tester</font>
				</td>					
				<td>
					<font color="#ffffff">Total</font>
				</td>
				<td>
					<font color="#ffffff">NotRun</font>
				</td>
				<td>
					<font color="#ffffff">Passed</font>
				</td>
				<td>
					<font color="#ffffff">Failed</font>
				</td>
				<td>
					<font color="#ffffff">Inprogress</font>
				</td>
				<td>
					<font color="#ffffff">Deferred</font>
				</td>
				<td>
					<font color="#ffffff">Blocked</font>
				</td>
			</tr>	
		<c:forEach var="testplan" items="${testplans}" varStatus="index">
			<tr>
				<!--  <td>${index.count}.</td>
				<td width="10"></td>-->
				<td>
					<font color="#707070">${testplan.id}</font>
				</td>
				<td>
					
					<form action="edittestcases.html" method="get">
						<input hidden="true" name="testplanID" value="${testplan.id}">
						<input type="submit" value="${testplan.testplanName}">
					</form>					
					
				</td>
				<td>
					<font color="#707070">${testplan.testerName}</font>
				</td>					
				<td align="center">
					<font color="lightblue">${testplan.totalTests}</font>
				</td>
				<td align="center">
					<font color="navy">${testplan.totalNotRun}</font>
				</td>
				<td align="center">
					<font color="green">${testplan.totalPassed}</font>
				</td>
				<td align="center">
					<font color="red">${testplan.totalFailed}</font>
				</td>
				<td align="center">
					<font color="orange">${testplan.totalInProgress}</font>
				</td>
				<td align="center">
					<font color="purple">${testplan.totaldeferred}</font>
				</td>
				<td align="center">
					<font color="#505050">${testplan.totalBlocked}</font>
				</td>			
			</tr>	
		</c:forEach>
		</table>
		
		<hr>
	</body>
</html>