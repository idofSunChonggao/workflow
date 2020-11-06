package com.lingz.workflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author SunChonggao
 * @Date 2020-11-04 下午 9:14
 * @Version 1.0
 * @Description：
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private int id;
    private int userId;
    private String userNickname;
    private String subject;
    private String content;
    private String applyTime;
    private int status;
    private String instanceId;
    private int processId;
}
