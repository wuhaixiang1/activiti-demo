package com.example.activitidemo.callback.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

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
 *  修改日期: 2020/11/3 19:01
 *  修改内容:
 * </pre>
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface CallBack {
    String name() default "";
}


