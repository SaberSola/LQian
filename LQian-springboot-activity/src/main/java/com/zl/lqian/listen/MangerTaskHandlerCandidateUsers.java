package com.zl.lqian.listen;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.Arrays;


public class MangerTaskHandlerCandidateUsers implements TaskListener {

	private static final long serialVersionUID = -8328518556439258389L;  

	// 指定任务的办理人  
	public void notify(DelegateTask delegateTask) {
		String[] empLoyees = {"a","b","v"};
		System.out.println("指定流程节点办理人：" + empLoyees.length);
		delegateTask.addCandidateGroups(Arrays.asList(empLoyees));//完成多处理人的指定
	}  

}
