package com.example.activitidemo.controller;

import com.example.activitidemo.service.ActivityConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

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
 *  修改日期: 2020/11/4 17:04
 *  修改内容:
 * </pre>
 */
@RestController
public class MyController {
    @Resource
    ActivityConsumerService consumerService;
    @GetMapping("/activiti")
    public Object activiti(String bytearrayId) throws IOException, ClassNotFoundException {

        return consumerService.activiti(bytearrayId);
    }
}
