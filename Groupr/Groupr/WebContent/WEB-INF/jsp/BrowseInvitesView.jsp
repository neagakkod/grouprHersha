<%@page import="java.util.ArrayList" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>[
{"total":"${pageCount}"}<c:forEach items="${myInvites}" var="invite" varStatus="loopCount"><c:choose><c:when test="${loopCount.count eq 1}">
,{
"inviter":"${invite.inviter.username}",
"groupname":"${invite.group.name}",
"groupdesc":"${invite.group.desc}",
"accept":"<a href='/groupsoen3872011k/servlet/app/AcceptInvites?groupID=${invite.group.id}'><img width=30 height=30 src='/groupsoen3872011k/servlet/img/accept.png'/></a>",
"deny":"<a href='/groupsoen3872011k/servlet/app/DeclineInvites?inviteID=${invite.id}'><img width=30 height=30 src='/groupsoen3872011k/servlet/img/deny.png'/></a>"
}</c:when><c:otherwise>
,{
"inviter":"${invite.inviter.username}",
"groupname":"${invite.group.name}",
"groupdesc":"${invite.group.desc}",
"accept":"<a href='/groupsoen3872011k/servlet/app/AcceptInvites?groupID=${invite.group.id}'><img width=30 height=30 src='/groupsoen3872011k/servlet/img/accept.png' /></a>",
"deny":"<a href='/groupsoen3872011k/servlet/app/DeclineInvites?inviteID=${invite.id}'><img width=30 height=30 src='/groupsoen3872011k/servlet/img/deny.png' /></a>"
}</c:otherwise></c:choose></c:forEach>
]
