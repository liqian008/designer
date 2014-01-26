<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
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

        <link rel="stylesheet" href="/designer-front/css/bootstrap.min.css">
        <link rel="stylesheet" href="/designer-front/css/font-awesome.css">
        <link rel="stylesheet" href="/designer-front/css/animate.css">
        <link rel="stylesheet" href="/designer-front/css/flexslider.css">
        <link rel="stylesheet" href="/designer-front/css/style.css">
        
        <link rel="stylesheet"href="/designer-front/uploadify/uploadify.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="/designer-front/css/ie8.css" />    
        <![endif]-->
                

        <script src="/designer-front/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="/designer-front/js/vendor/jquery-1.8.3.min.js"></script>
        <script src="/designer-front/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(/designer-front/img/backgrounds/bg3.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        
        <jsp:include page="../../inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                <jsp:include page="../../inc/headerBanner.jsp"></jsp:include>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="../../inc/headerNav.jsp?menuFlag=settings"></jsp:include>

                    <jsp:include page="../../inc/ad.jsp"></jsp:include>

                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="/designer-front">首页</a>/</li>
                        <li><a href="/designer-front/settings">设置</a>/</li>
                        <li><a href="javascript:void(0)">新发布</a></li>
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
                                	<jsp:include page="../settingsTabInc.jsp?settingsMenuFlag=publisher"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="apply4Designer">
                                         <div class="widget-box widget-wrapper-form clearfix">
                                        	<form id='album-widget-form' class="widget-form" method="post" class="clearfix"
												action="/designer-front/settings/postAlbum">
												<div class="content-title">
													<h4>发布作品集【上限6张】</h4>
												</div>
												
												<div class="infobox info-info info-info-alt clearfix">
					                                <span>i</span>
					                                <div class="infobox-wrap">
					                                    <h4>小贴士：</h4>
					                                    <p>为达到最佳浏览效果，建议使用横竖比为4:3，且分辨率不小于800x600的图片</p>
					                                </div>
					                                <a href="#" class="info-hide"></a>
					                            </div>
												
												<div class="row-container clearfix">
													<div class="row-left">作品主题: </div>
													<div class="row-right">
														<input type="text" class="span6" id="title"  name="title" value=""/>
														<span id="album-title-required" class="required">*</span>
														<span id="album-title-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">标签: </div>
													<div class="row-right">
														<input type="text" class="span5" id="tags" name="tags" value=""/>
														<span id="album-tags-required" class="required">*</span>
														<span id="album-tags-prompt" class="text-prompt">多个标签间请用空格分隔</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">参考价格: </div>
													<div class="row-right">
														<input type="text" class="span3" id="price" name="price" value=""/> 元
														<span id="album-price-required" class="required">*</span>
														<span id="album-price-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">购买链接: </div>
													<div class="row-right">
														<input type="text" class="span8" id="link" name="link" value=""/>
														<span id="album-link-required" class="required">*</span>
														<span id="album-link-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">上传作品: </div>
													<div class="row-right" id="previewContainer">
														<input id="fileUploader" name="image" type="file" multiple="true">
														<span id="album-upload-prompt" class="text-prompt" style="display:none"></span>
													</div>
												</div>
												<div id="queue"></div>
												
												<div class="row-container clearfix">
													<div class="row-left">作品描述: </div>
													<div class="row-right">
														<textarea class='album-slide-remark' name='remark' rows='2'></textarea>
													</div>
												</div>
												<div class="row-container clearfix">
													<div class="row-left">分享选项: </div>
													<div class="row-right">
														<input type="checkbox" name="" value="1"/>同时分享到Sina微博 &nbsp;<a href="/designer-front/settings/thirdparty">修改分享设置</a><br/>
														<input type="checkbox" name="" value="1"/>同时分享到QQ空间&nbsp;<a href="/designer-front/settings/thirdparty">修改分享设置</a>
													</div>
												</div>
												<!-- <div>
													<ul id="imgPreview" class="clearfix">
													</ul>
												</div> -->
												
												<script type="text/javascript">
													$(function() {
														var counter = 0;
														$('#fileUploader').uploadify({
															'swf' : '/designer-front/uploadify/uploadify.swf',
															//'uploader' : '/designer-front/uploadify/response.json',
															'uploader' : '/designer-front/uploadImage.json;jsessionid=<%=session.getId()%>',
															//'cancelImg' : "uploadify-cancel.png",
															'fileObjName' : 'image',
															'debug' : false,
															'buttonText' : '选择照片',
															'method' : 'post',
															'fileTypeDesc' : '图片文件',
															'fileTypeExts' : '*.*',
															'multi' : true,
															'auto' : true,
															'uploadLimit' : 6,
															'simUploadLimit' : 1,
															'fileSizeLimit' : 2048,
															'onUploadSuccess' : function(file, data, response) {
													            //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);
													            //alert(data);
													            counter = counter + 1;
													            var response = jQuery.parseJSON(data);
													            
													        	$("<div  style='margin:10px 0; outline:1px solid #ECECEC'><img id='img1' src='"+response.data.mediumImage.url+"' width='100%'/><br/><input type='radio' id='coverId' name='coverId' value='"+counter+"'/>设置为封面<br/></div>").appendTo($("#previewContainer"));
													        	$("<input type='hidden' name='albumSlideNums' value='"+counter+"'/>").appendTo($("#previewContainer"));
													        	$("<input type='hidden' name='largeImage"+counter+"' value='"+response.data.largeImage.url+"'/>").appendTo($("#previewContainer"));
													        	$("<input type='hidden' name='mediumImage"+counter+"' value='"+response.data.mediumImage.url+"'/>").appendTo($("#previewContainer"));
													        	$("<input type='hidden' name='smallImage"+counter+"' value='"+response.data.smallImage.url+"'/>").appendTo($("#previewContainer"));
															},
														});
													});
												</script>
												
												<input class="common-submit button" id="submit-button" type="button" value="提 交">
											</form>
										</div>
                                    </div>
                                </div>
                            </div>
                        </section> 
                        
                        <!-- right slidebar -->
						<aside class="sidebar widgets-light span3">
                       		<jsp:include page="../../inc/right/sidebar_settings.jsp"></jsp:include> 
                    	</aside>
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="../../inc/footer.jsp"></jsp:include>
           
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
    var titleAvailable = false;
    var tagsAvailable = false;
	var priceAvailable = false;
	var linkAvailable = false;
	var albumAvailable = false;
    
	$('#title').blur(function(){
		checkTitle();
	});
	
	$("#tags").blur(function(){
		checkTags();
	});
	
	$('#price').blur(function(){
		checkPrice();
	});
	
	$('#link').blur(function(){
		checkLink();
	});
	
    $('#submit-button').click(function(){
		checkAlbumSlides();
		if(titleAvailable && tagsAvailable && priceAvailable && linkAvailable && albumAvailable){
			$("#album-widget-form").submit();
    	}
	});
    
    //检查标题&正则
    function checkTitle(){
    	var titleVal = $('#title').val();
    	if(titleVal==''){
			$('#album-title-prompt').text('作品标题不能为空').show();
		}else{
			titleAvailable = true;
			$('#album-title-prompt').hide();
		}
    }
    
	//检查标签&正则
    function checkTags(){
    	var tagsVal = $('#tags').val();
		if(tagsVal==''){
 			$('#album-tags-prompt').text('标签不能为空').show();
 		}else{
 			tagsAvailable = true;
 			$('#album-tags-prompt').hide();
 		}
    }
    
	//检查价格&正则
    function checkPrice(){
    	var priceVal = $('#price').val();
    	if(priceVal==''){
			$('#album-price-prompt').text('作品价格不能为空').show();
		}else{
			priceAvailable = true;
			$('#album-price-prompt').hide();
		}
	}
	
	//检查标题&正则
    function checkLink(){
    	var linkVal = $('#link').val();
    	if(linkVal==''){//检查链接是否正确
			$('#album-link-prompt').text('购买链接不能为空').show();
		}else{
			linkAvailable = true;
			$('#album-link-prompt').hide();
		}
	}
	
  //检查标题&正则
    function checkAlbumSlides(){
		if($('#coverId').length<=0){
			$('#album-upload-prompt').text('请上传作品内容图').show();
		}else if($('#coverId').length>6){
			$('#album-upload-prompt').text('上传作品内容图超限').show();
		}else{
			var coverIdVal = $('input:radio[name="coverId"]:checked').val();
			if(coverIdVal == null){
				$('#album-upload-prompt').text('请设置封面图片').show();
			}else{
				alert("选中！");
				albumAvailable = true;
			}
		}
	}
    </script>
    </body>
</html>
