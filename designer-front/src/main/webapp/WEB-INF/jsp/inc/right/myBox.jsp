<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.util.*"%>
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
					<a href="/designer-front/settings/avatar" title="点击修改头像">
						<img src="<%=UploadUtil.getAvatarUrl(currentUser.getId(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM)%>" alt="点击修改头像">
					</a> 
				</div>
				<div class="widget-blogpost-content">
					<div class="widget-blogpost-date">
						<p>
							昵 称:&nbsp;<%=currentUser.getNickname()%>
						</p>
						<%if(isDesigner){ %>
						<p>专辑数:&nbsp;x个</p>
						<p>粉丝数:&nbsp;<span class="fansCount">0</span>个</p>
						
						<script>
						//初始化加载用户资料&状态
						var queryJsonData = {"queryUserId": <%=currentUser.getId()%>};
						$.post("/designer-front/userboxInfo.json", queryJsonData, function(responseData) {
							if(responseData.result==1){
								$('.fansCount').each(function(){
									$(this).text(responseData.data.fansCount);
								});
								$('.followsCount').each(function(){
									$(this).text(responseData.data.followsCount); 
								});
								if(responseData.data.hasFollowed==true){//已关注
									$('.followBtn').hide();
									$('.unfollowBtn').show();
								}else{
									$('.unfollowBtn').hide();
									$('.followBtn').show();
								}
							}else{
								alert(responseData.message);
							}
						 }, "json");
						</script>
						<%}%>
						<p>关注数:&nbsp;<span class="followsCount">0</span>个</p>
					</div>
					
					
					
				</div>
			</li>
		</ul>
		<%
		%>
		<%if(isDesigner){%>
			<input class="common-button button button-green" type="button" value="发布作品"
			onclick="location.href='/designer-front/settings/newAlbum'" />
		<%} %>
		<input class="common-button button button-blue" type="button" value="个人主页"
			onclick="location.href='/designer-front/<%=currentUser.getId()%>/home'" />
			
		<input class="common-button button" type="button" value="修改密码"
			onclick="location.href='/designer-front/settings/changePasswd'" />
		
		
	</form>
</div>
<%}%>