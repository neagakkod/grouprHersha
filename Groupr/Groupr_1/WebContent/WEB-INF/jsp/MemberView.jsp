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
	<div id='overlay'>
		<div id='overlayBox'>
			<div id='closeOverlay'>
				<a href='#'>close</a>
			</div>
			<div id='sendInvite'>
				<h3>Invite members</h3>
				<form method='POST' action='/groupsoen3872011k/servlet/app/InviteMember'>
					<p>
						<label for='inviteeUsername'>Username</label>
							<input type='text' placeholder='e.g.: Ronald' name='inviteeUsername' />
					</p>
					<input type='hidden' name='groupName' value='${myGroup.name}' />
					<input type='submit' class='button' value='Send invite' />
				</form>
			</div>
			<div id='editGroupInfo'>
				<h3>Edit Group</h3>
				<form method='POST' action='/groupsoen3872011k/servlet/app/EditGroup'>
					<p>
						<label for='gName'>Group Name</label>
							<input type='text' value='${myGroup.name}' name='gName' />
					</p>
					<p>
						<label for='gDescr'>Group Description</label>
							<input type='text' value='${myGroup.desc}' name='gDescr' />
					</p>
					<input type='submit' class='button' value='Save edits' />
				</form>
			</div>

		</div>
	</div>


	<div id='container'>
		<header>
			<div id='status'>
				<a href='#' id='viewProfile' class='TG' title='View your profile'><img src='/groupsoen3872011k/servlet/img/profile.png' alt='Profile' /></a>
				<a href='/groupsoen3872011k/servlet/' class='TG' title='Log out of Groupr'><img src='/groupsoen3872011k/servlet/img/logout.png' alt='Logout' /></a>
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
				<a href='#' id='browseMyGroup' class='button'>
					<h3>Check Group</h3>
				</a>
				<a href='#' id='leaveGroup' class='button'>
					<h3>Browse Requests</h3>
				</a>
			</nav>

			<div class='clearfix'></div>

			<section id='content'>
				<div class='sep'></div>
				<div id='myGroupView'>

					<div id='myGroupInfo'>
						<h4>${myGroup.name}</h4>
						<div id='groupDescription'>${myGroup.desc}</div>
						<div id='editGroupBtn'><a href='#'>edit group info</a></div>
					</div>

					<div class='clearfix'></div>
					<div class='subSep'></div>

					<div id='memberListFiller'>
						<img src='http://i.imgur.com/5wsH3.gif' width=30 height=30 alt='LOADING..' />
						<span>LOADING...</span>
					</div>

					<h4 class='center'>Missing a member? <a id='sendInviteBtn' href='#'>Send an invite!</a></h4>
				</div>

				<div id='leaveGroupView'>
					<h4>These people <em>really</em> want to be a part of your circle:</h4>

					<div id='groupRequestFiller'>
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
						<td>${myGroup.name}</td>
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