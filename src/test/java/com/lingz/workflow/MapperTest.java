package com.lingz.workflow;

import com.lingz.workflow.dao.ExpenseDao;
import com.lingz.workflow.dao.ProcessDao;
import com.lingz.workflow.dao.UserDao;
import com.lingz.workflow.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;

/**
 * @Author SunChonggao
 * @Date 2020/3/24 18:04
 * @Version 1.0
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WorkflowApplication.class)
public class MapperTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProcessDao processDao;
    @Autowired
    private ExpenseDao expenseDao;
    @Test
    public void mapperTest(){
        System.out.println(userDao.selectUserByUsername("boss"));
        //System.out.println(processDao.selectProcessById(1));
//        Expense expense = new Expense();
//        expense.setSubject("test3");
//        expense.setUserId(1);
//        expense.setProcessId(3);
//        expenseDao.insertExpense(expense);
        //System.out.println(expenseDao.updateStatus(2, 2));
        //System.out.println(expenseDao.updateProcessId(2, 3));
        //System.out.println(expenseDao.selectExpenseByInstanceId("sfasdfasfasdfa"));
        //System.out.println(expenseDao.listExpenseOfAll());
    }
}
