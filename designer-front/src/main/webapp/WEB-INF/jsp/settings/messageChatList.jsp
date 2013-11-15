<%@page import="com.bruce.designer.front.controller.FrontController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="com.bruce.designer.util.*" %> 
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);

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
                        <li><a href="post-gallery.html#">Home</a>/</li>
                        <li><a href="post-gallery.html#">Blog</a>/</li>
                        <li><a href="post-gallery.html#">Gallery Post Format</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
							<div class="content-title">
                                <h2>管理中心</h2>
                            </div>
	
                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                	<jsp:include page="./settingsTabInc.jsp"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="inbox">
			                            <div class="content-title">
											<h4>消息列表</h4> 
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
                                    		for(Message message: messageList){
                                    	%> 
                                        <div class="comment-container" id="comment-1">
                                            <div class="comment-avatar">
                                                <div class="comment-author vcard">
                                                    <img src="<%=toUser.getHeadImg()%>"> 
                                                </div>
                                            </div>                           
                                            <div class="comment-body">
                                                <div class="comment-meta commentmetadata">
                                                    <h4 class="comment-author">
                                                        <a href="http://www.somnia-themes.com" class="url">
															<%=MessageUtil.getMessageTypeName(message.getMessageType())%>
														</a>
                                                    </h4>                                   
                                                </div>                              
                                                <div class="comment-content">
                                                    <p>
                                                        <%=message.getMessage()%>
                                                    </p>
                                                </div>
                                                <div class="comment-date">
                                                    <p><%=message.getCreateTime()%></p>
                                                </div>
                                            </div>
                                        </div>
			                                
			                            <%}
			                            }%>
			                            
                                    </div>
                                </div>
                            </div>
                        </section> 
                        
                        <jsp:include page="../inc/rightSidebar.jsp"></jsp:include>
                    	
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="../inc/footer.jsp"></jsp:include>
           
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
	$("#sendMessageBtn").click(function(){
		//disable submitBtn
		$("#sendMessageBtn").attr("disabled", "disabled");
		var messageJsonData = {"content": $("#content").val(), 'toId':$("#toId").val()};
		$.post("/designer-front/settings/sendMsg.json", messageJsonData, function(data) {
			$("#sendMessageBtn").removeAttr("disabled");
			alert("result: " + data.result);
			alert("message: " + data.data);
			$("#commentListContainer").prepend(data.data);
			//enable submitBtn
		 }, "json"); 
	});
		
	</script>
    </body>
</html>
