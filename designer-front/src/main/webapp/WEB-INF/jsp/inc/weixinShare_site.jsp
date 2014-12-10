<%@ page language="java" contentType="text/html; charset=utf-8"%>


<script>
var imgUrl = "https://open.weixin.qq.com/cgi-bin/openproxy?url=http%3A%2F%2Fmmbiz.qpic.cn%2Fmmbiz%2FNTRC7kKL3m1ssRI2vvYQLmYhqcsekxfT0FicoicI3MJGm5fibrZv7j0yq7OE6TOzgia4goFH3soUT0wp1yRJoejwzw%2F0";
var lineLink = "http://www.jinwanr.com";
var shareTitle = '【金玩儿网】';
var shareDesc = '最专业的原创首饰设计网';

function shareFriend() {
    WeixinJSBridge.invoke('sendAppMessage',{
        "img_url": imgUrl,
        "link": lineLink,
        "desc": shareDesc,
        "title": shareTitle
    }, function(res) {
    })
}

function shareTimeline() {
    WeixinJSBridge.invoke('shareTimeline',{
        "img_url": imgUrl,
        "link": lineLink,
        "desc": shareDesc,
        "title": shareTitle
    }, function(res) {
	   //_report('timeline', res.err_msg);
	});
}
// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	// 发送给好友
	WeixinJSBridge.on('menu:share:appmessage', function(argv){
	shareFriend();
	});
	//分享到朋友圈
    WeixinJSBridge.on('menu:share:timeline', function(argv){
      shareTimeline();
     });
}, false);
</script>