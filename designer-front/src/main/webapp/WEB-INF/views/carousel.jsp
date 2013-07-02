<%@ page language="java" contentType="text/html; charset=utf-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>轮播示例</title>
<link href="http://www.see-source.com/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript"
	src="http://www.see-source.com/bootstrap/js/jquery.js"></script>
<script type="text/javascript"
	src="http://www.see-source.com/bootstrap/js/bootstrap-carousel.js"></script>
	
	<script type="text/javascript" src="http://www.see-source.com/bootstrap/js/jquery.js"></script>  
<script type="text/javascript" src="http://www.see-source.com/bootstrap/js/bootstrap-carousel.js"></script>  
<script type="text/javascript" src="http://www.see-source.com/bootstrap/js/bootstrap-transition.js"></script> 

<style type="text/css">
body {
	width: 800px;
	margin: auto;
	margin-top: 100px;
}
</style>

</head>

<body>
	<div id="myCarousel" class="carousel slide">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="a0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="a1"></li>
			<li data-target="#myCarousel" data-slide-to="a2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="item active">
				<img
					src="http://www.bootcss.com/assets/img/bootstrap-mdo-sfmoma-01.jpg"
					alt="">
				<div class="carousel-caption">
					<h4>First Thumbnail label</h4>
					<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.
						Donec id elit non mi porta gravida at eget metus. Nullam id dolor
						id nibh ultricies vehicula ut id elit.</p>
				</div>
			</div>
			<div class="item">
				<img
					src="http://www.bootcss.com/assets/img/bootstrap-mdo-sfmoma-02.jpg"
					alt="">
				<div class="carousel-caption">
					<h4>Second Thumbnail label</h4>
					<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.
						Donec id elit non mi porta gravida at eget metus. Nullam id dolor
						id nibh ultricies vehicula ut id elit.</p>
				</div>
			</div>
			<div class="item">
				<img
					src="http://www.bootcss.com/assets/img/bootstrap-mdo-sfmoma-03.jpg"
					alt="">
				<div class="carousel-caption">
					<h4>Third Thumbnail label</h4>
					<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam.
						Donec id elit non mi porta gravida at eget metus. Nullam id dolor
						id nibh ultricies vehicula ut id elit.</p>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
		
		
		
	</div>
	
	<script type="text/javascript">   
$('#myCarousel').carousel('next');  
</script>  
</body>
</html>
