<%@page import="java.util.ArrayList" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>[
{"total":"${pageCount}"}<c:forEach items="${allGroupRequests}" var="aUser" varStatus="loopCount"><c:choose><c:when test="${loopCount.count eq 1}">
,{
"User":"${aUser.username}",
"Accept":"<a href='/groupsoen3872011k/servlet/app/AcceptEntry?requesterID=${aUser.id}'><img width=30 height=30 src='/groupsoen3872011k/servlet/img/accept.png'/></a>",
"Deny":"<a href='/groupsoen3872011k/servlet/app/CancelEntry?requesterID=${aUser.id}'><img width=30 height=30 src='/groupsoen3872011k/servlet/img/deny.png'/></a>"
}</c:when><c:otherwise>
,{
"User":"${aUser.username}",
"Accept":"<a href='/groupsoen3872011k/servlet/app/AcceptEntry?requesterID=${aUser.id}'><img width=30 height=30 src='/groupsoen3872011k/servlet/img/accept.png'/></a>",
"Deny":"<a href='/groupsoen3872011k/servlet/app/CancelEntry?requesterID=${aUser.id}'><img width=30 height=30 src='/groupsoen3872011k/servlet/img/deny.png'/></a>"
}</c:otherwise></c:choose></c:forEach>
]
