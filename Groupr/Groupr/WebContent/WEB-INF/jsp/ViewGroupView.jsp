<%@page import="java.util.ArrayList" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>[
{"total":"${pageCount}"}<c:forEach items="${myGroupMates}" var="memberProfile" varStatus="loopCount"><c:choose><c:when test="${loopCount.count eq 1}">
,{
"User":"${memberProfile.username}",
"Kick":"<a class='kick' href='/groupsoen3872011k/servlet/app/RemoveMember?memberID=${memberProfile.id}'><img src='/groupsoen3872011k/servlet/img/boot.png' width=30 height=30 alt='Kick this user' /></a>"
}</c:when><c:otherwise>
,{
"User list":"${memberProfile.username}",
"Kick":"<a class='kick' href='/groupsoen3872011k/servlet/app/RemoveMember?memberID=${memberProfile.id}'><img src='/groupsoen3872011k/servlet/img/boot.png' width=30 height=30 alt='Kick this user' /></a>"
}</c:otherwise></c:choose></c:forEach>
]
