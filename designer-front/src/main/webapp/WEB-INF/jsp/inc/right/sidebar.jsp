<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
User queryUser = (User) request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);
boolean isDesigner = queryUser!=null&&queryUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;
%>

<%
if (queryUser != null) {
	%>
<jsp:include page="./userBox.jsp"></jsp:include>
<%if(isDesigner){%>
<jsp:include page="./latestDesignerAlbums.jsp"></jsp:include>
<%}
}%>
<jsp:include page="./hotTags.jsp"></jsp:include>

<jsp:include page="./latestAlbums.jsp"></jsp:include>

<jsp:include page="./latestDesigners.jsp"></jsp:include>

