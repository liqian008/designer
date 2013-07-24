<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
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
                <div id="header-container" class="clearfix">  <!-- Header Container, contains logo and contact button -->
                    <header class="clearfix">
                        <div class="container">
                            <div class="row-fluid">
                                <div class="span3 logo">
                                    <a href="post-gallery.html#">
                                        <img src="../img/verendus-logo.png" alt="Verendus Logo" title="Verendus Logo" />
                                    </a>
                                </div>

                                <div class="header-contact button">
                                    <a href="post-gallery.html#">
                                        <ul class="clearfix">
                                            <li class="phone-number">
                                                <i class="icon-phone"></i>0(123) - 45678910
                                            </li>
                                            <li class="mailto-email">
                                                <i class="icon-envelope"></i>info@somnia-themes.com
                                            </li>
                                        </ul>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </header>       
                </div>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="./inc/headerNav.jsp"></jsp:include>

                    <div class="page-title">
                        <div class="container">
                            <div class="page-title-avatar">
                                <img src="../img/demo/portraits/avatar_middle.jpg" alt="Page Title" width="80" height="80"/>
                            </div>
                            <div class="page-title-content">
                                <h2>大树珠宝</h2>
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
							List<Album> albumList = (List<Album>)request.getAttribute("albumList");
							if(albumList!=null){
		                        for(Album album: albumList){
		                    %>
                            <article class="post format-blog-left clearfix">
                                <div class="post-thumb-wrap span5">                            
                                    <div class="post-thumb hoverdir preload ">
                                        <span class="preload-done"><img src="<%=album.getCoverImg()%>" alt="Blogpost Preview Image" style="display: block; visibility: visible; opacity: 1; "></span>
                                        <div class="post-thumb-overlay" style="left: 0px; top: 100%; display: block; -webkit-transition: all 300ms ease; ">
                                            <section class="post-thumb-overlay-inner">
                                            <hgroup>
                                                <h2><%=album.getTitle()%></h2>
                                            </hgroup>
                                            <span class="overlay-meta-container">
                                                <span class="meta-comments"><a href="#">10</a></span>
                                                <span class="meta-link"><a href="#"></a></span>
                                            </span>
                                        </section>
                                        </div>
                                    </div>
                                </div>
                                    
                                <div class="post-content span7">
                                    <h2><%=album.getTitle()%></h2>
                                     <p>
                                        <%=album.getRemark()%>.
                                    </p>
                                    <div class="meta-container clearfix">
                                        <ul class="span6">
                                            <li class="meta-comments"><a href="post-left.html#">浏览数: 100次 </a></li>
                                            <li class="meta-comments"><a href="post-left.html#">评论数: 10次 </a></li>
                                            <li class="meta-tags">
                                                <ul>
                                                    <li><a href="post-left.html#">翡翠</a>,</li>
                                                    <li><a href="post-left.html#">玉石</a>,</li>
                                                </ul>
                                            </li>
                                        </ul>
                                        <ul class="span6">
                                            <li class="meta-comments"><a href="post-left.html#">喜欢数: 10次 </a></li>
                                            <li class="meta-comments"><a href="post-left.html#">收藏数: 8次 </a></li>
											<li class="meta-date"><%=album.getCreateTime()%></li>
                                        
                                        </ul>
                                    </div>
                                </div>
                            </article>
                            <%}
                            }%>
                        </section>
                       	
                       	<jsp:include page="./inc/rightSidebar.jsp"></jsp:include>
                    	
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
