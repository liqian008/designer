<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
String redirectUrl = (String)request.getAttribute(ConstFront.REDIRECT_URL);
String loginErrorMessage = (String)request.getAttribute(ConstFront.LOGIN_ERROR_MESSAGE);
String regErrorMessage = (String)request.getAttribute(ConstFront.REG_ERROR_MESSAGE);
boolean registerActive = true;//(null != (String)request.getAttribute(ConstFront.REGISTER_ACTIVE));
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
                    <jsp:include page="../inc/headerNav.jsp"></jsp:include>

                    <jsp:include page="../inc/ad.jsp"></jsp:include>

                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="/">首页</a>/</li>
                        <li><a href="javascript:void(0)">第三方账户登录</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">            
                <div class="container">
                    <div class="row-fluid">
                        
                        <section class="content span6 offset3">
							<div class="shortcode-tabs">
							    <ul class="tabs-nav tabs clearfix">
							        <li <%=registerActive?"class='active'":""%>><a class="button button-white" href="#register" data-toggle="tab">完善帐号信息</a></li>
							        <li <%=!registerActive?"class='active'":""%>><a class="button button-white" href="#login" data-toggle="tab">绑定已有账户</a></li>
							    </ul>
							     
							    <div class="tab-content">
							        <div class="tab-pane widgets-light <%=registerActive?"active":""%>" id="register">
							        	<div class="widget-box widget-wrapper-form">
											<div class="content-title">
												<h4>完善帐号信息可体验本站更多功能</h4>
											</div>
											
											<%if(regErrorMessage!=null){%>
											<div id="reg-failed" class="infobox info-error info-error-alt clearfix">
				                                <span></span>
				                                <div class="infobox-wrap">
				                                    <h4 id="regErrorTitle">提示</h4>
				                                    <p id="regErrorMessage"><%=regErrorMessage%></p>
				                                </div>
				                            </div>
											<%}%>
				                            
											<form id="reg-widget-form" method="post" class="clearfix"
												action="/designer-front/oauthRegister">
												<div class="row-container clearfix">
													<div class="row-left">用户名: </div>
													<div class="row-right">
														<input type="text" class="span5" id="reg-username" name="username"/>
														<span id="reg-username-required" class="required">*</span>
														<span id="reg-username-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">昵 称: </div>
													<div class="row-right">
														<input type="text" class="span5" id="reg-nickname" name="nickname"/>
														<span id="reg-nickname-required" class="required">*</span>
														<span id="reg-nickname-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">密 码: </div>
													<div class="row-right">
														<input type="password" class="span5" id="reg-password" name="password"/>
														<span id="reg-password-required" class="required">*</span>
														<span id="reg-password-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">确认密码: </div>
													<div class="row-right">
														<input type="password" class="span5" id="reg-rePassword" name="rePassword"/>
														<span id="reg-rePassword-required" class="required">*</span>
														<span id="reg-rePassword-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<input id="oauth-reg" class="common-submit button" type="button" value="提 交">
												<input id="oauth-reg-cancel" class="common-submit button" type="reset" value="取 消">
											</form>
										</div>
							        </div>
							        <div class="tab-pane widgets-light <%=!registerActive?"active":""%>" id="login">
							        	<div class="widget-box widget-wrapper-form">
								            <div class="content-title">
												<h4>绑定已有账户进行登录</h4>
											</div>
											
											<%if(loginErrorMessage!=null){%>
											<div id="login-failed" class="infobox info-error info-error-alt clearfix">
				                                <span></span>
				                                <div class="infobox-wrap">
				                                    <h4 id="loginErrorTitle">提示</h4>
				                                    <p id="loginErrorMessage"><%=loginErrorMessage%></p>
				                                </div>
				                            </div>
											<%}%>
				                            
											<form id="login-widget-form" method="post" class="clearfix"
												action="/designer-front/oauthBind">
												
												<div class="row-container clearfix">
													<div class="row-left">昵 称：</div>
													<div class="row-right">
														<input type="text" id="login-username" name="username" class="span5" value="liqian">
														<span id="login-username-required" class="required">*</span>
														<span id="login-username-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">密 码：</div>
													<div class="row-right">
														<input type="password" id="login-password" name="password"  class="span5" value="liqian">
														<span id="login-password-required" class="required">*</span>
														<span id="login-password-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<input id="oauth-bind" class="common-submit button" type="button" value="绑 定">
												<input id="oauth-bind-cancel" class="common-submit button" type="button" value="取 消">
				
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
	<script>
    	$(document).ready(function(){
    		<%if(registerActive){%>
				$('#reg-username').focus();
			<%}else{%>
				$('#login-username').focus();
			<%}%>
    	});
    	
    	$('#oauth-reg-cancel').click(function(){
			location.href="/designer-front/";
    	});
    	
    	$('#oauth-bind-cancel').click(function(){
			location.href="/designer-front/";
    	});
    	
    	$('#oauth-bind').click(function(){
    		$('#login-username-prompt').text('').hide();
    		$('#login-password-prompt').text('').hide();
    		//$('#login-failed').hide();
    		
    		var usernameVal = $('#login-username').val();
    		var passwordVal = $('#login-password').val();
    		if(usernameVal==''){
    			$('#login-username').focus();
    			$('#login-username-prompt').text('用户名不能为空').show();
    		}else if(passwordVal==''){
    			$('#login-password').focus();
    			$('#login-password-prompt').text('密码不能为空').show();
    		}else{//验证通过
    			//提交请求
    			$('#login-widget-form').submit();
    		}
    	});
    	
    	//校验注册账户的数据完整性
    	
    	$('#reg-username').blur(function(){
    		checkRegUsername();
    	});
    	$('#reg-nickname').blur(function(){
    		checkRegNickname();
    	});
    	$('#reg-password').blur(function(){
    		checkRegPassword();
    	});
    	$('#reg-rePassword').blur(function(){
    		checkRegPassword();
    	});
    	
    	var usernameAvailable = false;
    	var nicknameAvailable = false;
    	var passwordAvailable = false;
    	
    	
    	$('#oauth-reg').click(function(){
    		alert(usernameAvailable);
        	alert(nicknameAvailable);
        	alert(passwordAvailable);
        	
    		if(usernameAvailable && nicknameAvailable && passwordAvailable){
    			//所有数据项均可用
	    		$('#reg-widget-form').submit();
    		}
    	});
    	
    	//检查用户名是否合法
    	function checkRegUsername(){
    		usernameAvailable = false;
    		var usernameVal = $('#reg-username').val();
    		//中文、英文字符 4-6位
    		var usernameRegex =  /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{4,16}$/;
    		if(usernameVal==''){
    			$('#reg-username-prompt').text('用户名不能为空').show();
        		return false;
    		}else if(!usernameRegex.test(usernameVal)){//检查正则匹配
    			$('#reg-username-prompt').text('用户名不符合规范').show();
        		return false;
    		}else{//ajax检查是否可用
    			var jsonData = {'username':usernameVal};
    			$.post('/designer-front/usernameExists.json', jsonData, function(data) {
       				var result = data.result;
       				if(result==1){
       					//设置username available的标识
       					usernameAvailable = true;
       					$('#reg-username-prompt').hide();
       				}else{
       					//设置username unabailable的标识
       					usernameAvailable = false;
       					$('#reg-username-prompt').text(data.message).show();
       				}
       			});
    		}
    	}
    	
    	//检查昵称是否合法
    	function checkRegNickname(){
    		nicknameAvailable = false;
    		var nicknameVal = $('#reg-nickname').val();
    		var nicknameRegex =  /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{4,16}$/;
    		if(nicknameVal==''){
    			$('#reg-nickname-prompt').text('昵称不能为空').show();
    		}else if(!nicknameRegex.test(nicknameVal)){//监察正则匹配
    			$('#reg-nickname-prompt').text('昵称不符合规范').show();
    		}else{//ajax检查昵称是否可用
    			var jsonData = {'nickname':nicknameVal};
    			$.post('/designer-front/nicknameExists.json', jsonData, function(data) {
       				var result = data.result;
       				if(result==1){
       					//设置nickname available的标识
       					nicknameAvailable = true;
       					$('#reg-nickname-prompt').text('').hide();
       				}else{
       	    			//设置nickname unavailable的标识
       	    			nicknameAvailable = false;
       	    			$('#reg-nickname-prompt').text(data.message).show();
       				}
       			});
    		}
    	}

    	//检查密码是否合法
    	function checkRegPassword(){
    		passwordAvailable = false;
    		var passwordVal = $('#reg-password').val();
    		var rePasswordVal = $('#reg-rePassword').val();
    		var passwordRegex = /^(\w){6,20}$/;
    		if(passwordVal==''){
    			$('#reg-password-prompt').text('密码不能为空').show();
    		}else if(!passwordRegex.test(passwordVal)){ 
    			$('#reg-password-prompt').text('密码不符合规范').show();
    		}else{
    			//密码验证完成，开始验证确认密码
    			$('#reg-password-prompt').text('').hide();
    			if(rePasswordVal==''){
	    			$('#reg-rePassword-prompt').text('确认密码不能为空').show();
	    		}else if(passwordVal!=rePasswordVal){
	    			$('#reg-rePassword-prompt').text('密码与确认密码不匹配').show();
	    		}else{
	    			passwordAvailable = true;
	        		$('#reg-rePassword-prompt').text('').hide();
	    		}
    		}
    	}
    </script>
    </body>
</html>
