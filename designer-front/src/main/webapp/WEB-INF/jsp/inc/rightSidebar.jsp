<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
%>

<aside class="sidebar widgets-light span3">

	<%if(user==null){%>
		<!-- Contactform Widget -->
		<div class="widget-box widget-contact-form">
			<div class="content-title">
				<h4 class="widget-title">用户登录</h4>
			</div>
			<form id="contact-form-widget" method="post" class="clearfix" action="/designer-front/login.art">
				<div class="input-container">
					<input type="text" class="contact-form-name" name="username"
						value="liqian"
						onfocus="if(this.value=='Your Name')this.value='';"
						onblur="if(this.value=='')this.value='Your Name';" /> 
					<i class="icon-user"></i>
				</div>
				<div class="input-container">
					<input type="password" class="contact-form-email" name="password"
						value="liqian"/> 
						<i class="icon-envelope-alt"></i>
				</div>
				<input class="contact-submit button" type="button" value="微博登录" onclick="location.href='/designer-front/connectWeibo.art'"/>
				<input class="contact-submit button" type="button" value="QQ登录" onclick="location.href='/designer-front/connectTencent.art'"/>
				<input class="contact-submit button" type="submit" value="登 录">
				<input class="contact-submit button" type="button" value="注 册"  onclick="location.href='/designer-front/register.art'">
			</form>
		</div>
	<%}else{%>
		<div class="widget-box widget-contact-form">
		<div class="content-title">
			<h4 class="widget-title">个人中心</h4>
		</div>
		<form id="contact-form-widget" method="post" class="clearfix" action="/designer-front/login.art">
			<ul>
				<li class="clearfix">
					<div class="widget-blogpost-avatar">
						<a href="post-gallery.html#">
						<img src="/designer-front/img/demo/portraits/1_avatar_middle.jpg" alt="Blogpost-1">
						</a>
					</div>
					<div class="widget-blogpost-content">
						<div class="widget-blogpost-date">
							<p>
								您好：<%=user.getNickname()%>
							</p>
							<p>
								身份：设计师
							</p>
							<p>
								未读消息：2条
							</p>
						</div>
					</div>
				</li>
			</ul>
			<input class="contact-submit button" type="button" value="个人设置" onclick="location.href='/designer-front/settings.art'"/>
			 <%if(user.getDesignerStatus()!=null&&user.getDesignerStatus()==ConstService.DESIGNER_APPLY_PASSED){%> 
				<input class="contact-submit button" type="button" value="我要发布"  onclick="location.href='/designer-front/settings.art?op=publisher'"/>
				<input class="contact-submit button" type="button" value="发布设置"  onclick="location.href='/designer-front/settings.art?op=shareSettings'"/>
			<%}else{%>
				<input class="contact-submit button" type="button" value="申请设计师"  onclick="location.href='/designer-front/settings.art?op=designerApply'"/>
			<%}%>
			<input class="contact-submit button" type="button" value="注 销" onclick="location.href='/designer-front/logout.art'"/>
			
		</form>
	</div>
	<%}%>
	
	<div class="widget-box widget-social-media">
		<div class="content-title">
			<h4 class="widget-title">热门设计师</h4>
		</div>
		<ul class="clearfix">
			<li class="social-icons-facebook-icon">
				<a href="#"><img src="/designer-front/img/demo/portraits/avatar_middle.jpg" alt="Page Title"/></a>
			</li>
			<li class="social-icons-facebook-icon">
				<a href="#"><img src="/designer-front/img/demo/portraits/avatar_middle.jpg" alt="Page Title"/></a>
			</li>
			<li class="social-icons-facebook-icon">
				<a href="#"><img src="/designer-front/img/demo/portraits/avatar_middle.jpg" alt="Page Title"/></a>
			</li>
			<li class="social-icons-facebook-icon">
				<a href="#"><img src="/designer-front/img/demo/portraits/avatar_middle.jpg" alt="Page Title"/></a>
			</li>
			<li class="social-icons-facebook-icon">
				<a href="#"><img src="/designer-front/img/demo/portraits/avatar_middle.jpg" alt="Page Title"/></a>
			</li>
		</ul>
	</div>

	<!-- Flickr Widget -->
	<!-- 
	<div class="widget-box widget-flickr">
		<div class="content-title">
			<h4 class="widget-title">热门设计师</h4>
		</div>

		<div class="flickr-wrap clearfix">
			<div class="flickr_badge_image" id="flickr_badge_image1">
				<a href="http://www.flickr.com/photos/we-are-envato/7694616386/"><img
					src="http://farm9.staticflickr.com/8153/7694616386_a1ed62f0bf_s.jpg"
					alt="Flickr 上的一張相片" title="New York Meetup 2012" height="75"
					width="75"></a>
			</div>
			<div class="flickr_badge_image" id="flickr_badge_image2">
				<a href="http://www.flickr.com/photos/we-are-envato/8954583944/"><img
					src="http://farm4.staticflickr.com/3671/8954583944_610e0758cf_s.jpg"
					alt="Flickr 上的一張相片" title="Coffee break!" height="75" width="75"></a>
			</div>
			<div class="flickr_badge_image" id="flickr_badge_image3">
				<a href="http://www.flickr.com/photos/we-are-envato/8228352489/"><img
					src="http://farm9.staticflickr.com/8065/8228352489_6249d2841f_s.jpg"
					alt="Flickr 上的一張相片" title="Pressnomics Conference - 2012"
					height="75" width="75"></a>
			</div>
			<div class="flickr_badge_image" id="flickr_badge_image4">
				<a href="http://www.flickr.com/photos/we-are-envato/8355850981/"><img
					src="http://farm9.staticflickr.com/8047/8355850981_feec796190_s.jpg"
					alt="Flickr 上的一張相片" title="Envato Christmas Party 2012" height="75"
					width="75"></a>
			</div>
			<div class="flickr_badge_image" id="flickr_badge_image5">
				<a href="http://www.flickr.com/photos/we-are-envato/7694617332/"><img
					src="http://farm9.staticflickr.com/8431/7694617332_d79b94dd48_s.jpg"
					alt="Flickr 上的一張相片" title="New York Meetup 2012" height="75"
					width="75"></a>
			</div>
			<div class="flickr_badge_image" id="flickr_badge_image6">
				<a href="http://www.flickr.com/photos/we-are-envato/8886280231/"><img
					src="http://farm8.staticflickr.com/7380/8886280231_a527a8527d_s.jpg"
					alt="Flickr 上的一張相片" title="Microlancer Wallpaper 2" height="75"
					width="75"></a>
			</div>
			<div class="flickr_badge_image" id="flickr_badge_image7">
				<a href="http://www.flickr.com/photos/we-are-envato/8140535004/"><img
					src="http://farm9.staticflickr.com/8469/8140535004_46edff8000_s.jpg"
					alt="Flickr 上的一張相片" title="Envato Halloween 2012" height="75"
					width="75"></a>
			</div>
			<div class="flickr_badge_image" id="flickr_badge_image8">
				<a href="http://www.flickr.com/photos/we-are-envato/7694621116/"><img
					src="http://farm9.staticflickr.com/8421/7694621116_a1749d4979_s.jpg"
					alt="Flickr 上的一張相片" title="New York Meetup 2012" height="75"
					width="75"></a>
			</div>
			<div class="flickr_badge_image" id="flickr_badge_image9">
				<a href="http://www.flickr.com/photos/we-are-envato/8228351611/"><img
					src="http://farm9.staticflickr.com/8481/8228351611_96503cc178_s.jpg"
					alt="Flickr 上的一張相片" title="Pressnomics Conference - 2012"
					height="75" width="75"></a>
			</div>
		</div>
	</div>
	-->

	<!-- Blogpost Widget -->
	<div class="widget-box widget-blogposts">
		<div class="content-title">
			<h4 class="widget-title">热门作品</h4>
		</div>
		<ul>
			<li class="clearfix">
				<div class="widget-blogpost-avatar">
					<a href="post-gallery.html#"> <img
						src="/designer-front/img/demo/ecopower-70x70.jpg" alt="Blogpost-1"> <span
						class="widget-blogpost-overlay"></span>
					</a>
				</div>
				<div class="widget-blogpost-content">
					<div class="widget-blogpost-title ">
						<h5>Just a simple blogtitle with two lines of text.</h5>
					</div>
					<div class="widget-blogpost-date">
						<p>
							<a href="post-gallery.html#">December 8, 2012</a>
						</p>
					</div>
				</div>
			</li>
			<li class="clearfix">
				<div class="widget-blogpost-avatar">
					<a href="post-gallery.html#"> <img
						src="/designer-front/img/demo/architecture-70x70.jpg" alt="Blogpost-1"> <span
						class="widget-blogpost-overlay"></span>
					</a>
				</div>
				<div class="widget-blogpost-content">
					<div class="widget-blogpost-title ">
						<h5>Just a simple blogtitle with two lines of text.</h5>
					</div>
					<div class="widget-blogpost-date">
						<p>
							<a href="post-gallery.html#">December 8, 2012</a>
						</p>
					</div>
				</div>
			</li>
			<li class="clearfix">
				<div class="widget-blogpost-avatar">
					<a href="post-gallery.html#"> <img
						src="/designer-front/img/demo/ecohands-70x70.jpg" alt="Blogpost-1"> <span
						class="widget-blogpost-overlay"></span>
					</a>
				</div>
				<div class="widget-blogpost-content">
					<div class="widget-blogpost-title ">
						<h5>Just a simple blogtitle with two lines of text.</h5>
					</div>
					<div class="widget-blogpost-date">
						<p>
							<a href="post-gallery.html#">December 8, 2012</a>
						</p>
					</div>
				</div>
			</li>
		</ul>
	</div>
	
	<script src="./js/jquery.tagcloud.js"></script>
	<script>
        $.fn.tagcloud.defaults = {
          size: {start: 8, end: 13, unit: 'pt'},
          color: {start: '#cde', end: '#f52'}
        };

        $(function () {
          $('#tagcloud a').tagcloud();
        });
    </script>
	<div class="widget-box widget-social-media">
		<div class="content-title">
			<h4 class="widget-title">热门标签</h4>
		</div>
		<div id="tagcloud">
		    <a href="#" rel="0">Lorem</a>
		    <a href="#" rel="2">ipsum</a>
		    <a href="#" rel="3">dolor</a>
		    <a href="#" rel="4">sit</a>
		    <a href="#" rel="5">amet,</a>
		    <a href="#" rel="6">consectetur</a>
		    <a href="#" rel="7">adipisicing</a>
		    <a href="#" rel="8">elit,</a>
		    <a href="#" rel="5">sed</a>
		    <a href="#" rel="7">do</a>
		    <a href="#" rel="3">eiusmod</a>
		    <a href="#" rel="1">tempor</a>
		    <a href="#" rel="12">incididunt</a>
		    <a href="#" rel="1">ut</a>
		    <a href="#" rel="2">labore</a>
		    <a href="#" rel="5">et</a>
		    <a href="#" rel="7">dolore</a>
		    <a href="#" rel="1">magna</a>
		    <a href="#" rel="4">aliqua.</a>
		    <a href="#" rel="1">Ut</a>
		    <a href="#" rel="2">enim</a>
		    <a href="#" rel="10">ad</a>
		    <a href="#" rel="4">minim</a>
		    <a href="#" rel="1">veniam,</a>
		    <a href="#" rel="1">quis</a>
		</div>
	</div>

</aside>