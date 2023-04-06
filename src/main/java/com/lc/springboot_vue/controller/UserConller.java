package com.lc.springboot_vue.controller;

import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.User;
import com.lc.springboot_vue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Lc
 * @Date 2023/3/30
 * @Description
 * http://localhost:80/user
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserConller {
    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    //登录返回token
    @PostMapping("/login")
    public Restult<HashMap<String, Object>> login(@RequestBody User user){
        return userService.login(user);
    }

    //登录返回权限与用户信息
    @GetMapping("/info")
    public Restult<HashMap<String, Object>> info(@RequestParam("token") String token){
        return userService.info(token);
    }

    //退出登录
    @PostMapping("/logout")
    public Restult<String> logout(@RequestHeader("X-Token") String token){
        return userService.logout(token);
    }

    //获取分页用的数据
    @GetMapping("/list")
    public Restult<HashMap<String,Object>> list(@RequestParam("pageNo") Integer pageNo,
                                                @RequestParam("pageSize") Integer pageSize,
                                                @RequestParam(value = "username",required = false) String username,
                                                @RequestParam(value = "phone",required = false) String phone){
        return userService.getlist(pageNo,pageSize,username,phone);
    }

    //添加用户
    @PostMapping
    public Restult<?> addUser(@RequestBody User user){
        log.info("user信息:{}",user);
        //每次传入之后跟第一次传入不一样 直接加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return Restult.success("添加成功");
    }

    //删除用户
    @DeleteMapping("/delete")
    public Restult<String> delete(@RequestParam Long id){
        userService.removeRoleAndUserById(id);
        return Restult.success("删除成功");
    }

    //修改用户
    @PutMapping
    public Restult<String> update(@RequestBody User user){
        //如果存入的password为null是不会更新的
        user.setPassword(null);
        userService.updateRoleAndUserById(user);
        return Restult.success("修改成功");
    }

    //根据id查询信息 回显编辑用户的信息
    @GetMapping("/{id}")
    public Restult<?> getUserById(@PathVariable("id") Long id){
        User user = userService.getRoleAndUserById(id);
        return Restult.success(user);
    }
}
