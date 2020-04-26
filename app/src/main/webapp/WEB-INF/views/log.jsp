<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../includes/header.jsp"%>
<div>
	<a href="./registration.html" id="	">Sign Up</a>
</div>
<c:choose>
	<c:when test="${sessionScope.user.email==null}">
		<form class="loginForm" action="./login.html" method="post">
			<h1 align='center'>Sign Into Your Account!</h1>
			<div>
				<input class="logInput" placeholder="E-Mail" type="email"
					name="email" maxlength='30' value="${requestScope.email}" required
					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" />
			</div>

			<div>
				<input class="logInput" placeholder="Password" type="password"
					name="password" required value=""
					pattern="^(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\\d\\W])|(?=.*\\W)(?=.*\\d))|(?=.*\\W)(?=.*[A-Z])(?=.*\\d)).{8,20}$" />

			</div>

			<div>
				<a href="#" id="forgot"><font size='1'>Forgot your
						password?</font></a> <label for="remember"> <input name="remember"
					type="checkbox" value="" />Remember me
				</label>
			</div>
			<button id="logButton" type="submit">Log In</button>
		</form>
	</c:when>

	<c:otherwise>
		<br>
		<div>
			<label><font color='${color}' id="loginMessage">${message}</font></label>
		</div>
	</c:otherwise>


</c:choose>

<c:choose>
	<c:when test="${errorMessage!=null}">
		<font color='red'>${errorMessage}</font>
		<br>
		<font color='${color}'>${message}</font>
	</c:when>
</c:choose>

<%@include file="../../includes/footer.jsp"%>