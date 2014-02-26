<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>


<%
User currentUser = (User) session.getAttribute(ConstFront.CURRENT_USER);
boolean isDesigner = currentUser!=null&&currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;
%>
<%if(currentUser!=null&&currentUser.getId()>0){%>
<jsp:include page="./myBox.jsp"></jsp:include>
<%} %>

<jsp:include page="./hotTags.jsp"></jsp:include>

<jsp:include page="./latestAlbums.jsp"></jsp:include>

<jsp:include page="./latestDesigners.jsp"></jsp:include>

<!-- 
<jsp:include page="./hotDesigners.jsp"></jsp:include>
-->
