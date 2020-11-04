package com.example.activitidemo.callback.dto;

import lombok.Data;

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
 *  修改日期: 2020/11/3 23:44
 *  修改内容:
 * </pre>
 */
@Data
public class WfRequestParam {
    private String classPath;
    private String methodName;
    private String beanName;
    private Object[] params;
    private String[] paramTypes;
}
