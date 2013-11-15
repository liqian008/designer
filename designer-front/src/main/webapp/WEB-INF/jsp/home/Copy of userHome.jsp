<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
User requestUser = (User)request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);
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

                    <div class="page-title">
                        <div class="container">
                            <%if(requestUser!=null&&requestUser.getId()!=null){%>
                            <div class="page-title-avatar">
                                <img src="<%=requestUser.getHeadImg()%>" alt="Page Title" width="80" height="80"/>
                            </div>
                            <div class="page-title-content">
                            	<%
                                boolean isDesigner = requestUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;
                            	%>
                                <h3><%=requestUser.getNickname()%><%=isDesigner?"【设计师】":""%></h3>
                                <p class="page-description">
                                    With this gallery you can create a blogpost with multiple images.
                                </p>
                               <%
                               if(isDesigner){
                                   Boolean hasFollowed = (Boolean)request.getAttribute("hasFollowed");
                               	if(hasFollowed!=null&&hasFollowed){%>
                                <a href="/designer-front/unfollow?uid=<%=requestUser.getId()%>" class="button button-small button-white">取消关注</a>
                                <%}else{%>
                                <a href="/designer-front/follow?uid=<%=requestUser.getId()%>" class="button button-small button-green">关注</a>
                                <%}%>
                                <a href="javascript:void(0)" class="button button-small">作品辑</a>
                                <%}%>
                                <a href="/designer-front/3/info" class="button button-small button-white">个人资料</a>
                                <%if(currentUser!=null&&requestUser.getId()==currentUser.getId()){ %>
                                	<a href="/designer-front/settings" class="button button-small button-white">我的设置</a>
                                <%}%>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="post-gallery.html#">首页</a>/</li>
                        <li><a href="post-gallery.html#">他的主页</a>/</li>
                        <li><a href="post-gallery.html#">个人资料</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">            
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
                            
                            <%
						List<Album> albumList = (List<Album>)request.getAttribute("albumList");
						if(albumList!=null){
							int count =0;
	                        for(Album album: albumList){
	                        	count++;
	                        	boolean newline = count%3==1;
	                        	boolean endline = count%3==0;
	                    %>
	                    <%if(newline){%>
                    	<div class="shortcode-blogpost row-fluid">
                        <%}%>
                            <article class="blog-item span4">
                                <div class="blog-post-image-wrap">
                                    <a class="blog-single-link" href="/designer-front/album/<%=album.getId()%>">
                                        <img src="<%=album.getCoverLargeImg()%>">
                                    </a>
                                </div>
                                <div class="content-wrap span9">
                                   	<a href="#"><h5><%=album.getTitle()%></h5></a>
                                    <ul>
                                        <li><span>标 签:&nbsp;</span>翡翠, 珠宝, 玉石</li>
                                        <li><span>价 格:</span>1w~2w</li>
                                        <li>
                                        	<%=album.getBrowseCount()%>&nbsp;浏览&nbsp;/&nbsp;
                                        	<%=album.getCommentCount()%>&nbsp;评论&nbsp;/&nbsp;
                                        	<%=album.getFavoriteCount()%>&nbsp;收藏&nbsp;
                                        </li> 
                                    </ul>
                                </div>
                                <div class="content-avatar">
                                     <a href="/designer-front/<%=album.getUserId()%>/home" title="xxx">
                                    	<img src="/designer-front/img/demo/portraits/avatar_middle.jpg" alt="Blogpost Comment">
									</a>
                                 </div>
                            </article>
                        <%if(endline){%>
                    	</div>
                        <%}
                        }
	                    }%>
	                    
	                    <input type="hidden" id="albumsTailId" name="albumsTailId" value="0" />
						<div class="shortcode-blogpost row-fluid" id="moreAlbumsContainer">
							<div class="span2 offset5">
								<input id="moreAlbumsBtn"
									class="button-small button button-white btn-block" type="button"
									value="加载更多..." />
							</div>
						</div>
						
                        </section>
                        
                        
                       	<jsp:include page="../inc/designerBox.jsp"></jsp:include>
                    	
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
