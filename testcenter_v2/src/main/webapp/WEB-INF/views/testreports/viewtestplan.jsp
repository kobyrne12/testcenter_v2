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
		<a href="newtestplan.html">New Test plan</a> | <a href="newtestcase.html">New Test Case</a> | 
		<a href="viewtestplan.html">View plan</a>
		<hr>
			List of current Tests (total: ${fn:length(testcases)}) :
		<table>
		<c:forEach var="testcase" items="${testcases}" varStatus="index">
		<tr>
		<td>
			<table>			
				<tr>
					<td>${index.count}.</td>
					<td width="10"></td>
					<td>
					
						<c:if test="${testcase.notrun }"> <font color="blue">Not Run</font></c:if>
						<c:if test="${testcase.passed }"> <font color="green">Passed</font></c:if>
						<c:if test="${testcase.failed }"> <font color="red">Failed</font></c:if>
						<c:if test="${testcase.inprogress }"> <font color="orange">In progress</font></c:if>
						<c:if test="${testcase.deferred }"> <font color="purple">Deferred</font></c:if>
						<c:if test="${testcase.blocked }"> <font color="black">Blocked</font></c:if>
					</td>
				</tr>
				
				<tr>
					<td colspan="3">
						<font color="blue"><u>Test Plan</u></font>
					</td>									
				</tr>
				<tr>
					<td colspan="2">
					</td>
					<td>${testcase.testplan}</td>					
				</tr>
				<tr>
					<td colspan="3">
						<font color="blue"><u>Test case name</u></font>
					</td>									
				</tr>
				<tr>
					<td colspan="2">
					</td>
					<td>${testcase.testcasename}</td>					
				</tr>
				
				<tr>
					<td colspan="3">
						<font color="blue"><u>Test case Summary</u></font>
					</td>									
				</tr>
				<tr>
					<td colspan="2">
					</td>
					<td>${testcase.testcasesummary}</td>					
				</tr>
				
				<tr>
					<td colspan="3">
						<font color="blue"><u>Test case Precondition</u></font>
					</td>									
				</tr>
				<tr>
					<td colspan="2">
					</td>
					<td>${testcase.testcasepre}</td>					
				</tr>
				
				<tr>
					<td colspan="3">
						<font color="blue"><u>Test case Steps</u></font>
					</td>									
				</tr>
				<tr>
					<td colspan="2">
					</td>
					<td>${testcase.testcasesteps}</td>					
				</tr>
				
				<tr>
					<td colspan="3">
						<font color="blue"><u>Test case Pass condition</u></font>
					</td>									
				</tr>
				<tr>
					<td colspan="2">
					</td>
					<td>${testcase.testcasepass}</td>					
				</tr>
				<tr>
					<td colspan="3">
						<font color="blue"><u>Test case OS</u></font>
					</td>									
				</tr>
				<tr>
					<td colspan="2">
					</td>
					<td>${testcase.testcaseOS}</td>					
				</tr>
				
				
			
			</table>
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
								<INPUT type="image" src="images/block_blank.png" alt="Blocked" title="Blocked">
							</form>
						</c:otherwise>
					</c:choose>						
					</td>	
					<td>	
						<form name="delete" action="delete.html" method="get">
							<input hidden="true" name="id" value="${testcase.id}">
							
							<INPUT type="image" src="images/delete.png" alt="Delete Test" title="Delete Test"  onclick="return confirm('Are you sure you want to delete Test Case (${testcase.id}) ?')">
						</form>										
					</td>	
					<td>	
					here : ${testcase.testplan}
					</td>								
					</tr>
					</table>
					<!-- End of Set states table -->
				
			</td>
		</tr>
		</c:forEach>
		</table>
		
		<hr>
	</body>
</html>