package com.example.activitidemo.listener;

import com.example.activitidemo.utils.SpringContextHolder;
import jdk.nashorn.internal.ir.CallNode;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 *  修改日期: 2020/10/31 10:51
 *  修改内容:
 * </pre>
 */
@Component("wfTaskConfigService")
public class WfTaskConfigService implements  Serializable {
    //private static final long serialVersionUID = 1L;
    //只能放ExecutionEntityImpl和DelegateExecution执行实例
    public List<String> assignee(ExecutionEntityImpl execution) {
        List<TaskEntity> tasks = execution.getTasks();
        tasks.forEach(taskEntity -> System.out.println(taskEntity.getName() + taskEntity.getAssignee() + taskEntity.getId()));
        System.out.println(tasks.toString());

        System.out.println(execution.toString());
        System.out.println("=====================");
        RuntimeService runtimeService = SpringContextHolder.getBean(RuntimeService.class);
        Object variable = runtimeService.getVariable(execution.getProcessInstanceId(), "姓名");
        List<String> o = (List<String>)variable;
//        StringBuffer buffer = new StringBuffer();
//        for (int i = 0; i < o.size(); i++) {
//            buffer.append(o.get(i));
//            if (i != (o.size() - 1)) {
//                buffer.append(",");
//            }
//        }
//        System.out.println("=====================");
//        System.out.println(buffer.toString());
        return o;
    }
//    关于实体类，自己创建太麻烦，我直接使用activiti自带的类，格式一般是“xxxEntityImpl”比如execution的实体用   ExecutionEntityImpl，具体的字段信息可以跟正规流程的一一对照。这里有个坑，ExecutionEntityImpl对象的isScope属性初始化为true，必须修改为false，因为一个流程*实例只有一个主execution的isScope字段为true，其他execution的必须为false，如果设置错了，手动退回后只能正常推进一次节点，然后该execution会被异常地删除，导致流转异常。
//    execution的acti_id字段指当前正在执行的节点id，ExecutionEntityImpl对象通过setcurrentFlowElement的方式赋值，需要从流程定义中获取。
//    推进时流程的exection对象会根据这条记录获取，对象包含自身execution，主execution和活动节点activity对象

}
