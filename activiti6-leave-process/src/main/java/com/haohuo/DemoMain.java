package com.haohuo;

import com.google.common.collect.Maps;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther: Zhang Peike
 * @Date: 2019/7/3 09:48
 */
public class DemoMain {
    private static final Logger logger = LoggerFactory.getLogger(DemoMain.class);

    public static void main(String[] args) throws ParseException {
        logger.info("开始请假流程 . . .");
        // 创建流程引擎
        ProcessEngine processEngine = getProcessEngine();

        // 部署流程定义文件
        ProcessDefinition processDefinition = getProcessDefinition(processEngine);

        // 启动运行流程
        ProcessInstance processInstance = getProcessInstance(processEngine, processDefinition);

        // 处理流程任务
        processTask(processEngine, processInstance);
        logger.info("结束请假流程 . . ");
    }

    /**
     * 处理流程任务
     * @param processEngine
     * @param processInstance
     * @throws ParseException
     */
    private static void processTask(ProcessEngine processEngine, ProcessInstance processInstance) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while (processInstance != null && !processInstance.isEnded()) {   //判断流程不为空，且流程没有结束
            TaskService taskService = processEngine.getTaskService();
            List<Task> list = taskService.createTaskQuery().list();    //列出当前需要处理的任务
            logger.info("待处理任务数量 [{}]", list.size());
            for (Task task : list) {

                logger.info("待处理任务 [{}]", task.getName());
                Map<String, Object> variables = getMap(processEngine, scanner, task);     //获取用户的输入信息
                taskService.complete(task.getId(),variables);
                processInstance = processEngine.getRuntimeService().createProcessInstanceQuery()
                        .processInstanceId(processInstance.getId()).singleResult();
            }
        }
        scanner.close();
    }

    /**
     * 获取用户的输入信息
     * @param processEngine
     * @param scanner
     * @param task
     * @return
     * @throws ParseException
     */
    private static Map<String, Object> getMap(ProcessEngine processEngine, Scanner scanner, Task task) throws ParseException {
        FormService formService = processEngine.getFormService();    //通过formService来获取form表单输入
        TaskFormData taskFormData = formService.getTaskFormData (task.getId());
        List<FormProperty> formProperties = taskFormData.getFormProperties();    //获取taskFormData的表单内容
        Map<String,Object> variables = Maps.newHashMap();    //这个Map键值对来存对应表单用户输入的内容
        for (FormProperty property : formProperties){   //property为表单中的内容
            String line = null;    //这里获取输入的信息
            if(StringFormType.class.isInstance(property.getType())){   //如果是String类型的话
                logger.info("请输入 [{}] ?" , property.getName());    //输入form表单的某一项内容
                line = scanner.nextLine();
                variables.put(property.getId(),line);
            }else if(DateFormType.class.isInstance(property.getType())){   //如果是日期类型的话
                logger.info("请输入 [{}] ? 格式为（yyyy-MM-dd）" , property.getName());    //输入form表单的某一项内容
                line = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   //设置输入的日期格式
                Date date = dateFormat.parse(line);
                variables.put(property.getId(),date);
            }else{
                logger.info("类型不支持 [{}]",property.getType());
            }
            logger.info("您输入的内容是 [{}] " , line);
        }
        return variables;
    }


    /**
     * 启动运行流程
     *
     * @param processEngine
     * @param processDefinition
     */
    private static ProcessInstance getProcessInstance(ProcessEngine processEngine, ProcessDefinition processDefinition) {
        RuntimeService runtimeService = processEngine.getRuntimeService();   //启动流程要有一个运行时对象
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());   //这里我们根据processDefinition的ID来启动
        logger.info("启动流程 [{}]", processInstance.getProcessDefinitionKey());
        return processInstance;
    }

    /**
     * 部署流程定义文件
     *
     * @param processEngine
     * @return
     */
    private static ProcessDefinition getProcessDefinition(ProcessEngine processEngine) {
        RepositoryService repositoryService = processEngine.getRepositoryService();   //创建一个对流程编译库操作的Service
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();   //获取一个builder
        deploymentBuilder.addClasspathResource("LeaveProcess.bpmn");   //这里写上流程编译路径
        Deployment deployment = deploymentBuilder.deploy();    //部署
        String deploymentId = deployment.getId();    //获取deployment的ID
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();     //根据deploymentId来获取流程定义对象
        logger.info("流程定义文件 [{}] , 流程ID [{}]", processDefinition.getName(), processDefinition.getId());
        return processDefinition;
    }

    /**
     * 创建流程引擎
     *
     * @return
     */
    private static ProcessEngine getProcessEngine() {
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();   //创建默认的基于内存数据库的流程引擎配置对象
        ProcessEngine processEngine = cfg.buildProcessEngine();    //构造流程引擎
        String engineName = processEngine.getName();   //获取流程引擎的name
        String version = ProcessEngine.VERSION;    //获取流程引擎的版本信息

        logger.info("流程引擎名称 [{}], 版本 [{}]", engineName, version);
        return processEngine;
    }

}
