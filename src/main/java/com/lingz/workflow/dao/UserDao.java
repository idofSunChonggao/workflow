package com.lingz.workflow.dao;

import com.lingz.workflow.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author SunChonggao
 * @Date 2020/3/24 17:47
 * @Version 1.0
 * @Description:
 */
@Mapper
@Repository
public interface UserDao {
  User selectUserById(int id);
  User selectUserByUsername(String username);
}
