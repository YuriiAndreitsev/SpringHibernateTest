<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file="../../includes/header.jsp"%>
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>File Upload</title> -->
<!-- </head> -->

<center>
	<h1>File Upload</h1>
	<form method="post" action="UploadServlet"
		enctype="multipart/form-data">
		Select file to upload: <input type="file" name="file" size="60" /><br />
		<br /> <input type="submit" value="Upload" />
	</form>
</center>

<c:choose>
	<c:when test="${sessionScope.message!=null}">
		<br />${sessionScope.message}<br />
	</c:when>
</c:choose>

<%@include file="../../includes/footer.jsp"%>