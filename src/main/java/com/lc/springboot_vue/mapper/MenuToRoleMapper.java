package com.lc.springboot_vue.mapper;

import com.lc.springboot_vue.pojo.MenuToRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.lc.springboot_vue.pojo.MenuToRole
 */
public interface MenuToRoleMapper extends BaseMapper<MenuToRole> {
     List<Integer> getRoleById(Integer roleId);

     void  deleteMenubyRoleId(Integer roleId);
}




