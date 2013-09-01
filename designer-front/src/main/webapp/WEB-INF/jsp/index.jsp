<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
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

                    <div class="page-title">
                        <div class="container">
                            <div class="page-title-avatar">
                                <img src="./img/demo/portraits/avatar_middle.jpg" alt="Page Title" width="80" height="80"/>
                            </div>
                            <div class="page-title-content">
                                <h3>大树珠宝</h3>
                                <p class="page-description">
                                    With this gallery you can create a blogpost with multiple images. With the FlexSlider or Twitter Bootstrap Carousel you can rotate between these images.
                                </p>
                            </div>
                        </div>
                    </div>
                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <jsp:include page="./inc/breadcrumb.jsp"></jsp:include>
            
            <div class="main fullwidth">            
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
                            
                            <%
							List<Album> albumList = (List<Album>)request.getAttribute("albumList");
							if(albumList!=null){
		                        for(Album album: albumList){
		                    %>
                            <article class="post format-blogpost clearfix">
	                            <div class="span2 clearfix content-side">
	                            	 <div class="content-side">
		                                <div class="content-side-avatar">
		                                    <img src="./img/demo/portraits/1_avatar_middle.jpg" alt="Page Title" width="100" height="100"/>
		                                </div>
		                                
		                                <div>
	                                        <table>
	                                            <tbody>
	                                            	<tr>
	                                                	<th colspan="2">
	                                                        大树珠宝
	                                                    </th>
	                                                </tr>
	                                                <tr>
	                                                    <th>
	                                                        <a href="" class="xi2">31</a><br/>主题
	                                                    </th>
	                                                    <th>
	                                                        <a href="" class="xi2">31</a><br/>粉丝
	                                                    </th>
	                                                </tr>
	                                                
	                                            </tbody>
	                                        </table>
	                                    </div>
		                             </div>
	                            </div> 
                            	
                                <div class="span10 post-thumb">
                                    <div class="flexslider">
                                        <ul class="slides">
                                        	<li>
                                                <a href="./album.art?id=<%=album.getId()%>"><img src="<%=album.getCoverImg()%>" alt="Carousel Item 3" /></a>
                                                    <div class="flex-caption">
                                                    <h3><%=album.getTitle()%></h3>
                                                    <p>
                                                    	<%=album.getRemark()%>
                                                    </p> 
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                
	                                <div class="row-fluid clearfix">
	                                    <div class="meta-container-single clearfix">
                                        
	                                        <div class="meta-tags">
	                                            <ul>
	                                                <li><a href="single.html#">翡翠</a>,</li>
	                                                <li><a href="single.html#">玉石</a></li>
	                                            </ul>
	                                        </div>
	                                        
	                                        <div class="meta-categories">
	                                        	<ul>
	                                                <li><a href="single.html#">举报</a> </li>
	                                            </ul>
	                                            <ul>
	                                                <li><a href="single.html#">喜欢(1220)</a> </li>
	                                            </ul>
	                                             <ul>
	                                                <li><a href="single.html#">收藏(120)</a> </li>
	                                            </ul>
	                                            
	                                            <ul>
	                                                <li><a href="single.html#">评论(10)</a> </li>
	                                            </ul>
	                                        </div>
	                                    </div>
	                                    
	                                    <div id="comments">
                                        <ol class="commentlist">
                                            <li class="comment depth-1" id="li-comment-1">
                                                <div class="comment-container" id="comment-1">
                                                    <div class="comment-avatar">
                                                        <div class="comment-author vcard">
                                                            <img src="./img/demo/portraits/avatar_middle.jpg" alt="Blogpost Comment" />                 
                                                        </div>
                                                    </div>                          
                                                    <div class="comment-body">
                                                        <div class="comment-meta commentmetadata">
                                                            <h6 class="comment-author">
                                                                <a href='http://www.somnia-themes.com' rel='external nofollow' class='url'>猫王</a> 发表于 2013-01-01 23:23
                                                            </h6>                                   
                                                        </div>                              
                                                        <div class="comment-content">
                                                                很带感啊！
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="comment depth-1" id="li-comment-1">
                                                <div class="comment-container" id="comment-1">
                                                    <div class="comment-avatar">
                                                        <div class="comment-author vcard">
                                                            <img src="./img/demo/portraits/avatar_middle.jpg" alt="Blogpost Comment" />                 
                                                        </div>
                                                    </div>                          
                                                    <div class="comment-body">
                                                        <div class="comment-meta commentmetadata">
                                                            <h6 class="comment-author">
                                                                <a href='http://www.somnia-themes.com' rel='external nofollow' class='url'>猫王</a> 发表于 2013-01-01 23:23
                                                            </h6>                                   
                                                        </div>                              
                                                        <div class="comment-content">
                                                                很带感啊！
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ol>
                                    </div><!-- Close Comments -->
                                    
	                                 </div>
                                 </div>
                            </article>
                            <%}
		                    }%>
                          
                        </section>
                        
                        <jsp:include page="./inc/rightSidebar.jsp"></jsp:include>
                    	
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

    </body>
</html>
