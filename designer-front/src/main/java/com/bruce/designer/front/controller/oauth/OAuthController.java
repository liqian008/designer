package com.bruce.designer.front.controller.oauth;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.bean.User;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.UserService;
import com.bruce.designer.service.oauth.IAccessTokenService;
import com.bruce.designer.service.oauth.IOAuthService;

@Controller
public class OAuthController {

    @Autowired
    private UserService userService;
    @Autowired
    IOAuthService oauthService;
    @Autowired
    IAccessTokenService accessTokenService;

    private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);
    
    @RequestMapping(value = "/wbOauth")
    public String wbOauth(Model model, HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        if (StringUtils.isBlank(code)) {//回调错误
            
        } else {//回调正常
        	AccessTokenInfo tokenInfo = oauthService.loadTokenByWeiboCode(code);
        	if(tokenInfo!=null){
        		//缓存accessToken，便于后续绑定的时候使用
        		request.getSession().setAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN, tokenInfo);
        		//token正常
        	    User user = (User)request.getSession().getAttribute(ConstFront.CURRENT_USER);
  	          	if (user == null) {//用户未登录
  	          		//进入注册、绑定已有账户流程
  	          		return "registerThirdparty";
  	          	}else{//用户已登录
  	          		//绑定现有账户流程，需检查已登录用户是否已存在该oauth类型的绑定
  	          		boolean alreadyBind = false;
  	          		if(!alreadyBind){
  	          			//尚未绑定，可以绑定
  	          			return "registerThirdparty";
  	          		}else{
  	          			//已经绑定，不能重复绑定，提示出错
  	          			return "";
  	          		}
  	          	}
        	}else{
        		//无法获取token，系统错误
        	}
        }
        // 跳转到注册、绑定页面
        return "";
    }
    
    @RequestMapping(value = "/oauthRegister", method = RequestMethod.POST)
    public String oauthRegister(Model model, HttpServletRequest request,
            String username, String nickname, String password, String repassword) {
        //创建用户
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);
        Date currentTime = new Date();
        user.setCreateTime(currentTime);
        user.setUpdateTime(currentTime);
        int result = userService.save(user);
        //创建accessToken
        AccessTokenInfo accessToken = new AccessTokenInfo();
        accessToken.setUserId(user.getId());
        accessTokenService.save(accessToken);
        
        //重新加载用户
        if (result == 1) {
            user = userService.authUser(username, password);
            request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
        }
        return "redirect:/index.art";
    }
    
    @RequestMapping(value = "/oauthBind", method = RequestMethod.POST)
    public String oauthBind(Model model, HttpServletRequest request, String username, String password) {
        //创建用户
        User user = userService.authUser(username, password);
        if (user != null) {
            List<AccessTokenInfo> tokenList = user.getAccessTokenList();
            // 判断该用户是否被绑定过同类型账户
            boolean alreadyBind = false;
            if (!alreadyBind) {//之前未绑定过
                //获取accessToken
                AccessTokenInfo accessToken = new AccessTokenInfo();
                accessToken.setUserId(user.getId());
                accessTokenService.save(accessToken);
                tokenList.add(accessToken);
            }else{//绑定过
                //不能重复绑定
            }
        }else{
            //用户认证失败
        }
        return "redirect:/index.art";
    }
    
    /**
     * 绑定第三方
     * @param model
     * @return
     * @throws Exception
     */
    public String bindThirdparty(Model model, HttpServletRequest request, String code) throws Exception {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        //查询用户是否已经绑定过该第三方账户
        boolean alreadyBind = false;
        if(!alreadyBind){
            //绑定
            AccessTokenInfo accessToken = new AccessTokenInfo();
            int result = accessTokenService.save(accessToken);
        }
        return "redirec";
    }

    /**
     * 解绑第三方
     * @param model
     * @return
     * @throws Exception
     */
    public String unbindThirdparty(Model model, HttpServletRequest request, String thirdpartyType) throws Exception {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        accessTokenService.delete(user.getId(), thirdpartyType);
        //同步删除session中的绑定信息
        List<AccessTokenInfo> tokenList = user.getAccessTokenList();
        if(tokenList!=null&&tokenList.size()>0){
            //遍历查找相应的第三方绑定并删除
            for(AccessTokenInfo userToken: tokenList){
                if(thirdpartyType.equals(userToken.getThirdpartyType())){
                    tokenList.remove(userToken);
                    break;
                }
            }
        }
        return "";
    }
    
    @RequestMapping(value="/oauthLogin")
    public String oauthLogin(){
        return "registerThirdparty";
    }
    
    
}
