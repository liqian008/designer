<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
String contextPath = ConstFront.CONTEXT_PATH;
%>

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
        <title>登录 | 注册 - 【金玩儿网】</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

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

                    <jsp:include page="../inc/ad.jsp"></jsp:include>

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
				                                    <h4 id="regErrorTitle">错误提示</h4>
				                                    <p id="regErrorMessage"><%=regErrorMessage%></p>
				                                </div>
				                            </div>
											<%}%>
				                            
											<form id="reg-widget-form" method="post" class="clearfix"
												action="<%=contextPath%>/register">
												<div class="row-container clearfix">
													<div class="row-left">邮 箱: </div>
													<div class="row-right">
														<input type="text" class="span5" id="reg-username" name="username"/>
														<span id="reg-username-required" class="required">*</span>
														<span id="reg-username-prompt" class="text-prompt">Email格式</span>
													</div> 
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">中文昵称: </div>
													<div class="row-right">
														<input type="text" class="span5" id="reg-nickname" name="nickname"/>
														<span id="reg-nickname-required" class="required">*</span>
														<span id="reg-nickname-prompt" class="text-prompt">字符、数字及下划线，2-20位</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">密 码: </div>
													<div class="row-right">
														<input type="password" class="span5" id="reg-password" name="password"/>
														<span id="reg-password-required" class="required">*</span>
														<span id="reg-password-prompt" class="text-prompt">字符、数字，6-20位</span>
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
												
												<div class="row-container clearfix">
													<div class="row-left">验证码：</div>
													<div class="row-right">
														<input type="text" id="reg-verifyCode" name="verifyCode" class="span2" value="">
														<span id="reg-verifyCode-required" class="required">*</span>
														<a href="javascript:void(0)">
														<img src='<%=contextPath%>/verifyCode' id="reg-verifyCode-img" width="75px"/>
														</a>
														<span id="reg-verifyCode-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<input id="reg-button" class="common-submit button" type="submit" value="注 册">
												<input class="common-submit button" type="reset" value="重 置">
											</form>
										</div>
							        </div>
							        <div class="tab-pane widgets-light <%=!registerActive?"active":""%>" id="login">
							        	<div class="widget-box widget-wrapper-form">
								            <div class="content-title">
												<h4>请填写邮箱、密码进行登录</h4>
											</div>
											
											<%if(loginErrorMessage!=null){%>
											<div id="login-failed" class="infobox info-error info-error-alt clearfix">
				                                <span></span>
				                                <div class="infobox-wrap">
				                                    <h4 id="loginErrorTitle">错误提示</h4>
				                                    <p id="loginErrorMessage"><%=loginErrorMessage%></p>
				                                </div>
				                            </div>
											<%}%>
				                            
											<form id="login-widget-form" method="post" class="clearfix"
												action="<%=contextPath%>/login">
												<%if(redirectUrl!=null){%>
													<input type="hidden" name="<%=ConstFront.REDIRECT_URL%>" value="<%=redirectUrl%>"/>
												<%}%>
												
												<div class="row-container clearfix">
													<div class="row-left">邮 箱：</div>
													<div class="row-right">
														<input type="text" id="login-username" name="username" class="span5" value="">
														<span id="login-username-required" class="required">*</span>
														<span id="login-username-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">密 码：</div>
													<div class="row-right">
														<input type="password" id="login-password" name="password"  class="span5" value="">
														<span id="login-password-required" class="required">*</span>
														<span id="login-password-prompt" class="text-prompt"></span>
													</div>
												</div>

												<div class="row-container clearfix">
													<div class="row-left">验证码：</div>
													<div class="row-right">
														<input type="text" id="login-verifyCode" name="verifyCode" class="span2" value="">
														<a href="javascript:void(0)"><img src='<%=contextPath%>/verifyCode' id="login-verifyCode-img" width="75px" height="30px"/></a>
														<span id="login-verifyCode-required" class="required">*</span>
														<span id="login-verifyCode-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												
												<input id="login-button" class="common-submit button" type="submit" value="登 录">
												<input class="wb-login common-submit button" type="button" onclick="location.href='<%=contextPath%>/connectWeibo'"/>
												<input class="qq-login common-submit button" type="button" onclick="location.href='<%=contextPath%>/connectTencent'"/>
												
												<!-- 
												<input class="common-submit button" type="button" value="微博登录" onclick="location.href='<%=contextPath%>/connectWeibo'"/>
												<input class="common-submit button" type="button" value="QQ登录" onclick="location.href='<%=contextPath%>/connectTencent'"/>
												 -->
												 
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
    <script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
    <script src="<%=contextPath%>/js/superfish.js"></script>
    <!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
   <!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
    <script src="<%=contextPath%>/js/jquery.flexslider.js"></script> 
    <script src="<%=contextPath%>/js/retina.js"></script>

    <script src="<%=contextPath%>/js/custom.js"></script>
	<script>
    	$(document).ready(function(){
    		//$('#login-failed').hide();
    		<%if(registerActive){%>
    			$('#reg-username').focus();
    		<%}else{%>
    			$('#login-username').focus();
    		<%}%>
    	});
    	
    	
    	/*登录部分JS*/
    	var loginUsernameAvailable = false;
    	var loginPasswordAvailable = false;
    	var loginVerifyCodeAvailable = false;
    	
    	$('#login-username').blur(function(){
    		checkLoginUsername();
    	});
    	$('#login-password').blur(function(){
    		checkLoginPassword();
    	});
    	$('#login-verifyCode').blur(function(){
    		checkLoginVerifyCode();
    	});
    	
    	//检查邮箱是否合法
    	function checkLoginUsername(){
    		var usernameVal = $('#login-username').val();
    		//邮箱地址
    		var usernameRegex =  /^([a-z0-9A-Z]+[-|_|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
    		if(usernameVal==''){
    			$('#login-username-prompt').text('邮箱不能为空').show(); 
        		return false;
    		}else if(!usernameRegex.test(usernameVal)){//检查正则匹配
    			$('#login-username-prompt').text('邮箱不符合规范').show();
        		return false;
    		}else{//输入正确
				//设置username available的标识
				$('#login-username-prompt').text('').hide();
				loginUsernameAvailable = true;
    		}
    	}

    	//检查密码是否合法
    	function checkLoginPassword(){
    		var passwordVal = $('#login-password').val();
    		if(passwordVal==''){
    			$('#login-password-prompt').text('密码不能为空').show();
    		}else{
    			$('#login-password-prompt').text('').hide();
    			loginPasswordAvailable = true;
    		}
    	}
    	
    	//检查验证码是否合法
    	function checkLoginVerifyCode(){
    		var verifyCodeVal = $('#login-verifyCode').val();
    		if(verifyCodeVal==''){
    			$('#login-verifyCode-prompt').text('验证码不能为空').show();
    		}else{
    			var jsonData = {'verifyCode':verifyCodeVal};
    			$.post('<%=contextPath%>/checkVerifyCode.json', jsonData, function(responseData) {
       				var result = responseData.result;
       				if(result==1){
       					//设置verifyCode的标识
       					loginVerifyCodeAvailable = true;
       					$('#login-verifyCode-prompt').text('').hide();
       				}else{
       	    			//设置verifyCode unavailable的标识
       	    			loginVerifyCodeAvailable = false;
       	    			$('#login-verifyCode-prompt').text(responseData.message).show();
       				}
       			});
    		}
    	}
    	
    	$('#login-widget-form').submit(function(){
    		if(loginUsernameAvailable && loginPasswordAvailable&&loginVerifyCodeAvailable){ 
    			//所有数据项均可用
	    		$('#login-widget-form').submit();
    		}else{
    			return false;
    		}
    	});
    	
    	
    	/*注册部分JS*/
    	var regUsernameAvailable = false;
    	var regNicknameAvailable = false;
    	var regPasswordAvailable = false;
    	var regVerifyCodeAvailable = false;
    	
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
    	$('#reg-verifyCode').blur(function(){
    		checkRegVerifyCode();
    	});
    	
    	//$('#reg-button').click(function(){
    	$('#reg-widget-form').submit(function(){
    		if(regUsernameAvailable && regNicknameAvailable && regPasswordAvailable&&regVerifyCodeAvailable){
    			//所有数据项均可用
	    		$('#reg-widget-form').submit();
    		}else{
    			
    			alert(regUsernameAvailable);
    			alert(regNicknameAvailable);
    			alert(regPasswordAvailable);
    			alert(regVerifyCodeAvailable);
    			return false;
    		}
    	});
    	
    	//检查邮箱是否合法
    	function checkRegUsername(){
    		var usernameVal = $('#reg-username').val();
    		//邮箱地址
    		//var usernameRegex =  /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{4,20}$/;
    		var usernameRegex =  /^([a-z0-9A-Z]+[-|_|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
    		if(usernameVal==''){
    			$('#reg-username-prompt').text('邮箱不能为空').show();
        		return false;
    		}else if(!usernameRegex.test(usernameVal)){//检查正则匹配
    			$('#reg-username-prompt').text('邮箱不符合规范').show();
        		return false;
    		}else{//ajax检查是否可用
    			var jsonData = {'username':usernameVal};
    			$.post('<%=contextPath%>/usernameExists.json', jsonData, function(data) {
       				var result = data.result;
       				if(result==1){
       					//设置username available的标识
       					regUsernameAvailable = true;
       					$('#reg-username-prompt').hide();
       				}else{
       					//设置username unabailable的标识
       					regUsernameAvailable = false;
       					$('#reg-username-prompt').text(data.message).show();
       				}
       			});
    		}
    	}
    	
    	//检查昵称是否合法
    	function checkRegNickname(){
    		var nicknameVal = $('#reg-nickname').val();
    		//中文、英文字符 2-20位
    		var nicknameRegex =  /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{2,20}$/;
    		if(nicknameVal==''){
    			$('#reg-nickname-prompt').text('昵称不能为空').show();
    		}else if(!nicknameRegex.test(nicknameVal)){//监察正则匹配
    			$('#reg-nickname-prompt').text('昵称不符合规范').show();
    		}else{//ajax检查昵称是否可用
    			var jsonData = {'nickname':nicknameVal};
    			$.post('<%=contextPath%>/nicknameExists.json', jsonData, function(data) {
       				var result = data.result;
       				if(result==1){
       					//设置nickname available的标识
       					regNicknameAvailable = true;
       					$('#reg-nickname-prompt').text('').hide();
       				}else{
       	    			//设置nickname unavailable的标识
       	    			regNicknameAvailable = false;
       	    			$('#reg-nickname-prompt').text(data.message).show();
       				}
       			});
    		}
    	}

    	//检查密码是否合法
    	function checkRegPassword(){
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
	    			regPasswordAvailable = true;
	        		$('#reg-rePassword-prompt').text('').hide();
	    		}
    		}
    	}
    	
    	//检查验证码是否合法
    	function checkRegVerifyCode(){
    		var verifyCodeVal = $('#reg-verifyCode').val();
    		if(verifyCodeVal==''){
    			$('#reg-verifyCode-prompt').text('验证码不能为空').show();
    		}else{
    			/* regVerifyCodeAvailable = true; */
    			var jsonData = {'verifyCode':verifyCodeVal};
    			$.post('<%=contextPath%>/checkVerifyCode.json', jsonData, function(responseData) {
       				var result = responseData.result;
       				if(result==1){
       					//设置verifyCode的标识
       					regVerifyCodeAvailable = true;
       					$('#reg-verifyCode-prompt').text('').hide();
       				}else{
       	    			//设置verifyCode unavailable的标识
       	    			regVerifyCodeAvailable = false;
       	    			$('#reg-verifyCode-prompt').text(responseData.message).show();
       				}
       			});
    		}
    	}
    	
    	$('#login-verifyCode-img').click(function(){
    		var newUrl = "<%=contextPath%>/verifyCode?" + Math.floor(Math.random()*100);
    		$('#login-verifyCode-img').attr("src", newUrl).fadeIn();
    		$('#reg-verifyCode-img').attr("src", newUrl).fadeIn();
        })
    	
    	$('#reg-verifyCode-img').click(function(){
    		var newUrl = "<%=contextPath%>/verifyCode?" + Math.floor(Math.random()*100);
    		$('#reg-verifyCode-img').attr("src", newUrl).fadeIn();
    		$('#login-verifyCode-img').attr("src", newUrl).fadeIn();
        })
    </script>
    </body>
</html>
