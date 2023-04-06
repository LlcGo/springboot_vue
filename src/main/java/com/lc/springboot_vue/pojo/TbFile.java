package com.lc.springboot_vue.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 文件表
 * @TableName tb_file
 */
@TableName(value ="tb_file")
@Data
public class TbFile implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 下载链接
     */
    private String url;

    /**
     * 是否删除 0不删除
     */
    @TableLogic
    private Boolean isDelete;

    /**
     * 是否禁用链接 0不禁用
     */
    private Boolean isenable;

    /**
     * 判断是否相同图片
     */
    private String md5;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}