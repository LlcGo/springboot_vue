package com.lc.springboot_vue.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 权限表
 * @TableName tb_role
 */
@TableName(value ="tb_role")
@Data
public class Role implements Serializable {
    /**
     * 权限id
     */
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    /**
     * 权限名
     */
    private String roleName;

    /**
     * 管理员信息
     */
    private String roleDescribe;

    /**
     * 接收前端传过来的权限表
     */
    @TableField(exist = false)
    private List<Integer> menuiIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}