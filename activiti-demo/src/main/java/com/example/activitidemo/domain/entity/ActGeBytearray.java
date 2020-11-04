package com.example.activitidemo.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
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
 *  修改日期: 2020/11/4 11:32  
 *  修改内容: 
 * </pre>
 */
@Data
@TableName(value = "act_ge_bytearray")
public class ActGeBytearray {
    @TableId(value = "ID_", type = IdType.INPUT)
    private String id;

    @TableField(value = "REV_")
    private Integer rev;

    @TableField(value = "NAME_")
    private String name;

    @TableField(value = "DEPLOYMENT_ID_")
    private String deploymentId;

    @TableField(value = "BYTES_")
    private byte[] bytes;

    @TableField(value = "GENERATED_")
    private Byte generated;

    public static final String COL_ID_ = "ID_";

    public static final String COL_REV_ = "REV_";

    public static final String COL_NAME_ = "NAME_";

    public static final String COL_DEPLOYMENT_ID_ = "DEPLOYMENT_ID_";

    public static final String COL_BYTES_ = "BYTES_";

    public static final String COL_GENERATED_ = "GENERATED_";
}