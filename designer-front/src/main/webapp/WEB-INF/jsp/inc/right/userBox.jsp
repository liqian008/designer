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
						src="<%=UploadUtil.getAvatarUrl(queryUser.getId(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM)%>">
					</a>
				</div>
				<div class="widget-blogpost-content">
					<div class="widget-blogpost-date">
						<p>
							昵 称:&nbsp;<%=queryUser.getNickname()%>
						</p>
						<%if(isDesigner){ %>
						<p>专辑数:&nbsp;x个</p>
						<p>粉丝数:&nbsp;<span class="fansCount">0</span>个</p>
						<%}%>
						<p>关注数:&nbsp;<span class="followsCount">0</span>个</p>
					</div>
				</div>
			</li>
		</ul>

		<%if(isDesigner){%>
			<%if(!isMe){%>
			<%
				Boolean hasFollowed = (Boolean) request.getAttribute("hasFollowed");
				String hideStr = "style='display:none'";
			%>
			<input type="button" id="followBtn" class="followBtn common-button button button-green" value="关 注" <%=hideStr%>/>
			<input type="button" id="unfollowBtn" class="unfollowBtn common-button button button-white" value="取消关注" <%=hideStr%>/>
			
			<%}%>
			
			<script>
			//初始化加载用户资料&状态
			var queryJsonData = {"queryUserId": <%=queryUser.getId()%>};
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
			
			//触发关注操作
			$(".followBtn").click(function(){
				$(".followBtn").attr("disabled", "disabled");
				var followJsonData = {"uid": <%=queryUser.getId()%>};
				$.post("/designer-front/follow.json", followJsonData, function(data) {
					$(".followBtn").removeAttr("disabled");
					if(data.result==1){
						$(".unfollowBtn").show();
						$(".followBtn").hide();
						//更新粉丝数的显示
						$('.fansCount').each(function(){
							var fansCount = parseInt($(this).text())+1;
							$(this).text(fansCount);
						});
					}else{
						alert(data.message);
					}
				 }, "json");
			});
			
			//触发取消关注操作
			$(".unfollowBtn").click(function(){
				$(".unfollowBtn").attr("disabled", "disabled");
				var unfollowJsonData = {"uid": <%=queryUser.getId()%>};
				$.post("/designer-front/unfollow.json", unfollowJsonData, function(data) {
					$(".unfollowBtn").removeAttr("disabled");
					if(data.result==1){
						$(".followBtn").show();
						$(".unfollowBtn").hide();
						//更新粉丝数的显示
						$('.fansCount').each(function(){
							var fansCount = parseInt($(this).text())-1;
							if(fansCount<0){//确保不会出现负数
								fansCount = 0;
							}
							$(this).text(fansCount);
						});
					}else{
						alert(data.message);
					}
				 }, "json");
			});
			
			</script>
			
		<%}%>
		
		<%if (!isMe) {%>
			<input type="button" class="common-button button" value="私信"
			onclick="location.href='/designer-front/settings/msgbox/chat?toId=<%=queryUser.getId()%>'" />
		<%}%>
		
		<input type="button" class="common-button button button-blue" value="个人主页"
			onclick="location.href='/designer-front/<%=queryUser.getId()%>/home'" />	
		<%if (isMe) {%>
		<input type="button" class="common-button button" value="个人设置"
			onclick="location.href='/designer-front/settings'" />
		<%}%>
	</form>
	
</div>
<%}%>