<%@page import="com.bruce.designer.front.controller.FrontController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
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
							<div class="content-title">
                                <h2>管理中心</h2>
                            </div>

                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                	<jsp:include page="./settingsTabInc.jsp"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="info">
                                        <div class="widget-box widget-edit-form">
											<div class="content-title">
												<h4>个人资料</h4> 
											</div>
											
											<form class="form-horizontal">
											  <div class="control-group">
											    <label class="control-label" for="inputEmail">Email</label>
											    <div class="controls">
											     liqian
											    </div>
											  </div>
											  <div class="control-group">
											    <label class="control-label" for="inputPassword">Password</label>
											    <div class="controls">
											      <input type="password" id="inputPassword" placeholder="Password">
											    </div>
											  </div>
											</form>
											
											<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/settings.art">
												<div class="input-container">
													<input type="text" class="contact-form-name" name="username"
														value="<%=user.getUsername()%>" readonly="readonly"/>
												</div>
												
												<div class="input-container">
													用户名: 
													<input type="text" class="contact-form-name" name="username"
														value="<%=user.getUsername()%>" readonly="readonly"/>
												</div>
												<div class="input-container">
													昵 称: 
													<input type="text" class="contact-form-name" name="nickname"
														value="<%=user.getNickname()%>"  readonly="readonly"/>
												</div>
												<div class="input-container">
													E-Mail: 
													<input type="text" class="contact-form-name" name="email"
														value="<%=user.getEmail()%>"/>
												</div>
												<div class="input-container">
													性 别: 
													<input type="radio" name="gender" value="1"/>男
													<input type="radio" name="gender" value="2"/>女
												</div>
												
												
												<div class="content-title">
													<h4>第三方账户绑定</h4> 
												</div>
												
												<div class="infobox info-info info-info-alt clearfix">
					                                <span>i</span>
					                                <div class="infobox-wrap">
					                                    <h4>小贴士：</h4>
					                                    <p>绑定微博或QQ后，您在本站发布的作品会自动发布到微博或QQ空间上。</p>
					                                </div>
					                                <a href="#" class="info-hide"></a>
					                            </div>
												
												<div class="input-container">
													<%
													AccessTokenInfo wbToken = user.getAccessTokenMap().get(IOAuthService.OAUTH_WEIBO_TYPE);
													boolean wbBound = wbToken!=null;
													%>
													Sina微博: 
													<%=wbBound?"已绑定":"未绑定"%>，<%=wbToken!=null?wbToken.getThirdpartyUname():""%>
													<%if(wbBound){%>
													<a href="/designer-front/unbindOauth.art?thirdpartyType=1" class="button button-small button-white">解绑新浪微博账户</a>
													<%}else{%>
													<a href="/designer-front/connectWeibo.art" class="button button-small button-green">现在就去绑定</a>
													<%}%>
												</div>
												<div class="input-container">
													<%
													AccessTokenInfo tencentToken = user.getAccessTokenMap().get(IOAuthService.OAUTH_TENCENT_TYPE);
													boolean tencentBound = tencentToken!=null;
													%>
													腾讯微博: 
													<%=tencentBound?"已绑定":"未绑定"%>，<%=tencentToken!=null?tencentToken.getThirdpartyUname():""%>
													<%if(tencentBound){%>
													<a href="/designer-front/unbindOauth.art?thirdpartyType=2" class="button button-small button-white">解绑QQ账户</a>
													<%}else{%>
													<a href="/designer-front/connectTencent.art" class="button button-small button-green">现在就去绑定</a>
													<%}%>
												</div>
												<input type="hidden" name="op" value="info" readonly="readonly"/>
												<input class="contact-submit button" type="submit" value="完 成">
												<input class="contact-submit button" type="button" value="取 消">
											</form>
										</div>
                                    </div>
                                    
                                </div>
                            </div>
                        </section> 
                        
                        <jsp:include page="../inc/rightSidebar.jsp"></jsp:include>
                    	
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="../inc/footer.jsp"></jsp:include>
           
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
