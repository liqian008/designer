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

        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/font-awesome.css">
        <link rel="stylesheet" href="./css/animate.css">
        <link rel="stylesheet" href="./css/flexslider.css">
        <link rel="stylesheet" href="./css/style.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="./css/ie8.css" />    
        <![endif]-->
                

        <script src="./js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="./js/vendor/jquery-1.8.3.min.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(./img/backgrounds/bg3.jpg); ">

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
                                        <img src="./img/verendus-logo.png" alt="Verendus Logo" title="Verendus Logo" />
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
                                <img src="./img/demo/portraits/portrait-21.png" alt="Page Title" width="80" height="80"/>
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
                        
                        <section class="content span6 offset3">
							<div class="shortcode-tabs">
							    <ul class="tabs-nav tabs clearfix">
							        <li class="active"><a class="button button-white" href="#tab1" data-toggle="tab">注册新用户</a></li>
							        <li><a class="button button-white" href="#tab2" data-toggle="tab">绑定用户</a></li>
							    </ul>
							     
							    <div class="tab-content">
							        <div class="tab-pane active" id="tab1">
							        	<h2>完善帐号信息</h2>
							           <p>
							                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
							                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
							                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
							                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
							                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
							                proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
							            </p> 
							        </div>
							        <div class="tab-pane" id="tab2">
							            <h2>绑定已有帐号</h2>
							           <p>
							                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
							                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
							                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
							                consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
							                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
							                proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
							            </p> 
							        </div>
							    </div>
							</div>
                        </section> 
                        <!-- End Content -->
                       
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="./inc/footer.jsp"></jsp:include>
           
        </div> <!-- Close Page -->
   </div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="./js/vendor/bootstrap.min.js"></script>
    <script src="./js/jquery.hoverdir.js"></script>
    <script src="./js/superfish.js"></script>
    <!-- <script src="./js/supersubs.js"></script> -->
   <!--  <script src="./js/jquery.tweet.js"></script>  -->
    <script src="./js/jquery.flexslider.js"></script> 
    <script src="./js/retina.js"></script>

    <script src="./js/custom.js"></script>

    </body>
</html>
