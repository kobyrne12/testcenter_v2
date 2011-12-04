<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - New Test Case</title>
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
			<li><span><a href="../viewtests/viewtestplan.html">Test Plans</a></span></li>
			<li><span><a href="newtestplan.html">New Test Plan</a></span></li>	
			<li><span class="current">New Test Case</span></li>		
			<li><span><a href="enterresults.html?testplanID=${testplanID}">Enter Results</a></span></li>
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
		<a href="enterresults.html?testplanID=${testplanID}">${testplanName}</a>  -  ${testplanTester} :
		<BR /> <BR />	
		<form method="post">
		<input type="hidden" name="testplanID" value="${testplanID}">		
		<table  width="100%" border="0" align="center">
		<tr>
			<td valign="top" width="50%">
				<!-- Left side -->
				<table border="0" width="100%">
				<tr>
					<td>
						Test Title:<br>
						<input class="testcasetitle" type="text" name="testcasename" >	
					</td>
				</tr>
				<tr>
					<td >
						Test Summary:<br>
						<textarea name="testcasesummary"  class="testcase"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Test Preconditions:<br>
						<textarea name="testcasepre" class="testcase"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Test Pass conditions:<br>
						<textarea name="testcasepass" class="testcase"></textarea>
					</td>
				</tr>
				<tr>
					<td width="100%">
						Test OS:<br>
						<select name="testcaseOS">													
							<option value="Windows x32">Windows x32</option>													
							<option value="Windows x64">Windows x64</option>													
							<option value="Linux x32">Linux x32</option>											
							<option value="Linux x64">Linux x64</option>												
						</select>						
					</td>
								
				</tr>				
				</table>
			</td>
			<td width="50%">
				<!-- Right side -->
				<table border="0" width="100%">
				<tr>
					<td>
						Test Steps:<br>
						<textarea name="testcasesteps" class="testcasesteps"></textarea>				
					</td>
				</tr>
				<tr>
					<td align="right">
						<input type="submit">	
					</td>
				</tr>				
				</table>						
			</td>
		</tr>
		</table>
	</form>		

	</div>
	<div id="footer">
		Copyright © Testcenter, 2011
	</div>
</div>	
	<div id="preload">
		<img src="../images/title.png">				
	</div>
	</body>
</html>