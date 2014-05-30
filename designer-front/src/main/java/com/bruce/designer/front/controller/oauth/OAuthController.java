package com.bruce.designer.front.controller.oauth;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.designer.mail.MailService;
import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.User;
import com.bruce.designer.annotation.NeedAuthorize;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseUtil;
import com.bruce.designer.front.util.VerifyUtils;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.oauth.IAccessTokenService;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.service.oauth.SharedInfo;
import com.bruce.designer.util.OAuthUtil;
import com.google.code.kaptcha.Constants;
@Controller
public class OAuthController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IOAuthService oAuthService;
	@Autowired
	private IAccessTokenService accessTokenService;
	@Autowired
	private MailService mailService;
	

	private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);

	@RequestMapping(value = "/connectWeibo")
	public String connectWeibo(HttpServletRequest request, @RequestParam(required=false) String redirectUrl) throws Exception {
	    if(logger.isDebugEnabled()){
            logger.debug("请求微薄登录"+redirectUrl);  
        }
	    String weiboOAuthUrl = new weibo4j.Oauth().authorize("code", redirectUrl);
		return ResponseUtil.getRedirectString(weiboOAuthUrl);
	}

	@RequestMapping(value = "/connectTencent")
	public String connectTencent(HttpServletRequest request) throws Exception {
	    if(logger.isDebugEnabled()){
            logger.debug("请求QQ登录");
        }
		return ResponseUtil.getRedirectString(new com.qq.connect.oauth.Oauth().getAuthorizeURL(request));
	}

	/**
	 * weibo OAauth callback
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wbCallback")
	public String wbCallback(Model model, HttpServletRequest request, @RequestParam(required=false) String code, 
			@RequestParam(required=false) String state) { 
//		String code = request.getParameter("code");
		if(logger.isDebugEnabled()){
            logger.debug("微博登录回调"+state);
        }
		if (StringUtils.isBlank(code)) {// 用户取消授权，直接返回
			return ResponseUtil.getRedirectHomeString();
		}
		return unifiedCallback(request, IOAuthService.OAUTH_WEIBO_TYPE, state);
	}

	/**
	 * tencent OAauth callback
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tencentCallback")
	public String tencentCallback(Model model, HttpServletRequest request, 
			@RequestParam(value="state", required=false) String redirectUrl) {
	    if(logger.isDebugEnabled()){
            logger.debug("QQ登录回调");
        }
	    return unifiedCallback(request, IOAuthService.OAUTH_TENCENT_TYPE, null);
	}
	
//	/**
//	 * tencent OAauth callback(为通过审核临时做的)
//	 * @param model
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	
//	@RequestMapping(value = "/tencentCallback")
//	public String tencentCallback(Model model, HttpServletRequest request, 
//			@RequestParam(value="state", required=false) String redirectUrl) {
//	    if(logger.isDebugEnabled()){
//            logger.debug("QQ登录回调");
//        }
//	    AccessTokenInfo tokenInfo = oAuthService.loadTokenByCallback(request, IOAuthService.OAUTH_TENCENT_TYPE);
//	    if(tokenInfo!=null){
//			// 创建用户
//			User user = new User();
//			user.setNickname(tokenInfo.getThirdpartyUname());
//			user.setUsername(tokenInfo.getThirdpartyUname());
//			user.setPassword("f7c78e6132affce11e1e80d73fc16e01");
//			Date currentTime = new Date();
//			user.setCreateTime(currentTime);
//			user.setUpdateTime(currentTime);
//			user.setDesignerStatus((short) 0);
//			int result = userService.save(user);
//			request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
//		}
//		return "redirect:/";
//	}

	/**
	 * 通用oauth callback处理
	 * 
	 * @param request
	 * @param thirdpartyType
	 * @return
	 */
	private String unifiedCallback(HttpServletRequest request, short thirdpartyType, String redirectUrl) {
		AccessTokenInfo tokenInfo = oAuthService.loadTokenByCallback(request, thirdpartyType);
		String thirdpartyName = getThirdpartyName(thirdpartyType);
		
		if (tokenInfo != null) {
			// 缓存accessToken，便于后续绑定的时候使用
			request.getSession().setAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN, tokenInfo);
			// token正常
			User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
			if (user == null) {// 未登录状态
				//登录后的跳转链接
				request.setAttribute(ConstFront.REDIRECT_URL, redirectUrl);
				if (tokenInfo.getUserId() != null && tokenInfo.getUserId() > 0) {// 用户之前绑定过，可直接登录
				    if(logger.isDebugEnabled()){
	                    logger.debug("系统中存在用户["+tokenInfo.getUserId()+"]的第三方账户["+thirdpartyName+"]记录，可直接登录");
	                }
					user = userService.loadById(tokenInfo.getUserId());
					// 加载并设置用户的所有token
					List<AccessTokenInfo> accessTokenList = accessTokenService.queryByUserId(tokenInfo.getUserId());
					user.refreshTokenMap(accessTokenList);
					request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
					// oauth验证成功，返回之前页面
					request.setAttribute(ConstFront.REDIRECT_PROMPT, "欢迎您回来，" + user.getNickname() + "，即将转入先前页面，请稍候…");
					return ResponseUtil.getForwardReirect();
				} else {//新的accessToken，说明之前未绑定过，则进入注册、绑定已有账户流程
				    if(logger.isDebugEnabled()){
                        logger.debug("系统中不存在用户["+tokenInfo.getUserId()+"]的第三方账户["+thirdpartyName+"]，需登录或注册");
                    }
				    request.setAttribute(ConstFront.THIRDPARTY_USERNAME, OAuthUtil.getOAuthDisplayName(thirdpartyType, tokenInfo.getThirdpartyUname()));
					return "login/loginAndReg4Thirdparty";
				}
			} else {// 已登录状态（进入账户绑定流程）
				// 绑定现有账户流程，需检查已登录用户是否已存在该oauth类型的绑定
				AccessTokenInfo accessToken = user.getAccessTokenMap().get(thirdpartyType);
//				boolean bindAvailable = accessToken!=null && accessToken.getUserId()!=null;
//				boolean isMyToken = user.getId().equals(tokenInfo.getUserId());
//				if (bindAvailable && isMyToken) {//当前登录用户未绑定的第三方账户为空，可以进行绑定
				if (accessToken==null) {//当前登录用户未绑定的第三方账户为空，可以进行绑定
					tokenInfo.setUserId(user.getId());
					accessTokenService.save(tokenInfo);
					user.getAccessTokenMap().put(tokenInfo.getThirdpartyType(), tokenInfo);
					
					request.setAttribute(ConstFront.REDIRECT_PROMPT, "您已成功绑定[" + thirdpartyName + "]账户，即将转入个人主页，请稍候…");
					return ResponseUtil.getForwardReirect();
				} else {// 该uid已经被绑定过，无法同时绑定多个同类型账户
				    if(logger.isErrorEnabled()){
		                logger.error("用户["+user.getId()+"]已经被绑定过第三方账户["+getThirdpartyName(tokenInfo.getThirdpartyType())+"]，无法同时绑定多个同类型账户");
		            }
				    throw new DesignerException(ErrorCode.OAUTH_ALREADY_BIND);
				}
			}
		}
		throw new DesignerException(ErrorCode.OAUTH_ERROR);
	}

	@RequestMapping(value = "/oauthRegister", method = RequestMethod.POST)
	public String oauthRegister(Model model, HttpServletRequest request, String username, String nickname, String password, String repassword,
			@RequestParam(defaultValue="") String verifyCode, 
			@RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if(logger.isDebugEnabled()){
			logger.debug("用户["+username+"]使用第三方账户注册绑定");
		}
		
		String promptMessage = null;
		
		String sessionVerifyCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		
//		if (StringUtils.isNotEmpty(redirectUrl)) {
//			// 跳转地址
//			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
//		}
		
		if(logger.isDebugEnabled()){
//			logger.debug("提交注册时的redirectUrl地址: " + redirectUrl);
			logger.debug("提交注册绑定时的session验证码: "+sessionVerifyCode + ", 用户输入的验证码: " + verifyCode);
		}
		
		if(!verifyCode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
			model.addAttribute(ConstFront.LOGIN_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.SYSTEM_VERIFYCODE_ERROR));
			return "login/loginAndReg4Thirdparty";
		}
		
		
		AccessTokenInfo sessionToken = checkOAuthTokenStatus(request);
		String thirdpartyName = getThirdpartyName(sessionToken.getThirdpartyType());
		
		// 前端检查检查用户是否存在，在此不需要再次检查
		
		//验证用户名格式
		VerifyUtils.verifyUsername(username);

		// 创建用户
		User user = new User();
		user.setUsername(username.trim());
		user.setNickname(nickname.trim());
		user.setPassword(password.trim());
		Date currentTime = new Date();
		user.setCreateTime(currentTime);
		user.setUpdateTime(currentTime);
		try {
			int result = userService.save(user);
			if (result == 1) {
				sessionToken.setUserId(user.getId());
				accessTokenService.save(sessionToken);
				// 清空sessionToken
				request.getSession().removeAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN);
				// 重新加载用户
				user = userService.authUser(username, password);
				request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
				promptMessage = "注册成功, " + user.getNickname() + "，即将转入先前页面，请稍候…";
				
				if(logger.isDebugEnabled()){
                    logger.debug("用户["+username+"]使用第三方账户["+thirdpartyName+"]注册绑定成功");
                }
				
                //系统异步发送欢迎邮件
                mailService.sendWelcomeMail(username);
                
                request.setAttribute(ConstFront.REDIRECT_URL, redirectUrl);
                request.setAttribute(ConstFront.REDIRECT_PROMPT, promptMessage);
                return ResponseUtil.getForwardReirect();
			}
		} catch (Exception e) {
//			throw new DesignerException(ErrorCode.USER_ERROR);
			logger.error("oauthRegister()", e);
			
			if(logger.isErrorEnabled()){
                logger.error("用户["+username+"]使用第三方账户["+thirdpartyName+"]注册绑定失败");
            }
			model.addAttribute(ConstFront.REG_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.OAUTH_ERROR));
			model.addAttribute(ConstFront.REGISTER_ACTIVE, "REGISTER_ACTIVE");
			return "login/loginAndReg4Thirdparty";
		}
		throw new DesignerException(ErrorCode.OAUTH_ERROR);
	}

	/**
	 * oauth用户绑定
	 * 
	 * @param model
	 * @param request
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/oauthBind", method = RequestMethod.POST)
	public String oauthBind(Model model, HttpServletRequest request, String username, String password,
			@RequestParam(defaultValue="") String verifyCode,  
			@RequestParam(value = ConstFront.REDIRECT_URL, required = false) String redirectUrl) {
		if(logger.isDebugEnabled()){
			logger.debug("用户["+username+"]使用第三方账户登录绑定");
		}
		
		String sessionVerifyCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		
//		if (StringUtils.isNotEmpty(redirectUrl)) {
//			// 跳转地址
//			model.addAttribute(ConstFront.REDIRECT_URL, redirectUrl);
//		}
		
		if(logger.isDebugEnabled()){
//			logger.debug("提交注册时的redirectUrl地址: " + redirectUrl);
			logger.debug("提交登录绑定时的session验证码: "+sessionVerifyCode + ", 用户输入的验证码: " + verifyCode);
		}
		
		if(!verifyCode.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
			model.addAttribute(ConstFront.LOGIN_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.SYSTEM_VERIFYCODE_ERROR));
			return "login/loginAndReg4Thirdparty";
		}
		
		// 检查session中是否存在accessToken
		AccessTokenInfo sessionToken = checkOAuthTokenStatus(request);
		String thirdpartyName = getThirdpartyName(sessionToken.getThirdpartyType());
		
		// 加载用户
		User user = userService.authUser(username, password);
		
		if (user != null) { // 登录检查通过
			Map<Short, AccessTokenInfo> accessTokenMap = user.getAccessTokenMap();
			// 判断该用户是否被绑定过同类型账户
			boolean alreadyBind = accessTokenMap.get(sessionToken.getThirdpartyType()) != null;
			if (!alreadyBind) {// 之前未绑定过
				// 获取accessToken
				sessionToken.setUserId(user.getId());
				accessTokenService.save(sessionToken);
				accessTokenMap.put(sessionToken.getThirdpartyType(), sessionToken);
				// 绑定完毕，设置user到session中
				request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
				// 清空sessionToken
				request.getSession().removeAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN);
				
				if(logger.isDebugEnabled()){
		            logger.debug("用户["+username+"]使用第三方账户["+thirdpartyName+"]登录绑定成功");
		        }
				
//				request.setAttribute(ConstFront.REDIRECT_PROMPT, "欢迎您，" + user.getNickname() + "，即将转入首页，请稍候…");
//				return ResponseUtil.getRedirectHomeString();
				String promptMessage = "绑定成功, " + user.getNickname() + "，即将转入先前页面，请稍候…";
				request.setAttribute(ConstFront.REDIRECT_URL, redirectUrl);
                request.setAttribute(ConstFront.REDIRECT_PROMPT, promptMessage);
                return ResponseUtil.getForwardReirect();
			} else {// 账户之前已经绑定过本token，不能重复绑定
				//promptMessage = "用户已绑定过该第三方账户，不能重复绑定!";
			    if(logger.isDebugEnabled()){
	                logger.debug("用户["+username+"]已绑定过该第三方账户，不能重复绑定");
	            }
			    model.addAttribute(ConstFront.LOGIN_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.OAUTH_ALREADY_BIND));
				return "login/loginAndReg4Thirdparty";
			}
		} else { // 登录检查失败
//			throw new DesignerException(ErrorCode.USER_PASSWORD_NOT_MATCH);
			
		    if(logger.isDebugEnabled()){
                logger.debug("用户["+username+"]使用第三方账户["+thirdpartyName+"]绑定登录账户密码不匹配");
            }
		    model.addAttribute(ConstFront.LOGIN_ERROR_MESSAGE, ErrorCode.getMessage(ErrorCode.USER_PASSWORD_NOT_MATCH));
			return "login/loginAndReg4Thirdparty";
		}
	}

	/**
	 * 解绑第三方oauth用户
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@NeedAuthorize
	@RequestMapping(value = "/unbindOauth")
	public String unbindOauth(Model model, HttpServletRequest request, short thirdpartyType) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		int userId = user.getId();
		
		String thirdpartyName = getThirdpartyName(thirdpartyType);
		
		if(logger.isDebugEnabled()){
            logger.debug("用户["+userId+"]解绑第三方账户["+thirdpartyName+"]");
        }
		
		int result = accessTokenService.delete(user.getId(), thirdpartyType);
		if (result > 0) {// 删除成功
			// 同步删除session中的绑定信息
			Map<Short, AccessTokenInfo> accessTokenMap = user.getAccessTokenMap();
			accessTokenMap.remove(thirdpartyType);
			if(logger.isDebugEnabled()){
	            logger.debug("用户["+userId+"]解绑第三方账户["+thirdpartyType+"]成功");
	        }
		}
		request.setAttribute(ConstFront.REDIRECT_PROMPT, "您已成功解绑[" + thirdpartyName + "]账户，即将转入首页，请稍候…");
//		return "forward:/redirect";
		return ResponseUtil.getForwardReirect();
	}

	/**
	 * 临时测试api
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/oauthLogin")
	public String oauthLogin() throws Exception {
		return "login/loginAndReg4Thirdparty";
	}


//	/**
//	 * 同步微博api测试
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/shareout")
//	public String shareout(HttpServletRequest request, String content) throws Exception {
//		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
//
//		if (user != null) {
//			SharedContent sharedContent = new SharedContent();
//			sharedContent.setContent(content);
//			sharedContent.setAccessToken(user.getAccessTokenMap().get(IOAuthService.OAUTH_WEIBO_TYPE).getAccessToken());
//			oAuthService.shareout(sharedContent);
//		}
//		return "redirect:index";
//	}

	/**
	 * 使用oauth登录或绑定现有账户时，需检查session中的tokenInfo状态（不能为空且不其userId不能有值）
	 * 
	 * @param request
	 * @return
	 */
	private AccessTokenInfo checkOAuthTokenStatus(HttpServletRequest request) {
		AccessTokenInfo sessionToken = (AccessTokenInfo) request.getSession().getAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN);
		if (sessionToken == null) {// session中token不存在，无效请求
			// , "第三方登录信息不存在，请稍后重试!"
			throw new DesignerException(ErrorCode.OAUTH_SESSIONTOKEN_NOT_EXISTS);
		} else if (sessionToken.getUserId() != null && sessionToken.getUserId() > 0) {// 该sessionToken已被绑定到其他用户账户，无法使用其进行注册
			// , "用户已绑定过该第三方账户，不能重复绑定!"
			throw new DesignerException(ErrorCode.OAUTH_ALREADY_BIND);
		}
		return sessionToken;
	}
	
	
	private String getThirdpartyName(Short thirdpartyType){
		if(thirdpartyType.equals(IOAuthService.OAUTH_WEIBO_TYPE)) {
			return "新浪微博";
		}else if(thirdpartyType.equals(IOAuthService.OAUTH_WEIBO_TYPE)) {
			return "腾讯QQ";
		}else{
			return "未知第三方平台";
		}
	}

}
