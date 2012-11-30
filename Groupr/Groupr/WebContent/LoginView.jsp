<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% session.invalidate();%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login | Groupr</title>
	<link rel="stylesheet" type="text/css" href="/groupsoen3872011k/servlet/css/style.css" />
</head>

<body>
	<div id='loginLeft'><div id='leftRip'></div></div>
		<div id='loginRight'><div id='rightRip'></div>
			<div id='loginBox'>

				<c:if test="${!empty message}">
					<div id='error'>${message}</div>
				</c:if>
				<div id='loginHd'>
					<h4>Login</h4>
				</div>
				<form method='POST' action='/groupsoen3872011k/servlet/app/home' id='loginForm'>
					<input id='loginUsr' type='text' name='username' placeholder='Username' tabindex='1' />
					<input id='loginPw' type='password' name='password' placeholder='Password' tabindex='2' />
					<input id='loginBtn' type='submit' value='Login' class='button' tabindex='4' />
					<!--
					<br />
					<a id='signup' href='#'>Sign up!</a>
					<br />
					<a id='forgotPw' href='#'>Forgot your password?</a>
					-->
				</form>
			</div>
		</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/groupsoen3872011k/servlet/js/the.js"></script>
</body>
</html>