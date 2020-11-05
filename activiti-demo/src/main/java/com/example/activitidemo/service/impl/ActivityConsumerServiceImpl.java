package com.example.activitidemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.activitidemo.domain.entity.ActGeBytearray;
import com.example.activitidemo.domain.entity.Bytearr;
import com.example.activitidemo.mapper.ActGeBytearrayMapper;
import com.example.activitidemo.mapper.BytearrMapper;
import com.example.activitidemo.service.ActivityConsumerService;
import com.example.activitidemo.utils.FileUtils;
import com.example.activitidemo.utils.SpringContextHolder;
import org.activiti.bpmn.model.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

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
 *  修改日期: 2020/10/20 15:22
 *  修改内容:
 * </pre>
 */
@Service
public class ActivityConsumerServiceImpl implements ActivityConsumerService {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;

    @Resource
    private RepositoryService repositoryService;
    @Resource
    ActGeBytearrayMapper actGeBytearrayMapper;
    @Resource
    BytearrMapper bytearrMapper;

    @Override
    public boolean startActivityDemo(String processDefinitionId) {
        System.out.println("method startActivityDemo begin....");

        System.out.println( "调用流程存储服务，查询部署数量："
                + repositoryService.createDeploymentQuery().count());




        List<String> list = new ArrayList<>();
        list.add("吴上班");
        list.add("吴吃饭");
        list.add("吴睡觉");

        HashMap<String, Object> map = new HashMap<>();
        map.put("proportion",0.6);
        map.put("apply","翔");
        map.put("approve","吴海");
        map.put("num",0);
        //流程启动
//        ExecutionEntity pi1 = (ExecutionEntity) runtimeService.startProcessInstanceByKey("myProcess_1",map);
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceById(processDefinitionId, map);
        System.out.println(processInstance.getId());
        runtimeService.setVariable(processInstance.getId(), "姓名", list);
//        List<Task> tq=taskService.createTaskQuery().taskAssignee("zhangsan").list();
//        System.out.println(tq.size());
//        String assignee = "zhangsan";//当前任务办理人
//        List<Task> tasks = taskService//与任务相关的Service
//                .createTaskQuery()//创建一个任务查询对象
//                .taskAssignee(assignee)
//                .list();
//        if(tasks !=null && tasks.size()>0){
//            for(Task task:tasks){
//                System.out.println("任务ID:"+task.getId());
//                System.out.println("任务的办理人:"+task.getAssignee());
//                System.out.println("任务名称:"+task.getName());
//                System.out.println("任务的创建时间:"+task.getCreateTime());
//
//                System.out.println("流程实例ID:"+task.getProcessInstanceId());
//                System.out.println("#####################################");
//            }
//        }

        System.out.println("method startActivityDemo end....");
        return false;
    }

    @Override
    public String executeTask(String name) {
//        taskService.complete("5002");
        System.out.println("123214");
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("myProcess_1").taskAssignee(name).list();
        System.out.println(tasks.size());
        if (tasks != null && tasks.size() > 0) {
            for (Task task : tasks) {
                System.out.println("任务ID:" + task.getId());
                System.out.println("任务的办理人:" + task.getAssignee());
                System.out.println("任务名称:" + task.getName());
                System.out.println("任务的创建时间:" + task.getCreateTime());
                System.out.println("流程实例ID:" + task.getProcessInstanceId());
                SpringContextHolder.getBean(TaskService.class).complete(task.getId());
                System.out.println("执行完一个………………………………66666666");
//                HashMap<String, Object> map = new HashMap<>();
//                map.put("num", 4);
//                taskService.complete(task.getId(),map);


            }

        }
        return name + "已执行";

//    String excId = task.getExecutionId();
//    ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
//   String excId = task.getExecutionId();
//ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
//String activitiId = execution.getActivityId();



/**
 * 驳回到指定节点
 * @param approvalOpinionVO    //申请流程 审批信息
 * @param task  //任务信息
 * @param map
 * @return
 */
        /*补充知识点
         *complete(String taskId, Map<String,Object> variables)
         *参数：taskId(对应act_ru_task中的id_)，variables（下一次任务所需要的参数）
         *作用：完成这一次任务，并且下一步任务需要流程变量的
         */

    }

    @Override
    public boolean rejected(String taskId, String targetID, String comment) throws Exception {

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        String currActivityId = task.getTaskDefinitionKey();
        String processDefinitionId = task.getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        FlowNode currFlow = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currActivityId);

        if (null == currFlow) {
            List<SubProcess> subProcessList = bpmnModel.getMainProcess().findFlowElementsOfType(SubProcess.class, true);
            for (SubProcess subProcess : subProcessList) {
                FlowElement flowElement = subProcess.getFlowElement(currActivityId);
                if (flowElement != null) {
                    currFlow = (FlowNode) flowElement;
                    break;
                }
            }
        }
//        //获取目标节点
//        FlowNode targetFlow = (FlowNode) bpmnModel.getMainProcess().getFlowElement(targetID);
        HistoricTaskInstance historicTask = historyService.createHistoricTaskInstanceQuery().taskId(targetID).singleResult();
        FlowNode targetFlow = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicTask.getTaskDefinitionKey());


        System.out.println("当前" + task.getName());
        System.out.println("目标" + historicTask.getName());
        //如果不是同一个流程(子流程)不能驳回
        if (!(task.getExecutionId().equals(historicTask.getExecutionId()))) {
            throw new Exception("此处无法进行驳回操作");
        }

        //记录原活动方向
        List<SequenceFlow> oriSequenceFlows = new ArrayList();
        oriSequenceFlows.addAll(currFlow.getOutgoingFlows());

        //清理活动方向
        currFlow.getOutgoingFlows().clear();

        //建立新的方向
        List<SequenceFlow> newSequenceFlows = new ArrayList();
        SequenceFlow newSequenceFlow = new SequenceFlow();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        newSequenceFlow.setId(uuid);
        newSequenceFlow.setSourceFlowElement(currFlow);  //原节点
        newSequenceFlow.setTargetFlowElement(targetFlow);  //目标节点
        newSequenceFlows.add(newSequenceFlow);
        currFlow.setOutgoingFlows(newSequenceFlows);

        //完成节点任务
        taskService.complete(task.getId());
        //恢复原方向
        currFlow.setOutgoingFlows(oriSequenceFlows);
        return true;
    }

    @Override
    public Object activiti(String id) throws IOException, ClassNotFoundException {

//        //String str = new String(actGeBytearrayMapper.selectById(id).getBytes(),"UTF-8");
//        File file=new File("D:\\MyData\\wuhx29\\Desktop\\b.txt");
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
//        Object o = in.readObject();
//        System.out.println(o.toString());
        ActGeBytearray actGeBytearray = actGeBytearrayMapper.selectById(id);
        byte[] bytes = actGeBytearray.getBytes();

        //byte[] bytes = FileUtils.getContent("D:\\MyData\\wuhx29\\Desktop\\b.txt");
        Object o = unserialize(bytes);
        System.out.println(o.toString());
        return o;
    }

    @Override
    public Object bytearr(String id) {
        Bytearr actGeBytearray = bytearrMapper.selectById(id);
        byte[] bytes = actGeBytearray.getC5();
        Object o = unserialize(bytes);
        System.out.println(o.toString());
        return o;
    }

    public Object unserialize(byte[] bytes)
    {
        ByteArrayInputStream bais = null;
        if (bytes != null) {
            try {
                // 反序列化
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
        return null;
    }


}