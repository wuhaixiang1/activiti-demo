package com.example.activitidemo.config;

import com.example.activitidemo.listener.ComActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
 *  修改日期: 2020/10/30 13:12
 *  修改内容:
 * </pre>
 */
@Component

public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    @Autowired

    private ComActivitiEventListener comActivitiEventListener;

    @Override

    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {

        List<ActivitiEventListener>  activitiEventListener=new ArrayList<ActivitiEventListener>();

        activitiEventListener.add(comActivitiEventListener );//配置全局监听器

        processEngineConfiguration.setEventListeners(activitiEventListener);

    }

}