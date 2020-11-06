package com.lingz.workflow.dao;

import com.lingz.workflow.entity.Process;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author SunChonggao
 * @Date 2020-11-04 下午 9:27
 * @Version 1.0
 * @Description：
 */
@Mapper
@Repository
public interface ProcessDao {
    Process selectProcessById(int id);
}
