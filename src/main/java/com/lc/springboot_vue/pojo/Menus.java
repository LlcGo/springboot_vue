package com.lc.springboot_vue.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * ？？？
 * @TableName tb_menu
 */
@TableName(value ="tb_menu")
@Data
public class Menus implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    @TableField("menu_id")
    private Integer menuId;

    /**
     * 组件
     */
    private String component;

    /**
     * 组件的路径
     */
    private String path;

    /**
     * 重定向路径
     */
    private String redirect;

    /**
     * 组件的名字
     */
    private String name;

    /**
     * 列表名字
     */
    private String title;

    /**
     * 图片
     */
    private String icon;

    /**
     * 子组件id
     */
    private Integer parentid;

    /**
     * 是否有叶子节点
     */
    private String isleaf;

    /**
     * 隐藏
     */
    private boolean hidden;

    /**
     * 子节点
     * JsonInclude.Include.NON_EMPTY非空才返回
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Menus> children;

    /**
     * 前端的meta
     */
    @TableField(exist = false)
    private Map<String,Object> meta;
    public Map<String,Object> getmeta(){
        meta = new HashMap<>();
        meta.put("title",title);
        meta.put("icon",icon);
        return meta;
    }


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}