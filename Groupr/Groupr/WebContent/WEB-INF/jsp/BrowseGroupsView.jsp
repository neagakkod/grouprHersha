<%@page import="model.group.GroupProxy" %><%@page import="java.util.ArrayList" %><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>[
{"total":"${pageCount}"}<c:forEach items="${groups}" var="group" varStatus="loopCount"><c:choose><c:when test="${loopCount.count eq 1}">
,{
"name":"${group.name}",
"description":"${group.desc}",
"join":"<a class='joinRequest' href='/groupsoen3872011k/servlet/app/RequestEntry?groupID=${group.id}'>Join this group</a>"
}</c:when><c:otherwise>
,{
"name":"${group.name}",
"description":"${group.desc}",
"join":"<a class='joinRequest' href='/groupsoen3872011k/servlet/app/RequestEntry?groupID=${group.id}'>Join this group</a>"
}</c:otherwise></c:choose></c:forEach>
]
