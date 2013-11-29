<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="com.bruce.designer.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
User currentUser = (User) session.getAttribute(ConstFront.CURRENT_USER);
User queryUser = (User) request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);
boolean isDesigner = queryUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;
boolean isMe =  currentUser!=null&&queryUser!=null&&currentUser.getId().equals(queryUser.getId());
 
String who = "";
if(isDesigner){
	who = "设计师";
}

if (queryUser != null) {
%>
<div class="widget-box widget-wrapper-form">
	<div class="content-title">
		<h4 class="widget-title"><%=who%>个人信息
		</h4>
	</div>
	<form id="contact-form-widget" method="post" class="clearfix"
		action="/designer-front/login">
		<ul>
			<li class="clearfix">
				<div class="widget-blogpost-avatar">
					<a href="/designer-front/<%=queryUser.getId()%>/home"><img
						src="<%=UploadUtil.getAvatarUrl(queryUser.getId(), ConstService.UPLOAD_IMAGE_SPEC_LARGE)%>">
					</a>
				</div>
				<div class="widget-blogpost-content">
					<div class="widget-blogpost-date">
						<p>
							昵 称:&nbsp;<%=queryUser.getNickname()%>
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

		<%if(isDesigner){%>
		<%if(!isMe){%>
		<%
			Boolean hasFollowed = (Boolean) request
							.getAttribute("hasFollowed");
					if (hasFollowed != null && hasFollowed) {
		%>
		<%-- <input class="common-submit button" type="button" value="取消关注"
			onclick="location.href='/designer-front/<%=queryUser.getId()%>/home'" /> --%>
		<%
			} else {
		%>
		<%-- <input class="common-submit button" type="button" value="关注"
			onclick="location.href='/designer-front/<%=queryUser.getId()%>/home'" /> --%>
		<%}
		}%>

		<%if (!isMe) {%>
		<input class="common-submit button" type="button" value="私信"
			onclick="location.href='/designer-front/settings/chat?toId=<%=queryUser.getId()%>'" />
		<%}%>
		
		<input class="common-submit button" type="button" value="作品辑"
			onclick="location.href='/designer-front/<%=queryUser.getId()%>/home'" />
		<%}%>
		
		<input class="common-submit button" type="button" value="个人资料"
			onclick="location.href='/designer-front/<%=queryUser.getId()%>/info'" />	
		<%if (isMe) {%>
		<input class="common-submit button" type="button" value="个人设置"
			onclick="location.href='/designer-front/settings'" />
		<%}%>
	</form>
</div>
<%}%>