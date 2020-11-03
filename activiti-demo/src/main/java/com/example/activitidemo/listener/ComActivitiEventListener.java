package com.example.activitidemo.listener;

import com.example.activitidemo.utils.SpringContextHolder;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiActivityCancelledEventImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

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
 *  修改日期: 2020/10/30 11:39
 *  修改内容:
 * </pre>
 */
@Slf4j
@Component
public class ComActivitiEventListener implements ActivitiEventListener,Serializable{

    @Override
    public void onEvent(ActivitiEvent event) {
        String task2 = null;
        // TODO 暂时按照原代码逻辑支持以下几种事件
        ActivitiEventType eventType = event.getType();
        if(!ActivitiEventType.PROCESS_STARTED.equals(eventType) &&
                !ActivitiEventType.PROCESS_COMPLETED.equals(eventType) &&
                !ActivitiEventType.TASK_CREATED.equals(eventType) &&
                !ActivitiEventType.TASK_COMPLETED.equals(eventType)) {
            return;
        }

        System.out.println("进入监听器方法" + event.toString());


        try{
            if (event instanceof ActivitiEntityEvent) {
                ActivitiEntityEvent activitiEntityEvent = (ActivitiEntityEvent) event;
                Object entity = activitiEntityEvent.getEntity();
                if(entity instanceof TaskEntity) {
                    Task task = (TaskEntity)entity;
                    if ((task.getAssignee() == null || "".equals(task.getAssignee())) &&ActivitiEventType.TASK_CREATED.equals(eventType)) {
                        task2 = task.getId();
                        System.out.println("任务ID:"+task2);
                        System.out.println("任务的办理人:"+task.getAssignee());
                        System.out.println("任务名称:"+task.getName());
                        System.out.println("任务的创建时间:"+task.getCreateTime());
                        System.out.println("流程实例ID:"+task.getProcessInstanceId());
                        System.out.println("执行完成");

                        if (task2 != null) {
                                TaskService taskService = SpringContextHolder.getBean(TaskService.class);
                                taskService.complete(task2);
                        }
                    }

                }

            }
        } catch (Exception exception){
            throw exception;
        }



    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
