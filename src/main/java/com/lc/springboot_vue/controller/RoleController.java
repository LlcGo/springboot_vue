package com.lc.springboot_vue.controller;

import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.Role;
import com.lc.springboot_vue.pojo.User;
import com.lc.springboot_vue.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Lc
 * @Date 2023/4/3
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    public Restult<HashMap<String,Object>> list(@RequestParam("pageNo") Integer pageNo,
                                                @RequestParam("pageSize") Integer pageSize,
                                                @RequestParam(value = "roleName",required = false) String roleName
                                                ){
        return roleService.getPage(pageNo,pageSize,roleName);
    }

    //添加用户
    @PostMapping
    public Restult<?> addRole(@RequestBody Role role){
        roleService.addRole(role);
        return Restult.success("添加成功");
    }

    //删除用户
    @DeleteMapping("/delete")
    public Restult<String> delete(@RequestParam Integer id){
        roleService.removeMenuAndRoleToById(id);
        return Restult.success("删除成功");
    }

    //修改权限信息
    @PutMapping
    public Restult<String> update(@RequestBody Role role){
        return roleService.updateToroleById(role);
    }

    //根据id查询信息 回显编辑用户的信息
    @GetMapping("/{id}")
    public Restult<?> getUserById(@PathVariable("id") Long id){
        Role role = roleService.getRoleById(id);
        return Restult.success(role);
    }

    //获取所有role信息
    @GetMapping
    public Restult<List<Role>> getAll(){
        List<Role> list = roleService.list();
        return Restult.success(list);
    }

}
