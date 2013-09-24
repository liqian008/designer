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
<%if(user.getDesignerStatus()!=null&&user.getDesignerStatus()==ConstService.DESIGNER_APPLY_PASSED){%>
<li <%=getActiveStr("publisher", op)%>><a class="button button-white" href="./settings.art?op=publisher">发布新作品</a></li>
<li <%=getActiveStr("designerInfo", op)%>><a class="button button-white" href="./settings.art?op=designerInfo">设计师基本资料</a></li>
<li <%=getActiveStr("shareSettings", op)%>><a class="button button-white" href="./settings.art?op=shareSettings">分享设置（推荐）</a></li>
<li <%=getActiveStr("flowerers", op)%>><a class="button button-white" href="./settings.art?op=flowers">关注我的人</a></li>
<%}else{%>
<li <%=getActiveStr("designerApply", op)%>><a class="button button-white" href="./settings.art?op=designerApply">申请成为设计师</a></li>
<%}%>
<li <%=getActiveStr("info", op)%>><a class="button button-white" href="./settings.art">个人基本资料</a></li>
<li <%=getActiveStr("avatar", op)%>><a class="button button-white" href="./settings.art?op=avatar">修改头像</a></li>
<li <%=getActiveStr("changePasswd", op)%>><a class="button button-white" href="./settings.art?op=changePasswd">修改密码</a></li>
<li <%=getActiveStr("inbox", op)%>><a class="button button-white" href="./settings.art?op=inbox">我的消息</a></li>
<li <%=getActiveStr("favorities", op)%>><a class="button button-white" href="./settings.art?op=favorities">我的收藏</a></li>
<li <%=getActiveStr("flowerings", op)%>><a class="button button-white" href="./settings.art?op=flowerings">我关注的人</a></li>

