package com.lingz.workflow.service;

import com.lingz.workflow.dao.UserDao;
import com.lingz.workflow.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author SunChonggao
 * @Date 2020-11-05 上午 10:45
 * @Version 1.0
 * @Description：处理用户角色业务
 */
@Service
public class UserService {
    private UserDao userDao;
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    public User getUserByName(String username) {
        return userDao.selectUserByUsername(username);
    }
    public User getUserById(int id) {
        return userDao.selectUserById(id);
    }


}
