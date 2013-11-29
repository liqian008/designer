package com.bruce.designer.front.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.designer.model.User;
import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseUtil;
import com.bruce.designer.service.IUserService;

@Controller
public class SystemController {

	@Autowired
	private IUserService userService;

	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if (StringUtils.isNotEmpty(redirectUrl)) {
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
		}
		return "login/loginAndReg";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(Model model, HttpServletRequest request, String username, String password,
			@RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if (StringUtils.isNotEmpty(redirectUrl)) {
			// 跳转地址
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
		}

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
			@RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if (StringUtils.isNotEmpty(redirectUrl)) {
			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
		}

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
			request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
			model.addAttribute(ConstFront.REDIRECT_PROMPT, "您好，" + nickname + "，您已成功注册，现在将转入首页，请稍候…");
			return ResponseUtil.getForwardReirect();
		} else {
			model.addAttribute(ConstFront.REG_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.USER_PASSWORD_NOT_MATCH));
			model.addAttribute(ConstFront.REGISTER_ACTIVE, "REGISTER_ACTIVE");
			return "login/loginAndReg";
		}
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
