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

		<div class="widget-box widget-contact-form">
		<div class="content-title">
			<h4 class="widget-title">设计师个人资料</h4>
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
								设计师：<%=user.getNickname()%>
							</p>
							<p>
								作品数量：xx个
							</p>
							<p>
								10 粉丝
							</p>
						</div>
					</div>
				</li>
			</ul>
			<input class="contact-submit button" type="button" value="设计师主页" onclick="location.href='/designer-front/profile/<%=user.getId() %>.art'"/>
			<input class="contact-submit button" type="button" value="新浪微博" onclick="location.href='/designer-front/settings.art'"/>
		</form>
	</div>
	
	<!-- Blogpost Widget -->
	<div class="widget-box widget-blogposts">
		<div class="content-title">
			<h4 class="widget-title">设计师相关作品</h4>
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
	
	<div class="widget-box widget-social-media">
		<div class="content-title">
			<h4 class="widget-title">热门设计师推荐</h4>
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
	
	<script src="/designer-front/js/jquery.tagcloud.js"></script>
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