<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
String contextPath = ConstFront.CONTEXT_PATH;

User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
boolean isDesigner = currentUser.getDesignerStatus() == ConstService.DESIGNER_APPLY_APPROVED;
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <title>我的资料 - 【金玩儿网】</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="icon" href="<%=contextPath%>/img/favicon.ico">
        <link rel="stylesheet" href="<%=contextPath%>/css/bootstrap.min.css?v=${version}">
        <link rel="stylesheet" href="<%=contextPath%>/css/font-awesome.css?v=${version}">
        <link rel="stylesheet" href="<%=contextPath%>/css/animate.css?v=${version}">
        <link rel="stylesheet" href="<%=contextPath%>/css/flexslider.css?v=${version}">
        <link rel="stylesheet" href="<%=contextPath%>/css/style.css?v=${version}">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/css/ie8.css?v=${version}" />    
        <![endif]-->
                

        <script src="<%=contextPath%>/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js?v=${version}"></script>
        <script src="<%=contextPath%>/js/vendor/jquery-1.8.3.min.js?v=${version}"></script>

        <jsp:include page="../inc/baiduAsyncStat.jsp"></jsp:include>
    </head>
    <body class="body-background" style="background-image: url(<%=contextPath%>/img/backgrounds/bg.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

	<jsp:include page="../inc/topBar.jsp"></jsp:include>


	<div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                <jsp:include page="../inc/headerBanner.jsp"></jsp:include>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="../inc/headerNav.jsp?menuFlag=settings"></jsp:include>
					<jsp:include page="../inc/ad.jsp"></jsp:include>
                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="<%=contextPath%>">首页</a>/</li>
                        <li><a href="<%=contextPath%>/settings">设置</a>/</li>
                        <li><a href="javascript:void(0)">个人资料</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
							<div class="content-title">
                                <h2>个人设置</h2>
                            </div>

                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                	<jsp:include page="./settingsTabInc.jsp"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="info">
                                        <div class="widget-box widget-wrapper-form">
											<div class="content-title">
												<h4>个人资料</h4> 
											</div>
											
											<form class="widget-form" method="post" class="clearfix"
												action="<%=contextPath%>/settings/info">
												
												<div class="row-container clearfix">
													<div class="row-left">用户身份：</div>
													<div class="row-right">
													<%=isDesigner?"设计师":"普通用户"%>
													</div>
												</div>
												<div class="row-container clearfix">
													<div class="row-left">登录名：</div>
													<div class="row-right">
														<!-- <input type="text" name="username" value="liqian2"> -->
														<%=currentUser.getUsername()%>
													</div>
												</div>
												<div class="row-container clearfix">
													<div class="row-left">昵称：</div>
													<div class="row-right">
														<!-- <input type="text" name="username" value="liqian2"> -->
														<%=currentUser.getNickname()%>
													</div>
												</div>
												
												<!-- <div class="row-container clearfix">
													<div class="row-left">性别：</div>
													<div class="row-right">
														<input type="radio" name="gender" value="1"/>男
														<input type="radio" name="gender" value="2"/>女
													</div>
												</div>
												
												<div class="row-container clearfix">
													<input class="common-submit button" type="submit" value="修 改">
													<input class="common-submit button" type="reset" value="重 置">
												</div> -->
												
											</form>
										</div>
                                    </div>
                                    
                                </div>
                            </div>
                        </section> 
                        
                        <!-- right slidebar -->
						<aside class="sidebar widgets-light span3">
                       		<jsp:include page="../inc/right/sidebar_settings.jsp"></jsp:include> 
                    	</aside>
                    	
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="../inc/footer.jsp"></jsp:include>
           
        </div> <!-- Close Page -->
   </div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="<%=contextPath%>/js/vendor/bootstrap.min.js?v=${version}"></script>
    <script src="<%=contextPath%>/js/jquery.hoverdir.js?v=${version}"></script>
    <script src="<%=contextPath%>/js/superfish.js?v=${version}"></script>
    <!-- <script src="<%=contextPath%>/js/supersubs.js?v=${version}"></script> -->
   <!--  <script src="<%=contextPath%>/js/jquery.tweet.js?v=${version}"></script>  -->
    <script src="<%=contextPath%>/js/jquery.flexslider.js?v=${version}"></script> 
    <!--<script src="<%=contextPath%>/js/retina.js?v=${version}"></script>-->

    <script src="<%=contextPath%>/js/custom.js?v=${version}"></script>

    </body>

<jsp:include page="../inc/weixinShare_site.jsp"></jsp:include>
</html>
