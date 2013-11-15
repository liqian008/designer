<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
User currentUser = (User) session.getAttribute(ConstFront.CURRENT_USER);
User queryUser = (User) request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);
%>

<aside class="sidebar widgets-light span3">
	<%
		if (queryUser != null) {
	%>
	<div class="widget-box widget-wrapper-form">
		<div class="content-title">
			<h4 class="widget-title">设计师个人资料</h4>
		</div>
		<form id="contact-form-widget" method="post" class="clearfix"
			action="/designer-front/login">
			<ul>
				<li class="clearfix">
					<div class="widget-blogpost-avatar">
						<a href="/designer-front/<%=queryUser.getId()%>/home"> <img
							src="<%=queryUser.getHeadImg()%>">
						</a>
					</div>
					<div class="widget-blogpost-content">
						<div class="widget-blogpost-date">
							<p>
								设计师:
								<%=queryUser.getNickname()%>
							</p>
							<p>作品数量: xx个</p>
							<p>粉丝: 10个</p>
							<!-- <p>
								Sina微博
							</p>
							<p>
								QQ号：
							</p> -->
						</div>
					</div>
				</li>
			</ul>
			<%
				if (currentUser == null
							|| !currentUser.getId().equals(queryUser.getId())) {
			%>
			<%
				Boolean hasFollowed = (Boolean) request
								.getAttribute("hasFollowed");
						if (hasFollowed != null && hasFollowed) {
			%>
			<input class="common-submit button" type="button" value="取消关注"
				onclick="location.href='/designer-front/<%=queryUser.getId()%>/home'" />
			<%
				} else {
			%>
			<input class="common-submit button" type="button" value="关注"
				onclick="location.href='/designer-front/<%=queryUser.getId()%>/home'" />
			<%
				}
			%>
			<input class="common-submit button" type="button" value="私信"
				onclick="location.href='/designer-front/settings?op=inbox&messageType=<%=queryUser.getId()%>'" />
			<%
				}
			%>
			<input class="common-submit button" type="button" value="作品辑"
				onclick="location.href='/designer-front/<%=queryUser.getId()%>/home'" />
			<input class="common-submit button" type="button" value="个人资料"
				onclick="location.href='/designer-front/<%=queryUser.getId()%>/info'" />
			<%
				if (currentUser != null && currentUser.getId() == queryUser.getId()) {
			%>
			<input class="common-submit button" type="button" value="个人设置"
				onclick="location.href='/designer-front/settings'" />
			<%}%>
		</form>
	</div>
	<jsp:include page="./recentDesignerAlbums.jsp"></jsp:include>
	<%}%>
	<jsp:include page="./recentAlbums.jsp"></jsp:include>
	
	<jsp:include page="./hotDesigners.jsp"></jsp:include>

	<jsp:include page="./recentDesigners.jsp"></jsp:include>

	<jsp:include page="./hotTags.jsp"></jsp:include>

</aside>