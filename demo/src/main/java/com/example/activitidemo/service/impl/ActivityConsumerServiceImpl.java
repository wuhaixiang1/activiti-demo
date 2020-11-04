package com.example.activitidemo.service.impl;
import com.example.activitidemo.domain.entity.ActGeBytearray;
import com.example.activitidemo.mapper.ActGeBytearrayMapper;
import com.example.activitidemo.service.ActivityConsumerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

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
 *  修改日期: 2020/10/20 15:22
 *  修改内容:
 * </pre>
 */
@Service
public class ActivityConsumerServiceImpl implements ActivityConsumerService {

   @Resource
    ActGeBytearrayMapper actGeBytearrayMapper;
    @Override
    public Object activiti(String id) throws IOException, ClassNotFoundException {

//        //String str = new String(actGeBytearrayMapper.selectById(id).getBytes(),"UTF-8");
//        File file=new File("D:\\MyData\\wuhx29\\Desktop\\b.txt");
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
//        Object o = in.readObject();
//        System.out.println(o.toString());
        ActGeBytearray actGeBytearray = actGeBytearrayMapper.selectById(id);
        byte[] bytes = actGeBytearray.getBytes();

        //byte[] bytes = FileUtils.getContent("D:\\MyData\\wuhx29\\Desktop\\b.txt");
        Object o = unserialize(bytes);
        System.out.println(o.toString());
        return o;
    }

    public Object unserialize(byte[] bytes)
    {
        ByteArrayInputStream bais = null;
        if (bytes != null) {
            try {
                // 反序列化
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
        return null;
    }


}