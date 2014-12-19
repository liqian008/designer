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
        <title>Sina微博登录 | 注册 - 【金玩儿网】</title>

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
							    </ul>
							     
							    <div class="tab-content">
							        <div class="tab-pane widgets-light active" id="register">
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
												action="<%=contextPath%>/oauthRegister">
												
												<%if(redirectUrl!=null){%>
													<input type="hidden" name="<%=ConstFront.REDIRECT_URL%>" value="<%=redirectUrl%>"/>
												<%}%>
												
												<div class="row-container clearfix">
													<div class="row-left">邮 箱:  </div>
													<div class="row-right">
														<input type="text" class="span5" id="reg-username" name="username" placeholder="您的常用邮箱"/>
														<span id="reg-username-required" class="required">*</span>
														<span id="reg-username-prompt" class="text-prompt">多账户登录时会使用此邮箱，请牢记</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">昵 称: </div>
													<div class="row-right">
														<%
														String nickname = (String)request.getAttribute(ConstFront.THIRDPARTY_USERNAME);
														nickname = nickname==null?"":nickname;
														%>
														<input type="text" class="span5" id="reg-nickname" name="nickname" value="<%=nickname%>" placeholder="您在本站的昵称"/>
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
												
												<input id="oauth-reg" class="common-submit button" type="submit" value="提 交">
												<input id="oauth-reg-reset" class="common-submit button" type="reset" value="重 置">
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
    <script src="<%=contextPath%>/js/vendor/bootstrap.min.js?v=${version}"></script>
    <script src="<%=contextPath%>/js/jquery.hoverdir.js?v=${version}"></script>
    <script src="<%=contextPath%>/js/superfish.js?v=${version}"></script>
    <!-- <script src="<%=contextPath%>/js/supersubs.js?v=${version}"></script> -->
   <!--  <script src="<%=contextPath%>/js/jquery.tweet.js?v=${version}"></script>  -->
    <script src="<%=contextPath%>/js/jquery.flexslider.js?v=${version}"></script> 
    <!--<script src="<%=contextPath%>/js/retina.js?v=${version}"></script>-->

    <script src="<%=contextPath%>/js/custom.js?v=${version}"></script>
	<script>
    	$(document).ready(function(){
    		<%if(registerActive){%>
				$('#reg-username').focus();
			<%}%>
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
    			/* alert(regUsernameAvailable);
    			alert(regNicknameAvailable);
    			alert(regPasswordAvailable);
    			alert(regVerifyCodeAvailable); */
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
    	
    	$('#reg-verifyCode-img').click(function(){
    		var newUrl = "<%=contextPath%>/verifyCode?" + Math.floor(Math.random()*100);
    		$('#reg-verifyCode-img').attr("src", newUrl).fadeIn();
    		$('#login-verifyCode-img').attr("src", newUrl).fadeIn();
        })
    </script>
    </body>
    
<jsp:include page="../inc/weixinShare_site.jsp"></jsp:include>
</html>
