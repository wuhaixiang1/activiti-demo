package com.example.activitidemo.service;

import com.example.activitidemo.mapper.ActGeBytearrayMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
 *  修改日期: 2020/10/20 15:20
 *  修改内容:
 * </pre>
 */
//@RestController
//@RequestMapping("/test")
//public interface ActivityConsumerService {
//    /**
//     * 流程demo
//     * @return
//     */
//    @RequestMapping(value="/activitiDemo",method= RequestMethod.GET)
//    public boolean startActivityDemo();
//
//}
public interface ActivityConsumerService {



    Object activiti(String id) throws IOException, ClassNotFoundException;


}