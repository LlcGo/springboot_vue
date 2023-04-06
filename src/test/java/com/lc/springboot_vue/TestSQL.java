package com.lc.springboot_vue;

import com.lc.springboot_vue.mapper.UserMapper;
import com.lc.springboot_vue.pojo.MenuToRole;
import com.lc.springboot_vue.pojo.Menus;
import com.lc.springboot_vue.pojo.Role;
import com.lc.springboot_vue.pojo.User;
import com.lc.springboot_vue.service.MenuToRoleService;
import com.lc.springboot_vue.service.MenusService;
import com.lc.springboot_vue.service.RoleService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;
import sun.awt.SunHints;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Lc
 * @Date 2023/4/3
 * @Description
 */
@SpringBootTest
public class TestSQL {
    @Resource
    private RoleService roleService;

    @Resource
    private MenusService menusService;

    @Resource
    private MenuToRoleService menuToRoleService;

    @Resource
    private UserMapper userMapper;

    @Test
    void testSelect(){
        List<Role> list = roleService.list();
        System.out.println(list);
    }

    @Test
    void INSETRT(){
        Role role = new Role();
        role.setRoleDescribe("6");
        role.setRoleName("66");
        boolean save = roleService.save(role);
        Assert.assertEquals(true,save);
    }

    @Test
    void menuTest(){
        List<Menus> list = menusService.list();
        System.out.println(list);
    }

    @Test
    void mToR(){
        MenuToRole menuToRole = new MenuToRole();
        menuToRole.setRoleId(1);
        menuToRole.setRoleId(1);
        menuToRoleService.save(menuToRole);
    }

    @Test
    void testPage(){
        //看第一页，5条数据
//        Integer page = (1 - 1) * 5;
        List<User> users = userMapper.getLikeByClassName("唱歌", null, 0, 5);
        System.out.println(users);
    }
}
