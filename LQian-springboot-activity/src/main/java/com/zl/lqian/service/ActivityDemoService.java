package com.zl.lqian.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.task.Task;

import java.util.List;

public interface ActivityDemoService {


    /**
     * 启动流程
     * @param bizId 业务id
     */
    public void startProcesses(String bizId);
    /**
     *
     * <p>描述: 根据用户id查询待办任务列表</p>
     */
    public List<Task> findTasksByUserId(String userId);

    /**
     *
     * <p>描述:任务审批 	（通过/拒接） </p>
     * @param taskId 任务id
     * @param userId 用户id
     * @param result false OR true
     */
    public void completeTask(String taskId,String userId,String result);
    /**
     * 更改业务流程状态#{ActivityDemoServiceImpl.updateBizStatus(execution,"tj")}
     * @param execution
     * @param status
     */
    public void updateBizStatus(DelegateExecution execution, String status);

    /**
     *
     * <p>描述:  生成流程图
     * 首先启动流程，获取processInstanceId，替换即可生成</p>
     */
    public void queryProImg(String processInstanceId) throws Exception;

}
