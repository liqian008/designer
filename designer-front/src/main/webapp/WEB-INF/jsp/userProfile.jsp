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
                            <!-- <div class="page-title-avatar">
                                <img src="../img/demo/portraits/portrait-21.png" alt="Page Title" width="80" height="80"/>
                            </div> -->
                            <div class="page-title-content">
                                <h1>Gallery Post Format</h1>
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
                                                <h2>Full Size Picture Blog Post</h2>
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
                                            <li class="meta-date">October 19, 2012</li>
                                            <li class="meta-author">Written by <a href="post-left.html#">John Doe</a></li>
                                            
                                            <li class="meta-categories">
                                                <ul>
                                                    <li><a href="post-left.html#">Category</a>,</li>
                                                    <li><a href="post-left.html#">Web-development</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                        <ul class="span6">
                                            <li class="meta-comments"><a href="post-left.html#">10 Comments</a></li>
                                            <li class="read-more">
                                                <a href="post-left.html#">Read this post</a>
                                            </li>
                                            <li class="meta-tags">
                                                <ul>
                                                    <li><a href="post-left.html#">Photoshop</a>,</li>
                                                    <li><a href="post-left.html#">Webdesign</a>,</li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </article>
                            <%}
                            }%>
                        </section>
                        <aside class="sidebar widgets-light span3">

							<!-- Blogpost Widget -->
                            <div class="widget-box widget-blogposts">
                                <div class="content-title">
                                    <h4 class="widget-title">Latest Blogposts / Portfolio</h4>
                                </div>
                                <ul>
                                    <li class="clearfix">
                                        <div class="widget-blogpost-avatar">
                                            <a href="post-gallery.html#">
                                                <img src="../img/demo/ecopower-70x70.jpg" alt="Blogpost-1">
                                                <span class="widget-blogpost-overlay"></span>
                                            </a>
                                        </div>
                                        <div class="widget-blogpost-content">
                                            <div class="widget-blogpost-title ">
                                                <h5>Just a simple blogtitle with two lines of text.</h5>
                                            </div>
                                            <div class="widget-blogpost-date">
                                                <p><a href="post-gallery.html#">December 8, 2012</a></p>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="clearfix">
                                        <div class="widget-blogpost-avatar">
                                            <a href="post-gallery.html#">
                                                <img src="../img/demo/architecture-70x70.jpg" alt="Blogpost-1">
                                                <span class="widget-blogpost-overlay"></span>
                                            </a>
                                        </div>
                                        <div class="widget-blogpost-content">
                                            <div class="widget-blogpost-title ">
                                                <h5>Just a simple blogtitle with two lines of text.</h5>
                                            </div>
                                            <div class="widget-blogpost-date">
                                                <p><a href="post-gallery.html#">December 8, 2012</a></p>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="clearfix">
                                        <div class="widget-blogpost-avatar">
                                            <a href="post-gallery.html#">
                                                <img src="../img/demo/ecohands-70x70.jpg" alt="Blogpost-1">
                                                <span class="widget-blogpost-overlay"></span>
                                            </a>
                                        </div>
                                        <div class="widget-blogpost-content">
                                            <div class="widget-blogpost-title ">
                                                <h5>Just a simple blogtitle with two lines of text.</h5>
                                            </div>
                                            <div class="widget-blogpost-date">
                                                <p><a href="post-gallery.html#">December 8, 2012</a></p>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
							<!-- Flickr Widget -->
                            <div class="widget-box widget-flickr">
                                <div class="content-title">
                                    <h4 class="widget-title">My Flickr Stream</h4>
                                </div>
                                <div class="flickr-wrap clearfix">
                                    <script type="text/javascript" src="http://www.flickr.com/badge_code_v2.gne?count=9&amp;display=random&amp;size=s&amp;layout=x&amp;source=user&amp;user=52617155@N08">
                                    </script>
                                </div>
                            </div>
                        </aside>
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
