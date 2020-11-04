package com.example.activitidemo.callback;

import com.example.activitidemo.callback.annotations.UserScript;
import com.example.activitidemo.utils.SpringContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
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
 *  修改日期: 2020/11/3 18:44
 *  修改内容:
 * </pre>
 */
@Component
public class Demo {
    @Resource
    private  ApplicationContext applicationContext;
   // @PostConstruct
    public void test() {
        System.out.println("21435t4653");
//        try {
//            Class<?> clazz = Class.forName("com.example.activitidemo.callback.Test");
//            //获取本类的所有方法，存放入数组
//            Method[] methods = clazz.getDeclaredMethods();
//            for (Method method : methods) {
//                System.out.println("方法名：" + method.getName());
//                //获取本方法所有参数类型，存入数组
//                Class<?>[] getTypeParameters = method.getParameterTypes();
//                if (getTypeParameters.length == 0) {
//                    System.out.println("此方法无参数");
//                }
//                for (Class<?> class1 : getTypeParameters) {
//                    String parameterName = class1.getName();
//                    System.out.println("参数类型：" + parameterName);
//                }
//                System.out.println("****************************");
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }

}