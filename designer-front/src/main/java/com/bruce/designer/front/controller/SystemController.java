package com.bruce.designer.front.controller;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.front.util.ResponseUtil;
import com.bruce.designer.front.util.VerifyUtils;
import com.bruce.designer.mail.MailService;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.ConfigUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
public class SystemController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private MailService mailService;

	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if (StringUtils.isNotEmpty(redirectUrl)) {
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
		}
		return "login/loginAndReg";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(Model model, HttpServletRequest request, String username, String password, @RequestParam(defaultValue = "") String verifyCode,
			@RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if (StringUtils.isNotEmpty(redirectUrl)) {
			// 跳转地址
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
		}
		
		
		//校验后删除session中的随机码
		if(!verifyCode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
			model.addAttribute(ConstFront.LOGIN_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.SYSTEM_VERIFYCODE_ERROR));
			return "login/loginAndReg";
		}
		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);

		User user = userService.authUser(username.trim(), password);
		if (user != null) {
			request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
			model.addAttribute(ConstFront.REDIRECT_PROMPT, "您好，" + user.getNickname() + "，您已成功登录，现在将转后续页面，请稍候…");
			return ResponseUtil.getForwardReirect();
		} else {// 用户身份验证失败
			model.addAttribute(ConstFront.LOGIN_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.USER_PASSWORD_NOT_MATCH));
			return "login/loginAndReg";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model, HttpServletRequest request) {
		String redirectUrl = request.getParameter(ConstFront.REDIRECT_URL);
		if (StringUtils.isNotEmpty(redirectUrl)) {
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
		}
		model.addAttribute(ConstFront.REGISTER_ACTIVE, "REGISTER_ACTIVE");
		return "login/loginAndReg";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(Model model, HttpServletRequest request, String username, String nickname, String password, String rePassword,
			@RequestParam(defaultValue="") String verifyCode, @RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if (StringUtils.isNotEmpty(redirectUrl)) {
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
		}
		//标志为注册tab
		model.addAttribute(ConstFront.REGISTER_ACTIVE, "REGISTER_ACTIVE");
		
		//校验后删除session中的随机码
		if(!verifyCode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
			model.addAttribute(ConstFront.LOGIN_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.SYSTEM_VERIFYCODE_ERROR));
			return "login/loginAndReg";
		}
		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		//验证用户名格式
		VerifyUtils.verifyUsername(username);
		
		User user = new User();
		user.setUsername(username.trim());
		user.setNickname(nickname.trim());
		user.setPassword(password.trim());
		Date currentTime = new Date();
		user.setCreateTime(currentTime);
		user.setUpdateTime(currentTime);
		int result = userService.save(user);
		if (result == 1) {
			user = userService.authUser(username, password);
			if(user!=null){
				request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
				model.addAttribute(ConstFront.REDIRECT_PROMPT, "您好，" + nickname + "，您已成功注册，现在将转入首页，请稍候…");
				//系统发送欢迎消息
				long sourceId = 0;
				String welcomeMessage = ConfigUtil.getString("welcome_message");
				messageService.sendMessage(sourceId, ConstService.MESSAGE_DELIVER_ID_BROADCAST, user.getId(),  welcomeMessage, ConstService.MESSAGE_TYPE_SYSTEM);
				//系统异步发送欢迎邮件
				mailService.sendWelcomeMail(username);
				
				return ResponseUtil.getForwardReirect();
			}
		} else {
			model.addAttribute(ConstFront.REG_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.USER_PASSWORD_NOT_MATCH));
			return "login/loginAndReg";
		}
		throw new DesignerException(ErrorCode.ALBUM_CREATE_FAILED);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstFront.CURRENT_USER);
		request.setAttribute(ConstFront.REDIRECT_PROMPT, "您已成功注销登录，现在将以游客身份转入首页，请稍候…");
		return ResponseUtil.getRedirectHomeString();
	}
	
	
	@Autowired
	private Producer captchaProducer;

	@RequestMapping(value="verifyCode")
	public ModelAndView getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
//		String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//		System.out.println("******************验证码是: " + code + "******************");
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = captchaProducer.createText();
//		System.out.println("******************新验证码是: " + capText + "******************");
		// store the text in the session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
	
	@RequestMapping(value = "/checkVerifyCode.json")
	public ModelAndView checkVerifyCode(Model model, HttpServletRequest request, @RequestParam(defaultValue="") String verifyCode) {
		String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(verifyCode.equals(code)){
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}else{
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_VERIFYCODE_ERROR));
//			throw new DesignerException(ErrorCode.SYSTEM_VERIFYCODE_ERROR);
		}
	}
	
	/**
	 * 跳转请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/redirect")
	public String redirect(HttpServletRequest request) {
		return "redirect";
	}
	
	@RequestMapping(value = "/denied")
	public String denied(HttpServletRequest request) {
		return "denied";
	}

	@NeedAuthorize
	@RequestMapping(value = "/loginBack", method = RequestMethod.POST)
	public String loginBack(Model model) {
		return "login/loginAndReg";
	}

}
