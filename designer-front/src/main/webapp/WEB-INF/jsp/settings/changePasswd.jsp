<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>

<%
String contextPath = ConstFront.CONTEXT_PATH;

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
        <title>修改密码 - 【金玩儿网】</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="icon" href="<%=contextPath%>/img/favicon.ico">
        <link rel="stylesheet" href="<%=contextPath%>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/font-awesome.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/animate.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/flexslider.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/style.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/css/ie8.css" />    
        <![endif]-->
                

        <script src="<%=contextPath%>/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="<%=contextPath%>/js/vendor/jquery-1.8.3.min.js"></script>

        
        
        
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
                        <li><a href="javascript:void(0)">修改密码</a></li>
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
                                	<jsp:include page="./settingsTabInc.jsp?settingsMenuFlag=changePasswd"></jsp:include>
                                </ul> 
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="changePwd">
                                        
                                        <div class="widget-box widget-wrapper-form">
											<div class="content-title">
												<h4>修改密码</h4> 
											</div>
											<form class="widget-form" method="post"	action="<%=contextPath%>/settings/changePasswd">
												
												<div id="change-password-succeed" class="infobox info-succes info-succes-alt clearfix" style="display:none">
					                                <span></span>
					                                <div class="infobox-wrap">
					                                    <h4>提示:</h4>
					                                    <p>密码重置成功！</p>
					                                </div>
					                            </div>
					                            
					                            <div id="change-password-failed" class="infobox info-error info-error-alt clearfix" style="display:none">
					                                <span>!</span>
					                                <div class="infobox-wrap">
					                                    <h4>提示:</h4>
					                                    <p id="change-password-failed-message">
					                                   	密码重置失败！
					                                    </p>
					                                </div>
					                            </div>
					                            
												<div class="row-container clearfix">
													<div class="row-left">旧密码: </div>
													<div class="row-right">
														<input type="password" class="span5" id="oldPassword" name="oldPassword" value=""/>
														<span id="old-password-required" class="required">*</span>
														<span id="old-password-prompt" class="text-prompt">旧密码不能为空</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">新密码: </div>
													<div class="row-right">
														<input type="password" class="span5" id="password"  name="password" value=""/>
														<span id="password-required" class="required">*</span>
														<span id="password-prompt" class="text-prompt">新密码不能为空</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">确认密码: </div>
													<div class="row-right">
														<input type="password" class="span5" id="rePassword" name="rePassword" value=""/>
														<span id="re-password-required" class="required">*</span>
														<span id="re-password-prompt" class="text-prompt">确认密码不能为空</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<input id="submit-button" class="common-submit button" type="button" value="修 改">
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
    <script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
    <script src="<%=contextPath%>/js/superfish.js"></script>
    <!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
   <!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
    <script src="<%=contextPath%>/js/jquery.flexslider.js"></script> 
    <!--<script src="<%=contextPath%>/js/retina.js"></script>-->

    <script src="<%=contextPath%>/js/custom.js"></script>
    <script>
    	$(document).ready(function(){
    		$('#change-password-succeed').hide();
    		$('#change-password-failed').hide();
    		//密码格式
    	});
    	
    	$('#submit-button').click(function(){
    		$('#old-password-prompt').text('').hide();
    		$('#password-prompt').text('').hide();
    		$('#re-password-prompt').text('').hide();
    		$('#change-password-succeed').hide();
    		$('#change-password-failed').hide();
    		
    		var oldPasswordVal = $('#oldPassword').val();
    		var passwordVal = $('#password').val();
    		var rePasswordVal = $('#rePassword').val();
    		if(oldPasswordVal==''){
    			$('#oldPassword').focus();
    			$('#old-password-prompt').text('旧密码不能为空').show();
    		}else if(passwordVal==''){
    			$('#password').focus();
    			$('#password-prompt').text('新密码不能为空').show();
    		}else if(rePasswordVal==''){
    			$('#rePassword').focus();
    			$('#re-password-prompt').text('确认密码不能为空').show();
    		}else if(passwordVal!=rePasswordVal){
    			$('#password').focus();
    			$('#password-prompt').text('新密码与确认密码不匹配').show();
    		}else{//验证通过
    			var jsonData = {'oldPassword':oldPasswordVal,'password':passwordVal,'rePassword':rePasswordVal };
        		$.post('<%=contextPath%>/settings/changePasswd.json', jsonData, function(data) {
       				var result = data.result;
       				if(result==1){
       					$('#oldPassword').val('');
       					$('#password').val('');
       					$('#rePassword').val('');
       					$('#change-password-succeed').show().fadeOut(3000);
       				}else{
       					$('#change-password-failed-message').text(data.message);
       					$('#change-password-failed').show();
       				}
       			});
    			
    		}
    	});
    
    </script>

    </body>
    
<jsp:include page="../inc/weixinShare_site.jsp"></jsp:include>
</html>
