<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@include file="../../includes/header.jsp"%>
<div>
	<a href="./login.html" id="RegLogLink">Sign In</a>
</div>
<c:choose>
	<c:when test="${regComplete==null}">

		<form class="regForm" action='./registration.html' method='post'>
			<h1 align='center'>Fill in this registration form!</h1>
			<div id="registrationErrors">${errorObj}</div>
			<div>
				<input class='regInput' type='email' name='email' placeholder="Email"
					value='${requestScope.email}' required
					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
			</div>
			<div>
				<input class='regInput' type='password' placeholder="Password" name='password'
					value='' maxlength="20" required
					pattern="^(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\\d\\W])|(?=.*\\W)(?=.*\\d))|(?=.*\\W)(?=.*[A-Z])(?=.*\\d)).{8,20}$">
			</div>
			<div>
				<input class='regInput' placeholder="Confirm Your Password"
					type='password' name='passwordConfirmation'>
			</div>
			<div>
				<label for="location">Where are you Form?</label> 
				<select
					name='location' class='regInput'>
					<option value=""></option>
					<option value="dnr" ${requestScope.dnr}>DNR</option>
					<option value="lnr" ${requestScope.lnr}>LNR</option>
					<option value="crimea" ${requestScope.crimea}>CRIMEA</option>
				</select>
			</div>
			<div>
				<label for="gender">Specify Your Gender</label>
				<input	class="genderInput" type='radio' name='gender'value="male" ${requestScope.male}> <label>Male</label> 
				<input	class="genderInput" type='radio' name='gender' value="female" ${requestScope.female}><label>Female</label>
			</div>
			<div>
				<textarea class='regInput' name='comment' cols='25' rows='10'>${requestScope.textArea}</textarea>
			</div>
			<div>
				<label for='checkbox'>Have you seen those small letters?: 
				<font  id="readAgreement" color='grey' size='1'> <a href="agreement.txt"> (Read	this carefully) </a> <br />I don't want to read, I agree with everything!</font>
				</label> <input type="checkbox" name='checkbox' value="checked" checked>
			</div>
			<input id='regButton' name="signUpButton" type='submit' value="Sign Up!">
		</form>

	</c:when>
</c:choose>
${regComplete}
<%@include file="../../includes/footer.jsp"%>