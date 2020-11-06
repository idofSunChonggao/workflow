package com.lingz.workflow.utils;

/**
 * @Author SunChonggao
 * @Date 2020-11-05 下午 3:37
 * @Version 1.0
 * @Description：
 */
public interface Constants {
    /**
     * 提交申请，等待审核
     */
    int STATE_WAIT_CHECK = 0;
    /**
     * 经理审核通过
     */
    int STATE_PASS_MANAGER = 1;
    /**
     * 经理驳回
     */
    int STATE_REJECT_MANAGER = 2;
    /**
     * 总经理审核通过
     */
    int STATE_PASS_BOSS = 3;
    /**
     * 总经理驳回
     */
    int STATE_REJECT_BOSS = 4;
    /**
     * 流程进度Id：申请
     */
    int PROCESS_ID_APPLY = 1;
    /**
     * 流程进度Id：经理审批
     */
    int PROCESS_ID_CHECK_MANAGER = 2;
    /**
     * 流程进度Id：总经理审批
     */
    int PROCESS_ID_CHECK_BOSS = 3;
    /**
     * 流程进度Id：总经理审批
     */
    int PROCESS_ID_END = 4;
    /**
     * 用户角色：员工
     */
    int ROLE_EMPLOYEE = 1;
    /**
     * 用户角色：经理
     */
    int ROLE_MANAGER = 2;
    /**
     * 用户角色：总经理
     */
    int ROLE_BOSS = 3;

}
