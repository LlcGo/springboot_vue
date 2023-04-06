package com.lc.springboot_vue.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 兴趣课程
 * @TableName tb_class
 */
@TableName(value ="tb_class")
@Data
public class LikeClass implements Serializable {
    /**
     * 课程id
     */
    @TableId(type = IdType.AUTO)
    private Integer cid;

    /**
     * 
     */
    private String cname;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 学员id
     */
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}