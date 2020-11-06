package com.lingz.workflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author SunChonggao
 * @Date 2020/3/24 14:44
 * @Version 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    private int id;
    private String userName;
    private String password;
    private String nickname;
    private int role;
    private String headImage;
}
