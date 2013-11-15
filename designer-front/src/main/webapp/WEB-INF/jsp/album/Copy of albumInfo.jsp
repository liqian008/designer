<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="org.apache.commons.lang3.time.DateFormatUtils"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
<title>Verendus - Multipurpose Business Template</title>

<meta name="description"
	content="Verendus - A HTML5 / CSS3 Multipurpose Business Template">
<meta name="keywords"
	content="Bootstrap, Verendus, HTML5, CSS3, Business, Multipurpose, Template">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/designer-front/css/bootstrap.min.css">
<link rel="stylesheet" href="/designer-front/css/font-awesome.css">
<link rel="stylesheet" href="/designer-front/css/animate.css">
<link rel="stylesheet" href="/designer-front/css/flexslider.css">
<link rel="stylesheet" href="/designer-front/css/style.css">
<!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="/designer-front/css/ie8.css" />    
        <![endif]-->

<script
	src="/designer-front/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
<script src="/designer-front/js/vendor/jquery-1.8.3.min.js"></script>

<link href='http://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:700'
	rel='stylesheet' type='text/css'>
</head>
<body class="body-background"
	style="background-image: url(/designer-front/img/backgrounds/bg3.jpg);">

	<!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

	<jsp:include page="../inc/topBar.jsp"></jsp:include>


	<div id="wrapper" class="boxed">
		<!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page -->

		<div class="header-background">
			<!-- Header Background -->

			<jsp:include page="../inc/headerBanner.jsp"></jsp:include>

			<div class="header-wrap">
				<!-- Header Wrapper, contains Mene and Slider -->
				<jsp:include page="../inc/headerNav.jsp"></jsp:include>

				<div class="page-title">
					<div class="container">
						<!-- <div class="page-title-avatar">
                                <img src="./img/demo/portraits/portrait-21.png" alt="Page Title" width="80" height="80"/>
                            </div> -->
						<div class="page-title-content">
							<h2>此处放置广告作品</h2>
							<p class="page-description">With this gallery you can create
								a blogpost with multiple images. With the FlexSlider or Twitter
								Bootstrap Carousel you can rotate between these images.</p>
						</div>
					</div>
				</div>
			</div>
			<!-- Close Header Menu -->
		</div>
		<!-- Close Header Wrapper -->
		<div class="page-top-stripes"></div>
		<!-- Page Background Stripes -->

		<div class="page">
			<!-- Page -->
			<div class="breadscrumbs">
				<div class="container">
					<ul class="clearfix">
						<li><a href="/designer-front/">首页</a>/</li>
						<li><a href="javascript:void(0)">作品</a></li>
					</ul>
				</div>
			</div>
			<div class="main fullwidth">
				<div class="container">
					<div class="row-fluid">


						<section class="content span9">
							<%
							Album album = (Album)request.getAttribute("albumInfo");
							Integer slideIndex = (Integer)request.getAttribute("slideIndex");
							AlbumSlide albumSlide = null;
							if(album!=null){
								List<AlbumSlide> slideList = album.getSlideList();
								albumSlide = slideList.get(slideIndex);
								
							%>

							<article class="post format-image clearfix">
								<div class="divider-text divider-mid">
									<span><%=album.getTitle()%>【<%=slideList.size()%>张】</span>
								</div>
								<%
								
									if(slideIndex!=null){
								%>
								<div class="single-navigation navigation clearfix">
									<%if(slideIndex>0){%>
									<a href="/designer-front/album/<%=album.getId() %>/<%=slideList.get(slideIndex-1).getId()%>" class="nav-left"><span></span>上一张</a>
									<%}
									if(slideIndex<slideList.size()-1){
									%><a href="/designer-front/album/<%=album.getId() %>/<%=slideList.get(slideIndex+1).getId()%>" class="nav-right">下一张<span></span></a>
									<%}%>
								</div>
								<%}%>
								<div class="post-thumb preload">
									<span class="preload-done"><img
										src="<%=albumSlide.getSlideLargeImg()%>" alt=""
										style="display: block; visibility: visible; opacity: 1;"></span>
								</div>

								<div class="row-fluid clearfix">

									<div class="meta-container-single clearfix">
										<div class="meta-tags">
											<ul>
												<li><a href="single.html#">翡翠</a>,</li>
												<li><a href="single.html#">玉石</a></li>
											</ul>
										</div>

										<div class="meta-categories">
											<ul>
												<li><a href="single.html#">举报</a></li>
											</ul>
											<ul>
												<li><a href="javascript:void(0)">浏览(<%=albumSlide.getBrowseCount()%>)</a></li>
											</ul>
											<ul>
												<li><a href="javascript:void(0)" id="likeLink">喜欢(<%=albumSlide.getLikeCount()%>)</a></li>
											</ul>
											<ul>
												<li><a href="javascript:void(0)">收藏(<%=albumSlide.getFavoriteCount()%>)</a></li>
											</ul>

											<ul>
												<li><a href="javascript:void(0)" id="commentLink">评论(<%=albumSlide.getCommentCount()%>)</a>
												</li>
											</ul>
										</div>
									</div>

									<div id="comments">
										<ol id="commentListId" class="commentlist">
											<%
                                        	List<Comment> commentList = (List<Comment>)request.getAttribute("commentList");
                                        	if(commentList!=null){
                                        		for(Comment comment: commentList){
                                        	%>
											<li class="comment depth-1" id="li-comment-1">
												<div class="comment-container" id="comment-1">
													<div class="comment-avatar">
														<div class="comment-author vcard">
															<img src="<%=comment.getUserHeadImg()%>"/>
														</div>
													</div>
													<div class="comment-body">
														<div class="comment-meta commentmetadata">
															<h6 class="comment-author">
																<a href='#' rel='external nofollow' class='url'><%=comment.getNickname()%></a>
																发表于
																<%=DateFormatUtils.format(comment.getCreateTime(), ConstDateFormat.YYYYMMDD_HHMM_FORMAT)%>
															</h6>
														</div>
														<div class="comment-content">
															<%=comment.getComment()%>
														</div>
													</div>
												</div>
											</li>
											<%}
                                            }%>
											<li class="comment depth-1" id="li-comment-1">
												<div class="comment-container" id="comment-1">
													<div class="comment-body">
														<div class="comment-content">
															<%if(commentList==null||commentList.size()==0) {%>
																暂无评论...
															<%}%>
															<%if(commentList!=null&&commentList.size()>0) {%>
																<a href="javascript:void(0)">加载更多评论...</a>
															<%}%>
														</div>
													</div>
												</div>
											</li>
										</ol>

										<div id="respond">
											<div class="content-title">
												<h4>发表新评论</h4>
											</div>
											<%if(currentUser==null){%>
											<form action="/designer-front/loginBack" method="post"
												id="loginBackForm" class="form">
												<div class="span9">
													发表评论需要您先登录！ <input
														class="button button-small button-orange" type="button"
														name="loginBtn" id="loginBtn" tabindex="5" value="登 录" />
													<input class="button button-small button-blue"
														type="button" name="regBtn" id="regBtn" tabindex="5"
														value="注册新用户" />
												</div>
											</form>
											<%}else{%>
											<form action="#" method="post" id="commentform" class="form">
												<div class="span9">
													<p>
														<textarea class="comment_textarea" name="comment"
															id="comment" cols="50" rows="2" tabindex="4">我要评论...</textarea>
													</p>
												</div>
												<div class="span3">
													<input class="button button-small button-orange"
														type="button" name="publishBtn" id="publishBtn"
														tabindex="5" value="发表评论" />
												</div>
												<input type="hidden" name="albumId"
													value="<%=album.getId()%>" /> <input type="hidden"
													name="albumSlideId" value="" /> <input type="hidden"
													name="toId" value="<%=album.getUserId()%>" /> <input
													type="hidden" name="designerId"
													value="<%=album.getUserId()%>" />
											</form>
											<%}%>
										</div>
									</div>
									<!-- Close Comments -->
								</div>
							</article>
							<%}%>
						</section>

						<jsp:include page="../inc/designerBox.jsp"></jsp:include>

					</div>
				</div>
				<!-- Close Main -->
			</div>

			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- Close Page -->
	</div>
	<!-- Close wrapper -->


	<!-- Load all Javascript Files -->
	<script src="/designer-front/js/vendor/bootstrap.min.js"></script>
	<script src="/designer-front/js/jquery.hoverdir.js"></script>
	<script src="/designer-front/js/superfish.js"></script>
	<!-- <script src="/designer-front/js/supersubs.js"></script> -->
	<!--  <script src="/designer-front/js/jquery.tweet.js"></script>  -->
	<script src="/designer-front/js/jquery.flexslider.js"></script>
	<script src="/designer-front/js/retina.js"></script>

	<script src="/designer-front/js/custom.js"></script>

	<script>
    	$("#commentLink").click(function(){
    		 $("#comment").focus();
    		 $("#comment").val("");
    	});
    	$("#likeLink").click(function(){
    	$.post("/designer-front/like.json", {'albumId':'<%=album.getId()%>','albumSlideId':'<%=albumSlide.getId()%>'}, function(data) {
  			  alert("result: " + data.result);
  		 }, "json");
   	});
    	$("#publishBtn").click(function(){
    		//disable submitBtn
    		 $.post("/designer-front/comment.json", {"comment":"hello","albumId":"<%=album.getId()%>","albumSlideId":"<%=albumSlide.getId()%>", "toId":"<%=album.getUserId()%>", "designerId":"<%=album.getUserId()%>"}, function(data) {
    			  alert("result: " + data.result);
    			  alert("message: " + data.data);
    			  $("#commentListId").prepend(data.data);
    			//enable submitBtn
    		 }, "json"); 
    	});
    	
    	$("#loginBtn").click(function(){
			$("#loginBackForm").submit();
    	});
    	$("#regBtn").click(function(){
			location.href="/designer-front/register";
    	});
    </script>

</body>
</html>
