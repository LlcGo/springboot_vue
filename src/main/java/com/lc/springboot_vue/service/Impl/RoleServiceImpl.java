package com.lc.springboot_vue.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.mapper.MenuToRoleMapper;
import com.lc.springboot_vue.pojo.MenuToRole;
import com.lc.springboot_vue.pojo.Role;
import com.lc.springboot_vue.service.MenuToRoleService;
import com.lc.springboot_vue.service.RoleService;
import com.lc.springboot_vue.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Resource
    private MenuToRoleService menuToRoleService;

    @Resource
    private MenuToRoleMapper menuToRoleMapper;

    @Override
    public Restult<HashMap<String, Object>> getPage(Integer pageNo, Integer pageSize, String roleName) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(roleName),Role::getRoleName,roleName);
        Page<Role> rolePage = new Page<>(pageNo, pageSize);
        Page<Role> page = this.page(rolePage, queryWrapper);

        HashMap<String, Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Restult.success(data);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        //role传入数据库
        this.baseMapper.insert(role);
        //将数据传入menulist表
        List<Integer> menusList = role.getMenuiIdList();
        Integer roleId = role.getRoleId();
        for (Integer menu_id : menusList) {
            insertMenuRole(roleId, menu_id);
        }
    }

    private void insertMenuRole(Integer roleId, Integer menu_id) {
        //insert into tb_menu_role vales(null,roleid,menus.id)
        MenuToRole menuToRole = new MenuToRole();
        menuToRole.setRoleId(roleId);
        menuToRole.setMenuId(menu_id);
        menuToRoleService.save(menuToRole);
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = this.getById(id);
        List<Integer> menuToRoleMapperRoleById = menuToRoleMapper.getRoleById(role.getRoleId());
        role.setMenuiIdList(menuToRoleMapperRoleById);
        return role;
    }

    @Override
    public Restult<String> updateToroleById(Role role) {
        //1.先修改role的信息
        this.updateById(role);
        //2.再根据roleid修改menu_role对应的表
        List<Integer> menuiIdList = role.getMenuiIdList();
        Integer roleId = role.getRoleId();
        //3.如果里面设置的信息为空直接删除所有权限
        if (menuiIdList.size() == 0){
            //删除roleid所有的关联权限
            menuToRoleMapper.deleteMenubyRoleId(roleId);
            return Restult.success("修改成功");
        }
        //4.如果有要修改的信息，先删除原来的信息再添加现在要的权限
        menuToRoleMapper.deleteMenubyRoleId(roleId);
        for (Integer menuId : menuiIdList) {
            insertMenuRole(roleId,menuId);
        }
        return Restult.success("修改成功");
    }

    @Override
    public void removeMenuAndRoleToById(Integer id) {
        //删除role信息
        this.removeById(id);
        //删除role关联的menu表信息
        menuToRoleMapper.deleteMenubyRoleId(id);
    }
}




