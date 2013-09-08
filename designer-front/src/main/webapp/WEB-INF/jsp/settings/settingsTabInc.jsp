<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>

<%!
String getActiveStr(String option, String op){
	String activeStr = "class=active";
	if(op==null&&"info".equals(option)){
		return activeStr;
	}
	if(option.equals(op)){
		return activeStr;
	}
	return "";
}
%>

<%
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
String op =request.getParameter("op");
%>
<ul class="tabs-nav tabs clearfix span3">
	<li <%=getActiveStr("info", op)%>><a class="button button-white" href="./settings.art">个人基本信息</a></li>
	<li <%=getActiveStr("avatar", op)%>><a class="button button-white" href="./settings.art?op=avatar">修改头像</a></li>
	<li <%=getActiveStr("changePasswd", op)%>><a class="button button-white" href="./settings.art?op=changePasswd">修改密码</a></li>
	<li <%=getActiveStr("myFavorities", op)%>><a class="button button-white" href="./settings.art?op=myFavorities">我的收藏</a></li>
	<li <%=getActiveStr("myFlowerings", op)%>><a class="button button-white" href="./settings.art?op=myFlowerings">我关注的人</a></li>
	<%if(user.getDesignerStatus()==ConstService.DESIGNER_APPLY_PASSED){%>
	<li <%=getActiveStr("myFlowerers", op)%>><a class="button button-white" href="./settings.art?op=myFlowers">关注我的人</a></li>
	<li <%=getActiveStr("designerInfo", op)%>><a class="button button-white" href="./designerProfile.art">设计师基本信息</a></li>
	<li <%=getActiveStr("applyDesigner", op)%>><a class="button button-white" href="./syncSettings.art">作品分享器（推荐）</a></li>
	<%}else{%>
	<li <%=getActiveStr("applyDesigner", op)%>><a class="button button-white" href="./settings.art?op=applyDesigner">申请成为设计师</a></li>
	<%}%>
</ul>
  
