package com.bruce.designer.front.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.bean.User;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.AlbumService;
import com.bruce.designer.service.CommentService;
import com.bruce.designer.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SystemController {

    @Autowired
    private UserService userService;
//    @Autowired
//    private AlbumService albumService;
//    @Autowired
//    private CommentService commentService;

    private static final Logger logger = LoggerFactory
            .getLogger(SystemController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(Model model, HttpServletRequest request, String username, String password) {
        User user = userService.authUser(username, password);
        if (user != null) {
            request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
            return "redirect:/index.art";
        }
        return "redirect:/login.art";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(Model model, HttpServletRequest request, String username, String nickname, String password, String repassword) {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);
        Date currentTime = new Date();
        user.setCreateTime(currentTime);
        user.setUpdateTime(currentTime);
        int result = userService.save(user);
        if (result == 1) {
            user = userService.authUser(username, password);
            request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
            request.setAttribute(ConstFront.REDIRECT_PROMPT, "您好,"+nickname+"，您已成功注册，现在将转入首页，请稍候…");
            return "forward:/redirect.art";
        }
        return "redirect:/login.art";
    }

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(ConstFront.CURRENT_USER);
        request.setAttribute(ConstFront.REDIRECT_PROMPT, "您已成功注销登录，现在将以游客身份转入首页，请稍候…");
        return "forward:/redirect.art";
    }
    
    /**
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/redirect")
    public String redirect(HttpServletRequest request) {
        return "redirect";
    }
    
}
