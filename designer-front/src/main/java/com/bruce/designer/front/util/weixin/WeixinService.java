package com.bruce.designer.front.util.weixin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.weixin.bean.response.Article;
import com.bruce.designer.front.util.weixin.bean.response.NewsMessage;
import com.bruce.designer.front.util.weixin.bean.response.TextMessage;
import com.bruce.designer.model.Album;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IHotService;

/**
 * 核心服务类
 * 
 * @author liqian
 * 
 */
@Service
public class WeixinService {

	// 默认返回的文本消息内容 
	private static final String AUTO_REPLY_CONTENT = ConstFront.WEIXIN_AUTO_REPLY_CONTENT;//"回复【最新作品】或【zxzp】查看最新作品\n回复【热门作品】或【rmzp】查看热门作品\n\n更多精彩作品可访问【金玩网】\nhttp://www.jinwanr.com.cn";
	// 关注欢迎文本消息
	private static final String WELCOME_CONTENT = ConstFront.WEIXIN_WELCOME_CONTENT + AUTO_REPLY_CONTENT;//"您好，欢迎您关注【金玩儿网】微信公众账户\n\n";

	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IHotService hotService;

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		try {
			// xml请求解析
			Map<String, String> requestMap = WeixinMessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息内容
			String content = requestMap.get("Content");

			// 文本消息
			if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				if (content.equals("最新作品") || content.equalsIgnoreCase("zxzp")) {
					return buildNewAlbums(fromUserName, toUserName);
				}else if (content.equals("热门作品") || content.equalsIgnoreCase("rmzp")) {
					return buildHotAlbums(fromUserName, toUserName);
				}
//				else if (content.equals("最新设计师") || content.equalsIgnoreCase("zxsjs")) {
//					return buildHotDesigner(fromUserName, toUserName);
//				}else if (content.equals("热门设计师") || content.equalsIgnoreCase("rmsjs")) {
//					return buildHotDesigners(fromUserName, toUserName);
//				}
				else {// auto reply

				}
			} else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
				// 事件类型
				String eventType = requestMap.get("Event");

				if (eventType.equals(WeixinMessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅
					return WeixinMessageUtil.messageToXml(buildTextMessage(fromUserName, toUserName, WELCOME_CONTENT));
				} else if (eventType.equals(WeixinMessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				// else if
				// (eventType.equals(WeixinMessageUtil.EVENT_TYPE_CLICK)) {
				// // 事件KEY值，与创建自定义菜单时指定的KEY值对应
				// String eventKey = requestMap.get("EventKey");
				// if (eventKey.equals("weather")) {
				// respContent = "天气预报菜单项被点击！";
				// } else if (eventKey.equals("shop1")) {
				// respContent = "分店1被点击！";
				// } else if (eventKey.equals("shop2")) {
				// respContent = "分店2被点击！";
				// }
				// }
			}
			return WeixinMessageUtil.messageToXml(buildTextMessage(fromUserName, toUserName, AUTO_REPLY_CONTENT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 最新作品
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	private String buildNewAlbums(String fromUserName, String toUserName) {
		return buildAlbums(fromUserName, toUserName, 1);
	}

	/**
	 * 热门作品
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	private String buildHotAlbums(String fromUserName, String toUserName) {
		return buildAlbums(fromUserName, toUserName, 0);
	}
	
//	private String buildNewDesigners(String fromUserName, String toUserName) {
//		return buildDesigners(fromUserName, toUserName, 1);
//	}
//
//	private String buildHotDesigners(String fromUserName, String toUserName) {
//		return buildDesigners(fromUserName, toUserName, 0);
//	}

	private String buildAlbums(String fromUserName, String toUserName, int albumType) {
		// 创建图文消息
		NewsMessage newsMessage = genNewsMessage(fromUserName, toUserName);

		List<Album> albumList = null;
		if (albumType == 1) {
			albumList = albumService.fallLoadAlbums(0, 3, false, false, false);
		} else {
			albumList = hotService.fallLoadHotAlbums(IHotService.DAILY_FLAG, ConstFront.HOT_ALBUM_WEIXIN_LIMIT);
		}
		if (albumList != null && albumList.size() > 0) {
			List<Article> articleList = new ArrayList<Article>();
			for (Album album : albumList) {
				Article article = new Article();
				article.setTitle(album.getTitle());
				article.setDescription(album.getRemark());
				article.setPicUrl(album.getCoverMediumImg());
				article.setUrl(ConstFront.DOMAIN + ConstFront.CONTEXT_PATH + "/album/" + album.getId());
				articleList.add(article);
			}
			// 设置图文消息个数
			newsMessage.setArticleCount(articleList.size());
			// 设置图文消息包含的图文集合
			newsMessage.setArticles(articleList);
			// 将图文消息对象转换成xml字符串
			return WeixinMessageUtil.messageToXml(newsMessage);
		}
		return "";
	}
	
	

	/**
	 * 创建图文消息
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	private NewsMessage genNewsMessage(String fromUserName, String toUserName) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(WeixinMessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);
		return newsMessage;
	}

	/**
	 * 创建文字消息
	 * 
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return
	 */
	private static TextMessage buildTextMessage(String fromUserName, String toUserName, String content) {
		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setContent(content);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WeixinMessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setFuncFlag(0);
		return textMessage;
	}

	public static void main(String[] args) {
		WeixinService service = new WeixinService();
		System.out.println();
		service.processRequest(null);
	}
}