<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>PRIDE - Responsive HTML Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<link href="assets/css/docs.css" rel="stylesheet">
<link href="assets/js/google-code-prettify/prettify.css"
	rel="stylesheet">
<link href="assets/nivo/nivo-slider.css" type="text/css" media="screen"
	rel="stylesheet">
<link href="assets/css/prettyPhoto.css" type="text/css" media="screen"
	rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<!--[if lte IE 8]>
    	<link rel="stylesheet" type="text/css" href="assets/css/ie.css" />
		<![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">

<!-- 
        <link href='http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz:400,200,300,700&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
         -->
</head>

<body>


	<!--PAGE HEAD-->
	<jsp:include page="./inc/header.jsp"></jsp:include>
	<!--/PAGE HEAD-->

	<!--WELCOME AREA
    <div class="container">
        <div class="row">
            <div class="span12">
            	<div class="welcome">
                    <h6><span class="colored"><i class="icon-user icon-pride"></i> Contact us:</span> page tagline goes here</h6>
                </div>
            </div>
        </div>
    </div>
    <!--/WELCOME AREA-->

	<!--PAGE CONTENT-->
	<div class="container" style="margin-bottom: 50px;">
		<div class="row">
			<div class="span6 offset3">
				<h3 class="colored sep_bg">用户登录</h3>
				<div class="row">
					<form class="form-horizontal" name="loginForm" action="./login" method="post">
						<div class="control-group">
							<label class="control-label" for="inputUsername">用户名</label>
							<div class="controls">
								<input type="text" id="inputUsername" name="username" placeholder="用户名">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPassword">密 码</label>
							<div class="controls">
								<input type="password" id="inputPassword"  name="password"placeholder="密码">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-primary btn-large">登  录 </button>
								<button type="reset" class="btn btn-large">重  置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--PAGE CONTENT-->

	<!--FOOTER-->
	<jsp:include page="./inc/footer.jsp"></jsp:include>
	<!--/FOOTER-->

	</script>

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="assets/js/jquery-1.7.1.min.js"></script>
	<!-- <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script> -->
	<script src="assets/js/google-code-prettify/prettify.js"></script>
	<script src="assets/js/bootstrap-transition.js"></script>
	<script src="assets/js/bootstrap-alert.js"></script>
	<script src="assets/js/bootstrap-modal.js"></script>
	<script src="assets/js/bootstrap-dropdown.js"></script>
	<script src="assets/js/bootstrap-scrollspy.js"></script>
	<script src="assets/js/bootstrap-tab.js"></script>
	<script src="assets/js/bootstrap-tooltip.js"></script>
	<script src="assets/js/bootstrap-popover.js"></script>
	<script src="assets/js/bootstrap-button.js"></script>
	<script src="assets/js/bootstrap-collapse.js"></script>
	<script src="assets/js/bootstrap-carousel.js"></script>
	<script src="assets/js/bootstrap-typeahead.js"></script>
	<script src="assets/js/application.js"></script>
	<script src="assets/js/jquery.easing.1.3.js"></script>
	<script src="assets/js/superfish-menu/superfish.js"></script>
	<script src="assets/js/jquery.nivo.slider.js"></script>
	<script src="assets/js/jquery.prettyPhoto.js"></script>
	<script src="assets/js/twitter.js"></script>
	<script src="assets/js/testimonialrotator.js"></script>
	<script src="assets/js/slides.min.jquery.js"></script>
	<script src="assets/js/jquery.preloader.js"></script>
	<script src="assets/js/jquery.waitforimages.js"></script>
	<script src="assets/js/jquery.isotope.min.js"></script>

	<!-- FOR CONTACT PAGE -->
	<script type="text/javascript" src="assets/js/contact.js"></script>


	<!-- /FOR CONTACT PAGE -->

	<script src="assets/js/custom.js"></script>

	<script type="text/javascript">var runFancy = true;</script>
	<!--[if IE]>
    <script type="text/javascript">
        runFancy = false;
    </script>
    <![endif]-->
	<script type="text/javascript">
		if (runFancy) {
			jQuery.noConflict()(function($){
			$(".view").preloader();
			$(".theme-default").preloader();
			});
		};
    </script>

</body>
</html>
