<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<div class="widget-box widget-social-media">
	<div class="content-title">
		<h4 class="widget-title">热门设计师推荐</h4>
	</div>
	<ul class="clearfix" id="hotDesignerContainer">
		<!-- html format -->
		<!-- <li class="social-icons-facebook-icon"><a href="#"><img
				src="/designer-front/img/demo/portraits/avatar_middle.jpg"
				alt="Page Title" /></a></li> -->
	</ul>
</div>
<script>
fallLoad();

function fallLoad(){
	$.get('/designer-front/hot/designers.json', function(data) {
		var result = data.result;
		if(result==1){
			$("#hotDesignerContainer").append(data.data.html);
		}else{
			alert("error");
		}
	});
}
</script>