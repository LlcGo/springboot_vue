package com.lc.springboot_vue.controller;

import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.Menus;
import com.lc.springboot_vue.service.MenusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Lc
 * @Date 2023/4/3
 * @Description
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenusService menusService;

    @GetMapping
    public Restult<List<Menus>> getAllMenu(){
        return menusService.getAllMenu();
    }
}
