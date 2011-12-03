<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - Edit Test Case</title>
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
			<li><span><a href="newtestcase.html?testplanID=${testplan.id}">New Test Case</a></span></li>	
			<li><span class="current">Edit Test Case</span></li>		
			<li><span><a href="enterresults.html?testplanID=${testplan.id}">Enter Results</a></span></li>
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
		<a href="enterresults.html?testplanID=${testplan.id}">${testplan.testplanName}</a>  -  ${testplan.testerName} :
		<BR /> <BR />	
		<form method="post">
		<input hidden="true" name="testcaseID" value="${testcase.id}">		
		<table border="0" align="center">
		<tr>
			<td valign="top">
				<!-- Left side -->
				<table border="0">
				<tr>
					<td colspan="2">
						Test Title:<br>
						<input type="text" name="testcasename" size="65" value="${testcase.testcasename}">	
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Test Summary:<br>
						<textarea name="testcasesummary" cols="50" rows="3" >${testcase.testcasesummary}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Test Preconditions:<br>
						<textarea name="testcasepre" cols="50" rows="3">${testcase.testcasepre}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Test Pass conditions:<br>
						<textarea name="testcasepass" cols="50" rows="3">${testcase.testcasepass}</textarea>
					</td>
				</tr>
				<tr>
					<td width="50%">
						Test OS:<br>
						<select name="testcaseOS">
							<option selected="selected" value="${testcase.testcaseOS}">${testcase.testcaseOS}</option>
							<c:if test="${testcase.testcaseOS != 'Windows x32'}">							
								<option value="Windows x32">Windows x32</option>
							</c:if>
							<c:if test="${testcase.testcaseOS != 'Windows x64'}">							
								<option value="Windows x64">Windows x64</option>
							</c:if>
							<c:if test="${testcase.testcaseOS != 'Linux x32'}">							
								<option value="Linux x32">Linux x32</option>
							</c:if>
							<c:if test="${testcase.testcaseOS != 'Linux x64'}">							
								<option value="Linux x64">Linux x64</option>
							</c:if>							
						</select>						
					</td>	
					<td width="50%" align="right">
						Testplan:<br>
						<select name="testplanID_changed" >
						<c:forEach var="testplan2" items="${testplans}" varStatus="index">			
						<c:choose>
						      <c:when test="${testplan2.id eq testcase.testPlanID}">
						    	 <option selected="selected" value="${testplan2.id}">${testplan2.testplanName}</option>			     
						      </c:when>			
						      <c:otherwise>
						      	<option value="${testplan2.id}">${testplan2.testplanName}</option>
						      </c:otherwise>
						 </c:choose>
						 </c:forEach>
						 </select>		
					</td>		
				</tr>				
				</table>
			</td>
			<td>
				<table border="0">
				<tr>
					<td>
						Test Steps:<br>
						<textarea name="testcasesteps" cols="50" rows="20">${testcase.testcasesteps}</textarea>				
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