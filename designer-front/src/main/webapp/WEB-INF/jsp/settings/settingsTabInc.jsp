<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>

<%
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
int settingsMenuIndex = 0;
String tempFlag = request.getParameter("settingsMenuFlag");
if("avatar".equals(tempFlag)){
	settingsMenuIndex = 1;
}else if("changePasswd".equals(tempFlag)){
	settingsMenuIndex = 2; 
}else if("inbox".equals(tempFlag)){
	settingsMenuIndex = 3;
}else if("favorities".equals(tempFlag)){
	settingsMenuIndex = 4;
}else if("thirdparty".equals(tempFlag)){
	settingsMenuIndex = 5;
}else if("publisher".equals(tempFlag)){
	settingsMenuIndex = 6;
}else if("designerInfo".equals(tempFlag)){
	settingsMenuIndex = 7;
}else{
	settingsMenuIndex = 0;
}

%>
<%if(user.getDesignerStatus()!=null&&user.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED){%>
<li <%=settingsMenuIndex==6?"class=active":""%>><a class="button button-white" href="/designer-front/settings/newAlbum">发布新作品</a></li>
<%}%>
<li <%=settingsMenuIndex==5?"class=active":""%>><a class="button button-white" href="/designer-front/settings/thirdparty">第三方账户绑定</a></li>
<li <%=settingsMenuIndex==0?"class=active":""%>><a class="button button-white" href="/designer-front/settings">个人资料</a></li>
<li <%=settingsMenuIndex==7?"class=active":""%>><a class="button button-white" href="/designer-front/settings/designerInfo">设计师资料</a></li>
<li <%=settingsMenuIndex==1?"class=active":""%>><a class="button button-white" href="/designer-front/settings/avatar">修改头像</a></li>
<li <%=settingsMenuIndex==2?"class=active":""%>><a class="button button-white" href="/designer-front/settings/changePasswd">修改密码</a></li>
<li <%=settingsMenuIndex==3?"class=active":""%>><a class="button button-white" href="/designer-front/settings/inbox">我的消息</a></li>
<%-- <li <%=settingsMenuIndex==4?"class=active":""%>><a class="button button-white" href="/designer-front/settings/favorities">我的收藏</a></li> --%>

