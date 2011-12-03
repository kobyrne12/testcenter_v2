<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<title>TestCenter - Access Denied</title>
		<link rel="stylesheet" href="../css/style.css" type="text/css" media="screen" />
	</head>
	<body  onload='document.f.j_username.focus();'>
		
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
			<li><span class="current">Access Denied</span></li>							
		</ul>
	</div>
	<div id="content">	
		<font color="red">Access Denied</font> 
		<hr>
	 	<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">           
			<font color="green"> You are currently logged in as <security:authentication property="principal.username"/> - <a href="j_spring_security_logout">Logout</a> - or enter new login details below</font> 
		 	<br><hr> 	
		</security:authorize>
		<c:if test="${fn:length(errormessage) > 0}"> 				
		 		<font color="red">${errormessage}</font> 
		 	<hr>
		</c:if>
		<c:if test="${fn:length(sucessmessage) > 0}">  				
		 		<font color="green">${sucessmessage}</font> 
		 	<hr>
		</c:if>	
		
		<form name='f' action='/testcenter_v2/j_spring_security_check' method='POST'>
		<table align="center" border='0'>
		<tr>
		<td width="45%" align="center">	
			<fieldset>
			<legend>Enter Login Details:</legend>	
			 <table border='0' width="350" height="150">
			 	<tr>
			    	<td height="5">
			    		<!-- spacer -->
			    	</td>
			    </tr>
			    <tr><td>User:</td><td><input type='text' name='j_username' value=''></td></tr>
			    <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr>
			    <tr>
			    	<td>
			    		<input type="checkbox" id="remember_me" name="_remember_me" checked />
			    		<label for="remember_me">Keep me logged in</label>
			    	</td>		    
			    </tr>
			    <tr>
			    	<td colspan='2' align="right">
			    		<table>
			    		<tr>
						    <td>
						    	<input value="Login" name="submit" type="submit"/>
						    </td>
						    <td>
						    	<input name="reset" type="reset"/>
						    </td>
					    </tr>
					    </table>
					</td>
			    </tr>
			  </table>
			</fieldset>
		  </td>
		  <td width="10%" >
		  </td>
		  <td width="45%" align="center">
		  	<fieldset>
			    <legend>Test Login Details:</legend>
			    <table cellpadding="5" width="350" height="150">
			    <tr>
			    	<td height="5">
			    		<!-- spacer -->
			    	</td>
			    </tr>
			    <tr>
			    	<td>Admin Details:</td>
			    	<td>kenneth</td>
			    	<td>password</td>
			    </tr>
			    <tr>
			    	<td>User Details:</td>
			    	<td>igor</td>
			    	<td>password</td>
			    </tr>
			    <tr>
			    	<td height="5">
			    		<!-- spacer -->
			    	</td>
			    </tr>
			     <tr>
			     	<td colspan="3">
			    	<font color="#707070">Only Admin can create Test Plans and Test Cases</font>
			    	</td>
			    </tr>
			    </table>			   		    
			 </fieldset>
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