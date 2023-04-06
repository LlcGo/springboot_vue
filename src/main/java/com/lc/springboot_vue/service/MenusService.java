package com.lc.springboot_vue.service;

import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.Menus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface MenusService extends IService<Menus> {

    /**
     * 获得所有的menu信息
     * @return 所有的节点除了父节点
     */
    Restult<List<Menus>> getAllMenu();

    /**
     * 前端发送用户的id
     * @param id 接收前端的id
     * @return 根据id查询他的拥有的所有的路由表
     */
    List<Menus> getMuneListById(Long id);
}
