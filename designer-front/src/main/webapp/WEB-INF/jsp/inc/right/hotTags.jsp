<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>


<script src="/designer-front/js/jquery.tagcloud.js"></script>
<script>
	$.fn.tagcloud.defaults = {
		size : {
			start : 8,
			end : 13,
			unit : 'pt'
		},
		color : {
			start : '#bcd',
			end : '#f52'
		}
	};

</script>
<div class="widget-box widget-social-media">
	<div class="content-title">
		<h4 class="widget-title">热门标签</h4>
	</div>
	<div id="tagcloud">
	</div>
	
	<script>
		initHotTag();
		function initHotTag(){
			$.post('/designer-front/tag/hotTags.json', function(data) {
				var result = data.result;
				if(result==1){
					$("#tagcloud").append(data.data.html);
					$('#tagcloud a').tagcloud();
				}else{
					alert('failed');
				}
			});
		}
	</script>
</div>

