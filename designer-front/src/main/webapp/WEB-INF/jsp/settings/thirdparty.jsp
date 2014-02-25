<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
boolean isDesigner = currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;

%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <title>第三方账户绑定 - 【金玩儿网】</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="/designer-front/css/bootstrap.min.css">
        <link rel="stylesheet" href="/designer-front/css/font-awesome.css">
        <link rel="stylesheet" href="/designer-front/css/animate.css">
        <link rel="stylesheet" href="/designer-front/css/flexslider.css">
        <link rel="stylesheet" href="/designer-front/css/style.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="/designer-front/css/ie8.css" />    
        <![endif]-->
                

        <script src="/designer-front/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="/designer-front/js/vendor/jquery-1.8.3.min.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(/designer-front/img/backgrounds/bg3.jpg); ">

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
                        <li><a href="/designer-front">首页</a>/</li>
                        <li><a href="/designer-front/settings">设置</a>/</li>
                        <li><a href="javascript:void(0)">第三方账户绑定</a></li>
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
                                	<jsp:include page="./settingsTabInc.jsp?settingsMenuFlag=thirdparty"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="info">
                                        <div class="widget-box widget-wrapper-form">
											<form class="widget-form" method="post" class="clearfix"
												action="/designer-front/settings/info">
												
												<div class="content-title">
													<h4>第三方账户绑定</h4> 
												</div>
												
												<div class="infobox info-warning info-warning-alt clearfix">
					                                <span>i</span>
					                                <div class="infobox-wrap">
					                                    <h4>小贴士：</h4>
					                                    <p>
					                                    <%if(isDesigner){%>
					                                    绑定微博或QQ账户，有助于发布的作品在相应平台的自动传播，强烈建议设计师均进行绑定！
					                                    <%}else{ %>
					                                    绑定微博或QQ后，即可以使用相应账户登录本站。
					                                    <%} %>
					                                    </p>
					                                </div>
					                            </div>
														
												<div class="row-container clearfix">
													<div class="row-left">Sina微博: </div>
													<div class="row-right">
														<%
														AccessTokenInfo wbToken = currentUser.getAccessTokenMap().get(IOAuthService.OAUTH_WEIBO_TYPE);
														boolean wbBound = wbToken!=null;
														%>
														<%=wbBound?"已绑定":"未绑定"%><%=wbToken!=null?"【"+wbToken.getThirdpartyUname()+"】":""%>
														<%if(wbBound){%>
														<!-- <a href="/designer-front/unbindOauth?thirdpartyType=1" class="button button-small button-white">解绑Sina微博账户</a> -->
														<input type="button" class="button button-white" value="解 绑" onclick="location.href='/designer-front/unbindOauth?thirdpartyType=1'"/>
														<input type="checkbox" name="sync2Weibo" value="1" <%=wbToken.getSyncAlbum()==1?"checked='checked'":""%>/>发布作品时同步到Sina微博
														<%}else{%>
														<input type="button" class="button button-green" value="现在就去绑定" onclick="location.href='/designer-front/connectWeibo'"/>
														<!-- 
														<a href="/designer-front/connectWeibo" class="button button-small button-green">现在就去绑定</a>
														 -->
														<%}%>
													</div>
												</div>
												<div class="row-container clearfix">
													<div class="row-left">QQ: </div>
													<div class="row-right">
														<%
														AccessTokenInfo tencentToken = currentUser.getAccessTokenMap().get(IOAuthService.OAUTH_TENCENT_TYPE);
														boolean tencentBound = tencentToken!=null;
														%>
														<%=tencentBound?"已绑定":"未绑定"%><%=tencentToken!=null?"【"+tencentToken.getThirdpartyUname()+"】":""%>
														<%if(tencentBound){%>
														<!-- <a href="/designer-front/unbindOauth?thirdpartyType=2" class="button button-small button-white">解绑QQ账户</a> -->
														<input type="button" class="button button-white" value="解 绑" onclick="location.href='/designer-front/unbindOauth?thirdpartyType=2'"/>
														<input type="checkbox" name="sync2Tencent" value="1" <%=wbToken.getSyncAlbum()==1?"checked='checked'":""%>/>发布作品时同步到QQ空间
														<%}else{%>
														<input type="button" class="button button-green" value="现在就去绑定" onclick="location.href='/designer-front/connectTencent'"/>
														<!--<a href="/designer-front/connectTencent" class="button button-small button-green">现在就去绑定</a> -->
														<%}%>
													</div>
												</div>
												<div class="row-container clearfix">
													<input class="common-submit button" type="submit" value="修 改">
													<input class="common-submit button" type="reset" value="重 置">
												</div>
												
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
    <script src="/designer-front/js/vendor/bootstrap.min.js"></script>
    <script src="/designer-front/js/jquery.hoverdir.js"></script>
    <script src="/designer-front/js/superfish.js"></script>
    <!-- <script src="/designer-front/js/supersubs.js"></script> -->
   <!--  <script src="/designer-front/js/jquery.tweet.js"></script>  -->
    <script src="/designer-front/js/jquery.flexslider.js"></script> 
    <script src="/designer-front/js/retina.js"></script>

    <script src="/designer-front/js/custom.js"></script>

    </body>
</html>