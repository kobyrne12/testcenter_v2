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
			<a href="testconfig.html">Tests</a> |	
			<a href="newtestplan.html">New Test plan</a>  | 			
			<a href="viewtestplan.html">Test plans</a>	|
			<a href="newtestcase.html?testplanID=${testplanID}">New Test Case</a> |			
		<hr>
		<!-- 
		<font size="1" color="blue">
			<a href="index.html">Home</a> >
			<a href="testconfig.html">Test Config</a> >
			<a href="viewtestplan.html">Test plans</a> >
			Enter Results
		</font>
		<br>
		-->
		<c:if test="${fn:length(errormessage) > 0}">  	
			<hr>	
		 		<font color="red">${errormessage}</font> 
		 	<hr>
		</c:if>
			${testplanTester} -  ${testplanName} (total: ${fn:length(testcases)}) :
		<table border="0">
		<c:forEach var="testcase" items="${testcases}" varStatus="index">
		<tr>
			<td>${testcase.id}</td>					
			<td width="5">
			</td>
			<td >${testcase.testcasename}</td>				
			<td width="30">
			</td>
			<td><font color="#808080">${testcase.testcaseOS}</font></td>	
			<td width="5">
			</td>
			<td>
				<!-- Set states table -->
				<table>
				<tr>
				<td>
				<c:choose>
					<c:when test="${testcase.notrun}">
						<img src="images/NA.png">
					</c:when>
					<c:otherwise>
						<form name="notrun" action="notrun.html" method="get">
							<input hidden="true" name="id" value="${testcase.id}">
							<input hidden="true" name="testplanID" value="${testplanID}">								
							<INPUT type="image" src="images/NA_blank.PNG" alt="Not Run" title="Not Run">
						</form>
					</c:otherwise>
				</c:choose>	
				</td>
				<td>
				<c:choose>
					<c:when test="${testcase.passed}">
						<img src="images/ok.png" alt="Passed" title="Passed" >
					</c:when>
					<c:otherwise>
						<form name="passed" action="passed.html" method="get">
							<input hidden="true" name="id" value="${testcase.id}">
							<input hidden="true" name="testplanID" value="${testplanID}">
							<INPUT type="image" src="images/ok_blank.PNG" alt="Passed" title="Passed">
						</form>
					</c:otherwise>
				</c:choose>	
				</td>
				<td>
				<c:choose>
					<c:when test="${testcase.failed}">
						<img src="images/no.png" alt="Failed" title="Failed">
					</c:when>
					<c:otherwise>
						<form name="failed" action="failed.html" method="get">
							<input hidden="true" name="id" value="${testcase.id}">
							<input hidden="true" name="testplanID" value="${testplanID}">
							<INPUT type="image" src="images/no_blank.PNG" alt="Failed" title="Failed">
						</form>
					</c:otherwise>
				</c:choose>	
				</td>
				<td>
				<c:choose>
					<c:when test="${testcase.inprogress}">
						<img src="images/IP.png">
					</c:when>
					<c:otherwise>
						<form name="inprogress" action="inprogress.html" method="get">
							<input hidden="true" name="id" value="${testcase.id}">
							<input hidden="true" name="testplanID" value="${testplanID}">
							<INPUT type="image" src="images/IP_blank.PNG" alt="In Progress" title="In Progress">
						</form>
					</c:otherwise>
				</c:choose>
				</td>
				<td>
				<c:choose>
					<c:when test="${testcase.deferred}">
						<img src="images/def.png">
					</c:when>
					<c:otherwise>
						<form name="deferred" action="deferred.html" method="get">
							<input hidden="true" name="id" value="${testcase.id}">
							<input hidden="true" name="testplanID" value="${testplanID}">
							<INPUT type="image" src="images/def_blank.PNG" alt="Deferred" title="Deferred">
						</form>
					</c:otherwise>
				</c:choose>
				</td>
				<td>	
				<c:choose>
					<c:when test="${testcase.blocked}">
						<img src="images/block.png">
					</c:when>
					<c:otherwise>
						<form name="blocked" action="blocked.html" method="get">
							<input hidden="true" name="id" value="${testcase.id}">
							<input hidden="true" name="testplanID" value="${testplanID}">
							<INPUT type="image" src="images/block_blank.png" alt="Blocked" title="Blocked">
						</form>
					</c:otherwise>
				</c:choose>						
				</td>
																		
				</tr>
				</table>
				<!-- End of Set states table -->
			</td>	
			<td width="15">
			</td>
			<td>	
				<form name="deletetestcase" action="deletetestcase.html" method="get">
					<input hidden="true" name="id" value="${testcase.id}">
					<input hidden="true" name="testplanID" value="${testplanID}">
					<INPUT type="image" src="images/delete.png" alt="Delete Test" title="Delete Test"  onclick="return confirm('Are you sure you want to delete Test Case? \n\n ${testcase.id} : ${testcase.testcasename}')">
				</form>										
			</td>						
		</tr>
		</c:forEach>
		</table>		
		<hr>
		<!-- <td>${index.count}.</td>
					<td>
					
						<c:if test="${testcase.notrun }"> <font color="blue">Not Run</font></c:if>
						<c:if test="${testcase.passed }"> <font color="green">Passed</font></c:if>
						<c:if test="${testcase.failed }"> <font color="red">Failed</font></c:if>
						<c:if test="${testcase.inprogress }"> <font color="orange">In progress</font></c:if>
						<c:if test="${testcase.deferred }"> <font color="purple">Deferred</font></c:if>
						<c:if test="${testcase.blocked }"> <font color="black">Blocked</font></c:if>
					</td>
					-->
	</body>
</html>