package com.example.activitidemo.callback.register;

import com.example.activitidemo.callback.annotations.ScriptMethod;
import com.example.activitidemo.callback.annotations.ScriptParam;
import com.example.activitidemo.callback.annotations.UserScript;
import com.example.activitidemo.callback.vo.WfMethodVo;
import com.example.activitidemo.callback.vo.WfParamVo;
import com.example.activitidemo.callback.vo.WfUserScriptVo;
import com.example.activitidemo.utils.SpringContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class WfUserScriptRegistry {

    @Resource
    private ApplicationContext applicationContext;


    private final static Map<String, LinkedHashSet<WfUserScriptVo>> registry = new LinkedHashMap<>();


    // bean 加载初始化方法，去拿到所有@UserScript的类
    @PostConstruct
    public void init(){
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(UserScript.class);
        LinkedHashSet<WfUserScriptVo> wfUserScriptVos = new LinkedHashSet<>();
        String string = "类信息";

        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("================");
            Class objClass = entry.getValue().getClass();
            Method[] declaredMethods = objClass.getDeclaredMethods();

            WfUserScriptVo wfUserScriptVo = new WfUserScriptVo();
            wfUserScriptVo.setClassPath(objClass.getName());
            wfUserScriptVo.setBeanName(entry.getKey());
            List<WfMethodVo> wfMethodVos = new ArrayList<>();

            for (Method method : declaredMethods) {
                if(method.isAnnotationPresent(ScriptMethod.class)){
                    WfMethodVo wfMethodVo = new WfMethodVo();
                    wfMethodVo.setMethodName(method.getName());
                    List<WfParamVo> wfParamVos = new ArrayList<>();

                    Class[] typeParameters = method.getParameterTypes();
                    Annotation[][] parameterAnnotations = method.getParameterAnnotations();

                    if (parameterAnnotations != null && parameterAnnotations.length > 0) {
                        for (int i = 0; i < parameterAnnotations.length; i++) {
                            for (Annotation annotation : parameterAnnotations[i]) {
                                if (annotation instanceof ScriptParam) {
                                    ScriptParam param = (ScriptParam) annotation;
                                    WfParamVo wfParamVo = new WfParamVo();
                                    wfParamVo.setParamName(param.value());
                                    wfParamVo.setParamType(typeParameters[i]);
                                    wfParamVos.add(wfParamVo);
                                }
                            }
                        }
                    }
                    wfMethodVo.setParamVoList(wfParamVos);
                    wfMethodVos.add(wfMethodVo);
                }
            }
            wfUserScriptVo.setMethodNames(wfMethodVos);
            wfUserScriptVos.add(wfUserScriptVo);
        }
        registry.put(string, wfUserScriptVos);
    }



    public static  Map<String, LinkedHashSet<WfUserScriptVo>>  getRegistry(){return registry;}

}

//    //获取该类下面所有的字段
//    Field[] fields = objClass.getDeclaredFields();
//
////遍历所有字段
//for (Field field : fields) {
//        //如果存在该注解
//        if(field.isAnnotationPresent(DatabaseField.class)){
//        DatabaseField dt = field.getAnnotation(DatabaseField.class);
//        System.out.println("DatabaseField="+dt.columnName());
//        }
