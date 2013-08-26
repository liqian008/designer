package com.bruce.designer.front.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.bruce.designer.bean.User;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.oauth.IThirdpartyService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class OAuthController {

//    @Autowired
//    private UserService userService;
	@Autowired
	IThirdpartyService thirdpartyService;
	
    private static final Logger logger = LoggerFactory
            .getLogger(OAuthController.class);


    @RequestMapping(value = "/wbOauth")
    public String wbOauth(Model model, HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        if(StringUtils.isBlank(code)){
        	//weibo验证失败
        }else{
        	User user = thirdpartyService.getUserByWeiboCode(code);
        	if (user != null) {
                request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
            }
        }
        //跳转到注册、绑定页面
    	return "registerThirdparty";
    }
    
    @RequestMapping(value = "/wbRegister")
    public String doLogin(Model model, HttpServletRequest request) throws Exception {
    	return "registerThirdparty";
    }
    
}
