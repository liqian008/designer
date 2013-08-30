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
    private IOAuthService oAuthService;
    @Autowired
    private IAccessTokenService accessTokenService;

    private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);

    @RequestMapping(value = "/wbOauth")
    public String wbOauth(Model model, HttpServletRequest request)throws Exception {
        String code = request.getParameter("code"); 
        if (StringUtils.isBlank(code)) {// 回调错误

        } else {// 回调正常
            AccessTokenInfo tokenInfo = oAuthService.loadTokenByCode(code, IOAuthService.OAUTH_WEIBO_TYPE);
            if (tokenInfo != null) {
                // 缓存accessToken，便于后续绑定的时候使用
                request.getSession().setAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN, tokenInfo);
                // token正常
                User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
                if (user == null) {// 未登录状态
                    if (tokenInfo.getUserId()!=null && tokenInfo.getUserId()>0) {// 用户之前绑定过，可直接登录
                        user = userService.loadById(tokenInfo.getUserId());
                        // 加载并设置用户的所有token
                        List<AccessTokenInfo> accessTokenList = accessTokenService.queryByUserId(tokenInfo.getUserId());
                        user.setAccessTokenList(accessTokenList);
                        request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
                        //oauth验证成功，返回首页
                        return "redirect:/index.art";
                    } else {// 新用户，需绑定
                            // 进入注册、绑定已有账户流程
                        return "registerThirdparty";
                    }
                } else {//已登录状态
                        // 绑定现有账户流程，需检查已登录用户是否已存在该oauth类型的绑定
                    boolean alreadyBind = false;
                    if (!alreadyBind) {
                        // 尚未绑定，可以绑定
                        return "registerThirdparty";
                    } else {
                        // 已经绑定，不能重复绑定，提示出错
                        return "";
                    }
                }
            } else {
                // 无法获取token，系统错误
            }
        }
        // 跳转到注册、绑定页面
        return "";
    }

    @RequestMapping(value = "/oauthRegister", method = RequestMethod.POST)
    public String oauthRegister(Model model, HttpServletRequest request,
            String username, String nickname, String password, String repassword) {
        //检查session中是否存在accessToken
        AccessTokenInfo sessionToken = (AccessTokenInfo) request.getSession().getAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN);
        if (sessionToken == null) {//session中token不存在，无效请求
            
        }else if (sessionToken.getUserId()!=null && sessionToken.getUserId()>0) {//该sessionToken已被绑定到其他用户账户，无法使用其进行注册
            
        } else {
            // 检查用户是否存在
            boolean userExists = false;
            if(userExists){//用户已被占用，无法再次注册
                
            }else{//用户未被占用，可以使用oauth注册
                //创建用户
                User user = new User();
                user.setId(0);
                user.setUsername(username);
                user.setNickname(nickname);
                user.setPassword(password);
                Date currentTime = new Date(); 
                user.setCreateTime(currentTime);
                user.setUpdateTime(currentTime);
                int result = userService.save(user);
                
                sessionToken.setUserId(user.getId());
                accessTokenService.save(sessionToken);
                //清空sessionToken
                request.getSession().removeAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN);
                // 重新加载用户
                if (result == 1) {
                    user = userService.authUser(username, password);
                    request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
                }
            }
        }
        return "redirect:/index.art";
    }

    @RequestMapping(value = "/oauthBind", method = RequestMethod.POST)
    public String oauthBind(Model model, HttpServletRequest request, String username, String password) {
        //检查session中是否存在accessToken
        AccessTokenInfo sessionToken = (AccessTokenInfo) request.getSession().getAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN);
        if (sessionToken == null) {//session中token不存在，无效请求
            
        }else if (sessionToken.getUserId()!=null&&sessionToken.getUserId()>0) {//该sessionToken已被绑定到其他账户，无法使用其进行注册
            
        } else {
            // 加载用户
            User user = userService.authUser(username, password);
            if (user != null) {
                List<AccessTokenInfo> tokenList = user.getAccessTokenList();
                // 判断该用户是否被绑定过同类型账户
                boolean alreadyBind = false;
                if (!alreadyBind) {// 之前未绑定过
                    // 获取accessToken
                    sessionToken.setUserId(user.getId());
                    accessTokenService.save(sessionToken);
                    tokenList.add(sessionToken);
                    //绑定完毕，设置user到session中
                    request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
                    //清空sessionToken
                    request.getSession().removeAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN);
                } else {// 账户之前已经绑定过本token，不能重复绑定
                    
                }
            } else {
                // 用户认证失败
            }
        }
        return "redirect:/index.art";
    }

    /**
     * 解绑第三方
     * 
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unbindOauth")
    public String unbindOauth(Model model, HttpServletRequest request, String thirdpartyType) throws Exception {
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int result = accessTokenService.delete(user.getId(), thirdpartyType);
        if(result>0){// 删除成功
            // 同步删除session中的绑定信息
            List<AccessTokenInfo> tokenList = user.getAccessTokenList();
            if (tokenList != null && tokenList.size() > 0) {
                // 遍历查找相应的第三方绑定并删除
                for (AccessTokenInfo userToken : tokenList) {
                    if (thirdpartyType.equals(userToken.getThirdpartyType())) {
                        tokenList.remove(userToken);
                        break;
                    }
                }
            }
        }
        return "";
    }

    /**
     * 临时测试api
     * 
     * @return
     */
    @RequestMapping(value = "/oauthLogin")
    public String oauthLogin() {
        return "registerThirdparty";
    }

    
    /**
     * 使用oauth登录或绑定现有账户时，需检查session中的tokenInfo状态（不能为空且不其userId不能有值）
     * @param request
     * @return
     */
    private boolean checkOAuthLoginStatus(HttpServletRequest request){
        AccessTokenInfo sessionToken = (AccessTokenInfo) request.getSession().getAttribute(ConstFront.TEMPLATE_ACCESS_TOKEN);
        if (sessionToken == null) {//session中token不存在，无效请求
            return false;
        }else if (sessionToken.getUserId()!=null && sessionToken.getUserId()>0) {//该sessionToken已被绑定到其他用户账户，无法使用其进行注册
            return false;
        }
        return true; 
    }
    
}
