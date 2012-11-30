<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang='en'>
<head>
	<title>Groupr</title>
	<link rel="stylesheet" type="text/css" media="screen,print" href="/groupsoen3872011k/servlet/css/style.css" />
</head>
<body>
	<!-- content -->
	<div id='container'>
		<header>
			<div id='status'>
				<a href='#' id='viewProfile' class='TG' title='View your profile'><img src='/groupsoen3872011k/servlet/img/profile.png' alt='Profile' /></a>
				<a href='/groupsoen3872011k/servlet/' class='TG' title='Log out of Groupr'><img src='/groupsoen3872011k/servlet/img/logout.png' alt='Login' /></a>
			</div>

			<h1><a href='/groupsoen3872011k/servlet/app/home'>Groupr</a></h1>
		</header>

		<div class='sep'><noscript>Javascript is required to use GROUPR</noscript></div>

		<div id='gen'>


			<c:if test="${!empty success}">
				<div id='success'>${success}</div>
			</c:if>
			<c:if test="${!empty message}">
				<div id='error'>${message}</div>
			</c:if>

			<FORM  ENCTYPE="multipart/form-data" ACTION="/groupsoen3872011k/servlet/app/ImportCsv" METHOD=POST>
				<table>
		            <tr>
		            	<td colspan="2">
		            		<p align="center">
		                    <B>UPLOAD THE FILE</B>
		                    </p>
		            	</td>
		            </tr>
		            <tr>
		            	<td>
		            		<b>Choose the file To Upload:</b>
						</td>
		                <td>
		                	<INPUT NAME="file" TYPE="file">
		                </td>
		            </tr>
					<tr>
						<td colspan="2">
							<p align="right">
								<INPUT TYPE="submit"  VALUE="Send File" >
							</p>
						</td>
					</tr>
		        </table>
			</FORM>
		</div>


		<!-- Profile -->
		<div id='profileView'>
			<h3>Hey <em></em>${myUser.username}</em></h3>
			<p>Your info:</p>
			<table>
				<thead>
					<th>First name</th>
					<th>Last name</th>
					<th>Group</th>
				</thead>
				<tbody>
					<tr>
						<td>${myProfile.firstName}</td>
						<td>${myProfile.lastName}</td>
						<td>No group :(</td>
					</tr>
				</tbody>
			</table>

			<p>
				<a href="#" id="closeProfileView">Return</a>
			</p>
		</div>

		<footer>
			&copy; 2011 Shadi Isber, Nick Kilingi, Akmal Urazbaev
		</footer>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
	<script src='/groupsoen3872011k/servlet/js/the.js' type='text/javascript'></script>
</BODY>
</HTML>