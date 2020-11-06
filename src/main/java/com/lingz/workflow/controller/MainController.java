package com.lingz.workflow.controller;

import com.lingz.workflow.entity.Expense;
import com.lingz.workflow.entity.User;
import com.lingz.workflow.service.ExpenseService;
import com.lingz.workflow.service.UserService;
import com.lingz.workflow.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author SunChonggao
 * @Date 2020-11-05 下午 5:10
 * @Version 1.0
 * @Description：
 */
@Controller
public class MainController implements Constants {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private UserService userService;
    private ExpenseService expenseService;

    @Autowired
    public MainController(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public ModelAndView getIndexPage() {
        List<Expense> expenseList;
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        modelAndView.addObject("user", user);
        // 区分用户是员工还是领导
        if(user.getRole() == ROLE_EMPLOYEE){
            //是员工，则显示申请的报销单
            expenseList = expenseService.listExpenseOfEmployee(user.getId());
        }
        else {
            //是领导， 显示所有员工的报销单
            expenseList = expenseService.listExpenseOfAll();
        }
        logger.info(user.getUserName() + "已登录，当前有" + expenseList.size() + "条报销单据");
        modelAndView.addObject("expenses", expenseList);
        modelAndView.setViewName("/index");
        return modelAndView;
    }
    @RequestMapping(path = "/apply", method = RequestMethod.POST)
    public ModelAndView applyExpense(@RequestParam(value = "subject") String subject,
                                     @RequestParam(value = "content") String content) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String instanceId = expenseService.applyExpense(user.getId(), subject, content);
        modelAndView.addObject("msg","申请成功，您的流程ID为" + instanceId);
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }
    @RequestMapping(path = "/check", method = RequestMethod.GET)
    public ModelAndView getCheckPage() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Expense> expenseList = expenseService.listTaskOfUser(user.getId());
        for(Expense expense: expenseList) {
            User applyUser = userService.getUserById(expense.getUserId());
            expense.setUserNickname(applyUser.getNickname());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("expenses", expenseList);
        modelAndView.setViewName("/check");
        return modelAndView;
    }
    @RequestMapping(path = "/passOrNot", method = RequestMethod.GET)
    public String checkByManager(@RequestParam(value = "instanceId") String instanceId,
                                 @RequestParam(value = "isPassed") boolean isPassed) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user.getRole() == ROLE_MANAGER)
            expenseService.checkExpenseByManager(instanceId, isPassed);
        else if(user.getRole() == ROLE_BOSS)
            expenseService.checkExpenseByBoss(instanceId, isPassed);
        else
            logger.warn("用户身份不是领导，没有审核权限");
        return "redirect:/check";
    }
}
