<%@ page language="java" contentType="text/html; charset=utf-8"%>

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
					<li><a href="./index.art">首页</a></li>
					<li><a href="./index.art">热门排行</a>
						<ul class="sub-menu">
							<li><a
								href="./index.art">本周热门 - Top10</a></li>
							<li><a
								href="./index.art">本周热门 - Top10</a></li>
							<li><a
								href="./index.art">本月热门 - Top10</a></li>
						</ul></li>
					
					<li class="active current-menu-item"><a
						href="http://somnia-themes.com/templates/verendus/blog.html">Blog</a>
						<ul class="sub-menu">
							<li><a href="post-aside.html">Post Aside</a></li>
							<li><a href="post-audio.html">Post Audio</a></li>
						</ul></li>
					<li><a
						href="http://somnia-themes.com/templates/verendus/pages/contact.html">联系我们</a>
					</li>
					<li><a
						href="http://somnia-themes.com/templates/verendus/pages/contact.html">关于我们</a>
					</li>
				</ul>
			</nav>
			<div class="searchform">
				<!-- Menu Search Form -->
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