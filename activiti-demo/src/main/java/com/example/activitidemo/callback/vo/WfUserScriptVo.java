package com.example.activitidemo.callback.vo;

import lombok.Data;

import java.util.List;
@Data
public class WfUserScriptVo {

    // 类的绝对路径
    private String classPath;

    //bean 名字
    private String beanName;

    // 方法名
    private List<WfMethodVo> methodNames;

}
