package com.lingz.workflow.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    //创建日志对象
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "/login";
    }


    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(String username, String password) {
        ModelAndView model = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }
        catch (UnknownAccountException unknownAccountException) {
            model.addObject("msg", "未知用户名");
        }
        catch (IncorrectCredentialsException incorrectCredentialsException) {
            model.addObject("msg", "密码不正确");
        }
        catch (LockedAccountException lockedAccountException) {
            model.addObject("msg", "账号已被锁定");
        }
        catch (ExcessiveAttemptsException excessiveAttemptsException) {
            model.addObject("msg", "用户名或密码错误次数过多");
        }
        catch (AuthenticationException authenticationException) {
            model.addObject("msg", "用户名或密码不正确");
        }
        logger.info("登录信息：" +  model.toString());
        if(subject.isAuthenticated()) {
            model.setViewName("redirect:/index");
        }
        else {
            model.setViewName("/login");
        }
        return model;
    }
    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public String logout() {
        return "/login";
    }

}
