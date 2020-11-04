package com.example.activitidemo.callback.service;

import com.example.activitidemo.callback.Demo;
import com.example.activitidemo.callback.Test;
import com.example.activitidemo.callback.annotations.ScriptMethod;
import com.example.activitidemo.callback.annotations.ScriptParam;
import com.example.activitidemo.callback.annotations.UserScript;
import com.example.activitidemo.callback.vo.WfParamVo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@UserScript("用户查找脚本")
@Component
public class UserScriptImpl {
    @Resource
    private Demo demo;

   @ScriptMethod("根据部门类型和角色类型查找员工信息")
    public String getUser(@ScriptParam("部门类型") String deptType, @ScriptParam("角色类型") Test roleType){
       System.out.println(roleType.toString());
       demo.test();
        return "成功回调getUser";
    }

    @ScriptMethod("根据部门类型和角色类型查找员工信息")
    public String user(@ScriptParam("部门") String dept, @ScriptParam("角色") String role){
        System.out.println(dept.toString());
        System.out.println(dept);
        return "成功回调User";
    }

}
