<%@ include file="common/header.jsp" %>

<div class="login">
	<h2>Log In</h2>
	<c:if test="${param.login_error == 1}">
		<div class="error">Login Failed: Invalid username or password</div>
	</c:if>
	<form id="signin" action="/SpringSecurity/j_spring_security_check" method="POST">
		<label>UserName</label>
		<input type="text" name="j_username" tabindex="1"/>
		<label>Password</label>
		<input type="password" name="j_password" tabindex="2"  />
		<button type="submit">Login</button>
	</form>
</div>

<%@ include file="common/footer.jsp" %>
