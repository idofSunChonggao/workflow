package com.lingz.workflow;

import com.lingz.workflow.service.ExpenseService;
import com.lingz.workflow.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author SunChonggao
 * @Date 2020-11-05 下午 4:30
 * @Version 1.0
 * @Description：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WorkflowApplication.class)
public class ServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ExpenseService expenseService;

    @Test
    public void testFind() {
        //System.out.println(expenseService.listExpenseOfEmployee(1));
        System.out.println(expenseService.checkExpenseByManager("9ee0fedb5cf145bb9fb7354986cfa00f",true));
    }
}
