<%@page import="org.dsrg.soenea.domain.user.User" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang='en'>
<head>
	<title>Groupr</title>
	<link rel="stylesheet" type="text/css" media="screen,print" href="/groupsoen3872011k/servlet/css/style.css" />
</head>
<body>
	<!-- overlay -->
	<div id='overlay'>
		<div id='overlayBox'>
			<div id='closeOverlay'>
				<a href='#'>close</a>
			</div>
			<h3>Create a group</h3>
			<form method='POST' action='/groupsoen3872011k/servlet/app/CreateGroup'>
				<p>
					<label for='gName'>Name</label>
						<input type='text' placeholder='1337 Squad' name='gName' />
				</p>
				<p>
					<label for='gDescr'>Description</label>
						<input type='text' placeholder='The realest gangasters' name='gDescr' />
				</p>
				<input type='hidden' name='username' value='${myUser.username}' />
				<input id='createGroupBtn' type='submit' class='button' value='Create my group' />
			</form>
		</div>
	</div>

	<!-- content -->
	<div id='container'>
		<header>
			<div id='status'>
				<a href='#' id='viewProfile' class='TG' title='View your profile'><img src='/groupsoen3872011k/servlet/img/profile.png' alt='Profile' /></a>
				<a href='/groupsoen3872011k/servlet/' class='TG' title='Log out of Groupr'><img src='/groupsoen3872011k/servlet/img/logout.png' alt='Login' /></a>
			</div>

			<h1><a href='/groupsoen3872011k/servlet/app/home'>Groupr</a></h1>
		</header>

		<div class='sep'>
			<noscript>Javascript is required to use GROUPR</noscript>
			<c:if test="${!empty success}">
				<div id='success'>${success}</div>
			</c:if>
			<c:if test="${!empty message}">
				<div id='error'>${message}</div>
			</c:if>
		</div>

		<div id='gen'>
			<nav>
				<a href='../BrowseGroups' id='browseGroups' class='button'>
					<h3>Browse Groups</h3>
				</a>
				<a href='../BrowseInvites' id='browseInvites' class='button'>
					<h3>Browse Invites</h3>
				</a>
			</nav>

			<div class='clearfix'></div>

			<section id='content'>
				<div class='sep'></div>

				<div id='groupView'>
					<div id='GroupViewFiller'>
						<img src='http://i.imgur.com/5wsH3.gif' width=30 height=30 alt='LOADING..' />
						<span>LOADING...</span>
					</div>

					<h4>Don't see your group? <a id='createGroup' href='#'>Why not create one?</a></h4>
				</div>

				<div id='inviteView'>
					<div id='InviteViewFiller'>
						<img src='http://i.imgur.com/5wsH3.gif' width=30 height=30 alt='LOADING..' />
						<span>LOADING...</span>
					</div>
				</div>

			</section>
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
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
	<script src='/groupsoen3872011k/servlet/js/the.js' type='text/javascript'></script>
</body>
</html>