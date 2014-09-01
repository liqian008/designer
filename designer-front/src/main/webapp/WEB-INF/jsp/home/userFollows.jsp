<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.data.*" %>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="com.bruce.designer.front.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
String contextPath = ConstFront.CONTEXT_PATH;
%>
<%
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
User queryUser = (User)request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);

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
        <title><%=queryUser.getNickname()%>的关注 - 【金玩儿网】</title>

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
    <body class="body-background" style="background-image: url(../img/backgrounds/bg.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        
        <jsp:include page="../inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                <jsp:include page="../inc/headerBanner.jsp"></jsp:include>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <%
					boolean isMe =  currentUser!=null&&queryUser!=null&&currentUser.getId().equals(queryUser.getId());
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
                        <li><a href="<%=contextPath%>//home"><%=queryUser.getNickname()%></a>/</li>
                        <li><a href="javascript:void(0)">关注列表</a></li>
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
                                	<li><a class="button button-white" href="./info"><%=who%>的资料</a></li>
                                	<li class="active"><a class="button button-white" href="./follows"><%=who%>的关注</a></li>
                                	<%if(isDesigner){%>
                                	<li><a class="button button-white" href="./fans"><%=who%>的粉丝</a></li>
                                	<%}%>
                                </ul>
                                <div class="tab-content span9">
                                    
                                    <div class="tab-pane widgets-light active" id="inbox">
			                            <div class="content-title">
											<h4><%=who%>的关注</h4> 
										</div>
                                    	 
                                    	<%
	                                    Map<Integer, Boolean> followMap = (Map<Integer, Boolean>)request.getAttribute("followMap");
	                                    
	                                	List<UserFollow> followList = (List<UserFollow>)request.getAttribute("followList");
	                                	if(followList!=null&&followList.size()>0){
	                                	for(UserFollow follow: followList){
	                                	%>
			                            <div id="messages">
											<ol id="messageListContainer" class="messagelist">
												<li class="message" id="li-message-1"><div class="message-container" id="message-1">
													<div class="message-avatar-medium">
														<div class="message-author vcard">
															<!-- <img src="<%=contextPath%>/img/icon/icon_1.png"> -->
															<a href="<%=contextPath%>/<%=follow.getFollowId()%>/home">
																<img
																	src="<%=contextPath%>/staticFile/avatar/<%=follow.getFollowId()%>_medium.jpg"/>
															</a>
														</div>
													</div>
													<div class="message-body">
														<div class="message-meta messagemetadata">
																<a href="<%=contextPath%>/<%=follow.getFollowId()%>/home"><h5 class="message-author"><%=follow.getFollowUser().getNickname()%></h5></a>
														</div>
														<div class="message-content">
															<%
			                                            	boolean hasFollowed = followMap.get(follow.getFollowId())!=null&&followMap.get(follow.getFollowId());
			            									String hideStr = "style='display:none'";
			            									if(currentUser==null||!currentUser.getId().equals(follow.getFollowId())){
			            										//对于本人，不显示关注及取消关注按钮
			            									%>
			                                            	<a href="javascript:void(0)" dataItem="<%=follow.getFollowId()%>" class="listFollowBtn button button-small button-green" <%=hasFollowed?hideStr:""%>>关 注</a>
			                                            	<a href="javascript:void(0)" dataItem="<%=follow.getFollowId()%>" class="listUnfollowBtn button button-small button-white" <%=hasFollowed?"":hideStr%>>取消关注</a>
			                                            	<%}%>
			                                            	<a href="<%=contextPath%>/<%=follow.getFollowId()%>/home" class="button button-small button-white">作品辑</a>
															<a href="<%=contextPath%>/<%=follow.getFollowId()%>/info" class="button button-small button-white">个人资料</a>
														</div>
													</div>
												</div></li>
											</ol>
										</div>
										<%}
	                                	}%>
	                                	
	                                	<%
	                                	PagingData<UserFollow> followsPagingData = (PagingData<UserFollow>)request.getAttribute("followsPagingData");
	                                	%>
	                                    <%=PagingUtil.getPagingHtml(followsPagingData, contextPath + "/"+queryUser.getId()+"/follows")%>
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
    <script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
    <script src="<%=contextPath%>/js/superfish.js"></script>
    <!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
   <!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
    <script src="<%=contextPath%>/js/jquery.flexslider.js"></script> 
    <!--<script src="<%=contextPath%>/js/retina.js"></script>-->

    <script src="<%=contextPath%>/js/custom.js"></script>
	
	<script>
	
	$("body").delegate('a.listFollowBtn', 'click', function(){
		var listFollowBtn = $(this);
		var followId = listFollowBtn.attr('dataItem');
		listFollowBtn.attr("disabled", "disabled");
		var followJsonData = {"uid": followId};
		$.post("<%=contextPath%>/follow.json", followJsonData, function(data) {
			listFollowBtn.removeAttr("disabled");
			if(data.result==1){
				listFollowBtn.next().show();
				listFollowBtn.hide();
			}else{
				alert(data.message);
			}
		 }, "json");
	});
	
	$("body").delegate('a.listUnfollowBtn', 'click', function(){
		var listUnfollowBtn = $(this);
		var unfollowId = listUnfollowBtn.attr('dataItem');
		listUnfollowBtn.attr("disabled", "disabled");
		var unfollowJsonData = {"uid": unfollowId};
		$.post("<%=contextPath%>/unfollow.json", unfollowJsonData, function(data) {
			listUnfollowBtn.removeAttr("disabled");
			if(data.result==1){
				listUnfollowBtn.prev().show();
				listUnfollowBtn.hide();
			}else{
				alert(data.message);
			}
		 }, "json");
	});
	</script>
    </body>
</html>
