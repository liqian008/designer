package com.bruce.designer.front.controller.weixin;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.front.util.weixin.SignUtil;
import com.bruce.designer.front.util.weixin.WeixinService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/weixin")
public class WeixinController {

	private static final Logger logger = LoggerFactory.getLogger(WeixinController.class);
	
	@Autowired
	private WeixinService weixinService;

//	/**
//	 * Simply selects the home view to render by returning its name.
//	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String index(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//
//		String formattedDate = dateFormat.format(date);
//
//		model.addAttribute("serverTime", formattedDate);
//
//		return "home";
//	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String get(Model model, String signature, String timestamp, String nonce, String echostr) {
	    if(logger.isDebugEnabled()){
            logger.debug("微信验证");
        }
	    boolean checkResult = SignUtil.checkSignature(signature, timestamp, nonce);
		if (checkResult) {
		    if(logger.isDebugEnabled()){
                logger.debug("微信验证成功");
            }
			model.addAttribute("echostr", echostr);
		} else {
		    if(logger.isErrorEnabled()){
	            logger.error("微信验证失败");
	        }
			model.addAttribute("echostr", "");
		}
		return "weixin/token";
	}

	@RequestMapping(value = "/api", method = RequestMethod.POST)
	public String post(HttpServletRequest request, HttpServletResponse response, Model model) {
	    if(logger.isDebugEnabled()){
            logger.debug("微信请求数据");
        }
	    
	    // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
		    if(logger.isErrorEnabled()){
                logger.error("设置编码异常", e);
            }
		}
		// 调用核心业务类接收消息、处理消息
//		System.out.println("process weixin request...");
		
		String respMessage = weixinService.processRequest(request);
		model.addAttribute("respMessage", respMessage);
		return "weixin/response";
	}

}
