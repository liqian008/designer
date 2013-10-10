<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User requestUser = (User)request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <title>Verendus - Multipurpose Business Template</title>

        <meta name="description" content="Verendus - A HTML5 / CSS3 Multipurpose Business Template">
        <meta name="keywords" content="Bootstrap, Verendus, HTML5, CSS3, Business, Multipurpose, Template">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/font-awesome.css">
        <link rel="stylesheet" href="../css/animate.css">
        <link rel="stylesheet" href="../css/flexslider.css">
        <link rel="stylesheet" href="../css/style.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="../css/ie8.css" />    
        <![endif]-->
                

        <script src="../js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="../js/vendor/jquery-1.8.3.min.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(../img/backgrounds/bg3.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        
        <jsp:include page="./inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                <jsp:include page="./inc/headerBanner.jsp"></jsp:include>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="./inc/headerNav.jsp"></jsp:include>

                    <div class="page-title">
                        <div class="container">
                            <%if(requestUser!=null&&requestUser.getId()!=null){%>
                            <div class="page-title-avatar">
                                <img src="<%=requestUser.getHeadImg()%>" alt="Page Title" width="80" height="80"/>
                            </div>
                            <div class="page-title-content">
                            	<%
                                boolean isDesigner = true;//requestUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_PASSED;
                            	%>
                                <h3><%=requestUser.getNickname()%><%=isDesigner?"【设计师】":""%></h3>
                                <p class="page-description">
                                    With this gallery you can create a blogpost with multiple images.
                                </p>
                               <%
                               if(isDesigner){
									Boolean hasFollowed = (Boolean)request.getAttribute("hasFollowed");
									if(hasFollowed!=null&&hasFollowed){%>
                                <a href="/designer-front/unfollow.art?uid=<%=requestUser.getId()%>" class="button button-small button-white">取消关注</a>
                                	<%}else{%>
                                <a href="/designer-front/follow.art?uid=<%=requestUser.getId()%>" class="button button-small button-green">关注</a>
                                	<%}%>
                                <a href="/designer-front/3/home.art" class="button button-small button-white">作品辑</a>
                                <%}%>
                                <a href="javascript:void(0)" class="button button-small">个人资料</a>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="post-gallery.html#">首页</a>/</li>
                        <li><a href="post-gallery.html#">Ta的主页</a>/</li>
                        <li><a href="post-gallery.html#">个人资料</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">            
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
	
                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                	<li><a class="button button-white" href="javascript:void(0)">Ta的资料</a></li>
                                	<li class="active"><a class="button button-white" href="javascript:void(0)">Ta的关注</a></li>
                                	<li><a class="button button-white" href="javascript:void(0)">Ta的粉丝</a></li>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane active clearfix">
                                    	 <div class="content-title">
											<h4>关注的设计师</h4> 
										</div>
                                    	<div class="shortcode-blogpost-thumb shortcode-blogpost-medium span12">
		                                <ul>
		                                    <%
		                                	List<UserFollow> followList = (List<UserFollow>)request.getAttribute("followList");
		                                	if(followList!=null&&followList.size()>0){
		                                	for(UserFollow follow: followList){
		                                	%>
		                                    <li class="clearfix">
		                                        <div class="blogpost-avatar">
		                                            <a href="#">
		                                                <img src="../img/demo/happy-caller-80x80.jpg" alt="Blogpost-1">
		                                            </a>
		                                        </div>
		                                        <div class="blogpost-content">
		                                            <div class="blogpost-title ">
		                                                <a href="#"><h5><%=follow.getFollowId()%></h5></a>
		                                            </div>
		                                            <div class="blogpost-date">
		                                                <p><a href="#"><%=follow%></a></p>
		                                            </div>
		                                        </div>
		                                    </li>
		                                    <%}
		                                	} %>
		                                    
		                                </ul>
		                            </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                       	
                       	<jsp:include page="./inc/designerBox.jsp"></jsp:include>
                    	
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="./inc/footer.jsp"></jsp:include>
           
        </div> <!-- Close Page -->
   </div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="../js/vendor/bootstrap.min.js"></script>
    <script src="../js/jquery.hoverdir.js"></script>
    <script src="../js/superfish.js"></script>
    <!-- <script src="../js/supersubs.js"></script> -->
   <!--  <script src="../js/jquery.tweet.js"></script>  -->
    <script src="../js/jquery.flexslider.js"></script> 
    <script src="../js/retina.js"></script>

    <script src="../js/custom.js"></script>

    </body>
</html>
