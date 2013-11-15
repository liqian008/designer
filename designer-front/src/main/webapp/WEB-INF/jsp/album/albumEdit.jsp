<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
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
                            <div class="shortcode-contact-form row-fluid">
                                <form action="./saveAlbum" method="post" id="contactform" class="form">
                                    <div class="span8 offset2">
                                        <h2>新增作品集</h2>
                                        <p class="input-wrap">
                                            <input class="comment_input comment_name" type="text" name="title" id="title" value="title" size="22" tabindex="1" 
                                            onfocus="if(this.value=='title')this.value='';" onblur="if(this.value=='')this.value='title';" />
                                            <i class="icon-user"></i>
                                        </p>
                                        <p class="input-wrap">
                                            <input class="comment_input comment_email" type="text" name="remark" id="remark" value="remark" size="22" tabindex="2" 
                                            onfocus="if(this.value=='remark')this.value='';" onblur="if(this.value=='')this.value='remark';"  />
                                            <i class="icon-envelope-alt"></i>
                                        </p>

                                        <p class="input-wrap">
                                            <input class="comment_input comment_email" type="text" name="coverImg" id="coverImg" value="coverImg" size="22" tabindex="2" 
                                            onfocus="if(this.value=='coverImg')this.value='';" onblur="if(this.value=='')this.value='coverImg';"  />
                                            <i class="icon-envelope-alt"></i>
                                        </p>
                                        
                                        <h4>插图</h4>
                                        <p class="input-wrap">
                                            <input class="comment_input comment_email" type="text" name="slideList[0].slideImg" id="slideList[0].slideImg" value="http://localhost:8080/designer-front/img/demo/hands-plant-870x450.jpg" size="22" tabindex="2" 
                                            onfocus="if(this.value=='slideList[0].slideImg')this.value='';" onblur="if(this.value=='')this.value='coverImg';"  />
                                            <i class="icon-envelope-alt"></i>
                                        </p>
                                        <p class="input-wrap">
                                            <input class="comment_input comment_email" type="text" name="slideList[1].slideImg" id="slideList[0].slideImg" value="http://localhost:8080/designer-front/img/sample/gal1-5.jpg" size="22" tabindex="2" 
                                            onfocus="if(this.value=='slideList[1].slideImg')this.value='';" onblur="if(this.value=='')this.value='coverImg';"  />
                                            <i class="icon-envelope-alt"></i>
                                        </p>
                                        <p>
                                        <input class="button button-blue" name="submit" type="submit" id="submit" tabindex="5" value="注 册" />      
                                        <input class="button button-blue" name="reset" type="reset" id="reset" tabindex="6" value="重 置" />    
                                    </p>
                                    </div>
                                </form>

                                <div class="contact-form-respons">
                                    <div class="infobox info-succes info-succes-alt clearfix">
                                        <span></span>
                                        <div class="infobox-wrap">
                                            <h4>Your message was succesfully send!</h4>
                                            <p>We will contact you as soon as possible. Please reload the page if you want to send a message again.</p>                                            
                                        </div>
                                        <a href="contact.html#" class="info-hide"></a>
                                    </div>
                                </div>
                            </div>
                        </section> <!-- End Content -->
                        
                        <aside class="sidebar widgets-light span3">
							
							
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
