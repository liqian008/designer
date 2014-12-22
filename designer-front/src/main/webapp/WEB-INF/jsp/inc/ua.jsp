<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!-- 百度异步统计代码 -->
<script>
function isWeixin(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
        return true;
     } else {
        return false;
    }
}

function isIPad(){  
    var ua = navigator.userAgent.toLowerCase();  
    if(ua.match(/iPad/i)=="ipad") {  
        return true;  
    } else {  
        return false;  
    }
}
</script>
