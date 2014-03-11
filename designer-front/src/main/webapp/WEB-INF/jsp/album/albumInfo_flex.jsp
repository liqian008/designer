<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
String contextPath = ConstFront.CONTEXT_PATH;
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <title>金玩儿网</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

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

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(../img/backgrounds/bg.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        
        <jsp:include page="../inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                
                <jsp:include page="../inc/headerBanner.jsp"></jsp:include>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="../inc/headerNav.jsp"></jsp:include>

                    <div class="page-title">
                        <div class="container">
                            <!-- <div class="page-title-avatar">
                                <img src="./img/demo/portraits/portrait-21.png" alt="Page Title" width="80" height="80"/>
                            </div> -->
                            <div class="page-title-content">
                                <h2>此处放置广告作品</h2>
                                <p class="page-description">
                                    With this gallery you can create a blogpost with multiple images. With the FlexSlider or Twitter Bootstrap Carousel you can rotate between these images.
                                </p>
                            </div>
                        </div>
                    </div>
                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="post-gallery.html#">Home</a>/</li>
                        <li><a href="post-gallery.html#">Blog</a>/</li>
                        <li><a href="post-gallery.html#">Gallery Post Format</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">            
                <div class="container">
                    <div class="row-fluid">
                    	
                    
                        <section class="content span9">
                            <%
							Album album = (Album)request.getAttribute("albumInfo");
							if(album!=null){%>
                            <article class="post format-blogpost clearfix">
                                <div class="post-thumb">
                                	<div class="divider-text divider-mid">
                                        <span><%=album.getTitle()%>【<%=album.getSlideList().size()%>张】</span>
                                    </div>
                                    
                                    <div class="single-navigation navigation clearfix">
                                       <a href="single.html#" class="nav-left"><span></span>上一张</a>
                                       <a href="single.html#" class="nav-right">下一张<span></span></a>
                                    </div> 
                                    
                                    <div class="flexslider">
                                        <%
		                                List<AlbumSlide> slideList = album.getSlideList();
		                                if(slideList!=null&&slideList.size()>0){
		                                %>
                                        <ul class="slides">
                                        	<%
											boolean isFirst = true;
											for(AlbumSlide slide: slideList){
											%>
                                            <li>
                                                <img src="<%=slide.getSlideLargeImg()%>" alt="Carousel Item 3" />
                                                    <div class="flex-caption">
                                                    <h3><%=slide.getTitle()%></h3>
                                                    <p>
                                                    	<%=slide.getRemark()%>
                                                    </p> 
                                                </div>
                                            </li>
                                            <%}%>
                                        </ul>
                                        <%}%>
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
                                               <li><a href="single.html#">举报</a> </li>
                                           </ul>
                                           <ul>
                                               <li><a href="single.html#">喜欢(1220)</a> </li>
                                           </ul>
                                            <ul>
                                               <li><a href="single.html#">收藏(120)</a> </li>
                                           </ul>
                                           
                                           <ul>
                                               <li><a href="javascript:void(0)" id="commentLink">评论(10)</a> </li>
                                           </ul>
                                       </div>
                                    </div>
                                    
									
									
                                    <div id="comments">
                                        <ol id="commentListId" class="commentlist">
                                        	<%
                                        	List<Comment> commentList = album.getCommentList();
                                        	if(commentList!=null){
                                        		for(Comment comment: commentList){
                                        	%>
                                            <li class="comment depth-1" id="li-comment-1">
                                                <div class="comment-container" id="comment-1">
                                                    <div class="comment-avatar">
                                                        <div class="comment-author vcard">
                                                            <img src="./img/demo/portraits/avatar_middle.jpg" alt="Blogpost Comment" />                 
                                                        </div>
                                                    </div>                          
                                                    <div class="comment-body">
                                                        <div class="comment-meta commentmetadata">
                                                            <h6 class="comment-author">
                                                                <a href='#' rel='external nofollow' class='url'><%=comment.getNickname()%></a> 发表于 <%=comment.getCreateTime()%>
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
                                        </ol>

                                         <div id="respond">
                                            <div class="content-title">
                                                <h4>发表新评论</h4>
                                            </div>
                                            <%if(currentUser==null){%>
                                            <form action="./loginBack" method="post" id="loginBackForm" class="form">
                                                <div class="span9">
                                                	发表评论需要您先登录！
	                                                <input class="button button-small button-orange" type="button" name="loginBtn" id="loginBtn" tabindex="5" value="登 录" />
													<input class="button button-small button-blue" type="button" name="regBtn" id="regBtn" tabindex="5" value="注册新用户" />
												</div>
											</form>
                                            <%}else{%>
                                            <form action="#" method="post" id="commentform" class="form">
                                                <div class="span9">
                                                    <p>
                                                        <textarea class="comment_textarea" name="comment" id="comment" cols="50" rows="2" tabindex="4">在此发表评论</textarea>
                                                    </p>
                                                </div>
                                                <div class="span3">
                                                <input class="button button-small button-orange" type="button" name="publishBtn" id="publishBtn" tabindex="5" value="发表评论" />
												</div>
												<input type="hidden" name="albumId" value="<%=album.getId()%>"/>
                                                <input type="hidden" name="albumSlideId" value=""/>
                                                <input type="hidden" name="toId" value="<%=album.getUserId()%>"/>
                                                <input type="hidden" name="designerId" value="<%=album.getUserId()%>"/>
                                            </form>
                                            <%}%>
                                        </div>
                                    </div><!-- Close Comments -->
                                </div>
                                 </div>
                            </article>
                            <%}%>
                        </section>

                       <jsp:include page="../inc/designerBox.jsp"></jsp:include>
                    	
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="../inc/footer.jsp"></jsp:include>
           
        </div> <!-- Close Page -->
   </div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
    <script src="<%=contextPath%>/js/superfish.js"></script>
    <!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
   <!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
    <script src="<%=contextPath%>/js/jquery.flexslider.js"></script> 
    <script src="<%=contextPath%>/js/retina.js"></script>

    <script src="<%=contextPath%>/js/custom.js"></script>
    
    <script>
    	 $("#commentLink").click(function(){
    		 $("#comment").focus();
    		 $("#comment").val("");
    	});
    	$("#publishBtn").click(function(){
    		 $.post("<%=contextPath%>/comment.json", {"comment":"hello","albumId":"<%=album.getId()%>","albumSlideId":"<%=album.getId()%>", "toId":"<%=album.getUserId()%>", "designerId":"<%=album.getUserId()%>"}, function(data) {
    			  alert("result: " + data.result);
    			  alert("message: " + data.data);
    			  $("#commentListId").append(data.data);
    		 }, "json"); 
    	});
    	
    	$("#loginBtn").click(function(){
			$("#loginBackForm").submit();
    	});
    	$("#regBtn").click(function(){
			location.href="./register";
    	});
    </script>

    </body>
</html>
