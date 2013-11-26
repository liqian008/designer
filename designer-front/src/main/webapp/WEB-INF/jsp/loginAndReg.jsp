<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
String redirectUrl = (String)request.getAttribute(ConstFront.REDIRECT_URL);
String loginErrorMessage = (String)request.getAttribute(ConstFront.LOGIN_ERROR_MESSAGE);
String regErrorMessage = (String)request.getAttribute(ConstFront.REG_ERROR_MESSAGE);
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
                        <li><a href="javascript:void(0)">登录注册</a></li>
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
												action="/designer-front/register">
												<div class="row-container clearfix">
													<div class="row-left">用户名: </div>
													<div class="row-right">
														<input type="text" class="span5" id="reg-username" name="username"/>
														<span id="reg-username-required" class="required">*</span>
														<span id="reg-username-prompt" class="text-prompt">字符,数字及下划线，4-20位</span>
													</div> 
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">昵 称: </div>
													<div class="row-right">
														<input type="text" class="span5" id="reg-nickname" name="nickname"/>
														<span id="reg-nickname-required" class="required">*</span>
														<span id="reg-nickname-prompt" class="text-prompt">字符,数字及下划线，4-20位</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">密 码: </div>
													<div class="row-right">
														<input type="password" class="span5" id="reg-password" name="password"/>
														<span id="reg-password-required" class="required">*</span>
														<span id="reg-password-prompt" class="text-prompt">6-20位</span>
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
												
												<input id="reg-button" class="common-submit button" type="button" value="注 册">
												<input class="common-submit button" type="reset" value="取 消">
											</form>
										</div>
							        </div>
							        <div class="tab-pane widgets-light <%=!registerActive?"active":""%>" id="login">
							        	<div class="widget-box widget-wrapper-form">
								            <div class="content-title">
												<h4>请填写账户密码进行登录</h4>
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
												action="/designer-front/login">
												<%if(redirectUrl!=null){%>
													<input type="hidden" name="<%=ConstFront.REDIRECT_URL%>" value="<%=redirectUrl%>"/>
												<%}%>
												
												<div class="row-container clearfix">
													<div class="row-left">用户名：</div>
													<div class="row-right">
														<input type="text" id="login-username" name="username" class="span5" value="liqian">
														<span id="login-username-required" class="required">*</span>
														<span id="login-username-prompt" class="text-prompt">昵称不能为空</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">密 码：</div>
													<div class="row-right">
														<input type="password" id="login-password" name="password"  class="span5" value="liqian">
														<span id="login-password-required" class="required">*</span>
														<span id="login-password-prompt" class="text-prompt">密码不能为空</span>
													</div>
												</div>
												
												<input id="login-button" class="common-submit button" type="button" value="登 录">
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
	<script>
    	$(document).ready(function(){
    		//$('#login-failed').hide();
    	});
    	
    	$('#login-button').click(function(){
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
    	
    	$('#reg-button').click(function(){
    		if(checkRegUsername()&&checkRegNickname()&&checkRegPassword()){
	    		$('#reg-widget-form').submit();
	    	}
    	});
    	
    	//检查用户名是否合法
    	function checkRegUsername(){
    		var usernameVal = $('#reg-username').val();
    		//中文、英文字符 4-6位
    		var usernameRegex =  /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{4,16}$/;
    		if(usernameVal==''){
    			$('#reg-username-prompt').text('用户名不能为空').show();
    			$('#reg-username').focus();
        		return false;
    		}else if(!usernameRegex.test(usernameVal)){//检查正则匹配
    			$('#reg-username-prompt').text('用户名不符合规范').show();
    			$('#reg-username').focus();
        		return false;
    		}else{//ajax检查是否可用
    			var jsonData = {'username':usernameVal};
    			$.post('/designer-front/usernameExists.json', jsonData, function(data) {
       				var result = data.result;
       				if(result==1){
       					$('#reg-username-prompt').hide();
       					//设置username available的标识
       				}else{
       					$('#reg-username-prompt').text(data.message).show();
       					//设置username unabailable的标识
       				}
       			});
    		}
    		return true;
    	}
    	
    	//检查昵称是否合法
    	function checkRegNickname(){
    		var nicknameVal = $('#reg-nickname').val();
    		var nicknameRegex =  /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{4,16}$/;
    		if(nicknameVal==''){
    			$('#reg-nickname-prompt').text('昵称不能为空').show();
    			$('#reg-nickname').focus();
        		return false;
    		}else if(!nicknameRegex.test(nicknameVal)){//监察正则匹配
    			$('#reg-nickname-prompt').text('昵称不符合规范').show();
    			$('#reg-nickname').focus();
        		return false;
    		}else{//ajax检查昵称是否可用
    			var jsonData = {'nickname':nicknameVal};
    			$.post('/designer-front/nicknameExists.json', jsonData, function(data) {
       				var result = data.result;
       				if(result==1){
       					$('#reg-nickname-prompt').text('').hide();
       					//设置nickname available的标识
       				}else{
       	    			$('#reg-nickname-prompt').text(data.message).show();
       	    			//设置nickname unavailable的标识
       				}
       			});
    		}
    		return true;
    	}

    	//检查密码是否合法
    	function checkRegPassword(){
    		var passwordVal = $('#reg-password').val();
    		var rePasswordVal = $('#reg-rePassword').val();
    		var passwordRegex = /^(\w){6,20}$/;
    		if(passwordVal==''){
    			$('#reg-password-prompt').text('密码不能为空').show();
    		}else if(!passwordRegex.test(passwordVal)){ 
    			$('#reg-password').focus();
    			$('#reg-password-prompt').text('密码不符合规范').show();
    		}else{
    			//密码验证完成，开始验证确认密码
    			$('#reg-password-prompt').text('').hide();
    			if(rePasswordVal==''){
	    			$('#reg-rePassword').focus();
	    			$('#reg-rePassword-prompt').text('确认密码不能为空').show();
	    			return false;
	    		}else if(passwordVal!=rePasswordVal){
	    			$('#reg-rePassword').focus();
	    			$('#reg-rePassword-prompt').text('密码与确认密码不匹配').show();
	    			return false;
	    		}else{
	        		$('#reg-rePassword-prompt').text('').hide();
	    			return true;
	    		}
    		}
    		$('#reg-password').focus();
    		return false;
    	}
    </script>
    </body>
</html>
