package com.lc.springboot_vue.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.List;

/**
 * @Author Lc
 * @Date 2023/3/29
 * @Description
 */
@Data
@TableName("tb_user")
public class User {
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer status; //用户状态
    private String avatar;  //用户头像
    @TableLogic
    private String isdelete; //用户是否删除
    @TableField(exist = false)
    private List<Integer> roleIdList;
}
