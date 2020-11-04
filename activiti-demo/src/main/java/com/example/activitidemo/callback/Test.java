package com.example.activitidemo.callback;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.lang.reflect.Array;
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
 *  修改日期: 2020/11/3 19:02
 *  修改内容:
 * </pre>
 */
@Data
public class Test {
    // 参数中文名
    private String paramName;
    private int numb;

    public void man() {
        Object[] objects = new Object[2];
        objects[0] = "吴海翔";
        Test test = new Test();
        test.setParamName("测试");
        test.setNumb(25);
        objects[1] = test;
        String s = JSON.toJSONString(objects);
        System.out.println(s);
    }
}
