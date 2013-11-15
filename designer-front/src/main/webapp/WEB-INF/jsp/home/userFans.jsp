<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
User queryUser = (User)request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);

boolean isDesigner = queryUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;
String who = "Ta";
if(currentUser!=null&&currentUser.getId()==queryUser.getId()){
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
        <title>Verendus - Multipurpose Business Template</title>

        <meta name="description" content="Verendus - A HTML5 / CSS3 Multipurpose Business Template">
        <meta name="keywords" content="Bootstrap, Verendus, HTML5, CSS3, Business, Multipurpose, Template">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/font-awesome.css">
        <link rel="stylesheet" href="../css/animate.css">
        <link rel="stylesheet" href="../css/flexslider.css">
        <link rel="stylesheet" href="../css/style.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="../css/ie8.css" />    
        <![endif]-->
                

        <script src="../js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="../js/vendor/jquery-1.8.3.min.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(../img/backgrounds/bg3.jpg); ">

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
                        <li><a href="/designer-front/">首页</a>/</li>
                        <li><a href="./home"><%=queryUser.getNickname()%></a>/</li>
                        <%if(isDesigner){%>
                        <li><a href="javascript:void(0)">粉丝列表</a></li>
                        <%}%>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">            
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
	
                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                	<li><a class="button button-white" href="./info"><%=who%>的资料</a></li>
                                	<li><a class="button button-white" href="./follows"><%=who%>的关注</a></li>
                                	<li  class="active"><a class="button button-white" href="./fans"><%=who%>的粉丝</a></li>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane active clearfix">
                                    	 <div class="content-title">
											<h4><%=who%>的粉丝</h4>
										</div>
                                    	<div class="shortcode-blogpost-thumb shortcode-blogpost-medium span12">
		                                <ul>
		                                	<%
		                                	Map<Integer, Boolean> followMap = (Map<Integer, Boolean>)request.getAttribute("followMap");
		                                	List<UserFan> fanList = (List<UserFan>)request.getAttribute("fanList");
		                                	if(fanList!=null&&fanList.size()>0){
		                                	for(UserFan fan: fanList){
		                                	%>
		                                    <li class="clearfix">
		                                        <div class="blogpost-avatar">
		                                            <a href="/designer-front/<%=fan.getFanUser().getId()%>/home">
		                                                <img src="<%=fan.getFanUser().getHeadImg()%>" alt="Blogpost-1">
		                                            </a>
		                                        </div>
		                                        <div class="blogpost-content">
		                                            <div class="blogpost-title ">
		                                                 <a href="/designer-front/<%=fan.getFanUser().getId()%>/home">
		                                                 	<h5><%=fan.getFanUser().getNickname()%></h5>
		                                                 </a>
		                                            </div>
		                                            <div class="blogpost-date">
		                                            <%
	                                            	boolean hasFollowed = followMap.get(fan.getFanId())!=null&&followMap.get(fan.getFanId());
	            									if(hasFollowed){
	            									%>
	                                            	<a href="/designer-front/unfollow?uid=<%=fan.getFanId()%>" class="button button-small button-white">取消关注</a>
	                                            	<%}else{ %>
	                                            	<a href="/designer-front/follow?uid=<%=fan.getFanId()%>" class="button button-small button-green">关注</a>
	                                            	<%}%>
			                                        </div>
		                                        </div>
		                                    </li>
		                                    <%}
		                                	} %>
		                                </ul>
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
    <script src="../js/vendor/bootstrap.min.js"></script>
    <script src="../js/jquery.hoverdir.js"></script>
    <script src="../js/superfish.js"></script>
    <!-- <script src="../js/supersubs.js"></script> -->
   <!--  <script src="../js/jquery.tweet.js"></script>  -->
    <script src="../js/jquery.flexslider.js"></script> 
    <script src="../js/retina.js"></script>

    <script src="../js/custom.js"></script>

    </body>
</html>
