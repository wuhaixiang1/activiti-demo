package com.example.activitidemo.callback.vo;

import lombok.Data;

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
 *  修改日期: 2020/11/3 22:12
 *  修改内容:
 * </pre>
 */
@Data
public class WfMethodVo {
    //方法名
    private String methodName;
    // 参数信息
    private List<WfParamVo> paramVoList;
}
