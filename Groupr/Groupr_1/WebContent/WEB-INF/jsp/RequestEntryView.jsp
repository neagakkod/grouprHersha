<%@page import="model.group.GroupProxy" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty success}">
		${success}
	</c:if>
	<c:if test="${!empty message}">
		${message}
</c:if>
