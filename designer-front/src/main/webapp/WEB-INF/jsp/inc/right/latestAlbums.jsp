<%@ page language='java' contentType='text/html; charset=utf-8'%>
<%@ page import='com.bruce.designer.model.*'%>
<%@ page import='com.bruce.designer.front.constants.*'%>
<%@ page import='com.bruce.designer.constants.*'%>
<%@ page import='java.util.*'%>
<%@ page import='java.text.*'%>


<div class='widget-box widget-flickr'>
	<div class='content-title'>
		<h4 class='widget-title'>新晋作品辑</h4>
	</div>
	
	
	
	<div class='flickr-wrap clearfix' id="slideLatestAlbumsContainer">
		<!-- <div class='flickr_badge_image' id='flickr_badge_image2'>
			<a href='http://www.flickr.com/photos/we-are-envato/10323793146/'><img
				src='http://farm4.staticflickr.com/3801/10323793146_66337e58d1_s.jpg'
				alt='Flickr 上的一張相片'
				title='Selina (Internal Comms) Deciding Between iPhone &amp; Samsung'
				height='75' width='75'></a>
		</div> -->
	</div>
</div>
<script>
fallLoad();

function fallLoad(){
	$.get('/designer-front/sideLatestAlbums.json', function(data) {
		var result = data.result;
		if(result==1){
			$("#slideLatestAlbumsContainer").append(data.data.html);
		}else{
			/* alert("error"); */
		}
	});
}
</script>


<!-- Blogpost Widget -->
	<!-- 设计师最新作品 style1 瀑布式 -->
	<!-- <div class="widget-box widget-blogposts">
		<div class="content-title">
			<h4 class="widget-title">设计师最新作品</h4>
		</div>
		<ul>
			<li class="clearfix">
				<div class="widget-blogpost-avatar">
					<a href="post-gallery.html#"> <img
						src="/designer-front/img/demo/ecopower-70x70.jpg" alt="Blogpost-1">
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
						src="/designer-front/img/demo/architecture-70x70.jpg"
						alt="Blogpost-1">
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
						src="/designer-front/img/demo/ecohands-70x70.jpg" alt="Blogpost-1">
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
	</div> -->