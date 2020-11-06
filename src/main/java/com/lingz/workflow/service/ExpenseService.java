package com.lingz.workflow.service;

import com.lingz.workflow.dao.ExpenseDao;
import com.lingz.workflow.dao.ProcessDao;
import com.lingz.workflow.dao.UserDao;
import com.lingz.workflow.entity.Expense;
import com.lingz.workflow.entity.Process;
import com.lingz.workflow.entity.User;
import com.lingz.workflow.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Author SunChonggao
 * @Date 2020-11-05 上午 11:18
 * @Version 1.0
 * @Description：处理报销流程所有业务
 */
@Service
public class ExpenseService implements Constants {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);
    private UserDao userDao;
    private ExpenseDao expenseDao;
    private ProcessDao processDao;

    @Autowired
    public ExpenseService(UserDao userDao, ExpenseDao expenseDao, ProcessDao processDao) {
        this.userDao = userDao;
        this.expenseDao = expenseDao;
        this.processDao = processDao;
    }
    /**
     * 功能：申请报销
     * 角色：员工
     * processId：1 -> 2
     * @return 实例Id
     */
    public String applyExpense(int userId, String subject, String content) {
        Expense expense = new Expense();
        expense.setUserId(userId);
        expense.setSubject(subject);
        expense.setContent(content);
        //记录申请时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        expense.setApplyTime(date);
        expense.setProcessId(PROCESS_ID_CHECK_MANAGER);//设置下一个流程为经理审核
        //生成UUID作为任务实例ID
        String instanceId = UUID.randomUUID().toString().replace("-", "").toLowerCase(); // toLowerCase将字符串转换为小写
        expense.setInstanceId(instanceId);
        //设置给任务状态为审核中
        expense.setStatus(STATE_WAIT_CHECK);
        expenseDao.insertExpense(expense);
        User user = userDao.selectUserById(userId);
        logger.info("已创建新流程，流程实例ID为" + instanceId + ";创建人为" + user.getUserName() + ";");
        return instanceId;
    }

    /**
     * 功能：部门经理审核
     * 角色：部门经理
     * processId:2 -> 3
     * @param instanceId
     * @param isPassed
     * @return 报销单id
     */
    public int checkExpenseByManager(String instanceId, boolean isPassed) {
        Expense expense = expenseDao.selectExpenseByInstanceId(instanceId);
        int id = expense.getId();
        if(isPassed) {
            expenseDao.updateStatus(id, STATE_PASS_MANAGER);
            expenseDao.updateProcessId(id, PROCESS_ID_CHECK_BOSS);
            Process process = processDao.selectProcessById(PROCESS_ID_CHECK_BOSS);
            logger.info("流程" + instanceId + "已通过部门经理审核，下一个活动为" + process.getTaskName() + ";");
        }
        else {
            expenseDao.updateStatus(id, STATE_REJECT_MANAGER);
            expenseDao.updateProcessId(id, PROCESS_ID_END);
            Process process = processDao.selectProcessById(PROCESS_ID_END);
            logger.info("流程" + instanceId + "未通过部门经理审核，下一个活动为" + process.getTaskName() + ";");
        }
        return expense.getId();
    }
    /**
     * 功能：总经理审核
     * 角色：总经理
     * processId:3 -> 4
     * @param instanceId
     * @param isPassed
     * @return 报销单id
     */
    public int checkExpenseByBoss(String instanceId, boolean isPassed) {
        Expense expense = expenseDao.selectExpenseByInstanceId(instanceId);
        int id = expense.getId();
        Process process = processDao.selectProcessById(PROCESS_ID_END);
        if(isPassed) {
            expenseDao.updateStatus(id, STATE_PASS_BOSS);
            logger.info("流程" + instanceId + "已通过总经理审核，" + process.getTaskName() + ";");
        }
        else {
            expenseDao.updateStatus(id, STATE_REJECT_BOSS);
            logger.info("流程" + instanceId + "未通过总经理审核，" + process.getTaskName() + ";");
        }
        expenseDao.updateProcessId(id, PROCESS_ID_END);
        return expense.getId();
    }
    /**
     * 查询该用户的所有任务
     * @param userId
     * @return
     */
    public List<Expense> listTaskOfUser(int userId) {
        //查询该用户的角色
        User user = userDao.selectUserById(userId);
        int role = user.getRole();
        //根据用户角色查询对应的processId
        Process process = processDao.selectProcessById(role);
        int processId = process.getId();
        //根据processId查询对应的报销任务
        return expenseDao.listExpenseByProcessId(processId);
    }

    public List<Expense> listExpenseOfEmployee(int userId) {
        User user = userDao.selectUserById(userId);
        if(user.getRole() != ROLE_EMPLOYEE) {
            logger.warn(user.getUserName() + "不是员工，无法查询所提交的报销单");
            return new ArrayList<>();
        }
        return expenseDao.listExpenseByUserId(user.getId());
    }
    public List<Expense> listExpenseOfAll() {
        return expenseDao.listExpenseOfAll();
    }

}
