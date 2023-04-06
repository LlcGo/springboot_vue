package com.lc.springboot_vue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.User;

import java.util.HashMap;

public interface UserService extends IService<User> {
    /**
     * 登录校验
     * @param user  前端传入用户名和密码
     * @return token /
     */
    Restult<HashMap<String, Object>> login(User user);

    /**
     * 登陆时候需要获取这个当时用户的详细信息
     * @param token 前端发送给后端校验的一个token
     * @return 用户所有信息
     */
    Restult<HashMap<String, Object>> info(String token);

    /**
     * 删除token
     * @param token 前端发送给后端的用户token
     * @return
     */
    Restult<String> logout(String token);

    /**
     *  查询用户列表
     * @param pageNo 当前页码
     * @param pageSize 每页显示的条数
     * @param username 用户名
     * @param phone 手机号
     * @return 所有查询到的数据与总页数，total返回的是long类型设置了json转换器会直接转成string类型
     * 防止前端解析后丢失精度，前端接收后要用Number转换为long
     */
    Restult<HashMap<String, Object>> getlist(Integer pageNo, Integer pageSize, String username, String phone);

    void addUser(User user);

    User getRoleAndUserById(Long id);

    void removeRoleAndUserById(Long id);

    void updateRoleAndUserById(User user);

}
