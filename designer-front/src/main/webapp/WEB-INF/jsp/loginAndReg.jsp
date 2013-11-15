<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
String redirectUrl = (String)request.getAttribute(ConstFront.REDIRECT_URL);
boolean registerActive = (null != (String)request.getAttribute(ConstFront.REGISTER_ACTIVE));
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

                    <jsp:include page="./inc/ad.jsp"></jsp:include>

                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="/">首页</a>/</li>
                        <li><a href="javascript:void(0)">登陆注册</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">            
                <div class="container">
                    <div class="row-fluid">
                        
                        <section class="content span6 offset3">
							<div class="shortcode-tabs">
							    <ul class="tabs-nav tabs clearfix">
							        <li <%=!registerActive?"class='active'":""%>><a class="button button-white" href="#login" data-toggle="tab">已有账户登录</a></li>
							        <li <%=registerActive?"class='active'":""%>><a class="button button-white" href="#register" data-toggle="tab">新用户注册</a></li>
							    </ul>
							     
							    <div class="tab-content">
							        <div class="tab-pane widgets-light <%=registerActive?"active":""%>" id="register">
							        	<div class="widget-box widget-wrapper-form">
											<div class="content-title">
												<h4>请填写注册信息</h4>
											</div>
											
											<div id="regErrorContainer" class="infobox info-error info-error-alt clearfix">
				                                <span></span>
				                                <div class="infobox-wrap">
				                                    <h4>出错了！</h4>
				                                    <p id="regErrorMessage">您的输入有误，请重新输入！</p>
				                                </div>
				                                <a href="#" class="info-hide"></a>
				                            </div>
				                            
											<form id="widget-form" method="post" class="clearfix"
												action="/designer-front/register">
												<div class="row-container clearfix">
													<div class="row-left">用户名: </div>
													<div class="row-right">
														<input type="text" class="span6" name="username"/>
													</div> 
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">昵 称: </div>
													<div class="row-right">
														<input type="text" class="span6" name="nickname"/>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">密 码: </div>
													<div class="row-right">
														<input type="password" class="span6" name="password"/>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">密码: </div>
													<div class="row-right">
														<input type="password" class="span6" name="repassword"/>
													</div>
												</div>
												
												<input class="common-submit button" type="submit" value="注 册">
												<input class="common-submit button" type="button" value="取 消">
											</form>
										</div>
							        </div>
							        <div class="tab-pane widgets-light <%=!registerActive?"active":""%>" id="login">
							        	<div class="widget-box widget-wrapper-form">
								            <div class="content-title">
												<h4>请填写账户密码进行登录</h4>
											</div>
											
				                            <div id="loginErrorContainer" class="infobox info-error info-error-alt clearfix">
				                                <span></span>
				                                <div class="infobox-wrap">
				                                    <h4 id="loginErrorTitle">很抱歉</h4>
				                                    <p id="loginErrorMessage">您的输入有误，请重新输入！</p>
				                                </div>
				                                <a href="#" class="info-hide"></a>
				                            </div>
				                            <div id="loginErrorContainer" class="infobox info-succes info-succes-alt clearfix">
				                                <span></span>
				                                <div class="infobox-wrap">
				                                    <h4 id="loginErrorTitle">身份通过验证</h4>
				                                    <p id="loginErrorMessage">用户验证通过，正在为您跳转...</p>
				                                </div>
				                                <a href="#" class="info-hide"></a>
				                            </div>
				                            
											<form id="widget-form" method="post" class="clearfix"
												action="/designer-front/login">
												<%if(redirectUrl!=null){%>
													<input type="hidden" name="<%=ConstFront.REDIRECT_URL%>" value="<%=redirectUrl%>"/>
												<%}%>
												<div class="row-container clearfix">
													<div class="row-left">昵 称：</div>
													<div class="row-right">
														<input type="text" name="username" class="span6" value="liqian">
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">密码：</div>
													<div class="row-right">
														<input type="password" name="password"  class="span6" value="liqian">
													</div>
												</div>
												
												<input class="common-submit button" type="submit" value="登 录">
												<input class="common-submit button" type="button" value="微博登录" onclick="location.href='/designer-front/connectWeibo'"/>
												<input class="common-submit button" type="button" value="QQ登录" onclick="location.href='/designer-front/connectTencent'"/>
				
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
