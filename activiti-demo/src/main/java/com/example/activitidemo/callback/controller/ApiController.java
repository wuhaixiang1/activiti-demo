package com.example.activitidemo.callback.controller;

import com.alibaba.fastjson.JSON;
import com.example.activitidemo.callback.annotations.UserScript;
import com.example.activitidemo.callback.dto.WfRequestParam;
import com.example.activitidemo.callback.register.WfUserScriptRegistry;
import com.example.activitidemo.callback.vo.WfUserScriptVo;
import com.example.activitidemo.utils.SpringContextHolder;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/callBack")
public class ApiController {

    @Resource
    private  ApplicationContext applicationContext;

    /**
     * 返回业务端所有的用户查找脚本
     * @return
     */
    @GetMapping("/getAllScript")
    public Map<String, LinkedHashSet<WfUserScriptVo>>  getAllScript(){
        return WfUserScriptRegistry.getRegistry();
    }


    /**
     * 根据传入的json格式拿到类的信息并反射调用，返回结果
     * @param
     * @return
     */
    @PostMapping("/execute")
    public Object execute(@RequestBody WfRequestParam requestParam){
        Object result = null;
        try {
        Class<? extends Object>[] paramClass = null;
        Object[] params = requestParam.getParams();
            Object[] objects = new Object[params.length];
            String[] paramTypes = requestParam.getParamTypes();
        if (params != null) {
            int paramsLength = params.length;
            paramClass = new Class[paramsLength];
            for (int i = 0; i < paramsLength; i++) {
                paramClass[i] = Class.forName(paramTypes[i]);
                String s = JSON.toJSONString(params[i]);
                Object o = JSON.parseObject(s, paramClass[i]);
                objects[i] = o;
            }
        }
        String classPath = requestParam.getClassPath();
        String methodName = requestParam.getMethodName();
            // 找到方法
            Method method = ReflectionUtils.findMethod(Class.forName(classPath), methodName, paramClass);
            // 执行方法
            result = ReflectionUtils.invokeMethod(method, SpringContextHolder.getBean(requestParam.getBeanName()), objects);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}
