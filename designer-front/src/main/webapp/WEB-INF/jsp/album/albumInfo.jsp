<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="org.apache.commons.lang3.time.DateFormatUtils"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
String contextPath = ConstFront.CONTEXT_PATH;
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);

User queryUser =  (User)request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);
Album album = (Album)request.getAttribute("albumInfo");
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
<title><%=album.getTitle()%> - 【金玩儿网】</title>

<meta name="description"
	content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
<meta name="keywords"
	content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="icon" href="<%=contextPath%>/img/favicon.ico">
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=contextPath%>/css/font-awesome.css">
<link rel="stylesheet" href="<%=contextPath%>/css/animate.css">
<link rel="stylesheet" href="<%=contextPath%>/css/flexslider.css">
<link rel="stylesheet" href="<%=contextPath%>/css/style.css">
<!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/css/ie8.css" />    
        <![endif]-->

	<script src="<%=contextPath%>/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
	<script src="<%=contextPath%>/js/vendor/jquery-1.8.3.min.js"></script>

<jsp:include page="../inc/baiduAsyncStat.jsp"></jsp:include>

</head>
<body class="body-background"
	style="background-image: url(<%=contextPath%>/img/backgrounds/bg.jpg);">

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
				
				<jsp:include page="../inc/ad.jsp"></jsp:include>
				
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
						<li><a href="<%=contextPath%>/">首页</a>/</li>
						<li><a href="javascript:void(0)">专辑展示</a>/</li>
						<li><a href="javascript:void(0)"><%=album.getTitle()%></a></li>
					
					</ul>
				</div>
			</div>
			<div class="main fullwidth">
				<div class="container">
					<div class="row-fluid">

						<section class="content span9">
							<%
							Integer slideIndex = (Integer)request.getAttribute("slideIndex");
							AlbumSlide albumSlide = null;
							if(album!=null){
								List<AlbumSlide> slideList = album.getSlideList();
								albumSlide = slideList.get(slideIndex);
							%>

							<article class="format-blogpost blogpost-single clearfix">
								<div class="divider-text divider-mid">
									<span><%=album.getTitle()%>【<%=slideIndex+1%> / <%=slideList.size()%>张】</span>
								</div>
								<%
								if(slideIndex!=null){
								%>

								<img src="<%=albumSlide.getSlideLargeImg()%>" width="100%">

								<div class="single-navigation navigation clearfix">
									<%if(slideIndex>0){%>
									<a href="<%=contextPath%>/album/<%=album.getId() %>/<%=slideList.get(slideIndex-1).getId()%>" class="nav-left"><span></span>上一张</a>
									<%}
									if(slideIndex<slideList.size()-1){
									%><a href="<%=contextPath%>/album/<%=album.getId() %>/<%=slideList.get(slideIndex+1).getId()%>" class="nav-right">下一张<span></span></a>
									<%}%>
								</div>
								<%}%>
								
								<blockquote class="normal blockquote-left blockquote-bg">
                                    <p>
                                    <span class="blockquote-author"><a href='javascript:void(0)'>作品描述&nbsp;</a>:&nbsp;&nbsp;<%=album.getRemark()%></span>
                                    <span class="blockquote-author"><a href='javascript:void(0)'>价格&nbsp;</a>:&nbsp;&nbsp;<%=album.getPrice()%>&nbsp;元&nbsp;&nbsp;
                                    <%if(album.getLink()!=null){%><a href="<%=album.getLink()%>" class="button button-small button-blue" target="_blank">查看淘宝链接</a><%}%></span>
                                    </p>
                                </blockquote> 
								<div class="row-fluid clearfix">

									<div class="meta-container-single clearfix">
										<div class="meta-tags">
											<ul>
												<li>
												<%
												List<String> tagNameList = album.getTagList();
												if(tagNameList!=null&&tagNameList.size()>0){
													int tagIndex = 0;
													for(String tagName: tagNameList){
														tagIndex++;
												%>
													<a href="<%=contextPath%>/tag/<%=tagName%>"><%=tagName%></a>
													<%=tagIndex<tagNameList.size()?",&nbsp;":""%>
												<%}
												}%>
												</li>
											</ul>
										</div>
										
										<div class="meta-categories">
											<!-- <ul>
												<li><a href="javascript:void(0)">举报</a></li>
											</ul> -->
											<ul>
												<li><a href="javascript:void(0)" id="browseLink">浏览(<span id="album-browse-counter"><%=album.getBrowseCount()%></span>)</a></li>
											</ul>
											<ul>
												<li id="unlikeId"><a href="javascript:void(0)" id="unlikeLink">已赞(<span class="album-like-counter"><%=album.getLikeCount()%></span>)</a></li>
												<li id="likeId"><a href="javascript:void(0)" id="likeLink">赞(<span><%=album.getLikeCount()%></span>)</a></li>
											</ul>
											<ul>
												<li id="unfavoriteId"><a href="javascript:void(0)" id="unfavoriteLink">已收藏(<span class="album-favorite-counter"><%=album.getFavoriteCount()%></span>)</a></li>
												<li id="favoriteId"><a href="javascript:void(0)" id="favoriteLink">收藏(<span><%=album.getFavoriteCount()%></span>)</a></li>
											</ul>
											<ul>
												<li><a href="javascript:void(0)" id="commentLink">评论(<span id="album-comment-counter"><%=album.getCommentCount()%></span>)</a>
												</li>
											</ul>
										</div>
									</div> 
									
									<!-- 百度分享控件 -->
									<div class="share-out clearfix">
										<div class="bdsharebuttonbox">
											<a href="#" class="bds_more" data-cmd="more"></a>
											<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
											<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
											<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
											<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
										</div>
										<script>
											window._bd_share_config = {
												"common" : {
													"bdSnsKey" : {"tsina":"753177599"},
													"bdText" : "",
													"bdDesc" : "",
													"bdUrl" : "", 	
													"bdPic" : "<%=albumSlide.getSlideLargeImg()%>",
													"bdMini" : "2",
													"bdMiniList" : false,
													"bdStyle" : "1",
													"bdSize" : "32"
												},
												"share" : {},
												"image" : {
													"viewList" : [ "weixin", "tsina",
															"qzone", "tqq" ],
													"viewText" : "分享到：",
													"viewSize" : "24"
												},
											};
											
											with (document)
												0[(getElementsByTagName('head')[0] || body)
														.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='
														+ ~(-new Date() / 36e5)];
										</script>
									</div>
									
									<input type="hidden" id="commentsTailId" name="commentsTailId" value="0">
									<input type="hidden" id="albumId" name="albumId" value="<%=album.getId()%>" />
									<input type="hidden" id="toId" name="toId" value="<%=album.getUserId()%>" />
									<input type="hidden" id="designerId" name="designerId" value="<%=album.getUserId()%>" />
									
									<div id="comments">
										<!-- <div class="infobox info-succes info-succes-alt clearfix">
			                                <span></span>
			                                <div class="infobox-wrap">
			                                    <h4>操作成功</h4>
			                                    <p>操作已经成功!</p>
			                                </div>
			                                <a href="infoboxes.html#" class="info-hide"></a>
			                            </div> -->
										<ol id="commentListContainer" class="commentlist">
										</ol>
										
										<ol class="commentlist">
											<li class="comment depth-1" id="li-comment-1">
												<div class="comment-container-wrapper" id="comment-1">
													<div class="comment-body">
														<div class="comment-operation-container">
															<a href="javascript:void(0)" id="moreCommentsBtn">加载更多评论...</a>
														</div>
														<div class="comment-operation-container" style="display:none">
															加载中...
														</div>
														<div class="comment-empty-container" style="display:none">
															无更多评论...
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
											<form action="<%=contextPath%>/loginBack" method="post"
												id="loginBackForm" class="form">
												<div class="span12">
													发表评论需要您先登录！ 
													<!-- <input
														class="button button-small button-orange" type="button"
														name="loginBtn" id="loginBtn" tabindex="5" value="登 录" />
													<input class="button button-small button-green"
														type="button" name="regBtn" id="regBtn" tabindex="5"
														value="注册新用户" /> -->
													
													<input class="wb-login common-submit button" id="weiboLoginBtn" type="button" onclick="location.href='<%=contextPath%>/connectWeibo?<%=ConstFront.REDIRECT_URL%>=<%=contextPath%>/album/<%=album.getId()%>'"/>
													<!-- 
													<input class="qq-login common-submit button" id="tencentLoginBtn" type="button" onclick="location.href='<%=contextPath%>/connectTencent'"/>
												 	-->
												</div>
											</form>
											<%}else{%>
											<form action="#" method="post" id="commentform" class="form">
												<div class="span9">
													<p>
														<textarea class="comment_textarea" name="comment"
															id="comment" cols="50" rows="2" tabindex="4"></textarea>
													</p>
												</div>
												<div class="span3">
													<input class="button button-small button-orange"
														type="button" name="commentBtn" id="commentBtn"
														tabindex="5" value="发表评论" />
												</div>
												
											</form>
											<%}%>
										</div>
									</div>
									<!-- Close Comments -->
								</div>
							</article>
							<%}%>
						</section>

						<!-- right slidebar -->
						<aside class="sidebar widgets-light span3">
                       		<jsp:include page="../inc/right/sidebar.jsp"></jsp:include> 
                    	</aside>

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
	<script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
	<script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
	<script src="<%=contextPath%>/js/superfish.js"></script>
	<!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
	<!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
	<script src="<%=contextPath%>/js/jquery.flexslider.js"></script>
	<!--<script src="<%=contextPath%>/js/retina.js"></script>-->

	<script src="<%=contextPath%>/js/custom.js"></script>

	<script>
		<%if(album.isLike()){%>
			$('#likeId').hide();
		<%}else{%>
			$('#unlikeId').hide();
		<%}%>
		<%if(album.isFavorite()){%>
			$('#favoriteId').hide();
		<%}else{%>
			$('#unfavoriteId').hide();
		<%}%>
	
		var emptyCommentVal = "我要评论...";
		
		$(document).ready(function(){
			$('#comment').val(emptyCommentVal);
			fallLoad();
		});
		
		
		$('#moreCommentsBtn').click(function(){
			fallLoad();
		});
		
		function fallLoad(){
			//置为数据加载状态
			$(".comment-operation-container").toggle();
			
			var jsonData = {'commentsTailId' : $("#commentsTailId").val(), "albumId": <%=album.getId()%> };
			$.post('<%=contextPath%>/moreComments.json', jsonData, function(data) {
				$(".comment-operation-container").toggle();
				$("#commentListContainer").append(data.data.html);
				var nextTailId = data.data.tailId;
				$("#commentsTailId").val(nextTailId);
				if(nextTailId<=0){//无更多数据，则隐藏加载按钮
   					$(".comment-operation-container").hide();
   					$(".comment-empty-container").show();
   				}
			});
		}
		
    	$("#commentLink").click(function(){
    		 $("#comment").focus();
    	});
    	
    	$("#comment").focus(function(){
    		var commentVal = $("#comment").val();
    		if(commentVal==emptyCommentVal){
	   			$("#comment").val("");
    		}
   		});

    	$("#comment").blur(function(){
    		var commentVal = $("#comment").val();
    		if(commentVal==""){
   				$("#comment").val(emptyCommentVal);
    		}
   		});
    	
    	$("#likeLink").click(function(){
    		var likeJsonData = {'albumId': $("#albumId").val()};
	    	$.post("<%=contextPath%>/like.json", likeJsonData, function(responseData) {
	    		var result = responseData.result;
   				if(result==1){
   					$("#likeId").hide();
   					$("#unlikeId").show();
   					$('.album-like-counter').text(parseInt($('.album-like-counter').text())+1);
   				}else{
   					alert(responseData.message);
   				}
	  		 }, "json");
	   	});
    	
    	$("#favoriteLink").click(function(){
    		var favoriteJsonData = {'albumId': $("#albumId").val()};
	    	$.post("<%=contextPath%>/favorite.json", favoriteJsonData, function(responseData) {
	    		var result = responseData.result;
   				if(result==1){
   					$("#favoriteId").hide();
   					$("#unfavoriteId").show();
   					$('.album-favorite-counter').text(parseInt($('.album-favorite-counter').text())+1);
   				}else{
   					alert(responseData.message);
   				}
	  		 }, "json");
	   	});
    	
    	$("#commentBtn").click(function(){
    		//disable submitBtn
    		$("#commentBtn").attr("disabled", "disabled");
    		var commentJsonData = {"comment": $("#comment").val(),'albumId': $("#albumId").val(), 'toId':$("#toId").val(), 'designerId':$("#designerId").val()};
    		$.post("<%=contextPath%>/comment.json", commentJsonData, function(data) {
    			var result = data.result;
   				if(result==1){
   					$("#comment").val("");
	    			$("#commentBtn").removeAttr("disabled");
	    			$("#commentListContainer").prepend(data.data);
	    			$('#album-comment-counter').text(parseInt($('#album-comment-counter').text())+1);
   				}
    			//enable submitBtn
    		 }, "json"); 
    	});
    	
    	$("#loginBtn").click(function(){
			$("#loginBackForm").submit();
    	});
    	$("#regBtn").click(function(){
			location.href="<%=contextPath%>/register";
    	});
    	
    	function reply(fromId, fromName){
    		$("#toId").val(fromId);
    		$("#comment").focus();
    		$("#comment").val("回复"+fromName+": ");
    	}
    	
    </script>



<script>
var imgUrl = "<%=albumSlide.getWxShareIconUrl()%>";
var lineLink = "http://www.jinwanr.com/album/<%=album.getId()%>";
var shareTitle = '[<%=queryUser.getNickname()%>作品] - <%=album.getWxShareTitle()%> - 【金玩儿网】';
var shareDesc = '[<%=queryUser.getNickname()%>作品] - <%=album.getWxShareContent()%> - 【金玩儿网】';

function shareFriend() {
    WeixinJSBridge.invoke('sendAppMessage',{
        "img_url": imgUrl,
        "link": lineLink,
        "desc": shareDesc,
        "title": shareTitle
    }, function(res) {
        //_report('send_msg', res.err_msg);
    })
}
function shareTimeline() {
    WeixinJSBridge.invoke('shareTimeline',{
    	"img_url": imgUrl,
        "link": lineLink,
        "desc": shareDesc,
        "title": shareTitle
    }, function(res) {
           //_report('timeline', res.err_msg);
    });
}
// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    // 发送给好友
    WeixinJSBridge.on('menu:share:appmessage', function(argv){
        shareFriend();
    });
    // 分享到朋友圈
    WeixinJSBridge.on('menu:share:timeline', function(argv){
        shareTimeline();
    });
}, false);
</script>

</body>
</html>
