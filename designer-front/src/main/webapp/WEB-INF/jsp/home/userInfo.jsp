<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
String contextPath = ConstFront.CONTEXT_PATH;
%>
<%
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
User queryUser = (User)request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);

boolean isMe =  currentUser!=null&&queryUser!=null&&currentUser.getId().equals(queryUser.getId());
boolean isDesigner = queryUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;
String who = "Ta";
if(currentUser!=null&&currentUser.getId().equals(queryUser.getId())){
	 who = "我";
}
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <title><%=queryUser.getNickname()%>的资料 - 【金玩儿网】</title>

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
                    <%
                	if(isMe){%>
	                    <jsp:include page="../inc/headerNav.jsp?menuFlag=myHome"></jsp:include>
                	<%}else{%>
                		<jsp:include page="../inc/headerNav.jsp?menuFlag="></jsp:include>
                	<%} %>
					<jsp:include page="../inc/ad.jsp"></jsp:include>
					
                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="<%=contextPath%>/">首页</a>/</li>
                        <li><a href="<%=contextPath%>/<%=queryUser.getId()%>/home"><%=queryUser.getNickname()%></a>/</li>
                        <li><a href="javascript:void(0)">个人资料</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">            
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
							
                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                	<li><a class="button button-white" href="./home"><%=who%>的作品辑</a></li>
                                	<%-- <li><a class="button button-white" href="./home"><%=who%>的主页</a></li> --%>
                                	<li class="active"><a class="button button-white" href="javascript:void(0)"><%=who%>的资料</a></li>
                                	<li><a class="button button-white" href="./follows"><%=who%>的关注</a></li>
                                	<%if(isDesigner){%>
                                	<li><a class="button button-white" href="./fans"><%=who%>的粉丝</a></li> 
                                	<%}%>
                                </ul>
                                <div class="tab-content span9">
                                <div class="widget-box widget-wrapper-form">
		                            <div class="tab-pane widgets-light active" id="info">
										
										<div class="content-title">
											<h4>个人资料</h4>
										</div>
											
										<div class="row-container clearfix">
											<div class="row-left">昵称: </div>
											<div class="row-right"><%=queryUser.getNickname()%></div>
										</div>
										
										
										<div class="row-container clearfix">
											<div class="row-left">关注数:</div>
											<div class="row-right">
												<a href='<%=contextPath%>/<%=queryUser.getId()%>/follows'><span class="followsCount">0</span>人</a>
											</div>
										</div>
			                            
										<%
										if(isDesigner){%>
										<div class="infobox info-info info-info-alt clearfix">
			                                <span>i</span>
			                                <div class="infobox-wrap">
			                                    <h4>用户身份：设计师</h4>
			                                    <p>详细信息如下</p>
			                                </div>
			                            </div>
			                             
			                            <%
			                            Integer albumCount = (Integer)request.getAttribute("userAlbumsCount");
			                            if(albumCount!=null){
			                            	if(albumCount<0) albumCount = 0;
			                            %>
			                            <div class="row-container clearfix">
											<div class="row-left">专辑数: </div>
											<div class="row-right">
												<a href='<%=contextPath%>/<%=queryUser.getId()%>/home'><span class="albumsCount">x</span>个</a>
											</div>
										</div>
										<%}%>
										
										
										<div class="row-container clearfix">
											<div class="row-left">粉丝数: </div>
											<div class="row-right">
												<a href='<%=contextPath%>/<%=queryUser.getId()%>/fans'><span class="fansCount">0</span>人</a>
												<%
												if(!isMe){
													/* Boolean hasFollowed = (Boolean) request.getAttribute("hasFollowed"); */
	            									String hideStr = "style='display:none'";
	            								%>
													<input type="button" class="followBtn button button-small button-green" <%=hideStr%> value="关注">
	                                            	<input type="button" class="unfollowBtn button button-small button-white" <%=hideStr%> value="取消关注">
												<%}%>
											</div>
										</div>
										
										<div class="row-container clearfix">
											<div class="row-left">微信号: </div>
											<div class="row-right">
												<%=queryUser.getWeixinNumber()%>&nbsp;&nbsp;
											</div>
										</div>
										
										<%if(queryUser.getAccessTokenMap().containsKey(IOAuthService.OAUTH_WEIBO_TYPE)){%>
											<div class="row-container clearfix">
												<div class="row-left">微博账户: </div>
												<div class="row-right">
													<%=queryUser.getAccessTokenMap().get(IOAuthService.OAUTH_WEIBO_TYPE).getThirdpartyUname()%>&nbsp;&nbsp;
													<input type="button" class="button button-small button-blue" value="查看微博" onclick="window.open('http://weibo.com/u/<%=queryUser.getAccessTokenMap().get(IOAuthService.OAUTH_WEIBO_TYPE).getThirdpartyUid()%>','_blank')">
												</div>
											</div>
											<%}%>
										
										
										<div class="row-container clearfix">
											<div class="row-left">淘宝店铺主页: </div>
											<div class="row-right">
												<%=queryUser.getDesignerTaobaoHomepage()%>&nbsp;&nbsp;
												<input type="button" class="button button-small button-blue" value="去逛逛" onclick="window.open('<%=queryUser.getDesignerTaobaoHomepage()%>','_blank')">
											</div>
										</div>
										
										<div class="row-container clearfix">
											<div class="row-left">公 司: </div>
											<div class="row-right">
												<%=queryUser.getDesignerCompany()%>
											</div>
										</div>
										
										<div class="row-container clearfix">
											<div class="row-left">设计师简介: </div>
											<div class="row-right">
												
											</div>
										</div>
										<%}%>
										
                                    </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                       	
                    	<!-- right slidebar -->
						<aside class="sidebar widgets-light span3">
                       		<jsp:include page="../inc/right/sidebar.jsp"></jsp:include> 
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
    <!-- <script src="<%=contextPath%>/js/retina.js?v=${version}"></script> -->

    <script src="<%=contextPath%>/js/custom.js?v=${version}"></script>
	
	<script>
	
	/* $("body").delegate('input.followBtn', 'click', function(){
		var followBtn = $(this);
		var followId = followBtn.attr('dataItem');
		followBtn.attr("disabled", "disabled");
		var followJsonData = {"uid": followId};
		$.post("<%=contextPath%>/follow.json", followJsonData, function(data) {
			followBtn.removeAttr("disabled");
			if(data.result==1){
				followBtn.next().show();
				followBtn.hide();
			}else{
				alert(data.message);
			}
		 }, "json");
	});
	
	$("body").delegate('input.unfollowBtn', 'click', function(){
		var unfollowBtn = $(this);
		var unfollowId = unfollowBtn.attr('dataItem');
		unfollowBtn.attr("disabled", "disabled");
		var unfollowJsonData = {"uid": unfollowId};
		$.post("<%=contextPath%>/unfollow.json", unfollowJsonData, function(data) {
			unfollowBtn.removeAttr("disabled");
			if(data.result==1){
				unfollowBtn.prev().show();
				unfollowBtn.hide();
			}else{
				alert(data.message);
			}
		 }, "json");
	}); */
	</script>
    </body>
<jsp:include page="../inc/weixinShare_site.jsp"></jsp:include>
</html>
