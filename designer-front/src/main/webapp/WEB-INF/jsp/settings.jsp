<%@page import="com.bruce.designer.front.controller.FrontController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
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
                        <section class="content span9">
							<div class="content-title">
                                <h2>管理中心</h2>
                            </div>

                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                    <li class="active"><a class="button button-white" href="#profile" data-toggle="tab">个人基本信息</a></li>
                                    <li><a class="button button-white" href="#changePwd" data-toggle="tab">修改密码</a></li>
                                    <li><a class="button button-white" href="./myFavorites.art">我的收藏</a></li>
                                    <li><a class="button button-white" href="./myFlowerings.art">我的关注</a></li>
                                    <li><a class="button button-white" href="./myFlowers.art">我的粉丝</a></li>
                                    <li><a class="button button-white" href="#designerProfile" data-toggle="tab">设计师基本信息</a></li>
                                    <li><a class="button button-white" href="#syncSettings" data-toggle="tab">作品分享器（推荐）</a></li>
                                    <li><a class="button button-white" href="#apply4Designer" data-toggle="tab">申请成为设计师</a></li>
                                </ul>
                                <div class="tab-content span8">
                                    <div class="tab-pane widgets-light" id="syncSettings">
                                        <div class="widget-box widget-contact-form">
											<div class="content-title">
												<h4>分享设置</h4>
											</div>
											<div class="input-container">
												Sina微博: 
												<%=user.getAccessTokenMap().get(IOAuthService.OAUTH_WEIBO_TYPE)!=null?"已绑定":"未绑定"%>
												<br/>
												<input type="text" class="contact-form-name" name="syncAlbum"
														value="发布作品时同步分享到Sina微博"/>
											</div>
											<div class="input-container">
												腾讯微博: 
												<%=user.getAccessTokenMap().get(IOAuthService.OAUTH_TENCENT_TYPE)!=null?"已绑定":"未绑定"%>
												<br/>
												<input type="text" class="contact-form-name" name="syncAlbum"
														value="发布作品时同步分享到腾讯微博"/>
											</div>
											<div class="input-container">
												人人网: 
												<%=user.getAccessTokenMap().get(IOAuthService.OAUTH_RENREN_TYPE)!=null?"已绑定":"未绑定"%>
												<br/>
												<input type="text" class="contact-form-name" name="syncAlbum"
														value="发布作品时同步分享到人人网"/>
											</div>
										</div>
                                    </div>
                                    <div class="tab-pane widgets-light" id="designerProfile">
                                        <div class="widget-box widget-contact-form">
											<div class="content-title">
												<h4>设计师资料</h4>
											</div>
											<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/oauthRegister.art">
												<div class="input-container">
													姓 名: <input type="text" class="contact-form-name" name="username"
														value="姓 名"/>
												</div>
												<div class="input-container">
													身份证号: <input type="text" class="contact-form-name" name="nickname"
														value="身份证号"/>
												</div>
												<div class="input-container">
													公 司: <input type="text" class="contact-form-name" name="nickname"
														value="公 司"/>
												</div>
												<div class="input-container">
													个人主页: <input type="text" class="contact-form-name" name="nickname"
														value="个人主页"/>
												</div>
												<div class="input-container">
													淘宝店铺链接: <input type="text" class="contact-form-name" name="nickname"
														value="淘宝店铺链接"/>
												</div>
												<input class="contact-submit button" type="button" value="申 请">
												<input class="contact-submit button" type="button" value="完 成">
												<input class="contact-submit button" type="button" value="取 消">
											</form>
										</div>
                                    </div>
                                    <div class="tab-pane widgets-light active" id="profile">
                                        <div class="widget-box widget-contact-form">
											<div class="content-title">
												<h4>个人资料</h4>
											</div>
											<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/oauthRegister.art">
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
												<input class="contact-submit button" type="button" value="完 成">
												<input class="contact-submit button" type="button" value="取 消">
											</form>
										</div>
                                    </div>
                                    <div class="tab-pane widgets-light" id="changePwd">
                                        <div class="widget-box widget-contact-form">
											<div class="content-title">
												<h4>修改密码</h4>
											</div>
											<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/oauthRegister.art">
												<div class="input-container">
													旧密码: <input type="password" class="contact-form-email" name="oldPassword"
														value=""/> 
												</div>
												<div class="input-container">
													新密码: <input type="password" class="contact-form-email" name="password"
														value=""/> 
												</div>
												<div class="input-container">
													确认密码: <input type="password" class="contact-form-email" name="repassword"
														value=""/> 
												</div>
												<input class="contact-submit button" type="button" value="完 成">
												<input class="contact-submit button" type="button" value="取 消">
											</form>
										</div>
                                    </div>
                                    
                                    <div class="tab-pane widgets-light" id="apply4Designer">
                                        <div class="widget-box widget-contact-form">
											<div class="content-title">
												<h4>申请设计师</h4>
											</div>
											<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/oauthRegister.art">
												<div class="input-container">
													姓 名: <input type="text" class="contact-form-name" name="username"
														value="姓 名"/>
												</div>
												<div class="input-container">
													身份证号: <input type="text" class="contact-form-name" name="nickname"
														value="身份证号"/>
												</div>
												<div class="input-container">
													公司: <input type="text" class="contact-form-name" name="nickname"
														value="公 司"/>
												</div>
												<div class="input-container">
													淘宝店铺链接: <input type="text" class="contact-form-name" name="nickname"
														value="淘宝店铺链接"/>
												</div>
												<input class="contact-submit button" type="button" value="申 请">
												<input class="contact-submit button" type="button" value="完 成">
											</form>
										</div>
                                    </div>
                                    <div class="tab-pane widgets-light" id="changePwd">
                                        <div class="widget-box widget-contact-form">
											<div class="content-title">
												<h4>修改密码</h4>
											</div>
											<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/oauthRegister.art">
												<div class="input-container">
													旧密码: <input type="password" class="contact-form-email" name="oldPassword"
														value=""/> 
												</div>
												<div class="input-container">
													新密码: <input type="password" class="contact-form-email" name="password"
														value=""/> 
												</div>
												<div class="input-container">
													确认密码: <input type="password" class="contact-form-email" name="repassword"
														value=""/> 
												</div>
												<input class="contact-submit button" type="button" value="完 成">
												<input class="contact-submit button" type="button" value="取 消">
											</form>
										</div>
                                    </div>
                                    
                                </div>
                            </div>
                        </section> 
                        
                        <jsp:include page="./inc/rightSidebar.jsp"></jsp:include>
                    	
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
