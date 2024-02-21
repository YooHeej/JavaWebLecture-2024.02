<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="hello" value="Hello World!"></c:set>
<c:set var="mall" value="쇼핑몰의 중심 JSP Mall"></c:set>
<c:set var="center" value="중심"></c:set>
<c:set var="local" value="2024-02-21T14:23:02.12135" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL</title>
	<style>
	td, th { padding:3px;}
	</style>
</head>
<body style="margin: 50px">
	<h1>JSP Standard Tag Library(JSTL) - Core</h1>
	
	<table border="1">
		<tr><th>표현 방법</th><th>결과</th></tr>
		<tr><th>문자열 연결</th><td>${hello } ${mall }</td></tr>
		<tr><th>\${fn:length(mall) }</th><td>${fn:length(mall) }</td></tr>
		<tr><th>\${fn:toUpperCase(hello) }</th><td>${fn:toUpperCase(hello) }</td></tr>
		<tr><th>\${fn:substring(mall, 5, 7) }</th><td>${fn:substring(mall, 5, 7) }</td></tr>
		<tr><th>\${fn:replace(mall, " ", "==") }</th><td>${fn:replace(mall, " ", "==") }</td></tr>
		<tr><th>\${fn:indexOf(mall, center) }</th><td>${fn:indexOf(mall, center) }</td></tr>
		<tr><th>\${fn:contains(mall, center) }</th><td>${fn:contains(mall, center) }</td></tr>
	</table>
	<br>
	<%-- local를 24-02-21 14:23 --%>
	<h3>${fn:substring(fn:replace(local, "T", " "), 2, 16) }</h3>
	
</body>
</html>