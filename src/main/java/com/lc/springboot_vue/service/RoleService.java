package com.lc.springboot_vue.service;

import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 *
 */
public interface RoleService extends IService<Role> {

    Restult<HashMap<String, Object>> getPage(Integer pageNo, Integer pageSize, String roleName);

    void addRole(Role role);

    Role getRoleById(Long id);

    Restult<String> updateToroleById(Role role);

    void removeMenuAndRoleToById(Integer id);
}
