<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - View Test Plans</title>
		<link rel="stylesheet" href="../css/style.css" type="text/css" media="screen" />
	</head>
<body>
<div id="container">
	<div id="header">
		<a href="../index.html"><img src="../images/title.png"></a>				
	</div>
	<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
		<div id="loggedin">
			<security:authentication property="principal.username"/>
		</div>
	</security:authorize>
	<div >
		<ul id="menu">				
			<li><span><a href="../index.html">Home</a></span></li>
			<li><span class="current">Test Plans</span></li>			
			<security:authorize ifAllGranted="ROLE_ADMIN">	
			<li><span><a href="../testconfig/newtestplan.html">New Test Plan</a></span></li>	
			</security:authorize>	
			<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">	
			<li><span><a href="../j_spring_security_logout">Logout</a></span></li>	
			</security:authorize>					
		</ul>
	</div>
	<div id="content">		
		<c:if test="${fn:length(errormessage) > 0}"> 				
		 	<font color="red">${errormessage}</font> 
		 	<hr>
		</c:if>
		<c:if test="${fn:length(sucessmessage) > 0}">  				
		 	<font color="green">${sucessmessage}</font> 
		 	<hr>
		</c:if>		
		<font color="#707070">List of current Test Plans (total: ${fn:length(testplans)}) :</font>
		<BR/>
		<font size="1" color="#7F94B0">note : click test plan to enter results </font>
		<BR/><BR/>
		
		<table border="0" cellpadding="4" cellspacing="3" align="center">			
			<tr style="background-color:#7F94B0">
				<security:authorize ifAllGranted="ROLE_ADMIN">				
				<td style="background-color:#ffffff">					
				</td>	
				</security:authorize>				
				<td>
					<font color="#ffffff">ID</font>
				</td>
				<td width="300">
					<font color="#ffffff" >Test Plan Name</font>
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
					<security:authorize ifAllGranted="ROLE_ADMIN">	
					<td>									
						<form name="edittestplan" action="../testconfig/edittestplan.html" method="get">
							<input type="hidden" name="testplanID" value="${testplan.id}">							
							<input type="image" src="../images/edit.png" alt="Edit Testplan" title="Edit Testplan" width="15">
						</form>							
					</td>	
					</security:authorize>				
					<td valign="top">
						<font color="#707070">${testplan.id}</font>
					</td>
					<td  valign="top">		
						<a href="../testconfig/enterresults.html?testplanID=${testplan.id}">${testplan.testplanName}</a> 										
					</td>
					<td  valign="top">
						<font color="#707070">${testplan.testerName}</font>
					</td>					
					<td  valign="top" align="center">
						<font color="lightblue">${testplan.totalTests}</font>
					</td>
					<td   valign="top" align="center">
						<font color="navy">${testplan.totalNotRun}</font>
					</td>
					<td  valign="top" align="center">
						<font color="green">${testplan.totalPassed}</font>
					</td>
					<td  valign="top" align="center">
						<font color="red">${testplan.totalFailed}</font>
					</td>
					<td  valign="top" align="center">
						<font color="orange">${testplan.totalInProgress}</font>
					</td>
					<td  valign="top" align="center">
						<font color="purple">${testplan.totaldeferred}</font>
					</td>
					<td  valign="top" align="center">
						<font color="#505050">${testplan.totalBlocked}</font>
					</td>
					<security:authorize ifAllGranted="ROLE_ADMIN">			
					<td>
						<form name="deletetestplan" action="../testconfig/deletetestplan.html" method="get">
							<input type="hidden" name="testplanID" value="${testplan.id}">							
							<input type="image" src="../images/delete.png" alt="Delete Testplan" title="Delete Testplan"  onclick="return confirm('Are you sure you want to delete Test Plan (${testplan.testplanName}) ?')">
						</form>		
					</td>	
					</security:authorize>
				</tr>	
			</c:forEach>
			<security:authorize ifAllGranted="ROLE_ADMIN">	
			<tr>
				<td>
				</td>
				<td colspan="5">
					<a href="../testconfig/newtestplan.html"><img src="../images/plus.png" width="15"> <font color="#707070">New Test Plan</font></a>
				</td>
			</tr>
			</security:authorize>
		</table>
	</div>
	<div id="footer">
		Copyright © Testcenter, 2011
	</div>
</div>	
<div id="preload">
	<img src="../images/title.png">
	<img src="../images/delete.png" alt="Delete Test Case" title="Delete Test Case" /> 
 	<img src="../images/edit.png" alt="Edit Test Case" title="Edit Test Case" />    		
</div>
</body>
</html>