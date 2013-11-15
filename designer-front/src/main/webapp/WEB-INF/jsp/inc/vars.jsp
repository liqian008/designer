<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
%>