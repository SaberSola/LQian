package com.zl.lqian.controller;

import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class ActivitiDemoController {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private ProcessEngine processEngine;

	/**
	 * 简单示例
	 */
	@RequestMapping("/d2")
	public void firstDemo() {

		//根据bpmn文件部署流程
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("demo2.bpmn").deploy();
		//获取流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		//启动流程定义，返回流程实例
		ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
		String processId = pi.getId();
		System.out.println("流程创建成功，当前流程实例ID："+processId);
		
		Task task=taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第一次执行前，任务名称："+task.getName());
		taskService.complete(task.getId());

		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第二次执行前，任务名称："+task.getName());
		taskService.complete(task.getId());

		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("task为null，任务执行完毕："+task);
	}
	/**
	 * 带退回的示例
	 */
	@RequestMapping("/d1")
	public void startActivityDemo() {

		
		//根据bpmn文件部署流程
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("demo1.bpmn").deploy();
		//获取流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		//启动流程定义，返回流程实例
		ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
		String processId = pi.getId();
		System.out.println("流程实例ID："+processId);
		Task task=taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第一次执行前，任务名称："+task.getName());
		taskService.complete(task.getId());

		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		taskService.setVariable(task.getId(), "flag", false);//退回
		System.out.println("第二次执行前，任务名称："+task.getName());
		taskService.complete(task.getId());

		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第三次执行前，任务名称："+task.getName());
		taskService.complete(task.getId());

		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第四次执行前，任务名称："+task.getName());
		taskService.setVariable(task.getId(), "flag", true);//通过
		taskService.complete(task.getId());
		
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("task为null，任务执行完毕："+task);
	}
	
	@RequestMapping("/d3")
	public void startActivityDemo2() {
		
		//根据bpmn文件部署流程
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("demo1.bpmn").deploy();
		//获取流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		//启动流程定义，返回流程实例
		ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
		String processId = pi.getId();
		System.out.println("流程实例ID："+processId);
		
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processId).taskCandidateUser("a").list();
		if (tasks != null && tasks.size()>0) {
            for(Task task : tasks){
                System.out.println("task id="+task.getId());
                System.out.println("name="+task.getName());
                System.out.println("assinee="+task.getAssignee());
                System.out.println("executionId="+task.getExecutionId());
                System.out.println("=====================================");
            }
        }
		
		tasks = taskService.createTaskQuery().processInstanceId(processId).taskCandidateUser("c").list();
		if (tasks != null && tasks.size()>0) {
			for(Task task : tasks){
				System.out.println("task id="+task.getId());
				System.out.println("name="+task.getName());
				System.out.println("assinee="+task.getAssignee());
				System.out.println("executionId="+task.getExecutionId());
				System.out.println("=====================================");
			}
		}
		
		tasks = taskService.createTaskQuery().processInstanceId(processId).taskCandidateUser("b").list();
		if (tasks != null && tasks.size()>0) {
            for(Task task : tasks){
                System.out.println("task id="+task.getId());
                System.out.println("name="+task.getName());
                System.out.println("assinee="+task.getAssignee());
                System.out.println("executionId="+task.getExecutionId());
                System.out.println("=====================================");
                
                System.out.println("第一次执行前，任务名称："+task.getName());
        		taskService.complete(task.getId());
        		
        		System.out.println("第二次执行前，任务名称："+task.getName());
        		taskService.complete(task.getId());
        		
        		System.out.println("第三次执行前，任务名称："+task);
            }
        }
		
	}

	@RequestMapping("/add")
	public void addIdentity() {
		for (int i = 0; i < 10; i++) {
			Group g = identityService.newGroup(String.valueOf(i));
			g.setName("GROUP_"+i);
			g.setType("TYPE_"+i);
			identityService.saveGroup(g);
		}
	}

	@RequestMapping("/list")
	public String listQueryList() {
		List<Group> list = identityService.createNativeGroupQuery().sql("select * from act_id_group where name = #{name}").parameter("name", "Group_2").list();
		for(Group g : list ) {
			System.out.println("id:"+g.getId()+"-----name:"+g.getName());
		}
		return list.toString();
	}

	//后台创建流程设置内容
	public void addProcess() {
		DeploymentBuilder builder = repositoryService.createDeployment();
		builder.addBpmnModel("My Process", createProcessModel());
	}

	public static BpmnModel createProcessModel() {
		//创建model对象
		BpmnModel model = new BpmnModel();
		//创建流程实例
		Process process = new Process();
		process.setId("myProcess");
		process.setName("My Process");
		model.addProcess(process);
		//流程开始事件
		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEvent");
		process.addFlowElement(startEvent);
		//任务事件
		UserTask userTask = new UserTask();
		userTask.setId("userTask");
		userTask.setName("User Task");
		process.addFlowElement(userTask);
		//结束时间
		EndEvent endEvent = new EndEvent();
		endEvent.setId("endEvent");
		process.addFlowElement(endEvent);
		//流程连接线
		process.addFlowElement(new SequenceFlow("startEvent","userTask"));
		process.addFlowElement(new SequenceFlow("userTask","endEvent"));

		return model;
	}

	//根据已部署的流程生成图片
	@RequestMapping("/img")
	public void imgByBpmn() throws Exception {
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("demo1.bpmn").deploy();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		String id = processDefinition.getId();
		//根据流程定义获取输入流
		InputStream is = repositoryService.getProcessDiagram(id);
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

	//流程中设置定时任务执行器
	@RequestMapping("/timerEvent")
	public void timerEvent() throws Exception {
		//根据bpmn文件部署流程
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("timerTask.bpmn").deploy();
		//获取流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		//启动流程定义，返回流程实例
		ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
		
		System.out.println(pi.getId());
	}
	
	//流程中设置定时任务执行器
	@RequestMapping("/d4")
	public void d4(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		//根据bpmn文件部署流程
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("demo4.bpmn").deploy();
		//获取流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		//启动流程定义，返回流程实例
		ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
		String processId = pi.getId();
		System.out.println("流程创建成功，当前流程实例ID："+processId);
		
		
		//指派给另外一个人
//		processEngine.getTaskService().setAssignee("", "");
//	    System.out.println("指派成功");
	        
		Task task=taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第一次执行前(指定办理人为："+userId+")，任务名称："+task.getName());
		taskService.setVariable(task.getId(),"inputName", "");//指派为空
		taskService.complete(task.getId());
		List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("demo4").taskCandidateOrAssigned(userId).list();
		if (tasks != null && tasks.size()>0) {
            for(Task t : tasks){
                System.out.println("task id="+t.getId());
                System.out.println("name="+t.getName());
                System.out.println("assinee="+t.getAssignee());
                System.out.println("executionId="+t.getExecutionId());
                taskService.complete(task.getId());
                System.out.println("=====================================");
            }
        }
		
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("task为null，任务执行完毕："+task);
	}
	
	//查找指定人办理的任务
		@RequestMapping("/d5")
		public void d5(HttpServletRequest request) {
			
			List<Task> tasks = taskService.createTaskQuery().processInstanceId("190027").taskCandidateUser("a").list();
			
			System.out.println("当前任务："+tasks.toString());
		}
}
