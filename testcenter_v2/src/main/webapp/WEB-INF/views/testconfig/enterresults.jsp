<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - Enter Results</title>
		<link rel="stylesheet" href="../css/style.css" type="text/css" media="screen" />
		<script language="JavaScript">
			function loadTestDetails(TD_ID)
			{
				var t = "TestDetails_"+TD_ID;
				var i = "DropDownImg_"+TD_ID;
				var c = "TestDetailsCell_"+TD_ID;
				
				var f = document.getElementById(t);
				if(f)
				{ 
					if (document.getElementById(t).style.display == "")
					{
						document.getElementById(t).style.display = 'none';	
						document.getElementById(i).src = '../images/down.png';	
						document.getElementById(c).style.color = '#707070';						
					}
					else 
					{
						document.getElementById(t).style.display = '';	
						document.getElementById(i).src = '../images/up.png';	
						document.getElementById(c).style.color = '#2554C7'; 							
					}		
				}	
			}
		</script>
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
			<security:authorize ifAllGranted="ROLE_ADMIN">
				<li><span><a href="newtestplan.html">New Test Plan</a></span></li> 				
				<li><span><a href="newtestcase.html?testplanID=${testplan.id}">New Test Case</a></span></li>
			</security:authorize>	
			<li><span class="current">Enter Results</span></li>		
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
		
		
		<table width="100%" >
		<tr>
			<td> 
				<a href="../viewtests/viewtestplan.html">${testplan.testplanName}</a> <font color="#7F94B0"> -  ${testplan.testerName} :</font>
			</td>
			<td>
			<table width="100%" border="0" style="font-size:13px;">
	           <tr>
	           		 <td width="14%" align="center">
	                     <img alt="Not Run" title="Not Run" src="../images/NA.png" />
	                     <font color="#3a4f63" size="1">Not Run (${testplan.totalNotRun}/${testplan.totalTests})</font>
	                  </td>
					<td width="14%" align="center">
	               		<img alt="Passed" title="Passed" src="../images/ok.png" />
	              		<font color="#3a4f63" size="1">Passed (${testplan.totalPassed}/${testplan.totalTests})</font>
	            	 </td>	                  
	                   <td width="14%" align="center">
	                     <img alt="Failed" title="Failed" src="../images/no.png" />
	                     <font color="#3a4f63" size="1">Failed (${testplan.totalFailed}/${testplan.totalTests})</font>
	                  </td>
	                  <td width="14%" align="center">
	                      <img alt="In Progress" title="In Progress" src="../images/IP.png" />
	                      <font color="#3a4f63" size="1">In Progress (${testplan.totalInProgress}/${testplan.totalTests})</font>
	                  </td>
	                  <td width="14%" align="center">
	                      <img alt="Deferred" title="Deferred" src="../images/def.png" />
	                      <font color="#3a4f63" size="1">Deferred (${testplan.totaldeferred}/${testplan.totalTests})</font>
	                  </td> 
	                  <td width="14%" align="center">
	                      <img alt="Blocked" title="Blocked" src="../images/block.png" />
	                      <font color="#3a4f63" size="1">Blocked (${testplan.totalBlocked}/${testplan.totalTests})</font>
	                  </td> 
	             </tr>
	         </table>
	         </td>
         </tr>
         </table>
			<BR />
		<table  width="90%" align="center">
		<c:forEach var="testcase" items="${testcases}" varStatus="index">
		<tr>
			<td >
			<div id="boxshadow" >
				<table border="0" width="100%" style="border-collapse: collapse;"> 
				<tr>	
					<security:authorize ifAllGranted="ROLE_ADMIN">		
					<td width="15" valign="top" >
					
						<form name="edittestcase" action="edittestcase.html" method="get">					
							<input type="hidden" name="testplanID" value="${testplan.id}">	
							<input type="hidden" name="testcaseID" value="${testcase.id}">						
							<input type="image" src="../images/edit.png" width="17" alt="Edit Testcase" title="Edit Testcase">
						</form>		
					</td>
					</security:authorize>
					<!--  <td>${testcase.id}</td>-->					
					<td width="10" valign="top">
					</td>
					<td id="TestDetailsCell_${testplan.id}" style="color:#707070;" valign="top" >
						${testcase.testcasename}
					</td>				
					<td width="30">
					</td>
					<td align="right" width="115" valign="top">
						<font color="#808080">${testcase.testcaseOS}</font>
					</td>	
					<td width="15">
					</td>
					<td align="right" width="115" valign="top">
					<security:authorize ifAnyGranted="ROLE_ADMIN">
						<!-- Set states table -->
						<table>
						<tr>
						<td valign="top">
						<c:choose>
							<c:when test="${testcase.notrun}">
								<img src="../images/NA.png" alt="Not Run" title="Not Run">
							</c:when>
							<c:otherwise>
								<form name="notrun" action="notrun.html" method="get">
									<input type="hidden" name="id" value="${testcase.id}">
									<input type="hidden" name="testplanID" value="${testplan.id}">								
									<input type="image" src="../images/NA_blank.PNG" alt="Not Run" title="Not Run">
								</form>
							</c:otherwise>
						</c:choose>	
						</td>
						<td valign="top">
						<c:choose>
							<c:when test="${testcase.passed}">
								<img src="../images/ok.png" alt="Passed" title="Passed" >
							</c:when>
							<c:otherwise>
								<form name="passed" action="passed.html" method="get">
									<input type="hidden" name="id" value="${testcase.id}">
									<input type="hidden" name="testplanID" value="${testplan.id}">
									<INPUT type="image" src="../images/ok_blank.PNG" alt="Passed" title="Passed">
								</form>
							</c:otherwise>
						</c:choose>	
						</td>
						<td valign="top">
						<c:choose>
							<c:when test="${testcase.failed}">
								<img src="../images/no.png" alt="Failed" title="Failed">
							</c:when>
							<c:otherwise>
								<form name="failed" action="failed.html" method="get">
									<input type="hidden" name="id" value="${testcase.id}">
									<input type="hidden" name="testplanID" value="${testplan.id}">
									<INPUT type="image" src="../images/no_blank.PNG" alt="Failed" title="Failed">
								</form>
							</c:otherwise>
						</c:choose>	
						</td>
						<td valign="top">
						<c:choose>
							<c:when test="${testcase.inprogress}">
								<img src="../images/IP.png" alt="In Progress" title="In Progress">
							</c:when>
							<c:otherwise>
								<form name="inprogress" action="inprogress.html" method="get">
									<input type="hidden" name="id" value="${testcase.id}">
									<input type="hidden" name="testplanID" value="${testplan.id}">
									<INPUT type="image" src="../images/IP_blank.PNG" alt="In Progress" title="In Progress">
								</form>
							</c:otherwise>
						</c:choose>
						</td>
						<td valign="top">
						<c:choose>
							<c:when test="${testcase.deferred}">
								<img src="../images/def.png" alt="Deferred" title="Deferred">
							</c:when>
							<c:otherwise>
								<form name="deferred" action="deferred.html" method="get">
									<input type="hidden" name="id" value="${testcase.id}">
									<input type="hidden" name="testplanID" value="${testplan.id}">
									<INPUT type="image" src="../images/def_blank.PNG" alt="Deferred" title="Deferred">
								</form>
							</c:otherwise>
						</c:choose>
						</td>
						<td valign="top">	
						<c:choose>
							<c:when test="${testcase.blocked}">
								<img src="../images/block.png" alt="Blocked" title="Blocked">
							</c:when>
							<c:otherwise>
								<form name="blocked" action="blocked.html" method="get">
									<input type="hidden" name="id" value="${testcase.id}">
									<input type="hidden" name="testplanID" value="${testplan.id}">
									<INPUT type="image" src="../images/block_blank.png" alt="Blocked" title="Blocked">
								</form>
							</c:otherwise>
						</c:choose>						
						</td>																		
						</tr>
						</table>
						<!-- End of Set states table -->
						</security:authorize>
						<security:authorize ifAllGranted="ROLE_USER">							
							<c:choose>
								<c:when test="${testcase.passed}">
									<font color="green">Passed</font>
								</c:when>
								<c:when test="${testcase.failed}">
									<font color="red">Failed</font>
								</c:when>
								<c:when test="${testcase.inprogress}">
									<font color="orange">In Progress</font>
								</c:when>
								<c:when test="${testcase.deferred}">
									<font color="purple">Deferred</font>
								</c:when>
								<c:when test="${testcase.blocked}">
									<font color="#505050">Blocked</font>
								</c:when>
								<c:otherwise>
									<font color="blue">Not Run</font>
								</c:otherwise>
							</c:choose>	
						</security:authorize>
					</td>	
					<td width="15">
					</td>
					<security:authorize ifAllGranted="ROLE_ADMIN">
					<td align="right" width="15" valign="top">	
						<form name="deletetestcase" action="deletetestcase.html" method="get">
							<input type="hidden" name="testcaseID" value="${testcase.id}">
							<input type="hidden" name="testplanID" value="${testplan.id}">
							<INPUT type="image" src="../images/delete.png" alt="Delete Test" title="Delete Test"  onclick="return confirm('Are you sure you want to delete : \n\n  ${testcase.testcasename} ?')">
						</form>										
					</td>	
					</security:authorize>					
				</tr>
				<tr>
					<td colspan="9" align="center">
						<img alt="Expand/Close Test Details" title="Expand/Close Test Details" src="../images/down.png" style="cursor:pointer" id="DropDownImg_${testcase.id}" onclick="loadTestDetails(${testcase.id});">
					</td>
				</tr>						
				</table>
			</div>
			</td>
		</tr>
		<tr>				
			<td id="TestDetails_${testcase.id}" style="display:none;"  align="center"> 
				<table border="0" width="95%">
				<tr>					
					<td width="90%" style="background-color:#E6E8FA;border-color: #707070; border-width: 1px 2px 2px 1px; " >
						<table width="100%" border="0" class="maintable" cellpadding="6" >														
						<tr>
							<td>                           
								<font color="#707070"><b>Test Summary:</b></font><BR>
								<textarea class="enterresults" name="Summary_${testcase.id}" id="Summary_${testcase.id}"  readonly="readonly"  style="overflow:visible;background-color:#FAF8E5;border:none;color:#707070;font-family: Verdana;">${testcase.testcasesummary}</textarea>
							</td>									
						</tr>	
						<tr>
							<td>                           
								<font color="#707070"><b>Test Pre Conditions:</b></font><BR>
								<textarea class="enterresults" name="PreCond${testcase.id}" id="PreCond${testcase.id}"  readonly="readonly" style="overflow:visible;background-color:#FAF8E5;border:none;color:#707070;font-family: Verdana;">${testcase.testcasepre}</textarea>
							</td>									
						</tr>									
						<tr>
							<td>
								<font color="#707070"><b>Test Steps:</b></font><BR>
								<textarea class="enterresultssteps" name="Steps${testcase.id}" id="Steps${testcase.id}"  readonly="readonly"  style="overflow:visible;background-color:#FAF8E5;border:none;color:#707070;font-family: Verdana;">${testcase.testcasesteps}</textarea>
							</td>									
						</tr>		
						<tr>
							<td>                           
								<font color="#707070"><b>Test Pass Conditions:</b></font><BR>
								<textarea  class="enterresults" name="PassCond${testcase.id}" id="PassCond${testcase.id}"  readonly="readonly"style="overflow:visible;background-color:#FAF8E5;border:none;color:#707070;font-family: Verdana;">${testcase.testcasepass}</textarea>
							</td>									
						</tr>													
						</table>
					</td>					
				</tr>
				</table>					
			</td>
		</tr>			
		</c:forEach>
		<security:authorize ifAllGranted="ROLE_ADMIN">
		<tr>			
			<td>
				<br>
				<a href="newtestcase.html?testplanID=${testplan.id}"><img src="../images/plus.png" width="15"> <font color="#707070">New Test Case</font></a>
			</td>
		</tr>
		</security:authorize>
		</table>
	</div>
	<div id="footer">
		Copyright © Testcenter, 2011
	</div>
	<div id="preload">
		<img src="../images/title.png">
		<img src="../images/delete.png" alt="Delete Test Case" title="Delete Test Case" /> 
   		<img src="../images/edit.png" alt="Edit Test Case" title="Edit Test Case" />   				
  		<img src="../images/NA.png" alt="Not Run" title="Not Run">
		<img src="../images/ok.png" alt="Passed" title="Passed" >
		<img src="../images/no.png" alt="Failed" title="Failed">
		<img src="../images/IP.png" alt="In Progress" title="In Progress">
		<img src="../images/def.png" alt="Deferred" title="Deferred">
		<img src="../images/block.png" alt="Blocked" title="Blocked">
		<img src="../images/NA_blank.PNG" alt="Not Run" title="Not Run">
		<img src="../images/ok_blank.PNG" alt="Passed" title="Passed" >
		<img src="../images/no_blank.PNG" alt="Failed" title="Failed">
		<img src="../images/IP_blank.PNG" alt="In Progress" title="In Progress">
		<img src="../images/def_blank.PNG" alt="Deferred" title="Deferred">
		<img src="../images/block_blank.png" alt="Blocked" title="Blocked">	
	</div>
</div>
</body>
</html>