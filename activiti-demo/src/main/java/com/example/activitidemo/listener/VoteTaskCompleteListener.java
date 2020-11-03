package com.example.activitidemo.listener;

import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Map;

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
 *  修改日期: 2020/11/2 14:38
 *  修改内容:
 * </pre>
 */
@Component("voteTaskCompleteListener")
public class VoteTaskCompleteListener implements TaskListener {
    public void execute(DelegateExecution delegateExecution) {

        FlowElement currentFlowElement = delegateExecution.getCurrentFlowElement();
        Map<String, Object> variables = delegateExecution.getVariables();

        int num = (Integer)variables.get("num");
        System.out.println(currentFlowElement.getName() + "====num1===" + num);
        num++;
        System.out.println(currentFlowElement.getName() + "====num2===" + num);
        delegateExecution.setVariable("num", num);
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        int num = (Integer)delegateTask.getVariable("num");
        System.out.println(delegateTask.getName() + "====num1===" + num);
        num++;
        System.out.println(delegateTask.getName() + "====num2===" + num);
        delegateTask.setVariable("num", num);
//        delegateTask.getExecution().setVariable("voteUserTask_Countor_", counter);
    }
}
