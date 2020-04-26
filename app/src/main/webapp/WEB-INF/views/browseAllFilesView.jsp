<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@include file="../../includes/header.jsp"%>

<c:choose>
	<c:when test="${noFiles!=null }">
	${noFiles}
</c:when>
</c:choose>
All Files
<c:forEach var="file" items="${allFiles}">

	<div class="divTable" style="width: 1%;">
		<div class="divTableBody">
			<div class="divTableRow">
				<div class="divTableCellName" id="${file.getName()}">${file.getName()}</div>
				<div class="divTableCell">
					<input type='button' onclick='parseFile("${file.getName()}")'
						value='parse' />
				</div>
				<div class="divTableCellMessage" id="parseMessage${file.getName()}"></div>
			</div>

		</div>
	</div>
</c:forEach>

<div></div>

<script>
	function parseFile(name) {
		var fileToParseName = document.getElementById(name).innerHTML;
		$
				.ajax({
					url : "./parseXML.html",
					type : "POST",
					dataType : "json",
					data : {
						name : name
					},
					success : function(data) {
						document.getElementById("parseMessage"
								+ fileToParseName).innerHTML = data.parseMessage;
					},
					error : function() {
						console.log("Error");
					}
				});
	}
</script>

<%@include file="../../includes/footer.jsp"%>