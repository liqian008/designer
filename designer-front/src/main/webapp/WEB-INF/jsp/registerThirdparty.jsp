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
                <jsp:include page="./inc/headerBanner.jsp"></jsp:include>

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
							        <li class="active"><a class="button button-white" href="#tab1" data-toggle="tab">完善账户信息</a></li>
							        <li><a class="button button-white" href="#tab2" data-toggle="tab">绑定已有账户&分享</a></li>
							    </ul>
							     
							    <div class="tab-content">
							        <div class="tab-pane active widgets-light" id="tab1">
							        	<div class="widget-box widget-contact-form">
											<div class="content-title">
												<h4>完善帐号信息可体验本站更多功能</h4>
											</div>
											<%
											AccessTokenInfo tempToken = (AccessTokenInfo)session.getAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN);
											%>
											
											<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/oauthRegister.art">
												<div class="input-container">
													<input type="text" class="contact-form-name" name="username"
														value="<%=tempToken.getThirdpartyUname()%>"
														onfocus="if(this.value=='用户名')this.value='';"
														onblur="if(this.value=='')this.value='用户名';" /> <i
														class="icon-user"></i>
												</div>
												<div class="input-container">
													<input type="text" class="contact-form-name" name="nickname"
														value="<%=tempToken.getThirdpartyUname()%>"
														onfocus="if(this.value=='昵 称')this.value='';"
														onblur="if(this.value=='')this.value='昵 称';" /> <i
														class="icon-user"></i>
												</div>
												<div class="input-container">
													<input type="password" class="contact-form-email" name="password"
														value=""/> 
														<i class="icon-envelope-alt"></i>
												</div>
												<div class="input-container">
													<input type="password" class="contact-form-email" name="password"
														value=""/> 
														<i class="icon-envelope-alt"></i>
												</div>
												<input class="contact-submit button" type="submit" value="完 成">
												<input class="contact-submit button" type="button" value="取 消">
											</form>
										</div>
							        </div>
							        <div class="tab-pane widgets-light" id="tab2">
							        	<div class="widget-box widget-contact-form">
								            <div class="content-title">
												<h4>设计师绑定已有账户可分享作品至多个平台</h4>
											</div>
											<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/oauthBind.art">
												<div class="input-container">
													<input type="text" class="contact-form-name" name="username"
														value="用户名"
														onfocus="if(this.value=='用户名')this.value='';"
														onblur="if(this.value=='')this.value='用户名';" /> <i
														class="icon-user"></i>
												</div>
												<div class="input-container">
													<input type="password" class="contact-form-email" name="password"
														value=""/> 
														<i class="icon-envelope-alt"></i>
												</div>
												<input class="contact-submit button" type="submit" value="完善个人资料">
											</form>
										</div>
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
