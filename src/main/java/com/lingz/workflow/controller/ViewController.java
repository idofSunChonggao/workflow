package com.lingz.workflow.controller;

import com.lingz.workflow.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author SunChonggao
 * @Date 2020-11-05 下午 7:05
 * @Version 1.0
 * @Description：
 */
@Controller
public class ViewController {
    @Autowired
    private MainController mainController;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return mainController.getIndexPage();
    }
    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "/error/404";
    }
    @RequestMapping(path = "/apply", method = RequestMethod.GET)
    public String getApplyPage(Model  model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "/apply";
    }
}
