<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
String contextPath = ConstFront.CONTEXT_PATH;
%>

<%
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <title>设计师资料 - 【金玩儿网】</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="icon" href="<%=contextPath%>/img/favicon.ico">
        <link rel="stylesheet" href="<%=contextPath%>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/font-awesome.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/animate.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/flexslider.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/style.css">
        
        <link rel="stylesheet"href="<%=contextPath%>/uploadify/uploadify.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/css/ie8.css" />    
        <![endif]-->
                

        <script src="<%=contextPath%>/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="<%=contextPath%>/js/vendor/jquery-1.8.3.min.js"></script>
        <script src="<%=contextPath%>/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>

        
        
        
        <jsp:include page="../inc/baiduAsyncStat.jsp"></jsp:include>
    </head>
    <body class="body-background" style="background-image: url(<%=contextPath%>/img/backgrounds/bg.jpg); ">

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
                        <li><a href="<%=contextPath%>">首页</a>/</li>
                        <li><a href="<%=contextPath%>/settings">设置</a>/</li>
                        <li><a href="javascript:void(0)">设计师资料</a></li>
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
                                	<jsp:include page="./settingsTabInc.jsp?settingsMenuFlag=designerInfo"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="apply4Designer">
                                        <div class="widget-box widget-wrapper-form">
                                        	<form id='album-widget-form' class="widget-form clearfix" method="post" 
												action="<%=contextPath%>/settings/designerApply">
												<div class="content-title">
													<h4>设计师资料</h4>
												</div>
												<div class="infobox info-info info-info-alt clearfix">
					                                <span>i</span>
					                                <div class="infobox-wrap">
					                                    <h4>设计师状态：</h4>
					                                    <p>
														<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														您目前还不是设计师，申请设计师需完整填写以下资料，审核通过后即可发布作品!
														<%}else if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_SENT){%>
														您的设计师申请已提交，请耐心等待审核结果!
														<%}else if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED){%>
														您的设计师申请已经审核通过，可以
														<a class="button button-green button-small" href="<%=contextPath%>/settings/newAlbum">发布作品</a>
														<%}%>
														</p>
					                                </div>
					                            </div>
					                            
												<div class="row-container clearfix">
													<div class="row-left">真实姓名: </div>
													<div class="row-right">
													<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span4" id="realname" name="realname" value=""/>
														<span id="designer-realname-required" class="required">*</span>
														<span id="designer-realname-prompt" class="text-prompt"></span>
													<%}else{%>
														<%=currentUser.getDesignerRealname()%>
													<%} %>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">身份证号: </div>
													<div class="row-right">
													 <%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span6" id="idNum"  name="idNum" value=""/>
														<span id="designer-idNum-required" class="required">*</span>
														<span id="designer-idNum-prompt" class="text-prompt"></span>
													<%}else{%>
														<%=currentUser.getDesignerIdentifer()%>
													<%} %>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">手机号: </div>
													<div class="row-right">
													<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span4" id="mobile" name="mobile" value=""/>
														<span id="designer-mobile-required" class="required">*</span>
														<span id="designer-mobile-prompt" class="text-prompt"></span>
													<%}else{%>
														<%=currentUser.getDesignerMobile()%>
													<%} %>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">公 司: </div>
													<div class="row-right">
													<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span6" id="company" name="company" value=""/>
														<span id="designer-company-required" class="required"></span>
														<span id="designer-company-prompt" class="text-prompt"></span>
													<%}else{%>
														<%=currentUser.getDesignerCompany()%>
													<%} %>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">淘宝店铺主页: </div>
													<div class="row-right">
													<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span6" name="taobaoHomepage" value=""/> 
														<span id="designer-taobaoHomepage-required" class="required"></span>
														<span id="designer-taobaoHomepage-prompt" class="text-prompt"></span>
													<%}else{%>
														<%=currentUser.getDesignerTaobaoHomepage()%>
													<%} %>
													</div>
												</div>
												
												<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
												<div class="content-title">
													<h4>并附带一组作品集【上限6张】</h4>
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
												<!-- <div>
													<ul id="imgPreview" class="clearfix">
													</ul>
												</div> -->
												
												<script type="text/javascript">
													$(function() {
														var counter = 0;
														$('#fileUploader').uploadify({
															'swf' : '<%=contextPath%>/uploadify/uploadify.swf',
															//'uploader' : '<%=contextPath%>/uploadify/response.json',
															'uploader' : '<%=contextPath%>/uploadImage.json;jsessionid=<%=session.getId()%>',
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
												
												<input id="submit-button" class="common-submit button" type="button" value="申请设计师">
												<%}%>
											</form>
										</div>
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
    <script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
    <script src="<%=contextPath%>/js/superfish.js"></script>
    <!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
   <!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
    <script src="<%=contextPath%>/js/jquery.flexslider.js"></script> 
    <script src="<%=contextPath%>/js/retina.js"></script>
    <script src="<%=contextPath%>/js/custom.js"></script>
    <script>
    
    
    var idNumAvailable = false;
    var realnameAvailable = false;
    var mobileAvailable = false;
    
    var idNumAvailable = false;
    var titleAvailable = false;
    var tagsAvailable = false;
	var priceAvailable = false;
	var linkAvailable = false;
	var albumAvailable = false;
    
	$('#idNum').blur(function(){
		checkIdNum();
	});
	
	$('#realname').blur(function(){
		checkRealname();
	});
	
	$('#mobile').blur(function(){
		checkMobile();
	});
	
	
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
		if(titleAvailable && tagsAvailable && priceAvailable && linkAvailable && albumAvailable && idNumAvailable && realnameAvailable && mobileAvailable){
			$("#album-widget-form").submit();
    	}
	});
    
	//检查身份证
    function checkIdNum(){
    	var idNumVal = $('#idNum').val();
    	if(idNumVal==''){
			$('#album-title-prompt').text('身份证号码不能为空').show();
		}else{
			idNumAvailable = true;
			$('#album-idNum-prompt').hide();
		}
    }
  //检查身份证
    function checkIdNum(){
    	var idNumVal = $('#idNum').val();
    	if(idNumVal==''){
			$('#designer-idNum-prompt').text('身份证号码不能为空').show();
		}else{
			idNumAvailable = true;
			$('#designer-idNum-prompt').hide();
		}
    }
	//检查身份证
	function checkRealname(){
		var realnameVal = $('#realname').val();
		if(realnameVal==''){
			$('#designer-realname-prompt').text('真实姓名不能为空').show();
		}else{
			realnameAvailable = true;
			$('#designer-realname-prompt').hide();
		}
	}
	
	//检查手机号
	function checkMobile(){
		var mobileVal = $('#mobile').val();
		var mobileRegex = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
		if(mobileVal==''){
			$('#designer-mobile-prompt').text('手机号不能为空').show();
		}else if(!mobileRegex.test(mobileVal)){//手机号正则检查
			
			$('#designer-mobile-prompt').text('手机号格式有误').show();
		}else{
			mobileAvailable = true;
			$('#designer-mobile-prompt').hide();
		}
	}
  
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
				//alert("选中！");
				albumAvailable = true;
			}
		}
	}
	</script>
	
	<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
	<!-- 设计师协议 -->
    <div id="cautionModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="cautionModalLabel" aria-hidden="true">
        <div class="modal-header">
            <h4>设计师申请协议</h4>
        </div>
      <div class="modal-body">
        <p>
            1、设计师在本站发布的所有作品应为原创设计，不得有抄袭、剽窃行为。
       	</p>
       	<p>
            2、不得发布违法为原材料的首饰作品，如象牙等。
       </p>
       	<p>
            3、不得发布与本站无关的图片或内容，如色情、暴力。
        </p>
       	<p>
            4、设计师在本站发布的所有作品内容，本站享有使用权。
        </p>
      </div>
      <div class="modal-footer">
        <button class="button" data-dismiss="modal" aria-hidden="true">我同意</button>
        <button id="abort" class="button button-white">放 弃</button>
      </div>
    </div>
   	
   	
   	<script>
   	$('#cautionModal').modal();
   	$('#abort').click(function(){
   		history.back();
   		//location.href='<%=contextPath%>/settings';
   	});
   	</script>
	<%}%>
	
    </body>
</html>
