package com.lc.springboot_vue.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 权限映射联系表
 * @TableName tb_menu_role
 */
@TableName(value ="tb_menu_role")
@Data
public class MenuToRole implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 权限id
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 能看哪些信息
     */
    @TableField("menu_id")
    private Integer menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}