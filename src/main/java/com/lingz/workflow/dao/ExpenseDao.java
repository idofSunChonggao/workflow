package com.lingz.workflow.dao;

import com.lingz.workflow.entity.Expense;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author SunChonggao
 * @Date 2020-11-04 下午 9:41
 * @Version 1.0
 * @Description：
 */
@Mapper
@Repository
public interface ExpenseDao {
    Expense selectExpenseById(int id);
    Expense selectExpenseByInstanceId(String instanceId);
    List<Expense> listExpenseByProcessId(int processId);
    List<Expense> listExpenseByUserId(int userId);
    List<Expense> listExpenseOfAll();
    int insertExpense(Expense expense);
    int updateStatus(@Param("id") int id, @Param("status") int status);
    int updateProcessId(@Param("id") int id, @Param("processId") int processId);
}
