package com.example.activitidemo.controller;

import com.example.activitidemo.service.ActivityConsumerService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
 *  修改日期: 2020/10/20 15:49
 *  修改内容:
 * </pre>
 */
@RestController
@RequestMapping("/test")
public class ActivitiController {
    @Resource
    TaskService taskService;
    @Resource
    ActivityConsumerService consumerService;

    @RequestMapping(value="/demo",method= RequestMethod.GET)
    public String demo(String processDefinitionId) {
        consumerService.startActivityDemo(processDefinitionId);
        return "已启动流程";
    }
    @RequestMapping(value="/execute",method= RequestMethod.GET)
    public String execute(@RequestParam String name) {
        return consumerService.executeTask(name);
    }
    @GetMapping("/deploy")
    public String deploy(String name, String bpm) {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/" + bpm)  //添加bpmn资源
//                .addClasspathResource("diagram/holiday4.png")
                .name(name)
                .deploy();

        //4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
        return "部署成功";
    }
    @GetMapping("/complete")
    public String completeByTaskId(String taskId) {
        taskService.complete(taskId);
        return "已操作";
    }
    @RequestMapping("/reject")
    public String reject(String taskId, String targetID, String comment) {
        try {
            consumerService.rejected(taskId, targetID, comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "执行成功";
    }
}
