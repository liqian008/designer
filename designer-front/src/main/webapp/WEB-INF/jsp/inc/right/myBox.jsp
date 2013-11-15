<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
User currentUser = (User) session.getAttribute(ConstFront.CURRENT_USER);
boolean isDesigner = currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;

if (currentUser != null) {
%>
<div class="widget-box widget-wrapper-form">
	<div class="content-title">
		<h4 class="widget-title">我的信息
		</h4>
	</div>
	<form id="contact-form-widget" method="post" class="clearfix"
		action="/designer-front/login">
		<ul>
			<li class="clearfix">
				<div class="widget-blogpost-avatar">
					<a href="/designer-front/<%=currentUser.getId()%>/home"> <img
						src="<%=currentUser.getHeadImg()%>">
					</a> 
				</div>
				<div class="widget-blogpost-content">
					<div class="widget-blogpost-date">
						<p>
							昵 称:&nbsp;<%=currentUser.getNickname()%>
						</p>
						<%if(isDesigner){ %>
						<p>作品数量:&nbsp;xx个</p>
						<p>粉 丝:&nbsp;10个</p>
						<%}%>
						<p>关 注:&nbsp;10个</p>
					</div>
				</div>
			</li>
		</ul>
		<%
		%>
		<%if(isDesigner){%>
			<input class="common-submit button" type="button" value="发布作品"
			onclick="location.href='/designer-front/<%=currentUser.getId()%>/info'" />
		<%} %>
		<input class="common-submit button" type="button" value="个人主页"
			onclick="location.href='/designer-front/<%=currentUser.getId()%>/home'" />

		
	</form>
</div>
<%}%>