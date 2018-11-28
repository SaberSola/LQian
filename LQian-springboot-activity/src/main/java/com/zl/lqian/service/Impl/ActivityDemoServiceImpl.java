package com.zl.lqian.service.Impl;

import com.zl.lqian.service.ActivityDemoService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class ActivityDemoServiceImpl implements ActivityDemoService {


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngineConfigurationImpl processEngineConfiguration;



    /**
     * 启动流程
     * @param bizId 业务id
     */
    public void startProcesses(String bizId) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("demo5", bizId);//流程图id，业务表id
        System.out.println("流程启动成功，流程id:"+pi.getId());
    }

    /**
     *
     * <p>描述: 根据用户id查询待办任务列表</p>
     * @author 范相如
     * @date 2018年2月25日
     */
    public List<Task> findTasksByUserId(String userId) {
        List<Task> resultTask = taskService.createTaskQuery().processDefinitionKey("demo5").taskCandidateOrAssigned(userId).list();
        return resultTask;
    }

    /**
     *
     * <p>描述:任务审批 	（通过/拒接） </p>
     * @author 范相如
     * @date 2018年2月25日
     * @param taskId 任务id
     * @param userId 用户id
     * @param result false OR true
     */
    public void completeTask(String taskId,String userId,String result) {
        //获取流程实例
        taskService.claim(taskId, userId);

        Map<String,Object> vars = new HashMap<>();
        vars.put("sign", "true");
        taskService.complete(taskId, vars);
    }

    /**
     * 更改业务流程状态#{ActivityDemoServiceImpl.updateBizStatus(execution,"tj")}
     * @param execution
     * @param status
     */
    public void updateBizStatus(DelegateExecution execution,String status) {
        String bizId = execution.getProcessInstanceBusinessKey();
        //根据业务id自行处理业务表
        System.out.println("业务表["+bizId+"]状态更改成功，状态更改为："+status);
    }


    //流程节点权限用户列表${ActivityDemoServiceImpl.findUsers(execution,sign)}
    public List<String> findUsersForSL(DelegateExecution execution){
        return Arrays.asList("sly1","sly2");
    }

    //流程节点权限用户列表${ActivityDemoServiceImpl.findUsers(execution,sign)}
    public List<String> findUsersForSP(DelegateExecution execution){
        return Arrays.asList("spy1","uspy2");
    }

    /**
     *
     * <p>描述:  生成流程图
     * 首先启动流程，获取processInstanceId，替换即可生成</p>
     * @author 范相如
     * @date 2018年2月25日
     * @param processInstanceId
     * @throws Exception
     */
    public void queryProImg(String processInstanceId) throws Exception {
        //获取历史流程实例
        HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        //根据流程定义获取输入流
        InputStream is = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());
        BufferedImage bi = ImageIO.read(is);
        File file = new File("demo2.png");
        if(!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ImageIO.write(bi, "png", fos);
        fos.close();
        is.close();
        System.out.println("图片生成成功");

        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("userId").list();
        for(Task t : tasks) {
            System.out.println(t.getName());
        }
    }
}
