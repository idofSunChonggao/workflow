package com.lingz.workflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author SunChonggao
 * @Date 2020-11-04 下午 9:12
 * @Version 1.0
 * @Description：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Process {
    private int id;
    private String taskName;
    private String role;

}
