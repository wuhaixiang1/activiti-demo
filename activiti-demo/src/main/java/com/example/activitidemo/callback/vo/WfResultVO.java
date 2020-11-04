package com.example.activitidemo.callback.vo;

import lombok.Data;
import org.springframework.util.StringUtils;

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
 *  修改日期: 2020/11/3 23:00
 *  修改内容:
 * </pre>
 */
@Data
public class WfResultVO {
    private String processKey;

    private String callbackName;

    private String description;

    private String beanName;

    private String application;
}
