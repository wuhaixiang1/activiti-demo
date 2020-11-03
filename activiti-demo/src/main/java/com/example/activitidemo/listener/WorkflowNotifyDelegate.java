package com.example.activitidemo.listener;

import com.example.activitidemo.utils.SpringContextHolder;
import lombok.Data;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.delegate.ActivityBehavior;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *  功能名称
 * </pre>
 *
 * @author wuhx29@meicloud.com
 * @version 1.00.00
 *
 * <pre>
 *  修改记录
 *  修改后版本:
 *  修改人:
 *  修改日期: 2020/10/31 14:15
 *  修改内容:
 * </pre>
 */
//@Component("workflowNotifyDelegate")
//public class WorkflowNotifyDelegate implements JavaDelegate {
//    @Override
//    public void execute(DelegateExecution delegateExecution) {
//
//    }
//}继承那个类都可以ActivityBehavior
@Data
@Component("workflowNotifyDelegate")
public class WorkflowNotifyDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("进入方法服务任务方法");
        System.out.println(delegateExecution.getCurrentActivityId());
        FlowElement currentFlowElement = delegateExecution.getCurrentFlowElement();
        System.out.println(currentFlowElement.getName() + "123456789");
//        SpringContextHolder.getBean(RuntimeService.class).setVariable(delegateExecution.getId(),"youName", "老王");
        delegateExecution.setVariable("youName", "老五");
        System.out.println("出去方法服务任务方法");

    }
}
