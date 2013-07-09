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
                <div id="header-container" class="clearfix">  <!-- Header Container, contains logo and contact button -->
                    <header class="clearfix">
                        <div class="container">
                            <div class="row-fluid">
                                <div class="span3 logo">
                                    <a href="post-gallery.html#">
                                        <img src="./img/verendus-logo.png" alt="Verendus Logo" title="Verendus Logo" />
                                    </a>
                                </div>

                                <div class="header-contact button">
                                    <a href="post-gallery.html#">
                                        <ul class="clearfix">
                                            <li class="phone-number">
                                                <i class="icon-phone"></i>0(123) - 45678910
                                            </li>
                                            <li class="mailto-email">
                                                <i class="icon-envelope"></i>info@somnia-themes.com
                                            </li>
                                        </ul>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </header>       
                </div>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="./inc/headerNav.jsp"></jsp:include>

                    <div class="page-title">
                        <div class="container">
                            <!-- <div class="page-title-avatar">
                                <img src="./img/demo/portraits/portrait-21.png" alt="Page Title" width="80" height="80"/>
                            </div> -->
                            <div class="page-title-content">
                                <h1>Gallery Post Format</h1>
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
                            
                            <%
							List<Album> albumList = (List<Album>)request.getAttribute("albumList");
							if(albumList!=null){
		                        for(Album album: albumList){
		                    %>
                            <article class="post format-blogpost clearfix">
	                            <div class="span2 clearfix">
	                                <div class="page-title-avatar">
	                                    <img src="./img/demo/portraits/portrait-21.png" alt="Page Title" width="80" height="80"/>
	                                </div>
	                            </div> 
                            	
                                <div class="span10 post-thumb">
                                    <div class="flexslider">
                                        <ul class="slides">
                                        	<li>
                                                <img src="<%=album.getCoverImg()%>" alt="Carousel Item 3" />
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
	                                        <div class="meta-categories">
	                                            <ul>
	                                                <li><a href="single.html#">Photoshop</a>,</li>
	                                                <li><a href="single.html#">Web-Development,</a></li>
	                                                <li><a href="single.html#">Social Media</a></li>
	                                            </ul>
	                                        </div>
	                                        <div class="meta-tags">
	                                            <ul>
	                                                <li><a href="single.html#">Photoshop</a>,</li>
	                                                <li><a href="single.html#">Webdesign</a>,</li>
	                                                <li><a href="single.html#">Verendus</a></li>
	                                            </ul>
	                                        </div>
	                                    </div>
	                                 </div>
                                 </div>
                            </article>
                            <%}
		                    }%>
                          
                        </section>
                        <aside class="sidebar widgets-light span3">
							
							<!-- Contactform Widget -->
                            <!-- <div class="widget-box widget-contact-form">
                                <div class="content-title">
                                    <h4 class="widget-title">用户中心</h4>
                                </div>
                                <form id="contact-form-widget" method="post" class="clearfix">
                                    <div class="input-container" >
                                        <input type="text" class="contact-form-name" name="name" 
                                            value="Your Name" onfocus="if(this.value=='Your Name')this.value='';" 
                                            onblur="if(this.value=='')this.value='Your Name';"/>
                                            <i class="icon-user"></i>
                                    </div>
                                    <div class="input-container">
                                        <input type="text" class="contact-form-email" name="email" 
                                            value="Your Email" onfocus="if(this.value=='Your Email')this.value='';" 
                                            onblur="if(this.value=='')this.value='Your Email';" />
                                            <i class="icon-envelope-alt"></i>
                                    </div>
                                    <input class="contact-submit button" type="submit" value="登 录">
                                </form>

                                <div class="contact-form-widget-respons">
                                    <p>Your message was succesfully send.</p>
                                </div> 
                            </div> -->
                            
							<!-- Blogpost Widget -->
                            <div class="widget-box widget-blogposts">
                                <div class="content-title">
                                    <h4 class="widget-title">Latest Blogposts / Portfolio</h4>
                                </div>
                                <ul>
                                    <li class="clearfix">
                                        <div class="widget-blogpost-avatar">
                                            <a href="post-gallery.html#">
                                                <img src="./img/demo/ecopower-70x70.jpg" alt="Blogpost-1">
                                                <span class="widget-blogpost-overlay"></span>
                                            </a>
                                        </div>
                                        <div class="widget-blogpost-content">
                                            <div class="widget-blogpost-title ">
                                                <h5>Just a simple blogtitle with two lines of text.</h5>
                                            </div>
                                            <div class="widget-blogpost-date">
                                                <p><a href="post-gallery.html#">December 8, 2012</a></p>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="clearfix">
                                        <div class="widget-blogpost-avatar">
                                            <a href="post-gallery.html#">
                                                <img src="./img/demo/architecture-70x70.jpg" alt="Blogpost-1">
                                                <span class="widget-blogpost-overlay"></span>
                                            </a>
                                        </div>
                                        <div class="widget-blogpost-content">
                                            <div class="widget-blogpost-title ">
                                                <h5>Just a simple blogtitle with two lines of text.</h5>
                                            </div>
                                            <div class="widget-blogpost-date">
                                                <p><a href="post-gallery.html#">December 8, 2012</a></p>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="clearfix">
                                        <div class="widget-blogpost-avatar">
                                            <a href="post-gallery.html#">
                                                <img src="./img/demo/ecohands-70x70.jpg" alt="Blogpost-1">
                                                <span class="widget-blogpost-overlay"></span>
                                            </a>
                                        </div>
                                        <div class="widget-blogpost-content">
                                            <div class="widget-blogpost-title ">
                                                <h5>Just a simple blogtitle with two lines of text.</h5>
                                            </div>
                                            <div class="widget-blogpost-date">
                                                <p><a href="post-gallery.html#">December 8, 2012</a></p>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
							<!-- Flickr Widget -->
                            <div class="widget-box widget-flickr">
                                <div class="content-title">
                                    <h4 class="widget-title">My Flickr Stream</h4>
                                </div>
                                <div class="flickr-wrap clearfix">
                                    <script type="text/javascript" src="http://www.flickr.com/badge_code_v2.gne?count=9&amp;display=random&amp;size=s&amp;layout=x&amp;source=user&amp;user=52617155@N08">
                                    </script>
                                </div>
                            </div>
                        </aside>
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
