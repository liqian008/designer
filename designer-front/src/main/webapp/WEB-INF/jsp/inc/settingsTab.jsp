<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>

<%
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
%>
<ul class="tabs-nav tabs clearfix span3">
	<li><a class="button button-white" href="./settings.art">个人基本信息</a></li>
	<li><a class="button button-white" href="./testAvatar.art">修改头像</a></li>
	<li><a class="button button-white" href="./changePasswd.art">修改密码</a></li>
	<li><a class="button button-white" href="./myFavorites.art">我的收藏</a></li>
	<li><a class="button button-white" href="./myFlowerings.art">我的关注</a></li>
	<%if(user.getDesignerStatus()==ConstService.DESIGNER_APPLY_PASSED){%>
	<li><a class="button button-white" href="./myFlowers.art">我的粉丝</a></li>
	<li><a class="button button-white" href="./designerProfile.art">设计师基本信息</a></li>
	<li><a class="button button-white" href="./syncSettings.art">作品分享器（推荐）</a></li>
	<%}else{%>
	<li><a class="button button-white" href="./applyDesigner.art">申请成为设计师</a></li>
	<%}%>
</ul>
  
