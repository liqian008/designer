<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>

<%
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
				<!-- Menu Slider -->
				<ul id="header-menu" class="menu">
					<li class="active current-menu-item"><a
						href="/designer-front/index">首页</a></li> 
					<li><a href="/designer-front/albums">作品辑</a>
					<ul class="sub-menu">
						<li><a href="/designer-front/albums">新晋作品辑</a></li>
						<li><a href="/designer-front/hot/albums">热门作品辑</a></li>
					</ul></li>
					<li><a href="/designer-front/designers">设计师</a>
						<ul class="sub-menu">
							<li><a href="/designer-front/designers">新晋设计师</a></li>
							<li><a href="/designer-front/hot/designers">热门设计师</a></li>
						</ul></li>
					<li><a href="/designer-front/followView">我的关注</a></li>  
					<%if(currentUser!=null){%>
					<li><a href="/designer-front/<%=currentUser.getId()%>/home">我的作品</a></li> 
					<%}%>
					<!-- <li><a href="/designer-front/pro">精品推荐</a></li> -->
					<!-- <li><a href="/designer-front/index">新晋推荐</a>
						<ul class="sub-menu">
							<li><a href="/designer-front/index">新作推荐</a></li>
							<li><a href="/designer-front/index">新人推荐</a></li>
						</ul></li> -->
					<!-- <li><a
						href="javascript:void(0)">搜索</a>
					</li> -->
					<li><a
						href="http://somnia-themes.com/templates/verendus/pages/contact.html">联系我们</a>
					</li>
				</ul>
			</nav>

			<!-- Menu Search Form -->
			<div class="searchform">
				<form method="get" id="searchform" action="post-gallery.html#"
					class="clearfix">
					<input type="text" name="s" id="s" value="Search.."
						onfocus="if(this.value=='Search..')this.value='';"
						onblur="if(this.value=='')this.value='Search..';" />
				</form>
			</div>
			<!-- Close Menu Search Form -->
		</div>
	</div>
</div>
<!-- Close Main Menu -->
<!--/PAGE HEAD-->