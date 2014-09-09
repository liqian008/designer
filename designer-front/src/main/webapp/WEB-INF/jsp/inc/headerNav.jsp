<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>

<%
String contextPath = ConstFront.CONTEXT_PATH;

User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER); 
%>

<!--PAGE HEAD-->
<div class="header-menu">
	<!-- Main Menu -->
	<div class="container">
		<div class="row-fluid">
			<nav class="nav-menu">

				<div class="menu-mobile-wrapper">
					<!-- Menu Mobile Wrapper -->
					<a id="menu-mobile-trigger"></a>
				</div>

				<span class="menu-slider hidden-phone"></span>
				
				
				<%
				int menuIndex = 0;
				String tempFlag = request.getParameter("menuFlag");
				if("albums".equals(tempFlag)){
					menuIndex = 1;
				}else if("designers".equals(tempFlag)){
					menuIndex = 2;
				}else if("myFollow".equals(tempFlag)){
					menuIndex = 3;
				}else if("myHome".equals(tempFlag)){
					menuIndex = 4;
				//}else if("settings".equals(tempFlag)){
				//	menuIndex = 5;
				}else if("aboutUs".equals(tempFlag)){
					menuIndex = 6;
				}else{
					menuIndex = 0;
				}
				%>
				
				<!-- Menu Slider -->
				<ul id="header-menu" class="menu">
					<li <%=menuIndex==0?"class='active current-menu-item'":"" %>><a
						href="<%=contextPath%>/">首页</a></li> 
					<li <%=menuIndex==1?"class='active current-menu-item'":"" %>><a href="<%=contextPath%>/albums">作品辑</a>
					<ul class="sub-menu">
						<li><a href="<%=contextPath%>/albums">新晋作品辑</a></li>
						<li><a href="<%=contextPath%>/hot/weeklyAlbums">热门作品辑</a>
							<ul class="sub-menu">
	                            <li><a href="<%=contextPath%>/hot/dailyAlbums">本日热门</a></li>
	                            <li><a href="<%=contextPath%>/hot/weeklyAlbums">本周热门</a></li>
	                            <li><a href="<%=contextPath%>/hot/monthlyAlbums">本月热门</a></li>
	                            <li><a href="<%=contextPath%>/hot/yearlyAlbums">年度热门</a></li>
	                        </ul>
						</li>
					</ul></li>
					<li <%=menuIndex==2?"class='active current-menu-item'":"" %>><a href="<%=contextPath%>/designers">设计师</a>
						<ul class="sub-menu">
							<li><a href="<%=contextPath%>/designers">新晋设计师</a></li>
							<li><a href="<%=contextPath%>/hot/weeklyDesigners">热门设计师</a>
								<ul class="sub-menu">
		                            <li><a href="<%=contextPath%>/hot/dailyDesigners">本日热门</a></li>
		                            <li><a href="<%=contextPath%>/hot/weeklyDesigners">本周热门</a></li>
		                            <li><a href="<%=contextPath%>/hot/monthlyDesigners">本月热门</a></li>
		                            <li><a href="<%=contextPath%>/hot/yearlyDesigners">年度热门</a></li>
		                        </ul>
							</li>
						</ul></li> 
					<li <%=menuIndex==3?"class='active current-menu-item'":"" %>><a href="<%=contextPath%>/followAlbums">我的关注</a>
					</li>
					
					<%if(currentUser!=null){%>
					<li <%=menuIndex==4?"class='active current-menu-item'":"" %>><a href="<%=contextPath%>/<%=currentUser.getId()%>/home">我的主页</a>
						<ul class="sub-menu">
							<%
							boolean isDesigner = currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;
							%>
								<li><a href="<%=contextPath%>/<%=currentUser.getId()%>/home">我的作品辑</a></li>
								<li><a href="<%=contextPath%>/<%=currentUser.getId()%>/info">我的资料</a></li>
								<li><a href="<%=contextPath%>/<%=currentUser.getId()%>/follows">我的关注</a></li>
							<%if(isDesigner){%>
								<li><a href="<%=contextPath%>/<%=currentUser.getId()%>/fans">我的粉丝</a></li>
							<%}%>
						</ul>
					</li>
					
					<%}%>

					<li><a href="<%=contextPath%>/downloads/">下载客户端</a>
					</li>
					<li <%=menuIndex==6?"class='active current-menu-item'":"" %>><a
						href="<%=contextPath%>/aboutUs">关于我们</a>
					</li>
				</ul>
			</nav>

			<!-- Menu Search Form -->
			<!-- <div class="searchform">
				<form method="get" id="searchform" action="post-gallery.html#"
					class="clearfix">
					<input type="text" name="s" id="s" value="Search.."
						onfocus="if(this.value=='Search..')this.value='';"
						onblur="if(this.value=='')this.value='Search..';" />
				</form>
			</div> -->
			<!-- Close Menu Search Form -->
		</div>
	</div>
</div>
<!-- Close Main Menu -->
<!--/PAGE HEAD-->