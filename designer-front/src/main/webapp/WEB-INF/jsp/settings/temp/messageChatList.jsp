<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="com.bruce.designer.util.*" %> 
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_HH_MM_FORMAT);
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);

User toUser = (User)request.getAttribute(ConstFront.MESSAGE_TARGET_USER_ATTRIBUTE);
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <title>私信列表 - 【金玩儿网】</title>

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
                        <li><a href="javascript:void(0)">私信消息</a></li>
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
                                	<jsp:include page="./settingsTabInc.jsp?settingsMenuFlag=inbox"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="inbox">
			                            <div class="content-title">
											<h4>私信消息</h4> 
										</div>
										<input type="hidden" id="toId" name="toId" value="<%=toUser.getId()%>">
                                        <form action="#" method="post" id="commentform" class="form clearfix">
                                           	<div class="span9">
												<p>
													<textarea class="comment_textarea" name="content"
														id="content" cols="50" rows="2" tabindex="4"></textarea>
												</p>
											</div>
											<div class="span3">
												<input class="button button-small button-orange"
													type="button" name="sendMessageBtn" id="sendMessageBtn"
													tabindex="5" value="发 送"/>
											</div>
										</form>
										
                                    	<%
                                    	List<Message> messageList = (List<Message>)request.getAttribute("messageList");
                                    	if(messageList!=null&&messageList.size()>0){
                                    		int i=0;
                                    		for(Message message: messageList){
                                    			i++;
                                    			boolean selfMessage = message.getFromId().equals(currentUser.getId());
                                    	%> 
                                    	
                                    	<div id="messages">
											<ol id="messageListContainer" class="messagelist">
												<li class="message" id="li-message-<%=i%>"><div
														class="message-container" id="message-1">
														<div class="message-avatar<%=selfMessage?"-right":""%>">
															<div class="message-author vcard">
																<img
																	src="/designer-front/staticFile/avatar/100/<%=message.getFromId()%>.jpg">
															</div>
														</div>
														<div class="message-body<%=selfMessage?"-right":""%>">
															<div class="message-meta messagemetadata">
																<h5 class="message-author">
																	<%=selfMessage?"我":toUser.getNickname()%>
																</h5>
															</div>
															<div class="message-content">
																<%=message.getMessage()%> 发送于: <%=ymdSdf.format(message.getCreateTime())%>
															</div>
														</div>
													</div></li>
											</ol>
										</div>
			                            <%}
			                            }%>
			                            
			                            
			                            
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
	<script>
	$("#sendMessageBtn").click(function(){
		//disable submitBtn
		$("#sendMessageBtn").attr("disabled", "disabled");
		var messageJsonData = {"content": $("#content").val(), 'toId':$("#toId").val()};
		$.post("/designer-front/settings/sendMsg.json", messageJsonData, function(data) {
			$("#sendMessageBtn").removeAttr("disabled");
			$("#commentListContainer").prepend(data.data);
		 }, "json"); 
	});
		
	</script>
    </body>
</html>