<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>PRIDE - Responsive HTML Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        
        <!-- Le styles -->
        <!-- <link href="assets/css/bootstrap.css" rel="stylesheet"> -->
        <link href="http://www.see-source.com/bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="assets/css/docs.css" rel="stylesheet">
        <link href="assets/js/google-code-prettify/prettify.css" rel="stylesheet">
    	<link href="assets/nivo/nivo-slider.css" type="text/css" media="screen" rel="stylesheet">
        <link href="assets/css/prettyPhoto.css" type="text/css" media="screen" rel="stylesheet">
        
        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!--[if lte IE 8]>
    	<link rel="stylesheet" type="text/css" href="assets/css/ie.css" />
		<![endif]-->
        
        <!-- Le fav and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
        
<!--         <link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:400,200,300,700&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
 -->        
 
 		<style>
          .post-date {
              background-color: #F96E5B;
              position: absolute;
              text-align: center;
              padding: 6px 0px;
              overflow: hidden;
              width: 56px;
          }
          .span8 div.post-date{
              color: white;
              text-align: center;
              text-transform: uppercase;
              font-weight: bold;
          }
          
          
          .span8 div.post-date span.day {
              color:white;
              font-size: 27px;
              letter-spacing: 0px;
              margin-top: 2px;
              line-height: 33px;
          }
          .span8 div.post-date span.month {
              font-size: 13px;
              line-height: 11px;
          }
       </style> 
    </head>

	<body>
	
    <jsp:include page="./inc/header.jsp"></jsp:include>
	
    <!--WELCOME AREA-->
    <div class="container">
        <div class="row">
            <div class="span12">
            	<div class="welcome">
                    <h6><span class="colored"><i class="icon-bullhorn icon-pride"></i> Our Blog:</span> page tagline goes here</h6>
                </div>
            </div>
        </div>
    </div>
    <!--/WELCOME AREA-->
    
    <!--PAGE CONTENT-->
    <!--PAGE CONTENT-->
    <div class="container" style="margin-bottom:50px;">
        <div class="row">
            <div class="span9">
            	<h3 class="sep_bg colored">文章列表</h3>
                <div class="row">
                    
                    <%
					TbAlbum album = (TbAlbum)request.getAttribute("albumInfo");
					if(album!=null){%>
                    <div class="span9 blog_post">
                        <div class="row">
                            <div class="span1 block" style="position:relative">
                                <a href="./<%=album.getUserId()%>/profile">
                                <img src="assets/img/avatar.jpg" alt="" class="avatar"  style="float:left" width="50px"/>
                                </a>
                                <div class="clearfix"></div>
                            </div>
                            <div class="span8 block" style="position:relative">
                                <%
                                List<TbAlbumSlide> slideList = album.getSlideList();
                                if(slideList!=null&&slideList.size()>0){
                                %>
                                
                                <div id="myCarousel" class="carousel slide">
									<ol class="carousel-indicators">
										<%
										boolean isFirst = true;
										for(TbAlbumSlide slide: slideList){
											
										%>
										<li data-target="#myCarousel" data-slide-to="<%=slide.getId()%>" <%=isFirst?"class='active'":""%>></li>
										<%
										isFirst = false;
										}%>
									</ol>
									<div class="carousel-inner">
										<%
										boolean isFirst2 = true;
										for(TbAlbumSlide slide: slideList){%>
										<div class="item <%=isFirst2?" active":""%>">
											<img
												src="<%=slide.getSlideImg()%>"
												alt="">
											<div class="carousel-caption">
												<h4>标题: <%=slide.getTitle()%></h4>
												<p>表达主题：<%=slide.getRemark()%></p>
											</div>
										</div>
										<%
										isFirst2 = false;
										}%>
										
									</div>
									<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
									<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
								</div>
                                <%}%>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    	<!-- relativeBar  -->
                        <div class="relativeBar">
                            <div class="row">
                                <div class="span8 offset1">
                                    <div class="pull-right">
                                        <h6><a href="#">举 报</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">喜 欢 (23)</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">收 藏 (2)</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">评 论 (3)</a></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- relativeBar end  -->
                    	
                    	
                    	<div class="comment">
                    		<div class="row">
                    			<div class="span8 offset1">
                    				<hr class="sep" style=" margin-bottom:0px !important;">
                    			</div>
                    		</div>
                    	</div>
                    	<!-- comment  -->
                    	<div class="comment">
                            <div class="row">
                            	<%
		                    	List<TbComment> commentList = album.getCommentList();
		                    	if(commentList!=null&&commentList.size()>0){
                            	for(TbComment comment: commentList){
                            	%>
                                <div class="span8 offset1">
                                    <a href="./<%=comment.getUserId()%>/profile">
                                    	<img src="/designer-front/assets/img/avatar2.jpg" alt="" class="avatar"  style="float:left" width="40px"/>
                                    </a>
                                    <div>
                                        <h6><a href="#"><%=comment.getNickname()%></a> 评论于 <%=ymdSdf.format(comment.getCreateTime())%></h6> 
                                        <p><em><%=comment.getComment()%></em></p>
                                    </div>
                                </div>
                                <%}
                                }%>
                                
                                <%
                                TbUser currentUser = (TbUser)session.getAttribute(ConstFront.CURRENT_USER);
                           		%>
                                <div class="span8 offset1">
                                 <form class="commentForm" action="./postComment" method="post">
                                 	<input type="hidden" name="albumId" value="<%=album.getId()%>"/>
                                    <input type="hidden" name="albumPostId" value="<%=album.getId()%>"/>
                                    <%if(currentUser==null) {%>
                                    <input type="text" class="span3" name="username" value="" placeholder="用户名" style="margin-right:25px;"/>
                                    <input type="password" class="span3" name="password" value="" placeholder="密码"/>
                                    <%}%>
                                    <textarea type="text" name="comment" placeholder="点击输入评论" rows="2" class="span8"></textarea>
                                    <button type="submit"  class="btn btn-small btn-info">发表新评论</button>
                                </form>
                                </div>
                            </div>
                        </div>
                        <!-- comment end  -->
                       
                        
                    </div>
                    <div class="span9"><hr class="sep" style=" margin-bottom:20px !important;"></div>
                    <%}%>
                    
                </div>
            </div>
            
            <div class="span3 sidebar hidden-phone">
            	<jsp:include page="./inc/userbox.jsp"></jsp:include>
            	
                <div class="well">
                    <h4 class="sep_bg">分类</h4>
                    <ul class="nav nav-pills nav-stacked">
                        <li class="active"><a href="">Web templates</a></li>
                        <li><a href="">Logo Design</a></li>
                        <li><a href="">Print & Media</a></li>
                        <li><a href="">CSS3 Ttutorials</a></li>
                        <li><a href="">Uncotigorized</a></li>
                    </ul>
                </div>
                
                <div class="well">
                    <h4 class="sep_bg">热门标签</h4>
                    <div class="tags">
                        <p class="big1"><a href="">Templates</a></p>
                        <p class="big2"><a href="">Logo</a></p>
                        <p class="big1"><a href="">Promo</a></p>
                        <p class="big3"><a href="">Print</a></p>
                        <p class="big4"><a href="">HTML 5</a></p>
                        <p class="big5"><a href="">Media</a></p>
                        <p class="big6"><a href="">Tuttorials</a></p>
                        <p class="big7"><a href="">CSS 3</a></p>
                    </div>
                    <div class="clearfix"></div>
                </div>
                
                
            </div>
            
        </div>
    </div>
    <!--PAGE CONTENT-->
    <!--PAGE CONTENT-->
    
    <jsp:include page="./inc/footer.jsp"></jsp:include>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="assets/js/jquery-1.7.1.min.js"></script>
	<!-- <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script> -->
    <script src="assets/js/google-code-prettify/prettify.js"></script>
    <script src="assets/js/bootstrap-transition.js"></script>
    <script src="assets/js/bootstrap-alert.js"></script>
    <script src="assets/js/bootstrap-modal.js"></script>
    <script src="assets/js/bootstrap-scrollspy.js"></script>
    <script src="assets/js/bootstrap-tab.js"></script>
    <script src="assets/js/bootstrap-tooltip.js"></script>
    <script src="assets/js/bootstrap-popover.js"></script>
    <script src="assets/js/bootstrap-button.js"></script>
    <script src="assets/js/bootstrap-collapse.js"></script>
    <script src="assets/js/bootstrap-carousel.js"></script>
    <script src="assets/js/bootstrap-typeahead.js"></script>
    <script src="assets/js/application.js"></script>
    <script src="assets/js/jquery.easing.1.3.js"></script>
    <script src="assets/js/superfish-menu/superfish.js"></script>
    <script src="assets/js/jquery.nivo.slider.js"></script>
    <script src="assets/js/jquery.prettyPhoto.js"></script>
    <script src="assets/js/twitter.js"></script>
    <script src="assets/js/testimonialrotator.js"></script>
    <script src="assets/js/slides.min.jquery.js"></script>
    <script src="assets/js/jquery.preloader.js"></script>
    <script src="assets/js/jquery.waitforimages.js"></script>
    <script src="assets/js/jquery.isotope.min.js"></script>
    
    
    <!-- BLOG SOCIAL -->
	<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4f8811347196f281"></script>
    <!-- /BLOG SOCIAL-->
    
    <script src="assets/js/custom.js"></script>
   
    
    
    <script type="text/javascript">var runFancy = true;</script>
    <!--[if IE]>
    <script type="text/javascript">
        runFancy = false;
    </script>
    <![endif]-->
    <script type="text/javascript">
		if (runFancy) {
			jQuery.noConflict()(function($){
			$(".view").preloader();
			$(".theme-default").preloader();
			});
		};
    </script>

	</body>
</html>

